/*
* $Id: TextBufferFactory.java,v 1.2 2004-08-26 12:40:27 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.completion.template.textmanipulation;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.util.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.IDocumentProvider;

import org.eclipse.jdt.internal.corext.ValidateEditException;
import org.eclipse.jdt.internal.corext.util.IOCloser;
import org.eclipse.jdt.internal.corext.util.Resources;

import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.IJavaStatusConstants;

/* package */ class TextBufferFactory {

	private IDocumentProvider fDocumentProvider;
	private Map fFileValueMap;
	private Map fBufferValueMap;
	
	private static class Value {
		TextBuffer buffer;
		FileEditorInput input;
		IDocument document;
		IAnnotationModel annotationModel;
		int references;
		public Value(TextBuffer b, FileEditorInput i, IDocument d, IAnnotationModel m) {
			buffer= b;
			input= i;
			document= d;
			annotationModel= m;
		}
	}

	public TextBufferFactory() {
		// XXX http://dev.eclipse.org/bugs/show_bug.cgi?id=5170
		// Need way to map a file to a document without knowing any kind of document provider.
		this(JavaPlugin.getDefault().getCompilationUnitDocumentProvider());
	}
	
	public TextBufferFactory(IDocumentProvider provider) {
		fDocumentProvider= provider;
		Assert.isNotNull(fDocumentProvider);
		fFileValueMap= new HashMap(5);
		fBufferValueMap= new HashMap(5);
	}

	public TextBuffer acquire(IFile file) throws CoreException {
		FileEditorInput input= new FileEditorInput(file);	
		
		Value value= (Value)fFileValueMap.get(input);
		if (value != null) {
			value.references++;
			return value.buffer;
		}
		
		fDocumentProvider.connect(input);
		IDocument document= fDocumentProvider.getDocument(input);
		IAnnotationModel annotationModel= fDocumentProvider.getAnnotationModel(input);
		annotationModel.connect(document);
		value= new Value(new TextBuffer(document), input, document, annotationModel);
		fFileValueMap.put(input, value);
		fBufferValueMap.put(value.buffer, value);
		value.references++;
		return value.buffer;
	}
	
	public void release(TextBuffer buffer) {
		final Value value= (Value)fBufferValueMap.get(buffer);
		if (value == null)
			return;
						
		value.references--;
		if (value.references == 0) {
			buffer.release();	
			value.annotationModel.disconnect(value.document);
			fDocumentProvider.disconnect(value.input);
			fFileValueMap.remove(value.input);
			fBufferValueMap.remove(buffer);
		}
	}
	
	public void commitChanges(final TextBuffer buffer, boolean force, IProgressMonitor monitor) throws CoreException {
		final Value value= (Value)fBufferValueMap.get(buffer);
		if (value == null)
			return;
		
		boolean save= force || fDocumentProvider.mustSaveDocument(value.input);
		if (save) {
			IWorkspaceRunnable action= new IWorkspaceRunnable() {
				public void run(IProgressMonitor pm) throws CoreException {
					IStatus status= makeCommittable(value, null);
					if (!status.isOK())
						throw new ValidateEditException(status);
					fDocumentProvider.aboutToChange(value.input);
					fDocumentProvider.saveDocument(pm, value.input, value.document, true);
				}
			};
			try {
				ResourcesPlugin.getWorkspace().run(action, monitor);
			} finally {
				fDocumentProvider.changed(value.input);
			}
		}
	}
	
	public TextBuffer create(IFile file) throws CoreException {
		FileEditorInput input= new FileEditorInput(file);	
		IDocument document= fDocumentProvider.getDocument(input);
		if (document != null) {
			return new TextBuffer(new Document(document.get()));
		} else {
			return createFromFile(file);
		}
	}

	private TextBuffer createFromFile(IFile file) throws CoreException {
		IDocument document;
		// Fix for http://dev.eclipse.org/bugs/show_bug.cgi?id=19319
		InputStream stream= file.getContents();
		InputStreamReader in= null;
		try {		
			document= new Document();
			in= new InputStreamReader(new BufferedInputStream(stream), ResourcesPlugin.getEncoding());
			StringBuffer buffer= new StringBuffer();
			char[] readBuffer= new char[2048];
			int n= in.read(readBuffer);
			while (n > 0) {
				buffer.append(readBuffer, 0, n);
				n= in.read(readBuffer);
			}
			document.set(buffer.toString());
			return new TextBuffer(document);
		} catch (IOException x) {
			IStatus s= new Status(IStatus.ERROR, JavaPlugin.getPluginId(), IJavaStatusConstants.INTERNAL_ERROR, x.getMessage(), x);
			throw new CoreException(s);
		} finally {
			IOCloser.perform(in, stream);
		}
	}
	
	public TextBuffer create(String content) {
		return new TextBuffer(new Document(content));
	}
	
	public void save(TextBuffer buffer, IProgressMonitor pm) throws CoreException {
		Value value= (Value)fBufferValueMap.get(buffer);
		if (value == null)
			throwNotManaged();
		fDocumentProvider.saveDocument(pm, value.input, value.document, true);
	}

	public void aboutToChange(TextBuffer buffer) throws CoreException {
		Value value= (Value)fBufferValueMap.get(buffer);
		if (value == null)
			throwNotManaged();
		fDocumentProvider.aboutToChange(value.input);
	}
		
	public void changed(TextBuffer buffer) throws CoreException {
		Value value= (Value)fBufferValueMap.get(buffer);
		if (value == null)
			throwNotManaged();
		fDocumentProvider.changed(value.input);
	}
	
	private void throwNotManaged() throws CoreException {
		IStatus s= new Status(IStatus.ERROR, JavaPlugin.getPluginId(), 
			IJavaStatusConstants.INTERNAL_ERROR, TextManipulationMessages.getString("TextBufferFactory.bufferNotManaged"), null); //$NON-NLS-1$
		throw new CoreException(s);
	}
	
	public IStatus makeCommittable(TextBuffer buffer, Object context) {
		Value value= (Value)fBufferValueMap.get(buffer);
		if (value == null) {
			// The buffer is not managed. So it can be modified
			return new Status(IStatus.OK, JavaPlugin.getPluginId(), IStatus.OK, "", null); //$NON-NLS-1$;
		}
		return makeCommittable(value, context);
	}

	private IStatus makeCommittable(Value value, Object context) {
		IFile file= value.input.getFile();
		return Resources.makeCommittable(file, context);
	}
}

