package commons.utils;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class SecurityUtil {
	//--------------------------------------MD5信息摘要方法-------------------------------------
	public static byte[] MD5(String source){
		byte src[] = source.getBytes();
		byte target[] = MD5(src);
		return target;
	}
	public static byte[] MD5(byte source[]){
		try{
			MessageDigest mdInstance = MessageDigest.getInstance("MD5");
			mdInstance.update(source);
			byte mdValue[] = mdInstance.digest();
			return mdValue;
		}catch(NoSuchAlgorithmException e){
			throw new RuntimeException(e);
		}
	}
	
	//对字符串进行加密, 返回加密后的16进制的字符串表示
	public static String MD5String(String source){
		byte md5Bytes[] = MD5(source);
		String result = transferByte(md5Bytes);
		return result;
	}
	
	//对字节数组进行加密, 返回加密后的16进制的字符串表示
	public static String MD5String(byte source[]){
		byte md5Bytes[] = MD5(source);
		String result = transferByte(md5Bytes);
		return result;
	}
	
	//--------------------------------------SHA信息摘要方法-------------------------------------
	public static byte[] SHA(String source){
		byte src[] = source.getBytes();
		byte target[] = SHA(src);
		return target;
	}
	public static byte[] SHA(byte source[]){
		try{
			MessageDigest mdInstance = MessageDigest.getInstance("SHA");
			mdInstance.update(source);
			byte mdValue[] = mdInstance.digest();
			return mdValue;
		}catch(NoSuchAlgorithmException e){
			throw new RuntimeException(e);
		}
	}
	
	//对字符串进行加密, 返回加密后的16进制的字符串表示
	public static String SHAString(String source){
		byte md5Bytes[] = SHA(source);
		String result = transferByte(md5Bytes);
		return result;
	}
	
	//对字节数组进行加密, 返回加密后的16进制的字符串表示
	public static String SHAString(byte source[]){
		byte md5Bytes[] = SHA(source);
		String result = transferByte(md5Bytes);
		return result;
	}
	
	public static byte[] SHA256(byte[] source){
		try{
			MessageDigest mdInstance = MessageDigest.getInstance("SHA-256");
			mdInstance.update(source);
			byte mdValue[] = mdInstance.digest();
			return mdValue;
		}catch(NoSuchAlgorithmException e){
			throw new RuntimeException(e);
		}
	}
	
	public static byte[] SHA256(String source){
		byte[] bytes = source.getBytes();
		return SHA256(bytes);
	}
	
	public static String SHA256String(byte[] source){
		byte[] bytes = SHA256(source);
		String r = transferByte(bytes);
		return r;
	}
	
	public static String SHA256String(String string){
		byte[] bytes = string.getBytes();
		return SHA256String(bytes);
	}
	
	public static byte[] SHA512(byte[] source){
		try{
			MessageDigest mdInstance = MessageDigest.getInstance("SHA-512");
			mdInstance.update(source);
			byte mdValue[] = mdInstance.digest();
			return mdValue;
		}catch(NoSuchAlgorithmException e){
			throw new RuntimeException(e);
		}
	}
	
	public static byte[] SHA512(String source){
		byte[] bytes = source.getBytes();
		return SHA512(bytes);
	}
	
	public static String SHA512String(byte[] source){
		byte[] bytes = SHA512(source);
		String r = transferByte(bytes);
		return r;
	}
	
	public static String SHA512String(String string){
		byte[] bytes = string.getBytes();
		return SHA512String(bytes);
	}
	
	
	
	
	//--------------------------------------AES加密方法-------------------------------------
	public static Key AESKeyGenerator(){
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			Key key = keyGen.generateKey();
			return key;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	public static byte[] encryptAES(Key key, byte[] data){
		try{
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] cipherByte = cipher.doFinal(data);
			return cipherByte;
		} catch (GeneralSecurityException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static byte[] decryptAES(Key key, byte[] cipherData){
		try{
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] bytes = cipher.doFinal(cipherData);
			return bytes;
		} catch (GeneralSecurityException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	
	
	
	//--------------------------------------RSA钥对生成，以及加密解密、签名验证-------------------------------------
	public static KeyPair RSAKeyGenerator(int length){
		try{
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
	        keyPairGen.initialize(length);
	        KeyPair keyPair = keyPairGen.generateKeyPair();
	        return keyPair;
		}catch(NoSuchAlgorithmException e){
			throw new RuntimeException(e);
		}
	}
	
	//生成RSA密码对
	public static KeyPair RSAKeyGenerator(){
		return RSAKeyGenerator(1024);
	}
	
	//用公钥对数据进行加密
	public static byte[] encryptRSA(RSAPublicKey publicKey, byte[] obj) {
        if (publicKey != null){
            try{
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                return cipher.doFinal(obj);
            } catch (GeneralSecurityException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }else
        	throw new IllegalArgumentException("public key is null");
    }

	//用私钥对数据解密
    public static byte[] decryptRSA(RSAPrivateKey privateKey, byte[] obj) {
        if (privateKey != null){
            try{
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, privateKey);
                return cipher.doFinal(obj);
            } catch (GeneralSecurityException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }else{
        	throw new IllegalArgumentException("private key is null");
        }
    }
    
    //用私钥对数据进行数字签名
    public static byte[] signRSA(RSAPrivateKey privateKey, byte[] data){
    	if (privateKey != null){
            try{
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, privateKey);
                return cipher.doFinal(data);
            } catch (GeneralSecurityException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }else
        	throw new IllegalArgumentException("private key is null");
    }
    
    //先提取数据的MD5信息摘要，再对MD5信息摘要进行签名
    public static byte[] signMD5RSA(RSAPrivateKey privateKey, byte[] data){
    	if (privateKey != null){
            try{
            	Signature sign = Signature.getInstance("MD5WithRSA");
            	sign.initSign(privateKey);
            	sign.update(data);
            	byte[] r = sign.sign();
            	return r;
            } catch (GeneralSecurityException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }else
        	throw new IllegalArgumentException("private key is null");
    }
    
    //用公钥对数据进行数字签名的验证，方法只返回用公钥解密后的数据
    //验证过程需要用返回结果进行比对
    public static byte[] verifyRSA(RSAPublicKey publicKey, byte[] signData){
    	if (publicKey != null){
            try{
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, publicKey);
                return cipher.doFinal(signData);
            } catch (GeneralSecurityException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }else
        	throw new IllegalArgumentException("public key is null");
    }
    
    //用公钥对数据进行数字签名的验证,
    //先用公钥解密签名的数据，然后将数据和明文进行比对，返回比对的结果
    public static boolean verifyRSA(RSAPublicKey publicKey, byte[] signData, byte[] plainData){
    	byte[] datas = verifyRSA(publicKey, signData);
    	boolean equalResult = equal(datas, plainData);
    	return equalResult;
    }
    
    //用公钥对数据进行数字签名的验证,被签名的数据是明文的MD5码
    //先用公钥解密签名的数据，然后提取明文的MD5码，将两者进行比对，返回比对的结果
    public static boolean verifyMD5RSA(RSAPublicKey publicKey, byte[] signData, byte[] plainData){
		try {
			Signature sign = Signature.getInstance("MD5WithRSA");
			sign.initVerify(publicKey);
	    	sign.update(plainData);
	    	boolean r = sign.verify(signData);
	    	return r;
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }
    
    private static boolean equal(byte[] data1, byte[] data2){
    	return CompareUtils.equal(data1, data2);
    }
	
	private static String transferByte(byte[] byteArray){
		String str = CodeUtils.encodeHexString(byteArray);
		return str;
	}
	
	/**
	 * 计算coco cipher
	 * 
	 * @return
	 */
	public static String socketMap65Encrypt(String key,String expressly) {
		String assiitable = "an0bo1cp2dq3er4fs5gt6hu7iv8jw9kx.ly_mzABCDEFGHIJKLMNOPQRSTUVWXYZ=";// 对照表
		String randomCode = "an0bo1cp2dq3er4fs5gt6hu7iv8jw9kxlymzABCDEFGHIJKLMNOPQRSTUVWXYZ";// 随机码范围

		// 求偏移量
		String only = String.valueOf(randomCode.toCharArray()[getRandomNum(0, randomCode.length() - 1)]);
		key += only;
		int keyLength = key.length();
		int keyAsciiSum = 0;
		for (int i = 0; i < keyLength; i++) {
			keyAsciiSum += key.charAt(i);
		}
		int offset = keyAsciiSum % 65;
		if (offset == 0) {
			return socketMap65Encrypt(key,expressly);
		}

		// 加密
		int expresslyLength = expressly.length();
		String encryptedString = "";
		for (int j = 0; j < expresslyLength; j++) {
			int cOffset = assiitable.indexOf(expressly.charAt(j));
			int nOffset = cOffset + offset;
			if (nOffset > 64) {
				encryptedString += assiitable.charAt(nOffset - 65);
			} else {
				encryptedString += assiitable.charAt(nOffset);
			}
		}
		return encryptedString + only;
	}
	
	/**
	 * 取随机数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomNum(int min, int max) {
		Random random = new Random();
		return random.nextInt(max) % (max - min + 1) + min;
	}
}
