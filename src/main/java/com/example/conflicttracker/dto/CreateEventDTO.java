package com.example.conflicttracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class CreateEventDTO {
    @NotNull(message = "Event date is mandatory")
    private LocalDate eventDate;

    @NotBlank(message = "Location is mandatory")
    private String location;

    private String description;

    @NotNull(message = "Conflict ID is mandatory")
    private Long conflictId;

    // Constructors, Getters and Setters
    public CreateEventDTO() {
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getConflictId() {
        return conflictId;
    }

    public void setConflictId(Long conflictId) {
        this.conflictId = conflictId;
    }
}