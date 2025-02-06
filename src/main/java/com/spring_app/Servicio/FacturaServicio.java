package com.spring_app.Servicio;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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

    // Generar un archivo PDF de una factura
    public void generarFacturaPdf(Long idFactura, String rutaArchivo) throws IOException, DocumentException {
        Optional<Factura> facturaOptional = buscarFacturaPorId(idFactura);

        if (facturaOptional.isPresent()) {
            Factura factura = facturaOptional.get();
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
            documento.open();

            // Título
            documento.add(new Paragraph("Factura", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
            documento.add(new Paragraph(" "));

            // Información del cliente
            documento.add(new Paragraph("Cliente: " + factura.getCliente().getNombre()));
            documento.add(new Paragraph(" "));

            // Tabla de productos
            PdfPTable tabla = new PdfPTable(4); // 4 columnas: Producto, Cantidad, Precio, Subtotal
            tabla.addCell("Producto");
            tabla.addCell("Cantidad");
            tabla.addCell("Precio");
            tabla.addCell("Subtotal");

            // Producto asociado
            tabla.addCell(factura.getProductos().getNombre());
            tabla.addCell(String.valueOf(factura.getCantidad()));
            tabla.addCell(String.valueOf(factura.getPrecio()));
            tabla.addCell(String.valueOf(factura.getSubtotal()));

            documento.add(tabla);
            documento.add(new Paragraph(" "));

            // Total
            documento.add(new Paragraph("Total: " + factura.getTotal(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));

            documento.close();
        } else {
            throw new IllegalArgumentException("Factura con ID " + idFactura + " no encontrada.");
        }
    }
}
