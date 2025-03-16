package com.sudagoarth.sudanyallapay.BankAccounts.Interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sudagoarth.sudanyallapay.BankAccounts.Dtos.BankAccountRequest;
import com.sudagoarth.sudanyallapay.BankAccounts.Dtos.BankAccountResponse;
import com.sudagoarth.sudanyallapay.BankAccounts.Dtos.BankAccountStatusRequest;

public interface BankAccountInterface {
    Page<BankAccountResponse> getBankAccounts(Long userId, Pageable pageable);

    BankAccountResponse getBankAccount(Long id);

    BankAccountResponse createBankAccount(BankAccountRequest bankAccountRequest);

    BankAccountResponse updateBankAccount(Long id, BankAccountRequest bankAccountRequest);

    void deleteBankAccount(Long id);

    BankAccountResponse statusBankAccount(Long id, BankAccountStatusRequest bankAccountStatusRequest);
}
