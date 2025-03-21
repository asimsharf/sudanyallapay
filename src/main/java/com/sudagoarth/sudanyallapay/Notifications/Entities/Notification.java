package com.sudagoarth.sudanyallapay.Notifications.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.sudagoarth.sudanyallapay.Users.Entities.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications", indexes = {
    @Index(name = "index_notifications_user_id", columnList = "user_id")
})
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private String message;
    private Boolean isRead;
    private LocalDateTime createdAt;
}
