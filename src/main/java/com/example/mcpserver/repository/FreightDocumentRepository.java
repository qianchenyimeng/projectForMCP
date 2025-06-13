package com.example.mcpserver.repository;

import com.example.mcpserver.model.FreightDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FreightDocumentRepository extends JpaRepository<FreightDocument, Long> {

    Optional<FreightDocument> findByDocumentNumber(String documentNumber);

}
