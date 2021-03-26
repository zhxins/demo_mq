package com.example.demo.test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test2 {
	public static void main(String[] args) {

		SimpleDateFormat dFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<String> fileNameDate = new ArrayList<>();
		String path = "E:\\workspace\\work_idea\\git\\apm\\data\\pcapfiles-bak\\";
		File file = new File(path);
		if (file.isDirectory()) {
			String[] filelist = file.list();
			for (String fileName : filelist) {
				String[] fileNameArr = fileName.replace(".pcap", "").split("_");
				fileNameDate.add(fileNameArr[2]);
			}
		}

		Collections.sort(fileNameDate);

		try {
			String start = df.format(dFormat.parse(fileNameDate.get(0)));
			String end = df.format(dFormat.parse(fileNameDate.get(fileNameDate.size() - 1)));

			System.out.println(start);
			System.out.println(end);

			System.out.println(end.compareTo(start));
			System.out.println("4".compareTo("2"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
