package com.sudagoarth.sudanyallapay.Transactions.Entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.sudagoarth.sudanyallapay.Enums.TransactionStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction_logs")
public class TransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
    
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    private LocalDateTime updatedAt;
}
