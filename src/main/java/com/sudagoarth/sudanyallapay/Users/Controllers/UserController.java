package com.sudagoarth.sudanyallapay.Users.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sudagoarth.sudanyallapay.Users.Dtos.AuthSecurityRequest;
import com.sudagoarth.sudanyallapay.Users.Dtos.EmailRequest;
import com.sudagoarth.sudanyallapay.Users.Dtos.LoginRequest;
import com.sudagoarth.sudanyallapay.Users.Dtos.UserRequest;
import com.sudagoarth.sudanyallapay.Users.Dtos.UserResponse;
import com.sudagoarth.sudanyallapay.Users.Interfaces.UserInterface;
import com.sudagoarth.sudanyallapay.utils.ApiResponse;
import com.sudagoarth.sudanyallapay.utils.LocaledData;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

        @Autowired
        private UserInterface userInterface;

        private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getName());

        @PostMapping("/register")
        public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
                LOGGER.info("Creating user {}: ", userRequest);
                UserResponse userResponse = userInterface.createUser(userRequest);
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(ApiResponse.success(new LocaledData(
                                                "User created successfully", "تم إنشاء المستخدم بنجاح"),
                                                HttpStatus.CREATED.value(),
                                                userResponse));
        }

        @PostMapping("/login")
        public ResponseEntity<ApiResponse> loginUser(@RequestBody LoginRequest loginRequest) {
                LOGGER.info("Logging in user with email: {}", loginRequest);
                UserResponse userResponse = userInterface.loginUser(loginRequest);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success(
                                                new LocaledData("User logged in successfully", "تم تسجيل الدخول بنجاح"),
                                                HttpStatus.OK.value(),
                                                userResponse));
        }

        @GetMapping("/user")
        public ResponseEntity<ApiResponse> getUserById(@RequestParam Long id) {
                LOGGER.info("Getting user by ID: {}", id);
                UserResponse userResponse = userInterface.getUserById(id);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success(
                                                new LocaledData("User retrieved successfully",
                                                                "تم استرجاع المستخدم بنجاح"),
                                                HttpStatus.OK.value(), userResponse));
        }

        @GetMapping("/users")
        public ResponseEntity<ApiResponse> getAllUsers(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size) {
                LOGGER.info("Getting all users");

                Pageable pageable = PageRequest.of(page, size);
                Page<UserResponse> userResponses = userInterface.getAllUsers(pageable);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success(
                                                new LocaledData("Users retrieved successfully",
                                                                "تم استرجاع المستخدمين بنجاح"),
                                                HttpStatus.OK.value(), userResponses.getContent(),
                                                userResponses.getPageable()));
        }

        // updateUser
        @PostMapping("/update")
        public ResponseEntity<ApiResponse> updateUser(@Valid @RequestBody UserRequest userRequest,
                        @RequestParam Long id) {
                LOGGER.info("Updating user with ID: {}", id);
                UserResponse userResponse = userInterface.updateUser(userRequest, id);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success(
                                                new LocaledData("User updated successfully", "تم تحديث المستخدم بنجاح"),
                                                HttpStatus.OK.value(),
                                                userResponse));
        }

        // deleteUser
        @PostMapping("/delete")
        public ResponseEntity<ApiResponse> deleteUser(@RequestParam Long id) {
                LOGGER.info("Deleting user with ID: {}", id);
                userInterface.deleteUser(id);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success(
                                                new LocaledData("User deleted successfully", "تم حذف المستخدم بنجاح"),
                                                HttpStatus.OK.value(),
                                                null));
        }

        @PostMapping("/generate")
        public ResponseEntity<ApiResponse> generateCode(@RequestBody EmailRequest emailRequest) {
                LOGGER.info("Generating OTP for user with email: {}", emailRequest.getEmail());
                userInterface.generateCode(emailRequest);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success(
                                                new LocaledData("OTP generated successfully",
                                                                "تم إنشاء رمز التحقق بنجاح"),
                                                HttpStatus.OK.value(),
                                                null));
        }

        @PostMapping("/validate")
        public ResponseEntity<ApiResponse> validate(@Valid @RequestBody AuthSecurityRequest authSecurityRequest) {
                UserResponse userResponse = userInterface.validate(authSecurityRequest);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success(
                                                new LocaledData("User validated successfully",
                                                                "تم التحقق من المستخدم بنجاح"),
                                                HttpStatus.OK.value(),
                                                userResponse));
        }

}
