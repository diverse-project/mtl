package org.inria.mtl.editors.utils;
import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;

import java.util.Calendar;
import java.util.Date;

import java.text.*;
import java.util.*;

public class log {

	//Fichier log destination
	public static String dir = "C:\\logs\\log.txt";
	public static String dire = "C:\\logs\\log_.txt";

	/**Write in a log file.
	 * @param towrite String à écrire dans le fichier log
	 */
	public static void log_this(String towrite) {

		try {
			FileWriter f = new FileWriter(dir, true);
			BufferedWriter bf = new BufferedWriter(f);
			Calendar c = Calendar.getInstance();
			Date maintenant = c.getTime();
			String datelog =
				DateFormat
					.getDateTimeInstance(
						DateFormat.SHORT,
						DateFormat.MEDIUM,
						Locale.FRANCE)
					.format(maintenant);
			bf.write("[" + datelog + "]: " + towrite);
			bf.newLine();
			bf.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
		
		public static void log_(String towrite) {

				try {
					FileWriter f = new FileWriter(dire, true);
					BufferedWriter bf = new BufferedWriter(f);
					Calendar c = Calendar.getInstance();
					Date maintenant = c.getTime();
					String datelog =
						DateFormat
							.getDateTimeInstance(
								DateFormat.SHORT,
								DateFormat.MEDIUM,
								Locale.FRANCE)
							.format(maintenant);
					bf.write("[" + datelog + "]: " + towrite);
					bf.newLine();
					bf.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}

	}
}
