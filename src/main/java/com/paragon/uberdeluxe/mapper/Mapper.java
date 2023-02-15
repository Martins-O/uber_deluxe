package com.paragon.uberdeluxe.mapper;

import com.paragon.uberdeluxe.data.dto.request.RegisterPassengerRequest;
import com.paragon.uberdeluxe.data.models.AppUser;

public class Mapper {
    public static AppUser map(RegisterPassengerRequest request) {
        AppUser user = new AppUser();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setName(request.getName());
        return user;
    }
}
