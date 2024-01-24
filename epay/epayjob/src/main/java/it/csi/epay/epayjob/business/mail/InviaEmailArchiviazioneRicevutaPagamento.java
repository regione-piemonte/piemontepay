/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.business.mail;

import it.csi.epay.epayjob.utilities.LogUtil;
import it.csi.epay.epayservices.interfaces.ejb.MailFacade;
import it.csi.epay.epayservices.model.Mail;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;


public class InviaEmailArchiviazioneRicevutaPagamento {

	private static final LogUtil log = new LogUtil ( InviaEmailArchiviazioneRicevutaPagamento.class );

	private static final String CONFIG_PROPERTIES = "config.properties";

	private final MailFacade mailFacade;

	private final String emailOggetto;

	private final String emailTesto;

	private final String emailTo;

	public InviaEmailArchiviazioneRicevutaPagamento ( MailFacade mailFacade, String emailOggetto, String emailTesto, String emailTo ) {
		this.mailFacade = mailFacade;
		this.emailOggetto = emailOggetto;
		this.emailTesto = emailTesto;
		this.emailTo = emailTo;
	}

	public void inviaMailResponceError ( Long idQuietanza ) {
		try {
			StringBuilder sb = new StringBuilder ();
			try {
				sb.append ( getTxt ( emailTesto, String.valueOf ( idQuietanza ) ) );
			} catch ( Exception e ) {
				sb.append ( "[ERROR: " ).append ( e.getMessage () ).append ( "]" );
			}

			try {
				Mail mail = new Mail ();
				mail.setTo ( getTxt ( emailTo ) );
				mail.setSubject ( getTxt ( emailOggetto ) );
				mail.setText ( sb.toString () );
				mailFacade.inviaMail ( mail );
			} catch ( Exception e ) {
				System.out.println ( "Errore nell'invio della mail: " + e.getMessage () );
				e.printStackTrace ();
				throw e;
			}
		} catch ( MessagingException | UnsupportedEncodingException e ) {
			e.printStackTrace ();
		}
	}

	private String getTxt ( String propValue, String... parametri ) {
		String riga = propValue.trim ();
		try {
			int i = 0;
			for ( String parametro : parametri ) {
				System.out.println ( "riga:" + riga );
				riga = riga.replace ( "$" + i++, parametro );
			}
			return riga + "\n";
		} catch ( Exception e ) {
			throw new RuntimeException ( "Errore preparazione riga della mail (" + propValue + ")", e.getCause () );
		}
	}
}
