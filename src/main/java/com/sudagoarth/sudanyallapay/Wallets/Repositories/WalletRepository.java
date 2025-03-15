package com.sudagoarth.sudanyallapay.Wallets.Repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import com.sudagoarth.sudanyallapay.Wallets.Entities.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    boolean existsByUserId(Long userId);

    @Query("SELECT w FROM Wallet w ORDER BY w.createdAt DESC")
    Page<Wallet> getWallets(Pageable pageable);

}