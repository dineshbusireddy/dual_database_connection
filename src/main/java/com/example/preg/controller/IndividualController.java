package com.example.preg.controller;

import com.example.preg.dto.IndividualDto;
import com.example.preg.service.IndividualService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/individual")
@RequiredArgsConstructor
public class IndividualController {
    private final IndividualService individualService;

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveIndividual(@PathVariable String id) {
        IndividualDto dto = individualService.retrieveIndividual(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIndividual(@PathVariable String id) {
        return individualService.deleteIndividual(id) ?
            ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<?> updateIndividual(@RequestBody IndividualDto dto) {
        return individualService.updateIndividual(dto) ?
            ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}