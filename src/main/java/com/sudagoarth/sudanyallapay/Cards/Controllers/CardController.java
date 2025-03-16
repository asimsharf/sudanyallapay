package com.sudagoarth.sudanyallapay.Cards.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sudagoarth.sudanyallapay.Cards.Dtos.CardRequest;
import com.sudagoarth.sudanyallapay.Cards.Dtos.CardResponse;
import com.sudagoarth.sudanyallapay.Cards.Dtos.CardTypeRequest;
import com.sudagoarth.sudanyallapay.Cards.Interfaces.CardInterface;
import com.sudagoarth.sudanyallapay.utils.ApiResponse;
import com.sudagoarth.sudanyallapay.utils.LocaledData;


@RestController
@RequestMapping("/api/v1/cards")
public class CardController {
    
}
