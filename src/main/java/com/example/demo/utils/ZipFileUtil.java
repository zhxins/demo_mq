package com.example.demo.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 压缩文件测试
 */
public class ZipFileUtil {
	private String zipFileName;      // 压缩到的地方（目录）
	private String sourceFileName;   //源文件（带压缩的文件或文件夹）

	public ZipFileUtil(String zipFileName,String sourceFileName)
	{
		this.zipFileName=zipFileName;
		this.sourceFileName=sourceFileName;
	}

	public void zip() throws Exception
	{
		long start = System.currentTimeMillis();
		//File zipFile = new File(zipFileName);
		System.out.println("压缩中...");

		//创建zip输出流
		ZipOutputStream out = new ZipOutputStream( new FileOutputStream(zipFileName));

		File sourceFile = new File(sourceFileName);

		//调用函数
		compress(out,sourceFile,sourceFile.getName());

		out.close();
		System.out.println("压缩完成");
		System.out.println(System.currentTimeMillis() - start);

	}

	public void compress(ZipOutputStream out,File sourceFile,String base) throws Exception
	{

		out.putNextEntry( new ZipEntry(base) );
		FileInputStream fos = new FileInputStream(sourceFile);
		BufferedInputStream bis = new BufferedInputStream(fos);

		int tag;
		System.out.println(base);
		//将源文件写入到zip文件中
		while((tag=bis.read())!=-1)
		{
			out.write(tag);
		}
		bis.close();
		fos.close();

	}

	public void unzip(File file,String unzippath) throws IOException {
		ZipFile zf = null;
		try {
			zf = new ZipFile(file, Charset.forName("GBK"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Enumeration entries = zf.entries();
		ZipEntry entry = null;
		while (entries.hasMoreElements()) {
			entry = (ZipEntry) entries.nextElement();
			System.out.println("解压" + entry.getName());
			if (entry.isDirectory()) {
				String dirPath = unzippath + File.separator + entry.getName();
				File dir = new File(dirPath);
				dir.mkdirs();
			} else {
				InputStream is = zf.getInputStream(entry);
				// 表示文件
				File f = new File(unzippath + File.separator + entry.getName());
				//文件名
				String filename= f.getName();
				if (!f.exists()) {
					File fileParent = f.getParentFile();
					if(!fileParent.exists()){
						fileParent.mkdirs();
					}
				}
				f.createNewFile();
				// 将压缩文件内容写入到这个文件中
				FileOutputStream fos = new FileOutputStream(f);
				int count;
				byte[] buf = new byte[8192];
				while ((count = is.read(buf)) != -1) {
					fos.write(buf, 0, count);
				}
				fos.flush();
				fos.close();
				is.close();

			}
		}
		zf.close();
	}

	public static void main(String[] args) throws Exception {
		ZipFileUtil zipFileUtil = new ZipFileUtil("F:\\test\\173_3_20210409100608.zip", "F:\\test\\173_3_20210409100608.pcap");
		//压缩
		zipFileUtil.zip();
		//解压
		zipFileUtil.unzip(new File("F:\\test\\173_3_20210409100608.zip"), "F:\\test\\173_3_test.pcap");
	}
}
