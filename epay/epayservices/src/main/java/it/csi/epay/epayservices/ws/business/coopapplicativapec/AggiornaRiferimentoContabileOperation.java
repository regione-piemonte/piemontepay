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
import it.csi.epay.epayservices.integration.db.manager.TipoPagamentoManager;
import it.csi.epay.epayservices.integration.db.manager.TipoPagamentoTempManager;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.interfaces.rs.CodiciEsito;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TipoPagamentoTemp;
import it.csi.epay.epayservices.utilities.LogUtil;
import it.csi.epay.epayservices.ws.business.coopapplicativapec.util.ProgrammedRollbackException;
import it.csi.epay.epayservices.ws.coopapplicativapec.AggiornaRiferimentoContabileRequest;
import it.csi.epay.epayservices.ws.coopapplicativapec.ProtocolloAggiornamentoAzioneType;
import it.csi.epay.epayservices.ws.coopapplicativapec.RiferimentoContabileType;


@Singleton ( name = "AggiornaRiferimentoContabileOperation", mappedName = "AggiornaRiferimentoContabileOperation" )
public class AggiornaRiferimentoContabileOperation {

    protected LogUtil log = new LogUtil ( this.getClass () );

    @EJB
    EnteManager enteManager;

    @EJB
    TipoPagamentoManager tipoPagamentoManager;

    @EJB
    TipoPagamentoTempManager tipoPagamentoTempManager;

    @EJB
    AggiornaRiferimentoContabileConfermaOperation aggiornaRiferimentoContabileConfermaOperation;

    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    public CodiciEsito execute ( AggiornaRiferimentoContabileRequest parameters ) {

        TipoPagamentoTemp result;
        switch ( parameters.getProtocolloAggiornamentoAzione () ) {
        case INSERIMENTO :
            result = executeInserimento ( parameters );
            break;
        case MODIFICA :
            result = executeModifica ( parameters );
            break;
        case CANCELLAZIONE :
            result = executeEliminazione ( parameters );
            break;
        default :
            throw new RuntimeException ( "Azione non attesa" );
        }

        if ( result != null ) {

            try {
                aggiornaRiferimentoContabileConfermaOperation.simulaOperazioneRiferimentoContabile ( result );
                throw new RuntimeException ( "applicazione delle modifiche per simulazione non controllabile" );
            } catch ( ProgrammedRollbackException e ) {
                // tutto OK
                e.getMessage ();
            } catch ( Exception e ) {
                // errore nella simulazione
                throw e;
            }

        }

        return CodiciEsito.ESECUZIONE_OK;
    }

    private TipoPagamentoTemp executeInserimento ( AggiornaRiferimentoContabileRequest parameters ) {

        TipoPagamento attuale = getTipoPagamentoReferenziato ( parameters );

        if ( attuale.getChiaveIntersistema () == null || attuale.getChiaveIntersistema ().isEmpty () ) {
            // salva
            TipoPagamentoTemp temp = new TipoPagamentoTemp ();

            map ( parameters, attuale, temp );

            resetDateValiditaCvSuRiferimentoContabile ( attuale, temp );

            tipoPagamentoTempManager.inserisci ( temp );
            return temp;
        } else {
            // IGNORA
            return null;
        }
    }

    private void resetDateValiditaCvSuRiferimentoContabile ( TipoPagamento attuale, TipoPagamentoTemp temp ) {
        temp.setInizioValidita ( attuale.getInizioValidita () );
        temp.setFineValidita ( attuale.getFineValidita () );
    }

    private TipoPagamentoTemp executeModifica ( AggiornaRiferimentoContabileRequest parameters ) {

        TipoPagamento attuale = getTipoPagamentoReferenziato ( parameters );

        if ( attuale.getChiaveIntersistema () == null || attuale.getChiaveIntersistema ().isEmpty () ) {
            // IGNORA
            return null;
        } else if ( !attuale.getChiaveIntersistema ().equals ( parameters.getRiferimentoContabile ().getChiaveIntersistema () ) ) {
            // IGNORA
            return null;
        } else {
            TipoPagamentoTemp temp = new TipoPagamentoTemp ();

            map ( parameters, attuale, temp );

            resetDateValiditaCvSuRiferimentoContabile ( attuale, temp );

            tipoPagamentoTempManager.inserisci ( temp );
            return temp;
        }
    }

    private TipoPagamentoTemp executeEliminazione ( AggiornaRiferimentoContabileRequest parameters ) {

        TipoPagamento attuale = getTipoPagamentoReferenziato ( parameters );

        if ( attuale.getChiaveIntersistema () == null || attuale.getChiaveIntersistema ().isEmpty () ) {
            // IGNORA
            return null;
        } else if ( !attuale.getChiaveIntersistema ().equals ( parameters.getRiferimentoContabile ().getChiaveIntersistema () ) ) {
            // IGNORA
            return null;
        } else {
            TipoPagamentoTemp temp = new TipoPagamentoTemp ();

            map ( parameters, attuale, temp );

            resetDateValiditaCvSuRiferimentoContabile ( attuale, temp );

            tipoPagamentoTempManager.inserisci ( temp );
            return temp;
        }
    }

