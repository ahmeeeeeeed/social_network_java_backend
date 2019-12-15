/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services.implimentation;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import tn.esprit.entities.User;

public class GenererFichePDF {
    
    

   /* List<ProduitsPanier> ProduitsServicesList ;
    Panier panier = new Panier();
    Commande commande= new Commande();*/
    
    public PdfPCell getCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text, FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 13, new BaseColor(68,141,186))));
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }
    
    public void addrowsfrom(Document doc,User u,String description,double montant) throws DocumentException{
        
        
        
         Color mainColor =  Color.decode("#DBE4FF");
         BaseColor mycolor = new BaseColor(mainColor);
       //  System.out.println(prestationMap.get(id_resident));
        
      //  prestationMap.get(id_resident).stream().forEach((f)->{
           
            try{
            	
                
                
            
            Paragraph paragraphe = new Paragraph("Facture de paiement", FontFactory.getFont(FontFactory.TIMES_BOLD, 30, new BaseColor(68,141,186)));
            paragraphe.setAlignment(Paragraph.ALIGN_CENTER);
            doc.add(paragraphe);

             
             
              PdfPCell cell;
                
              PdfPTable table1 = new PdfPTable(5);
              {
                /*cell = new PdfPCell(new Phrase( "   ", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
                cell.setPaddingBottom(20);
                table1.addCell(cell);*/
                
                cell = new PdfPCell(new Phrase("Client :", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
                cell.setPaddingBottom(20);
                table1.addCell(cell);
                
                cell = new PdfPCell(new Phrase(u.getNom(), FontFactory.getFont(FontFactory.TIMES_BOLD, 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
                cell.setPaddingBottom(20);
                table1.addCell(cell);
                
                cell = new PdfPCell(new Phrase(u.getPrenom(), FontFactory.getFont(FontFactory.TIMES_BOLD, 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
                cell.setPaddingBottom(20);
                table1.addCell(cell);
                
                cell = new PdfPCell(new Phrase("    ", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
                cell.setPaddingBottom(20);
                table1.addCell(cell);


                cell = new PdfPCell(new Phrase( "   ", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
                cell.setPaddingBottom(20);
                table1.addCell(cell);
                
                table1.setWidthPercentage(100);
                doc.add(table1);
              }
              
              PdfPTable table2 = new PdfPTable(2);
              {
                cell = new PdfPCell(new Phrase( "Adresse mail :", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
              //  cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table2.addCell(cell);
                
                cell = new PdfPCell(new Phrase(u.getMail(), FontFactory.getFont(FontFactory.TIMES_BOLD, 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
             //   cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table2.addCell(cell);
                
           /*     cell = new PdfPCell(new Phrase("user", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
               // cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table2.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Date derniere modification :", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
              //  cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table2.addCell(cell);
                
                cell = new PdfPCell(new Phrase(""+"user", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
              //  cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table2.addCell(cell);
                
                */
                table2.setWidthPercentage(100);
                doc.add(table2);
              }
                   
              PdfPTable table3 = new PdfPTable(1);
              {
                cell = new PdfPCell(new Phrase( "Description : "+description, FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
                cell.setPaddingBottom(20);
                table3.addCell(cell);
                
              
                table3.setWidthPercentage(100);
                doc.add(table3);
              }
                   
              PdfPTable table4 = new PdfPTable(2);
              {
                cell = new PdfPCell(new Phrase( "montant de paiement :", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
              //  cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table4.addCell(cell);
                
                cell = new PdfPCell(new Phrase(+montant+"$", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
             //   cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table4.addCell(cell);
                
         
                table4.setWidthPercentage(100);
                doc.add(table4);
              }
              PdfPTable table5 = new PdfPTable(2);
              {
                cell = new PdfPCell(new Phrase( "Numero de carte de crédit :", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
              //  cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table5.addCell(cell);
                
                cell = new PdfPCell(new Phrase(u.getNumeroCarte(), FontFactory.getFont(FontFactory.TIMES_BOLD, 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
             //   cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table5.addCell(cell);
                
         
                table5.setWidthPercentage(100);
                doc.add(table5);
              }
              PdfPTable table6 = new PdfPTable(2);
              {
                cell = new PdfPCell(new Phrase( "TVA non appliquable, aucun escompte en cas de reglement anticipé :", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
              //  cell.setPaddingTop(20);
                cell.setBackgroundColor(mycolor);
                table6.addCell(cell);
                
              
                
         
                table6.setWidthPercentage(100);
                doc.add(table6);
              }
          
              PdfPTable table7 = new PdfPTable(6);
              {
                cell = new PdfPCell(new Phrase( " ", FontFactory.getFont("Comic Sans MS", 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table7.addCell(cell);
                
                cell = new PdfPCell(new Phrase(" ", FontFactory.getFont("Comic Sans MS", 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table7.addCell(cell);
                
                cell = new PdfPCell(new Phrase("     ", FontFactory.getFont("Comic Sans MS", 12, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table7.addCell(cell);
                
                cell = new PdfPCell(new Phrase("     ", FontFactory.getFont("Comic Sans MS", 12, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table5.addCell(cell);
                
                cell = new PdfPCell(new Phrase("     ", FontFactory.getFont("Comic Sans MS", 12, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table7.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Date de facture :", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table7.addCell(cell);
                
                cell = new PdfPCell(new Phrase(new Date().toString(), FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table7.addCell(cell);
                table7.setWidthPercentage(100);
                
                
                doc.add(table7);
              }
              PdfPTable table8 = new PdfPTable(6);
              {
                cell = new PdfPCell(new Phrase( " ", FontFactory.getFont("Comic Sans MS", 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table8.addCell(cell);
                
                cell = new PdfPCell(new Phrase(" ", FontFactory.getFont("Comic Sans MS", 10, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table8.addCell(cell);
                
                cell = new PdfPCell(new Phrase("     ", FontFactory.getFont("Comic Sans MS", 12, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table8.addCell(cell);
                
                cell = new PdfPCell(new Phrase("     ", FontFactory.getFont("Comic Sans MS", 12, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table8.addCell(cell);
                
                cell = new PdfPCell(new Phrase("     ", FontFactory.getFont("Comic Sans MS", 12, new BaseColor(100,100,100))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table8.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Date d'échéance :", FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table8.addCell(cell);
                
                cell = new PdfPCell(new Phrase(new Date().toString(), FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 10, new BaseColor(82,120,255))));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingTop(20);
             //   cell.setBackgroundColor(mycolor);
                table8.addCell(cell);
                
                
                table8.setWidthPercentage(100);
                
                
                doc.add(table8);
              }
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            }catch (DocumentException ex) {
                       System.out.println(ex.getMessage());
           }


        //});
         
    }

    public boolean pdf(User u,String description,double montant) throws IOException, SQLException {
    
        Document doc = new Document();
       // System.out.println(prestationMap);

        try {
           // ServiceResident sr = new ServiceResident();
           // Resident resident = sr.getResidentById(id_resident);
          //  System.out.println(resident);
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\wamp64\\www\\pi_social_network\\factures\\"+55+".pdf"));

            doc.open();
            
            PdfPTable table1 = new PdfPTable(1);
            table1.setWidthPercentage(100);
           //afficher la date courante au dessus de la fiche
            table1.addCell(getCell(LocalDate.now().toString(), PdfPCell.ALIGN_RIGHT));
            doc.add(table1);
            
          /*  Image img = Image.getInstance("X:\\Esprit\\semestre 2\\PIDev\\pidevs\\HappyoldsVersion1.0\\src\\GUI\\New Mockup 1 copy4.png");
            img.scaleAbsoluteWidth(80);
            img.scaleAbsoluteHeight(92);
            img.setAlignment(Image.ALIGN_CENTER);
            doc.add(img);*/
            
            addrowsfrom(doc,u, description,montant);
           
      
            doc.close();
         //   Desktop.getDesktop().open(new File("C:\\wamp64\\www\\pi_social_network\\factures"+55+".pdf"));
            return true;

        } catch (DocumentException | FileNotFoundException e) {
            
            System.out.println(e.getMessage());
            return false;
        }

    }


    
}
