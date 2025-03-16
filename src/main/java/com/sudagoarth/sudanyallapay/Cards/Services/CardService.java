package com.sudagoarth.sudanyallapay.Cards.Services;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.sudagoarth.sudanyallapay.Cards.Dtos.CardRequest;
import com.sudagoarth.sudanyallapay.Cards.Dtos.CardResponse;
import com.sudagoarth.sudanyallapay.Cards.Dtos.CardTypeRequest;
import com.sudagoarth.sudanyallapay.Cards.Interfaces.CardInterface;

@Service
public class CardService implements CardInterface {

    @Override
    public Page<CardResponse> getCards(Long userId, Pageable pageable) {
        throw new UnsupportedOperationException("Unimplemented method 'getCards'");
    }

    @Override
    public CardResponse getCard(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'getCard'");
    }

    @Override
    public CardResponse createCard(CardRequest cardRequest) {
        throw new UnsupportedOperationException("Unimplemented method 'createCard'");
    }

    @Override
    public CardResponse updateCard(Long id, CardRequest cardRequest) {
        throw new UnsupportedOperationException("Unimplemented method 'updateCard'");
    }

    @Override
    public void deleteCard(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteCard'");
    }

    @Override
    public CardResponse cardType(Long id, CardTypeRequest cardTypeRequest) {
        throw new UnsupportedOperationException("Unimplemented method 'cardType'");
    }
    
}
