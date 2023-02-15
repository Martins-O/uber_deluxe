package com.paragon.uberdeluxe.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterResponse {
    private String message;
    private int code;
    private boolean isSuccessful;
    private Long id;
}
