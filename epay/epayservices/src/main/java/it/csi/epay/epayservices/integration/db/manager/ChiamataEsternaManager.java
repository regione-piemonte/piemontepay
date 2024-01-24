/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import it.csi.epay.epayservices.integration.db.entities.EpayDChiamanteEsterno;
import it.csi.epay.epayservices.integration.db.entities.EpayTChiamataEsternaNonValida;
import it.csi.epay.epayservices.integration.db.entities.EpayTTracciabilitaChiamanteEsterno;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.ChiamanteEsterno;
import it.csi.epay.epayservices.model.ChiamataEsternaNonValida;
import it.csi.epay.epayservices.model.TracciabilitaChiamanteEsterno;
import it.csi.epay.epayservices.model.v1.ChiamanteEsternoCommonInput;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless ( name = "ChiamataEsternaManager", mappedName = "ChiamataEsternaManager" )
public class ChiamataEsternaManager extends _Manager {

    @PersistenceContext
    private EntityManager entityManager;

    public Long inserisci ( TracciabilitaChiamanteEsterno chiamanteEsterna ) throws IllegalArgumentException {

        EpayDChiamanteEsterno chiamanteEsterno
        = entityManager.find ( EpayDChiamanteEsterno.class, chiamanteEsterna.getChiamanteEsterno ().getCodiceChiamante () );

        EpayTTracciabilitaChiamanteEsterno tracciabilitaChiamanteEsterno = new EpayTTracciabilitaChiamanteEsterno ();
        tracciabilitaChiamanteEsterno.setCodiceFiscale ( chiamanteEsterna.getCodiceFiscale () );
        tracciabilitaChiamanteEsterno.setDigest ( chiamanteEsterna.getDigest () );
        tracciabilitaChiamanteEsterno.setEpayDChiamanteEsterno ( chiamanteEsterno );
        tracciabilitaChiamanteEsterno.setIuv ( chiamanteEsterna.getIuv () );
        tracciabilitaChiamanteEsterno.setRemoteHost ( chiamanteEsterna.getRemoteHost () );
        tracciabilitaChiamanteEsterno.setIdentificativoPagamento ( chiamanteEsterna.getIdentificativoPagamento () );
        tracciabilitaChiamanteEsterno.setDescrizioneChiamante ( chiamanteEsterno.getDescrizioneChiamante () );
        //CSI_PAG_AM-6491 INIZIO
        tracciabilitaChiamanteEsterno.setIdTransazione ( chiamanteEsterna.getIdTransazione () );
        //CSI_PAG_AM-6491 FINE
        entityManager.persist ( tracciabilitaChiamanteEsterno );
        entityManager.flush ();
        return tracciabilitaChiamanteEsterno.getIdChiamata ();
    }

    public Long inserisci ( ChiamanteEsternoCommonInput input ) {
		EpayDChiamanteEsterno chiamanteEsterno = entityManager.find ( EpayDChiamanteEsterno.class, input.getCodiceChiamante () );
		//
		EpayTTracciabilitaChiamanteEsterno tracciabilitaChiamanteEsterno = new EpayTTracciabilitaChiamanteEsterno ();
		tracciabilitaChiamanteEsterno.setEpayDChiamanteEsterno ( chiamanteEsterno );
		tracciabilitaChiamanteEsterno.setCodiceFiscale ( input.getCodiceFiscaleEnte () );
		tracciabilitaChiamanteEsterno.setTimestampChiamata ( input.getTimestampChiamata () );
		tracciabilitaChiamanteEsterno.setRemoteHost ( input.getIpChiamante () );
		tracciabilitaChiamanteEsterno.setIuv ( input.getIuv () );
		entityManager.persist ( tracciabilitaChiamanteEsterno );
		entityManager.flush ();
		return tracciabilitaChiamanteEsterno.getIdChiamata ();
	}

    public TracciabilitaChiamanteEsterno ricerca ( String idTransazione ) throws NoDataException, IllegalAccessException {
        TracciabilitaChiamanteEsterno tracciabilitaChiamanteEsterno = null;
        try {
            TypedQuery<EpayTTracciabilitaChiamanteEsterno> query
            = entityManager.createNamedQuery ( "EpayTTracciabilitaChiamanteEsterno.findByIdTransazione", EpayTTracciabilitaChiamanteEsterno.class );
            query.setParameter ( "idTransazione", idTransazione );
            EpayTTracciabilitaChiamanteEsterno tTracciabilitaChiamanteEsterno = query.getSingleResult ();

            tracciabilitaChiamanteEsterno = mappaTracciabilitaChiamanteEsterno ( tTracciabilitaChiamanteEsterno );

        } catch ( NoResultException e ) {
            throw new NoDataException ( e );
        }
        return tracciabilitaChiamanteEsterno;
    }

