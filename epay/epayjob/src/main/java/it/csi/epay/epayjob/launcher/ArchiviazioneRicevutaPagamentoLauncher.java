/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.launcher;

import it.csi.epay.epayjob.business.ArchiviazioneRicevutaPagamento;
import it.csi.epay.epayjob.utilities.CommonCliUtil;
import it.csi.epay.epayjob.utilities.EjbUtil;
import it.csi.epay.epayservices.interfaces.ejb.JobFacade;
import it.csi.epay.epayservices.interfaces.ejb.MailFacade;

import javax.naming.NamingException;
import java.io.IOException;


/*
 * batch in utilizzo per Fruitore Esterno Città Facile al fine di consentire l'archiviazione su sistema DOCME della ricevuta di pagamento
 * per i soggetti che hanno espressamente dato preferenza su servizi preposti del fruitore.
 */
public class ArchiviazioneRicevutaPagamentoLauncher {

	public static void main ( String[] args ) throws NamingException, IOException {
		CommonCliUtil commonCliUtil = new CommonCliUtil ( args );
		EjbUtil ejbUtil = new EjbUtil ( commonCliUtil.getServers (), commonCliUtil.getUsername (), commonCliUtil.getPassword () );
		MailFacade mailFacade = ejbUtil.getEjb ( MailFacade.class );
		try {
			JobFacade jobFacade = ejbUtil.getEjb ( JobFacade.class );
			ArchiviazioneRicevutaPagamento archiviazioneRicevutaPagamento = new ArchiviazioneRicevutaPagamento ( jobFacade, mailFacade );
			archiviazioneRicevutaPagamento.execute ();
		} finally {
			ejbUtil.close ();
		}
		System.exit ( 0 );
	}
}
