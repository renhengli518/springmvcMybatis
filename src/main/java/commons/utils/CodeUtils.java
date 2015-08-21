package commons.utils;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;

public class CodeUtils {
	
	/**
	 * 将字节编码为Base64字符串
	 * @param bytes
	 * @return
	 */
	public static String encodeBase64(byte[] bytes){
		return Base64.encodeBase64String(bytes);
	}
	
	/**
	 * 将字节编码为Base64字符串, 同时可以指定是否分割
	 * @param bytes
	 * @param isChunked 为true时, 编码后的字符串每隔76个字符, 使用\r\n进行一次分割
	 * @return
	 */
	public static String encodeBase64(byte[] bytes, boolean isChunked){
		return StringUtils.newStringUtf8(Base64.encodeBase64(bytes, isChunked));
	}
	
	public static String encodeBase64(InputStream input){
		throw new RuntimeException("Not implement");
		//TODO:implements it
	}
	
	public static String encodeBase64(InputStream input, boolean isChunked){
		throw new RuntimeException("Not implement");
		//TODO:implements it
	}
	
	public static void encodeBase64(InputStream input, OutputStream output){
		throw new RuntimeException("Not implement");
		//TODO:implements it
	}
	
	public static void encodeBase64(InputStream input, OutputStream output, boolean isChunked){
		throw new RuntimeException("Not implement");
		//TODO:implements it
	}
	
	/**
	 * 将Base64字符串转换为字节流
	 * @param base64
	 * @return
	 */
	public static byte[] decodeBase64(String base64){
		return Base64.decodeBase64(base64);
	}
	
	public static byte[] decodeBase64(InputStream input){
		throw new RuntimeException("Not implement");
		//TODO:implements it
	}
	
	public static void decodeBase64(InputStream input, OutputStream output){
		throw new RuntimeException("Not implement");
		//TODO:implements it 
	}
	
	
	
	public static char[] encodeHex(byte[] bytes){
		return Hex.encodeHex(bytes);
	}
	
	public static String encodeHexString(byte[] bytes){
		return Hex.encodeHexString(bytes);
	}
	
	public static byte[] decodeHex(String base64) throws DecoderException{
		return Hex.decodeHex(base64.toCharArray());
	}
	
	public static byte[] decodeHex(char[] chars) throws DecoderException{
		return Hex.decodeHex(chars);
	}
}
