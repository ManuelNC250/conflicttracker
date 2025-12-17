package com.example.conflicttracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class CreateFactionDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Conflict ID is mandatory")
    private Long conflictId;

    private List<String> supportingCountryCodes;

    // Constructors, Getters and Setters
    public CreateFactionDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getConflictId() {
        return conflictId;
    }

    public void setConflictId(Long conflictId) {
        this.conflictId = conflictId;
    }

    public List<String> getSupportingCountryCodes() {
        return supportingCountryCodes;
    }

    public void setSupportingCountryCodes(List<String> supportingCountryCodes) {
        this.supportingCountryCodes = supportingCountryCodes;
    }
}