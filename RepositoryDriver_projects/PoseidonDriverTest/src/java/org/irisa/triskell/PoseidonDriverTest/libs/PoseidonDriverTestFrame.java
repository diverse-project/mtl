/*
 * Created on 11 mai 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.irisa.triskell.PoseidonDriverTest.libs;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
* This class is useful to create a frame to write on for debugging
 * @author accordlocal
 */
public class PoseidonDriverTestFrame {

	private JButton button1;
	
	/**
	 * This is the constructor : it builds a new frame with a textarea to write on
	 */
	public PoseidonDriverTestFrame () {
		int width = 500;
		int height = 500;
		JButton button2;
		JButton button1;
		
		// Create the frame
		String title = "PoseidonDriverTest Panel";
		JFrame frame = new JFrame(title);
		frame.setSize(width, height);
		// Create a component to add to the frame

		button1 = new JButton("Get repository");
		button1.setSize(new Dimension(300, 25));
		button1.setBounds(50, 20, 200, 25);
		frame.getContentPane().add(button1);
		
		button2 = new JButton("Set Repository");
		button2.setSize(new Dimension(300, 25));
		button2.setBounds(50, 70, 200, 25);
		frame.getContentPane().add(button2);
		
		// Show the frame
		frame.setVisible(true);
	}
}
