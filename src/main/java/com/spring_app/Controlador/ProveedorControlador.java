package com.spring_app.Controlador;


import com.spring_app.Entidad.Proveedor;
import com.spring_app.Servicio.ProveedorServicio;
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
public class ProveedorControlador {
    @Autowired
    ProveedorServicio proveedorServicio;

    //LEER
    @GetMapping("/proveedores")
    public String mostrarProveedor(@RequestParam(name = "buscarProveedor", required = false, defaultValue = "") String buscarProveedor, Model model) {
        List<Proveedor> proveedor = proveedorServicio.buscarProveedorNombre(buscarProveedor);
        model.addAttribute("buscarProveedor", buscarProveedor);
        model.addAttribute("proveedores", proveedor);
        return "/Producto/listaProveedores";
    }

    //CREAR
    @GetMapping("/formularioProveedor")
    public String formularioProveedor(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "/Producto/formularioProveedor";
    }
//Guardar
@PostMapping("/guardarProveedor")
public String crearProveedor(Proveedor proveedor) {
    proveedorServicio.guardarProveedor(proveedor);
    return "redirect:/proveedores";
}

    //ACTUALIZAR<
    @GetMapping("/editarproveedor/{id}")
    public String editarproveedor(@PathVariable Long id, Model model) {
        Optional<Proveedor> proveedor = proveedorServicio.buscarProveedor(id);
        model.addAttribute("proveedor", proveedor);
        return "/Producto/formularioProveedor";
    }


    //ELIMINAR
    @GetMapping("/eliminarproveedor/{id}")
    public String eliminarProveedor(@PathVariable Long id) {
        proveedorServicio.eliminarProveedor(id);
        return "redirect:/proveedores";
    }
    }
