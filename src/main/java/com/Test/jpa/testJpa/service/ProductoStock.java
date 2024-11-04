package com.Test.jpa.testJpa.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductoStock {
    private Long id;
    private String nombre;
    private Integer stockRestante;
}