/*
 * $Id: OpenSomethingAction.java,v 1.1 2005-01-18 09:36:09 dvojtise Exp $
 * Authors : dvojtise
 * 
 * Copyright 2004 - INRIA - LGPL license
 */

package org.irisa.triskell.PoseidonDriverTest.modules;

import org.irisa.triskell.PoseidonDriverTest.poseidonLibs.PoseidonDriverTestPane;
import com.gentleware.jboogie.openapi.Project;
import com.gentleware.jboogie.openapi.ProjectAction;
import com.gentleware.poseidon.openapi.PoseidonCoreConnector;
import com.gentleware.poseidon.openapi.PoseidonProjectConnector;
import com.gentleware.poseidon.openapi.PoseidonUIConnector;
import com.gentleware.services.Services;
import com.gentleware.services.swingx.ResourceId;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * An action that enters text in the demo pane.
 * todo: present the i18n - make a resource bundle and register it, i18n-ize the texts in this action
 */
public class OpenSomethingAction extends ProjectAction {
    public OpenSomethingAction(final Project owner) {
        super(owner, new ResourceId("Unknown_Bundle", "PoseidonDriverTest Entry"));
    }

    public void actionPerformed(final ActionEvent e) {
        // get the tab that is currently used
        // note that the tab is only for the current project;
        // if the project changes, the tab changes
        final Component detailsTab = PoseidonUIConnector.getDetailsTab(PoseidonDriverTestPane.PANEL_TITLE);
        if(detailsTab instanceof PoseidonDriverTestPane) {
            final PoseidonDriverTestPane panel = (PoseidonDriverTestPane)detailsTab;
            panel.appendText("Hello World from the PoseidonDriverTest plugin.\n");
            panel.appendText("This seems to be Poseidon" + PoseidonCoreConnector.getProductLine() + ".\n");
            panel.appendText("The model is >" + PoseidonProjectConnector.getModel() + "<.\n\n");
            panel.appendText("The project contains " + PoseidonProjectConnector.getDiagrams().size() + " diagrams.\n\n");
        }
        else {
            if(detailsTab == null) {
                Services.logError("The tab under the key " + PoseidonDriverTestPane.PANEL_TITLE + " could not be found.");
            }
            else {
                final Class tabClass = detailsTab.getClass();
                Services.logError("The tab under the key " + PoseidonDriverTestPane.PANEL_TITLE + " is a " + tabClass.getName());
            }
        }
    }
}