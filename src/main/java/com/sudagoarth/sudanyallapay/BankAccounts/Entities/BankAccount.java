package com.sudagoarth.sudanyallapay.BankAccounts.Entities;

import com.sudagoarth.sudanyallapay.Enums.AccountStatus;
import com.sudagoarth.sudanyallapay.Users.Entities.User;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank_accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private String bankName;
    private String accountNumber;
    private String iban;
    private String swiftCode;
    
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
}