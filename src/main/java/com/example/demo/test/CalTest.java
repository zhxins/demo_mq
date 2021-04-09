package com.example.demo.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalTest {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date()));
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.MILLISECOND,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MINUTE,0);
		cal.add(Calendar.HOUR,1);
//		Timestamp ret = new Timestamp(cal.getTime().getTime());
		System.out.println(sdf.format(new Date(cal.getTime().getTime())));

//		nextStoreTs();

		delDate();
	}

	static Timestamp nextStoreTs(){
		int min = 10;
		int h = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.MILLISECOND,0);
		cal.set(Calendar.SECOND,0);
		if (min == 0) {
			cal.set(Calendar.MINUTE, 0);
			cal.add(Calendar.HOUR, 1);
		} else
			cal.add(Calendar.MINUTE,min);

//		cal.add(Calendar.HOUR,1);
		Timestamp ret = new Timestamp(cal.getTime().getTime());
		System.out.println(ret);
		return ret;
	}


	static public void delDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
//        cal.add(cal.DATE, -1* MAX_PCAP_STORE_DAY);  // 数据量太大，由原来的天改为小时
		cal.add(cal.HOUR_OF_DAY, -1* 1);
		Date dt = cal.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(dt));
	}

}
