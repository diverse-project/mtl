/*
* $Id: MTLConsole.java,v 1.6 2004-06-24 09:23:27 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.views;

import org.apache.log4j.Level;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.inria.mtl.plugin.MTLPlugin;
import org.inria.mtl.plugin.preferences.PreferenceConstants;
import org.inria.mtl.plugin.views.actions.ClearAction;
import org.inria.mtl.plugin.views.actions.ServerAction;
import org.inria.mtl.plugin.views.controller.Controller;
import org.inria.mtl.plugin.views.controller.ControllerAdapter;
import org.inria.mtl.plugin.views.server.Entry;

/**
 * The MTLConsole is used to display the output if you start 
 * @see ViewPart
 */
public class MTLConsole extends ViewPart {

  public static final String CONSOLE_ID =
	"org.inria.mtl.plugin.views.mtlconsoleview";
  public static ServerAction serverAction;
  public static  ClearAction clearAction;
  private Display display;
  private ViewContentProvider contentProvider;
  private static TableViewer viewer;
  
  
  private Action doubleClickAction;
  
  /**
   * The constructor.
   */
  public MTLConsole() {
	Controller.getInstance().acquaint(this);
	//System.out.println()
	makeActions();
	serverAction.run();
	serverAction.setRunning(true);
	//serverAction.setRunning(true);
  }
	
  class SelectionSortAdapter extends SelectionAdapter
  {
	  private int criteria;
	  private boolean ascending = true;
		
	  public SelectionSortAdapter(int criteria)
	  {
		  this.criteria = criteria;
	  }
		
	  public void widgetSelected(SelectionEvent e)
	  {
		  ascending = !ascending;
		  viewer.setSorter(new Sorter(criteria, ascending));
	  }
  }
  /**
   * Insert the method's description here.
   * @see ViewPart#createPartControl
   */
  public void createPartControl(Composite parent) {
		display = parent.getDisplay();
		contentProvider = new ViewContentProvider();

		final Table table = new Table(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);
		
		table.setLinesVisible(false);
		table.setHeaderVisible(true);

		TableColumn column;

		for (int i = 0; i < TableModel.getColumnCount(); i++)
		{
			column = new TableColumn(table, SWT.LEFT);
			column.setText(TableModel.getColumnHeader(i));		
			column.addSelectionListener(new SelectionSortAdapter(TableModel.getColumnId(i)));
			column.setWidth(TableModel.getColumnHeader(i).length()*10+80);			
		}

		viewer = new TableViewer(table);
		viewer.setContentProvider(contentProvider);
		viewer.setLabelProvider(new ViewLabelProvider()); // must be AFTER columns!
		viewer.setUseHashlookup(true);
		viewer.setInput(Controller.getInstance().getLogfile());
		viewer.setSorter(new Sorter(TableModel.TIME));
	

		//makeActions();
		//System.out.println("MAKE ACTIONS");
		//serverAction.run();
		//serverAction.setRunning(true);
		hookContextMenu();
		contributeToActionBars();
		
	Controller.getInstance().addListener(new ControllerAdapter()
	{
		public void serverUp()
		{
			serverAction.setRunning(true);
		}

		public void serverDown()
		{
			serverAction.setRunning(false);
		}

		public void update(Entry entry)
		{
			//final RGB rgb2 = new RGB(255, 255, 255);
			getDisplay().asyncExec(new Runnable()
			{
				public void run()
				{	
//					try{
//							viewer.refresh();
//					}catch (Exception E){
//						System.out.println("Erreur refresh "+E);
//					}
					
					try{
					int k=Controller.getInstance().getLogfile().toArray().length;	
					Object[] oo = contentProvider.getElements(null);
					int i = viewer.getTable().getItemCount();
//					System.out.println(viewer.getTable().getItems().toString());
					System.out.println("run MTLConsole :Contents :"+oo.length+" viewer :"+i+"  nb in log :"+k);
//					System.out.println("test-1 ");
					Object o = oo[i];
					//System.out.println("test0 ");
					viewer.add(o);
					//System.out.println("test1 ");
					//viewer.getTable().getItem(i-1).setBackground(new Color(getDisplay(),rgb2));
					viewer.getTable().getItem(i).setBackground(new Color(getDisplay(), getRowBackgroundRGB((Entry) o, i)));
					//System.out.println("test2 :"+((Entry) o).toString()+"   "+((Entry) o).getMessage()+"  "+((Entry) o).getLevel());;
					viewer.reveal(o);
					//System.out.println("test3 :"+getRowBackgroundRGB((Entry) o, i));
					}catch(Exception E){
						System.out.println("MTLConsole Run error:"+E);
						
					}
				}
					
				public RGB getRowBackgroundRGB(Entry entry, int index)
				{
						Controller ctrl = Controller.getInstance();
						RGB rgb;
						IPreferenceStore store=MTLPlugin.getDefault().getPreferenceStore();
						
						switch (entry.getLevel().toInt())
						{
							case Level.DEBUG_INT :
								rgb = PreferenceConverter.getColor(ctrl.getPlugin().getPreferenceStore(), PreferenceConstants.EDITOR_DEBUG_INDICATION_COLOR);
								break;

							case Level.INFO_INT :
								rgb = PreferenceConverter.getColor(ctrl.getPlugin().getPreferenceStore(), PreferenceConstants.EDITOR_INFO_INDICATION_COLOR);
								break;

							case Level.WARN_INT :
								rgb = PreferenceConverter.getColor(ctrl.getPlugin().getPreferenceStore(), PreferenceConstants.EDITOR_WARNING_INDICATION_COLOR);
								break;
							case Level.ERROR_INT :
								rgb = PreferenceConverter.getColor(ctrl.getPlugin().getPreferenceStore(), PreferenceConstants.EDITOR_PROBLEM_INDICATION_COLOR);
								break;

							case Level.FATAL_INT :
								rgb = PreferenceConverter.getColor(ctrl.getPlugin().getPreferenceStore(), PreferenceConstants.EDITOR_FATAL_INDICATION_COLOR);
								break;

							default :
								rgb = new RGB(255, 255, 255);
								break;
						}

						final int factor = 17;

						// darken every 2nd row
						if (index % 2 != 0)
						{
							if (rgb.red >= factor)
								rgb.red = rgb.red - factor;
							if (rgb.green >= factor)
								rgb.green = rgb.green - factor;
							if (rgb.green >= factor)
								rgb.blue = rgb.blue - factor;
						}

						return rgb;
					}
			});
		}
	});
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
	Menu menu = menuMgr.createContextMenu(viewer.getControl());
	viewer.getControl().setMenu(menu);
	getSite().registerContextMenu(menuMgr, viewer);
  }

    
  private void contributeToActionBars() {
	IActionBars bars = getViewSite().getActionBars();
	fillLocalPullDown(bars.getMenuManager());
	fillLocalToolBar(bars.getToolBarManager());
  }

