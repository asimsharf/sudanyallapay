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
public class BankAccountStatusRequest {
    
    @NotNull(message = "ID is required")
    private Long id;

    @NotBlank(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    
}
