

package com.robert.utils;

import com.robert.utils.security.EncryptionException;
import com.robert.utils.security.StringEncrypter;

public class Encrypter
{

    public Encrypter()
    {
    }

    public static String decrypt(String encryptedText)
        throws EncryptionException
    {
        return encrypter.decrypt(encryptedText);
    }

    public static String encrypt(String clearText)
        throws EncryptionException
    {
        return encrypter.encrypt(clearText);
    }

    private static StringEncrypter encrypter;
    private static String encryptionKey;
    private static String encryptionScheme;

    static 
    {
        encryptionKey = "Hoang Minh Duc: mobile.apps.pro.vn@gmail.com";
        encryptionScheme = "DES";
        encrypter = null;
        try{
        	 encrypter = new StringEncrypter(encryptionScheme, encryptionKey); 	
        }catch(EncryptionException ex){
        	ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
		try {
			String encrypt = Encrypter.encrypt("{\"phone\":\"84989664386\", \"password\":\"123456789\"}");
		
			System.out.println(encrypt);
			System.out.println(Encrypter.decrypt(encrypt));
			
		} catch (EncryptionException e) {
			e.printStackTrace();
		}
	}
}