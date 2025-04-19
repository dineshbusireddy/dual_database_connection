package com.example.dualdb.handler;

import com.example.dualdb.dto.IndividualDto;

public interface IndividualHandler {
    IndividualDto getIndividual(String id);
    boolean deleteIndividual(String id);
    boolean updateIndividual(IndividualDto dto);
    void setNext(IndividualHandler next);
}