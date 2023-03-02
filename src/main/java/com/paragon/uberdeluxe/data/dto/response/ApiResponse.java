package com.paragon.uberdeluxe.data.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ApiResponse {
    private String message;
    private int status;
}
