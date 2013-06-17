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
package com.jaspersoft.studio.data.sql.widgets;

import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.sql.dialogs.FromTableColumnsDialog;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;

public class FieldWidget extends AOperandWidget<FieldOperand> {

	private Text txt;

	public FieldWidget(Composite parent, FieldOperand operand) {
		super(parent, SWT.NONE, operand);
	}

	@Override
	protected void createWidget(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 3;
		setLayout(layout);

		final FieldOperand v = getValue();

		txt = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		txt.setText(v.toSQLString());
		txt.setToolTipText(v.toSQLString());
		txt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button b = new Button(this, SWT.PUSH);
		b.setText("...");
		b.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FromTableColumnsDialog dialog = new FromTableColumnsDialog(Display.getDefault().getActiveShell(), SWT.SINGLE);
				dialog.setSelection(v.getExpression());
				if (dialog.open() == Dialog.OK) {
					Map<MColumn, MFromTable> cmap = dialog.getColumns();
					for (MColumn t : cmap.keySet())
						v.setValue(t, cmap.get(t));
				}
				txt.setText(v.toSQLString());
				txt.setToolTipText(v.toSQLString());
			}
		});
	}

}
