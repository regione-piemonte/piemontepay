/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.dispatcher;

import java.util.concurrent.Callable;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandlerRegister.OperationHandlerWrapper;
import it.csi.epay.epaypacatalogsrv.business.service.DecodificaService;
import it.csi.epay.epaypacatalogsrv.business.service.ProfilazioneService;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.ParentInput;
import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.CodedException;
import it.csi.epay.epaypacatalogsrv.exception.ManagedException;
import it.csi.epay.epaypacatalogsrv.exception.NotAuthorizedException;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalVO;


@Component
public class OperationDispatcher implements ApplicationListener<ContextRefreshedEvent> {

    //    public static class AuditingInvocationHandler implements InvocationHandler {
    //
    //        private OperationDispatcher dispatcher;
    //
    //        public AuditingInvocationHandler ( OperationDispatcher dispatcher ) {
    //            this.dispatcher = dispatcher;
    //        }
    //
    //        @Override
    //        public Object invoke ( Object proxy, Method method, Object [] args ) throws Throwable {
    //
    //            ParentInput input = (ParentInput) args [0];
    //            Class<?> rt = method.getReturnType ();
    //            Class<? extends ParentOutput> rto = (Class<? extends ParentOutput>) rt;
    //
    //            return dispatcher.dispatch ( input, rto );
    //        }
    //    }

    @Override
    public void onApplicationEvent ( ContextRefreshedEvent event ) {

        //        InvocationHandler handler = new AuditingInvocationHandler ( this );
        //
        //        Epaypacatalogsrv proxy = (Epaypacatalogsrv) Proxy.newProxyInstance (
        //            this.getClass ().getClassLoader (),
        //            new Class [] { Epaypacatalogsrv.class },
        //            handler );

        // javax.xml.ws.Endpoint jaxwsEndpoint = javax.xml.ws.Endpoint.publish (
        //      "/epaypacatalogsrvApplEpaypacatalogsrvWs/Epaypacatalogsrv",
        //      proxy
        // );

        //        javax.xml.ws.Endpoint endpoint = javax.xml.ws.Endpoint.create ( proxy );

        //        id=it.csi.epay.epaypacatalogsrv.business.EpaypacatalogsrvImpl
        //        address=http://localhost:8080/epaypacatalogsrvApplEpaypacatalogsrvWs/EpaypacatalogsrvService
        //        implementor=it.csi.epay.epaypacatalogsrv.business.EpaypacatalogsrvImpl
        //        serviceName={http://business.epaypacatalogsrv.epay.csi.it/}EpaypacatalogsrvService
        //        portName={http://business.epaypacatalogsrv.epay.csi.it/}EpaypacatalogsrvPort
        //        annotationWsdlLocation=null wsdlLocationOverride=null mtomEnabled=false

        //        endpoint.getProperties ().put ( Endpoint.WSDL_SERVICE, new QName ( "http://business.epaypacatalogsrv.epay.csi.it/", "EpaypacatalogsrvServiceTwo" ) );
        //        endpoint.getProperties ().put ( Endpoint.WSDL_PORT, new QName ( "http://business.epaypacatalogsrv.epay.csi.it/", "EpaypacatalogsrvPortTwo" ) );
        //
        //        endpoint.publish ( "/epaypacatalogsrvApplEpaypacatalogsrvWs/EpaypacatalogsrvServiceTwo" );
        //
        //        endpoint.getProperties ();
    }

    public static final String LOGGER_PREFIX = Constants.APPLICATION_NAME;

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    private OperationExecutor operationExecutor;

    @Autowired
    private ProfilazioneService profilazioneService;

    @Autowired
    private DecodificaService decodificaService;

    @Autowired
    private OperationHandlerRegister operationHandlerRegister;

    public <TI extends ParentInput, TO extends ParentOutput> TO dispatch ( ParentInput inputData, Class<? extends ParentOutput> TO ) {

        if ( inputData == null ) {
            BadRequestException e = new BadRequestException ( Constants.MESSAGES.EMPTY_INPUT_NOT_ALLOWED );
            TO output = ParentOutput.fail ( e, TO );
            this.populateParentOutput ( output, e );
            return output;
        }

        return this.dispatch ( inputData, inputData.getClass (), TO );
    }

