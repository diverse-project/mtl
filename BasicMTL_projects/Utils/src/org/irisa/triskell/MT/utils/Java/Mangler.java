package org.irisa.triskell.MT.utils.Java;


public class Mangler 
{

    public static String mangle(
        String prefix,
        String name)
    {
		int length = name.length();
		char[] chars = name.toCharArray();
		StringBuffer buffer = new StringBuffer(prefix.length() + length*3);
		buffer.append(prefix);
		for (int index = 0; index < length; index++) {
			char c = chars[index];
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0'
					&& c <= '9'))
				buffer.append(c);
			else {
				buffer.append('_');
				buffer.append(Character.forDigit((c >> 4) & 0x0f, 16));
				buffer.append(Character.forDigit(c & 0x0f, 16));
			}
		}
		return  buffer.toString();
    }
}
