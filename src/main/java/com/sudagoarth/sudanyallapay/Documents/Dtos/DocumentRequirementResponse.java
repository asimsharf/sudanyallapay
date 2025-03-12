package com.sudagoarth.sudanyallapay.Documents.Dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.sudagoarth.sudanyallapay.Documents.Entities.DocumentRequirement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequirementResponse {
    private Long id;
    private String documentName;
    private boolean isRequired;
    private String createdAt;

    // ✅ Convert a single DocumentRequirement to DocumentRequirementResponse
    public static DocumentRequirementResponse fromDocumentRequirement(DocumentRequirement requirement) {
        DocumentRequirementResponse response = new DocumentRequirementResponse();
        response.id = requirement.getId();
        response.documentName = requirement.getDocumentName();
        response.isRequired = requirement.getIsRequired();
        response.createdAt = requirement.getCreatedAt().toString();
        return response;
    }

    // ✅ Convert a list of DocumentRequirements
    public static List<DocumentRequirementResponse> fromDocumentRequirements(
            List<DocumentRequirement> documentRequirements) {
        return documentRequirements.stream().map(DocumentRequirementResponse::fromDocumentRequirement)
                .collect(Collectors.toList());
    }

}