    public Long ricercaIdentificativoPagamento ( String identificativoPagamento ) throws NoDataException, IllegalAccessException {

        TypedQuery<Long> query = entityManager.createNamedQuery ( "EpayTTracciabilitaChiamanteEsterno.countByIdentificativoPagamento", Long.class );
        query.setParameter ( "identificativoPagamento", identificativoPagamento );
        return query.getSingleResult ();
    }

    public Long aggiorna ( TracciabilitaChiamanteEsterno chiamataEsterna ) throws NoDataException, IllegalAccessException {

        EpayTTracciabilitaChiamanteEsterno tTracciabilitaChiamanteEsterno
        = entityManager.find ( EpayTTracciabilitaChiamanteEsterno.class, chiamataEsterna.getIdChiamata () );

        tTracciabilitaChiamanteEsterno.setIdTransazione ( chiamataEsterna.getIdTransazione () );
        tTracciabilitaChiamanteEsterno.setIuv ( chiamataEsterna.getIuv () );

        entityManager.persist ( tTracciabilitaChiamanteEsterno );
        entityManager.flush ();

        return tTracciabilitaChiamanteEsterno.getIdChiamata ();

    }

    public ChiamanteEsterno recuperaChiamanteEsterno ( String codiceChiamanteEsterno ) throws NoDataException, IllegalAccessException {

        ChiamanteEsterno chiamanteEsterno;
        try {
            TypedQuery<EpayDChiamanteEsterno> query = entityManager
                            .createNamedQuery ( "EpayDChiamanteEsterno.ricercaChiamanteByCodiceAndDataInizioDataFine", EpayDChiamanteEsterno.class );
            query.setParameter ( "codiceChiamante", codiceChiamanteEsterno );
            EpayDChiamanteEsterno dChiamanteEsterno = query.getSingleResult ();
            chiamanteEsterno = map ( dChiamanteEsterno, ChiamanteEsterno.class );
        } catch ( NoResultException e ) {
            throw new NoDataException ( "Chiamante esterno non riconosciuto, non attivo o passphrase errata", e );
        }

        return chiamanteEsterno;

    }

    private TracciabilitaChiamanteEsterno mappaTracciabilitaChiamanteEsterno (
        EpayTTracciabilitaChiamanteEsterno tTracciabilitaChiamanteEsterno ) {

        TracciabilitaChiamanteEsterno tracciabilitaChiamanteEsterno = map ( tTracciabilitaChiamanteEsterno, TracciabilitaChiamanteEsterno.class );
        tracciabilitaChiamanteEsterno.setChiamanteEsterno ( map ( tTracciabilitaChiamanteEsterno.getEpayDChiamanteEsterno (), ChiamanteEsterno.class ) );
        tracciabilitaChiamanteEsterno.setCodiceFiscale ( tTracciabilitaChiamanteEsterno.getCodiceFiscale () );
        tracciabilitaChiamanteEsterno.setDigest ( tTracciabilitaChiamanteEsterno.getDigest () );
        tracciabilitaChiamanteEsterno.setIdChiamata ( tTracciabilitaChiamanteEsterno.getIdChiamata () );
        tracciabilitaChiamanteEsterno.setIdTransazione ( tTracciabilitaChiamanteEsterno.getIdTransazione () );
        tracciabilitaChiamanteEsterno.setIuv ( tTracciabilitaChiamanteEsterno.getIuv () );
        tracciabilitaChiamanteEsterno.setRemoteHost ( tTracciabilitaChiamanteEsterno.getRemoteHost () );
        tracciabilitaChiamanteEsterno.setIdentificativoPagamento ( tTracciabilitaChiamanteEsterno.getIdentificativoPagamento () );
        tracciabilitaChiamanteEsterno.setTimestampChiamata ( tTracciabilitaChiamanteEsterno.getTimestampChiamata () );

        return tracciabilitaChiamanteEsterno;
    }

    @TransactionAttribute ( TransactionAttributeType.REQUIRES_NEW )
    public Long inserisci ( ChiamataEsternaNonValida chiamataEsternaNonValida ) throws IllegalArgumentException {
        EpayTChiamataEsternaNonValida epayTChiamataEsternaNonValida = mappaEpayTChiamataEsternaNonValida ( chiamataEsternaNonValida );
        entityManager.persist ( epayTChiamataEsternaNonValida );
        entityManager.flush ();
        return epayTChiamataEsternaNonValida.getIdChiamata ();
    }

    @TransactionAttribute ( TransactionAttributeType.REQUIRES_NEW )
    public Long inserisci ( it.csi.epay.epayservices.model.v1.ChiamataEsternaNonValida chiamataEsternaNonValida ) throws IllegalArgumentException {
        EpayTChiamataEsternaNonValida epayTChiamataEsternaNonValida = mappaEpayTChiamataEsternaNonValida ( chiamataEsternaNonValida );
        entityManager.persist ( epayTChiamataEsternaNonValida );
        entityManager.flush ();
        return epayTChiamataEsternaNonValida.getIdChiamata ();
    }

