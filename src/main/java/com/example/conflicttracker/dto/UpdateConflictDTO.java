package com.example.conflicttracker.dto;

import com.example.conflicttracker.model.ConflictStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class UpdateConflictDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Start date is mandatory")
    private LocalDate startDate;

    @NotNull(message = "Status is mandatory")
    private ConflictStatus status;

    private String description;
    private List<String> countryCodes;

    // Constructors, Getters and Setters
    public UpdateConflictDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public ConflictStatus getStatus() {
        return status;
    }

    public void setStatus(ConflictStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCountryCodes() {
        return countryCodes;
    }

    public void setCountryCodes(List<String> countryCodes) {
        this.countryCodes = countryCodes;
    }
}