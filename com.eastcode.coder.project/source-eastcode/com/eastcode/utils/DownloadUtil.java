package com.eastcode.utils;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 文件下载工具类
 * 
 * @author 王一进
 *
 */
public class DownloadUtil {

	/**
	 * 从给定的URL中下载文件，并将文件保存为制定的名称
	 * 
	 * @param url
	 * @param fileName
	 */
	public static void download(String url, String fileName) {
		try {
			HttpURLConnection http = (HttpURLConnection) new URL(url).openConnection();
			// 设置连接属性
			http.setConnectTimeout(10000);
			http.setDoOutput(true);
			http.setRequestMethod("GET");
			http.setUseCaches(false);

			InputStream inputStream = new BufferedInputStream(http.getInputStream());

			// 创建输出流，保存文件
			BufferedWriter file = new BufferedWriter(new FileWriter(fileName));
			int ch;
			// 复制文件
			while ((ch = inputStream.read()) != -1) {
				file.write(ch);
			}
			inputStream.close();
			file.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
