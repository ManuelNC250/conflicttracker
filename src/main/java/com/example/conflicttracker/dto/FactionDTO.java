package com.example.conflicttracker.dto;

import java.util.List;

public class FactionDTO {
    private Long id;
    private String name;
    private Long conflictId;
    private String conflictName;
    private List<String> supportingCountryCodes;  // Cambiar de Set a List

    // Constructor vacío
    public FactionDTO() {}

    // Constructor con parámetros
    public FactionDTO(Long id, String name, Long conflictId, String conflictName, List<String> supportingCountryCodes) {
        this.id = id;
        this.name = name;
        this.conflictId = conflictId;
        this.conflictName = conflictName;
        this.supportingCountryCodes = supportingCountryCodes;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getConflictId() { return conflictId; }
    public void setConflictId(Long conflictId) { this.conflictId = conflictId; }

    public String getConflictName() { return conflictName; }
    public void setConflictName(String conflictName) { this.conflictName = conflictName; }

    public List<String> getSupportingCountryCodes() { return supportingCountryCodes; }
    public void setSupportingCountryCodes(List<String> supportingCountryCodes) {
        this.supportingCountryCodes = supportingCountryCodes;
    }
}