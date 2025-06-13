package com.example.mcpserver.controller;

import com.example.mcpserver.service.FreightService;
import com.example.mcpserver.service.DocumentNotFoundException; // Ensure this is available
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;


@WebMvcTest(FreightController.class)
public class FreightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FreightService freightService;

    private final String testDocNumber = "DOC123";
    private final LocalDateTime testEta = LocalDateTime.of(2024, 3, 15, 10, 30, 0);
    // Define a formatter that matches the default LocalDateTime serialization format
    private final DateTimeFormatter isoLocalDateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;


    @Test
    void whenGetEstimatedArrivalTime_givenValidDocumentNumber_thenReturnEta() throws Exception {
        given(freightService.getEstimatedArrivalTime(testDocNumber)).willReturn(testEta);

        mockMvc.perform(get("/api/freight/{documentNumber}/eta", testDocNumber)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                // Compare the JSON string representation
                .andExpect(jsonPath("$", is(testEta.format(isoLocalDateTimeFormatter))));
    }

    @Test
    void whenGetEstimatedArrivalTime_givenInvalidDocumentNumber_thenReturnNotFound() throws Exception {
        String invalidDocNumber = "INVALIDDOC";
        given(freightService.getEstimatedArrivalTime(invalidDocNumber))
                .willThrow(new DocumentNotFoundException("Freight document with number '" + invalidDocNumber + "' not found."));

        mockMvc.perform(get("/api/freight/{documentNumber}/eta", invalidDocNumber)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                // Check the structure of the ApiError response from RestExceptionHandler
                .andExpect(jsonPath("$.status", is("NOT_FOUND")))
                .andExpect(jsonPath("$.message", is("Freight document with number '" + invalidDocNumber + "' not found.")))
                .andExpect(jsonPath("$.error", is("Document not found")));
    }
}
