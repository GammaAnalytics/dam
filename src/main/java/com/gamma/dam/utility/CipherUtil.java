package com.gamma.dam.utility;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

// TODO: Auto-generated Javadoc
/**
 * The Class CipherUtil.
 *
 * @author abhijit
 */
public class CipherUtil {

	/** The secret key. */
    private static String secretKey = "Abhijit";

	/** The hash. */
	private static byte[] hash = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03 };

	/** The iteration count. */
	private static int iterationCount = 19;

	/**
	 * Encrypt.
	 *
	 * @param plainText the plain text
	 * @return the string
	 */
	@SuppressWarnings("restriction")
	public static String encrypt(String plainText) {
		String encStr = null;
		try {
			// Key generation for enc and desc
			KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), hash, iterationCount);
			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
			// Prepare the parameter to the ciphers
			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(hash, iterationCount);

			// Enc process
			Cipher ecipher = Cipher.getInstance(key.getAlgorithm());
			try {
				ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String charSet = "UTF-8";
			byte[] in = plainText.getBytes(charSet);
			byte[] out = ecipher.doFinal(in);
			encStr = new sun.misc.BASE64Encoder().encode(out);
		} catch (IOException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encStr;
	}

	/**
	 * Decrypt.
	 *
	 * @param encryptedText the encrypted text
	 * @return the string
	 */
	@SuppressWarnings("restriction")
	public static String decrypt(String encryptedText) {
		String plainStr = null;
		try {
			KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), hash, iterationCount);
			SecretKey key = null;
			key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
			// Prepare the parameter to the ciphers
			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(hash, iterationCount);
			// Decryption process; same key will be used for decr
			Cipher dcipher = Cipher.getInstance(key.getAlgorithm());
			dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
			byte[] enc = new sun.misc.BASE64Decoder().decodeBuffer(encryptedText);
			byte[] utf8 = null;
			utf8 = dcipher.doFinal(enc);
			String charSet = "UTF-8";
			plainStr = new String(utf8, charSet);
		} catch (IOException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException
				| NoSuchPaddingException | InvalidKeySpecException e) {
			e.printStackTrace();
		}

		return plainStr;
	}
}