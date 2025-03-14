package com.sudagoarth.sudanyallapay.Transactions.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudagoarth.sudanyallapay.Enums.TransactionStatus;
import com.sudagoarth.sudanyallapay.Transactions.Entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    boolean existsBySenderWalletIdOrReceiverWalletId(Long id, Long id2);

    boolean existsBySenderWalletIdAndStatus(Long id, TransactionStatus pending);

}