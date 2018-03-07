package com.thinkgem.jeesite.common.xstream;

public class Base64 {
	public static String decode(String text)
	  {
	    return new String(org.apache.commons.codec.binary.Base64.decodeBase64(text));
	  }

	  public static String decode(byte[] binaryData) {
	    return new String(org.apache.commons.codec.binary.Base64.decodeBase64(binaryData));
	  }

	  public static byte[] decodeByte(String text) {
	    return org.apache.commons.codec.binary.Base64.decodeBase64(text);
	  }

	  public static byte[] decodeByte(String text, String charset) {
	    return org.apache.commons.codec.binary.Base64.decodeBase64(StringUtils.getBytes(text, charset));
	  }

	  public static String decode(String text, String charset)
	  {
	    return StringUtils.getString(org.apache.commons.codec.binary.Base64.decodeBase64(text), charset);
	  }

	  public static String encode(String text)
	  {
	    return new String(org.apache.commons.codec.binary.Base64.encodeBase64(StringUtils.getBytes(text)));
	  }

	  public static String encode(byte[] binaryData) {
	    return new String(org.apache.commons.codec.binary.Base64.encodeBase64(binaryData));
	  }

	  public static String encode(String text, String charset)
	  {
	    return new String(org.apache.commons.codec.binary.Base64.encodeBase64(StringUtils.getBytes(text, charset)));
	  }

	  public static boolean isBase64(String text)
	  {
	    return org.apache.commons.codec.binary.Base64.isBase64(text);
	  }

	  public static boolean isBase64(byte[] binaryData)
	  {
	    return org.apache.commons.codec.binary.Base64.isBase64(binaryData);
	  }
}
