package com.Test.jpa.testJpa.dto;

import com.Test.jpa.testJpa.Producto;
import java.util.Date;
import java.util.List;

public class VentaResponseDTO {
    private Long id;
    private String numeroFactura;
    private Long clienteId;
    private Date fecha;
    private List<ProductoStockDTO> stockRestante;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<ProductoStockDTO> getStockRestante() {
        return stockRestante;
    }

    public void setStockRestante(List<ProductoStockDTO> stockRestante) {
        this.stockRestante = stockRestante;
    }
}