package com.sudagoarth.sudanyallapay.Users.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sudagoarth.sudanyallapay.Users.Repositories.AuthSecurityRepository;
import com.sudagoarth.sudanyallapay.Users.Dtos.AuthSecurityRequest;
import com.sudagoarth.sudanyallapay.Users.Dtos.EmailRequest;
import com.sudagoarth.sudanyallapay.Users.Dtos.LoginRequest;
import com.sudagoarth.sudanyallapay.Users.Dtos.UserRequest;
import com.sudagoarth.sudanyallapay.Users.Dtos.UserResponse;
import com.sudagoarth.sudanyallapay.Users.Entities.AuthSecurity;
import com.sudagoarth.sudanyallapay.Users.Entities.User;
import com.sudagoarth.sudanyallapay.Users.Interfaces.UserInterface;
import com.sudagoarth.sudanyallapay.Users.Repositories.UserRepository;
import com.sudagoarth.sudanyallapay.exceptions.DuplicateException;
import com.sudagoarth.sudanyallapay.exceptions.ExpiredException;
import com.sudagoarth.sudanyallapay.exceptions.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService implements UserInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthSecurityRepository authSecurityRepository;

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
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        if (userRequest.getDateOfBirth() != null && !userRequest.getDateOfBirth().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            user.setDateOfBirth(LocalDate.parse(userRequest.getDateOfBirth(), formatter));
        }
        User savedUser = userRepository.save(user);
        String code = createAuthSecurity(savedUser.getId());
        System.out.println("Generated OTP for user: " + code);
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
        Optional<AuthSecurity> authSecurity = authSecurityRepository.findByUserIdAndIsUsedFalse(user.getId());
        if (authSecurity.isPresent() && !authSecurity.get().getIsUsed()) {
            throw new NotFoundException("Please verify your account with OTP");
        }

        return new UserResponse(user);
    }

    @Override
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.getAllUsers(pageable);
        return  users.map(UserResponse::new);
    }

    private static final int OTP_VALIDITY_MINUTES = 10;

    // Generate a 6-digit OTP
    private String generate() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    @Override
    public String createAuthSecurity(Long userId) throws NotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));

        Optional<AuthSecurity> existingAuthSecurity = authSecurityRepository.findByUserIdAndIsUsedFalse(user.getId());

        AuthSecurity authSecurity;
        if (existingAuthSecurity.isPresent()) {
            // Update existing record
            authSecurity = existingAuthSecurity.get();
            authSecurity.setCode(generate());
            authSecurity.setExpiresAt(LocalDateTime.now().plusMinutes(OTP_VALIDITY_MINUTES));
            authSecurity.setCreatedAt(LocalDateTime.now());
            authSecurity.setIsUsed(false);
        } else {
            // Create a new record
            authSecurity = new AuthSecurity();
            authSecurity.setUser(user);
            authSecurity.setCode(generate());
            authSecurity.setExpiresAt(LocalDateTime.now().plusMinutes(OTP_VALIDITY_MINUTES));
            authSecurity.setCreatedAt(LocalDateTime.now());
            authSecurity.setIsUsed(false);
        }

        authSecurityRepository.save(authSecurity);
        return authSecurity.getCode();
    }

    @Override
    public UserResponse validate(AuthSecurityRequest authSecurityRequest) throws ExpiredException {
        AuthSecurity authSecurity = authSecurityRepository.findByCodeAndIsUsedFalse(authSecurityRequest.getCode())
                .orElseThrow(() -> new ExpiredException("Invalid OTP code"));

        if (authSecurity.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new ExpiredException("OTP code has expired");
        }

        authSecurity.setIsUsed(true);
        authSecurityRepository.save(authSecurity);

        return new UserResponse(authSecurity.getUser());
    }

    @Override
    public String generateCode(EmailRequest emailRequest) throws NotFoundException {
        User user = userRepository.findByEmail(emailRequest.getEmail().trim().toLowerCase());
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return createAuthSecurity(user.getId());
    }

}
