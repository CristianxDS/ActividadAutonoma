package com.spring_app.Servicio;

import com.spring_app.Entidad.Producto;
import com.spring_app.Repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicio {

    @Autowired
    ProductoRepositorio productoRepositorio;

    public List<Producto> listarProductos(){

        return productoRepositorio.findAll();
    }

    public List<Producto> buscarProductoNombre(String buscarProducto){
        if(buscarProducto==null || buscarProducto.isEmpty()){
            return productoRepositorio.findAll();
        }else{
            return productoRepositorio.findByNombreContainingIgnoreCase(buscarProducto);
        }
    }

    public Optional<Producto> buscarProducto(Long id){

        return productoRepositorio.findById(id);
    }

    public void guardarProducto(Producto producto ){

        productoRepositorio.save(producto);
    }

    public void eliminarProducto(Long id){
        productoRepositorio.deleteById(id);
    }

    public List<Producto> buscarTodos() {
        return productoRepositorio.findAll();
    }


}
