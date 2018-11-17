package com.EncryptionDecryption;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.core.io.ClassPathResource;

public class KeyGeneration {
	
	
	
	public static void main(String args[]) throws NoSuchAlgorithmException,
			IOException {
		
		ClassPathResource res = new ClassPathResource("key.key");
		File file = res.getFile();
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128);
		SecureRandom random = new SecureRandom();
		keyGen.init(random);
		SecretKey secretKey = keyGen.generateKey();
		FileOutputStream output = new FileOutputStream(file);
		output.write(secretKey.getEncoded());
		output.close();
		System.out.println("Success \n");

}

}
