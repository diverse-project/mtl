/*
 * Created on 13 oct. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.inria.mtl.commands.markers;

import org.inria.mtl.commands.MTLCommand;

/**
 * @author edrezen
 *
 */
public class MarkersCommandFactory 
{
	////////////////////////////////////////////////////////////////////////////////
	// MARKERS COMMANDS
	////////////////////////////////////////////////////////////////////////////////
	public MTLCommand createCreateMarkersCommand (java.util.Vector vector)
	{
		return new CreateMarkersCommand (vector);
	}
}
