package com.Test.jpa.testJpa.service;

import com.Test.jpa.testJpa.Producto;
import com.Test.jpa.testJpa.Venta;
import com.Test.jpa.testJpa.model.ContadorFactura;
import com.Test.jpa.testJpa.repository.ContadorFacturaRepository;
import com.Test.jpa.testJpa.repository.VentaRepository;
import com.Test.jpa.testJpa.dto.VentaDTO;
import com.Test.jpa.testJpa.dto.VentaResponseDTO;
import com.Test.jpa.testJpa.dto.ProductoStockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoService productoService;
    private final ContadorFacturaRepository contadorFacturaRepository;
    private final ClienteService clienteService;


    @Autowired
    public VentaService(VentaRepository ventaRepository, ProductoService productoService, ContadorFacturaRepository contadorFacturaRepository, ClienteService clienteService) {
        this.ventaRepository = ventaRepository;
        this.productoService = productoService;
        this.contadorFacturaRepository = contadorFacturaRepository;
        this.clienteService = clienteService;

        if (!contadorFacturaRepository.existsById(1L)) {
            ContadorFactura contadorFactura = new ContadorFactura();
            contadorFactura.setContador(0L);
            contadorFacturaRepository.save(contadorFactura);
        }
    }
    public VentaResponseDTO crearVenta(VentaDTO ventaDTO) {
        Venta venta = new Venta();
        venta.setFecha(new Date());

        // Asignar cliente a la venta
        venta.setCliente(clienteService.obtenerClientePorId(ventaDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado")));

        // Obtener los productos desde los IDs y asignarlos a la venta
        List<Producto> productos = ventaDTO.getProductosIds().stream()
                .map(id -> productoService.obtenerProductoPorId(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id)))
                .collect(Collectors.toList());
        venta.setProductos(productos);

        // Generar el número de factura
        ContadorFactura contadorFactura = contadorFacturaRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("No se encontró el contador de facturas"));
        contadorFactura.setContador(contadorFactura.getContador() + 1);
        contadorFacturaRepository.save(contadorFactura);

        venta.setNumeroFactura("FAC-" + contadorFactura.getContador());

        // Reducir el stock de los productos y recopilar el stock restante
        List<ProductoStockDTO> stockRestante = new ArrayList<>();
        productos.forEach(producto -> {
            productoService.reducirStock(producto.getId(), 1);
            Producto productoActualizado = productoService.obtenerProductoPorId(producto.getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            stockRestante.add(new ProductoStockDTO(productoActualizado.getId(), productoActualizado.getNombre(), productoActualizado.getStock()));
        });

        // Guardar la venta y devolver respuesta como DTO
        Venta nuevaVenta = ventaRepository.save(venta);
        VentaResponseDTO responseDTO = new VentaResponseDTO();
        responseDTO.setId(nuevaVenta.getId());
        responseDTO.setNumeroFactura(nuevaVenta.getNumeroFactura());
        responseDTO.setClienteId(nuevaVenta.getCliente().getId());
        responseDTO.setFecha(nuevaVenta.getFecha());
        responseDTO.setStockRestante(stockRestante);

        return responseDTO;
    }
}