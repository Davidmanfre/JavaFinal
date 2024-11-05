package com.Test.jpa.testJpa.controller;

import com.Test.jpa.testJpa.dto.VentaDTO;
import com.Test.jpa.testJpa.dto.VentaResponseDTO;
import com.Test.jpa.testJpa.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    @Autowired
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    // Crear una venta
    @PostMapping
    public VentaResponseDTO crearVenta(@RequestBody VentaDTO ventaDTO) {
        return ventaService.crearVenta(ventaDTO);
    }
}