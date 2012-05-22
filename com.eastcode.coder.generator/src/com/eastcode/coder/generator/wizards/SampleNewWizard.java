package com.eastcode.coder.generator.wizards;

import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.core.runtime.*;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import org.osgi.framework.Bundle;

import com.eastcode.coder.generator.Activator;
import com.eastcode.coder.generator.domain.Column;
import com.eastcode.coder.generator.util.ColumnTypeConvert;
import com.eastcode.coder.generator.util.MyCodeFormatter;
import com.eastcode.coder.generator.util.StringUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 代码生成向导
 * 
 * @author 王一进
 * 
 */
public class SampleNewWizard extends Wizard implements INewWizard {
	private DbSettingWizardPage dbPage;
	private TableSelectionWizardPage tablePage;
	private SourceSettingWizardPage sourcePage;

	private String[] args;
	private Map<String, List<Column>> columnForTable;
	private Map<String, String> tableMap = new HashMap<String, String>();

	/**
	 * Constructor for SampleNewWizard.
	 */
	public SampleNewWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	/**
	 * Adding the page to the wizard.
	 */

	public void addPages() {
		dbPage = new DbSettingWizardPage("配置数据库");
		tablePage = new TableSelectionWizardPage("选择数据库表");
		sourcePage = new SourceSettingWizardPage("设置代码存放路径");

		addPage(dbPage);
		addPage(tablePage);
		addPage(sourcePage);
	}

	/**
	 * This method is called when 'Finish' button is pressed in the wizard. We
	 * will create an operation and run it using wizard as execution context.
	 */
	public boolean performFinish() {
		args = getSetting();
		columnForTable = getColumn(tablePage.getSelectedTableName());
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					doFinish(monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};

		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}

