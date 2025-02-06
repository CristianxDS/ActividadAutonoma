package com.spring_app.Servicio;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
    //metodo para generar el listado PDF
    public String generarPdf()throws DocumentException, IOException {
        List<Proveedor> proveedores=proveedorRepositorio.findAll();
        Document document= new Document();
        String rutaPdf= Paths.get("Proveedores.pdf").toAbsolutePath().toString();
        try(FileOutputStream fos =new FileOutputStream(rutaPdf)){
            PdfWriter.getInstance(document,fos);
            document.open();
            document.add(new Paragraph("Lista de Proveedores", FontFactory.getFont("Arial",14, Font.BOLD)));
            PdfPTable tabla= new PdfPTable(5);
            tabla.setWidthPercentage(100);

            tabla.addCell(new PdfPCell(new Phrase("Id",FontFactory.getFont("Arial",14, Font.BOLD))));
            tabla.addCell(new PdfPCell(new Phrase("Nombre",FontFactory.getFont("Arial",14, Font.BOLD))));
            tabla.addCell(new PdfPCell(new Phrase("Descripción",FontFactory.getFont("Arial",14, Font.BOLD))));

            for (Proveedor proveedor: proveedores){
                tabla.addCell(new PdfPCell(new Phrase(String.valueOf(proveedor.getId()),FontFactory.getFont("Arial",11,Font.NORMAL))));
                tabla.addCell(new PdfPCell(new Phrase(String.valueOf(proveedor.getNombre()),FontFactory.getFont("Arial",11, Font.NORMAL))));
                tabla.addCell(new PdfPCell(new Phrase(String.valueOf(proveedor.getDireccion()),FontFactory.getFont("Arial",11, Font.NORMAL))));

            }
            document.add(tabla);
            document.close();
        }catch (IOException | DocumentException e){
            throw new IOException("No se puede Generar el pdf", e);
        }
        return rutaPdf;
    }
}
