package com.party.tool;

import java.security.MessageDigest;

public class MD5 {
		
		public static String toMD5(String plainText) {
			String password = null;
			try {
				MessageDigest md=MessageDigest.getInstance("MD5");
				md.update(plainText.getBytes());
				byte b[]=md.digest();
				int i;
				StringBuffer buf=new StringBuffer("");
				for (int offset = 0; offset < b.length; offset++) {
					i=b[offset];
					if (i<0) {
						i+=256;
					}
					if (i<16) {
						buf.append("0");
					}
					
					buf.append(Integer.toHexString(i));
				}
				
				password=buf.toString();
				//System.out.println(password);
				//System.out.println("32位："+buf.toString());
				//System.out.println("16位:"+buf.toString().substring(8,24));
			} catch (Exception e) {
				// TODO: handle exception
			}
			return password;
		}
			
}