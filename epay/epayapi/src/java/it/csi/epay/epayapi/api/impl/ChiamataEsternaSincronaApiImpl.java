/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.api.impl;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.epay.epayapi.api.ChiamataEsternaSincronaApi;
import it.csi.epay.epayapi.api.util.SpringSupportedResource;
import it.csi.epay.epayapi.business.EpayServicesClientService;
import it.csi.epay.epayapi.util.SecurityUtils;
import it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoInput;
import it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoOutput;
import it.csi.epay.epayservices.model.AggiornaPosizioneDebitoriaChiamanteEsternoInput;
import it.csi.epay.epayservices.model.AggiornaPosizioneDebitoriaChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoInputContainer;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoOutputContainer;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoInputContainer;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoOutputContainer;
import it.csi.epay.epayservices.model.GetRTChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetRTChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.PagamentoIuvChiamanteEsternoInput;
import it.csi.epay.epayservices.model.PagamentoIuvChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.PagamentoMarcaBolloChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.PagamentoMarcaDaBolloChiamanteEsternoInput;


/**
 * Risorsa RestEasy per il reperimento di informazioni sull'utente corrente.
 *
 * Nota: questa risorsa vale anche come esempio per l'introduzione al supporto di InitializingBean
 */
@SpringSupportedResource
public class ChiamataEsternaSincronaApiImpl implements ChiamataEsternaSincronaApi {

    private static final Logger logger = LogManager.getLogger ( ChiamataEsternaSincronaApi.class );

    @Autowired
    private EpayServicesClientService service;

    @Override
    public Response accessoChiamanteEsternoSincrono ( AccessoChiamanteEsternoSincronoInput input ) {

        HttpServletRequest request = SecurityUtils.getCurrentRequest ();

        input.setPassword ( "REMOVED" );
        input.setTimestampChiamata ( new Timestamp ( new java.util.Date ().getTime () ) );
        input.setCodiceChiamante ( SecurityUtils.getCurrentClient ().getCodice () );

        if ( request != null ) {
            input.setIpChiamante ( request.getRemoteAddr () );
        } else {
            input.setIpChiamante ( "UNKNOWN" );
        }

        AccessoChiamanteEsternoSincronoOutput result = service.accessoChiamanteEsternoSincrono ( input );

        return Response
                        .status ( result != null && result.getCodiceEsito ().equals ( "000" ) ? 200
                                        : result.getCodiceEsito ().equals ( "200" ) ? 403 : result.getCodiceEsito ().equals ( "300" ) ? 400 : 500 )
                        .entity ( result ).build ();
    }

    @Override
    public Response getIUVChiamanteEsterno ( GetIuvChiamanteEsternoInput input ) {
        logger.debug ( "[getIUVChiamanteEsterno]:: start" );

        HttpServletRequest request = SecurityUtils.getCurrentRequest ();

        input.setTimestampChiamata ( new Timestamp ( new java.util.Date ().getTime () ) );
        input.setCodiceChiamante ( SecurityUtils.getCurrentClient ().getCodice () );

        if ( request != null ) {
            input.setIpChiamante ( request.getRemoteAddr () );
        } else {
            input.setIpChiamante ( "UNKNOWN" );
        }

        GetIuvChiamanteEsternoOutput result = service.getIUVChiamanteEsterno ( input );

        logger.debug ( "[getIUVChiamanteEsterno]:: stop" );

        return Response.status ( result != null && result.getCodiceEsito ().equals ( "000" ) ? 200
                        : result.getCodiceEsito ().equals ( "200" ) ? 403 : result.getCodiceEsito ().equals ( "300" ) ? 400 : 500 )
                        .entity ( result ).build ();
    }

    @Override
    public Response getIUVMultibeneficiarioChiamanteEsterno ( GetIuvMultibeneficiarioChiamanteEsternoInput input ) {
        logger.debug ( "[getIUVMultibeneficiarioChiamanteEsterno]:: start" );

        HttpServletRequest request = SecurityUtils.getCurrentRequest ();

        input.setTimestampChiamata ( new Timestamp ( new java.util.Date ().getTime () ) );
        input.setCodiceChiamante ( SecurityUtils.getCurrentClient ().getCodice () );

        if ( request != null ) {
            input.setIpChiamante ( request.getRemoteAddr () );
        } else {
            input.setIpChiamante ( "UNKNOWN" );
        }

        GetIuvMultibeneficiarioChiamanteEsternoOutput result = service.getIUVMultibeneficiarioChiamanteEsterno ( input );

        logger.debug ( "[getIUVMultibeneficiarioChiamanteEsterno]:: stop" );

        return Response.status ( result != null && result.getCodiceEsito ().equals ( "000" ) ? 200
                        : result.getCodiceEsito ().equals ( "200" ) ? 403 : result.getCodiceEsito ().equals ( "300" ) ? 400 : 500 )
                        .entity ( result ).build ();
    }

