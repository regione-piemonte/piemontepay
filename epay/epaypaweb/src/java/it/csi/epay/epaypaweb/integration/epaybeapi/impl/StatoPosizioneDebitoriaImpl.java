/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.integration.epaybeapi.impl;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionContext;

import it.csi.epay.epaypaweb.integration.epaybeapi.interf.StatoPosizioneDebitoria;
import it.csi.epay.epaypaweb.persistence.dad.impl.GestioneFlussiDadImpl;


/**
 *
 */

public class StatoPosizioneDebitoriaImpl implements StatoPosizioneDebitoria {

    private static final String CLASSNAME = StatoPosizioneDebitoriaImpl.class.getSimpleName ();

    private static final Logger log = LogManager.getLogger ( APPLICATION_CODE + ".integration" );

    private  String uri;

    private  String auth;


    @Override
    public String getStatoPosizioneDebitoria ( String organization, String paymentType, String iuv ) throws IOException {
        String methodName = "getStatoPosizioneDebitoria";
        String result;
        Properties config = new Properties ();
        
        try {
//            ServletContext servletContext = (ServletContext) ActionContext.getContext ().get ( StrutsStatics.SERVLET_CONTEXT );
//            InputStream configInputStream = servletContext.getResourceAsStream ( "/WEB-INF/application-config.properties" );
            
            InputStream configInputStream = StatoPosizioneDebitoriaImpl.class.getResourceAsStream("/WEB-INF/application-config.properties");
            
            config.load ( configInputStream );
        } catch ( IOException e ) {
            log.error ( "Errore nella configurazione del progetto" );
        }
        uri = config.getProperty ( "epaybeapi.url" );
        
        try {
            uri += "organizations/" + organization + "/paymenttypes/" + paymentType + "/debtpositions/" + URLEncoder.encode ( iuv,
                            String.valueOf ( StandardCharsets.UTF_8 ) ) + "/status";
        } catch ( UnsupportedEncodingException e ) {
            throw new RuntimeException ( e );
        }
        
        auth =  config.getProperty ( "epaybeapi.auth" );
        
        log.info ( "uri=" + uri );
        HttpURLConnection conn = null;
        URL url = new URL ( uri );
        try {
            conn = (HttpURLConnection) url.openConnection ();
//            conn.setRequestMethod ( "GET" );
//            conn.setRequestProperty ( "Accept", "application/json" );
//            byte[] encodedAuth = Base64.encodeBase64 ( auth.getBytes ( StandardCharsets.UTF_8 ) );
//            String authHeaderValue = "Basic " + new String ( encodedAuth );
//            conn.setRequestProperty ( "Authorization", authHeaderValue );
            
            
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Basic "+auth);
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestMethod("GET");
//            conn.setConnectTimeout(TIMEOUT);
            conn.connect();


            
            
            if ( conn.getResponseCode () != 200 ) {
                log.error ( "Errore nel reperire dati sullo stato della posizione debitoria [" + iuv + "], response code: " + conn.getResponseCode () );
            }
            ObjectMapper mapper = new ObjectMapper ();
            TestObject testObject = mapper.readValue ( conn.getInputStream (), TestObject.class );
            result = testObject.code;
        } catch ( IOException e ) {
            throw new IOException ();
        } finally {
            assert conn != null;
            conn.disconnect ();
        }
        return result;

//        return null;
    }
    
    private static class TestObject {

        public Result result;

        public String code;

        public String description;
    }


    private static class Result {

        public String code;

        public String description;
    }

}
