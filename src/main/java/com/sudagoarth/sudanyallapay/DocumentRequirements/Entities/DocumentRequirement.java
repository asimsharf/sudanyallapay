package com.sudagoarth.sudanyallapay.DocumentRequirements.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.sudagoarth.sudanyallapay.Enums.EntityType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "document_requirements")
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