package com.thinkgem.jeesite.common.xstream;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5
 *
 * @author levis
 * @version 1.0 13-5-17
 */
public class MD5 {

	/**
	 * sign
	 *
	 * @param text
	 * @return
	 */
	public static String sign(String text) {
		return DigestUtils.md5Hex(text);
	}

	/**
	 * sign
	 *
	 * @param text
	 * @param charset
	 * @return
	 */
	public static String sign(String text, String charset) {
		return DigestUtils.md5Hex(StringUtils.getBytes(text, charset));
	}

	/**
	 * verify
	 *
	 * @param text
	 * @param sign
	 * @return
	 */
	public static boolean verify(String text, String sign) {
		String mySign = DigestUtils.md5Hex(text);
		if (mySign.equals(sign)) {
			return true;
		}
		return false;
	}

	/**
	 * verify
	 *
	 * @param text
	 * @param sign
	 * @param charset
	 * @return
	 */
	public static boolean verify(String text, String sign, String charset) {
		try {
			String mySign = DigestUtils.md5Hex(text.getBytes(charset));
			if (mySign.equals(sign)) {
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}
