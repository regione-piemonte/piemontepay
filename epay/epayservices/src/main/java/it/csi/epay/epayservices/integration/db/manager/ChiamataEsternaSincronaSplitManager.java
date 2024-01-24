/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.integration.db.manager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.csi.epay.epayservices.integration.db.entities.EpayTTracciabilitaChiamanteEsterno;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.ChiamanteEsterno;
import it.csi.epay.epayservices.model.TracciabilitaChiamanteEsterno;



/**
 *
 */
@Stateless ( name = "ChiamataEsternaSincronaSplitManager", mappedName = "ChiamataEsternaSincronaSplitManager" )
public class ChiamataEsternaSincronaSplitManager extends _Manager {

    @PersistenceContext
    private EntityManager entityManager;

    public TracciabilitaChiamanteEsterno ricercaIdentificativoPagamento ( String identificativoPagamento ) throws NoDataException {
    	try {
    		TypedQuery<EpayTTracciabilitaChiamanteEsterno> query = entityManager.createNamedQuery ( "EpayTTracciabilitaChiamanteEsterno.findByIdentificativoPagamento", EpayTTracciabilitaChiamanteEsterno.class );
    		query.setParameter ( "identificativoPagamento", identificativoPagamento );
    		EpayTTracciabilitaChiamanteEsterno tTracciabilitaChiamanteEsterno = query.getSingleResult ();
    		return mappaTracciabilitaChiamanteEsterno ( tTracciabilitaChiamanteEsterno );
    	 } catch ( NoResultException e ) {
             throw new NoDataException ( "Chiamante esterno non riconosciuto, non attivo o passphrase errata", e );
         }
    }

    private TracciabilitaChiamanteEsterno mappaTracciabilitaChiamanteEsterno ( EpayTTracciabilitaChiamanteEsterno tTracciabilitaChiamanteEsterno ) {

        TracciabilitaChiamanteEsterno tracciabilitaChiamanteEsterno
            = map ( tTracciabilitaChiamanteEsterno, TracciabilitaChiamanteEsterno.class );
        tracciabilitaChiamanteEsterno
            .setChiamanteEsterno ( map ( tTracciabilitaChiamanteEsterno.getEpayDChiamanteEsterno (), ChiamanteEsterno.class ) );
        tracciabilitaChiamanteEsterno.setCodiceFiscale ( tTracciabilitaChiamanteEsterno.getCodiceFiscale () );
        tracciabilitaChiamanteEsterno.setIdChiamata ( tTracciabilitaChiamanteEsterno.getIdChiamata () );
        tracciabilitaChiamanteEsterno.setIdTransazione ( tTracciabilitaChiamanteEsterno.getIdTransazione () );
        tracciabilitaChiamanteEsterno.setIuv ( tTracciabilitaChiamanteEsterno.getIuv () );
        tracciabilitaChiamanteEsterno.setRemoteHost ( tTracciabilitaChiamanteEsterno.getRemoteHost () );
        tracciabilitaChiamanteEsterno.setIdentificativoPagamento ( tTracciabilitaChiamanteEsterno.getIdentificativoPagamento () );
        tracciabilitaChiamanteEsterno.setTimestampChiamata ( tTracciabilitaChiamanteEsterno.getTimestampChiamata () );

        return tracciabilitaChiamanteEsterno;
    }

    public boolean verificaAutorizzazioneChiamanteEsterno ( String codiceChiamante, Long idTipoPagamento ) throws NoDataException {
        Boolean autorizzazioneVerificata = false;

        try {
            TypedQuery<Long> query = entityManager
                .createNamedQuery ( "EpayRAutorizzazioneChiamanteTipoPagamento.ricercaChiamanteByCodiceTipoPagamentoDateInizioDataFine",
                    Long.class );
            query.setParameter ( "codiceChiamante", codiceChiamante );
            query.setParameter ( "idTipoPagamento", idTipoPagamento );
            autorizzazioneVerificata = ( query.getSingleResult () > 0 );
        } catch ( NoResultException e ) {
            throw new NoDataException ( "Chiamante esterno non riconosciuto, non attivo o passphrase errata", e );
        }

        return autorizzazioneVerificata;
    }

    public boolean verificaAutorizzazioneChiamanteEsternoAutorizzazioneChiamante ( String codiceChiamante, String codiceAutorizzazioneChiamante ) throws NoDataException {
        Boolean autorizzazioneVerificata = false;

        try {
            TypedQuery<Long> query = entityManager
                .createNamedQuery ( "EpayRChiamanteAutorizzazioneChiamante.ricercaChiamanteByCodiceAutorizzazioneChiamante",
                    Long.class );
            query.setParameter ( "codiceChiamante", codiceChiamante );
            query.setParameter ( "codiceAutorizzazioneChiamante", codiceAutorizzazioneChiamante );
            autorizzazioneVerificata = ( query.getSingleResult () > 0 );
        } catch ( NoResultException e ) {
            throw new NoDataException ( "Chiamante esterno non associato all'autorizzazione chiamante", e );
        }

        return autorizzazioneVerificata;
    }
}
