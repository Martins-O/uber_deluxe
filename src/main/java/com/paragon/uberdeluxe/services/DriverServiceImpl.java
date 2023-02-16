package com.paragon.uberdeluxe.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.paragon.uberdeluxe.data.dto.request.RegisterDriverRequest;
import com.paragon.uberdeluxe.data.dto.response.RegisterResponse;
import com.paragon.uberdeluxe.data.models.AppUser;
import com.paragon.uberdeluxe.data.models.Driver;
import com.paragon.uberdeluxe.data.repositories.DriverRepository;
import com.paragon.uberdeluxe.exception.BusinessLogicException;
import com.paragon.uberdeluxe.mapper.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class DriverServiceImpl implements DriverService{

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public RegisterResponse register(RegisterDriverRequest driverRequest) {
        AppUser user = Mapper.map(driverRequest);
        user.setCreatedAt(LocalDateTime.now());
        Driver driver = new Driver();
        driver.setUserDetails(user);
        Driver saved = driverRepository.save(driver);
        log.info("saved ->{}", saved);
        RegisterResponse result = getRegisterResponse(saved);
        return result;
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
