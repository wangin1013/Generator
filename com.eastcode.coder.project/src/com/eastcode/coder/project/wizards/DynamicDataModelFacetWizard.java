package com.eastcode.coder.project.wizards;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.web.ui.internal.WSTWebUIPlugin;
import org.eclipse.wst.web.ui.internal.wizards.NewProjectDataModelFacetWizard;
import org.osgi.framework.Bundle;

import com.eastcode.coder.project.Activator;

/**
 * 重写其完成过程！
 * 
 * @author Administrator
 * 
 */
public abstract class DynamicDataModelFacetWizard extends NewProjectDataModelFacetWizard {

	public DynamicDataModelFacetWizard(IDataModel model) {
		super(model);
	}

	public DynamicDataModelFacetWizard() {
		super();
	}

	@Override
	protected void performFinish(IProgressMonitor monitor) throws CoreException {
		monitor.beginTask("工程生成中......", 10); //$NON-NLS-1$
		storeDefaultSettings();
		try {
			super.performFinish(new SubProgressMonitor(monitor, 8));

			try {
				getFacetProjectNotificationOperation().execute(new NullProgressMonitor(), null);
			} catch (ExecutionException e) {
				String msg = e.getMessage();
				if (msg == null)
					msg = "";
				final IStatus st = new Status(IStatus.ERROR, WSTWebUIPlugin.PLUGIN_ID, 0, msg, e);
				throw new CoreException(st);
			}

			// 拷贝工程的library
			String projecfLocation = getFacetedProjectWorkingCopy().getProjectLocation().toString();

			Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
			String pluginLocation = bundle.getLocation().replace("reference:file:", "");

			if (pluginLocation.endsWith(".jar")) {
				/** 发布环境下，代码构建过程 **/
				product(pluginLocation, projecfLocation);
			} else {
				/** 开发环境代码构建过程 **/
				development(pluginLocation, projecfLocation);
			}

			/** 刷新工程 **/
			getFacetedProjectWorkingCopy().getProject().refreshLocal(IResource.DEPTH_INFINITE, monitor);
			/** 设置JDK版本 **/
			getFacetedProjectWorkingCopy().changeProjectFacetVersion(JavaFacet.VERSION_1_5);
			/** 设置编码为UTF-8 **/
			getFacetedProjectWorkingCopy().getProject().setDefaultCharset("UTF-8", monitor);

			/** 重新编译工程 **/
			getFacetedProjectWorkingCopy().getProject().build(IncrementalProjectBuilder.CLEAN_BUILD, monitor);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			monitor.done();
		}

	}

