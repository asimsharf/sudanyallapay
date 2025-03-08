
package com.sudagoarth.sudanyallapay.Transactions.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.sudagoarth.sudanyallapay.Enums.TransactionStatus;
import com.sudagoarth.sudanyallapay.Enums.TransactionType;
import com.sudagoarth.sudanyallapay.Wallets.Entities.Wallet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions", indexes = {
    @Index(name = "index_transactions_sender_wallet_id", columnList = "sender_wallet_id"),
    @Index(name = "index_transactions_receiver_wallet_id", columnList = "receiver_wallet_id")
})
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "sender_wallet_id")
    private Wallet senderWallet;
    
    @ManyToOne
    @JoinColumn(name = "receiver_wallet_id")
    private Wallet receiverWallet;
    
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private BigDecimal amount;
    
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    private String referenceNumber;
    private String description;
    private LocalDateTime createdAt;
}