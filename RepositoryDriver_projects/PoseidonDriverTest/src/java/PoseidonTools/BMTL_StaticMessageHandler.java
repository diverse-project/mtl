/* $Id: BMTL_StaticMessageHandler.java,v 1.1 2005-01-18 09:36:10 dvojtise Exp $
 * Created on 6 juil. 2004
 *
 */
package PoseidonTools;

import javax.swing.JTextArea;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface;
import org.irisa.triskell.MT.BasicMTL.java2mtl.DefaultJava2MtlInstanciableType;
import org.irisa.triskell.MT.BasicMTL.java2mtl.DefaultJava2MtlMappingObject;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLString;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface;
/**
 * @author dvojtise
 */
public class BMTL_StaticMessageHandler extends DefaultJava2MtlMappingObject 
{
	//////////////////////////////////////////////////////////////////////////
	// We define the needed information for the java 2 mtl mapping
	//////////////////////////////////////////////////////////////////////////
	static public String   getName ()          { return "StaticMessageHandler"; }
	static public String[] getQualifiedName () { return new String[] {"PoseidonTools", "StaticMessageHandler"}; }	
	static public Class    getTheClass ()      { return BMTL_StaticMessageHandler.class; }

	public static Type TheType =  new DefaultJava2MtlInstanciableType (getName(), getQualifiedName(), getTheClass());
	public Type getType() { return TheType; } 
	
	//////////////////////////////////////////////////////////////////////////
	// We define special features for the java access 
	//////////////////////////////////////////////////////////////////////////
	
	static public String text = "";   // main string
	
	static private JTextArea textArea; // if not null, also maintain this one
	
	
	static public void initTextArea(JTextArea aTextArea)
	{
		textArea = aTextArea;
		// reset the text with the TextArea value
		text = textArea.getText();
	}
	static public void clear ()
	{		
		text = "";
		if (textArea != null)
		{
			textArea.setText("");
		}
	}
	static public String getText()
	{
		return text;
	}
	static public void appendText(String message)
	{
		text.concat(message);
		if (textArea != null)
		{
			textArea.append(message);
		}
	}
	//////////////////////////////////////////////////////////////////////////
	// We define the API we will retrieve in MTL
	//////////////////////////////////////////////////////////////////////////
	 
	public BMTLStringInterface BMTL_getText ()
	{
		return new BMTLString (text);
	}

	public void BMTL_appendText (BMTLStringInterface message)
	{	
		appendText(message.getTheString());
	}
	public void BMTL_clear ()
	{		
		clear();
	}
}
