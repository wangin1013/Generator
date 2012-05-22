package com.eastcode.coder.generator.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.eastcode.coder.generator.util.StringUtil;

/**
 * 源码设置页面
 * 
 * @author 王一进
 * 
 */
public class SourceSettingWizardPage extends WizardPage {

	private Text tablePrefixText;

	private Text packagePathText;

	private Text pagePathText;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public SourceSettingWizardPage(String title) {
		super(title);
		setTitle(title);
		setDescription("设置表名取舍部分，及表字段风格，选择存放的包路径及前端页面路径");

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
		label.setText("设定表名舍弃的部分：");

		tablePrefixText = new Text(container, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		tablePrefixText.setLayoutData(gd);

		label = new Label(container, SWT.NULL);
		label.setText("选择代码存放的包路径：");

		packagePathText = new Text(container, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		packagePathText.setLayoutData(gd);

		label = new Label(container, SWT.NULL);
		label.setText("选择页面存放的路径：");

		pagePathText = new Text(container, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		pagePathText.setLayoutData(gd);

		setControl(container);
	}

	/**
	 * 判断是否已经全部为空
	 * 
	 * @return
	 */
	public boolean isNotEmpty() {

		String tablePrefix = tablePrefixText.getText();
		String packagePath = packagePathText.getText();
		String pagePath = pagePathText.getText();

		boolean result = StringUtil.isNotEmpty(tablePrefix) && StringUtil.isNotEmpty(packagePath)
				&& StringUtil.isNotEmpty(pagePath);

		return result;
	}

	public String getTablePrefix() {
		return tablePrefixText.getText();
	}

	public String getPackagePath() {
		return packagePathText.getText();
	}

	public String getPagePath() {
		return pagePathText.getText();
	}
}