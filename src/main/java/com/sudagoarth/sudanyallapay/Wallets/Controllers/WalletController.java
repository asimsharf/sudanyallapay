package com.sudagoarth.sudanyallapay.Wallets.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sudagoarth.sudanyallapay.Users.Controllers.UserController;
import com.sudagoarth.sudanyallapay.Wallets.Dtos.WalletRequest;
import com.sudagoarth.sudanyallapay.Wallets.Dtos.WalletResponse;
import com.sudagoarth.sudanyallapay.Wallets.Interfaces.WalletInterface;
import com.sudagoarth.sudanyallapay.utils.ApiResponse;
import com.sudagoarth.sudanyallapay.utils.LocaledData;

@RestController
@RequestMapping("/api/v1/wallets")
public class WalletController {

    @Autowired
    private WalletInterface walletInterface;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getName());

    @GetMapping
    public ResponseEntity<ApiResponse> getWallets() {

        LOGGER.info("Getting wallets");

        List<WalletResponse> walletResponses = walletInterface.getWallets();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(new LocaledData(
                        "Wallet retrieved successfully", "تم استرجاع المحفظة بنجاح"),
                        HttpStatus.OK.value(),
                        walletResponses));

    }

    @GetMapping("/wallet")
    public ResponseEntity<ApiResponse> getWallet(@RequestParam Long walletId) {

        LOGGER.info("Getting wallet by ID: {}", walletId);

        WalletResponse walletResponse = walletInterface.getWallet(walletId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(new LocaledData(
                        "Wallet retrieved successfully", "تم استرجاع المحفظة بنجاح"),
                        HttpStatus.OK.value(),
                        walletResponse));

    }

    @PostMapping
    public ResponseEntity<ApiResponse> createWallet(@RequestBody WalletRequest  walletRequest) {
        LOGGER.info("Creating wallet");

        WalletResponse walletResponse = walletInterface.createWallet(walletRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(new LocaledData(
                        "Wallet created successfully", "تم إنشاء المحفظة بنجاح"),
                        HttpStatus.CREATED.value(),
                        walletResponse));

    }

    @PutMapping("/wallet")
    public ResponseEntity<ApiResponse> updateWallet(@RequestParam Long walletId, @RequestBody WalletRequest wallet) {

        LOGGER.info("Updating wallet by ID: {}", walletId);

        WalletResponse walletResponse = walletInterface.updateWallet(walletId, wallet);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(new LocaledData(
                        "Wallet updated successfully", "تم تحديث المحفظة بنجاح"),
                        HttpStatus.OK.value(),
                        walletResponse));

    }

}
