/*
 * Created on 26 oct. 2004
 *
 */
package org.inria.mtl.views.projectexplorer.model;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.BasicMtlLibrary;

/**
 * @author edrezen
 *
 */
public class ProjectExplorerModel 
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	
	private RuntimeTLLContainer runtimeTLLContainer = new RuntimeTLLContainer ();
	public RuntimeTLLContainer getRuntimeTLLContainer() { return runtimeTLLContainer; }
	public void setRuntimeTLLContainer(RuntimeTLLContainer runtimeTLLContainer) { this.runtimeTLLContainer = runtimeTLLContainer; }

	private UserTLLContainer    userTLLContainer    = new UserTLLContainer ();
	public UserTLLContainer getUserTLLContainer() {	return userTLLContainer; }
	public void setUserTLLContainer(UserTLLContainer userTLLContainer) { this.userTLLContainer = userTLLContainer; }

	private ExternTLLContainer  externTLLContainer  = new ExternTLLContainer ();
	public ExternTLLContainer getExternTLLContainer() {	return externTLLContainer; }
	public void setExternTLLContainer(ExternTLLContainer externTLLContainer) { this.externTLLContainer = externTLLContainer; }
	
	
	/** */
	private boolean containsLibrary (BasicMtlLibrary lib)
	{
//		for (int i=0; i<this.size(); i++)
//		{
//			BasicMtlLibrary currentLib = (BasicMtlLibrary) this.get(i);
//			if (lib.getName().equals(currentLib.getName()))
//			{
//				return true;
//			}
//		}
		return false;
	}
}
