package com.EncryptionDecryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import sun.misc.BASE64Encoder;

public class Encryption {

	 public String encryptText(String plainText,SecretKey secKey) throws Exception{
			// AES defaults to AES/ECB/PKCS5Padding in Java 7
	        Cipher aesCipher = Cipher.getInstance("AES");
	        aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
	        byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
	        String dataEncrypted = new BASE64Encoder().encode(byteCipherText);
	        return dataEncrypted;
	    }
	 public String PasswordEncode(String pass){
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String cyper_pass = passwordEncoder.encode(pass);
			return cyper_pass;
		}
	    
}
