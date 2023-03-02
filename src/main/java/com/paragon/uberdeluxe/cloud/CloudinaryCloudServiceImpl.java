package com.paragon.uberdeluxe.cloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.paragon.uberdeluxe.exception.ImageUploadException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class CloudinaryCloudServiceImpl implements CloudinaryCloudService{

    private final Cloudinary cloudinary;


    @Override
    public String uploadCloud(MultipartFile image){
        try {
            Map<?, ?> response =
                    cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
            return response.get("url").toString();
        }catch (IOException e) {
            throw new ImageUploadException(e.getMessage());
        }
    }
}
