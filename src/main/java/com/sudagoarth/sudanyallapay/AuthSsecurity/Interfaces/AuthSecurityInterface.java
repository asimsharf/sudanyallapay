package com.sudagoarth.sudanyallapay.AuthSsecurity.Interfaces;

public interface AuthSecurityInterface {
    String generateOtpForUser(Long userId);
    boolean validateOtp(String otpCode);
}