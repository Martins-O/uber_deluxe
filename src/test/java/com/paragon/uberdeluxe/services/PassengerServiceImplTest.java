package com.paragon.uberdeluxe.services;

import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.ReplaceOperation;
import com.paragon.uberdeluxe.data.dto.request.RegisterPassengerRequest;
import com.paragon.uberdeluxe.data.dto.response.RegisterResponse;
import com.paragon.uberdeluxe.data.models.AppUser;
import com.paragon.uberdeluxe.data.models.Passenger;
import com.paragon.uberdeluxe.data.repositories.PassengerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class PassengerServiceImplTest {


    @Autowired
    private PassengerService passengerService;
    private RegisterPassengerRequest request;
    @Autowired
    private PassengerRepository passengerRepository;

    @BeforeEach
    void setUp(){
        request = new RegisterPassengerRequest();
        request.setPassword("password");
        request.setName("test");
        request.setEmail("email");
    }

    @Test
    void registerTest(){
        RegisterPassengerRequest request = new RegisterPassengerRequest();
        request.setPassword("password");
        request.setName("test");
        request.setEmail("email");

        RegisterResponse response = passengerService.register(request);
        assertThat(response).isNotNull();
        assertThat(response.getCode())
                .isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void getUserByIdTest(){
        var response = passengerService.register(request);
        Passenger foundPassenger = passengerService.getPassengerById(response.getId());
        assertThat(foundPassenger).isNotNull();
        AppUser user = foundPassenger.getUserDetails();
        assertThat(user.getName()).isEqualTo(request.getName());
    }

    @Test
    public void updateTest(){
        JsonPatch update = new JsonPatch(List.of(
                new ReplaceOperation(new JsonPointer("/email"), "email")
        ));
        var response = passengerService.register(request);
        var updatePass = passengerService.update(re);
        assertThat(updatePass)
    }

}