package com.sudagoarth.sudanyallapay.Wallets.Interfaces;

import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sudagoarth.sudanyallapay.Enums.WalletStatus;
import com.sudagoarth.sudanyallapay.Wallets.Dtos.WalletRequest;
import com.sudagoarth.sudanyallapay.Wallets.Dtos.WalletResponse;

public interface WalletInterface {
    Page<WalletResponse> getWallets(Pageable pageable);

    WalletResponse getWallet(Long walletId);

    WalletResponse createWallet(WalletRequest walletRequest);


    void deleteWallet(Long walletId);

    WalletResponse statusWallet(Long walletId, WalletStatus status);

    WalletResponse depositWallet(Long walletId, BigDecimal amount);

    WalletResponse withdrawWallet(Long walletId, BigDecimal amount);

    WalletResponse transferWallet(Long walletId, Long targetId, BigDecimal amount);

}