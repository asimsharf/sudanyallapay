package com.sudagoarth.sudanyallapay.Wallets.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudagoarth.sudanyallapay.Wallets.Entities.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    boolean existsByUserId(Long userId);

}