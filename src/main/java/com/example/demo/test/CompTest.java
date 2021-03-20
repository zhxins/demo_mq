package com.example.demo.test;

import com.example.demo.utils.DES3D;
import com.example.demo.utils.ZipUtil;

import java.util.Collections;

/**
 * Created by admins on 2021/2/1.
 */
public class CompTest {
	public static void main(String[] args) {

		try {
			String str="ssssssssss";

			String encryptKey= "123456789012345678901234";
//			String desStr = DES3D.encrypt(ZipUtil.compress(str), encryptKey);

			/*System.out.println("desStr:" + desStr);
			System.out.println("压缩前：" + str.length());
			System.out.println("压缩后：" + ZipUtil.compress(str).length());
			System.out.println("加密后：" + desStr.length());

			String json = ZipUtil.uncompress(DES3D.decrypt(desStr, encryptKey));
			System.out.println("str:" + json);
			System.out.println("str size:" + json.length());*/

			for (int i=0; i< 400; i++) {
				String newStr = String.join("", Collections.nCopies(i+1, str));
				System.out.println("压缩前：" + newStr.length());
				System.out.println("压缩后：" + ZipUtil.compress(newStr).length());
				System.out.println("压缩比：" + Float.valueOf(newStr.length()) / Float.valueOf(ZipUtil.compress(newStr).length()));

//				String desStr = DES3D.encrypt(ZipUtil.compress(newStr), encryptKey);
//				System.out.println("加密后：" + desStr.length());
				System.out.println("=============================");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
