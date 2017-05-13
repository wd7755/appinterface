package cn.yunlianhui.appinterface;

/**
 * 加密和解密
 *
 */
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
	// 加密
	public static String Encrypt(String sSrc) throws Exception {
		return Encrypt(sSrc, UrlConstants.PUBLICKEY);
	}

	// 加密
	public static String Encrypt(String sSrc, String sKey) throws Exception {
		if (sKey == null) {
			System.out.print("Key为空null");
			return null;
		}
		// 判断Key是否为16位
		if (sKey.length() != 16) {
			System.out.print("Key长度不是16位");
			return null;
		}
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
		// Cipher cipher =
		// Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
		IvParameterSpec iv = new IvParameterSpec(
				UrlConstants.PUBLICIV.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度

		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		// cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes("UTF-8"));
		// String data = String.valueOf(arr);
		// return new String(Base64.decode(encrypted,Base64.DEFAULT));
		return new String(Base64.encodeToString(encrypted, Base64.DEFAULT));
	}

	public static String Decrypt(String sSrc) throws Exception {
		return Decrypt(sSrc, UrlConstants.PUBLICKEY);
	}

	// 解密
	public static String Decrypt(String sSrc, String sKey) throws Exception {
		try {
			// 判断Key是否正确
			if (sKey == null) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (sKey.length() != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}
			byte[] raw = sKey.getBytes("UTF-8");
			byte[] bytes = new byte[16];
			for (int i = 0; i < raw.length; i++) {
				bytes[i] = raw[i];
			}
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(
					UrlConstants.PUBLICIV.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = Base64.decode(sSrc.getBytes(), Base64.DEFAULT);
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original);
				return originalString;
				//return decodeUnicode(originalString);
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

	private static String decodeUnicode(String theString) {
		
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
							case '0':
							case '1':
							case '2':
							case '3':
							case '4':
							case '5':
							case '6':
							case '7':
							case '8':
							case '9':
								value = (value << 4) + aChar - '0';
								break;
							case 'a':
							case 'b':
							case 'c':
							case 'd':
							case 'e':
							case 'f':
								value = (value << 4) + 10 + aChar - 'a';
								break;
							case 'A':
							case 'B':
							case 'C':
							case 'D':
							case 'E':
							case 'F':
								value = (value << 4) + 10 + aChar - 'A';
								break;
							default:
								throw new IllegalArgumentException(
										"Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	public static void main(String[] args) throws Exception {

		String sSrc = "{\"module\":\"logging\",\"member_id\":\"\u65b0\u624b\u6559\u7a0b\",\"member_pwd\":\"12345678\"}";
		String value = AESUtil.Encrypt(sSrc);
		System.out.println(value);

		value = "GisyMj6oU/AzVmxHu1bzOmSMr4DgV2Xu93DDfBnypdIV1hZfqnN9dgUnIxrsW0ZX3uzpocunNn2V3fXl0J3AIzq815yyu3Zv5ujFJRygP63e9RGHVe+7bYYiuUcWhuGUeo7LskI99a94ifo4fjiI+KjZFtqULd1nUefHLqYN/bWdPg2cIiGEfzyAFUYxp5c1cdCdu0EK7ORtSTcbn/7jYKTwmpp6LQbi4NyNf0JZzbatJIHDmDiKjPJdA0PnY8iAa9EPpxxCzruaRizgnu7wkuNVVoSxIjK8cIkuP39CDRTiQF2Zzz0rbQ3x4mVw3OyJ2nzxqOqRy1R8M63qALVERTV0+d1fag3oswWjfWH9AcN6WzHYrES5c78eyO74QN0PZv3qShU4JsoCY/UYV0QKP4RL6N+GbttYpoItwMkNzXmv8w5o2nsVkZlMkmzN/NVkJnU4Ooz+C305dGIT6/BqESTE65aM45RBisR9zWw0/st/vk8toRQrVTCobprdWeYShJwoCN+UUb5AuRzvscuZJcepwxT9ae5GPKgF+04I7C39eCeYobeG+Rk0yOVGvPIg7Tmz94c/T64Q7Ur8tJeY8ikpPP+kpfEaBwdw3MM1ocHiT7DScLFK5KLRLXxgy9Dr7nc7bzpl9AglF/zGzNXO1bDT0rFR4oHVnW79jYl6VAckabjSO53iwpg/FrvyG3Xf1DfE29kaxS0es/E3pWk5HsxPc7Fn3PmkpiNsnSWWc89wAhYkEO7f5msDf+wACNcU";

		sSrc = AESUtil.Decrypt(value);
		System.out.println(sSrc);
		 sSrc = decodeUnicode(sSrc);
		 System.out.println(sSrc);
	}

}
