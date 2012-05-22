package com.eastcode.coder.project.mode;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jst.common.project.facet.IJavaFacetInstallDataModelProperties;
import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.J2EEFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.web.project.facet.IWebFacetInstallDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelListener;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;

public class DynamicWebProjectCreationDataModelProvider extends J2EEFacetProjectCreationDataModelProvider {
	public DynamicWebProjectCreationDataModelProvider() {
		super();
	}

	@Override
	public void init() {
		super.init();

		Collection<IProjectFacet> requiredFacets = new ArrayList<IProjectFacet>();
		requiredFacets.add(JavaFacet.VERSION_1_6.getProjectFacet());
		requiredFacets.add(IJ2EEFacetConstants.DYNAMIC_WEB_FACET);
		setProperty(REQUIRED_FACETS_COLLECTION, requiredFacets);

		FacetDataModelMap map = (FacetDataModelMap) getProperty(FACET_DM_MAP);
		IDataModel javaFacet = map.getFacetDataModel(JavaFacet.VERSION_1_6.getProjectFacet().getId());
		IDataModel webFacet = map.getFacetDataModel(IJ2EEFacetConstants.DYNAMIC_WEB_FACET.getId());
		String webRoot = webFacet.getStringProperty(IWebFacetInstallDataModelProperties.CONFIG_FOLDER);
		String webSrc = webFacet.getStringProperty(IWebFacetInstallDataModelProperties.SOURCE_FOLDER);
		javaFacet.setProperty(IJavaFacetInstallDataModelProperties.SOURCE_FOLDER_NAME, webSrc);

		javaFacet.setProperty(IJavaFacetInstallDataModelProperties.DEFAULT_OUTPUT_FOLDER_NAME, webRoot + File.separator
				+ "WEB-INF/classes");

		webFacet.addListener(new IDataModelListener() {
			public void propertyChanged(DataModelEvent event) {
				if (IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME.equals(event.getPropertyName())) {
					if (isPropertySet(EAR_PROJECT_NAME))
						setProperty(EAR_PROJECT_NAME, event.getProperty());
					else
						model.notifyPropertyChange(EAR_PROJECT_NAME, IDataModel.DEFAULT_CHG);
				} else if (IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR.equals(event.getPropertyName())) {
					setProperty(ADD_TO_EAR, event.getProperty());
				}
			}
		});
	}

	@Override
	public boolean propertySet(String propertyName, Object propertyValue) {
		if (propertyName.equals(MODULE_URI)) {
			FacetDataModelMap map = (FacetDataModelMap) getProperty(FACET_DM_MAP);
			IDataModel webFacet = map.getFacetDataModel(IJ2EEFacetConstants.DYNAMIC_WEB);
			webFacet.setProperty(IJ2EEModuleFacetInstallDataModelProperties.MODULE_URI, propertyValue);
		}
		return super.propertySet(propertyName, propertyValue);
	}
}
