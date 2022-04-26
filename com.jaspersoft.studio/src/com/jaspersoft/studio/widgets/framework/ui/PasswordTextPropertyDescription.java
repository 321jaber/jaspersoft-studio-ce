/*******************************************************************************
 * Copyright (C) 2010 - 2022. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import org.eclipse.swt.SWT;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

/**
 * Extending the original {@link TextPropertyDescription} one 
 * providing password holding capabilities to the text widget. 
 * 
 * @author Massimo Rabbi (mrabbi@tibco.com)
 *
 */
public class PasswordTextPropertyDescription<T> extends TextPropertyDescription<T> {

	public PasswordTextPropertyDescription() {
		super();
	}

	public PasswordTextPropertyDescription(String name, String description, boolean mandatory) {
		super(name, description, mandatory);
	}

	public PasswordTextPropertyDescription(String name, String label, String description, boolean mandatory,
			T defaultValue) {
		super(name, label, description, mandatory, defaultValue);
	}

	public PasswordTextPropertyDescription(String name, String label, String description, boolean mandatory) {
		super(name, label, description, mandatory);
	}

	@Override
	protected int getTextControlStyle() {
		return super.getTextControlStyle() | SWT.PASSWORD;
	}
	
	@Override
	public PasswordTextPropertyDescription<T> clone() {
		PasswordTextPropertyDescription<T> result = new PasswordTextPropertyDescription<>();
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.readOnly = readOnly;
		result.fallbackValue = fallbackValue;
		return result;
	}

	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd,
			JasperReportsConfiguration jConfig) {
		PasswordTextPropertyDescription<String> result = new PasswordTextPropertyDescription<>(cpd.getName(),
				cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(),
				cpd.getDefaultValue());
		result.setReadOnly(cpd.isReadOnly());
		result.setFallbackValue(cpd.getFallbackValue());
		return result;
	}
}
