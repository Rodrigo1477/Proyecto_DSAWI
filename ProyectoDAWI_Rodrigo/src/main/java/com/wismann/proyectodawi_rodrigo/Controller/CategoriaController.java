package com.wismann.proyectodawi_rodrigo.Controller;

import com.wismann.proyectodawi_rodrigo.Entity.Categoria;
import com.wismann.proyectodawi_rodrigo.Service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }


    @GetMapping
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "categorias/listar";  // Vista de listado de categorías
    }


    @GetMapping("/nuevo")
    public String nuevaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/crear";
    }


    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute Categoria categoria) {
        if (categoria.getIdCategoria() == null) {
            categoriaService.guardarCategoria(categoria);
        } else {
            categoriaService.actualizarCategoria(categoria);
        }
        return "redirect:/categorias";
    }



    @GetMapping("/editar/{id}")
    public String editarCategoria(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
        model.addAttribute("categoria", categoria);
        return "categorias/editar";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);  //
        return "redirect:/categorias";
    }
}
