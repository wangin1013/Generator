package com.eastcode.server.main.action;

import java.io.File;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.eastcode.base.action.BaseAction;
import com.eastcode.utils.DateUtil;

public class FileUploadAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(FileUploadAction.class);

	private File file;
	private String fileName;
	private String contentType;
	private String dir;
	private String targetFileName;

	/**
	 * 上传文件
	 * 
	 * @return
	 * @throws Exception
	 */
	protected String upload(String targetDir) throws Exception {
		if (fileName == null) {
			return "";
		}
		// 获取上传目录
		String realPath = ServletActionContext.getServletContext().getRealPath(targetDir);
		log.debug(realPath);
		String targetDirectory = realPath;
		targetFileName = genearaFileName(fileName);
		setDir(targetDirectory + "" + targetFileName);
		File target = new File(targetDirectory, targetFileName);
		FileUtils.copyFile(file, target);
		return target.getAbsolutePath();
	}

	/**
	 * 解析上传文件名
	 * 
	 * @param fileName
	 * @return
	 */
	protected String genearaFileName(String fileName) {
		// 产生随机数
		int random = (new Random()).nextInt(10000);
		// 获取文件后缀名称
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return DateUtil.getTime() + random + extension;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFileContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getTargetFileName() {
		return targetFileName;
	}

	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}

	public File getFile() {
		return file;
	}

	public String getFileName() {
		return fileName;
	}

	public String getContentType() {
		return contentType;
	}
}
