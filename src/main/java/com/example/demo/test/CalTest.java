package com.example.demo.test;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class CalTest {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.MILLISECOND,0);
		cal.set(Calendar.SECOND,0);
		cal.add(Calendar.MINUTE,3);
//		cal.set(Calendar.HOUR,0);
		Timestamp ret = new Timestamp(cal.getTime().getTime());
		System.out.println(new Date(ret.getTime()));
	}
}
