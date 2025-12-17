package com.example.conflicttracker.service;

import com.example.conflicttracker.dto.FactionDTO;
import com.example.conflicttracker.dto.CreateFactionDTO;
import com.example.conflicttracker.dto.UpdateFactionDTO;
import com.example.conflicttracker.model.*;
import com.example.conflicttracker.repository.FactionRepository;
import com.example.conflicttracker.repository.ConflictRepository;
import com.example.conflicttracker.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FactionService {

    @Autowired
    private FactionRepository factionRepository;

    @Autowired
    private ConflictRepository conflictRepository;

    @Autowired
    private CountryRepository countryRepository;

    public List<FactionDTO> findAll() {
        return factionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<FactionDTO> findById(Long id) {
        return factionRepository.findById(id)
                .map(this::convertToDTO);
    }

    public List<FactionDTO> findByConflictId(Long conflictId) {
        return factionRepository.findByConflictId(conflictId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FactionDTO create(CreateFactionDTO createFactionDTO) {
        Faction faction = new Faction();
        faction.setName(createFactionDTO.getName());

        // Set conflict
        Conflict conflict = conflictRepository.findById(createFactionDTO.getConflictId())
                .orElseThrow(() -> new RuntimeException("Conflict not found with id: " + createFactionDTO.getConflictId()));
        faction.setConflict(conflict);

        // Set supporting countries
        if (createFactionDTO.getSupportingCountryCodes() != null && !createFactionDTO.getSupportingCountryCodes().isEmpty()) {
            List<Country> countries = countryRepository.findByCodeIn(createFactionDTO.getSupportingCountryCodes());
            faction.setSupportingCountries(new HashSet<>(countries));
        }

        Faction savedFaction = factionRepository.save(faction);
        return convertToDTO(savedFaction);
    }

    public FactionDTO update(Long id, UpdateFactionDTO updateFactionDTO) {
        Faction faction = factionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faction not found with id: " + id));

        faction.setName(updateFactionDTO.getName());

        // Update conflict if provided
        if (updateFactionDTO.getConflictId() != null) {
            Conflict conflict = conflictRepository.findById(updateFactionDTO.getConflictId())
                    .orElseThrow(() -> new RuntimeException("Conflict not found with id: " + updateFactionDTO.getConflictId()));
            faction.setConflict(conflict);
        }

        // Update supporting countries
        if (updateFactionDTO.getSupportingCountryCodes() != null) {
            List<Country> countries = countryRepository.findByCodeIn(updateFactionDTO.getSupportingCountryCodes());
            faction.setSupportingCountries(new HashSet<>(countries));
        }

        Faction updatedFaction = factionRepository.save(faction);
        return convertToDTO(updatedFaction);
    }

    public void deleteById(Long id) {
        if (!factionRepository.existsById(id)) {
            throw new RuntimeException("Faction not found with id: " + id);
        }
        factionRepository.deleteById(id);
    }

    private FactionDTO convertToDTO(Faction faction) {
        FactionDTO dto = new FactionDTO();
        dto.setId(faction.getId());
        dto.setName(faction.getName());

        if (faction.getConflict() != null) {
            dto.setConflictId(faction.getConflict().getId());
            dto.setConflictName(faction.getConflict().getName());
        }

        if (faction.getSupportingCountries() != null) {
            // Cambiar de Collectors.toSet() a Collectors.toList()
            dto.setSupportingCountryCodes(faction.getSupportingCountries().stream()
                    .map(Country::getCode)
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}