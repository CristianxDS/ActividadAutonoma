package com.spring_app.Servicio;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.spring_app.Entidad.Cliente;
import com.spring_app.Entidad.Proveedor;
import com.spring_app.Repositorio.ClienteRepostorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {
    @Autowired
    ClienteRepostorio clienteRepostorio;


    public List<Cliente> buscarClienteNombre(String buscarCliente) {
        if (buscarCliente == null || buscarCliente.isEmpty()) {
          return clienteRepostorio.findAll();
        } else {
            return clienteRepostorio.findByNombreContainingIgnoreCase(buscarCliente);
        }
    }

    public Optional<Cliente> buscarCliente(Long id) {

        return clienteRepostorio.findById(id);
    }
    public void guardarCliente(Cliente cliente){

        clienteRepostorio.save(cliente);
    }

    public void eliminarCliente(Long id){
        clienteRepostorio.deleteById(id);
    }

    //metodo para generar el listado PDF
    public String generarPdf()throws DocumentException, IOException {
        List<Cliente> clientes=clienteRepostorio.findAll();
        Document document= new Document();
        String rutaPdf= Paths.get("Cliente.pdf").toAbsolutePath().toString();
        try(FileOutputStream fos =new FileOutputStream(rutaPdf)){
            PdfWriter.getInstance(document,fos);
            document.open();
            document.add(new Paragraph("Lista de Clientes", FontFactory.getFont("Arial",14, Font.BOLD)));
            PdfPTable tabla= new PdfPTable(5);
            tabla.setWidthPercentage(100);

            tabla.addCell(new PdfPCell(new Phrase("Id",FontFactory.getFont("Arial",14, Font.BOLD))));
            tabla.addCell(new PdfPCell(new Phrase("Nombre",FontFactory.getFont("Arial",14, Font.BOLD))));
            tabla.addCell(new PdfPCell(new Phrase("dirección",FontFactory.getFont("Arial",14, Font.BOLD))));
            tabla.addCell(new PdfPCell(new Phrase("Fecha de Nacimiento",FontFactory.getFont("Arial",14, Font.BOLD))));
            for (Cliente cliente :clientes){
                tabla.addCell(new PdfPCell(new Phrase(String.valueOf(cliente.getId()),FontFactory.getFont("Arial",11,Font.NORMAL))));
                tabla.addCell(new PdfPCell(new Phrase(String.valueOf(cliente.getNombre()),FontFactory.getFont("Arial",11, Font.NORMAL))));
                tabla.addCell(new PdfPCell(new Phrase(String.valueOf(cliente.getDireccion()),FontFactory.getFont("Arial",11, Font.NORMAL))));

            }
            document.add(tabla);
            document.close();
        }catch (IOException | DocumentException e){
            throw new IOException("No se puede Generar el pdf", e);
        }
        return rutaPdf;
    }

}
