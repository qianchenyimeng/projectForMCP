package com.example.mcpserver.service;

import com.example.mcpserver.model.FreightDocument;
import com.example.mcpserver.repository.FreightDocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FreightServiceTest {

    @Mock
    private FreightDocumentRepository freightDocumentRepository;

    @InjectMocks
    private FreightService freightService;

    private FreightDocument testDocument;
    private final String testDocNumber = "DOC123";
    private final LocalDateTime testEta = LocalDateTime.now().plusDays(5);

    @BeforeEach
    void setUp() {
        testDocument = new FreightDocument(testDocNumber, testEta);
        testDocument.setId(1L);
    }

    @Test
    void whenGetEstimatedArrivalTime_givenValidDocumentNumber_thenReturnEta() {
        when(freightDocumentRepository.findByDocumentNumber(testDocNumber)).thenReturn(Optional.of(testDocument));

        LocalDateTime eta = freightService.getEstimatedArrivalTime(testDocNumber);

        assertNotNull(eta);
        assertEquals(testEta, eta);
        verify(freightDocumentRepository, times(1)).findByDocumentNumber(testDocNumber);
    }

    @Test
    void whenGetEstimatedArrivalTime_givenInvalidDocumentNumber_thenThrowDocumentNotFoundException() {
        String invalidDocNumber = "INVALIDDOC";
        when(freightDocumentRepository.findByDocumentNumber(invalidDocNumber)).thenReturn(Optional.empty());

        DocumentNotFoundException exception = assertThrows(DocumentNotFoundException.class, () -> {
            freightService.getEstimatedArrivalTime(invalidDocNumber);
        });

        assertEquals("Freight document with number '" + invalidDocNumber + "' not found.", exception.getMessage());
        verify(freightDocumentRepository, times(1)).findByDocumentNumber(invalidDocNumber);
    }

    @Test
    void whenSaveDocument_thenSaveAndReturnDocument() {
        when(freightDocumentRepository.save(any(FreightDocument.class))).thenReturn(testDocument);

        FreightDocument savedDocument = freightService.saveDocument(new FreightDocument("NEWDOC", LocalDateTime.now()));

        assertNotNull(savedDocument);
        assertEquals(testDocNumber, savedDocument.getDocumentNumber()); // Assuming save returns the 'testDocument' mock
        verify(freightDocumentRepository, times(1)).save(any(FreightDocument.class));
    }
}
