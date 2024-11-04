package com.Test.jpa.testJpa;

import com.Test.jpa.testJpa.service.ClienteService;
import com.Test.jpa.testJpa.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductoService productoService;
    private final ClienteService clienteService;

    @Autowired
    public DataLoader(ProductoService productoService, ClienteService clienteService) {
        this.productoService = productoService;
        this.clienteService = clienteService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Cargar productos iniciales
        Producto producto1 = new Producto();
        producto1.setNombre("Producto 1");
        producto1.setPrecio(100.0);
        producto1.setStock(10);
        productoService.crearProducto(producto1);

        Producto producto2 = new Producto();
        producto2.setNombre("Producto 2");
        producto2.setPrecio(150.0);
        producto2.setStock(5);
        productoService.crearProducto(producto2);

        // Cargar clientes iniciales
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Cliente 1");
        cliente1.setEmail("cliente1@example.com");
        cliente1.setDireccion("Direccion 1");
        clienteService.crearCliente(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Cliente 2");
        cliente2.setEmail("cliente2@example.com");
        cliente2.setDireccion("Direccion 2");
        clienteService.crearCliente(cliente2);
    }
}