package com.sudagoarth.sudanyallapay.Documents.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.sudagoarth.sudanyallapay.Enums.EntityType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "document_requirements", indexes = {
    @Index(name = "index_document_requirements_entity_type", columnList = "entity_type")
})
public class DocumentRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private EntityType entityType;
    private String documentName;
    private Boolean isRequired;
    private LocalDateTime createdAt;
}