    @SuppressWarnings ( { "rawtypes", "unchecked" } )
    private <TI extends ParentInput, TO extends ParentOutput> TO dispatch (

        ParentInput inputData,
        Class<? extends ParentInput> TI,
        Class<? extends ParentOutput> TO

    ) {
        String methodName = "dispatch::" + TI.getSimpleName ();

        OperationDispatchingContext<TI, TO> context = new OperationDispatchingContext<> ( (TI) inputData );

        final OperationHandlerWrapper exeHandler = operationHandlerRegister.findOperationHandler ( TI, TO );
        Callable<TO> callable = new Callable<TO> () {

            @Override
            public TO call () throws Exception {
                return operationExecutor.execute ( exeHandler, TI, TO, context );
            }
        };

        it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch ( Constants.APPLICATION_NAME );
        watcher.start ();

        TO output = null;
        Exception e = null;

        if ( inputData.getCaller () != null && ( exeHandler.getAnnotation () == null || !exeHandler.getAnnotation ().skipAuthentication () ) ) {
            try {
                PrincipalVO principal = profilazioneService.getPrincipal ( inputData.getCaller () );
                SecurityUtils.setPrincipal ( principal );
                context.setPrincipal ( principal );
            } catch ( NotAuthorizedException ex ) {
                output = ParentOutput.fail ( ex, TO );
                e = ex;
            } catch ( Exception ex ) {
                NotAuthorizedException eout = new NotAuthorizedException ( Constants.MESSAGES.INVALID_CALLER );
                e = eout;
                output = ParentOutput.fail ( eout, TO );
            }
        }

        if ( e == null ) {
            try {
                output = callable.call ();
            } catch ( RuntimeException ex ) {
                logger.error (
                    "[" + this.getClass ().getSimpleName () + "::" + methodName + "] - Errore imprevisto occorso durante l'esecuzione del metodo:" + ex, ex );
                output = ParentOutput.fail ( ex, TO );
                e = ex;
            } catch ( Throwable ex ) {
                logger.error (
                    "[" + this.getClass ().getSimpleName () + "::" + methodName + "] - Errore imprevisto occorso durante l'esecuzione del metodo:" + ex,
                    ex );
                e = new ManagedException ();
                output = ParentOutput.fail ( e, TO );
            } finally {
                watcher.stop ();
                watcher.dumpElapsed ( Constants.APPLICATION_NAME + "OperationDispatcher", methodName + "()",
                    "invocazione servizio [" + this.getClass ().getSimpleName () + "::" + methodName + "]", "(valore input omesso)" );
                logger.debug ( "[" + this.getClass ().getSimpleName () + "::" + methodName + "] - END" );
            }
        }

        this.populateParentOutput ( output, e );
        return output;
    }

    private void populateParentOutput ( ParentOutput output, Exception e ) {
        String messageCodePrefix = Constants.DEFAULT_LANGUAGE + "." + Constants.APPLICATION_NAME + ".";
        if ( output.getCodiceMessaggio () == null || output.getCodiceMessaggio ().isEmpty () ) {
            output.setCodiceMessaggio ( output.getCodiceEsito () );
        }

        if ( e == null ) {
            if ( output.getCodiceEsito () == null || output.getCodiceEsito ().isEmpty () ) {
                output.setCodiceEsito ( Constants.RESULT_CODES.OK );
            }
            if ( output.getCodiceMessaggio () == null || output.getCodiceMessaggio ().isEmpty () ) {
                output.setCodiceMessaggio ( Constants.MESSAGES.OK );
            }
        }

        if ( StringUtils.isEmpty ( output.getDescrizioneEsito () ) ) {
            Object [] parameters = ( e instanceof CodedException ) ? ( (CodedException) e ).getParameters () : null;
            if ( parameters == null ) {
                parameters = new Object [] {};
            }

            output.setDescrizioneEsito (
                String.format ( decodificaService.getMessaggio ( output.getCodiceMessaggio () ), parameters ) );
        }

        if ( output.getCodiceMessaggio () != null && !output.getCodiceMessaggio ().startsWith ( messageCodePrefix ) ) {
            output.setCodiceMessaggio ( messageCodePrefix + output.getCodiceMessaggio () );
        }
    }

}
