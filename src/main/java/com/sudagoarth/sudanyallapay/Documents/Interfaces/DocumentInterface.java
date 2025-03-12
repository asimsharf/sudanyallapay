package com.sudagoarth.sudanyallapay.Documents.Interfaces;

import java.util.List;

import com.sudagoarth.sudanyallapay.Documents.Dtos.DocumentRequirementRequest;
import com.sudagoarth.sudanyallapay.Documents.Dtos.DocumentRequirementResponse;
import com.sudagoarth.sudanyallapay.Documents.Dtos.DocumentResponse;
import com.sudagoarth.sudanyallapay.Documents.Dtos.DocumentStatusRequest;
import com.sudagoarth.sudanyallapay.Enums.EntityType;

public interface DocumentInterface {

    List<DocumentResponse> getDocuments(EntityType entityType, Long referenceId);

    List<DocumentRequirementResponse> getDocumentRequirements(EntityType entityType);

    DocumentResponse getDocument(Long id);

    DocumentResponse createDocument(DocumentRequirementRequest documentRequirementRepository);

    DocumentResponse updateDocument(Long id, DocumentRequirementRequest documentRequirementRepository);

    void deleteDocument(Long id);

    DocumentResponse statusDocument(Long id, DocumentStatusRequest documentStatusRequest);
}
