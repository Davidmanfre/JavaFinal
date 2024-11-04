package com.Test.jpa.testJpa.repository;

import com.Test.jpa.testJpa.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}