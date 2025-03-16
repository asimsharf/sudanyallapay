package com.sudagoarth.sudanyallapay.BankAccounts.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import com.sudagoarth.sudanyallapay.BankAccounts.Entities.BankAccount;
import com.sudagoarth.sudanyallapay.Enums.AccountStatus;
import com.sudagoarth.sudanyallapay.Users.Dtos.UserResponse;

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
    private UserResponse user;

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


    // ✅ Convert a single BankAccount to BankAccountResponse
    public static BankAccountResponse fromBankAccount(BankAccount bankAccount) {
        BankAccountResponse response = new BankAccountResponse();
        response.id = bankAccount.getId();
        response.user = UserResponse.fromUser(bankAccount.getUser());
        response.bankName = bankAccount.getBankName();
        response.accountNumber = bankAccount.getAccountNumber();
        response.iban = bankAccount.getIban();
        response.swiftCode = bankAccount.getSwiftCode();
        response.status = bankAccount.getStatus();
        return response;
    }

    // ✅ Convert a list of BankAccounts
    public static List<BankAccountResponse> fromBankAccounts(List<BankAccount> bankAccounts) {
        return bankAccounts.stream().map(BankAccountResponse::fromBankAccount).collect(Collectors.toList());
    }

}
