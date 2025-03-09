package com.sudagoarth.sudanyallapay.AuthSsecurity.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sudagoarth.sudanyallapay.AuthSsecurity.Interfaces.AuthSecurityInterface;
import com.sudagoarth.sudanyallapay.utils.ApiResponse;
import com.sudagoarth.sudanyallapay.utils.LocaledData;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthSecurityController {

    @Autowired
    private AuthSecurityInterface authSecurityInterface;

    @PostMapping("/generate-otp")
    public ResponseEntity<ApiResponse> generateOtp(@RequestParam Long userId) {
        String otp = authSecurityInterface.generateOtpForUser(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        new LocaledData("OTP generated successfully", "تم إنشاء رمز التحقق بنجاح"),
                        HttpStatus.OK.value(),
                        otp));
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<ApiResponse> validateOtp(@RequestParam String otpCode) {
        boolean isValid = authSecurityInterface.validateOtp(otpCode);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        new LocaledData("OTP validated successfully", "تم التحقق من رمز التحقق بنجاح"),
                        HttpStatus.OK.value(),
                        isValid));
    }
}
