/*
 * For QR code send to passenger who is customer not student
 */

package com.MainController;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.pdfbox.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.io.IOException;

@Controller
@RequestMapping("/download")
public class Download {
	 @Autowired
	 ServletContext context;

	 @RequestMapping(value = "/pdf/{fileName:.+}", method = RequestMethod.GET, produces = "application/pdf")
	 public ResponseEntity<InputStreamResource> download(@PathVariable("fileName") String fileName) throws IOException, java.io.IOException {
	  System.out.println("Calling Download:- " + fileName);
	  ClassPathResource pdfFile = new ClassPathResource("downloads/" + fileName);
	  HttpHeaders headers = new HttpHeaders();
	  headers.setContentType(MediaType.parseMediaType("application/pdf"));
	  headers.add("Access-Control-Allow-Origin", "*");
	  headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
	  headers.add("Access-Control-Allow-Headers", "Content-Type");
	  headers.add("Content-Disposition", "filename=" + fileName);
	  headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	  headers.add("Pragma", "no-cache");
	  headers.add("Expires", "0");

	  headers.setContentLength(pdfFile.contentLength());
	  ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
	    new InputStreamResource(pdfFile.getInputStream()), headers, HttpStatus.OK);
	  return response;

	 }
	 
	 @RequestMapping("/file/{name}")
	    public ResponseEntity<byte[]> downloadFile(@PathVariable("name") String name) throws IOException, java.io.IOException {
	        System.out.println(name);
	    	String filename = System.getProperty("user.dir")+"/src/main/resources/img/" + name + ".png";
	        InputStream inputImage = new FileInputStream(filename);
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        byte[] buffer = new byte[512];
	        int l = inputImage.read(buffer);
	        while(l >= 0) {
	            outputStream.write(buffer, 0, l);
	            l = inputImage.read(buffer);
	        }
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Content-Type", "image/jpeg");
	        headers.set("Content-Disposition", "attachment; filename=\"" + name + ".png\"");
	        return new ResponseEntity<byte[]>(outputStream.toByteArray(), headers, HttpStatus.OK);
	    }
	    public static void main(String[] args) {
	        System.out.println("Working Directory = " +
	        		System.getProperty("user.dir")+"/src/main/resources/downloads/");
	   }
}
