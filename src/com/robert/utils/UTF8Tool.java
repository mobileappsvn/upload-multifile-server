package com.robert.utils;


public class UTF8Tool {
	private static final char[] a = { '\u00E1', '\u00E0', '\u00E3', '\u1EA1',
			'\u1EA3', '\u00E2', '\u1EA7', '\u1EAB', '\u1EAD', '\u1EA9',
			'\u0103', '\u1EAF', '\u1EB1', '\u1EB5', '\u1EB7', '\u1EB3',
			'\u1EA5' };
	private static final char[] A = { '\u00C1', '\u00C0', '\u00C3', '\u1EA0',
			'\u1EA2', '\u00C2', '\u1EA6', '\u1EAA', '\u1EAC', '\u1EA8',
			'\u0102', '\u1EB0', '\u1EB4', '\u1EB6', '\u1EB2', '\u1EA4' };
	private static final char[] O = { '\u00D3', '\u00D2', '\u00D5', '\u1ECC',
			'\u1EDE', '\u00D4', '\u1ED0', '\u1ED2', '\u1ED6', '\u1ED8',
			'\u1ED4', '\u01A0', '\u1EDA', '\u1EDC', '\u1EE0', '\u1EE2',
			'\u1EDE' };
	private static final char[] E = { '\u00C9', '\u00C8', '\u1EBC', '\u1EB8',
			'\u1EBA', '\u00CA', '\u1EBE', '\u1EC0', '\u1EC4', '\u1EC6',
			'\u1EC2' };
	private static final char[] I = { '\u00CD', '\u00CC', '\u0128', '\u1ECA',
			'\u1EC8' };
	private static final char[] U = { '\u00DA', '\u00D9', '\u0168', '\u01AF', '\u1EE4',
			'\u1EE6', '\u1EE8', '\u1EEA', '\u1EEC', '\u1EEE', '\u1EF0' };
	private static final char[] Y = { '\u00DD', '\u1EF2', '\u1EF8', '\u1EF4',
			'\u1EF6' };
	private static final char[] D = { '\u0110' }; // DD
	private static final char[] d = { '\u0111' }; // dd
	private static final char[] e = { '\u00E9', '\u00E8', '\u1EBD', '\u1EB9',
			'\u1EBB', '\u00EA', '\u1EBF', '\u1EC1', '\u1EC5', '\u1EC7',
			'\u1EC3' };
	private static final char[] o = { '\u00F3', '\u00F2', '\u00F5', '\u1ECD',
			'\u1ECF', '\u00F4', '\u1ED1', '\u1ED3', '\u1ED7', '\u1ED9',
			'\u1ED5', '\u01A1', '\u1EDB', '\u1EDD', '\u1EE1', '\u1EE3',
			'\u1EDF' };
	private static final char[] i = { '\u00ED', '\u00EC', '\u0129', '\u1ECB',
			'\u1EC9' };
	private static final char[] u = { '\u00FA', '\u00F9', '\u1EE7', '\u1EE5',
			'\u01B0', '\u1EE9', '\u1EEB', '\u1EED', '\u1EF1', '\u0169',
			'\u1EEF' };
	private static final char[] y = { '\u00FD', '\u1EF3', '\u1EF9', '\u1EF5',
			'\u1EF7' };

	public static String coDau2KoDau(String text) {
		text = convert(text, a, 'a');
		text = convert(text, A, 'A');
		text = convert(text, O, 'O');
		text = convert(text, E, 'E');
		text = convert(text, I, 'I');
		text = convert(text, U, 'U');
		text = convert(text, Y, 'Y');
		text = convert(text, D, 'D');
		text = convert(text, d, 'd');
		text = convert(text, e, 'e');
		text = convert(text, o, 'o');
		text = convert(text, i, 'i');
		text = convert(text, u, 'u');
		text = convert(text, y, 'y');
		return text;
	}

	private static String convert(String text, char[] oldChars, char newChar) {
		if (text == null)
			return null;
		for (int i = 0; i < oldChars.length; i++) {
			text = text.replace(oldChars[i], newChar);
		}
		return text;
	}


}
