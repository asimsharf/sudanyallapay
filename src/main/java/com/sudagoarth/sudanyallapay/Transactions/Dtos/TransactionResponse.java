package com.sudagoarth.sudanyallapay.Transactions.Dtos;

import java.math.BigDecimal;
import java.util.List;

import com.sudagoarth.sudanyallapay.Enums.TransactionStatus;
import com.sudagoarth.sudanyallapay.Enums.TransactionType;
import com.sudagoarth.sudanyallapay.Transactions.Entities.Transaction;
import com.sudagoarth.sudanyallapay.Wallets.Dtos.WalletResponse;
import com.sudagoarth.sudanyallapay.Wallets.Entities.Wallet;

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
public class TransactionResponse {

    @NotNull(message = "ID is required")
    private Long id;

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @NotNull(message = "Created At is required")
    private String created_at;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Reference Number is required")
    private String reference_number;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @NotNull(message = "Transaction Type is required")
    @Enumerated(EnumType.STRING)
    private TransactionType transaction_type;

    // @NotNull(message = "Receiver Wallet ID is required")
    private WalletResponse receiver;

    // @NotNull(message = "Sender Wallet ID is required");
    private WalletResponse sender;

    public static List<TransactionResponse> fromTransactions(List<Transaction> transaction) {
        return transaction.stream().map(TransactionResponse::fromTransaction).toList();
    }

    public static TransactionResponse fromTransaction(Transaction transaction) {
        TransactionResponse response = new TransactionResponse();
        response.id = transaction.getId();
        response.amount = transaction.getAmount();
        response.created_at = transaction.getCreatedAt().toString();
        response.description = transaction.getDescription();
        response.reference_number = transaction.getReferenceNumber();
        response.status = transaction.getStatus();
        response.transaction_type = transaction.getTransactionType();
        
        // Handle null sender and receiver safely
        response.receiver = transaction.getReceiverWallet() != null ? WalletResponse.fromWallet(transaction.getReceiverWallet()) : null;
        response.sender = transaction.getSenderWallet() != null ? WalletResponse.fromWallet(transaction.getSenderWallet()) : null;
        
        return response;
    }
    

}
