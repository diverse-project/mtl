/* $Id: PoseidonDriverTestInstall.java,v 1.1 2005-01-18 09:36:08 dvojtise Exp $
 * Created on 19 Octobre 2004
 *
 * Copyright 2004 - INRIA - LGPL license 
 */
package org.irisa.triskell.PoseidonDriverTest.modules;

import org.irisa.triskell.PoseidonDriverTest.poseidonLibs.PoseidonDriverTestPane;
import com.gentleware.poseidon.openapi.PoseidonUIConnector;
import com.gentleware.poseidon.uml.UmlProject;

/**
 * @author Andy SAPORITO
 *
 * These two methods are called when a the plugin is loaded or uninstalled
 */
public class PoseidonDriverTestInstall {

	static PoseidonDriverTestPane _poseidonDriverTestPane;
	
	
	/**
	 * This method is called when the plugin is loaded
	 */
	static void install() {
		_poseidonDriverTestPane= new PoseidonDriverTestPane();
		//PoseidonUIConnector.addDetailsTab(UmlProject.class, _poseidonDriverTestPane);
	}
	
	/**
	 * This method is called when the plugin is unloaded
	 */
	static void uninstall() {
	//	PoseidonUIConnector.removeDetailsTab(_poseidonDriverTestPane);
	}
}
