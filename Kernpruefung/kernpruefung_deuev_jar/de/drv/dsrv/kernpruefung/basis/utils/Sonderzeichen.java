package de.drv.dsrv.kernpruefung.basis.utils;

/**
 * Sammlung der Sonderzeichen, die in der Kernpruefung verwendet werden.
 */
public final class Sonderzeichen {

	private Sonderzeichen() {
		// Klasse mit Konstanten fuer die einzelnen Sonderzeichen.
	}

	/** */
	public static final Character SPACE = '\u0020';
	/** */
	public static final Character EXCLAMATION_MARK = '\u0021';
	/** */
	public static final Character QUOTATION_MARK = '\u0022';
	/** */
	public static final Character NUMBER_SIGN = '\u0023';
	/** */
	public static final Character DOLLAR_SIGN = '\u0024';
	/** */
	public static final Character PERCENT_SIGN = '\u0025';
	/** */
	public static final Character AMPERSAND = '\u0026';
	/** */
	public static final Character APOSTROPHE = '\'';
	/** */
	public static final Character LEFT_PARENTHESIS = '\u0028';
	/** */
	public static final Character RIGHT_PARENTHESIS = '\u0029';
	/** */
	public static final Character ASTERISK = '\u002A';
	/** */
	public static final Character PLUS_SIGN = '\u002B';
	/** */
	public static final Character COMMA = '\u002C';
	/** */
	public static final Character HYPHEN_MINUS = '\u002D';
	/** */
	public static final Character FULL_STOP = '\u002E';
	/** */
	public static final Character SOLIDUS = '\u002F';
	/** */
	public static final Character COLON = '\u003A';
	/** */
	public static final Character SEMICOLON = '\u003B';
	/** */
	public static final Character LESS_THAN_SIGN = '\u003C';
	/** */
	public static final Character EQUALS_SIGN = '\u003D';
	/** */
	public static final Character GREATER_THAN_SIGN = '\u003E';
	/** */
	public static final Character QUESTION_MARK = '\u003F';
	/** */
	public static final Character COMMERCIAL_AT = '\u0040';
	/** */
	public static final Character LEFT_SQUARE_BRACKET = '\u005B';
	/** */
	public static final Character REVERSE_SOLIDUS = '\\';
	/** */
	public static final Character RIGHT_SQUARE_BRACKET = '\u005D';
	/** */
	public static final Character CIRCUMFLEX_ACCENT = '\u005E';
	/** */
	public static final Character LOW_LINE = '\u005F';
	/** */
	public static final Character GRAVE_ACCENT = '\u0060';
	/** */
	public static final Character LEFT_CURLY_BRACKET = '\u007B';
	/** */
	public static final Character VERTICAL_LINE = '\u007C';
	/** */
	public static final Character RIGHT_CURLY_BRACKET = '\u007D';
	/** */
	public static final Character TILDE = '\u007E';
	/** */
	public static final Character CONTROL = '\u007F';
	/** */
	public static final Character SECTION_SIGN = '\u00A7';
	/** */
	public static final Character MULTIPLICATION_SIGN = '\u00D7';
	/** */
	public static final Character DIVISION_SIGN = '\u00F7';

	/** */
	public static final String ALL_SPECIAL_CHARACTERS = SPACE.toString() + EXCLAMATION_MARK + QUOTATION_MARK
			+ NUMBER_SIGN + DOLLAR_SIGN + PERCENT_SIGN + AMPERSAND + APOSTROPHE + LEFT_PARENTHESIS + RIGHT_PARENTHESIS
			+ ASTERISK + PLUS_SIGN + COMMA + HYPHEN_MINUS + FULL_STOP + SOLIDUS + COLON + SEMICOLON + LESS_THAN_SIGN
			+ EQUALS_SIGN + GREATER_THAN_SIGN + QUESTION_MARK + COMMERCIAL_AT + LEFT_SQUARE_BRACKET + REVERSE_SOLIDUS
			+ RIGHT_SQUARE_BRACKET + CIRCUMFLEX_ACCENT + LOW_LINE + GRAVE_ACCENT + LEFT_CURLY_BRACKET + VERTICAL_LINE
			+ RIGHT_CURLY_BRACKET + TILDE + CONTROL + SECTION_SIGN + MULTIPLICATION_SIGN + DIVISION_SIGN;
}
