/* $Id: CompilerMessage.java,v 1.2 2004-10-18 15:16:09 jpthibau Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.utils.MessagesHandler;

/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CompilerMessage {
	String message;
	String NDC;
	String fileName;
	String className;
	String methodName;
	String lineNumber;
	String messageType;
		
	public CompilerMessage(String type,String msg,String ndc,String file,String className,String methodName,String lineNumber) {
		this.messageType =type;
		this.message = msg;
		this.NDC=ndc;
		this.fileName = file;
		this.className=className;
		this.methodName=methodName;
		this.lineNumber = lineNumber;
	}
	
	public String getMessage() {
		return this.message;
	}

	public String getNDC() {
		return this.NDC;
	}

	public String getFileName() {
		return this.fileName;
	}

	public String getClassName() {
		return this.className;
	}

	public String getMethodName() {
		return this.methodName;
	}

	public String getLineNumber() {
		return this.lineNumber;
	}

	public String getMessageType() {
		return this.messageType;
	}

}
