package com.sudagoarth.sudanyallapay.Transactions.Dtos;

import java.util.List;

import com.sudagoarth.sudanyallapay.Enums.TransactionStatus;
import com.sudagoarth.sudanyallapay.Transactions.Entities.TransactionLog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionLogResponse {

    @NotNull(message = "ID is required")
    private Long id;

    @NotBlank(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @NotNull(message = "Transaction Type is required")
    private String updated_at;

    @NotNull(message = "Transaction Type is required")
    private TransactionResponse transaction;

    public static List<TransactionLogResponse> fromTransactionLogs(List<TransactionLog> transactionLog) {
        return transactionLog.stream().map(TransactionLogResponse::fromTransaction).toList();
    }

    public static TransactionLogResponse fromTransaction(TransactionLog transactionLog) {
        TransactionLogResponse response = new TransactionLogResponse();
        response.id = transactionLog.getId();
        response.status = transactionLog.getStatus();
        response.updated_at = transactionLog.getUpdatedAt().toString();
        response.transaction = TransactionResponse.fromTransaction(transactionLog.getTransaction());
        return response;
    }

}