    @Override
    public Response pagamentoIUVChiamanteEsterno ( PagamentoIuvChiamanteEsternoInput input ) {
        logger.debug ( "[pagamentoIUV]:: start" );

        HttpServletRequest request = SecurityUtils.getCurrentRequest ();

        input.setTimestampChiamata ( new Timestamp ( new java.util.Date ().getTime () ) );
        input.setCodiceChiamante ( SecurityUtils.getCurrentClient ().getCodice () );

        if ( request != null ) {
            input.setIpChiamante ( request.getRemoteAddr () );
        } else {
            input.setIpChiamante ( "UNKNOWN" );
        }

        PagamentoIuvChiamanteEsternoOutput result = service.getPagamentoIUVChiamanteEsterno ( input );

        logger.debug ( "[pagamentoIUV]:: stop" );

        return Response.status ( result != null && result.getCodiceEsito ().equals ( "000" ) ? 200
                        : result.getCodiceEsito ().equals ( "200" ) ? 403 : result.getCodiceEsito ().equals ( "300" ) ? 400 : 500 )
                        .entity ( result ).build ();
    }

    @Override
    public Response pagamentoMarcaBolloChiamanteEsterno ( PagamentoMarcaDaBolloChiamanteEsternoInput input ) {
        logger.debug ( "[pagamentoMarcaBollo]:: start" );

        HttpServletRequest request = SecurityUtils.getCurrentRequest ();

        input.setTimestampChiamata ( new Timestamp ( new java.util.Date ().getTime () ) );
        input.setCodiceChiamante ( SecurityUtils.getCurrentClient ().getCodice () );

        if ( request != null ) {
            input.setIpChiamante ( request.getRemoteAddr () );
        } else {
            input.setIpChiamante ( "UNKNOWN" );
        }

        PagamentoMarcaBolloChiamanteEsternoOutput result = service.getPagamentoMarcaBolloChiamanteEsterno ( input );

        logger.debug ( "[pagamentoMarcaBollo]:: stop" );

        return Response.status ( result != null && result.getCodiceEsito ().equals ( "000" ) ? 200
                        : result.getCodiceEsito ().equals ( "200" ) ? 403 : result.getCodiceEsito ().equals ( "300" ) ? 400 : 500 )
                        .entity ( result ).build ();
    }

    @Override
    public Response getRTChiamanteEsterno ( GetRTChiamanteEsternoInput input ) {
        logger.debug ( "[getRt]:: start" );

        HttpServletRequest request = SecurityUtils.getCurrentRequest ();

        input.setTimestampChiamata ( new Timestamp ( new java.util.Date ().getTime () ) );
        input.setCodiceChiamante ( SecurityUtils.getCurrentClient ().getCodice () );

        if ( request != null ) {
            input.setIpChiamante ( request.getRemoteAddr () );
        } else {
            input.setIpChiamante ( "UNKNOWN" );
        }

        GetRTChiamanteEsternoOutput result = service.getRTChiamanteEsterno ( input );

        logger.debug ( "[getRt]:: stop" );

        return Response.status ( result != null && result.getCodiceEsito ().equals ( "000" ) ? 200
                        : result.getCodiceEsito ().equals ( "200" ) ? 403 : result.getCodiceEsito ().equals ( "300" ) ? 400 : 500 )
                        .entity ( result ).build ();
    }

