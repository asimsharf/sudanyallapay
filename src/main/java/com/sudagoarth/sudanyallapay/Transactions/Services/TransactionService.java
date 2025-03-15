package com.sudagoarth.sudanyallapay.Transactions.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sudagoarth.sudanyallapay.Transactions.Dtos.TransactionLogResponse;
import com.sudagoarth.sudanyallapay.Transactions.Dtos.TransactionResponse;
import com.sudagoarth.sudanyallapay.Transactions.Entities.Transaction;
import com.sudagoarth.sudanyallapay.Transactions.Entities.TransactionLog;
import com.sudagoarth.sudanyallapay.Transactions.Interfaces.TransactionInterface;
import com.sudagoarth.sudanyallapay.Transactions.Repositories.TransactionLogRepository;
import com.sudagoarth.sudanyallapay.Transactions.Repositories.TransactionRepository;
import com.sudagoarth.sudanyallapay.exceptions.NotFoundException;

@Service
public class TransactionService implements TransactionInterface {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionLogRepository transactionLogRepository;

    @Override
    public Page<TransactionResponse> getTransactions(Pageable pageable) {
        Page<Transaction> transactions = transactionRepository.findAll(pageable);
        return transactions.map(TransactionResponse::fromTransaction);
    }

    @Override
    public TransactionResponse getTransaction(Long id) throws NotFoundException {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transaction not found"));
        return TransactionResponse.fromTransaction(transaction);
    }

    @Override
    public Page<TransactionLogResponse> getTransactionLogs(Long transactionId, Pageable pageable)
            throws NotFoundException {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new NotFoundException("Transaction not found"));

        Page<TransactionLog> transactionLogs = transactionLogRepository.findByTransaction(transaction, pageable);
        return transactionLogs.map(TransactionLogResponse::fromTransaction);
    }

}
