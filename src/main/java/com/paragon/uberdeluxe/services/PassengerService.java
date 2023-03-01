package com.paragon.uberdeluxe.services;

import com.github.fge.jsonpatch.JsonPatch;
import com.paragon.uberdeluxe.data.dto.request.RegisterPassengerRequest;
import com.paragon.uberdeluxe.data.dto.response.RegisterResponse;
import com.paragon.uberdeluxe.data.models.Passenger;
import org.springframework.data.domain.Page;

public interface PassengerService {
    RegisterResponse register(RegisterPassengerRequest request);

    Passenger getPassengerById(Long passengerId);

    Passenger update(Long passengerId, JsonPatch updatePatch);
//    Page<Passenger> getAllPassenger();
    void deletePassenger(Long passengerId);
    Page<Passenger> getAllPassenger(int pageNumber);
}
