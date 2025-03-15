package com.sudagoarth.sudanyallapay.Users.Interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sudagoarth.sudanyallapay.Users.Dtos.AuthSecurityRequest;
import com.sudagoarth.sudanyallapay.Users.Dtos.EmailRequest;
import com.sudagoarth.sudanyallapay.Users.Dtos.LoginRequest;
import com.sudagoarth.sudanyallapay.Users.Dtos.UserRequest;
import com.sudagoarth.sudanyallapay.Users.Dtos.UserResponse;

public interface UserInterface {
    UserResponse createUser(UserRequest userRequest);

    UserResponse getUserById(Long id);

    UserResponse updateUser(UserRequest userRequest, Long id);

    void deleteUser(Long id);

    Page<UserResponse> getAllUsers(Pageable pageable);

    UserResponse loginUser(LoginRequest loginRequest);

    String createAuthSecurity(Long userId);

    UserResponse validate(AuthSecurityRequest authSecurityRequest);

    String generateCode(EmailRequest emailRequest);
}
