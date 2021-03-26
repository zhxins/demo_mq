package com.example.demo.sql;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadSql {

	public static void main(String[] args) {
		new ReadSql().test();
	}

	public void test() {

//		File file = new File("F:\\test\\t_asset.sql");
		File file = new File("F:\\test\\h_if_flux.sql");
//		File file = new File("F:\\test\\h_if.sql");

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String content = null;
			int num = 0;
			while ((content = reader.readLine()) != null) {

				if (content.contains("INSERT")) {
					String subStr = content.substring(content.indexOf('(')).replace('(',' ').
							replace(')', ' ').replace(';', ' ');

					String[] strings = subStr.trim().split(",");

					System.out.println(JSON.toJSON(strings));


					num ++;
				}

			}
			System.out.println(num);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
}
