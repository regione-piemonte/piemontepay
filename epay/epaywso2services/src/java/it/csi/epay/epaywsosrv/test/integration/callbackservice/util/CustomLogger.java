/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.test.integration.callbackservice.util;

import it.csi.epay.epaywsosrv.util.LogAndWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import static it.csi.epay.epaywsosrv.test.integration.callbackservice.util.Util.APPLICATION_CODE;

public class CustomLogger {
	static private final Logger log = LogManager.getLogger(APPLICATION_CODE + ".integration");

	static private final String CLASSNAME = CustomLogger.class.getSimpleName();
	private static final String PROPERTY_LOG_FILEPATH = "log.filepath";
	private static final String PROPERTY_LOG_FILEDIR = "log.filedir";
	private static final String PROPERTY_LOG_FILENAME_PREFIX_NAME = "log.filename.prefix";
	private static final String PROPERTY_LOG_FILENAME_EXT = "log.filename.ext";
	private static final String PROPERTY_LOG_ENCODING = "log.encoding";

	private static final String SYSTEM_FILE_SEPATATOR = System.getProperty("file.separator");
	private static final String SYSTEM_LINE_SEPARATOR = System.getProperty("line.separator");

	private Writer logWriter;
	private String logFilepath;
	private String logFiledir;
	private String logFilenamePrefix;
	private String logFilenameExt;
	private String logEncoding;

	private String logFilename;
	private String logFilepPathAndDir;

	public CustomLogger(String configurationFilename) {
		String methodName = "CustomLogger";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

		Properties props = new Properties();
		try {
			props.loadFromXML(this.getClass().getResourceAsStream("/META-INF/" + configurationFilename));

		} catch (InvalidPropertiesFormatException e) {
			lw.error("Error reading " + configurationFilename, e);
		} catch (IOException e) {
			lw.error("Error reading " + configurationFilename, e);
		}

		logFilepath = props.getProperty(PROPERTY_LOG_FILEPATH);
		logFiledir = props.getProperty(PROPERTY_LOG_FILEDIR);
		logFilenamePrefix = props.getProperty(PROPERTY_LOG_FILENAME_PREFIX_NAME);
		logFilenameExt = props.getProperty(PROPERTY_LOG_FILENAME_EXT);
		logEncoding = props.getProperty(PROPERTY_LOG_ENCODING);
		//
		lw.info("property \"" + PROPERTY_LOG_FILEPATH + "\" = \"" + logFilepath + "\"");
		lw.info("property \"" + PROPERTY_LOG_FILEDIR + "\" = \"" + logFiledir + "\"");
		lw.info("property \"" + PROPERTY_LOG_FILENAME_PREFIX_NAME + "\" = \"" + logFilenamePrefix + "\"");
		lw.info("property \"" + PROPERTY_LOG_FILENAME_EXT + "\" = \"" + logFilenameExt + "\"");
		lw.info("property \"" + PROPERTY_LOG_ENCODING + "\" = \"" + logEncoding + "\"");
	}

	public void create(long logFileId) {
		create("" + logFileId);
	}

	public void create(String logFileId) {
		String methodName = "create";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

		String logFilenameSuffix = "_" + (new SimpleDateFormat("yyyyMMdd_HHmmss-SSS")).format(new Date());
		logFilename = logFilenamePrefix + logFileId + logFilenameSuffix + "." + logFilenameExt;
		logFilepPathAndDir = eventuallyAddFileSeparatorToPath(logFilepath) + eventuallyAddFileSeparatorToPath(logFiledir);
		try {
			logWriter = new OutputStreamWriter(new FileOutputStream(logFilepPathAndDir + logFilename), logEncoding);
			lw.info("creato il file di log " + logFilename + " sotto " + logFilepPathAndDir);

		} catch (UnsupportedEncodingException e) {
			lw.warn("encoding " + logEncoding + " non supportato");
		} catch (FileNotFoundException e) {
			lw.warn("impossibile creare/trovare il file di log " + logFilename + " in " + logFilepPathAndDir);
		}
	}

	public void write(String message) {
		String methodName = "write";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

		try {
			logWriter.write(message + SYSTEM_LINE_SEPARATOR);
			lw.info("scrive il messaggio: \"" + message + "\" in " + logFilename + " sotto " + logFilepPathAndDir);

		} catch (IOException e) {
			lw.warn("impossibile scrivere il messaggio: \"" + message + "\" in " + logFilename + " sotto " + logFilepPathAndDir);
		}
	}

	public void write(String fieldName, String value) throws Exception {
		write(fieldName, value, false, null);
	}

	public void write(String fieldName, String value, List<Exception> exceptionAccumulator) throws Exception {
		write(fieldName, value, false, exceptionAccumulator);
	}

