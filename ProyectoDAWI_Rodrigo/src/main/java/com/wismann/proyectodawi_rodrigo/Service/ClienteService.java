package com.wismann.proyectodawi_rodrigo.Service;

import com.wismann.proyectodawi_rodrigo.Entity.Cliente;
import com.wismann.proyectodawi_rodrigo.Repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Integer id) {
        clienteRepository.deleteById(id);
    }
}