	private Map<String, List<Column>> getColumn(String[] tableNames) {
		Map<String, List<Column>> result = new HashMap<String, List<Column>>();
		try {
			Connection connection = dbPage.getConnection();
			Statement statement = connection.createStatement();
			String querySql = "SELECT COLUMN_NAME, DATA_TYPE, DATA_SCALE, NULLABLE, DATA_LENGTH FROM USER_TAB_COLUMNS WHERE TABLE_NAME='";
			for (int i = 0; i < tableNames.length; i++) {
				ResultSet set = statement.executeQuery(querySql + tableNames[i] + "'");
				List<Column> columnList = new ArrayList<Column>();
				while (set.next()) {
					Column column = new Column();
					column.setColumnName(set.getString("COLUMN_NAME"));
					column.setDataType(set.getString("DATA_TYPE"));
					column.setDataScale(set.getString("DATA_SCALE"));
					column.setNullable(set.getString("NULLABLE"));
					column.setDataLength(set.getString("DATA_LENGTH"));

					columnList.add(column);
				}

				result.put(createClassName(tableNames[i]), columnList);
			}
			statement.close();
			connection.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	private void doFinish(IProgressMonitor monitor) throws CoreException {

		monitor.beginTask("代码路径" + args[2], 2);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = root.getProject(args[0]);

		// 获取source目录
		IJavaProject javaProject = JavaCore.create(project);

		IPackageFragmentRoot[] roots = javaProject.getPackageFragmentRoots();
		IPackageFragmentRoot sourceRoot = null;
		for (int i = 0; roots != null && i < roots.length; i++) {
			int a = roots[i].getKind();
			if (a == IPackageFragmentRoot.K_SOURCE) {
				sourceRoot = roots[i];
			}
		}

		// 创建默认源码目录
		if (sourceRoot == null || !sourceRoot.exists()) {
			sourceRoot.createPackageFragment("src", true, monitor);
		}

		IPackageFragment javaPackage = null;

		// 生成包路径
		String packagePath = args[2];
		javaPackage = sourceRoot.getPackageFragment(packagePath);
		if (javaPackage == null || !javaPackage.exists()) {
			sourceRoot.createPackageFragment(packagePath, true, monitor);
		}

		// 获取表名，生成对应包
		Iterator<String> tables = columnForTable.keySet().iterator();
		while (tables.hasNext()) {
			String tableName = tables.next();
			String bunisess = packagePath + "." + createPackageName(tableName);

			IPackageFragment bunisesePackage = sourceRoot.getPackageFragment(bunisess);
			if (bunisesePackage == null || !bunisesePackage.exists()) {
				sourceRoot.createPackageFragment(bunisess, true, monitor);
			}

			// 生成各个层次的包路径
			createBunisess(sourceRoot, bunisess, tableName, monitor);

			// 拷贝页面到指定文件夹
			String target = javaProject.getProject().getLocation().toString() + File.separator + "WebContent"
					+ File.separator + args[3] + File.separator;

			createJspPage("list", target + StringUtil.lowerCaptial(tableName) + ".jsp",
					StringUtil.lowerCaptial(tableName));
			createJspPage("detail", target + StringUtil.lowerCaptial(tableName) + "Detail.jsp",
					StringUtil.lowerCaptial(tableName));
			createJspPage("insert", target + StringUtil.lowerCaptial(tableName) + "Insert.jsp",
					StringUtil.lowerCaptial(tableName));
		}

		monitor.worked(1);
		monitor.done();
	}

	/**
	 * 根据表名生成类名
	 * 
	 * @param tableName
	 * @return
	 */
	private String createClassName(String tableName) {

		// 截取表名
		String className = tableName.replaceAll(args[1], "").toLowerCase();

		StringBuffer sb = new StringBuffer();
		String[] packages = className.split("_");
		for (int i = 0; packages != null && i < packages.length; i++) {
			sb.append(Character.toUpperCase(packages[i].charAt(0)) + packages[i].substring(1));
		}

		// 表名映射处理
		tableMap.put(sb.toString(), tableName);

		return sb.toString();
	}

	/**
	 * 产生包名
	 * 
	 * @param className
	 * @return
	 */
	private String createPackageName(String className) {
		return Character.toLowerCase(className.charAt(0)) + className.substring(1);
	}

	/**
	 * 获取源码路径设置
	 * 
	 * @return
	 */
	public String[] getSetting() {
		String project = dbPage.getProject();
		String tablePrefix = sourcePage.getTablePrefix();
		String pacakgePath = sourcePage.getPackagePath();
		String pagePath = sourcePage.getPagePath();

		args = new String[] { project, tablePrefix, pacakgePath, pagePath };

		return args;
	}

	/**
	 * 生成对应的各个包路径
	 * 
	 * @param bunisese
	 * @throws CoreException
	 */
	private void createBunisess(IPackageFragmentRoot sourceRoot, String bunisese, String tableName,
			IProgressMonitor monitor) throws CoreException {

		// 生成action，domain，service，dao，view类
		IPackageFragment action = sourceRoot.getPackageFragment(bunisese + ".action");
		if (action == null || !action.exists()) {
			sourceRoot.createPackageFragment(bunisese + ".action", true, monitor);
			createSource(action, tableName, "Action", monitor);
		}

		IPackageFragment domain = sourceRoot.getPackageFragment(bunisese + ".domain");
		if (domain == null || !domain.exists()) {
			sourceRoot.createPackageFragment(bunisese + ".domain", true, monitor);
			createSource(domain, tableName, "", monitor);
		}
		IPackageFragment service = sourceRoot.getPackageFragment(bunisese + ".service");
		if (service == null || !service.exists()) {
			sourceRoot.createPackageFragment(bunisese + ".service", true, monitor);
			createSource(service, tableName, "Service", monitor);
		}
		IPackageFragment serviceImpl = sourceRoot.getPackageFragment(bunisese + ".service" + ".impl");
		if (serviceImpl == null || !serviceImpl.exists()) {
			sourceRoot.createPackageFragment(bunisese + ".service" + ".impl", true, monitor);
			createSource(serviceImpl, tableName, "ServiceImpl", monitor);
		}
		IPackageFragment dao = sourceRoot.getPackageFragment(bunisese + ".dao");
		if (dao == null || !dao.exists()) {
			sourceRoot.createPackageFragment(bunisese + ".dao", true, monitor);
			createSource(dao, tableName, "Dao", monitor);
		}
		IPackageFragment view = sourceRoot.getPackageFragment(bunisese + ".view");
		if (view == null || !view.exists()) {
			sourceRoot.createPackageFragment(bunisese + ".view", true, monitor);
			createSource(view, tableName, "View", monitor);
		}
	}

	/**
	 * 产生各个层次的代码结构
	 * 
	 * @param sourcePackage
	 * @param name
	 */
	private void createSource(IPackageFragment sourcePackage, String tableName, String surrfix, IProgressMonitor monitor)
			throws CoreException {
		String javaName = tableName + surrfix;
		String sourceName = javaName + ".java";

		ICompilationUnit source = sourcePackage.getCompilationUnit(sourceName);
		if (source == null || !source.exists()) {
			if (surrfix.length() == 0) {
				generateDomain(sourcePackage, tableName, surrfix, monitor, javaName, sourceName);
			} else {
				Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
				String pluginLocation = bundle.getLocation().replace("reference:file:", "");

				String sourceContent = "";
				try {
					File config = new File(pluginLocation + File.separator + "config" + File.separator
							+ StringUtil.lowerCaptial(surrfix) + ".java");
					StringBuffer sb = new StringBuffer();
					if (!config.exists()) {
						JarFile jarFile = new JarFile(pluginLocation);
						Enumeration<JarEntry> jarEntrys = jarFile.entries();

						try {
							while (jarEntrys.hasMoreElements()) {
								JarEntry jarEntry = jarEntrys.nextElement();
								if (jarEntry.getName().contains("config/" + StringUtil.lowerCaptial(surrfix) + ".java")) {
									BufferedReader in = new BufferedReader(new InputStreamReader(
											jarFile.getInputStream(jarEntry), "UTF-8"));
									while ((sourceContent = in.readLine()) != null) {
										sb.append(sourceContent);
										sb.append("\n");
									}
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(config),
								"UTF-8"));

						while ((sourceContent = br.readLine()) != null) {
							sb.append(sourceContent);
						}
					}
					sourceContent = sb.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}

				sourceContent = sourceContent.replaceAll("com.eastcode.server.system.", sourcePackage.getElementName()
						.replaceAll(surrfix.toLowerCase(), "").replaceAll("service.impl", ""));
				sourceContent = sourceContent.replaceAll("SystemParam", tableName);
				sourceContent = sourceContent.replaceAll("systemParam", StringUtil.lowerCaptial(tableName));
				ICompilationUnit unit = sourcePackage.createCompilationUnit(sourceName, sourceContent, true, monitor);

				// 格式化代码
				IBuffer buf = unit.getBuffer();
				String originalContent = buf.getContents();
				buf.setContents(MyCodeFormatter.format(originalContent));
				buf.save(monitor, true);
			}
		}
	}

	/**
	 * 生成domain对象
	 * 
	 * @param sourcePackage
	 * @param tableName
	 * @param surrfix
	 * @param monitor
	 * @param javaName
	 * @param sourceName
	 * @throws CoreException
	 */
	private void generateDomain(IPackageFragment sourcePackage, String tableName, String surrfix,
			IProgressMonitor monitor, String javaName, String sourceName) throws CoreException {
		String sourceContent = "package " + sourcePackage.getElementName() + "; ";

		sourceContent += "@Entity @Table(name = \"" + tableMap.get(tableName) + "\")";

		sourceContent += " public class " + javaName + " implements PersistentObject {}";
		ICompilationUnit unit = sourcePackage.createCompilationUnit(sourceName, sourceContent, true, monitor);

		IType[] type = unit.getAllTypes();
		for (IType t : type) {

			List<Column> columns = columnForTable.get(tableName);

			// 添加包
			ColumnTypeConvert.addImport(columns, unit);
			unit.createImport("com.eastcode.base.domain.PersistentObject", null, null);

			// 添加字段
			for (int i = 0; i < columns.size(); i++) {
				t.createField(
						"private " + ColumnTypeConvert.convert(columns.get(i)) + " "
								+ StringUtil.createFieldName(columns.get(i).getColumnName()) + ";", null, true, monitor);
			}

			// 添加getter,setter方法
			for (int i = 0; i < columns.size(); i++) {
				// 产生getter方法,setter方法
				t.createMethod(StringUtil.createGetter(columns.get(i)), null, true, monitor);
				t.createMethod(StringUtil.createSetter(columns.get(i)), null, true, monitor);
			}
		}

		// 格式化代码
		IBuffer buf = unit.getBuffer();
		String originalContent = buf.getContents();
		buf.setContents(MyCodeFormatter.format(originalContent));
		buf.save(monitor, true);
	}

	/**
	 * 创建JSP页面
	 * 
	 * @param page
	 * @param target
	 */
	public void createJspPage(String page, String target, String domain) {
		Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
		String pluginLocation = bundle.getLocation().replace("reference:file:", "");

		String sourceContent = "";
		try {
			File jsp = new File(pluginLocation + File.separator + "page" + File.separator + page + ".jsp");
			StringBuffer sb = new StringBuffer();
			if (!jsp.exists()) {
				JarFile jarFile = new JarFile(pluginLocation);
				Enumeration<JarEntry> jarEntrys = jarFile.entries();
				try {
					while (jarEntrys.hasMoreElements()) {
						JarEntry jarEntry = jarEntrys.nextElement();
						if (jarEntry.getName().contains("page/" + page + ".jsp")) {
							BufferedReader in = new BufferedReader(new InputStreamReader(
									jarFile.getInputStream(jarEntry), "UTF-8"));
							while ((sourceContent = in.readLine()) != null) {
								sb.append(sourceContent);
								sb.append("\n");
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(jsp), "UTF-8"));

				while ((sourceContent = br.readLine()) != null) {
					sb.append(sourceContent);
				}
			}
			sourceContent = sb.toString();
			sourceContent.replaceAll("systemParam", domain);
			// 写入JSP页面
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(target, true), "UTF-8");
			osw.write(sourceContent);
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * We will accept the selection in the workbench to see if we can initialize
	 * from it.
	 * 
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.setWindowTitle("代码生成");
	}
}