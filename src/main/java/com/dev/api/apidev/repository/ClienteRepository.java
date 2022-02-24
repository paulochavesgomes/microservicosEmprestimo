package com.dev.api.apidev.repository;

import com.dev.api.apidev.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
