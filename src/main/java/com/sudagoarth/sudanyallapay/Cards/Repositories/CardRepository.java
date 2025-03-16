package com.sudagoarth.sudanyallapay.Cards.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudagoarth.sudanyallapay.Cards.Entities.Card;

@Repository
public interface  CardRepository extends JpaRepository<Card, Long> {
    
}
