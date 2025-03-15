package com.sudagoarth.sudanyallapay.Documents.Interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sudagoarth.sudanyallapay.Documents.Dtos.DocumentRequirementRequest;
import com.sudagoarth.sudanyallapay.Documents.Dtos.DocumentRequirementResponse;
import com.sudagoarth.sudanyallapay.Documents.Dtos.DocumentResponse;
import com.sudagoarth.sudanyallapay.Documents.Dtos.DocumentStatusRequest;
import com.sudagoarth.sudanyallapay.Enums.EntityType;

public interface DocumentInterface {

    Page<DocumentResponse> getDocuments(EntityType entityType, Long referenceId, Pageable pageable);

    Page<DocumentRequirementResponse> getDocumentRequirements(EntityType entityType, Pageable pageable);

    DocumentResponse getDocument(Long id);

    DocumentResponse createDocument(DocumentRequirementRequest documentRequirementRepository);

    DocumentResponse updateDocument(Long id, DocumentRequirementRequest documentRequirementRepository);

    void deleteDocument(Long id);

    DocumentResponse statusDocument(Long id, DocumentStatusRequest documentStatusRequest);
}
