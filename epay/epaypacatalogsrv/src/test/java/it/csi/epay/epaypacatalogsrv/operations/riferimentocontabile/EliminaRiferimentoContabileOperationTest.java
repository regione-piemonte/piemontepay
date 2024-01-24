/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.riferimentocontabile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.EliminaRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.EliminaRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.config.TestConstants;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class EliminaRiferimentoContabileOperationTest extends RiferimentoContabileParentOperationTest {

    @Autowired
    private RiferimentoContabileRepository repo;

    private EliminaRiferimentoContabileOutput call ( EliminaRiferimentoContabileInput input ) {
        try {
            return getPort ().eliminaRiferimentoContabile ( input );
        } catch ( RuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new RuntimeException ( e );
        }
    }

    @Test
    public void shouldReturnBadRequestWithNullInput () {
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( null ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCaller () {

        EliminaRiferimentoContabileInput input = new EliminaRiferimentoContabileInput ();
        input.setCaller ( null );
        input.setId ( 1L );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        EliminaRiferimentoContabileInput input = new EliminaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_RC_ELIMINABILE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        EliminaRiferimentoContabileInput input = new EliminaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_RC_ELIMINABILE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        EliminaRiferimentoContabileInput input = new EliminaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_RC_ELIMINABILE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        EliminaRiferimentoContabileInput input = new EliminaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_RC_ELIMINABILE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        EliminaRiferimentoContabileInput input = new EliminaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_RC_ELIMINABILE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    //    @Test
    //    public void shouldReturnNotAllowedWithUserNotAdmin () {
    //
    //        EliminaRiferimentoContabileInput input = new EliminaRiferimentoContabileInput ();
    //        input.setCaller ( TestHelper.getCallerOperatore () );
    //        input.setId ( TestConstants.ID_RC_ELIMINABILE );
    //
    //        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnBadRequestWithInputZero () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        EliminaRiferimentoContabileInput input = new EliminaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( 0L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNull () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        EliminaRiferimentoContabileInput input = new EliminaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( null );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNegative () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        EliminaRiferimentoContabileInput input = new EliminaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( -3L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNotAlive () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        EliminaRiferimentoContabileInput input = new EliminaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_RC_NON_IN_VITA );

        RiferimentoContabile entity = repo.findOne ( input.getId () );
        assertTrue ( "test entity should not be alive", entity.getDataFineValidita () != null );

        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.RIFERIMENTO_CONTABILE_NON_IN_VITA );

        entity.setDataFineValidita ( null );
        repo.save ( entity );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotFoundWithInputMissing () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        EliminaRiferimentoContabileInput input = new EliminaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( 99999L );

        assertEquals ( Constants.RESULT_CODES.NOT_FOUND, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        EliminaRiferimentoContabileInput input = new EliminaRiferimentoContabileInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setId ( TestConstants.ID_RC_ELIMINABILE );

        EliminaRiferimentoContabileOutput output = call ( input );

        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );

    }

    @Test
    public void shouldSetFlagAnnullato () {
        EliminaRiferimentoContabileInput i = new EliminaRiferimentoContabileInput ();
        i.setCaller ( TestHelper.getCallerAdmin () );
        Long id = TestConstants.ID_RC_ELIMINABILE;
        i.setId ( id );
        EliminaRiferimentoContabileOutput output = call ( i );

        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );
        assertEquals ( true, repo.findOne ( id ).getFlagAnnullato () );
    }

    @Test
    public void shouldDeleteFullTreeIfAlive () {

        EliminaRiferimentoContabileInput input = new EliminaRiferimentoContabileInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        int numNonValidi = 0;
        int numDeleted;
        do {
            numDeleted = 0;
            for ( RiferimentoContabile dto: repo.findAll () ) {
                if ( dto.getDataFineValidita () != null ) {
                    input.setId ( dto.getId () );
                    EliminaRiferimentoContabileOutput output = call ( input );
                    assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, output.getCodiceEsito () );

                    numNonValidi++;
                    continue;
                }

                input.setId ( dto.getId () );
                EliminaRiferimentoContabileOutput output = call ( input );
                assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );
            }

        } while ( numDeleted > 0 );

        assertTrue ( "should have tested at least two cycles", numNonValidi > 0 );
    }

}
