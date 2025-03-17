package com.sudagoarth.sudanyallapay.Documents.Services;


import com.sudagoarth.sudanyallapay.Enums.EntityType;
import com.sudagoarth.sudanyallapay.Users.Repositories.UserRepository;
import com.sudagoarth.sudanyallapay.Transactions.Repositories.TransactionRepository;
import com.sudagoarth.sudanyallapay.Wallets.Repositories.WalletRepository;
import com.sudagoarth.sudanyallapay.BankAccounts.Repositories.BankAccountRepository;
import com.sudagoarth.sudanyallapay.Cards.Repositories.CardRepository;
import com.sudagoarth.sudanyallapay.Bills.Repositories.BillRepository;
import com.sudagoarth.sudanyallapay.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EntityValidationService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final BankAccountRepository bankAccountRepository;
    private final CardRepository cardRepository;
    private final BillRepository billRepository;

    public EntityValidationService(UserRepository userRepository, TransactionRepository transactionRepository,
                                   WalletRepository walletRepository, BankAccountRepository bankAccountRepository,
                                   CardRepository cardRepository, BillRepository billRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.cardRepository = cardRepository;
        this.billRepository = billRepository;
    }

    public void validateReferenceEntity(EntityType entityType, Long referenceId) {
        switch (entityType) {
            case USER:
                if (!userRepository.existsById(referenceId)) throw new NotFoundException("User not found.");
                break;
            case TRANSACTION:
                if (!transactionRepository.existsById(referenceId)) throw new NotFoundException("Transaction not found.");
                break;
            case WALLET:
                if (!walletRepository.existsById(referenceId)) throw new NotFoundException("Wallet not found.");
                break;
            case BANK_ACCOUNT:
                if (!bankAccountRepository.existsById(referenceId)) throw new NotFoundException("Bank Account not found.");
                break;
            case CARD:
                if (!cardRepository.existsById(referenceId)) throw new NotFoundException("Card not found.");
                break;
            case BILL:
                if (!billRepository.existsById(referenceId)) throw new NotFoundException("Bill not found.");
                break;
            default:
                throw new IllegalArgumentException("Unsupported entity type: " + entityType);
        }
    }
}
