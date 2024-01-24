/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business;

import org.apache.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatcher;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationExecutor;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.dto.ParentInput;
import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetMessaggiOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniAssociazioneUtenteCduOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniInput;
import it.csi.epay.epaypacatalogsrv.exception.DuplicatedOperationHandlerException;
import it.csi.epay.epaypacatalogsrv.exception.MissingOperationHandlerException;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class OperationDispatcherTest extends ParentOperationTest {

    static class DummyDtoInput extends ParentInput {

        private static final long serialVersionUID = 1L;

        public DummyDtoInput () {
            // NOP
        }
    }

    static class DummyDtoOutput extends ParentOutput {

        private static final long serialVersionUID = 1L;

        public DummyDtoOutput () {
            // NOP
        }
    }

    @DirtiesContext
    @Test
    public void shouldEnrichEmptyOutputWithOKCodeAndMessage () {

        @Operation ( consumes = GetOpzioniInput.class, produces = DummyDtoOutput.class )
        class DummyOperation extends ParentOutput implements OperationHandler<GetOpzioniInput, DummyDtoOutput> {

            private static final long serialVersionUID = 1L;

            @Override
            public DummyDtoOutput execute ( GetOpzioniInput input, OperationDispatchingContext<GetOpzioniInput, DummyDtoOutput> context ) {
                return new DummyDtoOutput ();
            }
        }

        DummyOperation bean = new DummyOperation ();
        ConfigurableListableBeanFactory beanFactory = ( (ConfigurableApplicationContext) getApplicationContext () ).getBeanFactory ();
        beanFactory.registerSingleton ( "dummyOperation", bean );

        GetOpzioniInput input = new GetOpzioniInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        DummyDtoOutput output = getDispatcher ().dispatch ( input, DummyDtoOutput.class );

        assertResult ( output, Constants.RESULT_CODES.OK );
    }

    @DirtiesContext
    @Test
    public void shouldWrapUnrecoverableErrors () {

        @Operation ( consumes = DummyDtoInput.class, produces = GetMessaggiOutput.class )
        class DummyOperation extends ParentOutput implements OperationHandler<DummyDtoInput, GetMessaggiOutput> {

            private static final long serialVersionUID = 1L;

            @Override
            public GetMessaggiOutput execute ( DummyDtoInput input, OperationDispatchingContext<DummyDtoInput, GetMessaggiOutput> context ) {
                throw new Error ( "TEST ERROR" );
            }
        }

        DummyOperation bean = new DummyOperation ();
        ConfigurableListableBeanFactory beanFactory = ( (ConfigurableApplicationContext) getApplicationContext () ).getBeanFactory ();
        beanFactory.registerSingleton ( "dummyOperation", bean );

        DummyDtoInput input = new DummyDtoInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        GetMessaggiOutput output = getDispatcher ().dispatch ( input, GetMessaggiOutput.class );

        assertResult ( output, Constants.RESULT_CODES.INTERNAL_ERROR );
    }

    @DirtiesContext
    @Test
    public void shouldWrapRuntimeExceptionsDuringPreValidationWithBadRequest () {

        @Operation ( consumes = DummyDtoInput.class, produces = GetMessaggiOutput.class )
        class DummyOperation extends ParentOutput implements OperationHandler<DummyDtoInput, GetMessaggiOutput> {

            private static final long serialVersionUID = 1L;

            @Override
            public void preValidateInput ( DummyDtoInput input, OperationDispatchingContext<DummyDtoInput, GetMessaggiOutput> context ) {
                throw new RuntimeException ( "AAA LOLLOOOOO" );
            }

            @Override
            public GetMessaggiOutput execute ( DummyDtoInput input, OperationDispatchingContext<DummyDtoInput, GetMessaggiOutput> context ) {
                return null;
            }

        }

        DummyOperation bean = new DummyOperation ();
        ConfigurableListableBeanFactory beanFactory = ( (ConfigurableApplicationContext) getApplicationContext () ).getBeanFactory ();
        beanFactory.registerSingleton ( "dummyOperation", bean );

        DummyDtoInput input = new DummyDtoInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        GetMessaggiOutput output = getDispatcher ().dispatch ( input, GetMessaggiOutput.class );

        assertResult ( output, Constants.RESULT_CODES.BAD_REQUEST );
    }

    @DirtiesContext
    @Test
    public void shouldWrapRuntimeExceptionsDuringAuthorizationWithNotAllowed () {

        @Operation ( consumes = DummyDtoInput.class, produces = GetMessaggiOutput.class )
        class DummyOperation extends ParentOutput implements OperationHandler<DummyDtoInput, GetMessaggiOutput> {

            private static final long serialVersionUID = 1L;

            @Override
            public void authorize ( DummyDtoInput input, OperationDispatchingContext<DummyDtoInput, GetMessaggiOutput> context ) {
                throw new RuntimeException ( "AAA LOLLOOOOO" );
            }

            @Override
            public GetMessaggiOutput execute ( DummyDtoInput input, OperationDispatchingContext<DummyDtoInput, GetMessaggiOutput> context ) {
                return null;
            }
        }

        DummyOperation bean = new DummyOperation ();
        ConfigurableListableBeanFactory beanFactory = ( (ConfigurableApplicationContext) getApplicationContext () ).getBeanFactory ();
        beanFactory.registerSingleton ( "dummyOperation", bean );

        DummyDtoInput input = new DummyDtoInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        GetMessaggiOutput output = getDispatcher ().dispatch ( input, GetMessaggiOutput.class );

        assertResult ( output, Constants.RESULT_CODES.NOT_ALLOWED );
    }

    @DirtiesContext
    @Test
    public void shouldWrapRuntimeExceptionsDuringValidationWithBadRequest () {

        @Operation ( consumes = DummyDtoInput.class, produces = GetMessaggiOutput.class )
        class DummyOperation extends ParentOutput implements OperationHandler<DummyDtoInput, GetMessaggiOutput> {

            private static final long serialVersionUID = 1L;

            @Override
            public void validateInput ( DummyDtoInput input, OperationDispatchingContext<DummyDtoInput, GetMessaggiOutput> context ) {
                throw new RuntimeException ( "AAA LOLLOOOOO" );
            }

            @Override
            public GetMessaggiOutput execute ( DummyDtoInput input, OperationDispatchingContext<DummyDtoInput, GetMessaggiOutput> context ) {
                return null;
            }
        }

        DummyOperation bean = new DummyOperation ();
        ConfigurableListableBeanFactory beanFactory = ( (ConfigurableApplicationContext) getApplicationContext () ).getBeanFactory ();
        beanFactory.registerSingleton ( "dummyOperation", bean );

        DummyDtoInput input = new DummyDtoInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        GetMessaggiOutput output = getDispatcher ().dispatch ( input, GetMessaggiOutput.class );

        assertResult ( output, Constants.RESULT_CODES.BAD_REQUEST );
    }

    @Test ( expected = MissingOperationHandlerException.class )
    public void shouldThrowExceptionOnMissingOperationHandler () {

        OperationDispatcher dispatcher = getDispatcher ();

        class DummyDto2Output extends ParentOutput {

            private static final long serialVersionUID = 1L;
        }

        GetOpzioniInput input = new GetOpzioniInput ();

        dispatcher.dispatch ( input, DummyDto2Output.class );
    }

    @Test ( expected = MissingOperationHandlerException.class )
    public void shouldThrowExceptionOnMissingOperationHandlerByClassNaming () {

        OperationDispatcher dispatcher = getDispatcher ();

        class DummyOperationInput extends ParentInput {

            private static final long serialVersionUID = 1L;
        }

        class DummyOperationOutput extends ParentOutput {

            private static final long serialVersionUID = 1L;
        }

        dispatcher.dispatch ( new DummyOperationInput (), DummyOperationOutput.class );
    }

    @DirtiesContext
    @Test ( expected = DuplicatedOperationHandlerException.class )
    public void shouldThrowExceptionOnDuplicatedOperationHandler () {

        class DummyDtoOutput2 extends ParentOutput {

            private static final long serialVersionUID = 1L;
        }

        @Operation ( consumes = GetOpzioniInput.class, produces = DummyDtoOutput2.class )
        class DummyOperation extends ParentOutput implements OperationHandler<GetOpzioniInput, DummyDtoOutput2> {

            private static final long serialVersionUID = 1L;

            @Override
            public DummyDtoOutput2 execute ( GetOpzioniInput input, OperationDispatchingContext<GetOpzioniInput, DummyDtoOutput2> context ) {
                throw new RuntimeException ( "SHOULD NEVER GET HERE" );
            }
        }

        DummyOperation bean = new DummyOperation ();
        ConfigurableListableBeanFactory beanFactory = ( (ConfigurableApplicationContext) getApplicationContext () ).getBeanFactory ();
        beanFactory.registerSingleton ( "dummyOperation1", bean );
        beanFactory.registerSingleton ( "dummyOperation2", bean );

        GetOpzioniInput input = new GetOpzioniInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );
        getDispatcher ().dispatch ( input, DummyDtoOutput2.class );
    }

    @Test
    public void shouldExposeContext () {
        OperationDispatchingContext<GetOpzioniInput, GetMessaggiOutput> context = new OperationDispatchingContext<> ( null );
        context.getInput ();
        context.getPrincipal ();
        context.getOutput ();
    }
}
