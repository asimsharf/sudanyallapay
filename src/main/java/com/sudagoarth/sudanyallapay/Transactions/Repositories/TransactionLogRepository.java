package com.sudagoarth.sudanyallapay.Transactions.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudagoarth.sudanyallapay.Transactions.Entities.Transaction;
import com.sudagoarth.sudanyallapay.Transactions.Entities.TransactionLog;

@Repository
public interface TransactionLogRepository extends JpaRepository<TransactionLog, Long> {

    Page<TransactionLog> findByTransaction(Transaction transaction, Pageable pageable);

    
} 