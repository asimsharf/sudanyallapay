package com.sudagoarth.sudanyallapay.Wallets.Dtos;


import java.math.BigDecimal;

import com.sudagoarth.sudanyallapay.Enums.WalletStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WalletRequest {
    
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Balance is required")
    private BigDecimal balance;

    @NotBlank(message = "Currency is required")
    private String currency;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private WalletStatus status;
}
