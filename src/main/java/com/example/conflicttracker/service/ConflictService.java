package com.example.conflicttracker.service;

import com.example.conflicttracker.dto.ConflictDTO;
import com.example.conflicttracker.dto.CreateConflictDTO;
import com.example.conflicttracker.dto.UpdateConflictDTO;
import com.example.conflicttracker.model.*;
import com.example.conflicttracker.repository.ConflictRepository;
import com.example.conflicttracker.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConflictService {

    @Autowired
    private ConflictRepository conflictRepository;

    @Autowired
    private CountryRepository countryRepository;

    public List<ConflictDTO> findAll() {
        return conflictRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ConflictDTO> findByStatus(ConflictStatus status) {
        return conflictRepository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ConflictDTO> findById(Long id) {
        return conflictRepository.findById(id)
                .map(this::convertToDTO);
    }

    public ConflictDTO create(CreateConflictDTO createConflictDTO) {
        Conflict conflict = new Conflict();
        conflict.setName(createConflictDTO.getName());
        conflict.setStartDate(createConflictDTO.getStartDate());
        conflict.setStatus(createConflictDTO.getStatus());
        conflict.setDescription(createConflictDTO.getDescription());

        // Add countries - CORREGIDO
        if (createConflictDTO.getCountryCodes() != null && !createConflictDTO.getCountryCodes().isEmpty()) {
            List<Country> countries = countryRepository.findByCodeIn(createConflictDTO.getCountryCodes());
            conflict.setCountries(new HashSet<>(countries));
        }

        Conflict savedConflict = conflictRepository.save(conflict);
        return convertToDTO(savedConflict);
    }

    public ConflictDTO update(Long id, UpdateConflictDTO updateConflictDTO) {
        Conflict conflict = conflictRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conflict not found with id: " + id));

        conflict.setName(updateConflictDTO.getName());
        conflict.setStartDate(updateConflictDTO.getStartDate());
        conflict.setStatus(updateConflictDTO.getStatus());
        conflict.setDescription(updateConflictDTO.getDescription());

        if (updateConflictDTO.getCountryCodes() != null) {
            List<Country> countries = countryRepository.findByCodeIn(updateConflictDTO.getCountryCodes());
            conflict.setCountries(new HashSet<>(countries));
        }

        Conflict updatedConflict = conflictRepository.save(conflict);
        return convertToDTO(updatedConflict);
    }

    public void deleteById(Long id) {
        if (!conflictRepository.existsById(id)) {
            throw new RuntimeException("Conflict not found with id: " + id);
        }
        conflictRepository.deleteById(id);
    }

    public List<ConflictDTO> findByCountryCode(String countryCode) {
        return conflictRepository.findByCountryCode(countryCode).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ConflictDTO convertToDTO(Conflict conflict) {
        ConflictDTO dto = new ConflictDTO();
        dto.setId(conflict.getId());
        dto.setName(conflict.getName());
        dto.setStartDate(conflict.getStartDate());
        dto.setStatus(conflict.getStatus());
        dto.setDescription(conflict.getDescription());

        // Convert countries to codes - AHORA USANDO LIST
        if (conflict.getCountries() != null) {
            dto.setCountryCodes(conflict.getCountries().stream()
                    .map(Country::getCode)
                    .collect(Collectors.toList()));  // Cambiado a toList()
        } else {
            dto.setCountryCodes(new ArrayList<>());
        }

        // Convert factions to names - AHORA USANDO LIST
        if (conflict.getFactions() != null) {
            dto.setFactionNames(conflict.getFactions().stream()
                    .map(Faction::getName)
                    .collect(Collectors.toList()));  // Cambiado a toList()
        } else {
            dto.setFactionNames(new ArrayList<>());
        }

        return dto;
    }
}