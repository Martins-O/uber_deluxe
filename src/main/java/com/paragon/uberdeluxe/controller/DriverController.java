package com.paragon.uberdeluxe.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.paragon.uberdeluxe.data.dto.request.RegisterDriverRequest;
import com.paragon.uberdeluxe.data.dto.response.ApiResponse;
import com.paragon.uberdeluxe.data.dto.response.RegisterResponse;
import com.paragon.uberdeluxe.services.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/driver")
@AllArgsConstructor
public class DriverController {

    private final DriverService service;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterDriverRequest driverRequest){
        RegisterResponse response = service.register(driverRequest);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping("{driverId}")
    public ResponseEntity<?> getDriverById(@PathVariable Long driverId){
        var foundPassenger = service.getDriverById(driverId);
        return ResponseEntity.status(HttpStatus.OK).body(foundPassenger);
    }
    @PatchMapping(value = "{driverId}", consumes = "application/json-patch+json")
    public ResponseEntity< ? > updateDriverById(@PathVariable Long driverId, @RequestBody JsonPatch updatePatch){
        try {
            var response = service.updateDriver(driverId, updatePatch);
            return  ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{driverId}")
    public ResponseEntity<?> deleteDriverById(@PathVariable Long driverId){
        service.deleteDriverById(driverId);
        return ResponseEntity.ok(ApiResponse.builder().message("driver deleted successfully"));
    }
}
