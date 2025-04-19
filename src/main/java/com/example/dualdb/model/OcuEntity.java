package com.example.dualdb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "ocu_individual")
public class OcuEntity {
    @Id
    private String id;
    private String name;
    private String location;
}