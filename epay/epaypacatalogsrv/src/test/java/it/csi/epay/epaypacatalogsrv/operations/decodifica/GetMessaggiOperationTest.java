/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.decodifica;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetMessaggiInput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetMessaggiOutput;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;
import it.csi.epay.epaypacatalogsrv.vo.Constants;

@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class GetMessaggiOperationTest extends ParentOperationTest {

    protected GetMessaggiInput getInput () {
        GetMessaggiInput input = new GetMessaggiInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );
        return input;
    }

    protected GetMessaggiOutput call () {
        try {
            return getPort ().getMessaggi ( getInput () );
        } catch ( Exception e ) {
            throw new RuntimeException ( e );
        }
    }

    protected GetMessaggiOutput call ( GetMessaggiInput input ) {
        try {
            return getPort ().getMessaggi ( input );
        } catch ( Exception e ) {
            throw new RuntimeException ( e );
        }
    }

    @Test
    public void shouldReturnNotAllowedWithoutCaller () {

        GetMessaggiInput input = getInput ();
        input.setCaller ( null );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        GetMessaggiInput input = getInput ();
        input.getCaller ().setPrincipal ( null );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        GetMessaggiInput input = getInput ();
        input.getCaller ().getPrincipal ().setCodiceFiscale ( null );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        GetMessaggiInput input = getInput ();
        input.getCaller ().getPrincipal ().setCodiceEnte ( null );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        GetMessaggiInput input = getInput ();
        input.getCaller ().getPrincipal ().setCodiceFiscale ( "INVALID" );
        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        GetMessaggiInput input = getInput ();
        input.getCaller ().getPrincipal ().setCodiceEnte ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        GetMessaggiInput input = getInput ();
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

}
