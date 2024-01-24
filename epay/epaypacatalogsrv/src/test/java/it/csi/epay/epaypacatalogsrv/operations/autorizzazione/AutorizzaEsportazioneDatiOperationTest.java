/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.autorizzazione;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.AutorizzaEsportazioneDatiInput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.AutorizzaEsportazioneDatiOutput;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;
import it.csi.epay.epaypacatalogsrv.vo.Constants;

@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class AutorizzaEsportazioneDatiOperationTest extends ParentOperationTest {

    protected AutorizzaEsportazioneDatiInput getInput () {
        AutorizzaEsportazioneDatiInput input = new AutorizzaEsportazioneDatiInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setEntity ( "TestEntity" );
        input.setIdList ( new ArrayList<> () );
        input.getIdList ().add ( "1" );
        return input;
    }

    protected AutorizzaEsportazioneDatiOutput call () {
        try {
            return getPort ().autorizzaEsportazioneDati ( getInput () );
        } catch ( Exception e ) {
            throw new RuntimeException ( e );
        }
    }

    protected AutorizzaEsportazioneDatiOutput call ( AutorizzaEsportazioneDatiInput input ) {
        try {
            return getPort ().autorizzaEsportazioneDati ( input );
        } catch ( Exception e ) {
            throw new RuntimeException ( e );
        }
    }

    @Test
    public void shouldReturnNotAllowedWithoutCaller () {

        AutorizzaEsportazioneDatiInput input = getInput ();
        input.setCaller ( null );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        AutorizzaEsportazioneDatiInput input = getInput ();
        input.getCaller ().setPrincipal ( null );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        AutorizzaEsportazioneDatiInput input = getInput ();
        input.getCaller ().getPrincipal ().setCodiceFiscale ( null );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        AutorizzaEsportazioneDatiInput input = getInput ();
        input.getCaller ().getPrincipal ().setCodiceEnte ( null );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        AutorizzaEsportazioneDatiInput input = getInput ();
        input.getCaller ().getPrincipal ().setCodiceFiscale ( "INVALID" );
        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        AutorizzaEsportazioneDatiInput input = getInput ();
        input.getCaller ().getPrincipal ().setCodiceEnte ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }


    @Test
    public void shouldReturnBadRequestOnNullEntity () {

        AutorizzaEsportazioneDatiInput input = getInput ();
        input.setEntity ( null );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST );

    }

    @Test
    public void shouldReturnBadRequestOnBlankEntity () {

        AutorizzaEsportazioneDatiInput input = getInput ();
        input.setEntity ( "  " );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST );

    }

    @Test
    public void shouldReturnBadRequestOnNullIdList () {

        AutorizzaEsportazioneDatiInput input = getInput ();
        input.setIdList ( null );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST );

    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        AutorizzaEsportazioneDatiInput input = getInput ();
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

}
