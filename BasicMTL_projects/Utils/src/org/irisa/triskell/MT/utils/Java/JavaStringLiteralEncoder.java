/*
 * Created on 12 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.utils.Java;

/**
 * @author Philippe Studer
 *
 * A utility class to generate java straing constants from java straing values
 */
public class JavaStringLiteralEncoder {

    /**
     * encodes a string ; do not append the "
     * @param s
     * @return
     */
    public static String encodeString (String s) {
        int length = s.length();
        if (length == 0)
            return  s;
        StringBuffer b = new StringBuffer(length + length);
        for (int index = 0; index < length; index++) {
            encodeCharacter(s.charAt(index), b);
        }
        return  b.toString();
    }

    private static void encodeCharacter (char c, StringBuffer buffer) {
        switch (c) {
            case '\b':
                buffer.append("\\b");

                /* backspace BS       */
                break;
            case '\t':
                buffer.append("\\t");

                /* horizontal tab HT       */
                break;
            case '\n':
                buffer.append("\\n");

                /* linefeed LF       */
                break;
            case '\f':
                buffer.append("\\f");

                /* form feed FF       */
                break;
            case '\r':
                buffer.append("\\r");

                /* carriage return CR       */
                break;
            case '\"':
                buffer.append("\\\"");

                /* double quote     "   */
                break;
            case '\'':
                buffer.append("\\'");

                /* single quote '       */
                break;
            case '\\':
                buffer.append("\\\\");
                break;
            default:
                if (c < ' ' || c >= 0x007f)
                    buffer.append("\\u" + Character.forDigit(((int)c >> 12) & 15,
                            16) + Character.forDigit(((int)c >> 8) & 15, 16)
                            + Character.forDigit(((int)c >> 4) & 15, 16) +
                            Character.forDigit(((int)c) & 15, 16));
                else
                    buffer.append(c);
        }
    }
}
