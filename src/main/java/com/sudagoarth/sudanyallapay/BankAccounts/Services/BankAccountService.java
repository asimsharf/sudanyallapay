package com.sudagoarth.sudanyallapay.BankAccounts.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sudagoarth.sudanyallapay.BankAccounts.Dtos.BankAccountRequest;
import com.sudagoarth.sudanyallapay.BankAccounts.Dtos.BankAccountResponse;
import com.sudagoarth.sudanyallapay.BankAccounts.Dtos.BankAccountStatusRequest;
import com.sudagoarth.sudanyallapay.BankAccounts.Interfaces.BankAccountInterface;

@Service
public class BankAccountService implements BankAccountInterface {

    @Override
    public Page<BankAccountResponse> getBankAccounts(Long userId, Pageable pageable) {
        throw new UnsupportedOperationException("Unimplemented method 'getBankAccounts'");
    }

    @Override
    public BankAccountResponse getBankAccount(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'getBankAccount'");
    }

    @Override
    public BankAccountResponse createBankAccount(BankAccountRequest bankAccountRequest) {
        throw new UnsupportedOperationException("Unimplemented method 'createBankAccount'");
    }

    @Override
    public BankAccountResponse updateBankAccount(Long id, BankAccountRequest bankAccountRequest) {
        throw new UnsupportedOperationException("Unimplemented method 'updateBankAccount'");
    }

    @Override
    public void deleteBankAccount(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteBankAccount'");
    }

    @Override
    public BankAccountResponse statusBankAccount(Long id, BankAccountStatusRequest bankAccountStatusRequest) {
        throw new UnsupportedOperationException("Unimplemented method 'statusBankAccount'");
    }
    
}
