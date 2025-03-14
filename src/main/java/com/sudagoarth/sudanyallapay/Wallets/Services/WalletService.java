package com.sudagoarth.sudanyallapay.Wallets.Services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudagoarth.sudanyallapay.Enums.TransactionStatus;
import com.sudagoarth.sudanyallapay.Enums.TransactionType;
import com.sudagoarth.sudanyallapay.Enums.WalletStatus;
import com.sudagoarth.sudanyallapay.Transactions.Entities.Transaction;
import com.sudagoarth.sudanyallapay.Transactions.Entities.TransactionLog;
import com.sudagoarth.sudanyallapay.Transactions.Repositories.TransactionLogRepository;
import com.sudagoarth.sudanyallapay.Transactions.Repositories.TransactionRepository;
import com.sudagoarth.sudanyallapay.Users.Entities.User;
import com.sudagoarth.sudanyallapay.Users.Repositories.UserRepository;
import com.sudagoarth.sudanyallapay.Wallets.Dtos.WalletRequest;
import com.sudagoarth.sudanyallapay.Wallets.Dtos.WalletResponse;
import com.sudagoarth.sudanyallapay.Wallets.Entities.Wallet;
import com.sudagoarth.sudanyallapay.Wallets.Interfaces.WalletInterface;
import com.sudagoarth.sudanyallapay.Wallets.Repositories.WalletRepository;
import com.sudagoarth.sudanyallapay.exceptions.DuplicateException;
import com.sudagoarth.sudanyallapay.exceptions.InsufficientBalanceException;
import com.sudagoarth.sudanyallapay.exceptions.NotFoundException;

@Service
public class WalletService implements WalletInterface {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionLogRepository transactionLogRepository;

    @Override
    public List<WalletResponse> getWallets() {
        List<Wallet> wallets = walletRepository.findAll();
        return WalletResponse.fromWallets(wallets);
    }

