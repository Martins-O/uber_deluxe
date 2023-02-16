package com.paragon.uberdeluxe.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.paragon.uberdeluxe.data.dto.request.RegisterPassengerRequest;
import com.paragon.uberdeluxe.data.dto.response.RegisterResponse;
import com.paragon.uberdeluxe.data.models.AppUser;
import com.paragon.uberdeluxe.data.models.Passenger;
import com.paragon.uberdeluxe.data.repositories.PassengerRepository;
import com.paragon.uberdeluxe.exception.BusinessLogicException;
import com.paragon.uberdeluxe.mapper.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class PassengerServiceImpl implements PassengerService{

    @Autowired
    private PassengerRepository repository;
    private static final int NUMBER_OF_ITEMS_PER_PAGE = 3;


    @Override
    public RegisterResponse register(RegisterPassengerRequest request) {
        AppUser user = Mapper.map(request);
        user.setCreatedAt(LocalDateTime.now());
        Passenger passenger = new Passenger();
        passenger.setUserDetails(user);
        Passenger saved = repository.save(passenger);
        log.info("saved ->{}", saved);
        return getRegisterResponse(saved);

    }

    @Override
    public Passenger getPassengerById(Long passengerId) {
        return repository.findById(passengerId).orElseThrow(()->
            new BusinessLogicException(String.format("User %s not found", passengerId))
        );
    }

    @Override
    public Passenger update(Long passengerId, JsonPatch updatePatch) {
        ObjectMapper mapper = new ObjectMapper();
        Passenger foundPassenger = getPassengerById(passengerId);
        JsonNode node = mapper.convertValue(foundPassenger, JsonNode.class);
        try{
            JsonNode updateNode = updatePatch.apply(node);
            var updatedPassenger = mapper.convertValue(updateNode, Passenger.class);
            updatedPassenger = repository.save(updatedPassenger);
            return updatedPassenger;
        } catch (JsonPatchException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

//    @Override
//    public Page<Passenger> getAllPassenger() {
//        Page
//        return;
//    }

    @Override
    public void deletePassenger(Long passengerId) {
        repository.deleteById(passengerId);
    }

    private static RegisterResponse getRegisterResponse(Passenger saved) {
        RegisterResponse response = new RegisterResponse();
        response.setId(saved.getId());
        response.setCode(HttpStatus.CREATED.value());
        response.setSuccessful(true);
        response.setMessage("User Registration Success");
        return response;
    }


}
