/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.dispatcher;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandlerRegister.OperationHandlerWrapper;
import it.csi.epay.epaypacatalogsrv.dto.ParentInput;
import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.NotAuthorizedException;
import it.csi.epay.epaypacatalogsrv.exception.NotFoundException;


@Component
public class OperationExecutor {

    public static Logger logger = LogManager.getLogger ( OperationExecutor.class );

    @SuppressWarnings ( { "rawtypes", "unchecked" } )
    public <TI extends ParentInput, TO extends ParentOutput> TO execute (

        OperationHandlerWrapper exeHandler,
        Class<? extends ParentInput> TI,
        Class<? extends ParentOutput> TO,
        OperationDispatchingContext<TI, TO> context

    ) throws Exception {

        Object inputData = context.getInput ();
        OperationHandler handler = exeHandler.getHandler ();

        try {
            if ( logger.isTraceEnabled () ) {
                logger.trace ( "[OP:" + handler.getClass ().getSimpleName () + ":PRE-RUN] start" );
            }
            exeHandler.getHandler ().preRun ( inputData, context );
        } finally {
            if ( logger.isTraceEnabled () ) {
                logger.trace ( "[OP:" + handler.getClass ().getSimpleName () + ":PRE-RUN] end" );
            }
        }

        try {
            if ( logger.isTraceEnabled () ) {
                logger.trace ( "[OP:" + handler.getClass ().getSimpleName () + ":PRE-VALIDATE] start" );
            }
            exeHandler.getHandler ().preValidateInput ( inputData, context );
        } catch ( NotAuthorizedException | NotFoundException | BadRequestException e ) {
            exeHandler.getHandler ().onException ( inputData, context, e );
            throw e;
        } catch ( Exception e ) {
            logger.error ( "unexpected exception during prevalidation", e );
            exeHandler.getHandler ().onException ( inputData, context, e );
            throw new BadRequestException ();
        } finally {
            if ( logger.isTraceEnabled () ) {
                logger.trace ( "[OP:" + handler.getClass ().getSimpleName () + ":PRE-VALIDATE] end" );
            }
        }

        try {
            if ( logger.isTraceEnabled () ) {
                logger.trace ( "[OP:" + handler.getClass ().getSimpleName () + ":AUTHORIZE] start" );
            }
            exeHandler.getHandler ().authorize ( inputData, context );
        } catch ( NotAuthorizedException | NotFoundException | BadRequestException e ) {
            exeHandler.getHandler ().onException ( inputData, context, e );
            throw e;
        } catch ( Exception e ) {
            logger.error ( "unexpected exception during authorization", e );
            exeHandler.getHandler ().onException ( inputData, context, e );
            throw new NotAuthorizedException ();
        } finally {
            if ( logger.isTraceEnabled () ) {
                logger.trace ( "[OP:" + handler.getClass ().getSimpleName () + ":AUTHORIZE] end" );
            }
        }

        try {
            if ( logger.isTraceEnabled () ) {
                logger.trace ( "[OP:" + handler.getClass ().getSimpleName () + ":VALIDATE] start" );
            }
            exeHandler.getHandler ().validateInput ( inputData, context );
        } catch ( NotAuthorizedException | NotFoundException | BadRequestException e ) {
            exeHandler.getHandler ().onException ( inputData, context, e );
            throw e;
        } catch ( Exception e ) {
            logger.error ( "unexpected exception during input validation", e );
            exeHandler.getHandler ().onException ( inputData, context, e );
            throw new BadRequestException ();
        } finally {
            if ( logger.isTraceEnabled () ) {
                logger.trace ( "[OP:" + handler.getClass ().getSimpleName () + ":VALIDATE] end" );
            }
        }

        TO output;

        try {
            if ( logger.isTraceEnabled () ) {
                logger.trace ( "[OP:" + handler.getClass ().getSimpleName () + ":EXECUTE] start" );
            }
            output = (TO) exeHandler.getHandler ().execute ( inputData, context );
            context.setOutput ( output );
        } catch ( Exception e ) {
            logger.error ( "exception during operation execution", e );
            exeHandler.getHandler ().onException ( inputData, context, e );
            throw e;
        } finally {
            if ( logger.isTraceEnabled () ) {
                logger.trace ( "[OP:" + handler.getClass ().getSimpleName () + ":EXECUTE] end" );
            }
        }

        if ( logger.isTraceEnabled () ) {
            logger.trace ( "[OP:" + handler.getClass ().getSimpleName () + ":POST-RUN] start" );
        }
        try {
            exeHandler.getHandler ().postRun ( inputData, context );
        } finally {
            if ( logger.isTraceEnabled () ) {
                logger.trace ( "[OP:" + handler.getClass ().getSimpleName () + ":POST-RUN] end" );
            }
        }

        return output;
    }

}
