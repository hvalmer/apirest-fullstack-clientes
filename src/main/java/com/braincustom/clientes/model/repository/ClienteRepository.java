package com.braincustom.clientes.model.repository;

import com.braincustom.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findById(String idCliente);
}
