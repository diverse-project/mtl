/*
 * Created on May 22, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Java.commands;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MultipleCommandException extends Exception {
	private static String createMessage (Command [] possibleCommands) {
		StringBuffer sb = new StringBuffer();
		sb.append("Found multiple possibilities for command ");
		sb.append(possibleCommands[0].getName());
		for (int i = 0; i < possibleCommands.length; ++i) {
			sb.append('\n');
			sb.append('\t');
			sb.append(possibleCommands[i].toString());
		}
		return sb.toString();
	}
	
	protected final Command [] possibleCommands;
	
	public MultipleCommandException(Command [] possibleCommands) {
		super(createMessage(possibleCommands));
		this.possibleCommands = possibleCommands;
	}
	
	public Command [] getPossibleCommands () {
		return this.possibleCommands;
	}
}
