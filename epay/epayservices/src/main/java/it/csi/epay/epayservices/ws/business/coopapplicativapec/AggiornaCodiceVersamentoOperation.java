/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.ws.business.coopapplicativapec;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epayservices.integration.db.manager.EnteManager;
import it.csi.epay.epayservices.integration.db.manager.TipoPagamentoManager;
import it.csi.epay.epayservices.integration.db.manager.TipoPagamentoTempManager;
import it.csi.epay.epayservices.integration.db.manager.TipologiaPagamentoManager;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.interfaces.rs.CodiciEsito;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TipoPagamentoTemp;
import it.csi.epay.epayservices.model.TipologiaPagamento;
import it.csi.epay.epayservices.utilities.LogUtil;
import it.csi.epay.epayservices.ws.business.coopapplicativapec.util.ProgrammedRollbackException;
import it.csi.epay.epayservices.ws.coopapplicativapec.AggiornaCodiceVersamentoRequest;
import it.csi.epay.epayservices.ws.coopapplicativapec.CodiceVersamentoType;
import it.csi.epay.epayservices.ws.coopapplicativapec.TipoMultibeneficiarioType;
import it.csi.epay.epayservices.ws.coopapplicativapec.TipoPagamentoType;


@Singleton ( name = "AggiornaCodiceVersamentoOperation", mappedName = "AggiornaCodiceVersamentoOperation" )
public class AggiornaCodiceVersamentoOperation {

    protected LogUtil log = new LogUtil ( this.getClass () );

    @EJB
    EnteManager enteManager;

    @EJB
    TipoPagamentoManager tipoPagamentoManager;

    @EJB
    TipoPagamentoTempManager tipoPagamentoTempManager;

    @EJB
    TipologiaPagamentoManager tipologiaPagamentoManager;

