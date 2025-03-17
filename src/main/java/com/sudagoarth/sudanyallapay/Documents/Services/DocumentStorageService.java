
package com.sudagoarth.sudanyallapay.Documents.Services;

import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Slf4j
@Service
public class DocumentStorageService {

    private static final String UPLOAD_DIR = "uploads/";

    public String saveFile(String base64Data) {
        try {
            byte[] decodedBytes = decodeBase64(base64Data);
            String extension = getFileExtension(decodedBytes);
            String uniqueFileName = UUID.randomUUID().toString() + "." + extension;

            Path directoryPath = Paths.get(UPLOAD_DIR);
            Files.createDirectories(directoryPath); // Ensure directory exists

            Path filePath = directoryPath.resolve(uniqueFileName);
            Files.write(filePath, decodedBytes);

            log.info("File saved successfully: {}", filePath);

            return getFileAccessUrl(uniqueFileName);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file", e);
        }
    }

    public void deleteFile(String documentUrl) {
        try {
            // Extract filename from URL
            String fileName = documentUrl.substring(documentUrl.lastIndexOf("/") + 1);
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
    
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                log.info("File deleted successfully: {}", fileName);
            } else {
                log.warn("File not found, skipping deletion: {}", fileName);
            }
        } catch (IOException e) {
            log.error("Error deleting file: {}", e.getMessage(), e);
        }
    }
    

    private byte[] decodeBase64(String base64Data) {
        String base64WithoutPrefix = base64Data.contains(",") ? base64Data.split(",")[1] : base64Data;
        return Base64.getDecoder().decode(base64WithoutPrefix);
    }

    private String getFileExtension(byte[] fileBytes) {
        Tika tika = new Tika();
        String mimeType = tika.detect(fileBytes);
        switch (mimeType) {
            case "image/png":
                return "png";
            case "image/jpeg":
                return "jpg";
            case "application/pdf":
                return "pdf";
            default:
                throw new IllegalArgumentException("Unsupported file type: " + mimeType);
        }
    }

    private String getFileAccessUrl(String fileName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/documents/view/")
                .path(fileName)
                .toUriString();
    }
}
