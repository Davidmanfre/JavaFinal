package com.Test.jpa.testJpa.repository;

import com.Test.jpa.testJpa.model.ContadorFactura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContadorFacturaRepository extends JpaRepository<ContadorFactura, Long> {
}