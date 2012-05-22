package com.eastcode.utils;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class FileWriteUtil {

	/**
	 * 将内容写入到文件中
	 * 
	 * @param fileName
	 * @param content
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 *             May 24, 2009 yuan
	 */
	public static boolean writefile(File file, String content) {
		try {
			FileWriter filewriter = new FileWriter(file, true);
			filewriter.write(content);
			filewriter.close();

		} catch (Exception e) {
			System.out.println("writefile   Exception:" + e.getMessage());
		}
		return true;
	}

}
