/*
 * $Id: PoseidonDriverTestPane.java,v 1.2 2005-01-18 16:47:00 dvojtise Exp $
 * Authors : dvojtise
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.PoseidonDriverTest.poseidonLibs;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.jmi.reflect.RefObject;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.*;

import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLSequence;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLString;
import org.netbeans.api.mdr.MDRManager;
import org.netbeans.api.mdr.MDRepository;
import org.omg.uml.UmlPackage;
import org.omg.uml.foundation.core.Attribute;
import org.omg.uml.foundation.core.Stereotype;
import org.omg.uml.foundation.core.UmlClass;
import org.omg.uml.foundation.datatypes.ChangeableKindEnum;
import org.omg.uml.foundation.datatypes.VisibilityKindEnum;
import org.omg.uml.modelmanagement.Model;
import org.omg.uml2.diagraminterchange.*;
import com.gentleware.jboogie.openapi.SmId;

import PoseidonTools.BMTL_StaticMessageHandler;

import com.gentleware.jboogie.openapi.SmId;
import com.gentleware.jboogie.openapi.DiId;
import com.gentleware.poseidon.kernel.PoseidonProject;
import com.gentleware.poseidon.openapi.PoseidonProjectConnector;
import com.gentleware.poseidon.ui.DetailsTab;
import com.gentleware.poseidon.ui.MenuResourceBundle;






/**
 * This Panel show how to use Poseidon Open Api.
 * Please feel free to send an email to support@gentleware.com if you want to make
 * suggestions considering this example, or if you miss something in the example
 */
/**
 * @author Didier Vojtisek
 *
 */
public class PoseidonDriverTestPane extends DetailsTab {

	public final static String PANEL_TITLE = "PoseidonDriverTest";
	
    private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JTextArea textArea;
	private JFrame jFrame;
	
	// get the MDR default repository
	private MDRepository myRepository;
	// get the extent that contains the model
	private UmlPackage umlModel;
	
	// get the model (uses a poseidon operation)
	// private Model currentModel;
    
	 /**
     * Constructor - here we create the UI
     * This will be a new pane in the Poseidon GUI with 3 buttons
     * and a text area
     */
    public PoseidonDriverTestPane() {

        super(MenuResourceBundle.Untitled);
    	this.setTitle(PANEL_TITLE);    // for the display
        this.setName(PANEL_TITLE);    // this is for the lookup
        this.setLayout(null);
        // Now, this is the creation of 2 buttons
        button1 = new JButton("Show content");
        button1.setPreferredSize(new Dimension(300, 25));
        button1.setBounds(50, 20, 200, 25);
        add(button1);
        
		button2 = new JButton("run MTL transformation");
		button2.setPreferredSize(new Dimension(300, 25));
		button2.setBounds(50, 45, 200, 25);
		add(button2);
		
		button3 = new JButton("do something");
		button3.setPreferredSize(new Dimension(300, 25));
		button3.setBounds(50, 70, 200, 25);
		add(button3);

		button4 = new JButton("clear messages");
		button4.setPreferredSize(new Dimension(300, 25));
		button4.setBounds(50, 95, 200, 25);
		add(button4);
		
		button5 = new JButton("ReDipulize");
		button5.setPreferredSize(new Dimension(300, 25));
		button5.setBounds(50, 120, 200, 25);
		add(button5);
		
		button6 = new JButton("Relayout");
		button6.setPreferredSize(new Dimension(300, 25));
		button6.setBounds(50, 145, 200, 25);
		add(button6);
		
		button7 = new JButton("Save & Reload");
		button7.setPreferredSize(new Dimension(300, 25));
		button7.setBounds(50, 170, 200, 25);
		add(button7);

//		This is the creation of the text area
		textArea = new JTextArea();
        textArea.setAutoscrolls(true);
		JScrollPane scrollpan = new JScrollPane(textArea);
		scrollpan.setBounds(325, 1, 600, 330);
		scrollpan.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollpan);
        
		
        
        
		
        this.setVisible(true);
        
        // This is the creation of the listener : it detects every actions
        //which are performed on a button
        MyActionListener myActionListener = new MyActionListener();
        button1.addActionListener(myActionListener);
		button2.addActionListener(myActionListener);
		button3.addActionListener(myActionListener);
		button4.addActionListener(myActionListener);
		button5.addActionListener(myActionListener);
		button6.addActionListener(myActionListener);
		button7.addActionListener(myActionListener);
		
