package com.example.conflicttracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class UpdateEventDTO {
    @NotNull(message = "Event date is mandatory")
    private LocalDate eventDate;

    @NotBlank(message = "Location is mandatory")
    private String location;

    private String description;

    @NotNull(message = "Conflict ID is mandatory")
    private Long conflictId;

    // Constructor vacío
    public UpdateEventDTO() {}

    // Constructor con parámetros
    public UpdateEventDTO(LocalDate eventDate, String location, String description, Long conflictId) {
        this.eventDate = eventDate;
        this.location = location;
        this.description = description;
        this.conflictId = conflictId;
    }

    // Getters y Setters
    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getConflictId() { return conflictId; }
    public void setConflictId(Long conflictId) { this.conflictId = conflictId; }
}