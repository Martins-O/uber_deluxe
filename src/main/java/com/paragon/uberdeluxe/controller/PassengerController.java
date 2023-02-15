package com.paragon.uberdeluxe.controller;

import com.paragon.uberdeluxe.data.dto.request.RegisterPassengerRequest;
import com.paragon.uberdeluxe.data.dto.response.RegisterResponse;
import com.paragon.uberdeluxe.services.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/passenger")
@AllArgsConstructor
public class PassengerController {
    private final PassengerService service;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterPassengerRequest passengerRequest){
        RegisterResponse response = service.register(passengerRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
