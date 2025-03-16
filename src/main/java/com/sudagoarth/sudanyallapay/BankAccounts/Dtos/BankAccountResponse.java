package com.sudagoarth.sudanyallapay.BankAccounts.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.sudagoarth.sudanyallapay.Enums.AccountStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountResponse {

    @NotNull(message = "ID is required")
    private Long id;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Bank name is required")
    private String bankName;

    @NotBlank(message = "Account number is required")
    private String accountNumber;

    @NotBlank(message = "IBAN is required")
    private String iban;

    @NotBlank(message = "SWIFT code is required")
    private String swiftCode;

    @NotBlank(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

}
