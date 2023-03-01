package com.paragon.uberdeluxe.services;

import com.github.fge.jsonpatch.JsonPatch;
import com.paragon.uberdeluxe.data.dto.request.RegisterDriverRequest;
import com.paragon.uberdeluxe.data.dto.response.RegisterResponse;
import com.paragon.uberdeluxe.data.models.Driver;
import org.springframework.web.multipart.MultipartFile;

public interface DriverService {
    RegisterResponse register(RegisterDriverRequest driverRequest, MultipartFile licenseId);

    Driver getDriverById(Long driverId);

    Driver updateDriver(Long driverId, JsonPatch updatePatch);

    void deleteDriverById(Long driverId);
}
