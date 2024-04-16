package controlador;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.interfaces.IAccessibleElement;
import java.io.FileOutputStream;
import conexion.Conexion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Reportes {
    public void reportesClientes() throws DocumentException, BadElementException{
        String st = obtenerDiaActual();
        FileOutputStream archivo = null;
        try{
            Document documento = new Document();
            String nombreArchivo = "Reporte_Clientes_" + st  + ".pdf";
            
            File file = new File("src/pdf/"+nombreArchivo);
            archivo = new FileOutputStream(file);
            try{
                //creamos la instancia
                PdfWriter.getInstance(documento, archivo);
                
                //añadir imagen en el header y sclarla
                Image header = Image.getInstance("src/img/");
                header.scaleToFit(650, 1000);
                header.setAlignment(Chunk.ALIGN_CENTER);
                
                //formato de texto
                
                Paragraph parrafo = new Paragraph();
                parrafo.setAlignment(Paragraph.ALIGN_CENTER);
                parrafo.add("Reporte de BankAM \n\n");
                parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
                parrafo.add("Reporte de Clientes \n\n");
                
                
                documento.open();
                documento.add(header);
                documento.add(parrafo);
                
                PdfTable tabla = new PdfTable();
                
            }catch(PdfException e){
                System.out.println("Hubo un error" + e);
            }
        }catch(FileNotFoundException e){
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            try{
                archivo.close();
            }catch(IOException e){
                 Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
    }
    
    
    public String obtenerDiaActual(){
        String st = "";
        LocalDateTime ahora = LocalDateTime.now();
        int ano = ahora.getYear();
        int mes = ahora.getMonthValue();
        int dia = ahora.getDayOfMonth();
        int hora = ahora.getHour();
        int minuto = ahora.getMinute();
        int segundo = ahora.getSecond();
        
        st = dia + "/" + mes + "/" + ano + "||" + hora + ":" + minuto + ":" + segundo;
        return st;
    }
    
    
    public void reportesTransacciones(){
        
        
    }
    
    public void reportesCuentas(){
        
        
    }
    
    public void reportesTarjetas(){
        
        
    }
    
    
}