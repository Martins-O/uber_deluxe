package com.paragon.uberdeluxe.services;

import com.github.fge.jsonpatch.JsonPatch;
import com.paragon.uberdeluxe.data.dto.request.RegisterPassengerRequest;
import com.paragon.uberdeluxe.data.dto.response.RegisterResponse;
import com.paragon.uberdeluxe.data.dto.response.UpdatePassengerResponse;
import com.paragon.uberdeluxe.data.models.Passenger;

public interface PassengerService {
    RegisterResponse register(RegisterPassengerRequest request);

    Passenger getPassengerById(Long passengerId);

    UpdatePassengerResponse update(Long passengerId, JsonPatch updatePatch);
}
