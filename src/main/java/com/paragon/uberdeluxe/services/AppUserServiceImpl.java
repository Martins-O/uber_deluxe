package com.paragon.uberdeluxe.services;

import com.paragon.uberdeluxe.data.dto.response.ApiResponse;
import com.paragon.uberdeluxe.data.models.Driver;
import com.paragon.uberdeluxe.data.models.Passenger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService{


    @Override
    public ApiResponse uploadProfileImage(MultipartFile profileImage, Long userId) {
        Optional<Driver> foundDriver = Optional.empty();
        Optional<Passenger> foundPassenger;
        foundPassenger = findPassenger(userId);
    }

    @Override
    public ApiResponse verifyAccount(Long userId, String token) {
        return null;
    }
}
