package com.ITRoad.signup_service.services;

import com.ITRoad.signup_service.dto.UserRegistrationDto;
import com.ITRoad.signup_service.dto.UserResponseDto;
import com.ITRoad.signup_service.models.User;
import com.ITRoad.signup_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for handling user-related operations including registration and retrieval.
 * Manages user data and ensures business rules are enforced.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registers a new user in the system
     *
     * @param registrationDto Contains user registration details
     * @return UserResponseDto with registration confirmation
     * @throws RuntimeException if username is already taken
     */
    public UserResponseDto registerUser(UserRegistrationDto registrationDto) {
        // Validate username uniqueness
        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            throw new RuntimeException("Username is already registered. Please choose a different username.");
        }
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new RuntimeException("Email is already in use");
        }

        if (userRepository.existsByPhoneNumber(registrationDto.getPhoneNumber())) {
            throw new RuntimeException("Phone number is already in use");
        }


        // Create and populate new user entity
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword())); // Encrypt password
        user.setName(registrationDto.getName());
        user.setEmail(registrationDto.getEmail());
        user.setRole("ADHERANT"); // Default role for new users
        user.setStatus("Active"); // Default status for new registrations
        user.setLastLogin(null); // No login history for new user
        user.setAddress(registrationDto.getAddress());
        user.setBio(registrationDto.getBio());
        user.setPhoneNumber(registrationDto.getPhoneNumber());

        // Persist user to database
        User savedUser = userRepository.save(user);

        // Return success response with essential user information
        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getRole(),
                "Registration successful. Welcome to our platform!"
        );
    }
}