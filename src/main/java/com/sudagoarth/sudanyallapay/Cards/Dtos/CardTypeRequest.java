package com.sudagoarth.sudanyallapay.Cards.Dtos;

import com.sudagoarth.sudanyallapay.Enums.CardType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardTypeRequest {
    
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Card number is required")
    private String cardNumber;

    @NotBlank(message = "Card type is required")
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @NotBlank(message = "Expiry date is required")
    private String expiryDate;

    @NotBlank(message = "CVV is required")
    private String cvv;

    private boolean isDefault;
    
}
