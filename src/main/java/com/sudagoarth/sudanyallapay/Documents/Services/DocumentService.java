package com.sudagoarth.sudanyallapay.Documents.Services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;

import java.util.UUID;

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
import com.sudagoarth.sudanyallapay.Users.Repositories.UserRepository;
import com.sudagoarth.sudanyallapay.exceptions.DuplicateException;
import com.sudagoarth.sudanyallapay.exceptions.NotFoundException;

@Service
public class DocumentService implements DocumentInterface {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentRequirementRepository documentRequirementRepository;

    @Autowired
    private UserRepository userRepository;

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
    public DocumentResponse createDocument(DocumentRequirementRequest request) throws DuplicateException {
        // Validate the request
        if (request.getBase64Document() == null || request.getBase64Document().isEmpty()) {
            throw new IllegalArgumentException("Document content is required");
        }

        // Validate that the referenced entity exists
        validateReferenceEntity(request.getEntityType(), request.getReferenceId());

        // Extract file extension (png, jpg, pdf, etc.)
        String base64Data = request.getBase64Document();
        String extension = getFileExtension(base64Data);

        // Decode Base64 Document
        String base64WithoutPrefix = base64Data.contains(",") ? base64Data.split(",")[1] : base64Data;
        byte[] decodedBytes = Base64.getDecoder().decode(base64WithoutPrefix);

        // Generate a random unique filename
        String uniqueFileName = UUID.randomUUID().toString() + "." + extension;

        // Save the decoded file locally (Modify for S3/cloud storage if needed)
        saveFile(decodedBytes, uniqueFileName);

        // Construct full URL for document access
        String documentUrl = "http://localhost:4000/api/v1/documents/view/" + uniqueFileName;

        // Save document metadata in the database
        Document document = new Document();

        document.setReferenceId(request.getReferenceId());

        document.setEntityType(request.getEntityType());
        document.setDocumentName(request.getDocumentName());
        document.setDocumentUrl(documentUrl); // Store full access URL
        document.setStatus(DocumentStatus.PENDING);
        document.setUploadedAt(LocalDateTime.now());

        documentRepository.save(document);

        return DocumentResponse.fromDocument(document);
    }

    private void validateReferenceEntity(EntityType entityType, Long referenceId) {
        switch (entityType) {
            case USER:
                if (!userRepository.existsById(referenceId)) {
                    throw new NotFoundException("User with ID " + referenceId + " not found.");
                }
                break;
            default:
                throw new IllegalArgumentException("Unsupported entity type: " + entityType);
        }
    }

    // Helper method to save a file locally
    private String saveFile(byte[] fileData, String fileName) {
        String directoryPath = "uploads/";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it doesn't exist
        }

        String filePath = directoryPath + fileName;
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(fileData);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file", e);
        }

        return filePath; // Return file path for DB storage
    }

    // Helper method to extract file extension from Base64 data
    private String getFileExtension(String base64Data) {
        if (base64Data.startsWith("data:image/png")) {
            return "png";
        } else if (base64Data.startsWith("data:image/jpeg") || base64Data.startsWith("data:image/jpg")) {
            return "jpg";
        } else if (base64Data.startsWith("data:image/gif")) {
            return "gif";
        } else if (base64Data.startsWith("data:application/pdf")) {
            return "pdf";
        } else {
            throw new IllegalArgumentException("Unsupported file type");
        }
    }

    @Override
    public DocumentResponse updateDocument(Long id, DocumentRequirementRequest documentRequirementRequest)
            throws NotFoundException {

        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Document not found"));

        // Validate the request
        if (documentRequirementRequest.getBase64Document() == null
                || documentRequirementRequest.getBase64Document().isEmpty()) {
            throw new IllegalArgumentException("Document content is required");
        }

        // Extract file extension (png, jpg, pdf, etc.)
        String base64Data = documentRequirementRequest.getBase64Document();
        String extension = getFileExtension(base64Data);

        // Decode Base64 Document
        String base64WithoutPrefix = base64Data.contains(",") ? base64Data.split(",")[1] : base64Data;
        byte[] decodedBytes = Base64.getDecoder().decode(base64WithoutPrefix);

        // **Delete the existing file from the uploads folder**
        if (document.getDocumentUrl() != null) {
            deleteExistingFile(document.getDocumentUrl());
        }

        // Generate a new random unique filename
        String uniqueFileName = UUID.randomUUID().toString() + "." + extension;

        // Define upload path
        String uploadDir = "uploads/"; // Make sure this folder exists
        File uploadPath = new File(uploadDir);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs(); // Create the directory if it doesn't exist
        }

        // Save the new file to uploads folder
        File newFile = new File(uploadDir + uniqueFileName);
        try (FileOutputStream fos = new FileOutputStream(newFile)) {
            fos.write(decodedBytes);
        } catch (IOException e) {
            throw new RuntimeException("Error saving file", e);
        }

        // Construct full URL for document access
        String documentUrl = "http://localhost:4000/api/v1/documents/view/" + uniqueFileName;

        // Update document metadata in the database
        document.setReferenceId(documentRequirementRequest.getReferenceId());
        document.setEntityType(documentRequirementRequest.getEntityType());
        document.setDocumentName(documentRequirementRequest.getDocumentName());
        document.setDocumentUrl(documentUrl); // Store full access URL
        document.setStatus(DocumentStatus.PENDING);
        document.setUploadedAt(LocalDateTime.now());

        documentRepository.save(document);

        return DocumentResponse.fromDocument(document);
    }

    private void deleteExistingFile(String documentUrl) {
        try {
            // Extract file name from URL
            String fileName = documentUrl.substring(documentUrl.lastIndexOf("/") + 1);
            File file = new File("uploads/" + fileName);

            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("✅ Old file deleted: " + fileName);
                } else {
                    System.err.println("⚠️ Failed to delete old file: " + fileName);
                }
            }
        } catch (Exception e) {
            System.err.println("⚠️ Error while deleting file: " + e.getMessage());
        }
    }

    @Override
    public void deleteDocument(Long id) throws NotFoundException {

        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Document not found"));

        // Delete the file from the local storage
        String documentUrl = document.getDocumentUrl();
        String fileName = documentUrl.substring(documentUrl.lastIndexOf("/") + 1);
        String filePath = "uploads/" + fileName;
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }

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
