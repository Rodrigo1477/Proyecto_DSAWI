package com.wismann.proyectodawi_rodrigo.Controller;

import com.wismann.proyectodawi_rodrigo.Entity.Cliente;
import com.wismann.proyectodawi_rodrigo.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;



    @GetMapping
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteRepository.findAll();
        model.addAttribute("clientes", clientes);
        return "clientes/listar";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/crear";
    }

    @PostMapping("/crear")
    public String crearCliente(@ModelAttribute Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isPresent()) {
            model.addAttribute("cliente", clienteOpt.get());
            return "clientes/editar";
        }
        return "redirect:/clientes";
    }

    @PostMapping("/editar/{id}")
    public String actualizarCliente(@PathVariable Integer id, @ModelAttribute Cliente cliente) {
        cliente.setIdCliente(id);
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Integer id) {
        clienteRepository.deleteById(id);
        return "redirect:/clientes";
    }



    @GetMapping("/api")
    public @ResponseBody List<Cliente> listarClientesAPI() {
        return clienteRepository.findAll();
    }

    @PostMapping("/api")
    public @ResponseBody Cliente crearClienteAPI(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @GetMapping("/api/{id}")
    public @ResponseBody Optional<Cliente> obtenerClienteAPI(@PathVariable Integer id) {
        return clienteRepository.findById(id);
    }

    @PutMapping("/api/{id}")
    public @ResponseBody Cliente actualizarClienteAPI(@PathVariable Integer id, @RequestBody Cliente cliente) {
        cliente.setIdCliente(id);
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/api/{id}")
    public @ResponseBody void eliminarClienteAPI(@PathVariable Integer id) {
        clienteRepository.deleteById(id);
    }
}
