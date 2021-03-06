
package org.inria.mtl.editors.actions;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.texteditor.TextEditorAction;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.builders.MTLModel;
import org.inria.mtl.editors.MTLUnitEditor;

/**
 * Class that defines the action of compiling the current MTL file
 */

public class MTLCompileAction extends TextEditorAction {

	
	private static MTLCompileAction instance = new MTLCompileAction();
	private MTLUnitEditor editor;

	/**
	 * Constructs and updates the action.
	 */
	private MTLCompileAction() {
		super(ActionMessages.getResourceBundle(), "CompilerAction.", null); //$NON-NLS-1$
		this.setEditor(editor);
		update();
	}

	public static MTLCompileAction getInstance() {
		return instance;
	}

	/**
	 * Code called when the action is fired.
	 */
	public void run() {

		IFile fileToCompile = findCurrentMTLFile();
		System.out.println("File :"+fileToCompile.getName());
		if (fileToCompile == null) {
			// should never happen
			System.err.println("Error : no file in the editor");
			// should throw an exception
			return;
		}

		try {
			
			if (fileToCompile.getType() == IResource.FILE) {
				//Change the timestamp of the parent folder
				if (fileToCompile.getParent().getType()==IResource.FOLDER){
				
					IFolder srcFolder = (IFolder)fileToCompile.getParent();
					IProject currentProject=srcFolder.getProject();
					System.out.println("Folder :"+srcFolder.getName()+"   "+srcFolder.getFullPath()+"   project :"+currentProject);
					
					String newGen = new Long(srcFolder.getModificationStamp()-1).toString();
					srcFolder.setPersistentProperty(new QualifiedName(MTLPlugin.PLUGIN_ID, MTLModel.TLL_LASTGENTIME), newGen);
					boolean i=MTLPlugin.instance().getModel(currentProject).processResource(srcFolder);
					
					}
			}

			
			// parse the buffer to find the errors and create markers
			//createMarkers(compilerOutput, fileToCompile);

			// And refresh the compilation unit folder
			fileToCompile.getParent().refreshLocal(IResource.DEPTH_ONE, null);

		} catch (Exception e) {
			// $$$ should throw the exception again
			System.err.println("Problem");
			e.printStackTrace();
		}
	}


	/**
	 * Finds the file that's currently opened in the MTL Editor
	 */
	protected IFile findCurrentMTLFile() {
		IWorkbenchPage page= MTLPlugin.getActivePage();
		
		System.out.println("Recherche du fichier");
		if (page != null) {
				IEditorPart part= page.getActiveEditor();
				if (part != null) {
					IEditorInput editorInput= part.getEditorInput();
					if (editorInput != null) {
						if (editorInput instanceof IFileEditorInput)
							return ((IFileEditorInput) editorInput).getFile();
							
					}
				}
			}
			// if nothing was found, which should never happen
		return null;
	}



	/**
	 * Creates a string buffer from the given input stream
	 */
	protected String getStringFromStream(InputStream stream) throws IOException {
		StringBuffer buffer = new StringBuffer();
		byte[] b = new byte[100];
		int finished = 0;
		while (finished != -1) {
			finished = stream.read(b);
			if (finished != -1) {
				String current = new String(b, 0, finished);
				buffer.append(current);
			}
		}
		return buffer.toString();
	}

	/**
	 * Create markers according to the compiler output
	 */
//	protected void createMarkers(String output, IFile file) throws CoreException {
//		// first delete all the previous markers
//		file.deleteMarkers(IMarker.PROBLEM, false, 0);
//
//		// and then create the new ones
//		Vector result = new Vector();
//		String fullPath = file.getName();
//
//		StringTokenizer tokenizer = new StringTokenizer(output, "\n"); //$NON-NLS-1$
//		while (tokenizer.hasMoreElements()) {
//			String current = (String) tokenizer.nextElement();
//			if (current.indexOf(fullPath) != -1) {
//				String line =
//					current.substring(
//						current.indexOf(fullPath) + fullPath.length(),
//						current.length());
//				String errorsLocation = line.substring(1, line.indexOf(":") - 1); //$NON-NLS-1$
//				String message = line.substring(line.indexOf(":") + 2, line.length() - 1); //$NON-NLS-1$
//
//				int lineNumber =
//					Integer.parseInt(errorsLocation.substring(0, errorsLocation.indexOf(",")));	//$NON-NLS-1$
//
//				Hashtable attributes = new Hashtable();
//				MarkerUtilities.setMessage(attributes, message);
//				if (message.startsWith(ERROR))
//					attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
//				else if (message.startsWith(WARNING))
//					attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_WARNING));
//				else
//					attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_INFO));
//				MarkerUtilities.setLineNumber(attributes, lineNumber);
//				MarkerUtilities.createMarker(file, attributes, IMarker.PROBLEM);
//
//			}
//		}
//
//	}


}