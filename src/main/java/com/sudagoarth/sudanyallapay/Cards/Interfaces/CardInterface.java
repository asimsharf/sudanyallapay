package com.sudagoarth.sudanyallapay.Cards.Interfaces;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;

import com.sudagoarth.sudanyallapay.Cards.Dtos.CardRequest;
import com.sudagoarth.sudanyallapay.Cards.Dtos.CardResponse;
import com.sudagoarth.sudanyallapay.Cards.Dtos.CardTypeRequest;

public interface CardInterface {
    Page<CardResponse> getCards(Long userId, Pageable pageable);
    CardResponse getCard(Long id);
    CardResponse createCard(CardRequest cardRequest);
    CardResponse updateCard(Long id, CardRequest cardRequest);
    void deleteCard(Long id);
    CardResponse cardType(Long id, CardTypeRequest cardTypeRequest);
}