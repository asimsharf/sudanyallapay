package com.sudagoarth.sudanyallapay.Bills.Entities;


import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.sudagoarth.sudanyallapay.Enums.BillStatus;
import com.sudagoarth.sudanyallapay.Users.Entities.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bills", indexes = {
    @Index(name = "index_bills_user_id", columnList = "user_id")
})
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private String billerName;
    private String billNumber;
    private BigDecimal amount;
    
    @Enumerated(EnumType.STRING)
    private BillStatus status;
    private LocalDate dueDate;
    private LocalDate paidAt;
}
