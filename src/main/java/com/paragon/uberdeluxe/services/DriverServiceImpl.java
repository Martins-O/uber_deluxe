package com.paragon.uberdeluxe.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.paragon.uberdeluxe.cloud.CloudinaryCloudService;
import com.paragon.uberdeluxe.data.dto.request.RegisterDriverRequest;
import com.paragon.uberdeluxe.data.dto.response.RegisterResponse;
import com.paragon.uberdeluxe.data.models.AppUser;
import com.paragon.uberdeluxe.data.models.Driver;
import com.paragon.uberdeluxe.data.repositories.DriverRepository;
import com.paragon.uberdeluxe.exception.BusinessLogicException;
import com.paragon.uberdeluxe.exception.ImageUploadException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@Slf4j
public class DriverServiceImpl implements DriverService{


    private final DriverRepository driverRepository;
    private final CloudinaryCloudService cloudinaryCloudService;
    private final ModelMapper modelMapper;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository, CloudinaryCloudService cloudinaryCloudService, ModelMapper modelMapper) {
        this.driverRepository = driverRepository;
        this.cloudinaryCloudService = cloudinaryCloudService;
        this.modelMapper = modelMapper;
    }


    @Override
    public RegisterResponse register(RegisterDriverRequest driverRequest, MultipartFile licenseImage) {
        AppUser user = modelMapper.map(driverRequest, AppUser.class);
        user.setCreatedAt(LocalDateTime.now().toString());
        var imageUrl = cloudinaryCloudService.uploadCloud(licenseImage);
        if (imageUrl == null)
            throw new ImageUploadException("Driver Registration failed");
        Driver driver = Driver.builder()
                .userDetails(user)
                .licenseID(imageUrl)
                .build();
        Driver saved = driverRepository.save(driver);
        log.info("saved ->{}", saved);
        return getRegisterResponse(saved);
    }

    @Override
    public Driver getDriverById(Long driverId) {
        return driverRepository.findById(driverId).orElseThrow(()->
            new BusinessLogicException(String.format("User %s not found", driverId))
        );
    }

    @Override
    public Driver updateDriver(Long driverId, JsonPatch updatePatch) {
        ObjectMapper mapper = new ObjectMapper();
        Driver foundDriver = getDriverById(driverId);
        JsonNode node = mapper.convertValue(foundDriver, JsonNode.class);
        try{
            JsonNode updateNode = updatePatch.apply(node);
            var updatedDriver = mapper.convertValue(updateNode, Driver.class);
            updatedDriver = driverRepository.save(updatedDriver);
            return updatedDriver;
        } catch (JsonPatchException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteDriverById(Long driverId) {
        driverRepository.deleteById(driverId);
    }
    private static RegisterResponse getRegisterResponse(Driver saved) {
        RegisterResponse response = new RegisterResponse();
        response.setId(saved.getId());
        response.setCode(HttpStatus.CREATED.value());
        response.setSuccessful(true);
        response.setMessage("User Registration Success");
        return response;
    }
}
