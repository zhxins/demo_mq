/**
 * 
 */
package com.example.demo.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 功能：3DES加密解密工具包 提供对称加密
 * 
 * 
 *         mobile enterprise application platform Version 0.1
 */
public class DES3D {

	// 向量
	private final static String iv = "01234567";

	/**
	 * 3DES加密
	 * 
	 * @param plainText
	 *            普通文本
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String plainText, String key) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes("utf-8"));
		return ConvertUtil.convertEncodeBase64(encryptData);
	}

	/**
	 * 3DES解密
	 * 
	 * @param encryptText
	 *            加密文本
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String encryptText, String key)
			throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

		byte[] decryptData = cipher.doFinal(ConvertUtil
				.convertDecodeBase64(encryptText));

		return new String(decryptData, "utf-8");
	}

}
