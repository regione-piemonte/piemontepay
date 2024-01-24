/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.dto.mdpmultiiuv;

import it.csi.mdpnew.mdpmultiiuvsrv.util.MdpMultiIuvConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateMDP extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private static final String LOGGER_PREFIX = MdpMultiIuvConstants.MDPIUV_ROOT_LOG_CATEGORY;
	private static final Logger log = Logger.getLogger(LOGGER_PREFIX + ".dto");		

	private final static String DEFAULT_PATTERN = "dd/MM/yyyy";
	private String stringValue;
	private String pattern;
	private Date dateValue;

	private SimpleDateFormat simpleDateFormat;

	public DateMDP() {
		pattern = DEFAULT_PATTERN;
		setSimpleDateFormat(new SimpleDateFormat(pattern));
		dateValue = null;
	}

	public DateMDP(String dateString) {
		pattern = DEFAULT_PATTERN;
		setSimpleDateFormat(new SimpleDateFormat(pattern));
		stringValue = dateString;
		try {
			dateValue = getSimpleDateFormat().parse(stringValue);
		}
		catch (ParseException e) {
			log.error("[DateMDP::DateMDP] Errore ", e);
		}
	}

	public DateMDP(Date date) {
		pattern = DEFAULT_PATTERN;
		setSimpleDateFormat(new SimpleDateFormat(pattern));
		if (date != null) {
			dateValue = new Date(date.getTime());
			stringValue = getSimpleDateFormat().format(date);
		}
	}

	final public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
		try {
			if (stringValue != null) {
				dateValue = getSimpleDateFormat().parse(stringValue);
			}
		}
		catch (ParseException ex) {
			log.error("[DateMDP::setStringValue] Errore ", ex);
		}
	}

	public void setDateValue(Date date) {
		dateValue = (Date) date.clone();
		if (date != null) {
			this.stringValue = getSimpleDateFormat().format(date);
		}
	}

	public String getPattern() {
		return pattern;
	}

	public final void setPattern(String pattern) {
		this.pattern = pattern;
		setSimpleDateFormat(new SimpleDateFormat(pattern));
	}

	public final Date getDateValue() {
		return (Date) dateValue.clone();
	}

	public SimpleDateFormat getSimpleDateFormat() {
		return simpleDateFormat;
	}

	public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
		this.simpleDateFormat = simpleDateFormat;
	}
}
