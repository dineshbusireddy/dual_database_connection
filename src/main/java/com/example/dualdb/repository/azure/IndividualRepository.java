package com.example.dualdb.repository.azure;

import com.example.dualdb.model.azure.Individual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualRepository extends JpaRepository<Individual, String> {}