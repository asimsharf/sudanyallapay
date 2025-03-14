package com.sudagoarth.sudanyallapay.Transactions.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sudagoarth.sudanyallapay.Transactions.Dtos.TransactionResponse;
import com.sudagoarth.sudanyallapay.Transactions.Entities.Transaction;
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
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new NotFoundException("Transaction not found"));
        return TransactionResponse.fromTransaction(transaction);
    }


}
