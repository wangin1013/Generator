package com.eastcode.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

import javax.servlet.http.HttpServletRequest;

public class FileReadUtil {

	/**
	 * 删除某个文件夹下的所有文件夹和文件
	 * 
	 * @param delpath
	 *            String
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return boolean
	 */
	public static boolean deletefile(String delpath)
			throws FileNotFoundException, IOException {
		try {

			File file = new File(delpath);
			if (!file.isDirectory()) {
				System.out.println("1");
				file.delete();
			} else if (file.isDirectory()) {
				System.out.println("2");
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File delfile = new File(delpath + "\\" + filelist[i]);
					if (!delfile.isDirectory()) {
						System.out.println("path=" + delfile.getPath());
						System.out.println("absolutepath="
								+ delfile.getAbsolutePath());
						System.out.println("name=" + delfile.getName());
						delfile.delete();
						System.out.println("删除文件成功");
					} else if (delfile.isDirectory()) {
						deletefile(delpath + "\\" + filelist[i]);
					}
				}
				file.delete();

			}

		} catch (FileNotFoundException e) {
			System.out.println("deletefile()   Exception:" + e.getMessage());
		}
		return true;
	}

	/**
	 * 获取文件夹下所有的文件名称
	 * 
	 * @param filepath
	 *            String
	 * @return boolean
	 */
	public static String[] readFilesName(HttpServletRequest request,
			String filepath) {
		String[] filelist = null;

		/** 应用路径* */
		String path = request.getSession().getServletContext()
				.getRealPath("/image/" + filepath);
		File file = new File(path);
		if (file.isDirectory()) {
			filelist = file.list();
		}

		return filelist;
	}
}
