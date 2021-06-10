package com.example.demo.test;

public class StringTest {

	public static void main(String[] args) {

		String str = "0000:01:00.0";
		String s = str.split("\\.")[1];
		System.out.println(s);

	}
}
