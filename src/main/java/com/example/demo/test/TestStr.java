package com.example.demo.test;

import java.util.Collections;

/**
 * Created by admins on 2021/2/1.
 */
public class TestStr {
	public static void main(String[] args) {
		String str = "安全网络";
		String join = String.join("", Collections.nCopies(1, str));
		System.out.println(join);
	}

}
