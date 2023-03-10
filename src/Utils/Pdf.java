/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Services.ServiceAdmin;
import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import Entities.user;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author oumayma
 */
public class Pdf {
        public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        ServiceAdmin m=new ServiceAdmin();
        List<user> list=m.selectAll();    
        document.add(new Paragraph("La liste des Report :"));
        document.add(new Paragraph("     "));
         for(user u:list)
        {
            
        document.add(new Paragraph("Nom :"+u.getNom_u()));
        document.add(new Paragraph("prenom :"+u.getPrenom_u()));
               document.add(new Paragraph("adresse :"+u.getAdresse()));
        document.add(new Paragraph("Genere:"+u.getGenere()));
        document.add(new Paragraph("role :"+u.getRole()));
        document.add(new Paragraph("telephone :"+u.getTel()));
        document.add(new Paragraph("age :"+u.getAge()));
    


        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
}
