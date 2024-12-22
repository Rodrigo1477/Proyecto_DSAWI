package com.wismann.proyectodawi_rodrigo.Controller;

import com.wismann.proyectodawi_rodrigo.Entity.Categoria;
import com.wismann.proyectodawi_rodrigo.Entity.Producto;
import com.wismann.proyectodawi_rodrigo.Service.ProductoService;
import com.wismann.proyectodawi_rodrigo.Service.CategoriaService; // Asegúrate de tener el servicio de Categoría
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService; // Para obtener categorías

    public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        return "productos/listar";
    }

    @GetMapping("/nuevo")
    public String nuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.listarCategorias()); // Obtener categorías
        return "productos/crear";
    }

    @PostMapping
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable("id") Long id, Model model) {
        Producto producto = productoService.obtenerProductoPorId(id);
        List<Categoria> categorias = categoriaService.listarCategorias();
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categorias);
        return "productos/editar";
    }

    @PostMapping("/editar/{id}")
    public String actualizarProducto(@PathVariable Long id, @ModelAttribute Producto producto) {
        producto.setIdProducto(id);
        productoService.guardarProducto(producto);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos";
    }



    @GetMapping("/api")
    public ResponseEntity<List<Producto>> listarProductosAPI() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @PostMapping("/api")
    public ResponseEntity<Producto> guardarProductoAPI(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.guardarProducto(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    @PutMapping("/api/{id}")
    public ResponseEntity<Producto> actualizarProductoAPI(@PathVariable Long id, @RequestBody Producto producto) {
        producto.setIdProducto(id);
        Producto productoActualizado = productoService.guardarProducto(producto);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/api/{id}")
    public ResponseEntity<Void> eliminarProductoAPI(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
