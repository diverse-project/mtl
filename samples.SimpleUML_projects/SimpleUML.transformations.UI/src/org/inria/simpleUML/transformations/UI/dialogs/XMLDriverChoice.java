/* $Id: XMLDriverChoice.java,v 1.2 2004-10-26 10:03:28 dvojtise Exp $
 * Created on 19 août 2004
 * Authors: dvojtise
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.simpleUML.transformations.UI.dialogs;



import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author dvojtise
 */
public class XMLDriverChoice extends Dialog {

	private Text DestFileNameText;
	private Text SourceFileNameText;
	private Text ActionDescriptionText;
	private Button mdrButton;
	private Button modFactButton;
	
	private String destFileNameString;
	private String sourceFileNameString;
	private String actionDescriptionString;
	private String dialogTitleString;
	private boolean bIsMDR = true;
	private boolean bIsModFact = false;
	
	public XMLDriverChoice(String sourceFileName, String destFileName, String theDialogTitleString,String actionDescription, Shell parentShell) {
		super(parentShell);
		sourceFileNameString= sourceFileName;
		destFileNameString=destFileName;
		actionDescriptionString=actionDescription;
		dialogTitleString=theDialogTitleString;
	}
	/* Query method of the resulting state of the dialog */
	public boolean isMDR()
	{	
		return bIsMDR;
	}
	public boolean isModFact()
	{	
		return 	bIsModFact;
	}
	public String getDestFileName()
	{	
		return 	destFileNameString;	
	}
	
	/* creation of the dialog */
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		area.setLayout(new FillLayout(SWT.VERTICAL));
			{
							final Group group_info = new Group(area, SWT.NONE);
							group_info.setText("Transformation action");
							group_info.setLayout(new FillLayout());
							{
								ActionDescriptionText = new Text(group_info, SWT.MULTI | SWT.READ_ONLY);
								ActionDescriptionText.setText(actionDescriptionString);
							}
			}
			{
				final Group group_driver_selection = new Group(area, SWT.NONE);
				group_driver_selection.setBounds(74, 27, 68, 48);
				group_driver_selection.setText("Select Driver");
				group_driver_selection.setLayout(new FillLayout(SWT.VERTICAL));
				{
					mdrButton = new Button(group_driver_selection, SWT.RADIO);
					mdrButton.addMouseListener(new MouseAdapter() {
						public void mouseUp(MouseEvent e) 
						{
							bIsMDR = mdrButton.getSelection();
							bIsModFact =modFactButton.getSelection();
						}
					});
					mdrButton.setText("MDR");
					mdrButton.setSelection(bIsMDR);
					
				}
				{
					modFactButton = new Button(group_driver_selection, SWT.RADIO);
					modFactButton.addMouseListener(new MouseAdapter() {
						public void mouseUp(MouseEvent e) {
							bIsModFact =modFactButton.getSelection();
							bIsMDR = mdrButton.getSelection();
						}
					});
					modFactButton.setText("ModFact");
					mdrButton.setSelection(bIsModFact);
				}
			}
		{
			final Group group_files = new Group(area, SWT.NONE);
			group_files.setLayout(new FillLayout(SWT.VERTICAL));
			group_files.setText("Files");
			group_files.setBounds(45, 120, 270, 55);
			{
				final Label label = new Label(group_files, SWT.NONE);
				label.setText("Source file");
			}
			{
				SourceFileNameText = new Text(group_files, SWT.BORDER);
				SourceFileNameText.setEnabled(false);
				SourceFileNameText.setText(sourceFileNameString);
			}
			{
				final Label label = new Label(group_files, SWT.NONE);
				label.setText("Target file");
			}
			{
				DestFileNameText = new Text(group_files, SWT.BORDER);
				DestFileNameText.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent e) {
						destFileNameString = DestFileNameText.getText();
					}
				});
				DestFileNameText.setText(destFileNameString);
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
