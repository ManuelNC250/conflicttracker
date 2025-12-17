package com.example.conflicttracker.dto;

import com.example.conflicttracker.model.ConflictStatus;
import java.time.LocalDate;
import java.util.List;

public class ConflictDTO {
    private Long id;
    private String name;
    private LocalDate startDate;
    private ConflictStatus status;
    private String description;

    // Cambiar a List para consistencia
    private List<String> countryCodes;
    private List<String> factionNames;
    private List<EventDTO> events;

    // Constructors
    public ConflictDTO() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public ConflictStatus getStatus() { return status; }
    public void setStatus(ConflictStatus status) { this.status = status; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getCountryCodes() { return countryCodes; }
    public void setCountryCodes(List<String> countryCodes) { this.countryCodes = countryCodes; }

    public List<String> getFactionNames() { return factionNames; }
    public void setFactionNames(List<String> factionNames) { this.factionNames = factionNames; }

    public List<EventDTO> getEvents() { return events; }
    public void setEvents(List<EventDTO> events) { this.events = events; }
}