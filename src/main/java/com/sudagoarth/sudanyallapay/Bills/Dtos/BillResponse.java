package com.sudagoarth.sudanyallapay.Bills.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.sudagoarth.sudanyallapay.Enums.BillStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BillResponse {

    @NotNull(message = "ID is required")
    private Long id;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Biller name is required")
    private String billerName;

    @NotBlank(message = "Bill number is required")
    private String billNumber;

    @NotNull(message = "Amount is required")
    private Double amount;

    @NotBlank(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private BillStatus status;

    @NotNull(message = "Due date is required")
    private String dueDate;

    private String paidAt;

    private String createdAt;

}
