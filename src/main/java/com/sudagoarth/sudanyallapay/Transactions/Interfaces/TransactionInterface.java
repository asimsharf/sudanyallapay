package com.sudagoarth.sudanyallapay.Transactions.Interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sudagoarth.sudanyallapay.Transactions.Dtos.TransactionLogResponse;
import com.sudagoarth.sudanyallapay.Transactions.Dtos.TransactionResponse;

public interface TransactionInterface {
    Page<TransactionResponse> getTransactions(Pageable pageable);

    TransactionResponse getTransaction(Long id);

    Page<TransactionLogResponse> getTransactionLogs(Long transactionId, Pageable pageable);

}
