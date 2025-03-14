package com.sudagoarth.sudanyallapay.Wallets.Interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.sudagoarth.sudanyallapay.Enums.WalletStatus;
import com.sudagoarth.sudanyallapay.Wallets.Dtos.WalletRequest;
import com.sudagoarth.sudanyallapay.Wallets.Dtos.WalletResponse;

public interface WalletInterface {
    List<WalletResponse> getWallets();

    WalletResponse getWallet(Long walletId);

    WalletResponse createWallet(WalletRequest walletRequest);

    WalletResponse updateWallet(Long walletId, WalletRequest walletRequest);

    void deleteWallet(Long walletId);

    WalletResponse statusWallet(Long walletId, WalletStatus status);

    WalletResponse depositWallet(Long walletId, BigDecimal amount);

    WalletResponse withdrawWallet(Long walletId, BigDecimal amount);

    WalletResponse transferWallet(Long walletId, Long targetId, BigDecimal amount);

    WalletResponse getBalance(Long walletId);

    WalletResponse getTransactions(Long walletId);

    WalletResponse getTransaction(Long walletId, Long transactionId);

}