package com.example.conflicttracker.controller;

import com.example.conflicttracker.dto.CreateFactionDTO;
import com.example.conflicttracker.dto.FactionDTO;
import com.example.conflicttracker.dto.UpdateFactionDTO;
import com.example.conflicttracker.service.FactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/factions")
public class FactionController {

    @Autowired
    private FactionService factionService;

    @GetMapping
    public List<FactionDTO> getAllFactions(@RequestParam(required = false) Long conflictId) {
        if (conflictId != null) {
            return factionService.findByConflictId(conflictId);
        }
        return factionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactionDTO> getFactionById(@PathVariable Long id) {
        Optional<FactionDTO> faction = factionService.findById(id);
        return faction.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FactionDTO> createFaction(@Valid @RequestBody CreateFactionDTO createFactionDTO) {
        try {
            FactionDTO faction = factionService.create(createFactionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(faction);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactionDTO> updateFaction(@PathVariable Long id,
                                                    @Valid @RequestBody UpdateFactionDTO updateFactionDTO) {
        try {
            FactionDTO updatedFaction = factionService.update(id, updateFactionDTO);
            return ResponseEntity.ok(updatedFaction);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaction(@PathVariable Long id) {
        factionService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}