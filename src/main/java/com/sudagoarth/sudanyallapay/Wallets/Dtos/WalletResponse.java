package com.sudagoarth.sudanyallapay.Wallets.Dtos;

import java.math.BigDecimal;
import java.util.List;

import com.sudagoarth.sudanyallapay.Enums.WalletStatus;
import com.sudagoarth.sudanyallapay.Users.Dtos.UserResponse;
import com.sudagoarth.sudanyallapay.Wallets.Entities.Wallet;

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
public class WalletResponse {

    @NotNull(message = "ID is required")
    private Long id;

    @NotNull(message = "User ID is required")
    private UserResponse user;

    @NotNull(message = "Balance is required")
    private BigDecimal balance;

    @NotBlank(message = "Currency is required")
    private String currency;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private WalletStatus status;

    public static List<WalletResponse> fromWallets(List<Wallet> wallet) {
        return wallet.stream().map(WalletResponse::fromWallet).toList();
    }

    public static WalletResponse fromWallet(Wallet wallet) {
        WalletResponse response = new WalletResponse();
        response.id = wallet.getId();
        response.user =  UserResponse.fromUser(wallet.getUser());
        response.balance = wallet.getBalance();
        response.currency = wallet.getCurrency();
        response.status = wallet.getStatus();
        return response;
    }

}
