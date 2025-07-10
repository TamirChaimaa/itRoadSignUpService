package com.ITRoad.signup_service.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;
    private String role;
    private String message;
}