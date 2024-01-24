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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.collections.CollectionUtils;

import it.csi.epay.epayservices.integration.db.entities.EpayRTipoPagamentoCollegato;
import it.csi.epay.epayservices.integration.db.manager.TipoPagamentoManager;
import it.csi.epay.epayservices.integration.db.manager.TipoPagamentoTempManager;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TipoPagamentoTemp;
import it.csi.epay.epayservices.utilities.LogUtil;
import it.csi.epay.epayservices.ws.business.coopapplicativapec.util.ProgrammedRollbackException;
import it.csi.epay.epayservices.ws.coopapplicativapec.ProtocolloAggiornamentoAzioneType;


@Singleton ( name = "AggiornaCodiceVersamentoConfermaOperation", mappedName = "AggiornaCodiceVersamentoConfermaOperation" )
public class AggiornaCodiceVersamentoConfermaOperation {

    protected LogUtil log = new LogUtil ( this.getClass () );

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    TipoPagamentoTempManager tipoPagamentoTempManager;

    @EJB
    TipoPagamentoManager tipoPagamentoManager;

    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    public void confermaOperazioneCodiceVersamento ( TipoPagamentoTemp codiceVersamentoTemp ) {
        try {
            applicaOperazioneCodiceVersamento ( codiceVersamentoTemp, true );
        } catch ( ProgrammedRollbackException e ) {
            throw new RuntimeException ( e );
        }
    }

    @TransactionAttribute ( TransactionAttributeType.REQUIRES_NEW )
    public void simulaOperazioneCodiceVersamento ( TipoPagamentoTemp codiceVersamentoTemp ) throws ProgrammedRollbackException {
        applicaOperazioneCodiceVersamento ( codiceVersamentoTemp, false );
    }

    private void applicaOperazioneCodiceVersamento ( TipoPagamentoTemp codiceVersamentoTemp, boolean apply ) throws ProgrammedRollbackException {

        if ( apply ) {
            tipoPagamentoTempManager.deleteByIdTipoPagamentoTemp ( codiceVersamentoTemp.getIdTipoPagamentoTemp () );
        }

        switch ( ProtocolloAggiornamentoAzioneType.valueOf ( codiceVersamentoTemp.getTipoOperazione () ) ) {
        case INSERIMENTO :
            applicaOperazioneCodiceVersamentoInserimento ( codiceVersamentoTemp );
            break;
        case MODIFICA :
            applicaOperazioneCodiceVersamentoModifica ( codiceVersamentoTemp );
            break;
        case CANCELLAZIONE :
            applicaOperazioneCodiceVersamentoEliminazione ( codiceVersamentoTemp );
            break;
        default :
            throw new RuntimeException ( "Azione non attesa" );
        }

        entityManager.flush ();

        if ( !apply ) {
            throw new ProgrammedRollbackException ();
        }
    }

