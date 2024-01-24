/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.email;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.dto.email.ElaboraCodaEmailInput;
import it.csi.epay.epaypacatalogsrv.dto.email.ElaboraCodaEmailOutput;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;
import it.csi.epay.epaypacatalogsrv.vo.Constants;

@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class ElaboraCodaEmailOperationTest extends ParentOperationTest {

    @Mock
    private EnteRepository enteRepositoryMock;

    protected ElaboraCodaEmailInput getInput () {
        ElaboraCodaEmailInput input = new ElaboraCodaEmailInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );
        return input;
    }

    protected ElaboraCodaEmailOutput call () {
        try {
            return getPort ().elaboraCodaEmail ( getInput () );
        } catch ( Exception e ) {
            throw new RuntimeException ( e );
        }
    }

    protected ElaboraCodaEmailOutput call ( ElaboraCodaEmailInput input ) {
        try {
            return getPort ().elaboraCodaEmail ( input );
        } catch ( Exception e ) {
            throw new RuntimeException ( e );
        }
    }

    @Test
    public void shouldReturnOkWithoutCaller () {

        ElaboraCodaEmailInput input = getInput ();
        input.setCaller ( null );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        ElaboraCodaEmailInput input = getInput ();
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

}
