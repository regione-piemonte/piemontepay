/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.ws.business.coopapplicativapec;

import it.csi.epay.epayservices.integration.db.manager.EnteManager;
import it.csi.epay.epayservices.integration.db.manager.EnteTempManager;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.EnteTemp;
import it.csi.epay.epayservices.utilities.LogUtil;
import it.csi.epay.epayservices.ws.business.coopapplicativapec.util.ProgrammedRollbackException;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Singleton ( name = "AggiornaEnteConfermaOperation", mappedName = "AggiornaEnteConfermaOperation" )
public class AggiornaEnteConfermaOperation {

	protected LogUtil log = new LogUtil ( this.getClass () );

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	EnteTempManager enteTempManager;

	@EJB
	EnteManager enteManager;

	@TransactionAttribute ( TransactionAttributeType.REQUIRED )
	public void confermaOperazioneEnte ( EnteTemp enteTemp ) {
		try {
			applicaOperazioneEnte ( enteTemp, true );
		} catch ( ProgrammedRollbackException e ) {
			throw new RuntimeException ( e );
		}
	}

	@TransactionAttribute ( TransactionAttributeType.REQUIRES_NEW )
	public void simulaOperazioneEnte ( EnteTemp enteTemp ) throws ProgrammedRollbackException {
		applicaOperazioneEnte ( enteTemp, false );
	}

	private void applicaOperazioneEnte ( EnteTemp enteTemp, boolean apply ) throws ProgrammedRollbackException {
		Ente ente;

		try {
			ente = enteManager.getByCF ( enteTemp.getCodiceFiscale () );
		} catch ( NoDataException e ) {
			ente = new Ente ();
		}

		if ( ente == null ) {
			ente = new Ente ();
		}

		ente.setCodiceFiscale ( enteTemp.getCodiceFiscale () );
		ente.setCodiceGs1Gln ( enteTemp.getCodiceGs1Gln () );
		ente.setCodiceInterbancario ( enteTemp.getCodiceInterbancario () );
		ente.setFlagInvioPagamenti ( enteTemp.getFlagInvioPagamenti () );
		ente.setLogo ( enteTemp.getLogo () );
		ente.setNome ( enteTemp.getNome () );
		ente.setOrari ( enteTemp.getOrari () );
		ente.setFlagRiconciliazioneVersamenti ( enteTemp.getFlagRiconciliazioneVersamenti () );
		ente.setFlagAdesioneCittaFacile ( enteTemp.getFlagAdesioneCittaFacile () );
		ente.setCodiceIstat ( enteTemp.getCodiceIstat () );
		ente.setTemplateEmailId ( enteTemp.getTemplateEmailId () );
		ente.setUrlDominio ( enteTemp.getUrlDominio () );
        ente.setCodiceIpa  ( enteTemp.getCodiceIpa ());
        
		if ( ente.getIdEnte () != null ) {
			enteManager.aggiorna ( ente );
		} else {
			enteManager.inserisci ( ente );
		}

		entityManager.flush ();

		if ( apply ) {
			enteTempManager.deleteByIdEnteTemp ( enteTemp.getIdEnte () );
		} else {
			throw new ProgrammedRollbackException ();
		}
	}

   

}
