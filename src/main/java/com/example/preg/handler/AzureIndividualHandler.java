package com.example.preg.handler;

import com.example.preg.dto.IndividualDto;
import com.example.preg.model.azure.Individual;
import com.example.preg.repository.azure.IndividualRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AzureIndividualHandler implements IndividualHandler {
    private final IndividualRepository individualRepository;
    private IndividualHandler next;

    @Override
    public IndividualDto getIndividual(String id) {
        Optional<Individual> individual = individualRepository.findById(id);
        if (individual.isPresent()) {
            return toDto(individual.get());
        } else if (next != null) {
            return next.getIndividual(id);
        }
        return null;
    }

    @Override
    public boolean deleteIndividual(String id) {
        if (individualRepository.existsById(id)) {
            individualRepository.deleteById(id);
            return true;
        } else if (next != null) {
            return next.deleteIndividual(id);
        }
        return false;
    }

    @Override
    public boolean updateIndividual(IndividualDto dto) {
        Optional<Individual> existing = individualRepository.findById(dto.getId());
        if (existing.isPresent()) {
            Individual updated = toEntity(dto);
            individualRepository.save(updated);
            return true;
        } else if (next != null) {
            return next.updateIndividual(dto);
        }
        return false;
    }

    @Override
    public void setNext(IndividualHandler next) {
        this.next = next;
    }

    private IndividualDto toDto(Individual hotel) {
        IndividualDto dto = new IndividualDto();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setLocation(hotel.getLocation());
        return dto;
    }

    private Individual toEntity(IndividualDto dto) {
        Individual individual = new Individual();
        individual.setId(dto.getId());
        individual.setName(dto.getName());
        individual.setLocation(dto.getLocation());
        return individual;
    }
}