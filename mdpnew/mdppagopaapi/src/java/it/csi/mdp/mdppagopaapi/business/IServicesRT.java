/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.business;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import it.csi.mdp.mdppagopaapi.dto.exception.PaymentException;
import it.csi.mdp.mdppagopaapi.integration.domain.Applicationcustomfield;
import it.csi.mdp.mdppagopaapi.integration.domain.IuvOttici;
import it.csi.mdp.mdppagopaapi.pa.pafornode.CtFaultBean;
import it.csi.mdp.mdppagopaapi.util.ApplicationcustomfieldsEnum;
import it.csi.mdp.mdppagopaapi.util.EncryptionDecryptionUtil;
import it.csi.mdp.mdppagopaapi.util.FaultBeanEnum;


/**
 * Interfaccia con metodi comuni da implementare nei services di business se e solo se necessario.
 */
public interface IServicesRT {


    /**
     * Funzione che definisce i controlli preliminare di business per le primitive.
     *
     * @param paVerifyPaymentNoticeReq
     * @param iuvOttico
     * @throws RTPaymentException exception dopo i controlli da ritornare a pgopa
     */
    default void controlliPreliminariDiBusiness ( IuvOttici iuvOttico, String idPa, String idBrokerPa, String idStation,
        List<Applicationcustomfield> applicationcustomfield, String skeyDb ) throws PaymentException {
        List<Applicationcustomfield> streamACF = applicationcustomfield;

        String cryptedIdPa = EncryptionDecryptionUtil.encrypt ( idPa, skeyDb );
        
        Optional<Applicationcustomfield> trovato = streamACF.stream ().filter ( new Predicate<Applicationcustomfield> () {

            @Override
            public boolean test ( Applicationcustomfield t ) {
                return ApplicationcustomfieldsEnum.IDENTIFICATIVODOMINIO.getCodice ().equals ( t.getFieldname () )
                                && t.getFieldvalue ().equals ( cryptedIdPa );
            }
        } ).findFirst ();
        if ( !trovato.isPresent () ) {
            throw new PaymentException ( CtFaultBean.builder ()
                .withFaultCode ( FaultBeanEnum.PAA_ID_DOMINIO_ERRATO.name () )
                .withDescription ( FaultBeanEnum.PAA_ID_DOMINIO_ERRATO.getDescription () )
                .withId ( idPa )
                .withFaultString ( FaultBeanEnum.PAA_ID_DOMINIO_ERRATO.getDescription () )
                .build () );
        }
        String cryptedIdBrokerPa = EncryptionDecryptionUtil.encrypt ( idBrokerPa, skeyDb );
        Optional<Applicationcustomfield> trovato2 = streamACF.stream ().filter ( new Predicate<Applicationcustomfield> () {

            @Override
            public boolean test ( Applicationcustomfield t ) {
                return ApplicationcustomfieldsEnum.IDENTIFICATIVOINTERMEDIARIOPA.getCodice ().equals ( t.getFieldname () )
                                && t.getFieldvalue ().equals ( cryptedIdBrokerPa );
            }
        } ).findFirst ();
        if ( !trovato2.isPresent () ) {
            throw new PaymentException ( CtFaultBean.builder ()
                .withFaultCode ( FaultBeanEnum.PAA_ID_INTERMEDIARIO_ERRATO.name () )
                .withDescription ( FaultBeanEnum.PAA_ID_INTERMEDIARIO_ERRATO.getDescription () )
                .withId ( idPa )
                .withFaultString ( FaultBeanEnum.PAA_ID_INTERMEDIARIO_ERRATO.getDescription () )
                .build () );
        }
        
//      MDPNEW-001 - eliminato controllo su IDENTIFICATIVOSTAZIONEINTERMEDIARIOPA
//        String cryptedIdStation = EncryptionDecryptionUtil.encrypt ( idStation, skeyDb );
//
//        Optional<Applicationcustomfield> trovato3 = streamACF.stream ().filter ( new Predicate<Applicationcustomfield> () {
//
//            @Override
//            public boolean test ( Applicationcustomfield t ) {
//                return ApplicationcustomfieldsEnum.IDENTIFICATIVOSTAZIONEINTERMEDIARIOPA.getCodice ().equals ( t.getFieldname () )
//                    && t.getFieldvalue ().equals ( cryptedIdStation );
//            }
//        } ).findFirst ();
//        if ( !trovato3.isPresent () ) {
//            throw new PaymentException ( CtFaultBean.builder ()
//                .withFaultCode ( FaultBeanEnum.PAA_STAZIONE_INT_ERRATA.name () )
//                .withDescription ( FaultBeanEnum.PAA_STAZIONE_INT_ERRATA.getDescription () )
//                .withId ( idPa )
//                .withFaultString ( FaultBeanEnum.PAA_STAZIONE_INT_ERRATA.getDescription () )
//                .build () );
//        }


    }
}
