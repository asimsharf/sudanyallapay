package com.sudagoarth.sudanyallapay.BankAccounts.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sudagoarth.sudanyallapay.BankAccounts.Dtos.BankAccountRequest;
import com.sudagoarth.sudanyallapay.BankAccounts.Dtos.BankAccountResponse;
import com.sudagoarth.sudanyallapay.BankAccounts.Dtos.BankAccountStatusRequest;
import com.sudagoarth.sudanyallapay.BankAccounts.Interfaces.BankAccountInterface;
import com.sudagoarth.sudanyallapay.Users.Controllers.UserController;
import com.sudagoarth.sudanyallapay.utils.ApiResponse;
import com.sudagoarth.sudanyallapay.utils.LocaledData;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/bank-accounts")
public class BankAccountController {

        @Autowired
        private BankAccountInterface bankAccountInterface;

        private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getName());

        @GetMapping
        public ResponseEntity<ApiResponse> getBankAccounts(@RequestParam("userId") Long userId,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size) {
                LOGGER.info("Getting bank accounts - User ID: {} | Page: {} | Size: {}", userId, page, size);
                Pageable pageable = PageRequest.of(page, size);
                Page<BankAccountResponse> bankAccountResponses = bankAccountInterface.getBankAccounts(userId, pageable);

                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success(
                                                new LocaledData("Bank accounts retrieved successfully",
                                                                "تم استرجاع الحسابات البنكية بنجاح"),
                                                HttpStatus.OK.value(),
                                                bankAccountResponses.getContent(),
                                                bankAccountResponses.getPageable()));
        }

        @GetMapping("/bank-account")
        public ResponseEntity<ApiResponse> getBankAccount(@Valid @RequestParam Long bankAccountId) {
                LOGGER.info("Getting bank account by ID: {}", bankAccountId);
                BankAccountResponse bankAccountResponse = bankAccountInterface.getBankAccount(bankAccountId);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success(
                                                new LocaledData("Bank account retrieved successfully",
                                                                "تم استرجاع الحساب البنكي بنجاح"),
                                                HttpStatus.OK.value(),
                                                bankAccountResponse));
        }

        @PostMapping
        public ResponseEntity<ApiResponse> createBankAccount(@Valid @RequestBody BankAccountRequest bankAccountRequest) {
                LOGGER.info("Creating bank account for user ID: {}", bankAccountRequest.getUserId());
                BankAccountResponse bankAccountResponse = bankAccountInterface.createBankAccount(bankAccountRequest);
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(ApiResponse.success(
                                                new LocaledData("Bank account created successfully",
                                                                "تم إنشاء الحساب البنكي بنجاح"),
                                                HttpStatus.CREATED.value(),
                                                bankAccountResponse));
        }

        @PutMapping
        public ResponseEntity<ApiResponse> updateBankAccount(@Valid @RequestParam Long bankAccountId,
        @RequestBody BankAccountRequest  bankAccountRequest) {
                LOGGER.info("Updating bank account by ID: {}", bankAccountId);
                BankAccountResponse bankAccountResponse = bankAccountInterface.updateBankAccount(bankAccountId,
                                bankAccountRequest);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success(
                                                new LocaledData("Bank account updated successfully",
                                                                "تم تحديث الحساب البنكي بنجاح"),
                                                HttpStatus.OK.value(),
                                                bankAccountResponse));
        }

        @DeleteMapping
        public ResponseEntity<ApiResponse> deleteBankAccount(@RequestParam Long bankAccountId) {
                LOGGER.info("Deleting bank account by ID: {}", bankAccountId);
                bankAccountInterface.deleteBankAccount(bankAccountId);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success(
                                                new LocaledData("Bank account deleted successfully",
                                                                "تم حذف الحساب البنكي بنجاح"),
                                                HttpStatus.OK.value(), null));
        }

        @PostMapping("/status")
        public ResponseEntity<ApiResponse> statusBankAccount(@RequestParam Long bankAccountId,
                        @RequestParam BankAccountStatusRequest bankAccountStatusRequest) {
                LOGGER.info("Updating bank account status by ID: {}", bankAccountId);
                BankAccountResponse bankAccountResponse = bankAccountInterface.statusBankAccount(bankAccountId,
                                bankAccountStatusRequest);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success(
                                                new LocaledData("Bank account status updated successfully",
                                                                "تم تحديث حالة الحساب البنكي بنجاح"),
                                                HttpStatus.OK.value(),
                                                bankAccountResponse));
        }
}
