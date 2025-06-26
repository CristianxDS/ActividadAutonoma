package com.spring_app.Servicio;

import com.spring_app.Entidad.Factura;
import com.spring_app.Repositorio.FacturaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaServicio {
    @Autowired
    private FacturaRepositorio facturaRepositorio;

    // Guardar una factura
    public void guardarFactura(Factura factura) {
        facturaRepositorio.save(factura);
    }

    // Buscar todas las facturas
    public List<Factura> listarFacturas() {
        return facturaRepositorio.findAll();
    }

    // Buscar una factura por ID
    public Optional<Factura> buscarFacturaPorId(Long id) {
        return facturaRepositorio.findById(id);
    }

    // Eliminar una factura
    public void eliminarFactura(Long id) {
        facturaRepositorio.deleteById(id);
    }

    // Buscar facturas por cliente (nombre parcial)
    public List<Factura> buscarFacturasPorCliente(String nombre) {
        return facturaRepositorio.findByCliente_NombreContainingIgnoreCase(nombre);
    }

}
