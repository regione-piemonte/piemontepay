/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.ws.business.coopapplicativapec;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import it.csi.epay.epayservices.integration.db.manager.EnteManager;
import it.csi.epay.epayservices.integration.db.manager.EnteTempManager;
import it.csi.epay.epayservices.integration.db.manager.TipoPagamentoTempManager;
import it.csi.epay.epayservices.interfaces.rs.CodiciEsito;
import it.csi.epay.epayservices.model.EnteTemp;
import it.csi.epay.epayservices.model.TipoPagamentoTemp;
import it.csi.epay.epayservices.utilities.LogUtil;
import it.csi.epay.epayservices.ws.coopapplicativapec.ConfermaOperazioneRequest;


@Singleton ( name = "ConfermaOperazioneOperation", mappedName = "ConfermaOperazioneOperation" )
public class ConfermaOperazioneOperation {

    protected LogUtil log = new LogUtil ( this.getClass () );

    @EJB
    EnteTempManager enteTempManager;

    @EJB
    TipoPagamentoTempManager tipoPagamentoTempManager;

    @EJB
    EnteManager enteManager;

    @EJB
    AggiornaEnteConfermaOperation aggiornaEnteConfermaOperation;

    @EJB
    AggiornaCodiceVersamentoConfermaOperation aggiornaCodiceVersamentoConfermaOperation;

    @EJB
    AggiornaRiferimentoContabileConfermaOperation aggiornaRiferimentoContabileConfermaOperation;

    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    public CodiciEsito execute ( ConfermaOperazioneRequest parameters ) {

        int total = 0;

        List<EnteTemp> entiDaConfermare = enteTempManager.getByIdOperazione ( parameters.getIdOperazione () );
        List<TipoPagamentoTemp> tipiDaConfermare = tipoPagamentoTempManager.getByIdOperazione ( parameters.getIdOperazione () );

        total += entiDaConfermare.size () + tipiDaConfermare.size ();

        for ( EnteTemp temp: entiDaConfermare ) {
            aggiornaEnteConfermaOperation.confermaOperazioneEnte ( temp );
        }

        for ( TipoPagamentoTemp temp: tipiDaConfermare ) {
            if ( temp.getChiaveIntersistema () != null && !temp.getChiaveIntersistema ().isEmpty () ) {
                // riferimento contabile
                aggiornaRiferimentoContabileConfermaOperation.confermaOperazioneRiferimentoContabile ( temp );
            } else {
                // codice versamento
                aggiornaCodiceVersamentoConfermaOperation.confermaOperazioneCodiceVersamento ( temp );
            }
        }

        if ( total > 0 ) {
            return CodiciEsito.ESECUZIONE_OK;
        } else {
            // TODO DEVE ESSERE ERRORE ? MAGARI OPERAZIONI NON CONSIDERATE NON VENGONO SALVATE
            return CodiciEsito.ESECUZIONE_OK;
        }
    }

}
