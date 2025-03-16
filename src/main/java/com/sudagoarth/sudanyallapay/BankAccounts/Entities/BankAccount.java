package com.sudagoarth.sudanyallapay.BankAccounts.Entities;

import com.sudagoarth.sudanyallapay.Enums.AccountStatus;
import com.sudagoarth.sudanyallapay.Users.Entities.User;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank_accounts", indexes = {
    @Index(name = "index_bank_accounts_user_id", columnList = "user_id")
})
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private String bankName;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    @Column(unique = true, nullable = false)
    private String iban;
    private String swiftCode;
    
    @Enumerated(EnumType.STRING)
    private AccountStatus status;


}