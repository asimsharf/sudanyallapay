package com.sudagoarth.sudanyallapay.Documents.Controllers;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sudagoarth.sudanyallapay.Documents.Dtos.DocumentRequirementRequest;
import com.sudagoarth.sudanyallapay.Documents.Dtos.DocumentRequirementResponse;
import com.sudagoarth.sudanyallapay.Documents.Dtos.DocumentResponse;
import com.sudagoarth.sudanyallapay.Documents.Dtos.DocumentStatusRequest;
import com.sudagoarth.sudanyallapay.Documents.Interfaces.DocumentInterface;
import com.sudagoarth.sudanyallapay.Enums.EntityType;
import com.sudagoarth.sudanyallapay.Users.Controllers.UserController;
import com.sudagoarth.sudanyallapay.utils.ApiResponse;
import com.sudagoarth.sudanyallapay.utils.LocaledData;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {

        @Autowired
        private DocumentInterface documentInterface;

        private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getName());

        @GetMapping
        public ResponseEntity<ApiResponse> getDocuments(@Valid @RequestParam(required = true) EntityType entityType,
                        @RequestParam(required = true) Long referenceId) {

                LOGGER.info("Getting document by ID: {}", referenceId);

                List<DocumentResponse> documentResponses = documentInterface
                                .getDocuments(entityType, referenceId);

                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success(new LocaledData(
                                                "Document retrieved successfully", "تم استرجاع المستند بنجاح"),
                                                HttpStatus.OK.value(),
                                                documentResponses));

        }

        @GetMapping("/requirements")
        public ResponseEntity<ApiResponse> getDocumentRequirements(
                        @Valid @RequestParam(required = true) EntityType entityType) {

                LOGGER.info("Getting document requirements by entity type: {}", entityType);

                List<DocumentRequirementResponse> documentRequirementResponses = documentInterface
                                .getDocumentRequirements(entityType);

                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success(new LocaledData(
                                                "Document requirements retrieved successfully",
                                                "تم استرجاع متطلبات المستند بنجاح"),
                                                HttpStatus.OK.value(),
                                                documentRequirementResponses));
        }

        @GetMapping("/document")
        public ResponseEntity<ApiResponse> getDocument( @RequestParam Long documentId) {
                LOGGER.info("Getting document by ID: {}", documentId);

                DocumentResponse documentResponse = documentInterface.getDocument(documentId);

                return ResponseEntity.status(HttpStatus.OK)
                                .body(ApiResponse.success(new LocaledData(
                                                "Document retrieved successfully", "تم استرجاع المستند بنجاح"),
                                                HttpStatus.OK.value(),
                                                documentResponse));
        }

        @PostMapping("/upload")
        public ResponseEntity<ApiResponse> uploadDocument(
                        @Valid @RequestBody DocumentRequirementRequest documentRequirementRequest) {
                LOGGER.info("Uploading document: {}", documentRequirementRequest);

                DocumentResponse documentResponse = documentInterface
                                .createDocument(documentRequirementRequest);

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(ApiResponse.success(new LocaledData(
                                                "Document uploaded successfully", "تم تحميل المستند بنجاح"),
                                                HttpStatus.CREATED.value(),
                                                documentResponse));
        }

        @PutMapping("/{documentId}/update")
        public ResponseEntity<ApiResponse> updateDocument(@RequestParam Long documentId,
                        @RequestBody DocumentRequirementRequest documentRequirementRequest) {
                LOGGER.info("Updating document: {}", documentRequirementRequest);

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(ApiResponse.success(new LocaledData(
                                                "Document updated successfully", "تم تحديث المستند بنجاح"),
                                                HttpStatus.CREATED.value(),
                                                null));
        }

        @DeleteMapping("/{documentId}")
        public ResponseEntity<ApiResponse> deleteDocument(@RequestParam Long documentId) {
                LOGGER.info("Deleting document by ID: {}", documentId);

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(ApiResponse.success(new LocaledData(
                                                "Document deleted successfully", "تم حذف المستند بنجاح"),
                                                HttpStatus.CREATED.value(),
                                                null));
        }

        @PutMapping("/{documentId}/status")
        public ResponseEntity<ApiResponse> statusDocument(@RequestParam Long documentId,
                        @RequestBody DocumentStatusRequest documentStatusRequest) {
                LOGGER.info("Updating document status: {}", documentStatusRequest);

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(ApiResponse.success(new LocaledData(
                                                "Document status updated successfully", "تم تحديث حالة المستند بنجاح"),
                                                HttpStatus.CREATED.value(),
                                                null));
        }

        @GetMapping("/view/{fileName}")
        public ResponseEntity<Resource> viewDocument(@PathVariable String fileName) {
                Path filePath = Paths.get("uploads/").resolve(fileName);

                try {
                        Resource resource = new UrlResource(filePath.toUri());

                        if (!resource.exists() || !resource.isReadable()) {
                                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                        }

                        // Determine Content-Type
                        String contentType = determineContentType(fileName);

                        return ResponseEntity.ok()
                                        .contentType(MediaType.parseMediaType(contentType))
                                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                                        "inline; filename=\"" + fileName + "\"")
                                        .body(resource);

                } catch (MalformedURLException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                }
        }

        // Helper Method to Determine File Content-Type
        private String determineContentType(String fileName) {
                if (fileName.endsWith(".png"))
                        return "image/png";
                if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg"))
                        return "image/jpeg";
                if (fileName.endsWith(".gif"))
                        return "image/gif";
                if (fileName.endsWith(".pdf"))
                        return "application/pdf";
                return "application/octet-stream"; // Default for unknown types
        }

}