    private void applicaOperazioneCodiceVersamentoInserimento ( TipoPagamentoTemp codiceVersamentoTemp ) {
        TipoPagamento tipoPagamento = new TipoPagamento ();

        tipoPagamento.setIdTipoPagamento ( null );
        tipoPagamento.setEpayTEnti ( codiceVersamentoTemp.getEnte () );
        tipoPagamento.setCodiceVersamento ( codiceVersamentoTemp.getCodiceVersamento () );
        tipoPagamento.setCompilazioneNote ( codiceVersamentoTemp.getCompilazioneNote () );
        tipoPagamento.setDatiSpecificiRiscossione ( codiceVersamentoTemp.getDatiSpecificiRiscossione () );
        tipoPagamento.setDescrizionePortale ( codiceVersamentoTemp.getDescrizionePortale () );
        tipoPagamento.setFineValidita ( codiceVersamentoTemp.getFineValidita () );
        tipoPagamento.setFlagInvioPagamenti ( codiceVersamentoTemp.getFlagInvioPagamenti () );
        tipoPagamento.setIdApplicazione ( codiceVersamentoTemp.getIdApplicazione () );
        tipoPagamento.setInizioValidita ( codiceVersamentoTemp.getInizioValidita () );
        tipoPagamento.setPagamentoSpontaneo ( codiceVersamentoTemp.getPagamentoSpontaneo () );
        tipoPagamento.setContatoreCompilazioni ( 0L );
        tipoPagamento.setContatorePagamenti ( 0L );
        tipoPagamento.setContatoreSelezioni ( 0L );
        tipoPagamento.setTipologiaPagamento ( codiceVersamentoTemp.getTipologiaPagamento () );
        tipoPagamento.setFlagPresenzaBollettinoPostale ( codiceVersamentoTemp.getFlagPresenzaBollettinoPostale () );
        tipoPagamento.setFlagMultibeneficiario ( codiceVersamentoTemp.getMultibeneficiario () ); 
        
        // CSI_PAG-467 (04-2020)
        // Per gestire l'invio della RT a wso2, si e' aggiunto un parametro (flag_invio_rt) nella tabella epay_t_tipo_pagamento 
        // che di default deve essere false e si deve poi cambiare manualmente, per poter consentire l'invio della RT
        // Il parametro non viene propagato, per questo motivo viene automaticamente impostato a false 
        tipoPagamento.setFlagInvioRT ( codiceVersamentoTemp.getFlagInvioRT () != null ? codiceVersamentoTemp.getFlagInvioRT () : false );
        
        tipoPagamento.setFlagInvioNotificatore ( codiceVersamentoTemp.getFlagInvioNotificatore () );
        tipoPagamento.setFlagPersonalizzazioneCov ( codiceVersamentoTemp.getFlagPersonalizzazioneCov () );
        tipoPagamento.setDescrizioneTextCov ( codiceVersamentoTemp.getDescrizioneTextCov () );
        tipoPagamento.setPassphrase ( codiceVersamentoTemp.getPassphrase () );
        
        tipoPagamento.setFlagVisualizzaDaSportello ( codiceVersamentoTemp.getFlagVisualizzaDaSportello () );
        
        tipoPagamento.setCredenzialiPnd ( codiceVersamentoTemp.getCredenzialiPnd () );
        tipoPagamento.setUrlAttualizzazionePnd ( codiceVersamentoTemp.getUrlAttualizzazionePnd () );
        
        tipoPagamentoManager.inserisci ( tipoPagamento );
        if (null!= codiceVersamentoTemp.getIdTipoPagamentoSecondario ())
        {
            List<TipoPagamento> nuoviTipiPagamento= tipoPagamentoManager.getByEnteECodiceVersamento ( tipoPagamento.getEpayTEnti (), tipoPagamento.getCodiceVersamento () );
            tipoPagamentoManager.inserisciAssociazioneTipoPagamentoSecondario ( nuoviTipiPagamento.get ( 0 ).getIdTipoPagamento (), codiceVersamentoTemp.getIdTipoPagamentoSecondario () );
        }
    }

