package com.example.dualdb.service;

import com.example.dualdb.dto.IndividualDto;
import com.example.dualdb.handler.AzureIndividualHandler;
import com.example.dualdb.handler.OcuHandler;
import com.example.dualdb.handler.IndividualHandler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndividualService {
    private final AzureIndividualHandler azureIndividualHandler;
    private final OcuHandler ocuHandler;
    private IndividualHandler chain;

    @PostConstruct
    public void initChain() {
        azureIndividualHandler.setNext(ocuHandler);
        chain = azureIndividualHandler;
    }

    public IndividualDto retrieveIndividual(String id) {
        return chain.getIndividual(id);
    }

    public boolean deleteIndividual(String id) {
        return chain.deleteIndividual(id);
    }

    public boolean updateIndividual(IndividualDto dto) {
        return chain.updateIndividual(dto);
    }
}