/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.publish;

import java.net.URI;
import java.util.zip.ZipFile;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.internal.core.CompilationUnit;
import org.eclipse.jdt.internal.core.JarPackageFragmentRoot;
import org.eclipse.jdt.internal.core.PackageFragmentRoot;
import org.eclipse.jdt.internal.ui.packageview.ClassPathContainer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.handlers.HandlerUtil;

import com.jaspersoft.studio.editor.AbstractJRXMLEditor;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.publish.action.JrxmlPublishAction;
import com.jaspersoft.studio.server.publish.wizard.PublishFile2ServerWizard;
import com.jaspersoft.studio.utils.JRXMLUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.PersistentLocationWizardDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileExtension;
import net.sf.jasperreports.eclipse.util.Misc;

public class PublishHandler extends AbstractHandler {
	private static IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IFile file = null;
		JasperReportsConfiguration jContext = null;
		boolean disposeJrContext = false;
		ISelection sel = HandlerUtil.getCurrentSelection(event);
		if (sel instanceof StructuredSelection) {
			Object obj = ((StructuredSelection) sel).getFirstElement();
			if (obj instanceof IFile) {
				file = (IFile) obj;
			} else if (obj instanceof JarPackageFragmentRoot) {
				try {
					ZipFile zf = ((JarPackageFragmentRoot) obj).getJar();
					if (zf != null)
						file = getFileFromURI(new URI(zf.getName()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (obj instanceof CompilationUnit) {
				file = getFileFromURI(((CompilationUnit) obj).getPath().toFile().toURI());
			} else if (obj instanceof IProject || obj instanceof IFolder || obj instanceof IPackageFragment
					|| obj instanceof PackageFragmentRoot || obj instanceof ClassPathContainer) {
				UIUtils.showInformation(
						"Currently this type of operation is not supported, only publishing files is supported");
				return null;
			}
		}
		if (file == null) {
			IEditorInput ei = com.jaspersoft.studio.utils.compatibility.HandlerUtil.getActiveEditorInput(event);
			if (ei instanceof IFileEditorInput) {
				file = ((IFileEditorInput) ei).getFile();
				IEditorPart ep = HandlerUtil.getActiveEditor(event);
				if (ep instanceof AbstractJRXMLEditor)
					try {
						jContext = ((AbstractJRXMLEditor) ep).getJrContext(file);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
		if (file == null)
			UIUtils.showInformation(Messages.PublishHandler_0);
		else {
			String ext = file.getFileExtension();
			if (ext.equals(FileExtension.JRXML) || ext.equals(FileExtension.JASPER) || Misc.isNullOrEmpty(ext)) {
				if (jContext == null) {
					jContext = JasperReportsConfiguration.getDefaultJRConfig(file);
					disposeJrContext = true;
					try {
						jContext.setJasperDesign(JRXMLUtils.getJasperDesign(jContext, file.getContents(), null));
					} catch (Exception e) {
						e.printStackTrace();
						jContext.dispose();
						jContext = null;
					}
				}
				if (jContext != null) {
					JrxmlPublishAction publishAction = new JrxmlPublishAction(1, null);
					publishAction.setJrConfig(jContext);
					publishAction.run();
					// Check if the context was created internally and must be disposed
					if (disposeJrContext)
						jContext.dispose();
					return null;
				}
			}
			PublishFile2ServerWizard wizard = new PublishFile2ServerWizard(file, 1);
			new PersistentLocationWizardDialog(UIUtils.getShell(), wizard).open();
		}
		return null;
	}

	private IFile getFileFromURI(URI uri) {
		IFile[] res = root.findFilesForLocationURI(uri);
		if (res != null && res.length > 0)
			return res[0];
		return null;
	}
}
