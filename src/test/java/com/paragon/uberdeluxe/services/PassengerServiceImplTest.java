package com.paragon.uberdeluxe.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.ReplaceOperation;
import com.paragon.uberdeluxe.data.dto.request.RegisterPassengerRequest;
import com.paragon.uberdeluxe.data.dto.response.RegisterResponse;
import com.paragon.uberdeluxe.data.models.AppUser;
import com.paragon.uberdeluxe.data.models.Passenger;
import com.paragon.uberdeluxe.exception.BusinessLogicException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class PassengerServiceImplTest {


    @Autowired
    private PassengerService passengerService;
    private RegisterPassengerRequest request;


    @BeforeEach
    void setUp(){
        request = new RegisterPassengerRequest();
        request.setPassword("password");
        request.setName("test");
        request.setEmail("email");
    }

    @Test
    void registerTest(){
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
    public void updateTest() throws JsonProcessingException, JsonPointerException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree("234775757575");
        JsonPatch update = new JsonPatch(List.of(
                new ReplaceOperation(new JsonPointer("/phoneNumber"),
                        node)
        ));
        var response = passengerService.register(request);
        var updatePass = passengerService.update(response.getId(), update);
        assertThat(updatePass).isNotNull();
        assertThat(updatePass.getPhonenumber()).isNotNull();
    }

    @Test
    public void deletePassengerTest() {
        var response = passengerService.register(request);
        passengerService.deletePassenger(response.getId());
        assertThrows(BusinessLogicException.class, ()->
                passengerService.getPassengerById(response.getId()));
    }

}