package com.sudagoarth.sudanyallapay.Wallets.Entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.sudagoarth.sudanyallapay.Enums.WalletStatus;
import com.sudagoarth.sudanyallapay.Users.Entities.User;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wallets", indexes = {
    @Index(name = "index_wallets_user_id", columnList = "user_id")
})
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private BigDecimal balance;
    private String currency;

    @Enumerated(EnumType.STRING)
    private WalletStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
