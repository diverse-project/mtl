/*
* $Id: ProjectExploreView.java,v 1.2 2004-08-31 13:46:07 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.views;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.builders.MTLCodeGenerator;
import org.inria.mtl.builders.MTLModel;
import org.inria.mtl.views.TLLCollector.TLLBrowser;
import org.inria.mtl.views.TLLCollector.tllObject;
import org.inria.mtl.views.model.Attribute;
import org.inria.mtl.views.model.Library;
import org.inria.mtl.views.model.MTLNode;
import org.inria.mtl.views.model.Method;
import org.inria.mtl.views.model.Model;

/**
 * Insert the type's description here.
 * @see ViewPart
 */
public class ProjectExploreView extends ViewPart {
	public  static TreeViewer treeViewer;
	protected Text text;
	protected exploreProjectLabelProvider labelProvider;
	private static Display display;
	
	protected Action onlyBoardGamesAction, atLeatThreeItems;
	protected Action booksBoxesGamesAction, noArticleAction;
	protected Action addLibraryAction, removeAction;
	protected ViewerFilter onlyBoardGamesFilter, atLeastThreeFilter;
	protected ViewerSorter librarySorter, noArticleSorter;
	
	protected static MTLNode root;
	
	public static Vector explorer;
	private static ArrayList allLibrairies=new ArrayList();
	
	/**
	 * The constructor.
	 */
	public ProjectExploreView() {
	}
	
	/**
	 * @return Returns the display.
	 */
	public static Display getDisplay()
	{
		return display;
	}

	/*
	 * @see IWorkbenchPart#createPartControl(Composite)
	 */
	public void createPartControl(Composite parent) {
		/* Create a grid layout object so the text and treeviewer
		 * are layed out the way I want. */
		//System.out.println(" NB FILES TLL :"+exploreTllFiles());
		
		display = parent.getDisplay();
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.verticalSpacing = 2;
		layout.marginWidth = 0;
		layout.marginHeight = 2;
		parent.setLayout(layout);
		
		/* Create a "label" to display information in. I'm
		 * using a text field instead of a lable so you can
		 * copy-paste out of it. */
		text = new Text(parent, SWT.READ_ONLY | SWT.SINGLE | SWT.BORDER);
		// layout the text field above the treeviewer
		GridData layoutData = new GridData();
		layoutData.grabExcessHorizontalSpace = true;
		layoutData.horizontalAlignment = GridData.FILL;
		text.setLayoutData(layoutData);
		
		// Create the tree viewer as a child of the composite parent
		treeViewer = new TreeViewer(parent);
		treeViewer.setContentProvider(new exploreProjectContentProvider());
		labelProvider = new exploreProjectLabelProvider();
		treeViewer.setLabelProvider(labelProvider);
		
		treeViewer.setUseHashlookup(true);
		
		// layout the tree viewer below the text field
		layoutData = new GridData();
		layoutData.grabExcessHorizontalSpace = true;
		layoutData.grabExcessVerticalSpace = true;
		layoutData.horizontalAlignment = GridData.FILL;
		layoutData.verticalAlignment = GridData.FILL;
		treeViewer.getControl().setLayoutData(layoutData);
		
		// Create menu, toolbars, filters, sorters.
		createFiltersAndSorters();
		//createActions();
		//createMenus();
		//createToolbar();
		hookListeners();
		
		treeViewer.setInput(getInitalInput());
		treeViewer.expandAll();
	}
	 
	protected void createFiltersAndSorters() {
		atLeastThreeFilter = new ThreeItemFilter();
		onlyBoardGamesFilter = new AttributeFilter();
		librarySorter = new LibrarySorter();
		noArticleSorter = new NoArticleSorter();
	}

