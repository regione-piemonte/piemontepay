/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.business.impl;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.jboss.resteasy.spi.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaybeapi.api.interceptors.AuthenticationInterceptor;
import it.csi.epay.epaybeapi.business.ClientService;
import it.csi.epay.epaybeapi.business.MessageService;
import it.csi.epay.epaybeapi.dto.exception.ManagedException;
import it.csi.epay.epaybeapi.dto.request.ClientRegistrationRequest;
import it.csi.epay.epaybeapi.dto.request.ClientUpdateRequest;
import it.csi.epay.epaybeapi.dto.response.ClientRegistrationResponse;
import it.csi.epay.epaybeapi.dto.security.ClientInfo;
import it.csi.epay.epaybeapi.dto.security.ClientInfoDTO;
import it.csi.epay.epaybeapi.integration.domain.EpayDChiamanteEsterno;
import it.csi.epay.epaybeapi.integration.dto.EpayDChiamanteEsternoDTO;
import it.csi.epay.epaybeapi.integration.mapper.ClientInfoMapper;
import it.csi.epay.epaybeapi.integration.mapper.EpayDChiamanteEsternoMapper;
import it.csi.epay.epaybeapi.integration.repository.EpayDChiamanteEsternoRepository;
import it.csi.epay.epaybeapi.util.Messages;
import it.csi.epay.epaybeapi.util.SecurityUtils;


/**
 * Implementazione del servizio per la gestione del client
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private static final Logger logger = LoggerFactory.getLogger ( ClientService.class );

    @Autowired
    private ClientInfoMapper clientInfoMapper;

    //    @Autowired
    //    private CryptoService cryptoService;

    @Autowired
    private EpayDChiamanteEsternoMapper fruitoreMapper;

    @Autowired
    private EpayDChiamanteEsternoRepository fruitoreRepository;

    @Autowired
    private MessageService messageService;

    @Override
    public ClientInfo getClientCorrente () {
        logger.trace ( "getClientCorrente()" );

        HttpServletRequest req = SecurityUtils.getCurrentRequest ();

        if ( req == null ) {
            return ProfilazioneClientServiceImpl.getClientSistema ();
        }

        ClientInfo output = (ClientInfo) req.getAttribute ( AuthenticationInterceptor.CLIENTINFO_REQUESTATTR );

        return ( output == null ) ? ProfilazioneClientServiceImpl.getClientAnonimo () : output;
    }

    @Override
    public ClientInfoDTO getClientCorrenteDTO () {
        return clientInfoMapper.toDTO ( getClientCorrente () );
    }

    @Override
    public ClientRegistrationResponse registerClient ( ClientRegistrationRequest request ) {

        if ( fruitoreRepository.findOne ( request.getCodiceChiamante () ) != null ) {
            throw new ManagedException ( HttpStatus.BAD_REQUEST, messageService.getMessaggio ( Messages.CLIENT_CRUD_CODE_CONFLICT ) );
        }

        String passphrase = generaPassword ();
        // String passphraseCriptata = cryptoService.encrypt ( passphrase );

        EpayDChiamanteEsterno fruitore = new EpayDChiamanteEsterno ();
        fruitore.setCodiceChiamante ( request.getCodiceChiamante () );
        fruitore.setDescrizioneChiamante ( request.getDescrizioneChiamante () );
        fruitore.setUrl ( request.getUrl () );
        fruitore.setDataInizioValidita ( new Date () );
        fruitore.setDataFineValidita ( null );
        fruitore.setPassphrase ( passphrase );
        fruitore = fruitoreRepository.save ( fruitore );

        return ClientRegistrationResponse.builder ()
            .withPassphrase ( passphrase )
            .build ();
    }

    private String generaPassword () {
        String passphrase = UUID.randomUUID ().toString ();
        return passphrase;
    }

    @Transactional ( readOnly = true )
    @Override
    public EpayDChiamanteEsternoDTO get ( String codice ) {

        EpayDChiamanteEsterno record = fruitoreRepository.findOne ( codice );
        if ( record == null ) {
            throw new NotFoundException ( "Fruitore non trovato" );
        }

        return fruitoreMapper.toDTO ( record );
    }

    @Override
    public EpayDChiamanteEsternoDTO update ( String codice, ClientUpdateRequest request ) {
        EpayDChiamanteEsterno record = fruitoreRepository.findOne ( codice );
        if ( record == null ) {
            throw new NotFoundException ( "Fruitore non trovato" );
        }

        record.setDataInizioValidita ( request.getDataInizioValidita () );
        record.setDataFineValidita ( request.getDataFineValidita () );
        record.setDescrizioneChiamante ( request.getDescrizioneChiamante () );
        record.setUrl ( request.getUrl () );

        record = fruitoreRepository.save ( record );
        return fruitoreMapper.toDTO ( record );
    }

    @Override
    public void delete ( String codice ) {
        EpayDChiamanteEsterno record = fruitoreRepository.findOne ( codice );
        if ( record == null ) {
            throw new NotFoundException ( "Fruitore non trovato" );
        }

        // fruitoreRepository.delete ( record );

        record.setDataFineValidita ( new Date () );

        record = fruitoreRepository.save ( record );
    }

}
