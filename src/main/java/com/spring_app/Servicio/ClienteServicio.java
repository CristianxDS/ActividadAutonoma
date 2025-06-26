package com.spring_app.Servicio;

import com.spring_app.Entidad.Cliente;
import com.spring_app.Entidad.Proveedor;
import com.spring_app.Repositorio.ClienteRepostorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {
    @Autowired
    ClienteRepostorio clienteRepostorio;


    public List<Cliente> buscarClienteNombre(String buscarCliente) {
        if (buscarCliente == null || buscarCliente.isEmpty()) {
          return clienteRepostorio.findAll();
        } else {
            return clienteRepostorio.findByNombreContainingIgnoreCase(buscarCliente);
        }
    }

    public Optional<Cliente> buscarCliente(Long id) {

        return clienteRepostorio.findById(id);
    }
    public void guardarCliente(Cliente cliente){

        clienteRepostorio.save(cliente);
    }

    public void eliminarCliente(Long id){
        clienteRepostorio.deleteById(id);
    }

    public List<Cliente> buscarTodos() {
        return clienteRepostorio.findAll();
    }

}
