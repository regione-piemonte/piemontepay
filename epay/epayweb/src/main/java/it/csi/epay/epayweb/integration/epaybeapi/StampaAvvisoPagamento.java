/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayweb.integration.epaybeapi;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.epay.epayweb.utilities.LogUtil;


/**
 *
 */

@Component
@Scope ( BeanDefinition.SCOPE_SINGLETON )
public class StampaAvvisoPagamento {

    private LogUtil log = new LogUtil ( this.getClass () );

    private static final String codeError = "400";

    @Value ( "${epaybeapi.endPoint:''}" )
    private String endpoint;

    @Value ( "${epaybeapi.auth:''}" )
    private String auth;

    public StampaAvvisoPagamentoResponse getAvvisoPagamento ( StampaAvvisoPagamentoRequest request ) {
        String methodName = "getAvvisoPagamento";
        log.debugStart ( methodName );

        StampaAvvisoPagamentoResponse stampaAvvisoResponse = null;

        HttpPost postRequest = new HttpPost ( endpoint + "stampaAvvisoPagamentoSportello" );
        postRequest.addHeader ( HttpHeaders.CONTENT_TYPE, "application/json" );
        postRequest.addHeader ( HttpHeaders.ACCEPT, "application/json" );

        postRequest.addHeader ( HttpHeaders.AUTHORIZATION, "Basic " + auth );

        ObjectMapper mapper = new ObjectMapper ();

        try {
            String json = mapper.writeValueAsString ( request );
            postRequest.setEntity ( new StringEntity ( json, ContentType.APPLICATION_JSON ) );
        } catch ( JsonProcessingException e ) {
            log.error ( methodName, "Errore nella costruzione della richiesta", e );
            stampaAvvisoResponse = new StampaAvvisoPagamentoResponse ( codeError, "Errore nella costruzione della richiesta:" + e.getMessage () );
        }

        log.info ( methodName, "Faccio la chiamata per ottenere il pdf" );

        try ( CloseableHttpClient httpClient = HttpClients.createDefault ();
            CloseableHttpResponse response = httpClient.execute ( postRequest ) ) {
            log.info ( methodName, "Ottengo la risposta con stato: " + response.getStatusLine ().getStatusCode () );

            if ( response.getStatusLine ().getStatusCode () != HttpStatus.SC_OK ) {
                stampaAvvisoResponse = new StampaAvvisoPagamentoResponse ( String.valueOf ( response.getStatusLine ().getStatusCode () ),
                    response.getStatusLine ().getReasonPhrase () );
            } else {
                String responseString = EntityUtils.toString ( response.getEntity () );
                stampaAvvisoResponse = mapper.readValue ( responseString, StampaAvvisoPagamentoResponse.class );
                log.info ( methodName, "L'esito dell'elaborazio del pdf e': " + stampaAvvisoResponse.getDesEsito () );
            }
        } catch ( IOException e ) {
            log.error ( methodName, "Errore nella chiamata", e );
            stampaAvvisoResponse = new StampaAvvisoPagamentoResponse ( "400", "Problemi nella chiamata al servizio:" + e.getMessage () );
        }

        log.debugEnd ( methodName );
        return stampaAvvisoResponse;
    }

}
