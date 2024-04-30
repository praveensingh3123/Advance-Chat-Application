package group.chatting.application;

import java.util.Base64;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class EncryptionManager  {
	
	private static EncryptionManager encryptionManager = null;
	
	public SecretKey secretKey;
	private static Cipher cipher;
	
	private EncryptionManager() throws Exception {
		String encryptionKey = "Er%Pm_89nNiO$/pqL@#mGxxY0";
		String encryptionScheme = "DESede";
		byte[] arrBytes = encryptionKey.getBytes("UTF8");
		
		DESedeKeySpec ks = new DESedeKeySpec(arrBytes);
		SecretKeyFactory skf = SecretKeyFactory.getInstance(encryptionScheme);
		EncryptionManager.cipher = Cipher.getInstance("DESede");
		
		this.secretKey = skf.generateSecret(ks);
	}
	
	public static synchronized EncryptionManager getInstance() throws Exception {
		if (encryptionManager == null) {
			encryptionManager = new EncryptionManager();
		}
		return encryptionManager;
	}

	 public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
	    	byte[] plainTextByte = plainText.getBytes();
	    	cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	    	byte[] encryptedByte = cipher.doFinal(plainTextByte);
	    	Encoder encoder = Base64.getEncoder();
	    	return encoder.encodeToString(encryptedByte);
	    }
	    
	    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
	    	Base64.Decoder decoder = Base64.getDecoder();
	    	byte[] encryptedTextByte = decoder.decode(encryptedText);
	    	cipher.init(Cipher.DECRYPT_MODE, secretKey);
	    	byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
	    	return new String(decryptedByte);
	    }
}
