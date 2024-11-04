package com.Test.jpa.testJpa.repository;

import com.Test.jpa.testJpa.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}