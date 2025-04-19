package com.example.preg.handler;

import com.example.preg.dto.IndividualDto;

public interface IndividualHandler {
    IndividualDto getIndividual(String id);
    boolean deleteIndividual(String id);
    boolean updateIndividual(IndividualDto dto);
    void setNext(IndividualHandler next);
}