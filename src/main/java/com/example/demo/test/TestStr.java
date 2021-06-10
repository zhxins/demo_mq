package com.example.demo.test;

import java.util.Collections;
import java.util.Date;

/**
 * Created by admins on 2021/2/1.
 */
public class TestStr {
	public static void main(String[] args) throws InterruptedException {
		System.out.println(new Date());
		Thread.sleep(1000 * 60 * 1);

		String str = "安全网络";
		String join = String.join("", Collections.nCopies(1, str));
		System.out.println(join);

		System.out.println(new Date());
	}

}
