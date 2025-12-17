package com.example.conflicttracker.controller;

import com.example.conflicttracker.dto.ConflictDTO;
import com.example.conflicttracker.dto.CreateConflictDTO;
import com.example.conflicttracker.dto.UpdateConflictDTO;
import com.example.conflicttracker.model.ConflictStatus;
import com.example.conflicttracker.service.ConflictService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/conflicts")
public class ConflictController {

    @Autowired
    private ConflictService conflictService;

    @GetMapping
    public List<ConflictDTO> getAllConflicts(@RequestParam(required = false) ConflictStatus status) {
        if (status != null) {
            return conflictService.findByStatus(status);
        }
        return conflictService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConflictDTO> getConflictById(@PathVariable Long id) {
        Optional<ConflictDTO> conflict = conflictService.findById(id);
        return conflict.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ConflictDTO createConflict(@Valid @RequestBody CreateConflictDTO createConflictDTO) {
        return conflictService.create(createConflictDTO);
    }

    @GetMapping("/countries/{countryCode}/conflicts")
    public List<ConflictDTO> getConflictsByCountryCode(@PathVariable String countryCode) {
        return conflictService.findByCountryCode(countryCode);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConflictDTO> updateConflict(@PathVariable Long id,
                                    @Valid @RequestBody UpdateConflictDTO updateConflictDTO) {
        try {
            ConflictDTO updatedConflict = conflictService.update(id, updateConflictDTO);
            return ResponseEntity.ok(updatedConflict);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
     }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConflict(@PathVariable Long id) {
        try {
           conflictService.deleteById(id);
            return ResponseEntity.noContent().build();
      } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
