package com.example.demo.test;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.DecimalFormat;

public class FileTest {

	public static void main(String[] args) {
		File file = new File("F:\\mp3");
		System.out.println(file.getFreeSpace());
		System.out.println(file.getTotalSpace());

		long size = FileUtils.sizeOfDirectory(new File("F:\\mp3"));
		System.out.println("Size: " + size + " bytes");
		System.out.println(getNetFileSizeDescription(size));
	}


	public static String getNetFileSizeDescription(long bytes) {

		if (bytes == 0) return "0B";
		final String[] units = new String[]{"B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};
		int digitGroups = (int) (Math.log10(bytes) / Math.log10(1024));
		return new DecimalFormat("#,##0.##").format(bytes / Math.pow(1024, digitGroups)) + "" + units[digitGroups];

	}
}
