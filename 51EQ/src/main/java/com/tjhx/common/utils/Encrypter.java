package com.tjhx.common.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springside.modules.utils.Encodes;

/**
 * 加密者
 */
public final class Encrypter {
	private static Cipher ecipher;

	private static Cipher dcipher;

	//必须24个字符
	private static final String key = "*:@1$7!a*:@1$7!a*:@1$7!a";

	private static final String alg = "DESede";

	static {
		try {

			SecretKey skey = new SecretKeySpec(key.getBytes(), alg);

			ecipher = Cipher.getInstance(alg);
			dcipher = Cipher.getInstance(alg);
			ecipher.init(Cipher.ENCRYPT_MODE, skey);
			dcipher.init(Cipher.DECRYPT_MODE, skey);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加密字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String encrypt(String str) {
		if (str == null)
			return "";
		try {
			// Encode the string into bytes using utf-8
			byte[] utf8 = str.getBytes("UTF8");

			// Encrypt
			byte[] enc = ecipher.doFinal(utf8);

			// Encode bytes to base64 to get a string
			return Encodes.encodeUrlSafeBase64(enc);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * 解密字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String decrypt(String str) {
		if (str == null)
			return "";
		try {
			// Decode base64 to get bytes
			byte[] dec = Encodes.decodeBase64(str);

			// Decrypt
			byte[] utf8 = dcipher.doFinal(dec);

			// Decode using utf-8
			return new String(utf8, "UTF8");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}
	
	public static void main(String[] args) {
//		String aa="1234567890123456";
//		System.out.println(aa.length());
//		System.out.println(encrypt(aa).length());
//		
//		
//		String bb="abcdefghtjkmnopq";
//		System.out.println(bb.length());
//		System.out.println(encrypt(bb).length());
		
		String cc=	"_SxtJ9X1_ij9LG0n1fX-KOI2KKohZA8q";
		System.out.println(cc.length());
		System.out.println(decrypt(cc).length());
		System.out.println(decrypt(cc));
	}
	

}
