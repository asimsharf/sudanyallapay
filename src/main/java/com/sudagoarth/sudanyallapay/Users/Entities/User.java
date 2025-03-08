package com.sudagoarth.sudanyallapay.Users.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", indexes = {
    @Index(name = "index_users_email", columnList = "email"),
    @Index(name = "index_users_phone_number", columnList = "phone_number"),
    @Index(name = "index_users_national_id", columnList = "national_id")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String passwordHash;
    private String nationalId;
    private LocalDate dateOfBirth;
    private String profileImageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}