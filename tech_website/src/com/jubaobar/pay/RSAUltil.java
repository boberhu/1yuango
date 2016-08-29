package com.jubaobar.pay;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class RSAUltil {

	private static final Logger	log					= Logger.getLogger(RSAUltil.class);

	private static String		MESSAGE_ALGORITHM	= "RSA";
	private static String		SIGNATURE_ALGORITHM	= "SHA1withRSA";

	private static PrivateKey convertToPrivateKey(String privKeyString) {
		try {
			byte[] privKeyByte = Base64.decodeBase64(privKeyString);
			PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(privKeyByte);
			KeyFactory kf = KeyFactory.getInstance(MESSAGE_ALGORITHM);
			return kf.generatePrivate(privKeySpec);
		} catch (Exception e) {
			log.error(e);
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
			log.error(e);
		}
		return null;
	}

	public static String encrypt(String pubKeyString, String plainText) {

		try {
			PublicKey pubKey = convertToPublicKey(pubKeyString);
			Cipher cipher = Cipher.getInstance(MESSAGE_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			byte[] cipherByte = cipher.doFinal(plainText.getBytes("UTF-8"));
			return new String(Base64.encodeBase64(cipherByte));
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	public static String sign(String privKeyString, String psw, String plainText) {
		try {

			PrivateKey privKey = convertToPrivateKey(privKeyString);
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initSign(privKey);
			String digest = plainText + psw;
			signature.update(digest.getBytes("UTF-8"));
			byte[] signedByte = signature.sign();
			return new String(Base64.encodeBase64(signedByte));

		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	public static String decrypt(String privKeyString, String cipherText) {

		try {

			PrivateKey privKey = convertToPrivateKey(privKeyString);
			Cipher cipher = Cipher.getInstance(MESSAGE_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, privKey);
			byte[] plainByte = cipher.doFinal(Base64.decodeBase64(cipherText.getBytes("UTF-8")));
			return new String(plainByte);

		} catch (Exception e) {
			log.error(e);
		}
		return null;

	}

	public static boolean verify(String pubKeyString, String psw, String plainText, String signature) {

		try {

			PublicKey pubKey = convertToPublicKey(pubKeyString);
			Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
			sig.initVerify(pubKey);
			String digest = plainText + psw;
			sig.update(digest.getBytes("UTF-8"));
			return sig.verify(Base64.decodeBase64(signature));
		} catch (Exception e) {
			log.error(e);
		}
		return false;
	}
}
