/*
 * Created on 18 oct. 2004
 *
 */
package org.inria.mtl.commands;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.inria.mtl.commands.build.AbstractBuildCommand;

/**
 * @author edrezen
 *
 */
public class MTLCommandWithProgressMonitor extends MTLCommandDelegate implements Observer
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private IProgressMonitor monitor;
	private String name;
	private int totalWork;

	
	////////////////////////////////////////////////////////////////////////////////
	// GETTERS AND SETTERS
	////////////////////////////////////////////////////////////////////////////////
	public IProgressMonitor getMonitor() {
		return monitor;
	}
	public void setMonitor(IProgressMonitor monitor) {
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
	public MTLCommandWithProgressMonitor (
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
	public Object execute() throws Exception 
	{
		Job job = new Job (getName()) {

			protected IStatus run (IProgressMonitor monitor) 
			{
				// we set the monitor
				setMonitor(monitor);
				try {
					monitor.beginTask (MTLCommandWithProgressMonitor.this.getName(), getTotalWork());
					getDelegate().execute();
				} 
				catch (Exception e) {
					e.printStackTrace();
				} 
				finally {
					monitor.done();
				}
				return Status.OK_STATUS;			
			}
			
		};
		job.setUser (true);
		job.schedule();
		
		return null;
	}
	

	/** */
	public void update (Observable o, Object arg) 
	{
		MTLCommand.log().debug ("update performed... observable is '" + o + "' and arg is '" + arg + "'");

		// we should use a Visitor instead of the instanceof ?
		if (o instanceof AbstractBuildCommand)
		{
			if (arg instanceof Object[])
			{
				Object[] args = (Object[])arg;
				if (args[0]=="before")
				{
					IFolder folder = (IFolder) args[1];
					getMonitor().subTask (folder.getName());
				}
				else if (args[0]=="after")
				{
					getMonitor().worked (1);
				} 
			}
		}
	}
	
}

