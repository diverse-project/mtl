/*******************************************************************************
 * Copyright (c) 2000, 2002 International Business Machines Corp. and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v0.5 
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v05.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 ******************************************************************************/

package org.inria.mtl.plugin.editors;


import org.eclipse.swt.widgets.Composite;

import org.eclipse.jface.text.information.IInformationPresenter;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

//import org.eclipse.jdt.ui.text.JavaSourceViewerConfiguration;



public class MTLSourceViewer extends SourceViewer {

	/**
	 * Text operation code for requesting the outline for the current input.
	 */
	public static final int SHOW_OUTLINE= 51;

	/**
	 * Text operation code for requesting the outline for the element at the current position.
	 */
	public static final int OPEN_STRUCTURE= 52;


	private IInformationPresenter fOutlinePresenter;
	private IInformationPresenter fStructurePresenter;

	public MTLSourceViewer(Composite parent, IVerticalRuler verticalRuler, IOverviewRuler overviewRuler, boolean showAnnotationsOverview, int styles) {
		super(parent, verticalRuler, overviewRuler, showAnnotationsOverview, styles);
	}

	/*
	 * @see ITextOperationTarget#doOperation(int)
	 */
	public void doOperation(int operation) {
		if (getTextWidget() == null)
			return;

		switch (operation) {
			case SHOW_OUTLINE:
				fOutlinePresenter.showInformation();
				return;
			case OPEN_STRUCTURE:
				fStructurePresenter.showInformation();
				return;
		}
		
		super.doOperation(operation);
	}

	/*
	 * @see ITextOperationTarget#canDoOperation(int)
	 */
	public boolean canDoOperation(int operation) {
		if (operation == SHOW_OUTLINE)
			return fOutlinePresenter != null;
		if (operation == OPEN_STRUCTURE)
			return fStructurePresenter != null;
		return super.canDoOperation(operation);
	}

	/*
	 * @see ISourceViewer#configure(SourceViewerConfiguration)
	 */
	public void configure(SourceViewerConfiguration configuration) {
		super.configure(configuration);
		if (configuration instanceof MTLSourceViewerConfiguration) {
//			fOutlinePresenter= ((MTLSourceViewerConfiguration)configuration).getOutlinePresenter(this, false);
//			fOutlinePresenter.install(this);
		}
		if (configuration instanceof MTLSourceViewerConfiguration) {
//			fStructurePresenter= ((MTLSourceViewerConfiguration)configuration).getOutlinePresenter(this, true);
//			fStructurePresenter.install(this);
		}
	}

	/*
	 * @see TextViewer#handleDispose()
	 */
	protected void handleDispose() {
		if (fOutlinePresenter != null) {
			fOutlinePresenter.uninstall();	
			fOutlinePresenter= null;
		}
		if (fStructurePresenter != null) {
			fStructurePresenter.uninstall();
			fStructurePresenter= null;
		}
		super.handleDispose();
	}
}
