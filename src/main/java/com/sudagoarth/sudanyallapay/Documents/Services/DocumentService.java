package com.sudagoarth.sudanyallapay.Documents.Services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sudagoarth.sudanyallapay.Documents.Dtos.DocumentRequirementRequest;
import com.sudagoarth.sudanyallapay.Documents.Dtos.DocumentRequirementResponse;
import com.sudagoarth.sudanyallapay.Documents.Dtos.DocumentResponse;
import com.sudagoarth.sudanyallapay.Documents.Dtos.DocumentStatusRequest;
import com.sudagoarth.sudanyallapay.Documents.Entities.Document;
import com.sudagoarth.sudanyallapay.Documents.Entities.DocumentRequirement;
import com.sudagoarth.sudanyallapay.Documents.Interfaces.DocumentInterface;
import com.sudagoarth.sudanyallapay.Documents.Repositories.DocumentRepository;
import com.sudagoarth.sudanyallapay.Documents.Repositories.DocumentRequirementRepository;
import com.sudagoarth.sudanyallapay.Enums.DocumentStatus;
import com.sudagoarth.sudanyallapay.Enums.EntityType;
import com.sudagoarth.sudanyallapay.exceptions.NotFoundException;

@Service
public class DocumentService implements DocumentInterface {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private EntityValidationService entityValidationService;

    @Autowired
    private DocumentStorageService fileStorageService;

    @Autowired
    private DocumentRequirementRepository documentRequirementRepository;

    @Override
    public Page<DocumentResponse> getDocuments(EntityType entityType, Long referenceId, Pageable pageable) {
        Page<Document> documents = documentRepository.findByEntityTypeAndReferenceId(entityType, referenceId, pageable);
        return documents.map(DocumentResponse::fromDocument);
    }

    @Override
    public Page<DocumentRequirementResponse> getDocumentRequirements(EntityType entityType, Pageable pageable) {
        Page<DocumentRequirement> documentRequirements = documentRequirementRepository.findByEntityType(entityType,
                pageable);
        return documentRequirements.map(DocumentRequirementResponse::fromDocumentRequirement);
    }

    @Override
    public DocumentResponse getDocument(Long id) throws NotFoundException {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Document not found"));
        return DocumentResponse.fromDocument(document);
    }

    @Override
    public DocumentResponse createDocument(DocumentRequirementRequest request) {
        if (request.getBase64Document() == null || request.getBase64Document().isEmpty()) {
            throw new IllegalArgumentException("Document content is required.");
        }

        entityValidationService.validateReferenceEntity(request.getEntityType(), request.getReferenceId());

        String documentUrl = fileStorageService.saveFile(request.getBase64Document());

        Document document = new Document();
        document.setReferenceId(request.getReferenceId());
        document.setEntityType(request.getEntityType());
        document.setDocumentName(request.getDocumentName());
        document.setDocumentUrl(documentUrl);
        document.setStatus(DocumentStatus.PENDING);
        document.setUploadedAt(LocalDateTime.now());

        documentRepository.save(document);

        return DocumentResponse.fromDocument(document);
    }

    @Override
    public DocumentResponse updateDocument(Long id, DocumentRequirementRequest request) throws NotFoundException {
        // Retrieve document from DB
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Document not found"));

        // Validate Base64 content
        if (request.getBase64Document() == null || request.getBase64Document().isEmpty()) {
            throw new IllegalArgumentException("Document content is required.");
        }

        // Validate entity reference
        entityValidationService.validateReferenceEntity(request.getEntityType(), request.getReferenceId());

        // Delete the existing file if it exists
        if (document.getDocumentUrl() != null) {
            fileStorageService.deleteFile(document.getDocumentUrl());
        }

        // Save new file and get URL
        String documentUrl = fileStorageService.saveFile(request.getBase64Document());

        // Update document metadata
        document.setReferenceId(request.getReferenceId());
        document.setEntityType(request.getEntityType());
        document.setDocumentName(request.getDocumentName());
        document.setDocumentUrl(documentUrl);
        document.setStatus(DocumentStatus.PENDING);
        document.setUploadedAt(LocalDateTime.now());

        // Save updated document in DB
        documentRepository.save(document);

        return DocumentResponse.fromDocument(document);
    }

    @Override
    public void deleteDocument(Long id) throws NotFoundException {
        // Retrieve document from database
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Document not found"));
    
        // Delete file using FileStorageService
        if (document.getDocumentUrl() != null) {
            fileStorageService.deleteFile(document.getDocumentUrl());
        }
    
        // Delete document record from database
        documentRepository.deleteById(id);
    
    }
    
    @Override
    public DocumentResponse statusDocument(Long id, DocumentStatusRequest documentStatusRequest)
            throws NotFoundException {

        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Document not found"));

        document.setStatus(documentStatusRequest.getStatus());
        documentRepository.save(document);

        return DocumentResponse.fromDocument(document);
    }

}
