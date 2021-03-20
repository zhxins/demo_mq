package com.example.demo.comp2;

import java.io.IOException;

/**
 * Created by admins on 2021/2/1.
 */
public class Test {
	public static void main(String[] args) {

		String test="wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwsssssssssssssssssssssssssssssssssssss";
		System.out.println("压缩前长度" + test.length());
		try{
			String afterCompressStr = CompressUtils.compress(test);
			System.out.println("压缩后长度" + afterCompressStr.length());

			// 加密
			String afterEncryptStr = AESUtils.encrypt(afterCompressStr);
			System.out.println("先压缩后加密的长度" + afterEncryptStr.length());

			// 加密
			String _afterEncryptStr = AESUtils.encrypt(test);
			String _afterCompressStr = CompressUtils.compress(_afterEncryptStr);
			System.out.println("先加密后压缩的长度" + _afterCompressStr.length());

		}catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
}
