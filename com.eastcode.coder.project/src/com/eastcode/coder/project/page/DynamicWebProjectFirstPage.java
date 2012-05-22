package com.eastcode.coder.project.page;

import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class DynamicWebProjectFirstPage extends ComponentFacetCreationWizardPage  {

	public DynamicWebProjectFirstPage(IDataModel model, String pageName) {
		super(model, pageName);
		setTitle("SSH2工程");
		setDescription("创建SSH2工程");
	}
	
	protected String getModuleFacetID() {
		return "jst.web";
	}
}