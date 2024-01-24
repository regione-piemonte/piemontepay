/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.dispatcher;

public interface OperationHandler<InputType, OutputType> {

    default void preRun ( InputType input, OperationDispatchingContext<InputType, OutputType> context ) {
        // NOP
        if ( OperationExecutor.logger.isTraceEnabled () ) {
            OperationExecutor.logger.trace ( "[OP:" + this.getClass ().getSimpleName () + ":PRE-RUN] no-op" );
        }
    }

    default void preValidateInput ( InputType input, OperationDispatchingContext<InputType, OutputType> context ) {
        // NOP
        if ( OperationExecutor.logger.isTraceEnabled () ) {
            OperationExecutor.logger.trace ( "[OP:" + this.getClass ().getSimpleName () + ":PRE-VALIDATE] no-op" );
        }
    }

    default void authorize ( InputType input, OperationDispatchingContext<InputType, OutputType> context ) {
        // NOP
        if ( OperationExecutor.logger.isTraceEnabled () ) {
            OperationExecutor.logger.trace ( "[OP:" + this.getClass ().getSimpleName () + ":AUTHORIZE] no-op" );
        }
    }

    default void validateInput ( InputType input, OperationDispatchingContext<InputType, OutputType> context ) {
        // NOP
        if ( OperationExecutor.logger.isTraceEnabled () ) {
            OperationExecutor.logger.trace ( "[OP:" + this.getClass ().getSimpleName () + ":VALIDATE-INPUT] no-op" );
        }
    }

    OutputType execute ( InputType input, OperationDispatchingContext<InputType, OutputType> context );

    default void postRun ( InputType input, OperationDispatchingContext<InputType, OutputType> context ) {
        // NOP
        if ( OperationExecutor.logger.isTraceEnabled () ) {
            OperationExecutor.logger.trace ( "[OP:" + this.getClass ().getSimpleName () + ":POST-RUN] no-op" );
        }
    }

    default void onException ( InputType input, OperationDispatchingContext<InputType, OutputType> context, Exception e ) {
        // NOP
        if ( OperationExecutor.logger.isTraceEnabled () ) {
            OperationExecutor.logger.trace ( "[OP:" + this.getClass ().getSimpleName () + ":ON-EXCEPTION] no-op" );
        }
    }
}
