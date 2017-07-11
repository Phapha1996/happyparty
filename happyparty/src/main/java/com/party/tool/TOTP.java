package com.party.tool;

import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class TOTP 
{
	private final static String NUM_CHAR = "0123456789";  
	private static int charLen = NUM_CHAR.length();  


	   /**  
	    * 根据系统时间获得指定位数的随机数  
	    * @param randomNumberDigit 随机数的位数  
	    *  @return  获得的随机数唯一秘钥
	    */  
	   public static String getRandomNumber(int randomNumberDigit) {  
	      long seed = System.currentTimeMillis();// 获得系统时间，作为生成随机数的种子  
	      StringBuffer sb = new StringBuffer();// 装载生成的随机数  
	      Random random = new Random(seed);// 调用种子生成随机数  
	      for (int i = 0; i < randomNumberDigit; i++) {  
	         sb.append(NUM_CHAR.charAt(random.nextInt(charLen)));  
	      }  


	      return sb.toString();  
	   }
/*	public static void main(String[] args) 
	{
		String seed=getRandomNumber(8);
		System.out.println(seed);
		System.out.println(getTOTP("041369"));
	}*/
	   /**  
	    * 根据秘钥获取唯一口令
	    * @param seed 秘钥
	    *  @return  动态口令    60秒生成一次
	    */  
	public static String getTOTP(String seed) {
		long T0 = 0;
		long X = 60;
		Calendar cal = Calendar.getInstance();
		long time = cal.getTimeInMillis() / 1000;
		String steps = "0";
		try {
			long T = (time - T0) / X;
			steps = Long.toHexString(T).toUpperCase();
			while (steps.length() < 16)
				steps = "0" + steps;
			return generateTOTP(seed, steps, "6", "HmacSHA1");
		} catch (final Exception e) {
			System.out.println("Error : " + e);
			return "生成动态口令失败";
		}
	}
	
	/**
	 * This method generates a TOTP value for the given set of parameters.
	 * 
	 * @param key
	 *            : the shared secret, HEX encoded
	 * @param time
	 *            : a value that reflects a time
	 * @param returnDigits
	 *            : number of digits to return
	 * @param crypto
	 *            : the crypto function to use
	 * 
	 * @return: a numeric String in base 10 that includes
	 *          {@link truncationDigits} digits
	 */
	public static String generateTOTP(String key, String time,
			String returnDigits, String crypto) {
		int codeDigits = Integer.decode(returnDigits).intValue();
		String result = null;
		// Using the counter
		// First 8 bytes are for the movingFactor
		// Compliant with base RFC 4226 (HOTP)
		while (time.length() < 16)
			time = "0" + time;
		// Get the HEX in a Byte[]
		byte[] msg = hexStr2Bytes(time);
		byte[] k = hexStr2Bytes(key);

		byte[] hash = hmac_sha(crypto, k, msg);
		// put selected bytes into result int
		int offset = hash[hash.length - 1] & 0xf;
		int binary = ((hash[offset] & 0x7f) << 24)
				| ((hash[offset + 1] & 0xff) << 16)
				| ((hash[offset + 2] & 0xff) << 8) | (hash[offset + 3] & 0xff);
		int otp = binary % DIGITS_POWER[codeDigits];
		result = Integer.toString(otp);
		while (result.length() < codeDigits) {
			result = "0" + result;
		}
		return result;
	}
	
	private static byte[] hmac_sha(String crypto, byte[] keyBytes, byte[] text) {
		try {
			Mac hmac;
			hmac = Mac.getInstance(crypto);
			SecretKeySpec macKey = new SecretKeySpec(keyBytes, "RAW");
			hmac.init(macKey);
			return hmac.doFinal(text);
		} catch (GeneralSecurityException gse) {
			throw new UndeclaredThrowableException(gse);
		}
	}

	/**
	 * This method converts a HEX string to Byte[]
	 * 
	 * @param hex
	 *            : the HEX string
	 * 
	 * @return: a byte array
	 */
	private static byte[] hexStr2Bytes(String hex) {
		// Adding one byte to get the right conversion
		// Values starting with "0" can be converted
		byte[] bArray = new BigInteger("10" + hex, 16).toByteArray();
		// Copy all the REAL bytes, not the "first"
		byte[] ret = new byte[bArray.length - 1];
		for (int i = 0; i < ret.length; i++)
			ret[i] = bArray[i + 1];
		return ret;
	}

	private static final int[] DIGITS_POWER
	// 0 1 2 3 4 5 6 7 8
	= { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000 };

	/**
	 * This method generates a TOTP value for the given set of parameters.
	 * 
	 * @param key
	 *            : the shared secret, HEX encoded
	 * @param time
	 *            : a value that reflects a time
	 * @param returnDigits
	 *            : number of digits to return
	 * 
	 * @return: a numeric String in base 10 that includes
	 *          {@link truncationDigits} digits
	 */
	public static String generateTOTP(String key, String time,
			String returnDigits) {
		return generateTOTP(key, time, returnDigits, "HmacSHA1");
	}

	/**
	 * This method generates a TOTP value for the given set of parameters.
	 * 
	 * @param key
	 *            : the shared secret, HEX encoded
	 * @param time
	 *            : a value that reflects a time
	 * @param returnDigits
	 *            : number of digits to return
	 * 
	 * @return: a numeric String in base 10 that includes
	 *          {@link truncationDigits} digits
	 */
	public static String generateTOTP256(String key, String time,
			String returnDigits) {
		return generateTOTP(key, time, returnDigits, "HmacSHA256");
	}

	/**
	 * This method generates a TOTP value for the given set of parameters.
	 * 
	 * @param key
	 *            : the shared secret, HEX encoded
	 * @param time
	 *            : a value that reflects a time
	 * @param returnDigits
	 *            : number of digits to return
	 * 
	 * @return: a numeric String in base 10 that includes
	 *          {@link truncationDigits} digits
	 */
	public static String generateTOTP512(String key, String time,
			String returnDigits) {
		return generateTOTP(key, time, returnDigits, "HmacSHA512");
	}


}
