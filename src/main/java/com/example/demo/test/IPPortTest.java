package com.example.demo.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPPortTest {

	public static boolean isboolIp(String ipAddress) {
		String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		Pattern pattern = Pattern.compile(ip);
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();
	}

	public static boolean isboolPort(String port) {
		//端口号验证 1 ~ 65535
		String regex = "^([1-9]|[1-9]\\d{1,3}|[1-6][0-5][0-5][0-3][0-5])$";
		boolean flag = Pattern.matches(regex, port);
		return  flag;

	}

	public static void main(String[] args) {
		String ip = "192.168.52.562";
		String port = "65539";
		System.out.println(isboolIp(ip));
		System.out.println(isboolPort(port));
	}

}
