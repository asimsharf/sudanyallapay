package com.sudagoarth.sudanyallapay.BankAccounts.Interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sudagoarth.sudanyallapay.BankAccounts.Dtos.BankAccountRequest;
import com.sudagoarth.sudanyallapay.BankAccounts.Dtos.BankAccountResponse;
import com.sudagoarth.sudanyallapay.BankAccounts.Dtos.BankAccountStatusRequest;

public interface BankAccountInterface {
    Page<BankAccountResponse> getBankAccounts(Long userId, Pageable pageable);

    BankAccountResponse getBankAccount(Long bankAccountId);

    BankAccountResponse createBankAccount(BankAccountRequest bankAccountRequest);

    BankAccountResponse updateBankAccount(Long bankAccountId, BankAccountRequest bankAccountRequest);

    void deleteBankAccount(Long bankAccountId);

    BankAccountResponse statusBankAccount(Long bankAccountId, BankAccountStatusRequest bankAccountStatusRequest);
}
