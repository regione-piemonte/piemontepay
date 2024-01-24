/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.application;

import it.csi.mdp.mdpetl.dto.FlussoSingoloPagamento;
import it.csi.mdp.mdpetl.dto.LoggingRT;
import it.csi.mdp.mdpetl.integration.util.dao.InserisciLoggingRTDAO;
import it.csi.mdp.mdpetl.util.LogUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.util.Date;


public class InvioEsitoPag {

	private static final LogUtil log = new LogUtil ( InvioEsitoPag.class );

	void loggaInvioEsito ( FlussoSingoloPagamento flusso, String esito, String messErrore ) {
		LoggingRT loggingRT = new LoggingRT ();
		loggingRT.setDataOraInvio ( new Timestamp ( ( new Date () ).getTime () ) );
		loggingRT.setErrori ( StringUtils.substring ( messErrore, 0, 255 ) );
		loggingRT.setEsito ( esito );
		loggingRT.setIstitutoMittente ( flusso.getDenominazioneMittente () );
		loggingRT.setIuv ( flusso.getIuv () );
		try {
			new InserisciLoggingRTDAO ( loggingRT ).executeUpdate ();
		} catch ( Exception e ) {
			log.error ( "loggaInvioEsito", "Errore in fase di registrazione dell'esito sulla tabella logging_rt! ", e );
			e.printStackTrace ();
		}
	}
}
