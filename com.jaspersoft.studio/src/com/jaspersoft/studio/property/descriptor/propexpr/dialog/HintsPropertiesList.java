/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.propexpr.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.property.dataset.DatasetUtil;
import com.jaspersoft.studio.property.infoList.ElementDescription;
import com.jaspersoft.studio.property.metadata.PropertyMetadataRegistry;

import net.sf.jasperreports.annotations.properties.PropertyScope;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.properties.PropertiesMetadataUtil;
import net.sf.jasperreports.properties.PropertyMetadata;

/**
 * Class that define static methods to get the hint properties specific to some type of elements
 */
public class HintsPropertiesList {

	public static List<ElementDescription> getElementProperties(Object holder, ExpressionContext eContext) {
		List<ElementDescription> result = new ArrayList<ElementDescription>();
		for (PropertyMetadata pm : getPropertiesMetadata(holder, eContext))
			result.add(new ElementDescription(pm.getName(), pm.getDescription(), true));
		return result;
	}

	public static List<PropertyMetadata> getPropertiesMetadata(Object holder, ExpressionContext eContext) {
		List<PropertyMetadata> result = new ArrayList<PropertyMetadata>();
		if (holder instanceof JasperDesign) {
			PropertiesMetadataUtil pmu = PropertiesMetadataUtil.getInstance(eContext.getJasperReportsConfiguration());
			ClassLoader oldCl = Thread.currentThread().getContextClassLoader();
			try {
				Thread.currentThread().setContextClassLoader(JRLoader.class.getClassLoader());

				List<PropertyMetadata> eps = pmu.getReportProperties((JasperDesign) holder);
				if (eps != null)
					result.addAll(eps);
				result.addAll(PropertyMetadataRegistry.getPropertiesMetadata(PropertyScope.REPORT));
				result.addAll(PropertyMetadataRegistry.getPropertiesMetadata(PropertyScope.DATASET));
			} finally {
				Thread.currentThread().setContextClassLoader(oldCl);
			}
		} else if (holder instanceof JRDesignDataset) {
			JRDesignDataset ds = (JRDesignDataset) holder;
			PropertiesMetadataUtil pmu = PropertiesMetadataUtil.getInstance(eContext.getJasperReportsConfiguration());
			ClassLoader oldCl = Thread.currentThread().getContextClassLoader();
			try {
				Thread.currentThread().setContextClassLoader(JRLoader.class.getClassLoader());
				try {
					List<PropertyMetadata> eps = pmu.getDatasetProperties((JRDesignDataset) holder,
							DatasetUtil.getDataAdapter(eContext.getJasperReportsConfiguration(), ds));
					if (eps != null)
						result.addAll(eps);
					result.addAll(PropertyMetadataRegistry.getPropertiesMetadata(PropertyScope.DATASET));
				} catch (JRException e) {
					e.printStackTrace();
				}
			} finally {
				Thread.currentThread().setContextClassLoader(oldCl);
			}
		} else if (holder instanceof JRElement) {
			PropertiesMetadataUtil pmu = PropertiesMetadataUtil.getInstance(eContext.getJasperReportsConfiguration());
			ClassLoader oldCl = Thread.currentThread().getContextClassLoader();
			try {
				Thread.currentThread().setContextClassLoader(JRLoader.class.getClassLoader());
				List<PropertyMetadata> eps = pmu.getElementProperties((JRElement) holder);
				if (eps != null)
					result.addAll(eps);
				result.addAll(PropertyMetadataRegistry.getPropertiesMetadata(PropertyScope.ELEMENT));
			} finally {
				Thread.currentThread().setContextClassLoader(oldCl);
			}
		} else if (holder instanceof JRField) {
			PropertiesMetadataUtil pmu = PropertiesMetadataUtil.getInstance(eContext.getJasperReportsConfiguration());
			ClassLoader oldCl = Thread.currentThread().getContextClassLoader();
			try {
				Thread.currentThread().setContextClassLoader(JRLoader.class.getClassLoader());

				try {
					List<PropertyMetadata> eps = pmu.getQueryExecuterFieldProperties(
							(String) eContext.getJasperReportsConfiguration().get(COM_JASPERSOFT_STUDIO_DATASET_LANGUAGE));
					if (eps != null)
						result.addAll(eps);
					result.addAll(PropertyMetadataRegistry.getPropertiesMetadata(PropertyScope.FIELD));
				} catch (JRException e) {
					e.printStackTrace();
				}
			} finally {
				Thread.currentThread().setContextClassLoader(oldCl);
			}
		} else if (holder instanceof JRParameter) {
			Map<String, PropertyMetadata> map = DatasetUtil.getPmap(eContext.getJasperReportsConfiguration());
			result.addAll(map.values());
		}

		return result;
	}

	public static final String COM_JASPERSOFT_STUDIO_DATASET_LANGUAGE = "com.jaspersoft.studio.dataset.language";

}
