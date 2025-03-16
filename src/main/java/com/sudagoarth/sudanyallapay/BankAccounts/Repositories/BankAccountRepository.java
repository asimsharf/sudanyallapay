package com.sudagoarth.sudanyallapay.BankAccounts.Repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sudagoarth.sudanyallapay.BankAccounts.Entities.BankAccount;
import com.sudagoarth.sudanyallapay.Users.Entities.User;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Page<BankAccount> findByUserId(Long userId, Pageable pageable);

    @Query("SELECT b FROM BankAccount b WHERE b.iban = :iban OR b.accountNumber = :accountNumber")
    Optional<BankAccount> findByIbanOrAccountNumber(String iban, String accountNumber);
    

}
