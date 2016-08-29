package com.jubaobar.pay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class RSA {

	private static String	MESSAGE_ALGORITHM	= "RSA";
	private static String	SIGNATURE_ALGORITHM	= "SHA1withRSA";
	private static String	PSW					= "123456";
	public static String	PRIVATE_KEY			= "";
	public static String	PUBLIC_KEY			= "";

	public static void intialize() {
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(Thread.currentThread().getContextClassLoader()
					.getResource("jubaopay.ini"));
			Element root = document.getRootElement();
			SIGNATURE_ALGORITHM = root.element("algo").getText();
			PSW = root.element("psw").getText();
			String cer = root.element("cer").getText();
			String pfx = root.element("pfx").getText();

			BufferedReader privateKey = new BufferedReader(new InputStreamReader(Thread.currentThread()
					.getContextClassLoader().getResourceAsStream(pfx)));
			PRIVATE_KEY = "";
			String line = "";
			while ((line = privateKey.readLine()) != null) {
				PRIVATE_KEY += line;
			}
			privateKey.close();
			PRIVATE_KEY = PRIVATE_KEY.replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----",
					"");
			PRIVATE_KEY = PRIVATE_KEY.replace("-----BEGIN RSA PRIVATE KEY-----", "").replace(
					"-----END RSA PRIVATE KEY-----", "");
			BufferedReader publicKey = new BufferedReader(new InputStreamReader(Thread.currentThread()
					.getContextClassLoader().getResourceAsStream(cer)));
			PUBLIC_KEY = "";
			while ((line = publicKey.readLine()) != null) {
				PUBLIC_KEY += line;
			}
			publicKey.close();

			PUBLIC_KEY = PUBLIC_KEY.replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static PrivateKey convertToPrivateKey(String privKeyString) {
		try {
			byte[] privKeyByte = Base64.decodeBase64(privKeyString);
			PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(privKeyByte);
			KeyFactory kf = KeyFactory.getInstance(MESSAGE_ALGORITHM);
			return kf.generatePrivate(privKeySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static PublicKey convertToPublicKey(String pubKeyString) {
		try {
			byte[] pubKeyByte = Base64.decodeBase64(pubKeyString);
			X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(pubKeyByte);
			KeyFactory kf = KeyFactory.getInstance(MESSAGE_ALGORITHM);
			return kf.generatePublic(pubKeySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String encrypt(String plainText) {

		try {
			PublicKey pubKey = convertToPublicKey(PUBLIC_KEY);
			Cipher cipher = Cipher.getInstance(MESSAGE_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			byte[] cipherByte = cipher.doFinal(plainText.getBytes("utf-8"));
			return new String(Base64.encodeBase64(cipherByte));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String sign(String plainText) {
		try {

			PrivateKey privKey = convertToPrivateKey(PRIVATE_KEY);
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initSign(privKey);
			String digest = plainText + PSW;
			signature.update(digest.getBytes("utf-8"));
			byte[] signedByte = signature.sign();
			return new String(Base64.encodeBase64(signedByte));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String cipherText) {

		try {

			PrivateKey privKey = convertToPrivateKey(PRIVATE_KEY);
			Cipher cipher = Cipher.getInstance(MESSAGE_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, privKey);
			byte[] plainByte = cipher.doFinal(Base64.decodeBase64(cipherText.getBytes("utf-8")));
			return new String(plainByte);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public static boolean verify(String plainText, String signature) {

		try {

			PublicKey pubKey = convertToPublicKey(PUBLIC_KEY);
			Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
			sig.initVerify(pubKey);
			String digest = plainText + PSW;
			sig.update(digest.getBytes("utf-8"));
			return sig.verify(Base64.decodeBase64(signature));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		String s = "MIICXwIBAAKBgQDzCguBI61iKQJ+wtjZRrCi5trzut4OcaT0VnYo4bcCBAzck9bU"
				+ "v9GtgPzr/m+GsW0lR2cxsTUXbk+gyeZrR4Dc3zwLLa9YBvZQuY57aYxvNXTAm1Ii"
				+ "6AHiAPn19WTxAIjEKkaBTwso/c36+JqOlzEULuoMApww+IVoKZjz73x5EQIDAQAB"
				+ "AoGBAM6n5exjF9T1pgd/SsBF2YBK6DaC2LpTa73Pnx1YADMYmo8crnmsuW/c1DMe"
				+ "4FoZmAMw2dBpLAnGZLMFuoXSAQEmJIqXOQc1VP9KUP+8d7w+8TU/rQGcoS+vzNfb"
				+ "8QA4KI3uOCJqBjBwe76SZJdWk3rhG5rcZBcF8JLuwu7LtwQBAkEA+ueglKDy+mgR"
				+ "IyD9NjXDUw9yf940gZnW2GzcR69FdEzxgcw1uFQfeYyTrVZPxUMBRS15fPsrFHkT"
				+ "CKccuNWTIQJBAPf5hvY0OmghL6sUoNKaPpecBISbL1KhbeB9HXXYA27dqnCDs1+f"
				+ "Qcn0EJjhP3noL97NByI7GvVVEYTFWJyVF/ECQQCkuQDx0yDySPRHTdcviehsVY3k"
				+ "aOur0mX8vKt53JYKnqFHh9wU4hWv5RznBe1lInJpCkviQ7uquzpFygDPUFhBAkEA"
				+ "h7vlPSUPh2oK7qlm8oCNnG1BJ9jOCXvlagjyNEwebPQW1GmyqLfCyim3YAeDuFX4"
				+ "xgsSSZFyyzFaWoOvYl5V8QJBAL4gFAQWkB9qIa9pM0RQv79HpNTfMLxHXWGmTwC0"
				+ "JeBU6+Yl9AXe7RFY55yp+14PgIcCc+93KYICKboZpbS4y4Y=";
		RSA.convertToPrivateKey(s);
	}

}
