package com.eastcode.coder.project.wizards;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectTemplate;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.project.facet.IProductConstants;
import org.eclipse.wst.project.facet.ProductManager;
import org.osgi.framework.Bundle;

import com.eastcode.coder.project.mode.DynamicWebProjectCreationDataModelProvider;
import com.eastcode.coder.project.page.DynamicWebProjectFirstPage;

public class DynamicWebProjectWizard extends DynamicDataModelFacetWizard {
	public DynamicWebProjectWizard(IDataModel model) {
		super(model);
		setWindowTitle("SSH2工程生成");
	}

	public DynamicWebProjectWizard() {
		super();
		setWindowTitle("SSH2工程生成");
	}

	protected IDataModel createDataModel() {
		return DataModelFactory.createDataModel(new DynamicWebProjectCreationDataModelProvider());
	}

	protected IFacetedProjectTemplate getTemplate() {
		return ProjectFacetsManager.getTemplate("template.jst.web"); //$NON-NLS-1$
	}

	protected IWizardPage createFirstPage() {
		return new DynamicWebProjectFirstPage(model, "first.page"); //$NON-NLS-1$
	}

	protected ImageDescriptor getDefaultPageImageDescriptor() {
		final Bundle bundle = Platform.getBundle("org.eclipse.jst.servlet.ui"); //$NON-NLS-1$
		final URL url = bundle.getEntry("icons/full/ctool16/web-wiz-banner.gif"); //$NON-NLS-1$
		return ImageDescriptor.createFromURL(url);
	}

	protected String getFinalPerspectiveID() {
		return ProductManager.getProperty(IProductConstants.FINAL_PERSPECTIVE_WEB);
	}
}