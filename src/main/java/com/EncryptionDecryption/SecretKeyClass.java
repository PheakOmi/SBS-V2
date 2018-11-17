package com.EncryptionDecryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.core.io.ClassPathResource;

public class SecretKeyClass {
	
    /**
     * gets the AES encryption key. In your actual programs, this should be safely
     * stored.
     * @return
     * @throws Exception 
     */
    public static SecretKey getSecretEncryptionKey(){
        //KeyGenerator generator;
        SecretKey skeySpec;
		try {
			ClassPathResource res = new ClassPathResource("key.key");

			if(res != null)
			{
				File file = res.getFile();
				FileInputStream input = new FileInputStream(file);
				byte[] in = new byte[(int)file.length()];
				input.read(in);
				skeySpec = new SecretKeySpec(in, "AES");
				input.close();
				return skeySpec;
			}
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

    }

}
