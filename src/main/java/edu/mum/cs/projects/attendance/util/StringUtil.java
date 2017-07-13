package edu.mum.cs.projects.attendance.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class StringUtil {
	
	public static String properCase(String name) {
		
		if(null == name) {
			return null;
		}
		
		if(name.trim().isEmpty()) {
			return name.trim();
		}
		
		char[] chars = name.trim().toCharArray();
		char[] result = new char[chars.length];
		
		int index = 0;
		boolean toUpper = true;
		for(char c : chars) {
			if(toUpper) {
				c = Character.toUpperCase(c);
				toUpper = false;
			}
			else {
				c = Character.toLowerCase(c);
			}
			
			if(' ' == c) {
				toUpper = true;
			}
			
			result[index++] = c;
		}
		
		return new String(result);
	}

	public static String getMD5(String msg) {
		String md5 = "";
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(msg.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			md5 = bigInt.toString(16);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return md5;
	}
}
