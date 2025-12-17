package com.example.conflicttracker.service;

import com.example.conflicttracker.dto.CountryDTO;
import com.example.conflicttracker.model.Country;
import com.example.conflicttracker.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<CountryDTO> findAll() {
        return countryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CountryDTO> findById(Long id) {
        return countryRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Optional<CountryDTO> findByCode(String code) {
        return countryRepository.findByCode(code)
                .map(this::convertToDTO);
    }

    public CountryDTO create(Country country) {
        Country savedCountry = countryRepository.save(country);
        return convertToDTO(savedCountry);
    }

    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }

    private CountryDTO convertToDTO(Country country) {
        CountryDTO dto = new CountryDTO();
        dto.setId(country.getId());
        dto.setName(country.getName());
        dto.setCode(country.getCode());
        return dto;
    }
}