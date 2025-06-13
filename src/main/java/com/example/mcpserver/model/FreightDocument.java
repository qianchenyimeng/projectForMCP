package com.example.mcpserver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class FreightDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentNumber;

    private LocalDateTime estimatedArrivalTime;

    // Constructors
    public FreightDocument() {
    }

    public FreightDocument(String documentNumber, LocalDateTime estimatedArrivalTime) {
        this.documentNumber = documentNumber;
        this.estimatedArrivalTime = estimatedArrivalTime;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public LocalDateTime getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    public void setEstimatedArrivalTime(LocalDateTime estimatedArrivalTime) {
        this.estimatedArrivalTime = estimatedArrivalTime;
    }

    @Override
    public String toString() {
        return "FreightDocument{" +
                "id=" + id +
                ", documentNumber='" + documentNumber + '\'' +
                ", estimatedArrivalTime=" + estimatedArrivalTime +
                '}';
    }
}
