/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager.utils;

import it.csi.epay.epaymodric.business.epaymodric.bo.JobTypeEnum;
import it.csi.epay.epaymodric.business.epaymodric.bo.ScheduledJob;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTScheduledJob;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class ScheduledJobUtility {

	final static Logger logger = LogManager.getLogger (ScheduledJobUtility.class );
	
	public static ScheduledJob getScheduledJob(CblTScheduledJob cblTScheduledJob) {
		if ( cblTScheduledJob == null ) {
			logger.warn ( "ScheduledJobUtility.getScheduledJob: oggetto null" );
			return null;
		} else {
			return ScheduledJob.builder()
						.withId(cblTScheduledJob.getIdScheduledJob())
						.withActive(cblTScheduledJob.getActive())
						.withJobTypeEmum(JobTypeEnum.valueOf(cblTScheduledJob.getJobType()))
						.withScheduleExpression(cblTScheduledJob.getScheduleExpression())
						.withInizioUltimaEsecuzione(cblTScheduledJob.getInizioUltimaEsecuzione())
						.withFineUltimaEsecuzione(cblTScheduledJob.getInizioUltimaEsecuzione())
						.withParams(getParametersPropertiesFromXmlString(cblTScheduledJob.getParams()))
						.withStatus(getStatusPropertiesFromXmlString(cblTScheduledJob.getStatus()))
						.build();
		}
	}
	
	
	private static Properties getParametersPropertiesFromXmlString(String params) {
		Properties properties = null;
		try {
			properties = xmlStringToProperties(params);
		} catch (Exception e) {
			logger.error("ScheduledJobUtility.getParametersPropertiesFromXmlString: errore durante la conversione da xml a properties dei parametri del job", e);
		}
		return properties;
	}
	
	private static Properties getStatusPropertiesFromXmlString(String params) {
		Properties properties = null;
		try {
			properties = xmlStringToProperties(params);
		} catch (Exception e) {
			logger.error("ScheduledJobUtility.getParametersPropertiesFromXmlString: errore durante la conversione da xml a properties dello stato del job", e);
		}
		return properties;
	}
	
	private static Properties xmlStringToProperties(String params) throws InvalidPropertiesFormatException, IOException {
		Properties properties = null;
		if ( params != null ) {
			properties = new Properties ();
			
			properties.loadFromXML( new ByteArrayInputStream ( params.getBytes () ) );

		}
		return properties;
	}
}
