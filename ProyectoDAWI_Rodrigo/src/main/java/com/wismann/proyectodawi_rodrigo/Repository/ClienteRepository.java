package com.wismann.proyectodawi_rodrigo.Repository;

import com.wismann.proyectodawi_rodrigo.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByDni(String dni);
}