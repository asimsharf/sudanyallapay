package com.sudagoarth.sudanyallapay.Documents.Dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sudagoarth.sudanyallapay.Documents.Entities.Document;
import com.sudagoarth.sudanyallapay.Enums.DocumentStatus;
import com.sudagoarth.sudanyallapay.Enums.EntityType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentResponse {

    @NotBlank(message = "Document ID is required")
    private Long id;

    @NotBlank(message = "Reference ID is required")
    private Long referenceId;

    @NotBlank(message = "Entity type is required")
    @Enumerated(EnumType.STRING)
    private EntityType entityType;

    @NotBlank(message = "Document name is required")
    private String documentName;

    @NotBlank(message = "Document URL is required")
    private String documentUrl;

    @NotBlank(message = "Document type is required")
    @Enumerated(EnumType.STRING)
    private DocumentStatus status;

    @NotBlank(message = "Updated at is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String updatedAt;

    // ✅ Convert a single Document to DocumentResponse
    public static DocumentResponse fromDocument(Document document) {
        DocumentResponse response = new DocumentResponse();
        response.id = document.getId();
        response.referenceId = document.getReferenceId();
        response.documentName = document.getDocumentName();
        response.documentUrl = document.getDocumentUrl();
        response.entityType = document.getEntityType();
        response.status = document.getStatus();
        response.updatedAt = document.getUploadedAt().toString();
        return response;
    }

    // ✅ Convert a list of Documents
    public static List<DocumentResponse> fromDocuments(List<Document> documents) {
        return documents.stream().map(DocumentResponse::fromDocument).collect(Collectors.toList());
    }

}
