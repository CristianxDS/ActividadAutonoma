package com.spring_app.Servicio;

import com.spring_app.Entidad.Producto;
import com.spring_app.Entidad.Proveedor;
import com.spring_app.Repositorio.ProveedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServicio {
    @Autowired
    ProveedorRepositorio proveedorRepositorio;



    public List<Proveedor> buscarProveedorNombre(String buscarProveedor) {
        if (buscarProveedor == null || buscarProveedor.isEmpty()) {
            return proveedorRepositorio.findAll();
        } else {
            return proveedorRepositorio.findByNombreContainingIgnoreCase(buscarProveedor);
        }
    }

    public Optional<Proveedor> buscarProveedor(Long id) {

        return proveedorRepositorio.findById(id);
    }

    public void guardarProveedor(Proveedor proveedor){

        proveedorRepositorio.save(proveedor);
    }

    public void eliminarProveedor(Long id){
        proveedorRepositorio.deleteById(id);
    }

}
