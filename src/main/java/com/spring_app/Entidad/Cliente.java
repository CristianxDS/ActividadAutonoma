package com.spring_app.Entidad;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String direccion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    /*@ManyToMany
    @JoinTable(
            name="factura",
            joinColumns = @JoinColumn(name="id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_producto"),
    )
   private List<Producto> productos; */

    @OneToMany(mappedBy = "cliente")
    private List<Factura> facturas;




}
