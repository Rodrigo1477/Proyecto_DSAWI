package com.wismann.proyectodawi_rodrigo.Repository;

import com.wismann.proyectodawi_rodrigo.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}