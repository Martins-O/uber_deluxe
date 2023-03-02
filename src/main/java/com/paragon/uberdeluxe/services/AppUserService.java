package com.paragon.uberdeluxe.services;

import com.paragon.uberdeluxe.data.dto.response.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

public interface AppUserService {

    ApiResponse uploadProfileImage(MultipartFile profileImage, Long userId);

    ApiResponse verifyAccount(Long userId, String token);
}
