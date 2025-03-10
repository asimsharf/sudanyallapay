package com.sudagoarth.sudanyallapay.Users.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auth_security", indexes = {
        @Index(name = "index_auth_security_user_id", columnList = "user_id")
})
public class AuthSecurity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String code;
    private LocalDateTime expiresAt;
    private Boolean isUsed;
    private LocalDateTime createdAt;
}