package com.sudagoarth.sudanyallapay.Users.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sudagoarth.sudanyallapay.Users.Dtos.LoginRequest;
import com.sudagoarth.sudanyallapay.Users.Dtos.UserRequest;
import com.sudagoarth.sudanyallapay.Users.Dtos.UserResponse;
import com.sudagoarth.sudanyallapay.Users.Entities.User;
import com.sudagoarth.sudanyallapay.Users.Interfaces.UserInterface;
import com.sudagoarth.sudanyallapay.Users.Repositories.UserRepository;
import com.sudagoarth.sudanyallapay.AuthSsecurity.Entities.AuthSecurity;
import com.sudagoarth.sudanyallapay.AuthSsecurity.Interfaces.AuthSecurityInterface;
import com.sudagoarth.sudanyallapay.AuthSsecurity.Repositories.AuthSecurityRepository;
import com.sudagoarth.sudanyallapay.AuthSsecurity.Services.AuthSecurityService;
import com.sudagoarth.sudanyallapay.exceptions.DuplicateException;
import com.sudagoarth.sudanyallapay.exceptions.NotFoundException;

import java.lang.foreign.Linker.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthSecurityRepository authSecurityRepository;
    
    @Autowired
    private AuthSecurityInterface authSecurityInterface;


    @Override
    public UserResponse createUser(UserRequest userRequest) throws DuplicateException {
        if (userRepository.existsByEmail(userRequest.getEmail())
                || userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())) {
            throw new DuplicateException("User with the same email or phone number already exists.");
        }

        User user = new User();
        user.setFullName(userRequest.getFullName());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setPassword(userRequest.getPassword());
        user.setNationalId(userRequest.getNationalId());

        // ✅ Automatically set createdAt and updatedAt
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // ✅ Convert dateOfBirth from String to LocalDate
        if (userRequest.getDateOfBirth() != null && !userRequest.getDateOfBirth().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            user.setDateOfBirth(LocalDate.parse(userRequest.getDateOfBirth(), formatter));
        }

        User savedUser = userRepository.save(user);
        
        // ✅ Generate OTP after user registration
        String otpCode = authSecurityInterface.generateOtpForUser(savedUser.getId());
        System.out.println("Generated OTP for user: " + otpCode);

        return new UserResponse(savedUser);
    }

    @Override
    public UserResponse getUserById(Long id) throws NotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
        return new UserResponse(user);
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest, Long id) throws NotFoundException {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));

        existingUser.setFullName(userRequest.getFullName());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setPhoneNumber(userRequest.getPhoneNumber());

        User updatedUser = userRepository.save(existingUser);
        return new UserResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long id) throws NotFoundException {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse loginUser(LoginRequest loginRequest) throws NotFoundException {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            throw new NotFoundException("Invalid email or password");
        }

        Optional<AuthSecurity> authSecurity = authSecurityRepository.findByCodeAndIsUsedFalse(user);
        if (authSecurity.isPresent()) {
            throw new NotFoundException("Please verify your account with OTP");
        }

        return new UserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserResponse.fromUsers(users);
    }
}
