package com.sudagoarth.sudanyallapay.Documents.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudagoarth.sudanyallapay.Documents.Entities.DocumentRequirement;
import com.sudagoarth.sudanyallapay.Enums.EntityType;

@Repository
public interface DocumentRequirementRepository extends JpaRepository<DocumentRequirement, Long> {

    List<DocumentRequirement> findByEntityType(EntityType entityType);
    
}