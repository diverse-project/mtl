/* $Id: PoseidonDriverTestPlugin.java,v 1.1 2005-01-18 09:36:09 dvojtise Exp $
 * Created on 19 Octobre 2004
 *
 * Copyright 2004 - INRIA - LGPL license 
 */
package org.irisa.triskell.PoseidonDriverTest.modules;

import com.gentleware.poseidon.jboogie.PoseidonApplicationState;
import com.gentleware.poseidon.kernel.PoseidonProject;
import com.gentleware.poseidon.openapi.PoseidonProjectConnector;
import com.gentleware.poseidon.openapi.PoseidonUIConnector;
import com.gentleware.poseidon.uml.UmlProject;
import com.gentleware.poseidon.uml.Uml1ProjectType;
import com.gentleware.poseidon.util.plugin.PoseidonModuleInstall;
import java.util.Vector;

import org.irisa.triskell.PoseidonDriverTest.poseidonLibs.PoseidonDriverTestPane;

/**
 * @author Didier Vojtisek
 *
 */
public class PoseidonDriverTestPlugin extends PoseidonModuleInstall {


    private final static String PLUGIN_NAME = "PoseidonDriverTest";
    protected final static String HELP_MENU_NAME = "Help";
    protected final static String DEMO_MENU_NAME = "PoseidonDriverTest Entry ...";
	public PoseidonDriverTestPane _newTab;
	
	public PoseidonDriverTestPlugin() {
		super("@fullname@", null, "@version@");
	}
	
	/** Module installed for the first time. */
	public void installed() {
		//PoseidonApplicationState.getInstance().getClassLoaders().add(getClass().getClassLoader());
		//PoseidonDriverTestInstall.install();
		 // don't have to test the license here, Poseidon does it for us
        // create a panel, and add it to the details panel
        _newTab = new PoseidonDriverTestPane();
        PoseidonUIConnector.addDetailsTab(Uml1ProjectType.getInstance(), _newTab);
        final PoseidonProject currentProject = PoseidonProjectConnector.getCurrentProject();
        if(currentProject instanceof UmlProject) {
            // insert a menu item in the help menu just after the entry "Open FAQs"
            PoseidonUIConnector.insertMenuItem(OpenSomethingAction.class, HELP_MENU_NAME, "Open FAQs");
        }
	}

	/**
	 * Called when an already-installed module is restored (at IDE startup time).
	 * Should perform whatever initializations are required.
	 */
	public void restored() {
		installed();
	}

	/**
	* Called when the module is loaded and the version is higher than
	* by the previous load
	* The default implementation calls restored().
	* @param release The major release number of the <B>old</B> module code name or -1 if not specified.
	* @param specVersion The specification version of the this <B>old</B> module.
	*/
	public void updated(final int release, final String specVersion) {
		installed();
	}

	/** Module was uninstalled. */
	public void uninstalled() {
//		PoseidonDriverTestInstall.uninstall();
//		final Vector loaders = PoseidonApplicationState.getInstance().getClassLoaders();
//		if(loaders.contains(getClass().getClassLoader())) {
//			loaders.remove(getClass().getClassLoader());
//			PoseidonApplicationState.getInstance().setLoaders(loaders);
//		}

        // remove the menu entry
        PoseidonUIConnector.removeDetailsTab(_newTab);
        PoseidonUIConnector.removeMenuItem(DEMO_MENU_NAME, HELP_MENU_NAME);
	}
}
