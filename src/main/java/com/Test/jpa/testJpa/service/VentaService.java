package com.Test.jpa.testJpa.service;

import com.Test.jpa.testJpa.Producto;
import com.Test.jpa.testJpa.Venta;
import com.Test.jpa.testJpa.model.ContadorFactura;
import com.Test.jpa.testJpa.repository.ContadorFacturaRepository;
import com.Test.jpa.testJpa.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoService productoService;
    private final ContadorFacturaRepository contadorFacturaRepository;


    @Autowired
    public VentaService(VentaRepository ventaRepository, ProductoService productoService, ContadorFacturaRepository contadorFacturaRepository) {
        this.ventaRepository = ventaRepository;
        this.productoService = productoService;
        this.contadorFacturaRepository = contadorFacturaRepository;
        if(!contadorFacturaRepository.existsById(1L)) {
            ContadorFactura contadorFactura = new ContadorFactura();
            contadorFactura.setContador(0L);
            contadorFacturaRepository.save(contadorFactura);
        }
    }

    public VentaResponse crearVenta(Venta venta) {
        venta.setFecha(new Date());

        // Obtener el contador de facturas y incrementarlo
        ContadorFactura contadorFactura = contadorFacturaRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("No se encontró el contador de facturas"));
        contadorFactura.setContador(contadorFactura.getContador() + 1);
        contadorFacturaRepository.save(contadorFactura);

        // Establecer el número de factura
        venta.setNumeroFactura("FAC-" + contadorFactura.getContador());

        // Reducir el stock de los productos y crear la lista de stock restante
        List<ProductoStock> stockRestante = new ArrayList<>();
        venta.getProductos().forEach(producto -> {
            productoService.reducirStock(producto.getId(), 1);
            // Obtener el producto actualizado para obtener el stock restante
            Producto productoActualizado = productoService.obtenerProductoPorId(producto.getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado")); // Manejar excepción si no se encuentra el producto
            stockRestante.add(new ProductoStock(productoActualizado.getId(), productoActualizado.getNombre(), productoActualizado.getStock()));
        });

        // Guardar la venta en la base de datos
        Venta nuevaVenta = ventaRepository.save(venta);
        // Crear la respuesta
        return new VentaResponse(nuevaVenta, stockRestante);
    }
}