/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.integration.epaybeapi.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.csi.epay.epaypaweb.dto.EsitoAvvisoPagamentoDto;
import it.csi.epay.epaypaweb.dto.RichiestaAvvisoPagamentoDto;
import it.csi.epay.epaypaweb.integration.epaybeapi.interf.AvvisoPagamento;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;


/**
 *
 */

public class AvvisoPagamentoImpl implements AvvisoPagamento {

    private static final String CLASSNAME = AvvisoPagamentoImpl.class.getSimpleName ();

    private static final Logger log = LogManager.getLogger ( APPLICATION_CODE + ".integration" );

    private final String url;

    private final String auth;

    public AvvisoPagamentoImpl ( String url, String auth ) {
        this.url = url;
        this.auth = auth;
    }

    @Override
    public EsitoAvvisoPagamentoDto getAvvisoPagamento ( RichiestaAvvisoPagamentoDto request ) {
        String methodName = "getAvvisoPagamento";
        
        

        EsitoAvvisoPagamentoDto stampaAvvisoResponse = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            HttpPost postRequest = new HttpPost ( url + "stampaAvvisoPagamento" );
            postRequest.addHeader ( HttpHeaders.CONTENT_TYPE, "application/json" );
            postRequest.addHeader ( HttpHeaders.ACCEPT, "application/json" );

            postRequest.addHeader ( HttpHeaders.AUTHORIZATION, "Basic " + auth );

            ObjectMapper mapper = new ObjectMapper ();

            String json = mapper.writeValueAsString ( request );
            postRequest.setEntity ( new StringEntity ( json, ContentType.APPLICATION_JSON ) );

            log.info ( "Faccio la chiamata per ottenere il pdf" );

            try ( CloseableHttpClient httpClient = HttpClients.createDefault ();
                CloseableHttpResponse response = httpClient.execute ( postRequest ) ) {

                log.info ( "Ottengo la risposta con stato: " + response.getStatusLine ().getStatusCode () );

                if ( response.getStatusLine ().getStatusCode () != HttpStatus.SC_OK ) {
                    stampaAvvisoResponse = new EsitoAvvisoPagamentoDto ( String.valueOf ( response.getStatusLine ().getStatusCode () ),
                        response.getStatusLine ().getReasonPhrase () );
                } else {
                    String responseString = EntityUtils.toString ( response.getEntity () );
                    stampaAvvisoResponse = mapper.readValue ( responseString, EsitoAvvisoPagamentoDto.class );
                    log.info ( "L'esito dell'elaborazione del pdf e': " + stampaAvvisoResponse.getDesEsito () );
                }
            } catch ( IOException e ) {
                log.error ( "Errore nella chiamata", e );
                stampaAvvisoResponse = new EsitoAvvisoPagamentoDto ( "400", "Problemi nella chiamata al servizio:" + e.getMessage () );
            }

        } catch ( JsonProcessingException e1 ) {
            log.error ( "Errore nella costruzione della richiesta", e1 );
            stampaAvvisoResponse = new EsitoAvvisoPagamentoDto ( "400", "Errore nella costruzione della richiesta:" + e1.getMessage () );
        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return stampaAvvisoResponse;
    }

}
