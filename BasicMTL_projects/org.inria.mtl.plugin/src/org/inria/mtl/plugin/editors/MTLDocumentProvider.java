package org.inria.mtl.plugin.editors;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.DefaultPartitioner;
import org.eclipse.ui.editors.text.FileDocumentProvider;
import org.eclipse.jface.text.ILineTracker;
import org.eclipse.jface.text.DefaultLineTracker;

//import org.irisa.mtl.plugin.editors.*;

/** 
 * The CSharpDocumentProvider provides the IDocuments used by c sharp editors.
 */

public class MTLDocumentProvider extends FileDocumentProvider {

	private final static String[] TYPES= new String[] { MTLPartitionScanner.MTL_DOC, MTLPartitionScanner.MTL_MULTILINE_COMMENT };

	private static MTLPartitionScanner scanner= null;

	public MTLDocumentProvider() {
		super();
	}
	
	/* (non-Javadoc)
	 * Method declared on AbstractDocumentProvider
	 */
	 protected IDocument createDocument(Object element) throws CoreException {
		IDocument document= super.createDocument(element);
		if (document != null) {
			IDocumentPartitioner partitioner= createMTLPartitioner();
			partitioner.connect(document);
			document.setDocumentPartitioner(partitioner);
		}
		return document;
	}
	
	/**
	 * Return a partitioner for .cs files.
	 */
	 private DefaultPartitioner createMTLPartitioner() {
		return new DefaultPartitioner(getMTLPartitionScanner(), TYPES);
	}
	
	/**
	 * Return a scanner for creating c sharp partitions.
	 */
	 private MTLPartitionScanner getMTLPartitionScanner() {
		if (scanner == null)
			scanner= new MTLPartitionScanner();
		return scanner;
	}
	
	/**
	   * Creates a line tracker working with the same line delimiters as the document
	   * of the given element. Assumes the element to be managed by this document provider.
	   * 
	   * @param element the element serving as blue print
	   * @return a line tracker based on the same line delimiters as the element's document
	   */
	  public ILineTracker createLineTracker(Object element) {
		return new DefaultLineTracker();
	  }
}