    private void applicaOperazioneCodiceVersamentoModifica ( TipoPagamentoTemp codiceVersamentoTemp ) {
        TipoPagamento tipoPagamento = codiceVersamentoTemp.getTipoPagamento ();

        tipoPagamento.setCompilazioneNote ( codiceVersamentoTemp.getCompilazioneNote () );
        tipoPagamento.setDescrizionePortale ( codiceVersamentoTemp.getDescrizionePortale () );
        tipoPagamento.setFlagInvioPagamenti ( codiceVersamentoTemp.getFlagInvioPagamenti () );
        tipoPagamento.setIdApplicazione ( codiceVersamentoTemp.getIdApplicazione () );
        tipoPagamento.setPagamentoSpontaneo ( codiceVersamentoTemp.getPagamentoSpontaneo () );
        tipoPagamento.setTipologiaPagamento ( codiceVersamentoTemp.getTipologiaPagamento () );
        tipoPagamento.setFlagPresenzaBollettinoPostale ( codiceVersamentoTemp.getFlagPresenzaBollettinoPostale () );

        // CSI_PAG-467 (04-2020)
        // Per gestire l'invio della RT a wso2, si e' aggiunto un parametro (flag_invio_rt) nella tabella epay_t_tipo_pagamento 
        // che di default deve essere false e si deve poi cambiare manualmente, per poter consentire l'invio della RT
        // Il parametro non viene propagato, per questo motivo viene automaticamente impostato a false 
        tipoPagamento.setFlagInvioRT ( codiceVersamentoTemp.getFlagInvioRT () != null ? codiceVersamentoTemp.getFlagInvioRT () : false );
        
        tipoPagamento.setFlagMultibeneficiario (  codiceVersamentoTemp.getMultibeneficiario () );
        List<EpayRTipoPagamentoCollegato> epayRTipoPagamentoCollegatos=   tipoPagamentoManager.getPagamentoSecondarioByidPagamentoPrincipale(tipoPagamento);
        if (!CollectionUtils.isEmpty ( epayRTipoPagamentoCollegatos ))
        {
            if (epayRTipoPagamentoCollegatos.size ()>1)
            {
                throw new RuntimeException ( "Ci sono piu' pagamenti secondari collegati al primario" );
            }
            if (!epayRTipoPagamentoCollegatos.get ( 0 ).getIdTipoPagamentoSecondario ().equals ( codiceVersamentoTemp.getIdTipoPagamentoSecondario () ))
            {
                tipoPagamentoManager.deleteAssociazioneTipoPagamentoSecondario ( tipoPagamento.getIdTipoPagamento (), epayRTipoPagamentoCollegatos.get ( 0 ).getIdTipoPagamentoSecondario () );

                if (null!= codiceVersamentoTemp.getIdTipoPagamentoSecondario ())
                {
                    tipoPagamentoManager.inserisciAssociazioneTipoPagamentoSecondario ( tipoPagamento.getIdTipoPagamento (), codiceVersamentoTemp.getIdTipoPagamentoSecondario () );
                }
            }
        }
        else
        {
            if (null!= codiceVersamentoTemp.getIdTipoPagamentoSecondario ())
            {
                tipoPagamentoManager.inserisciAssociazioneTipoPagamentoSecondario ( tipoPagamento.getIdTipoPagamento (), codiceVersamentoTemp.getIdTipoPagamentoSecondario () );
            } 
        }
        
        if (Boolean.FALSE.equals ( codiceVersamentoTemp.getMultibeneficiarioSecondario () ))
        {
            List<EpayRTipoPagamentoCollegato> pagamentiCollegati= tipoPagamentoManager.getAssociazionePagamentoCollegatoByIdPagamentoSecondario ( tipoPagamento.getIdTipoPagamento () );
            if (!CollectionUtils.isEmpty ( pagamentiCollegati ))
            {
                for (EpayRTipoPagamentoCollegato pagamentoCollegato : pagamentiCollegati)
                {
                    tipoPagamentoManager.deleteAssociazioneTipoPagamentoSecondario ( pagamentoCollegato );
                }
            }
        }
        
        tipoPagamento.setFlagInvioNotificatore ( codiceVersamentoTemp.getFlagInvioNotificatore () );
        tipoPagamento.setFlagPersonalizzazioneCov ( codiceVersamentoTemp.getFlagPersonalizzazioneCov () );
        tipoPagamento.setDescrizioneTextCov ( codiceVersamentoTemp.getDescrizioneTextCov () );
        tipoPagamento.setPassphrase ( codiceVersamentoTemp.getPassphrase () );
        tipoPagamento.setFlagVisualizzaDaSportello ( codiceVersamentoTemp.getFlagVisualizzaDaSportello () );
        tipoPagamento.setInizioValidita ( codiceVersamentoTemp.getInizioValidita ());
        tipoPagamento.setFineValidita ( codiceVersamentoTemp.getFineValidita ());
        tipoPagamento.setCredenzialiPnd ( codiceVersamentoTemp.getCredenzialiPnd () );
        tipoPagamento.setUrlAttualizzazionePnd ( codiceVersamentoTemp.getUrlAttualizzazionePnd () );
        
        tipoPagamentoManager.aggiorna ( tipoPagamento );
        
       
    }

    private void applicaOperazioneCodiceVersamentoEliminazione ( TipoPagamentoTemp codiceVersamentoTemp ) {

        
        
        TipoPagamento pagamentoOld= tipoPagamentoManager.getById ( codiceVersamentoTemp.getTipoPagamento ().getIdTipoPagamento ()  );
        
        List<EpayRTipoPagamentoCollegato> epayRTipoPagamentoCollegatos=   tipoPagamentoManager.getPagamentoSecondarioByidPagamentoPrincipale(pagamentoOld);
        if (!CollectionUtils.isEmpty ( epayRTipoPagamentoCollegatos ))
        {
            tipoPagamentoManager.deleteAssociazioneTipoPagamentoSecondario ( codiceVersamentoTemp.getIdTipoPagamentoTemp () , epayRTipoPagamentoCollegatos.get ( 0 ).getIdTipoPagamentoSecondario () );

            
        }
        List<EpayRTipoPagamentoCollegato> pagamentiCollegati= tipoPagamentoManager.getAssociazionePagamentoCollegatoByIdPagamentoSecondario ( codiceVersamentoTemp.getIdTipoPagamentoTemp () );
        if (!CollectionUtils.isEmpty ( epayRTipoPagamentoCollegatos ))
        {
            for (EpayRTipoPagamentoCollegato pagamentoCollegato : pagamentiCollegati)
            {
                tipoPagamentoManager.deleteAssociazioneTipoPagamentoSecondario ( pagamentoCollegato );
            }
        }
        if ( Boolean.TRUE.equals ( tipoPagamentoManager.existsPagamentiByTipoPagamento ( pagamentoOld.getIdTipoPagamento () ) ) ) {
            final Calendar cal = Calendar.getInstance ();
            cal.add ( Calendar.DATE, -1 );
            pagamentoOld.setFineValidita ( cal.getTime () );
            tipoPagamentoManager.aggiorna ( pagamentoOld );
        } else {
            tipoPagamentoManager.deleteById ( codiceVersamentoTemp.getTipoPagamento ().getIdTipoPagamento () );
        }
        
    }

}
