package com.example.demo.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadTest {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date()));

		try {
			Thread.sleep(1000 * 60 * 3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(sdf.format(new Date()));
	}
}
