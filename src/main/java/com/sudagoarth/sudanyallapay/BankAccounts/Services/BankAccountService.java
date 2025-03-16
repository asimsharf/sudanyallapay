package com.sudagoarth.sudanyallapay.BankAccounts.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sudagoarth.sudanyallapay.BankAccounts.Dtos.BankAccountRequest;
import com.sudagoarth.sudanyallapay.BankAccounts.Dtos.BankAccountResponse;
import com.sudagoarth.sudanyallapay.BankAccounts.Dtos.BankAccountStatusRequest;
import com.sudagoarth.sudanyallapay.BankAccounts.Entities.BankAccount;
import com.sudagoarth.sudanyallapay.BankAccounts.Interfaces.BankAccountInterface;
import com.sudagoarth.sudanyallapay.BankAccounts.Repositories.BankAccountRepository;
import com.sudagoarth.sudanyallapay.Enums.AccountStatus;
import com.sudagoarth.sudanyallapay.Users.Entities.User;
import com.sudagoarth.sudanyallapay.Users.Repositories.UserRepository;
import com.sudagoarth.sudanyallapay.exceptions.DuplicateException;
import com.sudagoarth.sudanyallapay.exceptions.NotFoundException;

@Service
public class BankAccountService implements BankAccountInterface {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<BankAccountResponse> getBankAccounts(Long userId, Pageable pageable) throws NotFoundException {

        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Page<BankAccount> bankAccounts = bankAccountRepository.findByUserId(userId, pageable);
        return bankAccounts.map(BankAccountResponse::fromBankAccount);
    }

    @Override
    public BankAccountResponse getBankAccount(Long bankAccountId) throws NotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new NotFoundException("Bank Account not found"));
        return BankAccountResponse.fromBankAccount(bankAccount);
    }

    @Override
    public BankAccountResponse createBankAccount(BankAccountRequest bankAccountRequest) throws DuplicateException {

        // Check if the user exists
        User user = userRepository.findById(bankAccountRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        // Check if the bank account already exists
        if (bankAccountRepository.findByIbanOrAccountNumber(
                bankAccountRequest.getIban(), bankAccountRequest.getAccountNumber()).isPresent()) {
            throw new DuplicateException("Bank Account already exists");
        }

        // Create and save the new bank account
        BankAccount bankAccount = buildBankAccount(bankAccountRequest, user);
        bankAccount = bankAccountRepository.save(bankAccount);

        // Return response
        return BankAccountResponse.fromBankAccount(bankAccount);
    }

    /**
     * Helper method to create a BankAccount entity from request
     */
    private BankAccount buildBankAccount(BankAccountRequest request, User user) {
        return BankAccount.builder()
                .accountNumber(request.getAccountNumber())
                .bankName(request.getBankName())
                .iban(request.getIban())
                .status(AccountStatus.ACTIVE)
                .swiftCode(request.getSwiftCode())
                .user(user)
                .build();
    }

    @Override
    public BankAccountResponse updateBankAccount(Long bankAccountId, BankAccountRequest bankAccountRequest)
            throws NotFoundException, DuplicateException {

        // Fetch the existing bank account
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new NotFoundException("Bank Account not found"));

        // Check for duplicate IBAN or Account Number (excluding the current account)
        Optional<BankAccount> existingBankAccount = bankAccountRepository.findByIbanOrAccountNumber(
                bankAccountRequest.getIban(), bankAccountRequest.getAccountNumber());

        if (existingBankAccount.isPresent() && !existingBankAccount.get().getId().equals(bankAccountId)) {
            throw new DuplicateException("Another bank account with the same IBAN or account number already exists.");
        }

        // Update fields
        bankAccount = BankAccount.builder()
                .id(bankAccount.getId()) // Preserve ID
                .accountNumber(bankAccountRequest.getAccountNumber())
                .bankName(bankAccountRequest.getBankName())
                .iban(bankAccountRequest.getIban())
                .swiftCode(bankAccountRequest.getSwiftCode())
                .user(bankAccount.getUser()) // Preserve user
                .status(bankAccount.getStatus()) // Preserve status
                .build();

        // Save updated bank account
        bankAccount = bankAccountRepository.save(bankAccount);

        return BankAccountResponse.fromBankAccount(bankAccount);
    }

    @Override
    public void deleteBankAccount(Long bankAccountId) throws NotFoundException {
        throw new UnsupportedOperationException("Unimplemented method 'deleteBankAccount'");
    }

    @Override
    public BankAccountResponse statusBankAccount(Long bankAccountId, BankAccountStatusRequest bankAccountStatusRequest)
            throws NotFoundException {
        throw new UnsupportedOperationException("Unimplemented method 'statusBankAccount'");
    }

}
