/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.business.manager.impl;

import it.csi.epay.epaypaweb.business.manager.SincronizzaPagamentiManager;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.facade.sincronizzapagamentipec.dto.ResponseType;
import it.csi.epay.epaypaweb.facade.sincronizzapagamentipec.dto.ResultType;
import it.csi.epay.epaypaweb.facade.sincronizzapagamentipec.dto.SincronizzaPagamentiRequest;
import it.csi.epay.epaypaweb.facade.sincronizzapagamentipec.dto.SincronizzaPagamento;
import it.csi.epay.epaypaweb.persistence.dao.impl.EpaypaTPagamentiBaseDaoImpl;
import it.csi.epay.epaypaweb.persistence.dao.impl.cooppec.EpaypaDErroreDaoImpl;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPagamentiBase;
import it.csi.epay.epaypaweb.persistence.entity.cooppec.EpaypaDErrore;
import it.csi.epay.epaypaweb.util.CostantiErrori;
import it.csi.epay.epaypaweb.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;


@Stateless
@TransactionManagement ( TransactionManagementType.BEAN )
public class SincronizzaPagamentiManagerImpl implements SincronizzaPagamentiManager {

    private final Logger log = LogManager.getLogger ( this.getClass () );

    private final static String CLASS_NAME = "SincronizzaPagamentiManagerImpl";

    @Inject
    private EpaypaDErroreDaoImpl erroreRepository;

    @Inject
    private EpaypaTPagamentiBaseDaoImpl pagamentiDao;

    @Resource
    private UserTransaction transaction;

    @Override
    public ResponseType sincronizzaPagamenti ( SincronizzaPagamentiRequest parameters ) {

        String methodName = "sincronizzaPagamenti";
        
        

        log.info ( CLASS_NAME + " " + methodName + " - START" );

        try {
            transaction.begin ();

            for ( SincronizzaPagamento p: parameters.getPagamenti () ) {
                processPagamento ( p );
            }

            transaction.commit ();

            return returnStandardSuccessfulOutput ();
        } catch ( Throwable t ) {
            try {
                transaction.rollback ();
            } catch ( IllegalStateException | SecurityException | SystemException e ) {
                log.error ( "Errore in fase di rollback: ", e );
            }
            log.error ( "Errore in fase di elaborazione", t );

            return returnErrorOutput ( CostantiErrori.WS_ESITO_KO_DEFAULT );

        } finally {
            log.info ( CLASS_NAME + " " + methodName + " - STOP" );
        }
    }

    private void processPagamento ( SincronizzaPagamento pagamento ) throws Throwable {

        String methodName = "sincronizzaPagamenti";
        
        
        log.info ( CLASS_NAME + " " + methodName + " - START" );

        try {
//            lw.debug ( "elaboro pagamento in input: " + pagamento.getIdPagamento () + " - " + pagamento.getIuvNumeroAvviso () );
//            lw.debug ( "processPagamento getCodVersamento - getCodFiscaleEnte: " + pagamento.getCodVersamento () + " - " + pagamento.getCodFiscaleEnte () );

            EpaypaTPagamentiBase esistente = new EpaypaTPagamentiBase (pagamento.getIuvNumeroAvviso ());
            esistente.setDataPagamento ( Util.xmlGregorianCalendarToTimestamp ( pagamento.getDataPagamento () ) );
            esistente.setDescStato ( pagamento.getDescStato () );
            esistente.setIdStato ( pagamento.getIdStato () );
            esistente.setImportoPagato ( pagamento.getImportoPagato () );
            esistente.setPagamentoSpontaneo ( pagamento.getPagamentoSpontaneo () );
            esistente.setCodVersamento ( pagamento.getCodVersamento () );
            esistente.setDescrCodVersamento ( pagamento.getDescrCodVersamento () );
            esistente.setCodFiscaleEnte ( pagamento.getCodFiscaleEnte () );
            esistente.setNote ( pagamento.getNotePagamento () );
            esistente.setNome ( pagamento.getNome () );
            esistente.setCognome_ragione_sociale ( pagamento.getRagioneSociale () != null ? pagamento.getRagioneSociale () : pagamento.getCognome () );
            esistente.setIdUnicoFiscale ( pagamento.getCodiceFiscalePagatore () );
            esistente.setFlagPersonaFisica ( pagamento.getFlagPersonaFisica () );
            esistente.setDataScadenza ( Util.xmlGregorianCalendarToDate ( pagamento.getDataScadenza () ) );
            //RDI-23
            esistente.setCausale ( pagamento.getCausale () );
            esistente.setRequiresCostUpdate(pagamento.getRequiresCostUpdate());
            log.debug ( "aggiorno entity con merge per IUV " + esistente.getIuv () );
            pagamentiDao.merge ( esistente );

        } catch ( Throwable t ) {
            log.error ( "Errore in fase di elaborazione del singolo pagamento", t );
            throw t;

        } finally {
            log.info ( CLASS_NAME + " " + methodName + " - STOP" );
        }
    }

    private ResponseType returnErrorOutput ( String codiceErrore ) {
        ResponseType responseType = new ResponseType ();
        ResultType result = new ResultType ();

        EpaypaDErrore errore;
        try {
            errore = erroreRepository.findByCodiceErrore ( codiceErrore );
        } catch ( PersistenceException e ) {
            log.error ( "errore nel reperimento del messaggio di errore", e );
            errore = null;
        }

        if ( null == errore || !StringUtils.isNotBlank ( errore.getDescrizioneErrore () ) ) {
            result.setCodice ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            result.setMessaggio ( "Si e' verificato un errore durante l'elaborazione" );
            log.warn (
                String.format ( "Non e' stato possibile recuperare l'errore associato al codice %s, verra' utilizzato un errore di default.", codiceErrore ) );
        } else {
            result.setCodice ( codiceErrore );
            result.setMessaggio ( errore.getDescrizioneErrore () );
        }

        responseType.setResult ( result );
        return responseType;
    }

    private ResponseType returnStandardSuccessfulOutput () {
        ResponseType responseType = new ResponseType ();
        ResultType result = new ResultType ();
        result.setCodice ( CostantiErrori.WS_ESITO_OK_DEFAULT );
        result.setMessaggio ( "Operazione completata correttamente" );
        responseType.setResult ( result );
        return responseType;
    }
}
