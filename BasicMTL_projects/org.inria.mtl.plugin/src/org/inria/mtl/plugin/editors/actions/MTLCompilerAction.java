package org.inria.mtl.plugin.editors.actions;

import java.io.*;
import java.util.*;

import fr.improve.csharp.ui.internal.CSharpPropertyPage;
import fr.improve.csharp.ui.internal.CSharpUIPlugin;
import fr.improve.csharp.ui.internal.ICSharpPreferenceConstants;
import fr.improve.csharp.ui.views.CSharpConsole;

import org.irisa.mtl.plugin.*;
import org.inria.mtl.plugin.preferences.*;
import org.inria.mtl.plugin.views.MTLConsole;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.ui.*;
import org.eclipse.ui.part.MessagePage;
import org.eclipse.ui.texteditor.*;
import org.eclipse.ui.views.tasklist.TaskList;

/**
 * Class that defines the action of compiling the current C Sharp file
 */

public class MTLCompilerAction extends TextEditorAction {

	public static final String EXE = "exe"; //$NON-NLS-1$
	public static final String WINEXE = "winexe"; //$NON-NLS-1$
	public static final String LIBRARY = "library"; //$NON-NLS-1$
	public static final String MODULE = "module"; //$NON-NLS-1$
	
	private static final String ERROR = "error"; //$NON-NLS-1$
	private static final String WARNING = "warning"; //$NON-NLS-1$

	private static MTLCompilerAction instance = new MTLCompilerAction();

	private static String cSharpCompilerLocation; //$NON-NLS-1$


	/**
	 * Constructs and updates the action.
	 */
	private MTLCompilerAction() {
		super(MTLMessages.getString("CompilerAction.")); //$NON-NLS-1$
		update();

		cSharpCompilerLocation =
			CSharpUIPlugin.getDefault().getPreferenceStore().getString(
				PreferenceConstants.C_SHARP_COMPILER_PATH);
		if (cSharpCompilerLocation.equals("")) {
			cSharpCompilerLocation = null;
		}
	}

	public static MTLCompilerAction getInstance() {
		return instance;
	}

	/**
	 * Code called when the action is fired.
	 */
	public void run() {

		if (cSharpCompilerLocation==null) {
			MessageBox infoBox = new MessageBox(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.ICON_WARNING | SWT.OK);
			infoBox.setMessage(ActionMessages.getString("CompilerAction.messageBoxContent"));
			infoBox.setText(ActionMessages.getString("CompilerAction.messageBoxTitle"));
			infoBox.open();
			return;
		}

		IFile fileToCompile = findCurrentCSharpFile();
		if (fileToCompile == null) {
			// should never happen
			System.err.println("Error : no file in the editor");
			// should throw an exception
			return;
		}

		Runtime r = Runtime.getRuntime();

		try {

			String command = buildCommand(fileToCompile);
			
			String filePath = fileToCompile.getLocation().toOSString();
			String fileFolderPath =	filePath.substring(0, filePath.length() - fileToCompile.getName().length());			

			// runs the command
			Process p = r.exec(command.toString(), new String[] {}, new File(fileFolderPath));

			// gets the input stream to have the post-compile-time information
			InputStream stream = p.getInputStream();

			// and get the string from it
			String compilerOutput = getStringFromStream(stream);

			// prints out the information
			printResultInConsole(compilerOutput);

			// parse the buffer to find the errors and create markers
			createMarkers(compilerOutput, fileToCompile);

			// And refresh the compilation unit folder
			fileToCompile.getParent().refreshLocal(IResource.DEPTH_ONE, null);

		} catch (IOException e) {
			// $$$ should throw the exception again
			System.err.println("Problem");
			e.printStackTrace();
		} catch (CoreException e) {
			// $$$ do something here !
		}
	}


	/**
	 * Finds the file that's currently opened in the C# Text Editor
	 */
	protected IFile findCurrentCSharpFile() {
		ITextEditor editor = getTextEditor();

		IEditorInput editorInput = null;
		if (editor != null) {
			editorInput = editor.getEditorInput();
		}

		if (editorInput instanceof IFileEditorInput)
			return ((IFileEditorInput) editorInput).getFile();

		// if nothing was found, which should never happen
		return null;
	}

