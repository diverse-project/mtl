package org.irisa.triskell.MT.utils.Java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**@author F. Fondement for ObjeXion
 * @version 1.0
 * Utility class manipulating fields
 */

public class AWK
{
	/**find the fieldth field in a String
	 */
	public static String fieldOf (String s, int field, String separator){
		String ret = s;
		int fieldFound = 1;
		int i;
		while (fieldFound < field) {
			i = ret.indexOf(separator);
			if (i != -1){
				i += separator.length();
				if (i < ret.length())
					ret = ret.substring(i, ret.length());
				else
					return "";
				fieldFound++;
			}
			else
				return "";
		}
		i = ret.indexOf(separator);
		if (i != -1)
			ret = ret.substring(0, i);
		return ret;
	}

	/**the number of fields
	 */
	public static int numberOfFieldOf(String s, String separator){
		if (s == null || s.length() == 0)
			return 0;
		String str = s;
		int ret = 1;
		int i;
		while (str.length() > 0) {
			i = str.indexOf(separator);
			if (i != -1) {
				ret++;
				i += separator.length();
				if (i < str.length())
					str = str.substring(i, str.length());
				else
					return ret;
			}
			else
				return ret;
		}
		return ret;
	}

	/**s excepted the first field (with its separator)
	 */
	public static String eliminateFirstFieldOf (String s, String separator){
		if (numberOfFieldOf(s, separator) > 1){
			int beginning = s.indexOf(separator) + separator.length();
			return s.substring(beginning, s.length());
		}
		else
			return "";
	}

	/**s excepted the last field (with its separator)
	 */
	public static String eliminateLastFieldOf (String s, String separator) {
		if (numberOfFieldOf(s, separator) > 1){
			int theEnd = s.lastIndexOf(separator);
			return s.substring(0, theEnd);
		}
		else
			return "";
	}

	/**The first field of s
	 */
	public static String firstFieldOf (String s, String separator){
		return fieldOf(s, 1, separator);
	}

	/**the last field of s
	 */
	public static String lastFieldOf (String s, String separator){
		String ret;
		int i = s.lastIndexOf(separator);
		if (i == -1)
			ret = s;
		else {
			i += + separator.length();
			if (i < s.length())
				ret = s.substring(i, s.length());
			else
				ret = "";
		}
		return ret;
	}

	/**replace a sequence by another in s; bug: toReplace cannot be ""
	 */
	public static String replace (String s, String toFind, String toReplace) {
//Trop lent ...
/*		String ret = s;
		if (ret.indexOf(toFind) != -1) {
			String head = firstFieldOf(ret, toFind);
			String tail = eliminateFirstFieldOf(ret, toFind);
			ret = head + toReplace + replace(tail, toFind, toReplace);
		}
		return ret;*/
		int length = s != null ? s.length() : 0;
		if (length == 0 || toFind.length() == 0 )
			return s;
		StringBuffer ret = new StringBuffer(length * 2);
		int i = 0, j = 0, diff = toFind.length();
		do {
			j = s.indexOf(toFind, i);
			if (j == -1) {
				j = length;
				ret.append(s.substring(i, j));
			} else {
				ret.append(s.substring(i, j));
				ret.append(toReplace);
			}
			j += diff;
			i = j;
		} while (i < length);
		return ret.toString();
	}
	
	/**
	 * Returns an array of string according to the separator.
	 */
	public static String [] split (String s, String separator) {
		final int separatorLength = separator.length();
		int i=0, j = s.indexOf(separator);
		if (j == -1)
			return new String [] {s};
			
		ArrayList ret = new ArrayList((int) (s.length() / separatorLength) + 1);
		while (j != -1) {
			ret.add(s.substring(i, j));
			
			 i = j + separatorLength;
			 j = s.indexOf(separator);
		}
		ret.add(s.substring(i));
		return (String [])ret.toArray(new String [ret.size()]);
	}
	
	/**
	 * Returns a single string based on a array, separating it with separator.
	 * WARNING: there is no control of the array does not conatin the separator
	 */
	public static String merge (String [] array, String separator) {
		if (array == null)
			return null;
		int arrayLength = array.length;
		if (arrayLength == 0)
			return "";
			
		int separatorLength = separator.length();
		int length = separatorLength * (arrayLength - 1);
		int i;
		for (i = 0; i < arrayLength; ++i) 
			length += array[i].length();
		StringBuffer ret = new StringBuffer(length);
		
		for (i = 0; i < arrayLength; ++i) {
			if (i != 0)
				ret.append(separator);
			ret.append(array[i]);
		}
		return ret.toString();
	}
	
	/**
	 * Returns a single string based on a array, separating it with separator.
	 * WARNING: there is no control of the array does not conatin the separator
	 */
	public static String mergeCollection (Collection collection, String separator) {
		if (collection == null)
			return null;
		int collectionLength = collection.size();
		if (collectionLength == 0)
			return "";
			
		int separatorLength = separator.length();
		int length = separatorLength * (collectionLength - 1);
		Iterator it = collection.iterator();
		while (it.hasNext())
			length += ((String)it.next()).length();
		StringBuffer ret = new StringBuffer(length);
		
		it = collection.iterator();
		boolean firstIter = true;
		while (it.hasNext()) {
			if (firstIter)  {
				firstIter = false;
				ret.append(separator);
			}
			ret.append((String)it.next());
		}
		return ret.toString();
	}
}

