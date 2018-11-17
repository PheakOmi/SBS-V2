package com.client_mail;

import com.itextpdf.io.image.ImageData; 
import com.itextpdf.io.image.ImageDataFactory; 

import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfWriter; 

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell; 
import com.itextpdf.layout.element.Image; 
import com.itextpdf.layout.element.Table;  

public class AddOverlappingImage {
   public static void main(String args[]) throws Exception {
      // Creating a PdfWriter object 
      String dest = "D:/temp/addingImage.pdf";
      PdfWriter writer = new PdfWriter(dest);    
      
      // Creating a PdfDocument object   
      PdfDocument pdfDoc = new PdfDocument(writer);
      
      // Creating a Document object
      Document doc = new Document(pdfDoc);
      
      // Creating a table
      float [] pointColumnWidths = {150f, 150f};
      Table table = new Table(pointColumnWidths);
      
      // Creating an ImageData object       
      String imageFile = "1.png";       
      ImageData data = ImageDataFactory.create(imageFile);        

      // Creating the image       
      Image img = new Image(data);              

      Cell cell10=new Cell();
      // Adding image to the cell10       
      cell10.add(img.setAutoScale(true)); 
      Cell cell12=new Cell();
      // Adding cell110 to the table       
      table.addCell(cell10);                         
      
      // Adding Table to document        
      doc.add(table);                  
      
      // Closing the document       
      doc.close();  
      
      System.out.println("Image added to table successfully..");     
   } 
} 