	protected void hookListeners() {
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				// if the selection is empty clear the label
				if(event.getSelection().isEmpty()) {
					text.setText("");
					return;
				}
				if(event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection)event.getSelection();
					StringBuffer toShow = new StringBuffer();
					for (Iterator iterator = selection.iterator(); iterator.hasNext();) {
						Object domain = (Model) iterator.next();
						String value = labelProvider.getText(domain);
						toShow.append(value);
						toShow.append(", ");
					}
					// remove the trailing comma space pair
					if(toShow.length() > 0) {
						toShow.setLength(toShow.length() - 2);
					}
					text.setText(toShow.toString());
				}
			}
		});
	}
	
	protected void createActions() {  
		onlyBoardGamesAction = new Action("Only Board Games") {
			public void run() {
				updateFilter(onlyBoardGamesAction);
			}
		};
		onlyBoardGamesAction.setChecked(false);
		
		atLeatThreeItems = new Action("Boxes With At Least Three Items") {
			public void run() {
				updateFilter(atLeatThreeItems);
			}
		};
		atLeatThreeItems.setChecked(false);
		
		booksBoxesGamesAction = new Action("Books, Boxes, Games") {
			public void run() {
				updateSorter(booksBoxesGamesAction);
			}
		};
		booksBoxesGamesAction.setChecked(false);
		
		noArticleAction = new Action("Ignoring Articles") {
			public void run() {
				updateSorter(noArticleAction);
			}
		};
		noArticleAction.setChecked(false);
		
		addLibraryAction = new Action("Add Library") {
			public void run() {
				addNewLibrary();
			}			
		};
		addLibraryAction.setToolTipText("Add a New Library");
		addLibraryAction.setImageDescriptor(MTLPlugin.getImageDescriptor("newBook.gif"));

		removeAction = new Action("Delete") {
			public void run() {
				removeSelected();
			}			
		};
		removeAction.setToolTipText("Delete");
		removeAction.setImageDescriptor(MTLPlugin.getImageDescriptor("remove.gif"));		
	}
	
	/** Add a new book to the selected moving box.
	 * If a moving box is not selected, use the selected
	 * obect's moving box. 
	 * 
	 * If nothing is selected add to the root. */
	protected void addNewLibrary() {
		MTLNode receivingBox;
		if (treeViewer.getSelection().isEmpty()) {
			receivingBox = root;
		} else {
			IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
			Model selectedDomainObject = (Model) selection.getFirstElement();
			if (!(selectedDomainObject instanceof MTLNode)) {
				receivingBox = selectedDomainObject.getParent();
			} else {
				receivingBox = (MTLNode) selectedDomainObject;
			}
		}
		receivingBox.add(Library.newLibrary());
	}

	/** Remove the selected domain object(s).
	 * If multiple objects are selected remove all of them.
	 * 
	 * If nothing is selected do nothing. */
	protected void removeSelected() {
		if (treeViewer.getSelection().isEmpty()) {
			return;
		}
		IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
		/* Tell the tree to not redraw until we finish
		 * removing all the selected children. */
		treeViewer.getTree().setRedraw(false);
		for (Iterator iterator = selection.iterator(); iterator.hasNext();) {
			Model model = (Model) iterator.next();
			MTLNode parent = model.getParent();
			parent.remove(model);
		}
		treeViewer.getTree().setRedraw(true);
	}
	
	protected void createMenus() {
		IMenuManager rootMenuManager = getViewSite().getActionBars().getMenuManager();
		rootMenuManager.setRemoveAllWhenShown(true);
		rootMenuManager.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager mgr) {
				fillMenu(mgr);
			}
		});
		fillMenu(rootMenuManager);
	}


	protected void fillMenu(IMenuManager rootMenuManager) {
		IMenuManager filterSubmenu = new MenuManager("Filters");
		rootMenuManager.add(filterSubmenu);
		filterSubmenu.add(onlyBoardGamesAction);
		filterSubmenu.add(atLeatThreeItems);
		
		IMenuManager sortSubmenu = new MenuManager("Sort By");
		rootMenuManager.add(sortSubmenu);
		sortSubmenu.add(booksBoxesGamesAction);
		sortSubmenu.add(noArticleAction);
	}
	
	
	
	protected void updateSorter(Action action) {
		if(action == booksBoxesGamesAction) {
			noArticleAction.setChecked(!booksBoxesGamesAction.isChecked());
			if(action.isChecked()) {
				treeViewer.setSorter(librarySorter);
			} else {
				treeViewer.setSorter(null);
			}
		} else if(action == noArticleAction) {
			booksBoxesGamesAction.setChecked(!noArticleAction.isChecked());
			if(action.isChecked()) {
				treeViewer.setSorter(noArticleSorter);
			} else {
				treeViewer.setSorter(null);
			}
		}
			
	}
	
	/* Multiple filters can be enabled at a time. */
	protected void updateFilter(Action action) {
		if(action == atLeatThreeItems) {
			if(action.isChecked()) {
				treeViewer.addFilter(atLeastThreeFilter);
			} else {
				treeViewer.removeFilter(atLeastThreeFilter);
			}
		} else if(action == onlyBoardGamesAction) {
			if(action.isChecked()) {
				treeViewer.addFilter(onlyBoardGamesFilter);
			} else {
				treeViewer.removeFilter(onlyBoardGamesFilter);
			}
		}
	}
	
	protected void createToolbar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
		toolbarManager.add(addLibraryAction);
		toolbarManager.add(removeAction);
	}
	
	
	public  static MTLNode getInitalInput() {
		root = new MTLNode();
		MTLNode projectName;
		IProject project=MTLModel.getProject();
		if (project ==null){
			 projectName = new MTLNode("Projet ");
			}else{
			 projectName = new MTLNode("Projet :"+project.getName().toUpperCase());
		}
		//MTLNode games = new MTLNode("Games");
		//MTLNode Librarys = new MTLNode("Libraries involved in the project");
		MTLNode listLib = new MTLNode("Libraries involved in the project");
		ProjectExploreView pe=new ProjectExploreView();
		if (!(pe.exploreTllFiles()==0)){
		root.add(projectName);
		MTLNode lib = new MTLNode("",1);
		MTLNode cl = new MTLNode("",2);
		MTLNode attr = new MTLNode("",3);
		MTLNode met = new MTLNode("",4);
		for (int i=0;i<allLibrairies.size();i++){
			//System.out.println("In all librairies");
			boolean inClass =false;
			Vector v= (Vector)allLibrairies.get(i);
			Iterator it = v.iterator();
			while (it.hasNext()){
				//System.out.println(it.next());
				tllObject item = (tllObject) it.next ();
				//System.out.println("Elt :"+item.getName()+"  typ :"+item.getType()+" cat :"+item.getCategoryName());
				if (item.getCategoryName()==0){
					lib = new MTLNode(item.getName(),1);
					projectName.add(lib);
				}
				if (item.getCategoryName()==1){
					cl = new MTLNode(item.getName(),2);
					lib.add(cl);
					inClass=true;
				}
				if (item.getCategoryName()==2){
					attr = new MTLNode(item.getName(),3);
					Attribute attrib = new Attribute(item.getName(),"",item.getType());
					if (!inClass)lib.add(attrib);
					if (inClass)cl.add(attrib);
				}
				if (item.getCategoryName()==3){
					met = new MTLNode(item.getName(),4);
					Method mt = new Method(item.getName(),"",item.getType());
					if (!inClass)lib.add(mt);
					if (inClass)cl.add(mt);
				}
			}
		}
			
			
	}
		else{
			root.add(projectName);
			MTLNode zeroLib = new MTLNode("Zero Library involved in the project");
			projectName.add(zeroLib);
		  }
		return root;
	}

	/*
	 * @see IWorkbenchPart#setFocus()
	 */
	public void setFocus() {}
	
	/** 
	 * 
	 */
	public static void refresh() 
	{
	try{
		if (getDisplay()==null)return;
		getDisplay().syncExec(new Runnable()
				{
					public void run()
					{	
						//System.out.println("IN REFRESH0");
						try{
							if (treeViewer!=null){
								treeViewer.refresh();
				
							}else{
								System.out.println("treeViewer NULL");
							}
		}catch (Exception e){
			System.out.println("treeViewer  :"+e);
			// A voir
		}
		
				
	}
				}
		);
	 }catch(Exception e){
	 	System.out.println("PBS  :"+e);
	 	//e.printStackTrace();
	 }
	
}
	
	public static void update()
	{
		getDisplay().asyncExec(new Runnable()
		{
			public void run()
			{				
				try{
					treeViewer.setInput(getInitalInput());
					treeViewer.expandAll();
					
				}catch(Exception E){
					System.out.println("TreeViewer error:"+E);
					
				}
			}
		});
		//System.out.println(" NB FILES TLL :"+this.exploreTllFiles());
	}
	
	public int exploreTllFiles(){
		IPreferenceStore store=MTLPlugin.getDefault().getPreferenceStore();
		ArrayList files=new ArrayList();
		allLibrairies=new ArrayList();
		ArrayList dependencies=new ArrayList();
		int nbtll=0;
		//Manage folders
		String tllFolder = MTLCodeGenerator.getTllFolder();
		String RuntimetllFolder = MTLCodeGenerator.getRuntimeTllFolder();
		//System.out.println(" TLL FOLDER :"+tllFolder+"   runtime "+RuntimetllFolder);

		java.util.Vector tllfilenames=new java.util.Vector();
		if (!(tllFolder==null)&&!(RuntimetllFolder==null)){
		String filesList[]=new java.io.File(tllFolder).list();
		String filesList2[]=new java.io.File(RuntimetllFolder).list();
		
		if((filesList == null) || (filesList.length==0))
		{
			//System.out.println("Vide :"+0);
			return 0;
		}
		for (int i=0;i<filesList.length;i++)
		{
			// checks if file exists
			if (filesList[i].endsWith(".tll"))
			 {
				nbtll=nbtll+1;
				
//				MSGHandler.debug(Compiler.class,138,"Including file "+filesList[i]+"...");
				File aFile = new File(tllFolder+filesList[i]);
				if (! aFile.exists()) System.err.println("Cannot find fileName :"+aFile);
				else{
					TLLBrowser tb=new TLLBrowser();
					Vector v=tb.Browse(aFile.getAbsolutePath());
					allLibrairies.add(v);
					files.add(aFile.getAbsolutePath());
					Iterator it = v.iterator();
					while (it.hasNext()){
						tllObject item = (tllObject) it.next ();
						if (item instanceof tllObject){
							//if (!(((tllObject)it).getType()==null)){
								//dependencies[dependencies.length+1]==(((tllObject)it).getType()).
								//System.out.println(" LIB DEPEND :"+item.getType());
								if (!(item.getType()==null)){
									File rFile = new File(RuntimetllFolder+(String)(item.getType().firstElement()).toString().concat(".tll"));
									dependencies.add(rFile.getAbsolutePath());
									//System.out.println(" LIB DEPEND ARG1 :"+(String)(item.getType().firstElement()));
								}
							//}
						}
						//System.out.println(it.next());
					}
							//tb.explorer(v);
					//ProjectExploreView.refresh();
					//ProjectExploreView.update();
				}
//				if (aFile.canRead())
//					filenamesArguments.addElement(sourcesDir+filesList[i]);
//				else				
//					MSGHandler.warn(Compiler.class,143,"File not readable : "+sourcesDir+filesList[i]+" => file ignored !!!");
			 }
			//else MSGHandler.debug(Compiler.class,145,"EXCLUDING file "+filesList[i]+"!!!");
		}
		
		//System.out.println("Plein NBTLL :"+nbtll +"  "+filesList2.length);
		
//		for (int i=0;i<filesList2.length;i++)
//		{
//			System.out.println(" Runtime :"+filesList2[i]);
//		}
		
//		if (filenamesArguments.size() == 0)
//		{
//			MSGHandler.fatal(Compiler.class,150,"No file to process");
//		}
//		else 
//		{
//			compileFromFiles(filenamesArguments, defaultPackagePrefix, defaultTLLPath, TLLLoadingPaths, defaultBinPath);	
//		}
	
		// System.out.println(allLibrairies.size());
		for (int i=0;i<dependencies.size();i++)
			{
			
			if (((String)(dependencies.get(i))).endsWith(".tll"))
			 {
				File aFile = new File(((String)(dependencies.get(i))));
				if (! aFile.exists()) System.err.println("Cannot find fileName :"+aFile);
				else{
					
					if (files.contains(aFile.getAbsolutePath()))return nbtll;
					TLLBrowser tb=new TLLBrowser();
					
					Vector v=tb.Browse(aFile.getAbsolutePath());
					allLibrairies.add(v);
					files.add(aFile.getAbsolutePath());
					Iterator it = v.iterator();
					while (it.hasNext()){
						tllObject item = (tllObject) it.next ();
						if (item instanceof tllObject){
							//if (!(((tllObject)it).getType()==null)){
								//dependencies[dependencies.length+1]==(((tllObject)it).getType()).
//								if (!(item.getType()==null)){
//									File rFile = new File(RuntimetllFolder+(String)(item.getType().firstElement()).toString().concat(".tll"));
//									dependencies.add(rFile.getAbsolutePath());
//								}
							}
						}
						//System.out.println(it.next());
					}
							//tb.explorer(v);
					//ProjectExploreView.refresh();
					//ProjectExploreView.update();
				}
				//System.out.println(" dependencies :"+(String)(dependencies.get(i)));
			}
		    
		    return nbtll;
	}
		   
			
			return 0;
	}

}
