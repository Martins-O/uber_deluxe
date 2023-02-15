package com.paragon.uberdeluxe.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.paragon.uberdeluxe.data.dto.request.RegisterPassengerRequest;
import com.paragon.uberdeluxe.data.dto.response.RegisterResponse;
import com.paragon.uberdeluxe.data.dto.response.UpdatePassengerResponse;
import com.paragon.uberdeluxe.data.models.AppUser;
import com.paragon.uberdeluxe.data.models.Passenger;
import com.paragon.uberdeluxe.data.repositories.PassengerRepository;
import com.paragon.uberdeluxe.exception.BusinessLogicException;
import com.paragon.uberdeluxe.mapper.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class PassengerServiceImpl implements PassengerService{

    @Autowired
    private PassengerRepository repository;
    private ObjectMapper map;


    @Override
    public RegisterResponse register(RegisterPassengerRequest request) {
        AppUser user = Mapper.map(request);
        user.setCreatedAt(LocalDateTime.now());
        Passenger passenger = new Passenger();
        passenger.setUserDetails(user);
        Passenger saved = repository.save(passenger);
        log.info("saved ->{}", saved);
        RegisterResponse result = getRegisterResponse(saved);
        return result;

    }

    @Override
    public Passenger getPassengerById(Long passengerId) {
        return repository.findById(passengerId).orElseThrow(()->
            new BusinessLogicException(String.format("User %s not found", passengerId))
        );
    }

    @Override
    public UpdatePassengerResponse update(Long passengerId, JsonPatch updatePatch) {
        Passenger passenger = getPassengerById(passengerId);
        map.

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
