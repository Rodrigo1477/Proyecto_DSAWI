package com.wismann.proyectodawi_rodrigo.Service;

import com.wismann.proyectodawi_rodrigo.Entity.Categoria;
import com.wismann.proyectodawi_rodrigo.Repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria obtenerCategoriaPorId(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElse(null);
    }

    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    // Método para actualizar una categoría existente
    public Categoria actualizarCategoria(Categoria categoria) {
        if (categoria.getIdCategoria() != null && categoriaRepository.existsById(categoria.getIdCategoria())) {
            return categoriaRepository.save(categoria);
        }
        return null;
    }
}
