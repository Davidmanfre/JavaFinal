package com.Test.jpa.testJpa.service;

import com.Test.jpa.testJpa.Venta;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class VentaResponse {
    private Venta venta;
    private List<ProductoStock> stockRestante;
}