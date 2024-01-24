/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.dispatcher;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;
import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;
import it.csi.epay.epaypacatalogsrv.exception.DuplicatedOperationHandlerException;
import it.csi.epay.epaypacatalogsrv.exception.MissingOperationHandlerException;


@Component
public class OperationHandlerRegister implements ApplicationContextAware {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    private static ApplicationContext context;

    @SuppressWarnings ( "rawtypes" )
    private Map<String, OperationHandlerWrapper> operationHandlerCache = new HashMap<> ();

    public static ApplicationContext getApplicationContext () {
        return OperationHandlerRegister.context;
    }

    @Override
    public void setApplicationContext ( ApplicationContext context ) throws BeansException {
        OperationHandlerRegister.context = context;
    }

    @SuppressWarnings ( { "rawtypes", "unchecked" } )
    public OperationHandlerWrapper findOperationHandler ( Class<? extends ParentInput> TI, Class<? extends ParentOutput> TO ) {
        String key = "OH" + ( TI.getName () + ";O=" + TO.getName () ).hashCode ();
        if ( !operationHandlerCache.containsKey ( key ) ) {

            logger.info ( "resolving operation handler for types: \nINPUT = " + TI.getName () + "\nOUTPUT = " + TO.getName () );

            Map<String, Object> beans = context.getBeansWithAnnotation ( Operation.class );

            OperationHandlerWrapper handler = null;

            for ( Object bean: beans.values () ) {
                Operation annotation = AopProxyUtils.ultimateTargetClass ( bean ).getAnnotation ( Operation.class );
                if ( annotation.consumes () == TI && annotation.produces () == TO ) {
                    if ( handler == null ) {
                        handler = new OperationHandlerWrapper ( (OperationHandler) bean, annotation );
                    } else {
                        String errorMessage = "OperationHandler non univoco per i tipi INPUT = " + TI.getName () + ", OUTPUT = " + TO.getName ();
                        logger.error ( errorMessage );
                        throw new DuplicatedOperationHandlerException ( errorMessage );
                    }
                }
            }

            if ( handler == null ) {
                // prova a cercare per nome
                if ( TI.getSimpleName ().endsWith ( "Input" ) && TO.getSimpleName ().endsWith ( "Output" ) ) {
                    String radiceInput = TI.getSimpleName ().substring ( 0, TI.getSimpleName ().length () - 5 );
                    String radiceOutput = TO.getSimpleName ().substring ( 0, TO.getSimpleName ().length () - 6 );
                    if ( radiceInput.equals ( radiceOutput ) ) {

                        String nomeOperationHandler = radiceInput.substring ( 0, 1 ).toLowerCase () + radiceInput.substring ( 1 ) + "Operation";
                        logger.debug ( "risolto nome operation temptative da nome classi: " + nomeOperationHandler );
                        try {
                            Object bean = context.getBean ( nomeOperationHandler );
                            logger.debug ( "lookup del bean riuscito: " + bean.getClass ().getName () );
                            handler = new OperationHandlerWrapper ( (OperationHandler) bean, null );
                        } catch ( Exception e ) {
                            logger.debug ( "lookup del bean fallito", e );
                        }

                    } else {
                        logger.warn ( "Attenzione: i nomi delle classi INPUT / OUTPUT non seguono la naming convention corretta: " + TI.getName () + ", "
                            + TO.getName () );
                    }
                }
            }

            if ( handler == null ) {
                String errorMessage = "nessun OperationHandler registrato per i tipi INPUT = " + TI.getName () + ", OUTPUT = " + TO.getName ();
                logger.error ( errorMessage );
                throw new MissingOperationHandlerException ( errorMessage );
            }

            logger.info ( "resolved operation handler " + handler.getClass ().getName () );

            operationHandlerCache.put ( key, handler );
            return handler;
        } else {
            return operationHandlerCache.get ( key );
        }
    }

    public class OperationHandlerWrapper<TI, TO> {

        private OperationHandler<TI, TO> handler;

        private Operation annotation;

        public OperationHandlerWrapper ( OperationHandler<TI, TO> handler, Operation annotation ) {
            super ();
            this.handler = handler;
            this.annotation = annotation;
        }

        public OperationHandler<TI, TO> getHandler () {
            return handler;
        }

        public Operation getAnnotation () {
            return annotation;
        }
    }
}
