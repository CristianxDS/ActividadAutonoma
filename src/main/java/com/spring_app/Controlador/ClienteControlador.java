package com.spring_app.Controlador;

import com.spring_app.Entidad.Cliente;
import com.spring_app.Entidad.Producto;
import com.spring_app.Repositorio.ClienteRepostorio;
import com.spring_app.Servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Controller
public class ClienteControlador {
    @Autowired
    ClienteServicio clienteServicio;

    @GetMapping("/clientes")
    public String mostrarClientes(@RequestParam(name = "buscarCliente", required = false, defaultValue = "") String buscarCliente, Model model) {
        List<Cliente> clientes= clienteServicio.buscarClienteNombre(buscarCliente);
        model.addAttribute("buscarCliente", buscarCliente);
        model.addAttribute("clientes", clientes);
        return "/Producto/listaClientes";
    }
    //CREAR
    @GetMapping("Clientes/formulario")
    public String formularioProducto(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "/Producto/formularioClientes";
    }

    @PostMapping("/guardarClientes")
    public String crearProducto(Cliente cliente) {
        clienteServicio.guardarCliente(cliente);
        return "redirect:/clientes";
    }

    //ACTUALIZAR<
    @GetMapping("/editarCliente/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Optional<Cliente> cliente = clienteServicio.buscarCliente(id);
        model.addAttribute("Cliente", cliente);
        return "/Producto/formularioClientes";
    }

    //ELIMINAR
    @GetMapping("/eliminarCliente/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteServicio.eliminarCliente(id);
        return "redirect:/clientes";
    }



}