	public void write(String fieldName, String value, boolean checkMandatory) throws Exception {
		write(fieldName, value, checkMandatory, null);
	}

	public void write(String fieldName, String value, boolean checkMandatory, List<Exception> exceptionAccumulator) throws Exception {
		String methodName = "write";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

		if (checkMandatory && value == null) {
			Exception exception = new Exception("campo obbligatorio: " + fieldName + " deve essere fornito!");
			if (exceptionAccumulator != null)
				exceptionAccumulator.add(exception);
			else
				throw exception;
		}

		String message = quoteValue(fieldName, value);
		try {
			logWriter.write(message + SYSTEM_LINE_SEPARATOR);
			lw.info("scrive il messaggio: \"" + message + "\" in " + logFilename + " sotto " + logFilepPathAndDir);

		} catch (IOException e) {
			lw.warn("impossibile scrivere il messaggio: \"" + message + "\" in " + logFilename + " sotto " + logFilepPathAndDir);
		}
	}

	public void write(String fieldName, Long value) throws Exception {
		write(fieldName, value, false, null);
	}

	public void write(String fieldName, Long value, List<Exception> exceptionAccumulator) throws Exception {
		write(fieldName, value, false, exceptionAccumulator);
	}

	public void write(String fieldName, Long value, boolean checkMandatory) throws Exception {
		write(fieldName, value, checkMandatory, null);
	}

	public void write(String fieldName, Long value, boolean checkMandatory, List<Exception> exceptionAccumulator) throws Exception {
		if (value != null)
			write(fieldName, Long.toString(value), checkMandatory, exceptionAccumulator);
		else
			write(fieldName, (String) null);
	}

	public void write(String fieldName, Double value) throws Exception {
		write(fieldName, value, false, null);
	}

	public void write(String fieldName, Double value, List<Exception> exceptionAccumulator) throws Exception {
		write(fieldName, value, false, exceptionAccumulator);
	}

	public void write(String fieldName, Double value, boolean checkMandatory) throws Exception {
		write(fieldName, value, checkMandatory, null);
	}

	public void write(String fieldName, Double value, boolean checkMandatory, List<Exception> exceptionAccumulator) throws Exception {
		if (value != null)
			write(fieldName, Double.toString(value), checkMandatory, exceptionAccumulator);
		else
			write(fieldName, (String) null);
	}

	public void write(String fieldName, BigDecimal value) throws Exception {
		write(fieldName, value, false, null);
	}

	public void write(String fieldName, BigDecimal value, List<Exception> exceptionAccumulator) throws Exception {
		write(fieldName, value, false, exceptionAccumulator);
	}

	public void write(String fieldName, BigDecimal value, boolean checkMandatory) throws Exception {
		write(fieldName, value, checkMandatory, null);
	}

	public void write(String fieldName, BigDecimal value, boolean checkMandatory, List<Exception> exceptionAccumulator) throws Exception {
		if (value != null)
			write(fieldName, value.toString(), checkMandatory, exceptionAccumulator);
		else
			write(fieldName, (String) null);
	}

	public void write(String fieldName, XMLGregorianCalendar value) throws Exception {
		write(fieldName, value, false, null);
	}

	public void write(String fieldName, XMLGregorianCalendar value, List<Exception> exceptionAccumulator) throws Exception {
		write(fieldName, value, false, exceptionAccumulator);
	}

	public void write(String fieldName, XMLGregorianCalendar value, boolean checkMandatory) throws Exception {
		write(fieldName, value, checkMandatory, null);
	}

	public void write(String fieldName, XMLGregorianCalendar value, boolean checkMandatory, List<Exception> exceptionAccumulator) throws Exception {
		if (value != null)
			write(fieldName, Util.date2strdatetime(value.toGregorianCalendar().getTime()), checkMandatory, exceptionAccumulator);
		else
			write(fieldName, (String) null);
	}

	public void close() {
		String methodName = "close";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

		try {
			logWriter.close();
			lw.info("chiuso il file di log " + logFilename + " sotto " + logFilepPathAndDir);

		} catch (IOException e) {
			lw.warn("impossibile chiudere il file di log " + logFilename + " sotto " + logFilepPathAndDir);
		}
	}

	private String eventuallyAddFileSeparatorToPath(String path) {
		String resPath;

		if (path != null) {
			resPath = path.endsWith(SYSTEM_FILE_SEPATATOR) ? path : path + SYSTEM_FILE_SEPATATOR;
		} else {
			resPath = SYSTEM_FILE_SEPATATOR;
		}

		return resPath;
	}

	private String quoteValue(String fieldName, String value) {
		if (value != null)
			return fieldName + ": '" + value + "'";
		else
			return fieldName + ": null";
	}
}