    public ChiamataEsternaNonValida ricerca ( ChiamataEsternaNonValida chiamataEsternaNonValida ) throws NoDataException, IllegalAccessException {

        EpayTChiamataEsternaNonValida epayTChiamataEsternaNonValida
        = entityManager.find ( EpayTChiamataEsternaNonValida.class, chiamataEsternaNonValida.getIdChiamata () );

		return mappaChiamataEsternaNonValida ( epayTChiamataEsternaNonValida );

    }

    private ChiamataEsternaNonValida mappaChiamataEsternaNonValida ( EpayTChiamataEsternaNonValida epayTChiamataEsternaNonValida ) {

        ChiamataEsternaNonValida chiamataEsternaNonValida = map ( epayTChiamataEsternaNonValida, ChiamataEsternaNonValida.class );

        chiamataEsternaNonValida.setCodiceChiamante ( epayTChiamataEsternaNonValida.getCodiceChiamante () );
        chiamataEsternaNonValida.setCodiceFiscale ( epayTChiamataEsternaNonValida.getCodiceFiscale () );
        chiamataEsternaNonValida.setDigest ( epayTChiamataEsternaNonValida.getDigest () );
        chiamataEsternaNonValida.setIdChiamata ( epayTChiamataEsternaNonValida.getIdChiamata () );
        chiamataEsternaNonValida.setIuv ( epayTChiamataEsternaNonValida.getIuv () );
        chiamataEsternaNonValida.setRemoteHost ( epayTChiamataEsternaNonValida.getRemoteHost () );
        chiamataEsternaNonValida.setIdentificativoPagamento ( epayTChiamataEsternaNonValida.getIdentificativoPagamento () );
        chiamataEsternaNonValida.setTimestampChiamata ( epayTChiamataEsternaNonValida.getTimestampChiamata () );

        return chiamataEsternaNonValida;
    }

    private EpayTChiamataEsternaNonValida mappaEpayTChiamataEsternaNonValida ( ChiamataEsternaNonValida chiamataEsternaNonValida ) {

        EpayTChiamataEsternaNonValida epayTChiamataEsternaNonValida = map ( chiamataEsternaNonValida, EpayTChiamataEsternaNonValida.class );

        epayTChiamataEsternaNonValida.setCodiceChiamante ( epayTChiamataEsternaNonValida.getCodiceChiamante () );
        epayTChiamataEsternaNonValida.setCodiceFiscale ( epayTChiamataEsternaNonValida.getCodiceFiscale () );
        epayTChiamataEsternaNonValida.setDigest ( epayTChiamataEsternaNonValida.getDigest () );
        epayTChiamataEsternaNonValida.setIdChiamata ( epayTChiamataEsternaNonValida.getIdChiamata () );
        epayTChiamataEsternaNonValida.setIuv ( epayTChiamataEsternaNonValida.getIuv () );
        epayTChiamataEsternaNonValida.setRemoteHost ( epayTChiamataEsternaNonValida.getRemoteHost () );
        epayTChiamataEsternaNonValida.setIdentificativoPagamento ( epayTChiamataEsternaNonValida.getIdentificativoPagamento () );
        epayTChiamataEsternaNonValida.setTimestampChiamata ( epayTChiamataEsternaNonValida.getTimestampChiamata () );

        return epayTChiamataEsternaNonValida;
    }

    private EpayTChiamataEsternaNonValida mappaEpayTChiamataEsternaNonValida ( it.csi.epay.epayservices.model.v1.ChiamataEsternaNonValida chiamataEsternaNonValida ) {
        
        EpayTChiamataEsternaNonValida epayTChiamataEsternaNonValida = map ( chiamataEsternaNonValida, EpayTChiamataEsternaNonValida.class );
        
        epayTChiamataEsternaNonValida.setCodiceChiamante ( epayTChiamataEsternaNonValida.getCodiceChiamante () );
        epayTChiamataEsternaNonValida.setCodiceFiscale ( epayTChiamataEsternaNonValida.getCodiceFiscale () );
        epayTChiamataEsternaNonValida.setDigest ( epayTChiamataEsternaNonValida.getDigest () );
        epayTChiamataEsternaNonValida.setIdChiamata ( epayTChiamataEsternaNonValida.getIdChiamata () );
        epayTChiamataEsternaNonValida.setIuv ( epayTChiamataEsternaNonValida.getIuv () );
        epayTChiamataEsternaNonValida.setRemoteHost ( epayTChiamataEsternaNonValida.getRemoteHost () );
        epayTChiamataEsternaNonValida.setIdentificativoPagamento ( epayTChiamataEsternaNonValida.getIdentificativoPagamento () );
        epayTChiamataEsternaNonValida.setTimestampChiamata ( epayTChiamataEsternaNonValida.getTimestampChiamata () );
        
        return epayTChiamataEsternaNonValida;
    }

}
