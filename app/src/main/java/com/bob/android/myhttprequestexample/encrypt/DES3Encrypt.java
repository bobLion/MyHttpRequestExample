package com.bob.android.myhttprequestexample.encrypt;

import java.security.Key;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;


/**
 * 3DES加密工具类
 * 
 */
public class DES3Encrypt {
	// 向量
	private final static String iv = "0123abcd";
	/**
	 * 3DES加密
	 *
	 * @param secretKey
	 *            密钥，24位
	 * @param plainText
	 *            将加密的字符串
	 * @return
	 *
	 * 		lee on 2017-08-09 10:51:44
	 */
	public static String encode3Des(String secretKey, String plainText) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes("UTF-8"));
		return Base64.encode(encryptData);
	}

	/**
	 * 3DES解密
	 *
	 * @param secretKey
	 *            加密密钥，长度为24字节
	 * @param encryptText
	 *            解密后的字符串
	 * @return
	 *
	 * 		lee on 2017-08-09 10:52:54
	 */
	public static String decode3Des(String secretKey, String encryptText) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

		byte[] decryptData = cipher.doFinal(Base64.decode(encryptText.toCharArray()));
		return new String(decryptData, "UTF-8");
	}

	public static void main(String[] args) {
		String plainText = "工作中遇到了安全传输问题，需要解决iOS和Android客户端跟java服务端的安全传输问题，结合对HTTPS的了解，便使用DES+RSA方式模拟HTTPS。在实现过程中，遇到了一些瓶颈，主要是保持平台兼容性的问题，Android和服务的还可以，统一使用java API，但要包含iOS就比较麻烦了，参考了网上很多资料，忙了三四天，终于搞通了";
		String key = UUID.randomUUID().toString();
		try {
			String encode = DES3Encrypt.encode3Des(key, plainText);
			System.out.println(encode);
			String decode = DES3Encrypt.decode3Des(key, encode);
			System.out.println(decode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
