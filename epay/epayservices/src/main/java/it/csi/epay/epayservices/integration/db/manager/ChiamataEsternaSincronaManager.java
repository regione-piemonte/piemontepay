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

import it.csi.epay.epayservices.integration.db.entities.EpayDChiamanteEsterno;
import it.csi.epay.epayservices.integration.db.entities.EpayRAutorizzazioneChiamanteTipoPagamento;
import it.csi.epay.epayservices.integration.db.entities.EpayTChiamataEsternaNonValida;
import it.csi.epay.epayservices.integration.db.entities.EpayTTracciabilitaChiamanteEsterno;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.ChiamanteEsterno;
import it.csi.epay.epayservices.model.ChiamataEsternaSincronaNonValida;
import it.csi.epay.epayservices.model.TracciabilitaChiamanteEsternoSincrono;


@Stateless ( name = "ChiamataEsternaSincronaManager", mappedName = "ChiamataEsternaSincronaManager" )
public class ChiamataEsternaSincronaManager extends _Manager {

    @PersistenceContext
    private EntityManager entityManager;

    public Boolean verificaAutorizzazioneChiamanteEsternoSincrono ( TracciabilitaChiamanteEsternoSincrono chiamataEsternaSincrona )
                    throws NoDataException, IllegalArgumentException {
        Boolean autorizzazioneVerificata = false;
        try {
            TypedQuery<EpayRAutorizzazioneChiamanteTipoPagamento> query = entityManager
                .createNamedQuery ( "EpayDChiamanteEsterno.ricercaChiamanteByCodiceAndDataInizioDataFine", EpayRAutorizzazioneChiamanteTipoPagamento.class );
            query.setParameter ( "codiceChiamante", chiamataEsternaSincrona.getCodiceChiamante () );
            query.setParameter ( "tipoPagamento", chiamataEsternaSincrona.getTipoPagamaneto () );
            autorizzazioneVerificata = ( query.getResultList ().size () > 0 );
        } catch ( NoResultException e ) {
            throw new NoDataException ( "Chiamante esterno non riconosciuto, non attivo o passphrase errata", e );
        }
        return autorizzazioneVerificata;
    }

    public Long inserisci ( TracciabilitaChiamanteEsternoSincrono chiamataEsternaSincrona ) throws IllegalArgumentException {
        EpayDChiamanteEsterno chiamanteEsterno
            = entityManager.find ( EpayDChiamanteEsterno.class, chiamataEsternaSincrona.getChiamanteEsterno ().getCodiceChiamante () );

        EpayTTracciabilitaChiamanteEsterno tracciabilitaChiamanteEsterno = new EpayTTracciabilitaChiamanteEsterno ();
        tracciabilitaChiamanteEsterno.setCodiceFiscale ( chiamataEsternaSincrona.getCodiceFiscaleEnte () );
        tracciabilitaChiamanteEsterno.setEpayDChiamanteEsterno ( chiamanteEsterno );
        tracciabilitaChiamanteEsterno.setIuv ( chiamataEsternaSincrona.getIuv () );
        tracciabilitaChiamanteEsterno.setRemoteHost ( chiamataEsternaSincrona.getRemoteHost () );
        tracciabilitaChiamanteEsterno.setIdentificativoPagamento ( chiamataEsternaSincrona.getIdentificativoPagamento () );
        tracciabilitaChiamanteEsterno.setDescrizioneChiamante ( chiamataEsternaSincrona.getDescrizioneChiamante () );
        entityManager.persist ( tracciabilitaChiamanteEsterno );
        entityManager.flush ();
        return tracciabilitaChiamanteEsterno.getIdChiamata ();
    }

    public Long ricercaIdentificativoPagamento ( String identificativoPagamento ) throws NoDataException, IllegalAccessException {
        TypedQuery<Long> query = entityManager.createNamedQuery ( "EpayTTracciabilitaChiamanteEsterno.countByIdentificativoPagamento", Long.class );
        query.setParameter ( "identificativoPagamento", identificativoPagamento );
        return query.getSingleResult ();
    }

    public Long aggiorna ( TracciabilitaChiamanteEsternoSincrono chiamataEsternaSincrona ) throws NoDataException, IllegalAccessException {
        EpayTTracciabilitaChiamanteEsterno tTracciabilitaChiamanteEsterno
            = entityManager.find ( EpayTTracciabilitaChiamanteEsterno.class, chiamataEsternaSincrona.getIdChiamata () );

        tTracciabilitaChiamanteEsterno.setIdTransazione ( chiamataEsternaSincrona.getIdTransazione () );

        entityManager.persist ( tTracciabilitaChiamanteEsterno );
        entityManager.flush ();

        return tTracciabilitaChiamanteEsterno.getIdChiamata ();
    }

