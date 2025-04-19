package com.example.dualdb.model.azure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "individual")
public class Individual {
    @Id
    private String id;
    private String name;
    private String location;
}