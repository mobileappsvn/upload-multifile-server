package com.robert.utils.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

// Referenced classes of package vtv.btsc.utils.security:
//            EncryptionException

public class StringEncrypter {
	public StringEncrypter(String encryptionScheme) throws EncryptionException {
		this(encryptionScheme, "This is a fairly long phrase used to encrypt");
	}

	public StringEncrypter(String encryptionScheme, String encryptionKey)
			throws EncryptionException {
		if (encryptionKey == null)
			throw new IllegalArgumentException("encryption key was null");
		if (encryptionKey.trim().length() < 24)
			throw new IllegalArgumentException(
					"encryption key was less than 24 characters");
		try {
			byte keyAsBytes[] = encryptionKey.getBytes(UNICODE_FORMAT);
			if (encryptionScheme.equals("DESede"))
				keySpec = new DESedeKeySpec(keyAsBytes);
			else if (encryptionScheme.equals("DES"))
				keySpec = new DESKeySpec(keyAsBytes);
			else
				throw new IllegalArgumentException(
						"Encryption scheme not supported: " + encryptionScheme);
			keyFactory = SecretKeyFactory.getInstance(encryptionScheme);

			cipher = Cipher.getInstance(encryptionScheme);
		} catch (InvalidKeyException e) {
			throw new EncryptionException(e);
		} catch (UnsupportedEncodingException e) {
			throw new EncryptionException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new EncryptionException(e);
		} catch (NoSuchPaddingException e) {
			throw new EncryptionException(e);
		}
	}

	/**
	 * can xem lai
	 */
	public String encrypt(String unencryptedString) throws EncryptionException {
		if (unencryptedString == null || unencryptedString.trim().length() == 0)
			throw new IllegalArgumentException(
					"unencrypted string was null or empty");
		byte ciphertext[];
		BASE64Encoder base64encoder;
		try {
			javax.crypto.SecretKey key = keyFactory.generateSecret(keySpec);
			cipher.init(1, key);
			byte cleartext[] = unencryptedString.getBytes(UNICODE_FORMAT);
			ciphertext = cipher.doFinal(cleartext);
			base64encoder = new BASE64Encoder();
			return base64encoder.encode(ciphertext);
		} catch (Exception e) {
			throw new EncryptionException(e);
		}

		// Exception e;
		// e;
		// throw new EncryptionException(e);
	}

	public String decrypt(String encryptedString) throws EncryptionException {
		if (encryptedString == null || encryptedString.trim().length() <= 0)
			throw new IllegalArgumentException(
					"encrypted string was null or empty");
		byte ciphertext[];
		try {
			javax.crypto.SecretKey key = keyFactory.generateSecret(keySpec);
			cipher.init(2, key);
			BASE64Decoder base64decoder = new BASE64Decoder();
			byte cleartext[] = base64decoder.decodeBuffer(encryptedString);
			ciphertext = cipher.doFinal(cleartext);
			return bytes2String(ciphertext);
		} catch (Exception e) {
			throw new EncryptionException(e);
		}
	}

	private static String bytes2String(byte bytes[]) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++)
			stringBuffer.append((char) bytes[i]);

		return stringBuffer.toString();
	}

	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";

	public static final String DES_ENCRYPTION_SCHEME = "DES";

	public static final String DEFAULT_ENCRYPTION_KEY = "This is a fairly long phrase used to encrypt";

	private KeySpec keySpec;

	private SecretKeyFactory keyFactory;

	private Cipher cipher;

	private static final String UNICODE_FORMAT = "UTF8";
}