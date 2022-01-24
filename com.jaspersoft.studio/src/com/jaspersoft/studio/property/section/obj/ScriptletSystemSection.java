/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.obj;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

import net.sf.jasperreports.engine.base.JRBaseScriptlet;
import net.sf.jasperreports.engine.design.JRDesignScriptlet;

public class ScriptletSystemSection extends AbstractSection {
	
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		parent = getWidgetFactory().createSection(parent, "Scriptlet Properties", false, 2);
		GridData gd = new GridData(SWT.FILL,SWT.FILL,true,false);
		createWidget4Property(parent, JRDesignScriptlet.PROPERTY_NAME).getControl().setLayoutData(gd);
		createWidget4Property(parent, JRBaseScriptlet.PROPERTY_DESCRIPTION).getControl().setLayoutData(gd);
		createWidget4Property(parent, JRDesignScriptlet.PROPERTY_VALUE_CLASS_NAME).getControl().setLayoutData(gd);
	}
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRDesignScriptlet.PROPERTY_NAME, Messages.common_name);
		addProvidedProperties(JRBaseScriptlet.PROPERTY_DESCRIPTION, Messages.common_description);
		addProvidedProperties(JRDesignScriptlet.PROPERTY_VALUE_CLASS_NAME, Messages.common_class);
	}

}
