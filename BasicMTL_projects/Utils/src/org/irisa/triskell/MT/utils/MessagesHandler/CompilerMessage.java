/* $Id: CompilerMessage.java,v 1.1 2004-06-09 09:35:08 jpthibau Exp $
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
	String fileName;
	int lineNumber;
	String messageType;
	Class origin;
	int originLineNumber;
		
	public CompilerMessage(String type,String msg,String file,int lineNumber,Class origin,int originLine) {
		this.messageType =type;
		this.message = msg;
		this.fileName = file;
		this.lineNumber = lineNumber;
		this.origin = origin;
		this.originLineNumber = originLine;
	}
	
	public String getMessage() {
		return this.message;
	}

	public String getFileName() {
		return this.fileName;
	}

	public int getLineNumber() {
		return this.lineNumber;
	}

	public String getMessageType() {
		return this.messageType;
	}

	public Class getOrigin() {
		return this.origin;
	}

	public int getOriginLineNumber() {
		return this.originLineNumber;
	}
}