  private void fillLocalPullDown(IMenuManager manager) {
	manager.add(serverAction);
	manager.add(new Separator());
	manager.add(clearAction);
	manager.add(new Separator());
  }

  private void fillContextMenu(IMenuManager manager) {
	manager.add(serverAction);
	manager.add(clearAction);
	// Other plug-ins can contribute there actions here
	manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
  }

  
  private void fillLocalToolBar(IToolBarManager manager)
	  {
		  manager.add(serverAction);
		  manager.add(clearAction);
	  }
	public ViewContentProvider getContentProvide()
		{
			return contentProvider;
		}
	
		private void makeActions()
		{
			serverAction = new ServerAction(this);
			clearAction = new ClearAction(this);

			doubleClickAction = new Action()
			{
				public void run()
				{
					ISelection selection = viewer.getSelection();
					Object obj = ((IStructuredSelection) selection).getFirstElement();
					//showMessage("Double-click detected on " + obj.toString());
				}
			};
		}

		private void hookDoubleClickAction()
		{
			viewer.addDoubleClickListener(new IDoubleClickListener()
			{		
				public void doubleClick(DoubleClickEvent event)
				{
					doubleClickAction.run();
				}
			});
		}
	
//		public void showMessage(String message)
//		{
//			MessageDialog.openInformation(viewer.getControl().getShell(), MTLPlugin.PLUGIN_ID, message);
//		}

		/**
		 * Passing the focus request to the viewer's control.
		 */
		public void setFocus()
		{
			viewer.getControl().setFocus();
		}
		/**
		 * @return Returns the display.
		 */
		public Display getDisplay()
		{
			return this.display;
		}

		/**
		 * 
		 */
		public void refresh()
		{
			try{
				if (viewer!=null){
					viewer.refresh();
				}else{
					System.out.println("VIEWER NULL");
				}
			}catch (Exception E){
				// A voir
				//System.out.println("cell active :"+viewer.isCellEditorActive());
				//System.out.println("Pb refresh :"+E.getMessage());
				//System.out.println("Pb refresh cause :"+E.getCause());
			}
			
					
		}
		
		public static MTLConsole getConsole() {
		IWorkbenchPage page =
			PlatformUI
				.getWorkbench()
				.getActiveWorkbenchWindow()
				.getActivePage();
		MTLConsole console = (MTLConsole) page.findView(MTLConsole.CONSOLE_ID);
		return console;
		}
		
		public static void cleanConsole(){
			try{
				System.out.println("Console clear"+(serverAction==null));
			  if (!(serverAction==null)){
				if (serverAction.isRunning){
					serverAction.run();
					serverAction.setRunning(false);
				}
				Controller.getInstance().clear();
//				Voir les actions du menu
			//	   MTLPlugin.MenuAction=false;
			  }
				
		
			}catch (Exception E){
				
				System.out.println("Erreur Console clear :");
			}
			
		}
 


}