    public ChiamanteEsterno recuperaChiamanteEsterno ( String codiceChiamanteEsterno ) throws NoDataException, IllegalAccessException {
        ChiamanteEsterno chiamanteEsterno = null;
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

    public TracciabilitaChiamanteEsternoSincrono ricerca ( String idTransazione ) throws NoDataException, IllegalAccessException {
        TypedQuery<EpayTTracciabilitaChiamanteEsterno> query
            = entityManager.createNamedQuery ( "EpayTTracciabilitaChiamanteEsterno.findByIdTransazione", EpayTTracciabilitaChiamanteEsterno.class );
        query.setParameter ( "idTransazione", idTransazione );
        EpayTTracciabilitaChiamanteEsterno tTracciabilitaChiamanteEsterno = query.getSingleResult ();

        TracciabilitaChiamanteEsternoSincrono tracciabilitaChiamanteEsternoSincrono = mappaTracciabilitaChiamanteEsterno ( tTracciabilitaChiamanteEsterno );

        return tracciabilitaChiamanteEsternoSincrono;
    }

    public Long inserisci ( ChiamataEsternaSincronaNonValida chiamataEsternaSincronaNonValida ) throws IllegalArgumentException {
        EpayTChiamataEsternaNonValida epayTChiamataEsternaNonValida = mappaEpayTChiamataEsternaSincronaNonValida ( chiamataEsternaSincronaNonValida );
        entityManager.persist ( epayTChiamataEsternaNonValida );
        entityManager.flush ();
        return epayTChiamataEsternaNonValida.getIdChiamata ();
    }

    public ChiamataEsternaSincronaNonValida ricerca ( ChiamataEsternaSincronaNonValida chiamataEsternaSincronaNonValida )
                    throws NoDataException, IllegalAccessException {

        EpayTChiamataEsternaNonValida epayTChiamataEsternaNonValida
            = entityManager.find ( EpayTChiamataEsternaNonValida.class, chiamataEsternaSincronaNonValida.getIdChiamata () );
        ChiamataEsternaSincronaNonValida chiamataEsternaSincronaNonValidaRet = mappaChiamataEsternaSincronaNonValida ( epayTChiamataEsternaNonValida );

        return chiamataEsternaSincronaNonValidaRet;

    }

    private ChiamataEsternaSincronaNonValida mappaChiamataEsternaSincronaNonValida ( EpayTChiamataEsternaNonValida epayTChiamataEsternaNonValida ) {

        ChiamataEsternaSincronaNonValida chiamataEsternaSincronaNonValida = map ( epayTChiamataEsternaNonValida, ChiamataEsternaSincronaNonValida.class );

        chiamataEsternaSincronaNonValida.setCodiceChiamante ( epayTChiamataEsternaNonValida.getCodiceChiamante () );
        chiamataEsternaSincronaNonValida.setCodiceFiscaleEnte ( epayTChiamataEsternaNonValida.getCodiceFiscale () );
        chiamataEsternaSincronaNonValida.setIdChiamata ( epayTChiamataEsternaNonValida.getIdChiamata () );
        chiamataEsternaSincronaNonValida.setIuv ( epayTChiamataEsternaNonValida.getIuv () );
        chiamataEsternaSincronaNonValida.setRemoteHost ( epayTChiamataEsternaNonValida.getRemoteHost () );
        chiamataEsternaSincronaNonValida.setIdentificativoPagamento ( epayTChiamataEsternaNonValida.getIdentificativoPagamento () );
        chiamataEsternaSincronaNonValida.setTimestampChiamata ( epayTChiamataEsternaNonValida.getTimestampChiamata () );

        return chiamataEsternaSincronaNonValida;
    }

    private TracciabilitaChiamanteEsternoSincrono mappaTracciabilitaChiamanteEsterno ( EpayTTracciabilitaChiamanteEsterno tTracciabilitaChiamanteEsterno ) {

        TracciabilitaChiamanteEsternoSincrono tracciabilitaChiamanteEsternoSincrono
            = map ( tTracciabilitaChiamanteEsterno, TracciabilitaChiamanteEsternoSincrono.class );
        tracciabilitaChiamanteEsternoSincrono
            .setChiamanteEsterno ( map ( tTracciabilitaChiamanteEsterno.getEpayDChiamanteEsterno (), ChiamanteEsterno.class ) );
        tracciabilitaChiamanteEsternoSincrono.setCodiceFiscaleEnte ( tTracciabilitaChiamanteEsterno.getCodiceFiscale () );
        tracciabilitaChiamanteEsternoSincrono.setIdChiamata ( tTracciabilitaChiamanteEsterno.getIdChiamata () );
        tracciabilitaChiamanteEsternoSincrono.setIdTransazione ( tTracciabilitaChiamanteEsterno.getIdTransazione () );
        tracciabilitaChiamanteEsternoSincrono.setIuv ( tTracciabilitaChiamanteEsterno.getIuv () );
        tracciabilitaChiamanteEsternoSincrono.setRemoteHost ( tTracciabilitaChiamanteEsterno.getRemoteHost () );
        tracciabilitaChiamanteEsternoSincrono.setIdentificativoPagamento ( tTracciabilitaChiamanteEsterno.getIdentificativoPagamento () );
        tracciabilitaChiamanteEsternoSincrono.setTimestampChiamata ( tTracciabilitaChiamanteEsterno.getTimestampChiamata () );

        return tracciabilitaChiamanteEsternoSincrono;
    }

    private EpayTChiamataEsternaNonValida mappaEpayTChiamataEsternaSincronaNonValida ( ChiamataEsternaSincronaNonValida chiamataEsternaSincronaNonValida ) {

        EpayTChiamataEsternaNonValida epayTChiamataEsternaNonValida = map ( chiamataEsternaSincronaNonValida, EpayTChiamataEsternaNonValida.class );

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

    public boolean verificaAurizzazioneChiamanteEsternoSincrono ( String codiceChiamante, Long idTipoPagamento ) throws NoDataException {
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

}
