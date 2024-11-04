package com.Test.jpa.testJpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ContadorFactura {
    @Id
    private Long id = 1L; // Este ID será único y siempre será 1

    private Long contador;
}