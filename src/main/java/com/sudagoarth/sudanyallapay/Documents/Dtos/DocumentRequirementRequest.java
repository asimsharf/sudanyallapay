package com.sudagoarth.sudanyallapay.Documents.Dtos;

import com.sudagoarth.sudanyallapay.Enums.EntityType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequirementRequest {
    @NotNull(message = "Reference ID is required")
    private Long referenceId;

    @NotNull(message = "Entity type is required")
    @Enumerated(EnumType.STRING)
    private EntityType entityType;

    @NotBlank(message = "Document name is required")
    private String documentName;

    @NotBlank(message = "Document is required")
    private String base64Document;
}