	/**
	 * Builds the command for compiling the current file. <br/>
	 * It uses the persistent properties of the file to know the
	 * compile-time arguments.
	 */
	protected String buildCommand(IFile fileToCompile) {
		StringBuffer command = new StringBuffer(cSharpCompilerLocation);
		command.append(" "); //$NON-NLS-1$

		// retrieves the persistent properties
		String outputName = null;
		try {
			outputName =
				fileToCompile.getPersistentProperty(CSharpPropertyPage.outputNameQName);
		} catch (CoreException e) {
		}

		String kindOfTarget = null;
		try {
			kindOfTarget =
				fileToCompile.getPersistentProperty(CSharpPropertyPage.targetQName);
		} catch (CoreException e) {
		}

		String docFileName = null;
		try {
			docFileName =
				fileToCompile.getPersistentProperty(CSharpPropertyPage.docFileNameQName);
		} catch (CoreException e) {
		}

		String refFileNames = null;
		try {
			refFileNames =
				fileToCompile.getPersistentProperty(CSharpPropertyPage.refFileTextQName);
		} catch (CoreException e) {
		}

		String moduleNames = null;
		try {
			moduleNames =
				fileToCompile.getPersistentProperty(CSharpPropertyPage.moduleTextQName);
		} catch (CoreException e) {
		}

		String otherArgs = null;
		try {
			otherArgs =
				fileToCompile.getPersistentProperty(CSharpPropertyPage.otherArgsQName);
		} catch (CoreException e) {
		}

		// give the right name
		command.append("/out:"); //$NON-NLS-1$
		if (outputName != null && !outputName.trim().equals("")) { //$NON-NLS-1$
			// an output name has been specified
			command.append(outputName.trim());
		} else {
			// no output name has been specified: we give the same name as the
			// source code file
			String fileName = fileToCompile.getName();
			// length()-2 to remove the ".cs" extension
			command.append(fileName.substring(0, fileName.length() - 3));
		}
		// and now add the corresponding extension
		if (kindOfTarget != null) {
			if (kindOfTarget.equals(MTLCompilerAction.EXE)
				|| kindOfTarget.equals(MTLCompilerAction.WINEXE))
				command.append(".exe"); //$NON-NLS-1$
			else if (kindOfTarget.equals(MTLCompilerAction.LIBRARY))
				command.append(".dll"); //$NON-NLS-1$
			else if (kindOfTarget.equals(MTLCompilerAction.MODULE))
				command.append(".netmodule"); //$NON-NLS-1$
		} else {
			command.append(".exe"); //$NON-NLS-1$
		}
		command.append(" "); //$NON-NLS-1$

		if (kindOfTarget != null && !kindOfTarget.equals(MTLCompilerAction.EXE)) {
			command.append("/t:"); //$NON-NLS-1$
			command.append(kindOfTarget);
			command.append(" "); //$NON-NLS-1$
		}

		if (docFileName != null && !docFileName.trim().equals("")) { //$NON-NLS-1$
			command.append("/doc:"); //$NON-NLS-1$
			command.append(docFileName.trim());
			command.append(" "); //$NON-NLS-1$
		}

		if (refFileNames != null && !refFileNames.trim().equals("")) { //$NON-NLS-1$
			command.append("/r:"); //$NON-NLS-1$
			command.append(refFileNames.trim());
			command.append(" "); //$NON-NLS-1$
		}

		if (moduleNames != null && !moduleNames.trim().equals("")) { //$NON-NLS-1$
			command.append("/addmodule:"); //$NON-NLS-1$
			command.append(moduleNames.trim());
			command.append(" "); //$NON-NLS-1$
		}

		command.append(fileToCompile.getName());

		return command.toString();
	}
	

	/**
	 * Prints out the string represented by the string buffer
	 */
	protected void printResultInConsole(String output) {
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			CSharpConsole console = (CSharpConsole)page.findView(CSharpConsole.CONSOLE_ID);
			
			if (console!=null) {
				console.setOutputText(output);
			} else if (CSharpUIPlugin.getDefault().getPreferenceStore().getBoolean(ICSharpPreferenceConstants.SHOW_OUTPUT_IN_CONSOLE)==true) {
				page.showView(CSharpConsole.CONSOLE_ID);
				console = (CSharpConsole)page.findView(CSharpConsole.CONSOLE_ID);			
				console.setOutputText(output);
			}
		} catch (PartInitException e) {
			CSharpUIPlugin.getDefault().getLog().log(
				new Status(
					IStatus.ERROR,
					CSharpUIPlugin.PLUGIN_ID,
					0,
					ActionMessages.getString("CompilerAction.consoleViewOpeningProblem"),
					e));
		}
		
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
	protected void createMarkers(String output, IFile file) throws CoreException {
		// first delete all the previous markers
		file.deleteMarkers(IMarker.PROBLEM, false, 0);

		// and then create the new ones
		Vector result = new Vector();
		String fullPath = file.getName();

		StringTokenizer tokenizer = new StringTokenizer(output, "\n"); //$NON-NLS-1$
		while (tokenizer.hasMoreElements()) {
			String current = (String) tokenizer.nextElement();
			if (current.indexOf(fullPath) != -1) {
				String line =
					current.substring(
						current.indexOf(fullPath) + fullPath.length(),
						current.length());
				String errorsLocation = line.substring(1, line.indexOf(":") - 1); //$NON-NLS-1$
				String message = line.substring(line.indexOf(":") + 2, line.length() - 1); //$NON-NLS-1$

				int lineNumber =
					Integer.parseInt(errorsLocation.substring(0, errorsLocation.indexOf(",")));	//$NON-NLS-1$

				Hashtable attributes = new Hashtable();
				MarkerUtilities.setMessage(attributes, message);
				if (message.startsWith(ERROR))
					attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
				else if (message.startsWith(WARNING))
					attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_WARNING));
				else
					attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_INFO));
				MarkerUtilities.setLineNumber(attributes, lineNumber);
				MarkerUtilities.createMarker(file, attributes, IMarker.PROBLEM);

			}
		}

	}

	public static void setCompilerLocation(String newLocation) {
		cSharpCompilerLocation = newLocation;
	}

}