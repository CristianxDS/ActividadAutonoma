package com.spring_app.Controlador;

import com.spring_app.Entidad.Factura;
import com.spring_app.Servicio.ClienteServicio;
import com.spring_app.Servicio.FacturaServicio;
import com.spring_app.Servicio.ProductoServicio;
<<<<<<< HEAD
=======
import jakarta.persistence.Id;
>>>>>>> 5e6653afb630086f4ecbd0798085eef7d3d7b9d5
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

import java.util.List;
import java.util.Optional;

@Controller
public class FacturaControlador {

    @Autowired
    private FacturaServicio facturaServicio;
    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private ProductoServicio productoServicio;

    // Mostrar lista de facturas
    @GetMapping("/facturas")
    public String mostrarFacturas(@RequestParam(name = "buscarFactura", required = false, defaultValue = "") String buscarFactura,String buscarClientes,String buscarProducto, Model model) {
        List<Factura> facturas = facturaServicio.buscarFacturasPorCliente(buscarFactura);
        model.addAttribute("buscarFactura", buscarFactura);
        model.addAttribute("facturas", facturas);

        model.addAttribute("clientes", clienteServicio.buscarClienteNombre(buscarClientes));
        model.addAttribute("productos", productoServicio.buscarProductoNombre(buscarProducto));

        return "Producto/listaFacturas"; // Actualizado
    }

    // Mostrar formulario para crear una nueva factura
    @GetMapping("/facturas/formulario")
    public String formularioFactura(Model model) {
        model.addAttribute("factura", new Factura());
        model.addAttribute("clientes", clienteServicio.buscarTodos());
        model.addAttribute("productos", productoServicio.buscarTodos());
        return "Producto/formularioFactura";
    }

    // Guardar o actualizar una factura
    @PostMapping("/guardarFactura")
    public String guardarFactura(Factura factura) {
        facturaServicio.guardarFactura(factura);
        return "redirect:/facturas";
    }

    // Mostrar formulario para editar una factura existente
    @GetMapping("/editarFactura/{id}")
    public String editarFactura(@PathVariable Long id, Model model) {
        Optional<Factura> factura = facturaServicio.buscarFacturaPorId(id);
        if (factura.isPresent()) {
            model.addAttribute("factura", factura.get());
            model.addAttribute("clientes", clienteServicio.buscarTodos());
            model.addAttribute("productos", productoServicio.buscarTodos());
            return "Producto/formularioFactura";
        } else {
            return "redirect:/facturas";
        }
    }

    // Eliminar una factura
    @GetMapping("/eliminarFactura/{id}")
    public String eliminarFactura(@PathVariable Long id) {
        facturaServicio.eliminarFactura(id);
        return "redirect:/facturas";
    }

}
