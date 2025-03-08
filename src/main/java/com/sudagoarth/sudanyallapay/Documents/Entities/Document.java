
package com.sudagoarth.sudanyallapay.Documents.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.sudagoarth.sudanyallapay.Enums.DocumentStatus;
import com.sudagoarth.sudanyallapay.Enums.EntityType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long referenceId;
    
    @Enumerated(EnumType.STRING)
    private EntityType entityType;
    private String documentName;
    private String documentUrl;
    
    @Enumerated(EnumType.STRING)
    private DocumentStatus status;
    private LocalDateTime uploadedAt;
}