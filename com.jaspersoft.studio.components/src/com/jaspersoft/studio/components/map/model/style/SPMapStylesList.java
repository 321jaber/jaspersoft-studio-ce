/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model.style;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.map.model.itemdata.ElementsListWidgetConfiguration;
import com.jaspersoft.studio.components.map.model.itemdata.MapDataElementsContentProvider;
import com.jaspersoft.studio.components.map.model.itemdata.SPMapDataElementsList;
import com.jaspersoft.studio.property.section.AbstractSection;

/**
 * Widget that allows to manage the <code>StandardMapComponent.PROPERTY_PATH_STYLE_LIST</code> property.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class SPMapStylesList extends SPMapDataElementsList {

	public SPMapStylesList(Composite parent, AbstractSection section,
			IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	protected IBaseLabelProvider getElementsViewerLabelProvider() {
		return new StylesLabelProvider();
	}

	@Override
	protected IContentProvider getElementsViewerContentProvider() {
		return new MapDataElementsContentProvider();
	}

	@Override
	protected List<String> getMandatoryProperties() {
		return Arrays.asList("name");
	}

	@Override
	protected ElementsListWidgetConfiguration getWidgetConfiguration() {
		return new MapStylesWidgetConfiguration();
	}

}
