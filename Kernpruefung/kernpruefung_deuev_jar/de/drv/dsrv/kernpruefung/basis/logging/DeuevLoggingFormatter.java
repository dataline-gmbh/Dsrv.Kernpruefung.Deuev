package de.drv.dsrv.kernpruefung.basis.logging;

import java.text.MessageFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Formatiert die Log-Ausgaben.
 */
public class DeuevLoggingFormatter extends Formatter {

	private static final MessageFormat MESSAGE_FORMAT = new MessageFormat(
			"[{3,date,dd.MM.yyyy HH:mm:ss} ThreadID: {2} {0} {5}] {1} {4} \n");
	private static final int AMOUNT_ARGUMENTS = 6;

	/**
	 * Konstruktor.
	 */
	public DeuevLoggingFormatter() {
	}

	@Override
	public String format(LogRecord record) {
		final Object[] arguments = new Object[AMOUNT_ARGUMENTS];
		int i = 0;

		arguments[i++] = record.getLoggerName();
		arguments[i++] = record.getLevel();
		arguments[i++] = Thread.currentThread().getId();
		arguments[i++] = new Date(record.getMillis());
		arguments[i++] = record.getMessage();
		arguments[i++] = record.getSourceMethodName();

		return MESSAGE_FORMAT.format(arguments);
	}

}
