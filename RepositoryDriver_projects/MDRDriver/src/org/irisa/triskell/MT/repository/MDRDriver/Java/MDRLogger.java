/*
 * $Id: MDRLogger.java,v 1.2 2004-02-16 15:44:25 dvojtise Exp $
 * Authors : ffondeme
 * Created on 1 avr. 2003
 * 
 * Copyright 2004 - INRIA - LGPL license 
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java;

import java.util.Date;
import java.util.Hashtable;

import org.openide.ErrorManager;
// import org.openide.ErrorManager.Annotation;

/**
 * @author ffondeme
 * Connects MDR logger to the driver logger
 */
public class MDRLogger extends ErrorManager {
	
	public static class Annotation implements ErrorManager.Annotation {
		protected final Date date;
		protected final String localizedMessage;
		protected final String message;
		protected final int severity;
		protected final Throwable stackTrace; 
		
		public Annotation(
			Throwable t,
			int severity,
			String message,
			String localizedMessage,
			Throwable stackTrace,
			Date date) {
			this.date = date;
			this.localizedMessage = localizedMessage;
			this.message = message;
			this.severity = severity;
			this.stackTrace = stackTrace;
		}
		
		/* (non-Javadoc)
		 * @see org.openide.ErrorManager.Annotation#getDate()
		 */
		public Date getDate() {
			return this.date;
		}

		/* (non-Javadoc)
		 * @see org.openide.ErrorManager.Annotation#getLocalizedMessage()
		 */
		public String getLocalizedMessage() {
			return this.localizedMessage;
		}

		/* (non-Javadoc)
		 * @see org.openide.ErrorManager.Annotation#getMessage()
		 */
		public String getMessage() {
			return this.message;
		}

		/* (non-Javadoc)
		 * @see org.openide.ErrorManager.Annotation#getSeverity()
		 */
		public int getSeverity() {
			return this.severity;
		}

		/* (non-Javadoc)
		 * @see org.openide.ErrorManager.Annotation#getStackTrace()
		 */
		public Throwable getStackTrace() {
			return this.stackTrace;
		}

	}

	protected org.apache.log4j.Logger log;
	
	protected Hashtable annotations_throwable = new Hashtable();

	/**
	 * 
	 */
	public MDRLogger(String name) {
		this.log = org.apache.log4j.Logger.getLogger("openide_" + name);
	}

	/* (non-Javadoc)
	 * @see org.openide.ErrorManager#attachAnnotations(java.lang.Throwable, org.openide.ErrorManager.Annotation[])
	 */
	public Throwable attachAnnotations(Throwable t, ErrorManager.Annotation[] arr) {
		this.annotations_throwable.put(t, arr);
		return t;
	}

	/* (non-Javadoc)
	 * @see org.openide.ErrorManager#findAnnotations(java.lang.Throwable)
	 */
	public ErrorManager.Annotation[] findAnnotations(Throwable t) {
		return t == null ? new ErrorManager.Annotation[0] : (ErrorManager.Annotation[])this.annotations_throwable.get(t);
	}

	/* (non-Javadoc)
	 * @see org.openide.ErrorManager#annotate(java.lang.Throwable, int, java.lang.String, java.lang.String, java.lang.Throwable, java.util.Date)
	 */
	public Throwable annotate(
		Throwable t,
		int severity,
		String message,
		String localizedMessage,
		Throwable stackTrace,
		Date date) {
		ErrorManager.Annotation [] currentAnnotations = this.findAnnotations(t);
		ErrorManager.Annotation [] newAnnotations = new ErrorManager.Annotation [currentAnnotations == null ? 1 : (currentAnnotations.length + 1)];
		newAnnotations[0] = new Annotation(t, severity, message, localizedMessage, stackTrace, date);
		if (currentAnnotations != null && currentAnnotations.length > 0)
			System.arraycopy(currentAnnotations, 0, newAnnotations, 1, currentAnnotations.length);
		this.attachAnnotations(t, newAnnotations);
		return t;
	}

	/* (non-Javadoc)
	 * @see org.openide.ErrorManager#notify(int, java.lang.Throwable)
	 */
	public void notify(int severity, Throwable t) {
		switch (severity) {
			case UNKNOWN:
				log.debug(t.getMessage(), t); break;
			case INFORMATIONAL:
				log.debug(t.getMessage(), t); break;
			case WARNING:
				log.warn(t.getMessage(), t); break;
			case USER:
				log.info(t.getMessage(), t); break;
			case EXCEPTION:
				log.error(t.getMessage(), t); break;
			case ERROR:
				log.error(t.getMessage(), t); break;
			default:
				log.debug(t.getMessage(), t); break;
		}
	}

	/* (non-Javadoc)
	 * @see org.openide.ErrorManager#log(int, java.lang.String)
	 */
	public void log(int severity, String s) {
		switch (severity) {
			case UNKNOWN:
				log.debug(s); break;
			case INFORMATIONAL:
				log.debug(s); break;
			case WARNING:
				log.warn(s); break;
			case USER:
				log.info(s); break;
			case EXCEPTION:
				log.error(s); break;
			case ERROR:
				log.error(s); break;
			default:
				log.debug(s); break;
		}

	}

	/* (non-Javadoc)
	 * @see org.openide.ErrorManager#getInstance(java.lang.String)
	 */
	public ErrorManager getInstance(String name) {
		return new MDRLogger(name);
	}

}
