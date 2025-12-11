/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.business.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epayapi.business.ConfigurazioneService;
import it.csi.epay.epayapi.business.MessageService;
import it.csi.epay.epayapi.business.ProfilazioneClientService;
import it.csi.epay.epayapi.dto.exception.ManagedException;
import it.csi.epay.epayapi.dto.security.ClientInfo;
import it.csi.epay.epayapi.dto.security.ScopeDTO;
import it.csi.epay.epayapi.dto.security.Scopes;
import it.csi.epay.epayapi.integration.domain.EpayDAutorizzazioneChiamante;
import it.csi.epay.epayapi.integration.domain.EpayDChiamanteEsterno;
import it.csi.epay.epayapi.integration.repository.EpayDAutorizzazioneChiamanteRepository;
import it.csi.epay.epayapi.integration.repository.EpayDChiamanteEsternoRepository;
import it.csi.epay.epayapi.util.Constants;
import it.csi.epay.epayapi.util.Messages;
import it.csi.epay.epayapi.util.ParametriApplicativo;


/**
 * Implementazione del servizio per la gestione della profilazione del client
 */
@Service
@Transactional
public class ProfilazioneClientServiceImpl implements ProfilazioneClientService {

    private static final String BASIC_AUTHENTICATION = "Basic";

    private final static Logger logger = LogManager.getLogger ( ProfilazioneClientService.class );

    @Autowired
    private EpayDChiamanteEsternoRepository fruitoreRepository;

    @Autowired
    private EpayDAutorizzazioneChiamanteRepository authRepository;

    @Autowired
    private ConfigurazioneService configurazioneService;

    @Autowired
    private MessageService messageService;

    private boolean isBypassed () {
        return configurazioneService.getConfig ( ParametriApplicativo.FILTER_CLIENT_AUTH_BYPASSED ).asBoolean ();
    }

    private static final ClientInfo clientAnonimo = ClientInfo.builder ()
        .withNome ( "GUEST" )
        .withCodice ( "GUEST_CLIENT" )
        .withScopes ( Collections.emptyList () )
        .withAnonimo ( true )
        .build ();

    private static final ClientInfo clientSistema = ClientInfo.builder ()
        .withNome ( "SYSTEM" )
        .withCodice ( "SYSTEM" )
        .withScopes ( Arrays.asList ( ScopeDTO.fromScope ( Scopes.PLATFORM_MANAGEMENT ) ) )
        .withAnonimo ( false )
        .build ();

    public static ClientInfo getClientAnonimo () {
        return clientAnonimo;
    }

    public static ClientInfo getClientSistema () {
        return clientSistema;
    }

    @Transactional ( readOnly = true )
    @Override
    public ClientInfo caricaClientInfo ( String authHeader ) {
        logger.debug ( "carico info client da header [" + authHeader + "]" );

        if ( StringUtils.isEmpty ( authHeader ) ) {
            return null;
        }

        if ( !authHeader.toLowerCase ().startsWith ( BASIC_AUTHENTICATION.toLowerCase () + " " ) ) {
            throw new ManagedException ( HttpStatus.FORBIDDEN, messageService.getMessaggio ( Messages.AUTH_FAILED_UNSUPPORTED_STRATEGY ) );
        }

        String usernpass = new String ( Base64.getDecoder ().decode ( authHeader.substring ( BASIC_AUTHENTICATION.length () + 1 ) ) );
        int index = usernpass.indexOf ( ":" );

        String username = usernpass.substring ( 0, index );
        String password = usernpass.substring ( index + 1 );

        if ( StringUtils.isEmpty ( username ) ) {
            throw new ManagedException ( HttpStatus.FORBIDDEN, messageService.getMessaggio ( Messages.AUTH_FAILED_INVALID_CLIENT_CODE ) );
        }

        EpayDChiamanteEsterno fruitore = fruitoreRepository.findOne ( username );

        // verifico che il fruitore esista
        if ( fruitore == null ) {
            throw new ManagedException ( HttpStatus.FORBIDDEN, messageService.getMessaggio ( Messages.AUTH_FAILED_INVALID_CLIENT_CODE ) );
        }

        // verifico che il fruitore sia in fase di validita'
        if ( !fruitore.inValidInterval () ) {
            throw new ManagedException ( HttpStatus.FORBIDDEN, messageService.getMessaggio ( Messages.AUTH_FAILED_EXPIRED_CLIENT ) );
        }

        if ( !isBypassed () ) {
            // verifico che il fruitore sia corretto e che la passphrase corrisponda

            if ( StringUtils.isEmpty ( password ) ) {
                throw new ManagedException ( HttpStatus.FORBIDDEN, messageService.getMessaggio ( Messages.AUTH_FAILED_INVALID_PASSWORD ) );
            }

            if ( configurazioneService.requireConfig ( ParametriApplicativo.FILTER_CLIENT_AUTH_CHECK_PASSPHRASE ).asBoolean () ) {
                if ( !password.equals ( fruitore.getPassphrase () ) ) {
                    throw new ManagedException ( HttpStatus.FORBIDDEN, messageService.getMessaggio ( Messages.AUTH_FAILED_INVALID_PASSWORD ) );
                }
            }
        }

        return ClientInfo.builder ()
            .withNome ( fruitore.getDescrizioneChiamante () )
            .withCodice ( fruitore.getCodiceChiamante () )
            .withScopes ( getScopePerFruitore ( fruitore ) )
            .withAnonimo ( false )
            .build ();
    }

    @Transactional ( readOnly = true )
    private List<ScopeDTO> getScopePerFruitore ( EpayDChiamanteEsterno fruitore ) {

        List<ScopeDTO> output = new ArrayList<> ();

        for ( EpayDAutorizzazioneChiamante auth: authRepository.findAll () ) {
            if ( auth.getAssegnazioneAutomatica () != null && auth.getAssegnazioneAutomatica ().equalsIgnoreCase ( Constants.TRUE ) ) {
                output.add ( ScopeDTO.builder ()
                    .withCodice ( auth.getCodice () )
                    .withDescrizione ( auth.getDescrizione () )
                    .build () );
            }
        }

        for ( EpayDAutorizzazioneChiamante auth: fruitore.getAutorizzazioni () ) {
            output.add ( ScopeDTO.builder ()
                .withCodice ( auth.getCodice () )
                .withDescrizione ( auth.getDescrizione () )
                .build () );
        }

        return output;
    }

}
