/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.ws.business.coopapplicativapec;

import it.csi.epay.epayservices.integration.db.manager.EnteManager;
import it.csi.epay.epayservices.integration.db.manager.EnteTempManager;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.interfaces.rs.CodiciEsito;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.EnteTemp;
import it.csi.epay.epayservices.utilities.LogUtil;
import it.csi.epay.epayservices.ws.business.coopapplicativapec.util.ProgrammedRollbackException;
import it.csi.epay.epayservices.ws.coopapplicativapec.AggiornaEnteRequest;
import it.csi.epay.epayservices.ws.coopapplicativapec.EnteType;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.math.BigDecimal;


@Singleton ( name = "AggiornaEnteOperation", mappedName = "AggiornaEnteOperation" )
public class AggiornaEnteOperation {

	protected LogUtil log = new LogUtil(this.getClass());

    @EJB
    EnteTempManager enteTempManager;

    @EJB
    EnteManager enteManager;

    @EJB
    AggiornaEnteConfermaOperation aggiornaEnteConfermaOperation;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public CodiciEsito execute ( AggiornaEnteRequest parameters ) {

        EnteType enteInput = parameters.getEnte ();

        Ente enteAttualeNelDb;

        try {
            enteAttualeNelDb = enteManager.getByCF ( enteInput.getCodiceFiscale () );
        } catch ( NoDataException e ) {
            // l'ente non esiste
            enteAttualeNelDb = null;
        }

        EnteTemp temp = new EnteTemp ();


        if(enteAttualeNelDb  != null) {
            temp.setIdEnte ( enteAttualeNelDb.getIdEnte () );
        }

        temp.setCodiceFiscale ( enteInput.getCodiceFiscale () );
        temp.setCodiceGs1Gln ( enteInput.getGs1Gln () == null || enteInput.getGs1Gln ().isEmpty () ? null : new BigDecimal( enteInput.getGs1Gln () ));
        temp.setCodiceInterbancario ( enteInput.getCbill () );
        temp.setFlagInvioPagamenti ( true );
        temp.setIdOperazione ( parameters.getIdOperazione () );
        temp.setLogo ( enteInput.getLogo () );
        temp.setNome ( enteInput.getDenominazione () );
        temp.setOrari ( null );
        temp.setFlagRiconciliazioneVersamenti ( enteInput.isRiconciliazioneVersamenti () );
		temp.setFlagAdesioneCittaFacile ( enteInput.isFlagAdesioneCittaFacile () );
		temp.setCodiceIstat ( enteInput.getCodiceIstat () );
		temp.setTemplateEmailId ( enteInput.getTemplateEmailId () );
		temp.setUrlDominio ( enteInput.getUrlDominio () );
		temp.setCodiceIpa  ( enteInput.getCodiceIpa ());

        if(enteAttualeNelDb == null) {
            enteTempManager.inserisci ( temp );
        } else {
            enteTempManager.modifica( temp );
        }

        try {
            aggiornaEnteConfermaOperation.simulaOperazioneEnte ( temp );
            throw new RuntimeException ( "applicazione delle modifiche per simulazione non controllabile" );
        } catch ( ProgrammedRollbackException e ) {
            // tutto OK
            e.getMessage ();
        } catch ( Exception e ) {
            // errore nella simulazione
            throw e;
        }

		return CodiciEsito.ESECUZIONE_OK;
	}

}
