/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.operations.test.TestResourcesOperation;
import it.csi.epay.epaypacatalogsrv.dto.test.TestResourcesInput;
import it.csi.epay.epaypacatalogsrv.dto.test.TestResourcesOutput;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;
import it.csi.epay.epaypacatalogsrv.vo.Constants;

@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class TestResourcesOperationTest extends ParentOperationTest {

    @Mock
    private EnteRepository enteRepositoryMock;

    protected TestResourcesInput getInput () {
        TestResourcesInput input = new TestResourcesInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );
        return input;
    }

    protected TestResourcesOutput call () {
        try {
            return getPort ().testResources ( getInput () );
        } catch ( Exception e ) {
            throw new RuntimeException ( e );
        }
    }

    protected TestResourcesOutput call ( TestResourcesInput input ) {
        try {
            return getPort ().testResources ( input );
        } catch ( Exception e ) {
            throw new RuntimeException ( e );
        }
    }

    @Test
    public void shouldReturnOkWithoutCaller () {

        TestResourcesInput input = getInput ();
        input.setCaller ( null );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        TestResourcesInput input = getInput ();
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

    @DirtiesContext
    @Test
    public void shouldReturnInternalServerErrorWithRepoFault () {

        ReflectionTestUtils.setField ( getBean ( TestResourcesOperation.class ), "enteRepository", enteRepositoryMock );
        doThrow ( new RuntimeException () ).when ( enteRepositoryMock ).count ();

        TestResourcesInput input = getInput ();
        assertEquals ( Constants.RESULT_CODES.INTERNAL_ERROR, call ( input ).getCodiceEsito () );
    }

}
