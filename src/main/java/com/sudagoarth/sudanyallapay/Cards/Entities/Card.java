package com.sudagoarth.sudanyallapay.Cards.Entities;

import com.sudagoarth.sudanyallapay.Enums.CardType;
import com.sudagoarth.sudanyallapay.Users.Entities.User;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cards", indexes = {
    @Index(name = "index_cards_user_id", columnList = "user_id")
})
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private String cardNumber;
    
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    private String expiryDate;
    private String cvv;
    private Boolean isDefault;
}