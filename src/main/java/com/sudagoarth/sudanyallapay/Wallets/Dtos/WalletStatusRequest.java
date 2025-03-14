package com.sudagoarth.sudanyallapay.Wallets.Dtos;
import com.sudagoarth.sudanyallapay.Enums.WalletStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WalletStatusRequest {
    @NotNull(message = "Wallet ID is required")
    private Long walletId;


    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private WalletStatus status;
    
}
