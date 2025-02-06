package com.spring_app.Repositorio;

import com.spring_app.Entidad.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepositorio  extends JpaRepository<Factura, Long> {
    List<Factura> findByNombreContainingIgnoreCase(String nombre);
}
