package org.inria.mtl.plugin.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//import net.sourceforge.phpdt.internal.ui.PHPUiImages;
//import net.sourceforge.phpeclipse.PHPeclipsePlugin;
//import net.sourceforge.phpeclipse.actions.PHPActionMessages;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;


import org.inria.mtl.plugin.MTLPlugin;

/**
 * The PHPConsole is used to display the output if you start MySQL/Apache
 * @see ViewPart
 */
public class MTLConsole extends ViewPart {

  public static final String CONSOLE_ID =
	"org.irisa.mtl.plugin.views.mtlconsoleview";
  private int COMMAND_COMBO_SIZE = 10;

  private TextViewer fViewer = null;
  private Document fDocument = null;
  private StyledText fStyledText;
  // private Combo fCommandCombo;
  //  private ProcessOutputWriter consoleOut;
  //  private ProcessOutputWriter consoleErr;

  // private Action goAction;

  private Action cutAction = new Action() {
	public void run() {
	  fViewer.getTextWidget().cut();
	}
  };
  private Action copyAction = new Action() {
	public void run() {
	  fStyledText.copy();
	}
  };
    private Action pasteAction = new Action() {
      public void run() {
        fViewer.getTextWidget().paste();
      }
    };
  private Action selectAllAction = new Action() {
	public void run() {
	  fStyledText.selectAll();
	}
  };
  private Action clearAction = new Action() {
	public void run() {
	  fStyledText.setText("");
	}
  };
  /**
   * The constructor.
   */
  public MTLConsole() {
  }

  /**
   * Insert the method's description here.
   * @see ViewPart#createPartControl
   */
  public void createPartControl(Composite parent) {
	fViewer = new TextViewer(parent, SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL);
	GridData viewerData = new GridData(GridData.FILL_BOTH);
	fViewer.getControl().setLayoutData(viewerData);
	fViewer.setEditable(false);

	fStyledText = fViewer.getTextWidget();
	fStyledText.setFont(
	  JFaceResources.getFontRegistry().get(JFaceResources.TEXT_FONT));

	cutAction.setText("Cut");
	copyAction.setText("Copy");
	//   pasteAction.setText("Paste");
	selectAllAction.setText("Select All");
	clearAction.setText("Clear MTL Console");
	//clearAction.setImageDescriptor(PHPUiImages.DESC_CLEAR);
	clearAction.setToolTipText("Clear MTL Console");

	IActionBars bars = this.getViewSite().getActionBars();
	bars.setGlobalActionHandler(IWorkbenchActionConstants.CUT, cutAction);
	bars.setGlobalActionHandler(IWorkbenchActionConstants.COPY, copyAction);
	bars.setGlobalActionHandler(IWorkbenchActionConstants.PASTE, pasteAction);

	hookContextMenu();
	//	hookDoubleClickAction();
	contributeToActionBars();

	    appendOutputText("This is the MTL console.\n");
	
  }

  private void createCommandBar(Composite parent) {
	GridData gd = new GridData(GridData.FILL_HORIZONTAL);

  }

 
  private void hookContextMenu() {
	MenuManager menuMgr = new MenuManager("#PopupMenu");
	menuMgr.setRemoveAllWhenShown(true);
	menuMgr.addMenuListener(new IMenuListener() {
	  public void menuAboutToShow(IMenuManager manager) {
		MTLConsole.this.fillContextMenu(manager);
	  }
	});
	Menu menu = menuMgr.createContextMenu(fViewer.getControl());
	fViewer.getControl().setMenu(menu);
	getSite().registerContextMenu(menuMgr, fViewer);
  }

  private void contributeToActionBars() {
	IActionBars bars = getViewSite().getActionBars();
	fillLocalPullDown(bars.getMenuManager());
	fillLocalToolBar(bars.getToolBarManager());
  }

  private void fillLocalPullDown(IMenuManager manager) {
	manager.add(cutAction);
	manager.add(copyAction);
	manager.add(pasteAction);
	manager.add(selectAllAction);
  }

  private void fillContextMenu(IMenuManager manager) {
	manager.add(cutAction);
	manager.add(copyAction);
	manager.add(pasteAction);
	manager.add(selectAllAction);
	// Other plug-ins can contribute there actions here
	manager.add(new Separator("Additions"));
  }

  private void fillLocalToolBar(IToolBarManager manager) {
	manager.add(clearAction);
  }
  /**
   * Insert the method's description here.
   * @see ViewPart#setFocus
   */
  public void setFocus() {
	//   fCommandCombo.forceFocus();
  }

  /**
   * Set the text for the viewer
   */
  public void setOutputText(String text) {
	fDocument = new Document(text);
	fViewer.setDocument(fDocument);
  }

  public void appendOutputText(String text) {
	try {
	  if (fDocument == null) {
		fDocument = new Document(text);
		fViewer.setDocument(fDocument);
	  } else {
		fDocument.replace(fDocument.getLength(), 0, text);
	  }
	} catch (BadLocationException e) {
	}
	//  viewer.setDocument(document);
  }

  public static MTLConsole getInstance() {
	// IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	IWorkbenchPage page =
	  MTLPlugin
		.getDefault()
		.getWorkbench()
		.getActiveWorkbenchWindow()
		.getActivePage();
	MTLConsole console = (MTLConsole) page.findView(MTLConsole.CONSOLE_ID);

//	if (MTLPlugin
//	  .getDefault()
//	  .getPreferenceStore()
//	  .getBoolean(MTLPlugin.SHOW_OUTPUT_IN_CONSOLE) 
//	  == true) {
	  try {
		page.showView(MTLConsole.CONSOLE_ID);
		if (console == null) {
		  console = (MTLConsole) page.findView(MTLConsole.CONSOLE_ID);
		}
	  } catch (PartInitException e) {
		MTLPlugin.getDefault().getLog().log(
		  new Status(
			IStatus.ERROR,
			MTLPlugin.PLUGIN_ID,
			0,
			"",
			e));
	  }
	//}
	return console;
  }

  /**
   * Prints out the string represented by the string 
   */
  public synchronized void write(String output) {
	appendOutputText(output);
  }

  /**
   * Creates a string buffer from the given input stream
   */
  public static String getStringFromStream(InputStream stream)
	throws IOException {
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
   * Finds the file that's currently opened in the PHP Text Editor
   */
  
  class ProcessOutputWriter extends Thread {
	boolean fStreamClosed;
	InputStream fInputStream;

	ProcessOutputWriter(InputStream inputStream) {
	  fInputStream = inputStream;
	  fStreamClosed = false;
	}

	public void closeStream() {
	  fStreamClosed = true;
	  try {
		fInputStream.close();
	  } catch (IOException io) {
	  }
	}

	public void run() {
	  try {
		BufferedReader in =
		  new BufferedReader(new InputStreamReader(fInputStream));

		String line;
		while ((line = in.readLine()) != null) {
		  write(line);
		}
		in.close();
	  } catch (Exception e) {
		e.printStackTrace(System.out);
		if (!fStreamClosed) {
		  //  write("\nPHP Console Exception: "+ e.toString() );
		}
	  } finally {
	  }
	}
  }


}