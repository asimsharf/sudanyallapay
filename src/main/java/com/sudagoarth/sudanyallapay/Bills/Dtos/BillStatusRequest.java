package com.sudagoarth.sudanyallapay.Bills.Dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.sudagoarth.sudanyallapay.Enums.BillStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BillStatusRequest {
    
    @NotBlank(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private BillStatus status;
    
}
