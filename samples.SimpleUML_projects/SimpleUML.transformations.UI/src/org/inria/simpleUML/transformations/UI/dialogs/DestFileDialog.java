/* $Id: DestFileDialog.java,v 1.1 2004-08-19 10:18:48 dvojtise Exp $
 * Created on 19 août 2004
 * Authors: dvojtise
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.simpleUML.transformations.UI.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

/**
 * @author dvojtise
 *
 */
public class DestFileDialog extends Dialog {

	private String destFileNameString;
	private String sourceFileNameString;
	private String actionDescriptionString;
	private String dialogTitleString;
	
	public DestFileDialog(String sourceFileName, String destFileName, String theDialogTitleString,String actionDescription, Shell parentShell) {
		super(parentShell);
		sourceFileNameString= sourceFileName;
		destFileNameString=destFileName;
		actionDescriptionString=actionDescription;
		dialogTitleString=theDialogTitleString;
	}
	
	public String getDestFileName()
	{	
		return 	destFileNameString;	
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		area.setLayout(new FillLayout(SWT.VERTICAL));
		{
						final Group group_info = new Group(area, SWT.NONE);
						group_info.setText("Transformation action");
						group_info.setLayout(new FillLayout());
						{
							final Text ActionDescriptionText = new Text(group_info, SWT.MULTI | SWT.READ_ONLY);
							ActionDescriptionText.setText(actionDescriptionString);
						}
		}
		{
			final Group group = new Group(area, SWT.NONE);
			group.setText("Files");
			group.setLayout(new FillLayout(SWT.VERTICAL));
			{
				final Label label = new Label(group, SWT.NONE);
				label.setText("Source file");
			}
			{
				final Text sourceFileText = new Text(group, SWT.BORDER);
				sourceFileText.setEnabled(false);
				sourceFileText.setText(sourceFileNameString);
			}
			{
				final Label label = new Label(group, SWT.NONE);
				label.setText("Target file");
			}
			{
				final Text destFileText = new Text(group, SWT.BORDER);
				destFileText.setText(destFileNameString);
				destFileText.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent e) {
						destFileNameString = destFileText.getText(); 
					}
				});
			}
		}
		return area;
	}
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(
			parent,
			IDialogConstants.OK_ID,
			IDialogConstants.OK_LABEL,
			true);
		createButton(
			parent,
			IDialogConstants.CANCEL_ID,
			IDialogConstants.CANCEL_LABEL,
			false);
	}
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(dialogTitleString);
	}
}
