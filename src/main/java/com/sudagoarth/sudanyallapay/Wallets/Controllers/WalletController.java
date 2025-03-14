package com.sudagoarth.sudanyallapay.Wallets.Controllers;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sudagoarth.sudanyallapay.Enums.WalletStatus;
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
        public ResponseEntity<ApiResponse> createWallet(@RequestBody WalletRequest walletRequest) {
                LOGGER.info("Creating wallet");

                WalletResponse walletResponse = walletInterface.createWallet(walletRequest);

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(ApiResponse.success(new LocaledData(
                                                "Wallet created successfully", "تم إنشاء المحفظة بنجاح"),
                                                HttpStatus.CREATED.value(),
                                                walletResponse));

        }

        @PostMapping("/deposit")
        public ResponseEntity<ApiResponse> deposit(@RequestParam Long walletId, @RequestParam BigDecimal amount) {
                LOGGER.info("Depositing to wallet");

                WalletResponse walletResponse = walletInterface.depositWallet(walletId, amount);

                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success(new LocaledData(
                                                "Deposited successfully", "تم الإيداع بنجاح"),
                                                HttpStatus.OK.value(),
                                                walletResponse));

        }

        @PostMapping("/withdraw")
        public ResponseEntity<ApiResponse> withdraw(@RequestParam Long walletId, @RequestParam BigDecimal amount) {
                LOGGER.info("Withdrawing from wallet");

                WalletResponse walletResponse = walletInterface.withdrawWallet(walletId, amount);

                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success(new LocaledData(
                                                "Withdrawn successfully", "تم السحب بنجاح"),
                                                HttpStatus.OK.value(),
                                                walletResponse));

        }

        @PostMapping("/transfer")
        public ResponseEntity<ApiResponse> transfer(@RequestParam Long senderWalletId, @RequestParam Long receiverWalletId,
                        @RequestParam BigDecimal amount) {
                LOGGER.info("Transferring from wallet");

                WalletResponse walletResponse = walletInterface.transferWallet(senderWalletId, receiverWalletId, amount);

                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success(new LocaledData(
                                                "Transferred successfully", "تم التحويل بنجاح"),
                                                HttpStatus.OK.value(),
                                                walletResponse));

        }

        @DeleteMapping("/wallet")
        public ResponseEntity<ApiResponse> deleteWallet(@RequestParam Long walletId) {
                LOGGER.info("Deleting wallet by ID: {}", walletId);

                walletInterface.deleteWallet(walletId);

                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success(new LocaledData(
                                                "Wallet deleted successfully", "تم حذف المحفظة بنجاح"),
                                                HttpStatus.OK.value(), null));

        }

        @PutMapping("/status")
        public ResponseEntity<ApiResponse> updateWalletStatus(@RequestParam Long walletId, @RequestParam WalletStatus status) {
                LOGGER.info("Updating wallet status by ID: {}", walletId);

                WalletResponse walletResponse = walletInterface.statusWallet(walletId, status);

                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success(new LocaledData(
                                                "Wallet status updated successfully", "تم تحديث حالة المحفظة بنجاح"),
                                                HttpStatus.OK.value(),
                                                walletResponse));

        }

}
