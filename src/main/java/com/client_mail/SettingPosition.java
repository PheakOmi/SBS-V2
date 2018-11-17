package com.client_mail;

import com.itextpdf.io.image.ImageData; 
import com.itextpdf.io.image.ImageDataFactory; 
import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter; 
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document; 
import com.itextpdf.layout.element.Image;  
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Paragraph;

public class SettingPosition {      
   public static void main(String args[]) throws Exception {              
      // Creating a PdfWriter       
      String dest = "D:/temp/sp3.pdf";       
      PdfWriter writer = new PdfWriter(dest);               
      
      // Creating a PdfDocument       
      PdfDocument pdfDoc = new PdfDocument(writer);              
      
      // Creating a Document        
      Document document = new Document(pdfDoc);              
      
      // Creating an ImageData object       
      String imFile = "./src/main/resources/img/1.png";       
      ImageData data = ImageDataFactory.create(imFile);             

      // Creating an Image object        
      Image image = new Image(data);                
      
      // Setting the position of the image to the center of the page       
      image.setFixedPosition(240, 500);                  
      
      // Adding image to the document       
      document.add(image.scale(3, 3));              
      
      
      String para1 = "Enjoy Your vKirirom Shuttle Bus: \n";             
    	      
     // Creating Paragraphs       
	  Paragraph paragraph1 = new Paragraph(para1);             
     // Adding paragraphs to document       
     document.add(paragraph1.setFontSize(16).setBold());  
      
      
   // Creating a list
      List list = new List();  
      
      // Add elements to the list       
      list.add("Booking Code\t: 000000695");
      list.add("Source\t    : Kirirom \n(Ativity Room)");       
      list.add("Destination\t: Phnom Penh \n(Rathana Plaza)");      
      list.add("Departure Date\t: 2018-03-06");       
      list.add("Departure Time\t: 02:00 PM");  
      list.add("Adult\t   : 2  person"); 
      list.add("Child\t   : 0  person");                  
      list.add("Total Amount\t: 0  person"); 
      list.add("Total Cost\t: 50 dollars"); 
      // Adding list to the document       
      document.add(list);
      
      
      
   // Creating a new page       
      PdfPage pdfPage = pdfDoc.getFirstPage();               
      
      // Creating a PdfCanvas object       
      PdfCanvas canvas = new PdfCanvas(pdfPage);              
      
      // Initial point of the line       
      canvas.moveTo(25, 540);              
      
      // Drawing the line       
      canvas.lineTo(570, 540);           
      
      // Closing the path stroke       
      canvas.closePathStroke();  
      
      
      String para2 = "\n\n\n Note: ";                  
 	  Paragraph paragraph2 = new Paragraph(para2);                  
      document.add(paragraph2.setFontSize(14).setBold());  
      
      String para3 = "Please show this QR-Code to driver before enter the Shuttle Bus for validation passenger.";                  
 	  Paragraph paragraph3 = new Paragraph(para3);                  
      document.add(paragraph3);  
      
      
      // Closing the document       
      document.close();
      
      System.out.println("Image added");    
   } 
}