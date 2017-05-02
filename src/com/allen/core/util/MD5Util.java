package com.allen.core.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.allen.core.common.Const;

/**
 * 
* @ClassName: MD5Util 
* @Description: 加密
* @author wenquan
* @date 2016年5月25日 上午10:19:12 
*
 */
public class MD5Util {


	private static final Integer SALT_LENGTH = 12;
	/**
	 * 获得加密后的16进制形式口令
	 * 
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String getEncryptedPwd(String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// 声明加密后的口令数组变量
		byte[] pwd = null;
		// 声明盐数组变量
		String saltConst = Const.PASSWORD_SALT;
		byte[] salt = new byte[SALT_LENGTH];
		salt = saltConst.getBytes("UTF8");
				
		// 声明消息摘要对象
		MessageDigest md = null;
		// 创建消息摘要
		md = MessageDigest.getInstance("MD5");
		// 将盐数据传入消息摘要对象
		md.update(salt);
		// 将口令的数据传给消息摘要对象
		md.update(password.getBytes("UTF-8"));
		// 获得消息摘要的字节数组
		byte[] digest = md.digest();

		// 因为要在口令的字节数组中存放盐，所以加上盐的字节长度
		pwd = new byte[digest.length + SALT_LENGTH];
		// 将盐的字节拷贝到生成的加密口令字节数组的前12个字节，以便在验证口令时取出盐
		System.arraycopy(salt, 0, pwd, 0, SALT_LENGTH);
		// 将消息摘要拷贝到加密口令字节数组从第13个字节开始的字节
		System.arraycopy(digest, 0, pwd, SALT_LENGTH, digest.length);
		// 将字节数组格式加密后的口令转化为16进制字符串格式的口令
		System.out.println("length:"+pwd.length);
		System.out.println("hexlength:"+byteToHexString(pwd).length());
		System.out.println("byteToHexString(pwd):"+byteToHexString(pwd));
		return byteToHexString(pwd);
	}
	
	public static String getEncryptedPwd(String saltText, String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// 声明加密后的口令数组变量
		byte[] pwd = null;
		// 声明盐数组变量
		String saltConst = saltText;
		int saltLength = saltConst.length();
		byte[] salt = new byte[saltLength];
		salt = saltConst.getBytes("UTF8");
				
		// 声明消息摘要对象
		MessageDigest md = null;
		// 创建消息摘要
		md = MessageDigest.getInstance("MD5");
		// 将盐数据传入消息摘要对象
		md.update(salt);
		// 将口令的数据传给消息摘要对象
		md.update(password.getBytes("UTF-8"));
		// 获得消息摘要的字节数组
		byte[] digest = md.digest();

		// 因为要在口令的字节数组中存放盐，所以加上盐的字节长度
		pwd = new byte[digest.length + saltLength];
		// 将盐的字节拷贝到生成的加密口令字节数组的前12个字节，以便在验证口令时取出盐
		System.arraycopy(salt, 0, pwd, 0, saltLength);
		// 将消息摘要拷贝到加密口令字节数组从第13个字节开始的字节
		System.arraycopy(digest, 0, pwd, saltLength, digest.length);
		// 将字节数组格式加密后的口令转化为16进制字符串格式的口令
		System.out.println("length:"+pwd.length);
		System.out.println("hexlength:"+byteToHexString(pwd).length());
		System.out.println("byteToHexString(pwd):"+byteToHexString(pwd));
		return byteToHexString(pwd);
	}
	
	/**
	 * 将指定byte数组转换成16进制字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byteToHexString(byte[] b) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			hexString.append(hex.toUpperCase());
		}
		return hexString.toString();
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(getEncryptedPwd("123456"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	//313233343536373839303132435BDCA7FFBE969784B28F3C34DC0E35
	//3132333435363738393031321C591510B47B379BFA19B3B7627EF0AE
	
	//31323334353637383930313250108432ECA4D4B76BC0DBCFA9DD64BD
}
