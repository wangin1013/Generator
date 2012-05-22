package com.eastcode.coder.generator.wizards;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * 选择要生成的数据库表
 * 
 * @author 王一进
 * 
 */
public class TableSelectionWizardPage extends WizardPage {

	private Composite container;

	private String[] tableNames;

	private Table table;

	private TableViewer tableViewer;

	private Map<String, String> itemMap = new HashMap<String, String>();

	public String[] getTableNames() {
		return tableNames;
	}

	public void setTableNames(String[] tableNames) {
		this.tableNames = tableNames;

		tableViewer = new TableViewer(container, SWT.V_SCROLL | SWT.H_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION
				| SWT.CHECK);
		table = tableViewer.getTable();

		TableColumn headerTableName = new TableColumn(table, SWT.CENTER);
		headerTableName.setText("表名");
		headerTableName.setWidth(600);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setBounds(0, 0, container.getSize().x, container.getSize().y);

		for (int i = 0; tableNames != null && i < tableNames.length; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(tableNames[i]);
		}

		container.redraw();

		table.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent event) {
				if (event.detail == SWT.CHECK) {
					TableItem item = (TableItem) event.item;
					if (item.getChecked()) {
						itemMap.put(item.getText(), item.getText());
					} else {
						itemMap.remove(item.getText());
					}
					if (itemMap.size() == 0) {
						TableSelectionWizardPage.this.setPageComplete(false);
					} else {
						TableSelectionWizardPage.this.setPageComplete(true);
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent event) {
			}
		});

		setControl(container);

		int length = getSelectedTableName().length;
		if (length > 0) {
			this.setPageComplete(true);
		} else {
			setPageComplete(false);
		}
	}

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public TableSelectionWizardPage(String title) {
		super(title);
		setTitle(title);
		setDescription("选择你要生成的表。");
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 1;

		setControl(container);
	}

	/**
	 * 获取所选的表
	 * 
	 * @return
	 */
	public String[] getSelectedTableName() {

		return itemMap.keySet().toArray(new String[0]);
	}

	@Override
	public IWizardPage getNextPage() {
		int length = getSelectedTableName().length;

		if (length > 0) {
			return super.getNextPage();
		} else {
			this.setDescription("请选择要生的表格！");
			return this;
		}
	}
}