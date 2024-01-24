/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.launcher;

import it.csi.epay.epayjob.business.TassonomiaAggiornamentoPosizioneDebitoria;
import it.csi.epay.epayjob.model.JobContext;
import it.csi.epay.epayjob.utilities.CommonCliUtil;
import it.csi.epay.epayjob.utilities.EjbUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.naming.NamingException;


/*
 * batch che verra' eseguito dopo TassonomiaLoaderLauncher
 */
public class TassonomiaAggiornamentoPosizioneDebitoriaLauncher {

	public static void main ( String[] args ) throws NamingException {
		CommonCliUtil commonCliUtil = new CommonCliUtil ( args );
		EjbUtil ejbUtil = new EjbUtil ( commonCliUtil.getServers (), commonCliUtil.getUsername (), commonCliUtil.getPassword () );
		JobContext jobContext = new JobContext ( "TassonomiaAggiornamentoPosizioneDebitoria" );
		try {
			jobContext.infoStart ();
			TassonomiaAggiornamentoPosizioneDebitoria tassonomiaAggiornamentoPosizioneDebitoria = new TassonomiaAggiornamentoPosizioneDebitoria ( jobContext );
			tassonomiaAggiornamentoPosizioneDebitoria.execute ();
		} catch ( Throwable t ) {
			jobContext.error ( "Errore nell'esecuzione del job", t );
			jobContext.addProblem ( "Si e' verificato un errore non gestito: " + t.getClass ().getName () + " - " + t.getMessage () + "\r\n" + ExceptionUtils.getStackTrace ( t ) );
		} finally {
			jobContext.infoEnd ();
			ejbUtil.close ();
		}
		System.exit ( 0 );
	}
}
