package com.example.attendance_system.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateLecturerRequest {

    @NotBlank(message = "Full name is required")
    @Size(max = 120)
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    @Size(max = 180)
    private String email;

    @NotBlank(message = "Initial password is required")
    @Size(min = 8, max = 72,
            message = "Password must be between 8 and 72 characters")
    private String password;

}
