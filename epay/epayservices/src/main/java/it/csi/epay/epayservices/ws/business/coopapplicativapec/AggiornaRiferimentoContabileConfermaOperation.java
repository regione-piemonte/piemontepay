/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.ws.business.coopapplicativapec;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.csi.epay.epayservices.integration.db.manager.TipoPagamentoManager;
import it.csi.epay.epayservices.integration.db.manager.TipoPagamentoTempManager;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TipoPagamentoTemp;
import it.csi.epay.epayservices.utilities.LogUtil;
import it.csi.epay.epayservices.ws.business.coopapplicativapec.util.ProgrammedRollbackException;
import it.csi.epay.epayservices.ws.coopapplicativapec.ProtocolloAggiornamentoAzioneType;


@Singleton ( name = "AggiornaRiferimentoContabileConfermaOperation", mappedName = "AggiornaRiferimentoContabileConfermaOperation" )
public class AggiornaRiferimentoContabileConfermaOperation {

    protected LogUtil log = new LogUtil ( this.getClass () );

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    TipoPagamentoTempManager tipoPagamentoTempManager;

    @EJB
    TipoPagamentoManager tipoPagamentoManager;

    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    public void confermaOperazioneRiferimentoContabile ( TipoPagamentoTemp codiceVersamentoTemp ) {
        try {
            applicaOperazioneRiferimentoContabile ( codiceVersamentoTemp, true );
        } catch ( ProgrammedRollbackException e ) {
            throw new RuntimeException ( e );
        }
    }

    @TransactionAttribute ( TransactionAttributeType.REQUIRES_NEW )
    public void simulaOperazioneRiferimentoContabile ( TipoPagamentoTemp codiceVersamentoTemp ) throws ProgrammedRollbackException {
        applicaOperazioneRiferimentoContabile ( codiceVersamentoTemp, false );
    }

    private void applicaOperazioneRiferimentoContabile ( TipoPagamentoTemp codiceVersamentoTemp, boolean apply ) throws ProgrammedRollbackException {

        switch ( ProtocolloAggiornamentoAzioneType.valueOf ( codiceVersamentoTemp.getTipoOperazione () ) ) {
        case INSERIMENTO :
            applicaOperazioneRiferimentoContabileInserimento ( codiceVersamentoTemp );
            break;
        case MODIFICA :
            applicaOperazioneRiferimentoContabileModifica ( codiceVersamentoTemp );
            break;
        case CANCELLAZIONE :
            applicaOperazioneRiferimentoContabileEliminazione ( codiceVersamentoTemp );
            break;
        default :
            throw new RuntimeException ( "Azione non attesa" );
        }

        entityManager.flush ();

        if ( apply ) {
            tipoPagamentoTempManager.deleteByIdTipoPagamentoTemp ( codiceVersamentoTemp.getIdTipoPagamentoTemp () );
        } else {
            throw new ProgrammedRollbackException ();
        }
    }

    private void applicaOperazioneRiferimentoContabileInserimento ( TipoPagamentoTemp codiceVersamentoTemp ) {

        TipoPagamento tipoPagamento = codiceVersamentoTemp.getTipoPagamento ();

        tipoPagamento.setAnnoAccertamento ( codiceVersamentoTemp.getAnnoAccertamento () );
        tipoPagamento.setChiaveIntersistema ( codiceVersamentoTemp.getChiaveIntersistema () );
        tipoPagamento.setDatiSpecificiRiscossione ( codiceVersamentoTemp.getDatiSpecificiRiscossione () );
        tipoPagamento.setFineValidita ( codiceVersamentoTemp.getFineValidita () );
        tipoPagamento.setInizioValidita ( codiceVersamentoTemp.getInizioValidita () );
        tipoPagamento.setNumeroAccertamento ( codiceVersamentoTemp.getNumeroAccertamento () );
        tipoPagamento.setFlagPresenzaBollettinoPostale ( codiceVersamentoTemp.getFlagPresenzaBollettinoPostale () );
        
        tipoPagamentoManager.aggiorna ( tipoPagamento );
    }

    private void applicaOperazioneRiferimentoContabileModifica ( TipoPagamentoTemp codiceVersamentoTemp ) {

        TipoPagamento tipoPagamento = codiceVersamentoTemp.getTipoPagamento ();

        tipoPagamento.setAnnoAccertamento ( codiceVersamentoTemp.getAnnoAccertamento () );
        tipoPagamento.setChiaveIntersistema ( codiceVersamentoTemp.getChiaveIntersistema () );
        tipoPagamento.setDatiSpecificiRiscossione ( codiceVersamentoTemp.getDatiSpecificiRiscossione () );
        tipoPagamento.setFineValidita ( codiceVersamentoTemp.getFineValidita () );
        tipoPagamento.setInizioValidita ( codiceVersamentoTemp.getInizioValidita () );
        tipoPagamento.setNumeroAccertamento ( codiceVersamentoTemp.getNumeroAccertamento () );
        tipoPagamento.setFlagPresenzaBollettinoPostale ( codiceVersamentoTemp.getFlagPresenzaBollettinoPostale () );
        
        tipoPagamentoManager.aggiorna ( tipoPagamento );
    }

    private void applicaOperazioneRiferimentoContabileEliminazione ( TipoPagamentoTemp codiceVersamentoTemp ) {

        TipoPagamento tipoPagamento = codiceVersamentoTemp.getTipoPagamento ();

        tipoPagamento.setAnnoAccertamento ( codiceVersamentoTemp.getAnnoAccertamento () );
        tipoPagamento.setChiaveIntersistema ( null );
        tipoPagamento.setDatiSpecificiRiscossione ( codiceVersamentoTemp.getDatiSpecificiRiscossione () );
        tipoPagamento.setFineValidita ( codiceVersamentoTemp.getFineValidita () );
        tipoPagamento.setInizioValidita ( codiceVersamentoTemp.getInizioValidita () );
        tipoPagamento.setNumeroAccertamento ( codiceVersamentoTemp.getNumeroAccertamento () );
        tipoPagamento.setFlagPresenzaBollettinoPostale ( codiceVersamentoTemp.getFlagPresenzaBollettinoPostale () );
        
        tipoPagamentoManager.aggiorna ( tipoPagamento );
    }

}