		initDirectoriesRootpath();
		
    }

	/** This is the body of the listener.
	 * It calls the method linked to the pushed button
	 */
	class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
            
			if(e.getSource() == button1) {
				button1Code();
			}
			if(e.getSource() == button2) {
				button2Code();
			}
			if(e.getSource() == button3) {
				button3Code();
			}
			if(e.getSource() == button4) {
				button4Code();
			}
			if(e.getSource() == button5) {
				button5Code();
			}
			if(e.getSource() == button6) {
				button6Code();
			}
			if(e.getSource() == button6) {
				button7Code();
			}
		}
	}
	
    /**
	 * This is the code of the button
	 * It gets the repository access point for the model currently open
	 */
	private void button1Code() {
		Object o;
		// direct access to the repository
		myRepository = MDRManager.getDefault().getDefaultRepository();
		umlModel=getCurrentModelExtent(myRepository); 
		
		// List all models in the extent
		textArea.append("List of models in the extent : \n");
		textArea.append(" - "+listModelsinExtent(umlModel)+"\n");
	
		////////////////////////////////////
		// List each package in the model //
		////////////////////////////////////
		textArea.append("List of elements in the model : \n");
		Iterator it=umlModel.getCore().getUmlClass().refAllOfClass().iterator();
		while (it.hasNext()) {
			o = it.next();
			textArea.append("  + class : "+((UmlClass)o).getName()+"\n");
		}
		it=umlModel.getModelManagement().getUmlPackage().refAllOfClass().iterator();
		while (it.hasNext()) {
			o = it.next();
			textArea.append("  + Package : "+((org.omg.uml.modelmanagement.UmlPackage)o).getName()+"\n");
		}
		textArea.append("\n");
		
		
		
		
	}
	
	private void button2Code() {
		Object o;
		Iterator classIterator;
		Collection classSet=null;
		boolean isRto=false;
		
	/*	// direct access to the repository
		myRepository = MDRManager.getDefault().getDefaultRepository();
		umlModel=getCurrentModelExtent(myRepository);
		
		// get all stereotyped classes of the model (with stereotype "RealTimeObject")
		classSet = selectStereotypedClass("RealTimeObject");
		classIterator = classSet.iterator();
		while (classIterator.hasNext()) {
			o = classIterator.next();
			addAttribute((UmlClass)o);
		}*/
		System.out.println("button2Code begin");
		BMTLString [] params = new BMTLString [1];
		params[0] = new BMTLString("hello param");
	
		try {
			initDirectoriesRootpath();

			BMTL_StaticMessageHandler.initTextArea(textArea);
		    PoseidonTest.BMTLLib_PoseidonTest.TheInstance.BMTL_main(new BMTLSequence(params));
		   
		}
		catch(Exception e)
		{
			System.err.println("Exception: "+ e.getMessage());
			e.printStackTrace();
		} catch (Throwable e) {
			System.err.println("Exception: "+ e.getMessage());
			e.printStackTrace();
		}
		
		
		//PoseidonProjectConnector.getModel().notifyAll();
		
		for (int i = 0; i < PoseidonProjectConnector.getCurrentProject().getUpdateModelListeners().size(); i++)
		{
			textArea.append("notifying an  UpdateModelListener\n");
			com.gentleware.jboogie.openapi.UpdateModelListener modelListener;
			modelListener = (com.gentleware.jboogie.openapi.UpdateModelListener)PoseidonProjectConnector.getCurrentProject().getUpdateModelListeners().get(i);
			try{
				modelListener.updateModel();
			}
			catch (com.gentleware.jboogie.openapi.UpdateModelException e)
			{
				textArea.append(e.getMessage() + "\n");
			}
		}
		// com.gentleware.jboogie.openapi.DiCommandFactory
		
	//	ReDiPulizeAllModelElements();
		
		textArea.append("\n************************\n");
		//ReLayout();

		textArea.append("\n************************\n");
		//SaveAndReload();
				
		
		PoseidonProject currentProject = PoseidonProjectConnector.getCurrentProject();
		currentProject.setNeedsSave(true);
		/*try {
			PoseidonProject.fireProjectChanged(currentProject, currentProject, "LOADED");
		}
		catch (java.beans.PropertyVetoException e)
		{
			System.out.println(e.getMessage());
		}
		*/
		System.out.println("button2Code end");
	}
	private void button3Code() {
		/////////////////////////////////
		// Creation of a new package  //
		////////////////////////////////
		textArea.append("Creation of a new package \n");
		// all packages have been found, try now to had a package in the package testPackage
		org.omg.uml.modelmanagement.UmlPackage myPackage;
		myPackage=umlModel.getModelManagement().getUmlPackage().createUmlPackage("newPackage", VisibilityKindEnum.VK_PUBLIC, false, false, false, false);
		textArea.append(myPackage.getName()+" created\n");
		
		//////////////////////////////
		// Creation of a new class //
		/////////////////////////////
		textArea.append("Creation of a new class \n");
		UmlClass myClass;
		myClass = umlModel.getModelManagement().getCore().getUmlClass().createUmlClass("toto", VisibilityKindEnum.VK_PUBLIC, false, false, false, false, false);
		textArea.append(myClass.getName()+" created\n");
	}
	
	private void button4Code() {
			textArea.setText("");
			
	}
	private void button5Code() {
		ReDiPulizeAllModelElements();
		
	}

	private void button6Code() {
		
		textArea.append("starting Relayout of diagrams \n");
		
		Iterator it = PoseidonProjectConnector.getDiagrams().iterator();
		while (it.hasNext())
		{
			ReLayout((RefObject)it.next(), "");
		}
		textArea.append("end of relayout of diagrams \n");
		
	}
	private void button7Code() {
		SaveAndReload();
		
	}
	
	private void SaveAndReload()
	{
		String filename;
		if (PoseidonProjectConnector.getFile() != null)
		{
			try
			{
				filename = java.net.URLDecoder.decode(PoseidonProjectConnector.getFile().getFile(),"UTF-8");
				textArea.append("will save to " + filename + "\n");
				PoseidonProjectConnector.saveProject(filename);
				PoseidonProjectConnector.openProject(filename);
			}
			catch (Exception e)
			{
				textArea.append(e.getMessage() + "\n");
			}
		}
		else
		{
			// this is a new project must save in a temp file
			filename = com.gentleware.poseidon.util.PoseidonUtils.getTempDir()+"\\tempSaveAndReloadFile.zuml";
			textArea.append("projet never saved: saved to "+filename+"\n");
			PoseidonProjectConnector.saveProject(filename);
			PoseidonProjectConnector.openProject(filename);
		}

	}
	
	public void ReLayout(RefObject aRefObject, String tab)
	{		
		com.gentleware.jboogie.kernel.ProjectImpl ProjectI;
		DiId aDiId; 
				
		ProjectI = PoseidonProjectConnector.getCurrentProject();


		textArea.append(tab + "Retreiveing DiId on " +aRefObject+ " \n");
		aDiId = ProjectI.getDiId(aRefObject);
			
			if (aDiId == null)
			{
				textArea.append(tab + "Creating DiId: "+aDiId+" for " + aRefObject + "\n");
				aDiId = ProjectI.getNextDiId();
				aDiId.attach(aRefObject);
			}
			else
			{
				textArea.append(tab + "Retreived DiId: "+aDiId+" for " + aRefObject + "\n");
			}
			com.gentleware.jboogie.openapi.DiCommandFactory DiCF;
			DiCF = PoseidonProjectConnector.getCommandDiFactory();
			// To redraw all representations of the element based on the current state of the UML element. 
			DiCF.makeCommandRelayout(aDiId, new Boolean(true)).execute();
			try
			{
				GraphElement GE = (GraphElement)aRefObject;
				Iterator it = GE.getContained().iterator();
				while (it.hasNext())
				{
					ReLayout((RefObject)it.next(),tab+"   ");
				}
			}
			catch (Exception e)
			{
				// not a GraphElement  
			}
		
		
	}
	
	
	public void ReDiPulizeAllModelElements()
	{
		 org.omg.uml.modelmanagement.Model model = (org.omg.uml.modelmanagement.Model)PoseidonProjectConnector.getModel();
		 ReDiPulizeModelElements(model, "");
	}

	/**
	 * Recursively redraw all representations of the element based on the current state of the UML element.
	 */
	public void ReDiPulizeModelElements(RefObject aRefObject, String tab)
	{
		ReDiPulize(aRefObject, tab);
		
		// recursive call on owned elements
		// try to cast to a namespace
		try
		{
			org.omg.uml.foundation.core.Namespace ns;
			ns = (org.omg.uml.foundation.core.Namespace)aRefObject;
			textArea.append(tab +ns.getOwnedElement().size()+" ownedElements in" + aRefObject + "\n");
			Iterator it = ns.getOwnedElement().iterator();
			while (it.hasNext())
			{
				ReDiPulizeModelElements((RefObject)it.next(), tab + "   ");
			}
		}
		catch (Exception e)
		{
			textArea.append(tab + aRefObject.toString()+" is not a Namespace\n");
		}

		//		 try to cast to a Classifier
		try
		{
			org.omg.uml.foundation.core.Classifier aClassifier;
			aClassifier = (org.omg.uml.foundation.core.Classifier)aRefObject;
			//ReLayout(aRefObject, tab);
			textArea.append(tab +aClassifier.getFeature().size()+" features in" + aRefObject + "\n");
			
			int nbfeat=0;
			Iterator it2 = aClassifier.getFeature().iterator();
			while (it2.hasNext())
			{	
				textArea.append(tab + "feature n°"+nbfeat+"\n");
				nbfeat++;
				ReDiPulizeModelElements((RefObject)it2.next(), tab + "   ");
			}
		}
		catch (Exception e)
		{
			textArea.append(tab + aRefObject.toString()+" is not a Classifier\n");
		}
		
		
//		 try to cast to a Operation
		try
		{
			org.omg.uml.foundation.core.Operation anOperation;
			anOperation = (org.omg.uml.foundation.core.Operation)aRefObject;
			//ReLayout(aRefObject, tab);
			textArea.append(tab +anOperation.getParameter().size()+" features in" + aRefObject + "\n");
			
			int nbfeat=0;
			Iterator it2 = anOperation.getParameter().iterator();
			while (it2.hasNext())
			{	
				textArea.append(tab + "parameter n°"+nbfeat+"\n");
				nbfeat++;
				ReDiPulizeModelElements((RefObject)it2.next(), tab + "   ");
			}
		}
		catch (Exception e)
		{
			textArea.append(tab + aRefObject.toString()+" is not an Operation\n");
		}
	}
	
	/**
	 * To redraw all representations of the element based on the current state of the UML element.
	 */
	public void ReDiPulize (RefObject aRefObject, String tab)
	{
		com.gentleware.jboogie.kernel.ProjectImpl ProjectI;
		com.gentleware.jboogie.openapi.SmId anSmId; 
		ProjectI = PoseidonProjectConnector.getCurrentProject();

		if (ProjectI.hasSmId(aRefObject))
		{
			anSmId = ProjectI.getSmId(aRefObject);
			textArea.append(tab +"Retreived SmId: "+anSmId+" for " + aRefObject + "\n");
		}
		else
		{
			anSmId = (SmId)ProjectI.getId(aRefObject);
			textArea.append(tab +"creating new SmId: "+anSmId+" for " + aRefObject+"\n");
		}
		com.gentleware.jboogie.openapi.DiCommandFactory DiCF;
		DiCF = PoseidonProjectConnector.getCommandDiFactory();
		// To redraw all representations of the element based on the current state of the UML element.
		try {
			DiCF.makeCommandReDiPulize(anSmId).execute();
		}
		catch (Exception e)
		{
			System.out.println("Problem with ReDiPulize on "+ aRefObject+"\n" + e.getMessage());
		}
	}
	private void initDirectoriesRootpath()
	{
//		 initialize the  Directories.RootPath property so the log4j logger will find its configuration file
		// Looks for the rootpath from BasicMTL.runtime plugin installation
		//URL theURL;
		//try{
			/*theURL = org.eclipse.core.runtime.Platform.asLocalURL(RuntimePlugin.getDefault().getBundle().getEntry("/"));			
			java.io.File aFile = new java.io.File(theURL.getFile()); // convert it into system specific string
			String systemSpecificPath = aFile.getPath();
			System.setProperty("Directories.RootPath",
					systemSpecificPath);*/
			// TODO retreive it from Poseidon API
			System.setProperty("Directories.RootPath",
					"C:\\Program Files\\Poseidon For UML PE 3.0\\lib\\");
		/*}
		catch (java.io.IOException e)
		{
			// not able to find runtime plugin, let the Directories.RootPath empty ...
		}*/
	
	}
	/**
	 * List each extent of the MDR repository (there should be 4 extents)
	 * The extent that is the repository for the uml models is named M1_UmlInstance
	 * @param myRepository - the repository 
	 * @return the extent that contains all UML models (Extent converted into a UmlPackage)
	 */
	private UmlPackage getCurrentModelExtent(MDRepository myRepository) {
		UmlPackage umlModel = null;
		
		// list all extent in the repository
		String[] listExtent = myRepository.getExtentNames();
		// list all extents in the MDRepository
		textArea.append("List of Extents in the repository : \n");
		for(int i=0; i < listExtent.length;i++) {
			textArea.append(" - "+listExtent[i]+"\n");
		}
		// get the extent named "M1_UmlInstance"
		// getCurrentModelName
		//textArea.append("Current  Model name "+PoseidonProjectConnector.getModel().name);
		umlModel = (UmlPackage)myRepository.getExtent("M1_UmlInstance");
		if(umlModel == null) {
			textArea.append("umlModel not found in extent \"M1_UmlInstance\"!\n");
		}
		umlModel = (UmlPackage)myRepository.getExtent("M1_Uml1UserModelExtent");
		if(umlModel == null) {
			textArea.append("umlModel not found in extent \"M1_Uml1UserModelExtent\"!\n");
		}
		return umlModel;
	}
	
	/**
	 * Uses a Poseidon method. should not be used if directly connected to the Poseidon instance of MDR
	 * @return the first model of the list of models in poseidon
	 */
	private Model getCurrentModel(){
		Model currentModel = null;
		currentModel = (Model)getProject().getFirstModel();
		return currentModel;	
	}
	
	/**
	 * List all models in the extent M1_UmlInstance
	 * @param modelExtent - the M1_UmlInstance or another extent
	 * @return the names of the models in the extent, under the form of a pretty-printed string.
	 */
	private String listModelsinExtent(UmlPackage modelExtent) {
		String retString="";
		Object[] allModels = null;
		allModels = modelExtent.getModelManagement().getModel().refAllOfClass().toArray();
		for(int i = 0 ; i < allModels.length ; i++) {
			retString=retString+"Model : "+allModels[i].toString()+"\n";
		}
		return retString;	
	}
	
	/**
	 * This methods checks if a class has a stereotype.
	 * It lists each stereotype of the class and compare the name to the string given as a parameter
	 * @param myClass class to be checked
	 * @param strName name of the stereotype 
	 * @return boolean true if the class is stereotyped
	 */
	public boolean isStereotyped(UmlClass myClass, String strName){
		boolean retBool=false;
		Iterator itS;
		Stereotype st;
		itS = myClass.getStereotype().iterator();
		// textArea.append("class : "+myClass.getName());
		while (itS.hasNext()) {	
			st = (Stereotype)itS.next();
			// textArea.append("  stereotype : "+st.getName());
			if((st.getName()).compareTo(strName)==0){
				retBool=true;
			}
		}
		return retBool;
	}
	
	/**
	 * This method returns the set of class in the model that are stereotyped
	 * The string parameter is compared to the stereotype names of the class 
	 * @param stereotypeName name of the stereotype
	 * @return Collection collection of classes that are stereotyped
	 */
	public Collection selectStereotypedClass(String stereotypeName) {
		Vector v = new Vector();
		Object o=null;
		Collection classes = umlModel.getModelManagement().getCore().getUmlClass().refAllOfClass();
		Iterator it = classes.iterator();
		while(it.hasNext()) {
			o=it.next();
			if ((o instanceof UmlClass) && isStereotyped((UmlClass)o, "RealTimeObject")) {
				// output a message to tell user a stereotyped class has been found
				textArea.append("StereotypedClass detected : "+((UmlClass)o).getName()+"\n");
				// adds the class to the vector
				v.addElement(o);	
			}
		}
		textArea.append("Number of class : "+v.size()+"\n");
		return v;
	}
	
	
	/**
	 * This method adds the attribute "currentState" to the class given as a parameter
	 * It sets some of the properties of the element, to give an exemple 
	 * @param umlClass class to be modified
	 */
	public void addAttribute(UmlClass umlClass) {
		Attribute attribute;
		attribute = umlModel.getModelManagement().getCore().getAttribute().createAttribute();
		attribute.setName("currentState");
		// set the properties of the attribute
		attribute.setChangeability(ChangeableKindEnum.CK_CHANGEABLE);
		attribute.setOwner(umlClass);
		attribute.setVisibility(VisibilityKindEnum.VK_PRIVATE);
		textArea.append("Attribtue added to the class : "+umlClass.getName()+"\n");
	}
	
	
	/**
     * used by the Menu-Action
     */
    public void appendText(final String s) {
        textArea.append(s);
    }

}

