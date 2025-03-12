package com.sudagoarth.sudanyallapay.Users.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthSecurityRequest {
    
    @NotBlank(message = "Code is required")
    private String code;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
}
