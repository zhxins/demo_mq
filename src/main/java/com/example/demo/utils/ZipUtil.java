package com.example.demo.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


/**
 * 
 * 功能：zip压缩通用类
 * 
 * @author yubaojian0616@163.com
 * 
 *         mobile enterprise application platform Version 0.1
 */
public class ZipUtil {

	/**
	 * 压缩字符串
	 * 
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static String compress(String str) {
		if (ValidateUtil.isNull(str)) {
			return str;
		}
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(out);
			gzip.write(str.getBytes("gbk"));
			gzip.close();
			return out.toString("ISO-8859-1");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 解压缩字符串
	 * 
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static String uncompress(String str) {
		if (ValidateUtil.isNull(str)) {
			return str;
		}
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ByteArrayInputStream in = new ByteArrayInputStream(
					str.getBytes("ISO-8859-1"));
			GZIPInputStream gunzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n;
			while ((n = gunzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
			return out.toString("gbk");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 解压缩zip
	 * 
	 * @param 要解压缩的文件列表
	 * @param 解压缩到的路径
	 * @return
	 * @throws IOException
	 */
	public static boolean unzip(String[] paramArrayOfString, String paramString)
			throws IOException {
		if (paramArrayOfString == null)
			return false;
		ZipInputStream localZipInputStream = null;
		FileInputStream localFileInputStream = null;
		CheckedInputStream localCheckedInputStream = null;
		byte[] arrayOfByte = new byte[1024];
		FileOutputStream localFileOutputStream = null;
		try {
			File localFile1 = new File(paramString);
			for (int i = 0; i < paramArrayOfString.length; ++i) {
				localFileInputStream = new FileInputStream(
						paramArrayOfString[i]);
				localCheckedInputStream = new CheckedInputStream(
						localFileInputStream, new Adler32());
				localZipInputStream = new ZipInputStream(
						localCheckedInputStream);
				ZipEntry localZipEntry = null;
				while ((localZipEntry = localZipInputStream.getNextEntry()) != null) {
					File localFile2 = new File(localFile1,
							localZipEntry.getName());
					if (localZipEntry.isDirectory()) {
						if (!(localFile2.mkdirs()))
							localFileOutputStream.close();
						throw new IllegalArgumentException("Can't make dir:"
								+ localFile2);
					}
					if (!(localFile2.getParentFile().exists()))
						localFile2.getParentFile().mkdirs();
					localFileOutputStream = new FileOutputStream(localFile2);
					int j = 0;
					while ((j = localZipInputStream.read(arrayOfByte)) != -1)
						localFileOutputStream.write(arrayOfByte, 0, j);
				}
				localFileOutputStream.close();
			}

			return true;
		} finally {
			try {
				if (localFileOutputStream != null)
					localFileOutputStream.close();
			} catch (Exception localException1) {
			}
			try {
				if (localZipInputStream != null)
					localZipInputStream.close();
				if (localCheckedInputStream != null)
					localCheckedInputStream.close();
				if (localFileInputStream != null)
					localFileInputStream.close();
			} catch (Exception localException2) {
				localException2.printStackTrace();
			}
		}
	}
}