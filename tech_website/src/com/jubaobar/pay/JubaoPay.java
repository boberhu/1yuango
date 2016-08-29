package com.jubaobar.pay;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class JubaoPay {

	private String			message;
	private String			signature;
	HashMap<String, String>	encrypts	= new HashMap<String, String>();

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public void setEncrypt(String key, String value) {
		this.encrypts.put(key, value);
	}

	public String getEncrypt(String key) {
		return this.encrypts.get(key);
	}

	public void interpret() {

		try {
			String digest = "";
			String plainString = "";
			Iterator<Entry<String, String>> iter = this.encrypts.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, String> entry = iter.next();
				String field = (String) entry.getKey();
				String value = (String) entry.getValue();

				digest += value;
				plainString += URLEncoder.encode(field, "UTF-8") + "&" + URLEncoder.encode(value, "UTF-8");
				if (iter.hasNext())
					plainString += "&";
			}

			String key = AES.generateRandomString();
			String iv = AES.generateRandomString();

			this.message = RSA.encrypt(key) + RSA.encrypt(iv) + AES.encrypt(plainString, key, iv);
			this.signature = RSA.sign(digest);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public boolean decrypt(String message, String signature) {

		try {
			String key = RSA.decrypt(message.substring(0, 172));
			String iv = RSA.decrypt(message.substring(172, 172 + 172));
			String plainString = AES.decrypt(message.substring(172 + 172), key, iv).trim();

			String digest = "";
			HashMap<String, String> encrypts = new HashMap<String, String>();

			String[] items = plainString.split("&");
			for (int i = 0; i < items.length / 2; i++) {
				String field = URLDecoder.decode(items[2 * i], "UTF-8");
				String value = URLDecoder.decode(items[2 * i + 1], "UTF-8");
				encrypts.put(field, value);
				digest += value;
			}
			this.encrypts = encrypts;
			return RSA.verify(digest, signature);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
