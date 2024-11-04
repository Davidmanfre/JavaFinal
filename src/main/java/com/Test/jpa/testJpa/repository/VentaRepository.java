package com.Test.jpa.testJpa.repository;

import com.Test.jpa.testJpa.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}