    @Override
    public WalletResponse getWallet(Long id) throws NotFoundException {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new NotFoundException("Wallet not found"));
        return WalletResponse.fromWallet(wallet);
    }

    @Override
    public WalletResponse createWallet(WalletRequest walletRequest) throws DuplicateException {

        User user = userRepository.findById(walletRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (walletRepository.existsByUserId(walletRequest.getUserId())) {
            throw new DuplicateException("Wallet already exists");
        }

        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setCurrency("SDG");
        wallet.setStatus(WalletStatus.ACTIVE);
        wallet.setCreatedAt(LocalDateTime.now());
        wallet.setUpdatedAt(LocalDateTime.now());

        walletRepository.save(wallet);

        return WalletResponse.fromWallet(wallet);
    }

    @Override
    public WalletResponse depositWallet(Long walletId, BigDecimal amount) throws NotFoundException {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new NotFoundException("Wallet not found"));

        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);

        // Log the transaction
        Transaction transaction = new Transaction();
        transaction.setSenderWallet(null); // No sender in deposits
        transaction.setReceiverWallet(wallet); // The wallet receiving the deposit
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setAmount(amount);
        transaction.setReferenceNumber(generateReferenceNumber());
        transaction.setStatus(TransactionStatus.COMPLETED);
        transaction.setDescription("Deposit");
        transaction.setCreatedAt(LocalDateTime.now());

        transactionRepository.save(transaction);

        // Log the transaction status
        TransactionLog log = new TransactionLog();
        log.setTransaction(transaction);
        log.setStatus(TransactionStatus.COMPLETED);
        log.setUpdatedAt(LocalDateTime.now());
        transactionLogRepository.save(log);

        return WalletResponse.fromWallet(wallet);
    }

    @Override
    @Transactional
    public WalletResponse withdrawWallet(Long id, BigDecimal amount) throws NotFoundException {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Wallet not found"));

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance to complete this transaction.");
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));
        walletRepository.save(wallet);

        // Log the transaction
        Transaction transaction = new Transaction();
        transaction.setSenderWallet(wallet); // The wallet making the withdrawal
        transaction.setReceiverWallet(null); // No receiver in withdrawals
        transaction.setTransactionType(TransactionType.WITHDRAWAL);
        transaction.setAmount(amount);
        transaction.setReferenceNumber(generateReferenceNumber());
        transaction.setStatus(TransactionStatus.COMPLETED);
        transaction.setDescription("Withdrawal");
        transaction.setCreatedAt(LocalDateTime.now());
        transactionRepository.save(transaction);

        // Log the transaction status
        TransactionLog log = new TransactionLog();
        log.setTransaction(transaction);
        log.setStatus(TransactionStatus.COMPLETED);
        log.setUpdatedAt(LocalDateTime.now());
        transactionLogRepository.save(log);

        return WalletResponse.fromWallet(wallet);
    }

    @Override
    @Transactional
    public WalletResponse transferWallet(Long senderId, Long receiverId, BigDecimal amount) throws NotFoundException {
        
        if (senderId.equals(receiverId)) {
            throw new IllegalArgumentException("Sender and Receiver cannot be the same.");
        }
    
        Wallet senderWallet = walletRepository.findById(senderId)
                .orElseThrow(() -> new NotFoundException("Sender Wallet not found"));
    
        Wallet receiverWallet = walletRepository.findById(receiverId)
                .orElseThrow(() -> new NotFoundException("Receiver Wallet not found"));
    
        if (senderWallet.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance to complete the transfer.");
        }
    
        // Create transaction log before updating balances
        Transaction transaction = new Transaction();
        transaction.setSenderWallet(senderWallet);
        transaction.setReceiverWallet(receiverWallet);
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setAmount(amount);
        transaction.setReferenceNumber(generateReferenceNumber());
        transaction.setStatus(TransactionStatus.PENDING); // Start as PENDING
        transaction.setDescription("Transfer initiated");
        transaction.setCreatedAt(LocalDateTime.now());
    
        transaction = transactionRepository.save(transaction);
    
        // Log initial transaction state
        TransactionLog initialLog = new TransactionLog();
        initialLog.setTransaction(transaction);
        initialLog.setStatus(TransactionStatus.PENDING);
        initialLog.setUpdatedAt(LocalDateTime.now());
        transactionLogRepository.save(initialLog);
    
        // Update balances
        senderWallet.setBalance(senderWallet.getBalance().subtract(amount));
        receiverWallet.setBalance(receiverWallet.getBalance().add(amount));
    
        walletRepository.save(senderWallet);
        walletRepository.save(receiverWallet);
    
        // Update transaction status
        transaction.setStatus(TransactionStatus.COMPLETED);
        transaction.setDescription("Transfer completed");
        transactionRepository.save(transaction);
    
        // Log completion status
        TransactionLog completionLog = new TransactionLog();
        completionLog.setTransaction(transaction);
        completionLog.setStatus(TransactionStatus.COMPLETED);
        completionLog.setUpdatedAt(LocalDateTime.now());
        transactionLogRepository.save(completionLog);
    
        return WalletResponse.fromWallet(senderWallet);
    }
    
    @Override
    public void deleteWallet(Long id) throws NotFoundException {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Wallet not found"));
    
        // Prevent deletion if balance is not zero
        if (wallet.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalStateException("Cannot delete a wallet with a non-zero balance.");
        }
    
        // Prevent deletion if there are existing transactions
        boolean hasTransactions = transactionRepository.existsBySenderWalletIdOrReceiverWalletId(wallet.getId(), wallet.getId());
        if (hasTransactions) {
            throw new IllegalStateException("Cannot delete a wallet with associated transactions.");
        }
    
        // Soft delete: Mark as INACTIVE instead of hard deletion
        wallet.setStatus(WalletStatus.INACTIVE);
        walletRepository.save(wallet);
    }
    
    @Override
    public WalletResponse statusWallet(Long id, WalletStatus status) throws NotFoundException {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Wallet not found"));
    
        // Prevent setting status to INACTIVE if there are active transactions
        boolean hasActiveTransactions = transactionRepository.existsBySenderWalletIdAndStatus(wallet.getId(), TransactionStatus.PENDING);
        if (hasActiveTransactions && status == WalletStatus.INACTIVE) {
            throw new IllegalStateException("Cannot deactivate wallet with pending transactions.");
        }
    
        wallet.setStatus(status);
        walletRepository.save(wallet);
        return WalletResponse.fromWallet(wallet);
    }
    

    private String generateReferenceNumber() {
        return "TXN-" + System.currentTimeMillis();
    }

}