    private TipoPagamento getTipoPagamentoReferenziato ( AggiornaRiferimentoContabileRequest parameters ) {

        RiferimentoContabileType input = parameters.getRiferimentoContabile ();
        Ente ente;

        try {
            ente = enteManager.getByCF ( input.getCodiceFiscaleEnte () );
        } catch ( NoDataException e ) {
            throw new RuntimeException ( "Ente non trovato: " + input.getCodiceFiscaleEnte () );
        }

        List<TipoPagamento> attuali = tipoPagamentoManager.getByEnteECodiceVersamento ( ente, input.getCodiceVersamento () );
        if ( attuali != null && attuali.size () < 1 ) {
            throw new RuntimeException ( "Codice versamento non trovato" );
        } else if ( attuali.size () > 1 ) {
            throw new RuntimeException ( "Codice versamento esistente non univoco" );
        }

        TipoPagamento attuale = attuali.get ( 0 );
        return attuale;
    }

    private void map ( AggiornaRiferimentoContabileRequest parameters, TipoPagamento attuale, TipoPagamentoTemp tipoPagamentoTemp ) {
        RiferimentoContabileType input = parameters.getRiferimentoContabile ();

        tipoPagamentoTemp.setTipoOperazione ( parameters.getProtocolloAggiornamentoAzione ().name () );
        tipoPagamentoTemp.setIdOperazione ( parameters.getIdOperazione () );
        tipoPagamentoTemp.setTipoPagamento ( attuale );

        if ( parameters.getProtocolloAggiornamentoAzione () == ProtocolloAggiornamentoAzioneType.INSERIMENTO ||
            parameters.getProtocolloAggiornamentoAzione () == ProtocolloAggiornamentoAzioneType.MODIFICA ) {
            tipoPagamentoTemp.setAnnoAccertamento ( input.getAnnoAccertamento () != null ? input.getAnnoAccertamento ().longValue () : null );
            tipoPagamentoTemp.setChiaveIntersistema ( input.getChiaveIntersistema () );
            tipoPagamentoTemp.setDatiSpecificiRiscossione ( input.getDatoSpecificoRiscossione () );
            tipoPagamentoTemp.setFineValidita ( input.getDataFineValidita () != null ? input.getDataFineValidita ().toGregorianCalendar ().getTime () : null );
            tipoPagamentoTemp
                .setInizioValidita ( input.getDataInizioValidita () != null ? input.getDataInizioValidita ().toGregorianCalendar ().getTime () : null );
            tipoPagamentoTemp.setNumeroAccertamento ( input.getNumeroAccertamento () != null ? input.getNumeroAccertamento ().toString () : null );
        } else {
            tipoPagamentoTemp.setAnnoAccertamento ( null );
            tipoPagamentoTemp.setChiaveIntersistema ( input.getChiaveIntersistema () );
            tipoPagamentoTemp.setDatiSpecificiRiscossione ( null );
            tipoPagamentoTemp.setNumeroAccertamento ( null );
            tipoPagamentoTemp.setFineValidita ( null );
            tipoPagamentoTemp.setInizioValidita ( null );
        }

        tipoPagamentoTemp.setEnte ( attuale.getEpayTEnti () );
        tipoPagamentoTemp.setCodiceVersamento ( attuale.getCodiceVersamento () );
        tipoPagamentoTemp.setCompilazioneNote ( attuale.getCompilazioneNote () );
        tipoPagamentoTemp.setContatoreCompilazioni ( attuale.getContatoreCompilazioni () );
        tipoPagamentoTemp.setContatorePagamenti ( attuale.getContatorePagamenti () );
        tipoPagamentoTemp.setContatoreSelezioni ( attuale.getContatoreSelezioni () );
        tipoPagamentoTemp.setDescrizionePortale ( attuale.getDescrizionePortale () );
        tipoPagamentoTemp.setFlagInvioPagamenti ( attuale.getFlagInvioPagamenti () );
        tipoPagamentoTemp.setIdApplicazione ( attuale.getIdApplicazione () );
        tipoPagamentoTemp.setPagamentoSpontaneo ( attuale.getPagamentoSpontaneo () );
        tipoPagamentoTemp.setTipologiaPagamento ( attuale.getTipologiaPagamento () );
        tipoPagamentoTemp.setFlagPresenzaBollettinoPostale ( attuale.getFlagPresenzaBollettinoPostale () );

        // CSI_PAG-467 (04-2020)
        // Per gestire l'invio della RT a wso2, si e' aggiunto un parametro (flag_invio_rt) nella tabella epay_t_tipo_pagamento 
        // che di default deve essere false e si deve poi cambiare manualmente, per poter consentire l'invio della RT
        // Il parametro non viene propagato, per questo motivo viene automaticamente impostato a false 
        tipoPagamentoTemp.setFlagInvioRT ( attuale.getFlagInvioRT () != null ? attuale.getFlagInvioRT () : false );
    }

}
