package com.sudagoarth.sudanyallapay.Users.Interfaces;

import java.util.List;

import com.sudagoarth.sudanyallapay.Users.Dtos.LoginRequest;
import com.sudagoarth.sudanyallapay.Users.Dtos.UserRequest;
import com.sudagoarth.sudanyallapay.Users.Dtos.UserResponse;

public interface UserInterface {
    UserResponse createUser(UserRequest userRequest);

    UserResponse getUserById(Long id);

    UserResponse updateUser(UserRequest userRequest, Long id);

    void deleteUser(Long id);

    List<UserResponse> getAllUsers();

    UserResponse loginUser(LoginRequest loginRequest);
}
