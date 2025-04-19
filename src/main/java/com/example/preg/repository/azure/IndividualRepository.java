package com.example.preg.repository.azure;

import com.example.preg.model.azure.Individual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualRepository extends JpaRepository<Individual, String> {}