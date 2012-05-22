package com.eastcode.coder.generator.wizards;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.eastcode.coder.generator.util.Constants;
import com.eastcode.coder.generator.util.StringUtil;

/**
 * 设置数据库地址，用户名，密码
 * 
 * @author 王一进
 * 
 */
public class DbSettingWizardPage extends WizardPage {

	private Combo driverCombo;

	private String[] prjectNames;

	private Combo projectCombo;

	private IWorkspaceRoot workspaceRoot;

	private Text ipText;

	private Text dbText;

	private Text userNameText;

	private Text passwordText;

	private List<String> tableList;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public DbSettingWizardPage(String title) {
		super(title);
		setTitle(title);
		setDescription("选择项目，配置数据连接，连接数据库。");
		workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject[] iProjects = workspaceRoot.getProjects();
		prjectNames = new String[iProjects.length];
		for (int i = 0; i < iProjects.length; i++) {
			prjectNames[i] = iProjects[i].getName();
		}

	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		layout.verticalSpacing = 9;

		// 选择代码放置的工程
		Label label = new Label(container, SWT.NULL);
		label.setText("选择工程：");
		projectCombo = new Combo(container, SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		projectCombo.setLayoutData(gd);
		projectCombo.setItems(prjectNames);

		// 选择使用的数据库驱动
		label = new Label(container, SWT.NULL);
		label.setText("数据库驱动：");
		driverCombo = new Combo(container, SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		driverCombo.setLayoutData(gd);
		driverCombo.setItems(Constants.DRIVER_CLASS);

		// 输入数据库地址
		label = new Label(container, SWT.NULL);
		label.setText("数据库IP：");

		ipText = new Text(container, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		ipText.setLayoutData(gd);

		// 输入数据库名
		label = new Label(container, SWT.NULL);
		label.setText("数据库名：");

		dbText = new Text(container, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		dbText.setLayoutData(gd);

		// 输入用户名
		label = new Label(container, SWT.NULL);
		label.setText("用户名：");

		userNameText = new Text(container, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		userNameText.setLayoutData(gd);

		// 输入密码
		label = new Label(container, SWT.NULL);
		label.setText("密码：");

		passwordText = new Text(container, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		passwordText.setLayoutData(gd);

		// 输入密码
		label = new Label(container, SWT.NULL);
		label.setText("");

		Button button = new Button(container, SWT.BORDER);
		button.setText("连接数据库");
		gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		button.setLayoutData(gd);
		// 点击按钮
		button.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (isNotEmpty()) {
					getTableList();
				} else {
					DbSettingWizardPage.this.setDescription("请输入完整之后，再连接");
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		setControl(container);

		setPageComplete(false);
	}

	public boolean isNotEmpty() {

		String project = projectCombo.getText();
		String driverClass = driverCombo.getText();
		String ip = ipText.getText();
		String db = dbText.getText();
		String username = userNameText.getText();
		String password = passwordText.getText();

		boolean result = StringUtil.isNotEmpty(project) && StringUtil.isNotEmpty(ip)
				&& StringUtil.isNotEmpty(driverClass) && StringUtil.isNotEmpty(username)
				&& StringUtil.isNotEmpty(password) && StringUtil.isNotEmpty(db);

		return result;
	}

	@Override
	public IWizardPage getNextPage() {
		TableSelectionWizardPage page = (TableSelectionWizardPage) super.getNextPage();
		page.setTableNames(tableList.toArray(new String[0]));
		return page;
	}

	public String getProject() {
		return projectCombo.getText();
	}

	/**
	 * 获取数据库驱动
	 * 
	 * @return
	 */
	public String getDriverClass() {
		return driverCombo.getText();
	}

	public String getDbUrl() {
		// jdbc:oracle:thin:@172.16.22.195:1521:lotcard
		return "jdbc:oracle:thin:@" + ipText.getText() + ":1521:" + dbText.getText();
	}

	public String getUserName() {
		return userNameText.getText();
	}

	public String getPassword() {
		return passwordText.getText();
	}

	public void getTableList() {
		try {
			// 连接数据库，获取所有表格
			Class.forName(getDriverClass());
			Connection connection = getConnection();
			Statement stmt = connection.createStatement();
			String selectTable = "select TABLE_NAME tableName from user_tables";
			ResultSet resultSet = stmt.executeQuery(selectTable);
			tableList = new ArrayList<String>();
			while (resultSet.next()) {
				tableList.add(resultSet.getString("tableName"));
			}

			stmt.close();
			connection.close();
			this.setPageComplete(true);

		} catch (Exception ex) {
			DbSettingWizardPage.this.setDescription("数据库连接错误，请确认填写正确！");
			this.setPageComplete(false);
			ex.printStackTrace();
		}
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception {
		Connection connection = DriverManager.getConnection(getDbUrl(), getUserName(), getPassword());
		return connection;
	}

}