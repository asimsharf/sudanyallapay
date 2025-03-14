package com.sudagoarth.sudanyallapay.Transactions.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sudagoarth.sudanyallapay.Transactions.Dtos.TransactionResponse;
import com.sudagoarth.sudanyallapay.Transactions.Interfaces.TransactionInterface;
import com.sudagoarth.sudanyallapay.Users.Controllers.UserController;
import com.sudagoarth.sudanyallapay.utils.ApiResponse;
import com.sudagoarth.sudanyallapay.utils.LocaledData;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionInterface transactionInterface;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getName());

    @GetMapping
    public ResponseEntity<ApiResponse> getTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        LOGGER.info("Getting transactions - Page: {} | Size: {}", page, size);
        
        Pageable pageable = PageRequest.of(page, size);
        Page<TransactionResponse> transactionResponses = transactionInterface.getTransactions(pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        new LocaledData("Transactions retrieved successfully", "تم استرجاع المعاملات بنجاح"),
                        HttpStatus.OK.value(),
                        transactionResponses.getContent(),
                        transactionResponses.getPageable()));
    }

}
