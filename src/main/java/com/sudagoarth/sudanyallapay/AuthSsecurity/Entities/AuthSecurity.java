package com.sudagoarth.sudanyallapay.AuthSsecurity.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.sudagoarth.sudanyallapay.Users.Entities.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auth_security")
public class AuthSecurity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private String otpCode;
    private LocalDateTime otpExpiresAt;
    private Boolean isUsed;
    private LocalDateTime createdAt;
}