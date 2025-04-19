package com.example.preg.service;

import com.example.preg.dto.IndividualDto;
import com.example.preg.handler.AzureIndividualHandler;
import com.example.preg.handler.OcuHandler;
import com.example.preg.handler.IndividualHandler;
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