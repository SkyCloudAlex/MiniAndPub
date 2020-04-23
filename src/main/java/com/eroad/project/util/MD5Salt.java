package com.eroad.project.util;

import java.security.MessageDigest;



public class MD5Salt {

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d","e", "f" };
	private Object salt;
	private String algorithm;

	public MD5Salt(Object salt, String algorithm) {
		this.salt = salt;
		this.algorithm = algorithm;
	}

	public String encode(String rawPass) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			// 加密后的字符串
			result = byteArrayToHexString(md.digest(mergePasswordAndSalt(rawPass).getBytes("utf-8")));
		} catch (Exception ex) {
		}
		return result;
	}

	public boolean isPasswordValid(String encPass, String rawPass) {
		String pass1 = "" + encPass;
		String pass2 = encode(rawPass);
		return pass1.equals(pass2);
	}

	private String mergePasswordAndSalt(String password) {
		if (password == null) {
			password = "";
		}

		if ((salt == null) || "".equals(salt)) {
			return password;
		} else {
			return password + "{" + salt.toString() + "}";
		}
	}

	/**
	 * 转换字节数组为16进制字串
	 * @param b
	 * 字节数组
	 * @return 16进制字串
	 */
	private static String byteArrayToHexString(byte[] b) {

		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String encryptSalt(String loginName, String pwd) {
		MD5Salt encoderMd5 = new MD5Salt(loginName, "MD5");
		String encode = encoderMd5.encode(loginName);

		MD5Salt encoderSha = new MD5Salt(loginName, "SHA");

		char[] srtbyte = encode.toCharArray();
		char[] pwdbyte = pwd.toCharArray();
		int a = 0;
	 	StringBuilder  sb = new StringBuilder (pwd);
		for (int i = 0; i < pwdbyte.length; i++) {
			if(i%2==0){
				a++;
				if(a<=4){
					sb.insert(i, srtbyte[i]);
				}
			}
		}

		String shapwd = encoderSha.encode(sb.toString());
		if (encoderSha.isPasswordValid(shapwd, sb.toString()))
			return shapwd;
		else
			return pwd;

	}
	
	

}