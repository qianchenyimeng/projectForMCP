package com.example.mcpserver.service;

import com.example.mcpserver.model.FreightDocument;
import com.example.mcpserver.repository.FreightDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

// Define a custom exception for document not found
class DocumentNotFoundException extends RuntimeException {
    public DocumentNotFoundException(String message) {
        super(message);
    }
}

@Service
public class FreightService {

    private final FreightDocumentRepository freightDocumentRepository;

    @Autowired
    public FreightService(FreightDocumentRepository freightDocumentRepository) {
        this.freightDocumentRepository = freightDocumentRepository;
    }

    public LocalDateTime getEstimatedArrivalTime(String documentNumber) {
        Optional<FreightDocument> documentOptional = freightDocumentRepository.findByDocumentNumber(documentNumber);

        if (documentOptional.isPresent()) {
            return documentOptional.get().getEstimatedArrivalTime();
        } else {
            // Later, this can be handled by a @ControllerAdvice for a 404 response
            throw new DocumentNotFoundException("Freight document with number '" + documentNumber + "' not found.");
        }
    }

    // Optional: Method to add a new document (useful for testing)
    public FreightDocument saveDocument(FreightDocument document) {
        return freightDocumentRepository.save(document);
    }
}
