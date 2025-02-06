package com.spring_app.Servicio;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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

    //metodo para generar el listado PDF
    public String generarPdf()throws DocumentException, IOException{
        List<Producto> productos=productoRepositorio.findAll();
        Document document= new Document();
        String rutaPdf= Paths.get("productos.pdf").toAbsolutePath().toString();
    try(FileOutputStream fos =new FileOutputStream(rutaPdf)){
        PdfWriter.getInstance(document,fos);
        document.open();
        document.add(new Paragraph("Lista de Productos", FontFactory.getFont("Arial",14, Font.BOLD)));
        PdfPTable tabla= new PdfPTable(5);
        tabla.setWidthPercentage(100);

        tabla.addCell(new PdfPCell(new Phrase("Código",FontFactory.getFont("Arial",14, Font.BOLD))));
        tabla.addCell(new PdfPCell(new Phrase("Nombre",FontFactory.getFont("Arial",14, Font.BOLD))));
        tabla.addCell(new PdfPCell(new Phrase("Descripción",FontFactory.getFont("Arial",14, Font.BOLD))));
        tabla.addCell(new PdfPCell(new Phrase("Precio",FontFactory.getFont("Arial",14, Font.BOLD))));
        tabla.addCell(new PdfPCell(new Phrase("Stock",FontFactory.getFont("Arial",14, Font.BOLD))));

        for (Producto producto: productos){
          tabla.addCell(new PdfPCell(new Phrase(String.valueOf(producto.getId()),FontFactory.getFont("Arial",11,Font.NORMAL))));
            tabla.addCell(new PdfPCell(new Phrase(String.valueOf(producto.getNombre()),FontFactory.getFont("Arial",11, Font.NORMAL))));
            tabla.addCell(new PdfPCell(new Phrase(String.valueOf(producto.getDescripcion()),FontFactory.getFont("Arial",11, Font.NORMAL))));
            tabla.addCell(new PdfPCell(new Phrase(String.valueOf(producto.getPrecio()),FontFactory.getFont("Arial",11, Font.NORMAL))));
            tabla.addCell(new PdfPCell(new Phrase(String.valueOf(producto.getStock()),FontFactory.getFont("Arial",11, Font.NORMAL))));
        }
        document.add(tabla);
        document.close();
    }catch (IOException | DocumentException e){
        throw new IOException("No se puede Generar el pdf", e);
    }
    return rutaPdf;
    }

}
