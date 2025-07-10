package com.ITRoad.signup_service.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

@Data
public class UserRegistrationDto {

    /**
     * Username of the user
     * - Must not be blank
     * - Must be between 3 and 50 characters
     */
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    /**
     * User's password
     * - Must not be blank
     * - Must be at least 6 characters long
     */
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    /**
     * Full name of the user
     * - Must not be blank
     */
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * User's email address
     * - Must not be blank
     * - Must be a properly formatted email address
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    /**
     * User's physical address
     * - Must not be blank
     * - Must be between 3 and 50 characters
     */
    @NotBlank(message = "Address is required")
    @Size(min = 3, max = 50, message = "Address must be between 3 and 50 characters")
    private String address;

    /**
     * Short biography or description about the user
     * - Must not be blank
     */
    @NotBlank(message = "Bio is required")
    private String bio;

    /**
     * User's phone number
     * - Must not be blank
     */
    @NotBlank(message = "PhoneNumber is required")
    private String phoneNumber;
}