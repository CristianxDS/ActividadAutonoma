package com.spring_app.Repositorio;

import com.spring_app.Entidad.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor,Long> {
    List<Proveedor> findByNombreContainingIgnoreCase(String nombre);
}
