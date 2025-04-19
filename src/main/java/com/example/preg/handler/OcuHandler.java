package com.example.preg.handler;

import com.example.preg.dto.IndividualDto;
import com.example.preg.model.azure.Individual;
import com.example.preg.model.OcuEntity;
import com.example.preg.repository.OcuService;
import com.example.preg.repository.azure.IndividualRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OcuHandler implements IndividualHandler {
    private final OcuService ocuService;
    private final IndividualRepository individualRepository;
    private IndividualHandler next;

    @Override
    public IndividualDto getIndividual(String id) {
        Optional<OcuEntity> ocuEntity = ocuService.retrieveById(id);
        if (ocuEntity.isPresent()) {
            // Migrate to Azure
            Individual migrated = toIndividual(ocuEntity.get());
            individualRepository.save(migrated);
            asyncDelete(id);
            return toDto(migrated);
        }
        return null;
    }

    @Override
    public boolean deleteIndividual(String id) {
        if (ocuService.existsById(id)) {
            ocuService.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateIndividual(IndividualDto dto) {
        Optional<OcuEntity> existing = ocuService.retrieveById(dto.getId());
        if (existing.isPresent()) {
            Individual migrated = new Individual();
            migrated.setId(dto.getId());
            migrated.setName(dto.getName());
            migrated.setLocation(dto.getLocation());
            individualRepository.save(migrated);
            asyncDelete(dto.getId());
            return true;
        }
        return false;
    }

    @Override
    public void setNext(IndividualHandler next) {
        this.next = next;
    }

    @Async
    void asyncDelete(String id) {
        ocuService.deleteById(id);
    }

    private Individual toIndividual(OcuEntity hotel) {
        Individual i = new Individual();
        i.setId(hotel.getId());
        i.setName(hotel.getName());
        i.setLocation(hotel.getLocation());
        return i;
    }

    private IndividualDto toDto(Individual hotel) {
        IndividualDto dto = new IndividualDto();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setLocation(hotel.getLocation());
        return dto;
    }
}
