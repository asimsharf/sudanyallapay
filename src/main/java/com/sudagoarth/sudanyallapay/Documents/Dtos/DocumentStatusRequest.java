package com.sudagoarth.sudanyallapay.Documents.Dtos;

import com.sudagoarth.sudanyallapay.Enums.DocumentStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentStatusRequest {

    @NotBlank(message = "Document type is required")
    @Enumerated(EnumType.STRING)
    private DocumentStatus status;

}
