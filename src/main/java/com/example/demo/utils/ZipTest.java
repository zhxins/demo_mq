package com.example.demo.utils;

import org.apache.commons.io.IOUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * zip压缩精简版
 */
public class ZipTest {

	public static void main(String[] args) {

		String srcPath = "F:\\test\\FileZip\\173_3_20210409100608.pcap";
		String zipPath = "";

		File srcFile = new File(srcPath);
		if (srcFile.exists()) {
			String path = srcFile.getPath();
			String name = srcFile.getName();

			zipPath = path.replace("pcap", "zip");
			compressZip(zipPath, srcPath);
		}


	}

	public static void compressZip(String zipPath, String srcPath) {

		try {
			long start = System.currentTimeMillis();
			File srcFile = new File(srcPath);
			ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(new File(zipPath)));
			zipOut.setEncoding("UTF-8");// 设置编码
			FileInputStream in = new FileInputStream(srcFile);
			zipOut.putNextEntry(new ZipEntry(srcFile.getName()));
			IOUtils.copy(in, zipOut);
			in.close();
			zipOut.closeEntry();
			zipOut.close();
			System.out.println(System.currentTimeMillis() - start);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
