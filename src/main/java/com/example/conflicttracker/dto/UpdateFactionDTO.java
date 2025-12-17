package com.example.conflicttracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class UpdateFactionDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Conflict ID is mandatory")
    private Long conflictId;

    private List<String> supportingCountryCodes;

    // Constructor vacío
    public UpdateFactionDTO() {}

    // Constructor con parámetros
    public UpdateFactionDTO(String name, Long conflictId, List<String> supportingCountryCodes) {
        this.name = name;
        this.conflictId = conflictId;
        this.supportingCountryCodes = supportingCountryCodes;
    }

    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getConflictId() { return conflictId; }
    public void setConflictId(Long conflictId) { this.conflictId = conflictId; }

    public List<String> getSupportingCountryCodes() { return supportingCountryCodes; }
    public void setSupportingCountryCodes(List<String> supportingCountryCodes) {
        this.supportingCountryCodes = supportingCountryCodes; }
}