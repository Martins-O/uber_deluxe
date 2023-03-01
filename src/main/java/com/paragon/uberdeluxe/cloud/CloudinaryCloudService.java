package com.paragon.uberdeluxe.cloud;

import com.paragon.uberdeluxe.exception.ImageUploadException;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryCloudService {

    String uploadCloud(MultipartFile image) throws ImageUploadException;
}