	/**
	 * 该方法用于在打包发布后，代码的拷贝构建
	 * 
	 * @param pluginLocation
	 * @param projecfLocation
	 */
	public void product(String pluginLocation, String projecfLocation) {

		try {
			JarFile jarFile = new JarFile(pluginLocation);
			Enumeration<JarEntry> jarEntrys = jarFile.entries();
			while (jarEntrys.hasMoreElements()) {
				JarEntry jarEntry = jarEntrys.nextElement();

				/** 发布环境下，文件被打包，采用解压缩获取每个实体内容 **/
				if (jarEntry.isDirectory()) {
					String path = "";
					String fileName = jarEntry.getName();
					if (fileName.startsWith("libs")) {
						path = projecfLocation + File.separator + "WebContent" + File.separator + "WEB-INF"
								+ File.separator + "lib" + File.separator;
						File lib = new File(path);
						if (!lib.exists()) {
							lib.mkdirs();
						}
					} else if (fileName.startsWith("source-eastcode")) {
						path = projecfLocation + File.separator + "src" + fileName.substring(15) + File.separator;
						File src = new File(path);
						if (!src.exists()) {
							src.mkdirs();
						}
					} else if (fileName.startsWith("page-eastcode")) {
						path = projecfLocation + File.separator + "WebContent" + fileName.substring(13)
								+ File.separator;
						File page = new File(path);
						if (!page.exists()) {
							page.mkdirs();
						}
					}
				} else {
					String path = "";
					String fileName = jarEntry.getName();
					// 拷贝所有jar包
					if (fileName.startsWith("libs")) {
						path = projecfLocation + File.separator + "WebContent" + File.separator + "WEB-INF"
								+ File.separator + "lib" + File.separator + fileName.substring(5);
						FileOutputStream os = new FileOutputStream(path);
						copy(jarFile.getInputStream(jarEntry), os);
					}

					// 拷贝所有基础代码
					if (fileName.startsWith("source-eastcode")) {
						path = projecfLocation + File.separator + "src" + fileName.substring(15);
						FileOutputStream os = new FileOutputStream(path);
						copy(jarFile.getInputStream(jarEntry), os);
					}

					// 拷贝所有jsp页面
					if (fileName.startsWith("page-eastcode")) {
						path = projecfLocation + File.separator + "WebContent" + fileName.substring(13);
						FileOutputStream os = new FileOutputStream(path);
						copy(jarFile.getInputStream(jarEntry), os);
					}

					// 拷贝所有配置文件信息
					if (fileName.startsWith("web-inf")) {
						path = projecfLocation + File.separator + "WebContent" + File.separator + "WEB-INF"
								+ fileName.substring(7);
						FileOutputStream os = new FileOutputStream(path);
						copy(jarFile.getInputStream(jarEntry), os);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 开发环境下，改方法将完成工程包路径及代码的构建，在发布环境下，该方法无法完成代码的构建
	 * 
	 * @param pluginLocation
	 * @param projecfLocation
	 */
	public void development(String pluginLocation, String projecfLocation) throws Exception {
		File lib = new File(pluginLocation + File.separator + "libs");

		/** 拷贝所有jar包 **/
		File[] files = lib.listFiles();
		for (int i = 0; files != null && i < files.length; i++) {
			File file = files[i];
			String path = projecfLocation + File.separator + "WebContent" + File.separator + "WEB-INF" + File.separator
					+ "lib" + File.separator;
			try {
				copy(file, new File(path + file.getName()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/** 创建java源码目录 **/
		File src = new File(pluginLocation + File.separator + "source-eastcode" + File.separator);
		List<File> srcList = new ArrayList<File>();
		listFile(src, srcList);
		for (int i = 0; srcList != null && i < srcList.size(); i++) {
			File file = srcList.get(i);
			String path = file.getPath().substring(file.getPath().lastIndexOf("source-eastcode") + 16);
			path = path.replaceAll(file.getName(), "");
			String targetPath = projecfLocation + File.separator + "src" + File.separator + path;
			File location = new File(targetPath);
			if (!location.exists()) {
				location.mkdirs();
			}
			copy(file, new File(targetPath + file.getName()));
		}

		/** 创建页面目录 **/
		File page = new File(pluginLocation + File.separator + "page-eastcode" + File.separator);
		List<File> pageList = new ArrayList<File>();
		listFile(page, pageList);
		for (int i = 0; pageList != null && i < pageList.size(); i++) {
			File file = pageList.get(i);
			String path = file.getPath().substring(file.getPath().lastIndexOf("page-eastcode") + 14);
			path = path.replaceAll(file.getName(), "");
			String targetPath = projecfLocation + File.separator + "WebContent" + File.separator + path;
			File location = new File(targetPath);
			if (!location.exists()) {
				location.mkdirs();
			}
			copy(file, new File(targetPath + file.getName()));
		}

		/** 拷贝WEB-INF目录下所有文件 **/
		String source = pluginLocation + File.separator + "web-inf";
		File webinfo = new File(source);
		File[] webinfos = webinfo.listFiles();
		for (int i = 0; webinfos != null && i < webinfos.length; i++) {
			copy(webinfos[i], new File(projecfLocation + File.separator + "WebContent" + File.separator + "WEB-INF"
					+ File.separator + webinfos[i].getName()));
		}
	}

	/**
	 * 文件拷贝
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 * @throws Exception
	 */
	public static long copy(File f1, File f2) throws Exception {
		if (f1.isFile()) {
			long time = new Date().getTime();
			int length = 2097152;
			FileInputStream in = new FileInputStream(f1);
			FileOutputStream out = new FileOutputStream(f2);
			byte[] buffer = new byte[length];
			while (true) {
				int ins = in.read(buffer);
				if (ins == -1) {
					in.close();
					out.flush();
					out.close();
					return new Date().getTime() - time;
				} else
					out.write(buffer, 0, ins);
			}
		}

		return 0;
	}

	/**
	 * 文件拷贝
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 * @throws Exception
	 */
	public static long copy(InputStream is, OutputStream os) throws Exception {
		long time = new Date().getTime();
		int length = 2097152;
		byte[] buffer = new byte[length];
		while (true) {
			int ins = is.read(buffer);
			if (ins == -1) {
				is.close();
				os.flush();
				os.close();
				return new Date().getTime() - time;
			} else
				os.write(buffer, 0, ins);
		}
	}

	/**
	 * 解压缩
	 * 
	 * @param targetPath
	 * @param zipFilePath
	 */
	public void unzipFile(String targetPath, String zipFilePath) {
		try {
			File zipFile = new File(zipFilePath);
			InputStream is = new FileInputStream(zipFile);
			ZipInputStream zis = new ZipInputStream(is);
			ZipEntry entry = null;
			while ((entry = zis.getNextEntry()) != null) {
				String zipPath = entry.getName();
				try {
					if (!entry.isDirectory()) {
						File file = new File(targetPath + File.separator + zipPath);
						if (!file.exists()) {
							File pathDir = file.getParentFile();
							pathDir.mkdirs();
							file.createNewFile();
						}
						FileOutputStream fos = new FileOutputStream(file);
						int bread;
						while ((bread = zis.read()) != -1) {
							fos.write(bread);
						}
						fos.close();
					}
				} catch (Exception e) {
					continue;
				}
			}
			zis.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 遍历所有的文件
	 * 
	 * @param file
	 * @param list
	 */
	public void listFile(File file, List<File> list) {
		if (file.isDirectory()) {
			File[] childs = file.listFiles();
			for (int i = 0; childs != null && i < childs.length; i++) {
				listFile(childs[i], list);
			}
		} else {
			list.add(file);
		}
	}
}