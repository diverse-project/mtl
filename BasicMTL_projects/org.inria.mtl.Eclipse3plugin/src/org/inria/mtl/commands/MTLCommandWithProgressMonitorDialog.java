/*
 * Created on 18 oct. 2004
 *
 */
package org.inria.mtl.commands;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.inria.mtl.MTLPlugin;

/**
 * @author edrezen
 *
 */
public class MTLCommandWithProgressMonitorDialog extends MTLCommandDelegate implements Observer, IWorkspaceRunnable
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private ProgressMonitorDialog monitor = new ProgressMonitorDialog (MTLPlugin.getActiveWorkbenchShell());
	private String name;
	private int totalWork;

	
	////////////////////////////////////////////////////////////////////////////////
	// GETTERS AND SETTERS
	////////////////////////////////////////////////////////////////////////////////
	public ProgressMonitorDialog getMonitor() {
		return monitor;
	}
	public void setMonitor(ProgressMonitorDialog monitor) {
		this.monitor = monitor;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getTotalWork() {
		return totalWork;
	}
	public void setTotalWork(int totalWork) {
		this.totalWork = totalWork;
	}

	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public MTLCommandWithProgressMonitorDialog (
		MTLCommand delegate,
		String name,
		int totalWork
	) 
	{
		super (delegate);
		setName (name);
		setTotalWork (totalWork);
		
		// we want to observ the delegate
		getDelegate().addObserver (this);
	}

	
	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public void run (IProgressMonitor monitor) throws CoreException 
	{
		try {
			getDelegate().mainExecute();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/** */
	public Object mainExecute() throws Exception 
	{
		IRunnableWithProgress op = new IRunnableWithProgress() {
         
			public void run (IProgressMonitor monitor)
			{
				try {
					getDelegate().mainExecute();
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
        getMonitor().run(false, true, op);
		return null;
	}

	/** */
	public Object preExecute() throws Exception 
	{
		// we return the delegate result
		return getDelegate().preExecute();
	}

	/** */
	public Object postExecute() throws Exception 
	{
		// we return the delegate result
		return getDelegate().postExecute();
	}

	/** */
	public void update (Observable o, Object arg) 
	{
		System.out.println ("KOOOOLLL KOOOOLLL KOOOOLLL KOOOOLLL KOOOOLLL KOOOOLLL");
	}
}
