package com.example.conflicttracker.service;

import com.example.conflicttracker.dto.EventDTO;
import com.example.conflicttracker.dto.CreateEventDTO;
import com.example.conflicttracker.dto.UpdateEventDTO;
import com.example.conflicttracker.model.*;
import com.example.conflicttracker.repository.EventRepository;
import com.example.conflicttracker.repository.ConflictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ConflictRepository conflictRepository;

    public List<EventDTO> findAll() {
        return eventRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<EventDTO> findById(Long id) {
        return eventRepository.findById(id)
                .map(this::convertToDTO);
    }

    public List<EventDTO> findByConflictId(Long conflictId) {
        return eventRepository.findByConflictId(conflictId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EventDTO create(CreateEventDTO createEventDTO) {
        Event event = new Event();
        event.setEventDate(createEventDTO.getEventDate());
        event.setLocation(createEventDTO.getLocation());
        event.setDescription(createEventDTO.getDescription());

        // Set conflict
        Conflict conflict = conflictRepository.findById(createEventDTO.getConflictId())
                .orElseThrow(() -> new RuntimeException("Conflict not found with id: " + createEventDTO.getConflictId()));
        event.setConflict(conflict);

        Event savedEvent = eventRepository.save(event);
        return convertToDTO(savedEvent);
    }

    public EventDTO update(Long id, UpdateEventDTO updateEventDTO) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));

        event.setEventDate(updateEventDTO.getEventDate());
        event.setLocation(updateEventDTO.getLocation());
        event.setDescription(updateEventDTO.getDescription());

        // Update conflict if provided
        if (updateEventDTO.getConflictId() != null) {
            Conflict conflict = conflictRepository.findById(updateEventDTO.getConflictId())
                    .orElseThrow(() -> new RuntimeException("Conflict not found with id: " + updateEventDTO.getConflictId()));
            event.setConflict(conflict);
        }

        Event updatedEvent = eventRepository.save(event);
        return convertToDTO(updatedEvent);
    }

    public void deleteById(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);
    }

    private EventDTO convertToDTO(Event event) {
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setEventDate(event.getEventDate());
        dto.setLocation(event.getLocation());
        dto.setDescription(event.getDescription());

        if (event.getConflict() != null) {
            dto.setConflictId(event.getConflict().getId());
            dto.setConflictName(event.getConflict().getName());
        }

        return dto;
    }
}