    @Override
    public Response getListIUVChiamanteEsterno ( GetIuvChiamanteEsternoInputContainer input ) {
        logger.debug ( "[getListIUVChiamanteEsterno]:: start" );

        HttpServletRequest request = SecurityUtils.getCurrentRequest ();
        // :TODO eliminare questa parte obsoleta
        for ( GetIuvChiamanteEsternoInput inputSingleIUV: input.getElencoPosizioniDaInserire () ) {
            inputSingleIUV.setTimestampChiamata ( new Timestamp ( new java.util.Date ().getTime () ) );
            inputSingleIUV.setCodiceChiamante ( SecurityUtils.getCurrentClient ().getCodice () );
            if ( request != null ) {
                inputSingleIUV.setIpChiamante ( request.getRemoteAddr () );
            } else {
                inputSingleIUV.setIpChiamante ( "UNKNOWN" );
            }
        }

        input.setTimestampChiamata ( new Timestamp ( new java.util.Date ().getTime () ) );
        input.setCodiceChiamante ( SecurityUtils.getCurrentClient ().getCodice () );
        if ( request != null ) {
            input.setIpChiamante ( request.getRemoteAddr () );
        } else {
            input.setIpChiamante ( "UNKNOWN" );
        }
        
        GetIuvChiamanteEsternoOutputContainer result = service.getListIUVChiamanteEsterno ( input );

        logger.debug ( "[getListIUVChiamanteEsterno]:: stop" );

        return Response.status ( result != null && result.getCodiceEsito ().equals ( "000" ) ? 200
                        : result.getCodiceEsito ().equals ( "200" ) ? 403 : result.getCodiceEsito ().equals ( "300" ) ? 400 : 500 )
                        .entity ( result ).build ();
    }

    @Override
	public Response getListIUVMultibeneficiarioChiamanteEsterno ( GetIuvMultibeneficiarioChiamanteEsternoInputContainer input ) {
        logger.debug ( "[getListIUVChiamanteEsterno]:: start" );

        HttpServletRequest request = SecurityUtils.getCurrentRequest (); //:TODO eliminare lista e gestire correttamente le informazioni del chiamante
        
		for ( GetIuvMultibeneficiarioChiamanteEsternoInput inputSingleIUV: input.getElencoPosizioniDaInserire () ) {
            inputSingleIUV.setTimestampChiamata ( new Timestamp ( new java.util.Date ().getTime () ) );
            inputSingleIUV.setCodiceChiamante ( SecurityUtils.getCurrentClient ().getCodice () );

            if ( request != null ) {
                inputSingleIUV.setIpChiamante ( request.getRemoteAddr () );
            } else {
                inputSingleIUV.setIpChiamante ( "UNKNOWN" );
            }
        }

		GetIuvMultibeneficiarioChiamanteEsternoOutputContainer result = service.getListIUVMultibeneficiarioChiamanteEsterno ( input );

        logger.debug ( "[getListIUVMultibeneficiarioChiamanteEsterno]:: stop" );
        return Response.status ( result != null && result.getCodiceEsito ().equals ( "000" ) ? 200
                        : result.getCodiceEsito ().equals ( "200" ) ? 403 : result.getCodiceEsito ().equals ( "300" ) ? 400 : 500 )
                        .entity ( result ).build ();
    }

    @Override
    public Response aggiornaPosizioneDebitoriaChiamanteEsterno ( AggiornaPosizioneDebitoriaChiamanteEsternoInput input ) {
        logger.debug ( "[aggiornaPosizioneDebitoriaChiamanteEsterno]:: start" );

        HttpServletRequest request = SecurityUtils.getCurrentRequest ();

        input.setTimestampChiamata ( new Timestamp ( new java.util.Date ().getTime () ) );
        input.setCodiceChiamante ( SecurityUtils.getCurrentClient ().getCodice () );

        if ( request != null ) {
            input.setIpChiamante ( request.getRemoteAddr () );
        } else {
            input.setIpChiamante ( "UNKNOWN" );
        }
        AggiornaPosizioneDebitoriaChiamanteEsternoOutput result = service.aggiornaPosizioneChiamanteEsterno ( input );

        return Response.status ( result != null && result.getCodice ().equals ( "000" ) ? 200
                        : result.getCodice ().equals ( "200" ) ? 403 : result.getCodice ().equals ( "300" ) ? 400 : 500 )
                        .entity ( result ).build ();
    }

    @Override
    public Response aggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsterno ( AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoInput input ) {
        logger.debug ( "[aggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiario]:: start" );

        HttpServletRequest request = SecurityUtils.getCurrentRequest ();

        input.setTimestampChiamata ( new Timestamp ( new java.util.Date ().getTime () ) );
        input.setCodiceChiamante ( SecurityUtils.getCurrentClient ().getCodice () );

        if ( request != null ) {
            input.setIpChiamante ( request.getRemoteAddr () );
        } else {
            input.setIpChiamante ( "UNKNOWN" );
        }
        AggiornaPosizioneDebitoriaChiamanteEsternoOutput result = service.aggiornaPosizioneChiamanteEsternoMultibeneficiario ( input );

        return Response.status ( result != null && result.getCodice ().equals ( "000" ) ? 200
                        : result.getCodice ().equals ( "200" ) ? 403 : result.getCodice ().equals ( "300" ) ? 400 : 500 )
                        .entity ( result ).build ();
    }

}
