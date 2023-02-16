package com.paragon.uberdeluxe.data.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterDriverRequest {

    @JsonProperty("full_name")
    private String name;
    private String email;
    @JsonProperty("password")
    private String password;
}