    @EJB
    AggiornaCodiceVersamentoConfermaOperation aggiornaCodiceVersamentoConfermaOperation;

    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    public CodiciEsito execute ( AggiornaCodiceVersamentoRequest parameters ) {

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

        try {
            aggiornaCodiceVersamentoConfermaOperation.simulaOperazioneCodiceVersamento ( result );
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

    private TipoPagamentoTemp executeInserimento ( AggiornaCodiceVersamentoRequest parameters ) {

        CodiceVersamentoType input = parameters.getCodiceVersamento ();
        Ente ente;
        
        if(StringUtils.isEmpty(parameters.getCodiceVersamento().getApplicationId())) {
        	throw new RuntimeException ( "Codice Applicazione non valorizzato" );
        }

        try {
            ente = enteManager.getByCF ( input.getCFEnte () );
        } catch ( NoDataException e ) {
            throw new RuntimeException ( "Ente non trovato: " + input.getCFEnte () );
        }

        List<TipoPagamento> attuali = tipoPagamentoManager.getByEnteECodiceVersamento ( ente, input.getCodice () );
        if ( attuali != null && attuali.size () > 0 ) {
            throw new RuntimeException ( "Codice versamento gia' esistente" );
        }

        TipoPagamentoTemp tipoPagamentoTemp = new TipoPagamentoTemp ();
        tipoPagamentoTemp.setTipoPagamento ( null );
        tipoPagamentoTemp.setEnte ( ente );
        

        map ( parameters, tipoPagamentoTemp );

        tipoPagamentoTempManager.inserisci ( tipoPagamentoTemp );

        return tipoPagamentoTemp;
    }

    private Long ricavaIdPagamentoSecondario ( CodiceVersamentoType input ) {
        if (!StringUtils.isEmpty ( input.getCfEnteSecondario () ) && !StringUtils.isEmpty ( input.getCodiceVersamentoSecondario () ))
        {
            Ente enteSecondario;
            List<TipoPagamento> tipoPagamentoSecondario;
            try {
                enteSecondario= enteManager.getByCF ( input.getCfEnteSecondario ());
            } catch ( NoDataException e ) {
                throw new RuntimeException ( "Ente secondario non trovato: " + input.getCfEnteSecondario  () );
            }
            
            tipoPagamentoSecondario = tipoPagamentoManager.getByEnteECodiceVersamento ( enteSecondario, input.getCodiceVersamentoSecondario ());
            if (CollectionUtils.isEmpty ( tipoPagamentoSecondario ))
            {
                throw new RuntimeException ( "Codice versamento secondario "+ input.getCodiceVersamentoSecondario () 
                +" per ente "+input.getCfEnteSecondario  ()+" non trovato: "   );
            }
            if ( tipoPagamentoSecondario.size ()>1)
            {
                throw new RuntimeException ( "Ci sono piu' codici versamento secondari per  "+ input.getCodiceVersamentoSecondario () 
                +" per ente "+input.getCfEnteSecondario  ()+" non trovato: "   );
            }
            return tipoPagamentoSecondario.get ( 0 ).getIdTipoPagamento () ;
        }
            return null;
    }

    private TipoPagamentoTemp executeModifica ( AggiornaCodiceVersamentoRequest parameters ) {

        CodiceVersamentoType input = parameters.getCodiceVersamento ();
        Ente ente;

        if(StringUtils.isEmpty(parameters.getCodiceVersamento().getApplicationId())) {
        	throw new RuntimeException ( "Codice Applicazione non valorizzato" );
        }
        
        try {
            ente = enteManager.getByCF ( input.getCFEnte () );
        } catch ( NoDataException e ) {
            throw new RuntimeException ( "Ente non trovato: " + input.getCFEnte () );
        }

        List<TipoPagamento> attuali = tipoPagamentoManager.getByEnteECodiceVersamento ( ente, input.getCodice () );
        if ( attuali == null || attuali.size () < 1 ) {
            throw new RuntimeException ( "Codice versamento non esistente" );
        } else if ( attuali.size () > 1 ) {
            throw new RuntimeException ( "Codice versamento esistente non univoco" );
        }

        TipoPagamento attuale = attuali.get ( 0 );

        TipoPagamentoTemp tipoPagamentoTemp = new TipoPagamentoTemp ();
        tipoPagamentoTemp.setEnte ( ente );
        tipoPagamentoTemp.setTipoPagamento ( attuale );
        
        

        map ( parameters, tipoPagamentoTemp );

        tipoPagamentoTempManager.inserisci ( tipoPagamentoTemp );

        return tipoPagamentoTemp;
    }

    private TipoPagamentoTemp executeEliminazione ( AggiornaCodiceVersamentoRequest parameters ) {
        CodiceVersamentoType input = parameters.getCodiceVersamento ();
        Ente ente;

        try {
            ente = enteManager.getByCF ( input.getCFEnte () );
        } catch ( NoDataException e ) {
            throw new RuntimeException ( "Ente non trovato: " + input.getCFEnte () );
        }

        List<TipoPagamento> attuali = tipoPagamentoManager.getByEnteECodiceVersamento ( ente, input.getCodice () );
        if ( attuali == null || attuali.size () < 1 ) {
            throw new RuntimeException ( "Codice versamento non esistente" );
        } else if ( attuali.size () > 1 ) {
            throw new RuntimeException ( "Codice versamento esistente non univoco" );
        }

        TipoPagamento attuale = attuali.get ( 0 );

        TipoPagamentoTemp tipoPagamentoTemp = new TipoPagamentoTemp ();
        tipoPagamentoTemp.setEnte ( ente );
        tipoPagamentoTemp.setTipoPagamento ( attuale );

        map ( parameters, tipoPagamentoTemp );

        tipoPagamentoTempManager.inserisci ( tipoPagamentoTemp );

        return tipoPagamentoTemp;
    }

    private void map ( AggiornaCodiceVersamentoRequest parameters, TipoPagamentoTemp tipoPagamentoTemp ) {

        CodiceVersamentoType input = parameters.getCodiceVersamento ();

        tipoPagamentoTemp.setTipoOperazione ( parameters.getProtocolloAggiornamentoAzione ().name () );
        tipoPagamentoTemp.setIdOperazione ( parameters.getIdOperazione () );
        tipoPagamentoTemp.setCodiceVersamento ( input.getCodice () );
        tipoPagamentoTemp.setAnnoAccertamento ( null );
        tipoPagamentoTemp.setChiaveIntersistema ( null );
        tipoPagamentoTemp.setCompilazioneNote ( null );
        tipoPagamentoTemp.setContatoreCompilazioni ( null );
        tipoPagamentoTemp.setContatorePagamenti ( null );
        tipoPagamentoTemp.setContatoreSelezioni ( null );
        tipoPagamentoTemp.setDatiSpecificiRiscossione ( null );
        tipoPagamentoTemp.setDescrizionePortale ( input.getDescrizione () );
        tipoPagamentoTemp.setFineValidita ( input.getDataFineValidita ()!= null? 
                        input.getDataFineValidita ().toGregorianCalendar ().getTime () :null  );
        tipoPagamentoTemp.setFlagInvioPagamenti ( true );
        tipoPagamentoTemp.setIdApplicazione ( parameters.getCodiceVersamento ().getApplicationId () );
        tipoPagamentoTemp.setInizioValidita (input.getDataInizioValidita ()!= null? 
                        input.getDataInizioValidita ().toGregorianCalendar ().getTime () : Calendar.getInstance ().getTime () );
        tipoPagamentoTemp.setNumeroAccertamento ( null );
        tipoPagamentoTemp.setPagamentoSpontaneo ( input.getTipoPagamento () != null &&
                        input.getTipoPagamento () == TipoPagamentoType.PS ? true : false );

        TipologiaPagamento tipologia = parameters.getCodiceVersamento ().getTipoPagamento () != null
                        ? tipologiaPagamentoManager.getTipologiaPagamentoByCodice ( parameters.getCodiceVersamento ().getTipoPagamento ().name () ) : null;

        tipoPagamentoTemp.setTipologiaPagamento ( tipologia );
        tipoPagamentoTemp.setFlagPresenzaBollettinoPostale ( input.isFlagPresenzaBollettinoPostale () );

        // CSI_PAG-467 (04-2020)
        // Per gestire l'invio della RT a wso2, si e' aggiunto un parametro (flag_invio_rt) nella tabella epay_t_tipo_pagamento 
        // che di default deve essere false e si deve poi cambiare manualmente, per poter consentire l'invio della RT
        // Il parametro non viene propagato, per questo motivo viene automaticamente impostato a false
        tipoPagamentoTemp.setFlagInvioRT ( false );
        tipoPagamentoTemp.setMultibeneficiario ( TipoMultibeneficiarioType.PRIMARIO.equals ( parameters.getCodiceVersamento ().getTipoMultibeneficiario () ) );
        tipoPagamentoTemp.setMultibeneficiarioSecondario (TipoMultibeneficiarioType.SECONDARIO.equals ( parameters.getCodiceVersamento ().getTipoMultibeneficiario () ) );
        tipoPagamentoTemp.setIdTipoPagamentoSecondario ( ricavaIdPagamentoSecondario ( input ) );
        
        tipoPagamentoTemp.setFlagInvioNotificatore ( input.getFlagInvioNotificatore () );
        tipoPagamentoTemp.setFlagPersonalizzazioneCov ( input.getFlagPersonalizzazioneCov () );
        tipoPagamentoTemp.setDescrizioneTextCov ( input.getDescrizioneTextCov () );
        tipoPagamentoTemp.setPassphrase ( input.getPassphrase () );
        
        tipoPagamentoTemp.setFlagVisualizzaDaSportello ( input.isFlagVisualizzaDaSportello () );
        
        tipoPagamentoTemp.setCredenzialiPnd ( input.getCredenzialiPnd () );
        tipoPagamentoTemp.setUrlAttualizzazionePnd ( input.getUrlAttualizzazionePnd () );


    }
}
