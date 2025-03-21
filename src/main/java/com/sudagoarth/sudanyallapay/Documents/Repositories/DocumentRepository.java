package com.sudagoarth.sudanyallapay.Documents.Repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sudagoarth.sudanyallapay.Documents.Entities.Document;
import com.sudagoarth.sudanyallapay.Documents.Entities.DocumentRequirement;
import com.sudagoarth.sudanyallapay.Enums.EntityType;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    Page<DocumentRequirement> findByEntityType(EntityType entityType, Pageable pageable);

    Page<Document> findByEntityTypeAndReferenceId(EntityType entityType, Long referenceId, Pageable pageable);


    @Query("SELECT d FROM Document d WHERE d.entityType = 'USER' AND d.referenceId = :userId")
    List<Document> findDocumentsByUserId(@Param("userId") Long userId);
    
} 