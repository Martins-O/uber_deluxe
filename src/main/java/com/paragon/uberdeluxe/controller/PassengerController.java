package com.paragon.uberdeluxe.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.paragon.uberdeluxe.data.dto.request.RegisterPassengerRequest;
import com.paragon.uberdeluxe.data.dto.response.ApiResponse;
import com.paragon.uberdeluxe.data.dto.response.RegisterResponse;
import com.paragon.uberdeluxe.services.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{passengerId}")
    public ResponseEntity<?> getPassengerById(@PathVariable Long passengerId){
        var foundPassenger = service.getPassengerById(passengerId);
        return ResponseEntity.status(HttpStatus.OK).body(foundPassenger);
    }
    @PatchMapping(value = "{passengerId}", consumes = "application/json-patch+json")
    public ResponseEntity< ? > updatePassengerById(@PathVariable Long passengerId, @RequestBody JsonPatch updatePatch){
        try {
            var response = service.update(passengerId, updatePatch);
            return  ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{PassengerId}")
    public ResponseEntity<?> deletePassengerById(@PathVariable Long passengerId){
        service.deletePassenger(passengerId);
        return ResponseEntity.ok(ApiResponse.builder().message("passenger deleted successfully"));
    }

}

