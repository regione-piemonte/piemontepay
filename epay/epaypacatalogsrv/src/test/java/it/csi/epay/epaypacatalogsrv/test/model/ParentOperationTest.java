/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import it.csi.epay.epaypacatalogsrv.business.EpaypacatalogsrvImpl;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatcher;
import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;
import it.csi.epay.epaypacatalogsrv.model.Configurazione;
import it.csi.epay.epaypacatalogsrv.repository.ConfigurazioneRepository;


public abstract class ParentOperationTest extends ParentUnitTest {

    @Autowired
    private OperationDispatcher dispatcher;

    @Autowired
    private EpaypacatalogsrvImpl port;

    @Autowired
    private ConfigurazioneRepository configurazioneRepository;

    public OperationDispatcher getDispatcher () {
        return dispatcher;
    }

    private static java.util.ResourceBundle res;

    static {
        res = java.util.ResourceBundle.getBundle ( "config" );
    }

    public static java.util.ResourceBundle getRes () {
        return res;
    }

    @Before
    public void beforeParent () {
        // prepareDatabaseWithTestData();
        // verifyDatabaseTestData();
        mockServletEnvironment ();
        MockitoAnnotations.initMocks ( this );

        assertEquals ( "L'ambiente deve essere pre-configurato e dichiarato come JUNIT", "JUNIT", res.getString ( "nome.ambiente" ) );
        assertEquals ( "L'invio delle mail deve essere disabilitato", "false", res.getString ( "mail.active" ) );
    }

    protected void setConfigurazione ( String chiave, String valore ) {
        Configurazione token = configurazioneRepository.findOneByCodiceAndIdEnteIsNull ( chiave );

        if ( token == null ) {
            token = new Configurazione ();
            token.setCodice ( chiave );
            token.setId ( ( new Random () ).nextInt ( 1000 ) + 100 );
            token.setDescrizione ( "parametro " + chiave );
            token.setIdEnte ( null );
        }

        token.setValore ( valore );
        configurazioneRepository.save ( token );
    }

    protected void assertResult ( ParentOutput output, String codice ) {
        assertResult ( output, codice, null );
    }

    protected void assertResult ( ParentOutput output, String codice, String messaggio ) {

        if ( codice != null ) {
            assertEquals ( "codiceEsito should be " + codice + " but is " + output.getCodiceEsito (), codice, output.getCodiceEsito () );
        }

        if ( messaggio != null ) {
            assertTrue ( "codiceMessaggio should end with ." + messaggio + " but is " + output.getCodiceMessaggio (),
                output.getCodiceMessaggio ().endsWith ( "." + messaggio ) );
        }

    }

    protected void mockServletEnvironment () {
        MockHttpServletRequest request = new MockHttpServletRequest ();
        RequestContextHolder.setRequestAttributes ( new ServletRequestAttributes ( request ) );

        //        EpaypacatalogsrvImpl port = new EpaypacatalogsrvImpl ();
        //        port.setDispatcher ( dispatcher );
        //        this.port = port;
    }

    //    protected void executeOnKnownData ( boolean condition, Runnable test ) {
    //        if ( condition ) {
    //            prepareDatabaseWithTestData ();
    //            verifyDatabaseTestData ();
    //        }
    //        test.run ();
    //    }
    //
    //    protected void executeOnKnownData ( Runnable test ) {
    //        executeOnKnownData ( true, test );
    //    }
    //
    //    protected void executeOnKnownData ( Callable<Boolean> conditionEvaluator, Runnable test ) {
    //        Boolean result;
    //        try {
    //            result = conditionEvaluator.call ();
    //        } catch ( Exception e ) {
    //            throw new RuntimeException ( "invalid condition evaluator result", e );
    //        }
    //        if ( result == null ) {
    //            throw new RuntimeException ( "invalid condition evaluator result" );
    //        }
    //        executeOnKnownData ( result.booleanValue (), test );
    //    }

    public EpaypacatalogsrvImpl getPort () {
        return port;
    }

}
