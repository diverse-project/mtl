/*
* $Id: ExceptionHandler.java,v 1.2 2004-08-26 12:40:55 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.completion.link;

import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;

import org.inria.mtl.MTLPlugin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
 

public class ExceptionHandler {
	private static ExceptionHandler fgInstance= new ExceptionHandler();
	
	public static void log(Throwable t, String message) {
		MTLPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, MTLPlugin.PLUGIN_ID, 
			IStatus.ERROR, message, t));
	}
	
	public static void handle(CoreException e, String title, String message) {
		handle(e, MTLPlugin.getActiveWorkbenchShell(), title, message);
	}
	
	public static void handle(CoreException e, Shell parent, String title, String message) {
		fgInstance.perform(e, parent, title, message);
	}
	
	public static void handle(InvocationTargetException e, String title, String message) {
		handle(e, MTLPlugin.getActiveWorkbenchShell(), title, message);
	}
	
	public static void handle(InvocationTargetException e, Shell parent, String title, String message) {
		fgInstance.perform(e, parent, title, message);
	}

	protected void perform(CoreException e, Shell shell, String title, String message) {
		MTLPlugin.log(e);
		IStatus status= e.getStatus();
		if (status != null) {
			ErrorDialog.openError(shell, title, message, status);
		} else {
			displayMessageDialog(e, e.getMessage(), shell, title, message);
		}
	}

	protected void perform(InvocationTargetException e, Shell shell, String title, String message) {
		Throwable target= e.getTargetException();
		if (target instanceof CoreException) {
			perform((CoreException)target, shell, title, message);
		} else {
			MTLPlugin.log(e);
			if (e.getMessage() != null && e.getMessage().length() > 0) {
				displayMessageDialog(e, e.getMessage(), shell, title, message);
			} else {
				displayMessageDialog(e, target.getMessage(), shell, title, message);
			}
		}
	}

	private void displayMessageDialog(Throwable t, String exceptionMessage, Shell shell, String title, String message) {
		StringWriter msg= new StringWriter();
		if (message != null) {
			msg.write(message);
			msg.write("\n\n");
		}
		if (exceptionMessage == null || exceptionMessage.length() == 0)
			msg.write(LinkedPositionMessages.getString("ExceptionDialog.seeErrorLogMessage"));
		else
			msg.write(exceptionMessage);
		MessageDialog.openError(shell, title, msg.toString());			
	}	
}