package com.sudagoarth.sudanyallapay.AuthSsecurity.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sudagoarth.sudanyallapay.AuthSsecurity.Entities.AuthSecurity;
import com.sudagoarth.sudanyallapay.AuthSsecurity.Interfaces.AuthSecurityInterface;
import com.sudagoarth.sudanyallapay.AuthSsecurity.Repositories.AuthSecurityRepository;
import com.sudagoarth.sudanyallapay.Users.Entities.User;
import com.sudagoarth.sudanyallapay.Users.Repositories.UserRepository;
import com.sudagoarth.sudanyallapay.exceptions.NotFoundException;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AuthSecurityService implements AuthSecurityInterface {

    @Autowired
    private AuthSecurityRepository authSecurityRepository;

    @Autowired
    private UserRepository userRepository;

    private static final int OTP_VALIDITY_MINUTES = 10;

    // Generate a 6-digit OTP
    private String generateOtp() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    public String generateOtpForUser(Long userId) throws NotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));

        // Generate and save OTP
        AuthSecurity authSecuriity = new AuthSecurity();
        authSecuriity.setUser(user);
        authSecuriity.setCode(generateOtp());
        authSecuriity.setExpiresAt(LocalDateTime.now().plusMinutes(OTP_VALIDITY_MINUTES));
        authSecuriity.setCreatedAt(LocalDateTime.now());
        authSecuriity.setIsUsed(false);

        authSecurityRepository.save(authSecuriity);
        return authSecuriity.getCode();
    }

    public boolean validateOtp(String otpCode) {
        AuthSecurity authSecuriity = authSecurityRepository.findByCodeAndIsUsedFalse(otpCode)
                .orElseThrow(() -> new NotFoundException("Invalid or expired OTP"));

        // Check if OTP has expired
        if (authSecuriity.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new NotFoundException("OTP has expired");
        }

        // Mark OTP as used and save
        authSecuriity.setIsUsed(true);
        authSecurityRepository.save(authSecuriity);
        return true;
    }
}
