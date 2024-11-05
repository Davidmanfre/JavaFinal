package com.Test.jpa.testJpa.dto;

public class ProductoStockDTO {
    private Long id;
    private String nombre;
    private Integer stock;

    public ProductoStockDTO(Long id, String nombre, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
