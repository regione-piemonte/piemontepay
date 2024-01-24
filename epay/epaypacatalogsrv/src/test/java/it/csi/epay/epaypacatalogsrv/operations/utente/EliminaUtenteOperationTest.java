/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.utente;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.utente.EliminaUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.utente.EliminaUtenteOutput;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.config.TestConstants;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class EliminaUtenteOperationTest extends ParentOperationTest {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private EnteRepository enteRepository;

    private EliminaUtenteOutput call ( EliminaUtenteInput input ) {
        try {
            return getPort ().eliminaUtente ( input );
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

        EliminaUtenteInput input = new EliminaUtenteInput ();
        input.setCaller ( null );
        input.setId ( 1L );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        EliminaUtenteInput input = new EliminaUtenteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_UTENTE_ELIMINABILE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        EliminaUtenteInput input = new EliminaUtenteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_UTENTE_ELIMINABILE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        EliminaUtenteInput input = new EliminaUtenteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_UTENTE_ELIMINABILE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        EliminaUtenteInput input = new EliminaUtenteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_UTENTE_ELIMINABILE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        EliminaUtenteInput input = new EliminaUtenteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_UTENTE_ELIMINABILE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    //    @Test
    //    public void shouldReturnNotAllowedWithUserNotAdmin () {
    //
    //        EliminaUtenteInput input = new EliminaUtenteInput ();
    //        input.setCaller ( TestHelper.getCallerOperatore () );
    //        input.setId ( TestConstants.ID_UTENTE_ELIMINABILE );
    //
    //        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnBadRequestWithInputZero () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        EliminaUtenteInput input = new EliminaUtenteInput ();
        input.setCaller ( caller );
        input.setId ( 0L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNull () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        EliminaUtenteInput input = new EliminaUtenteInput ();
        input.setCaller ( caller );
        input.setId ( null );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNegative () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        EliminaUtenteInput input = new EliminaUtenteInput ();
        input.setCaller ( caller );
        input.setId ( -3L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotFoundWithInputMissing () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        EliminaUtenteInput input = new EliminaUtenteInput ();
        input.setCaller ( caller );
        input.setId ( 99999L );

        assertEquals ( Constants.RESULT_CODES.NOT_FOUND, call ( input ).getCodiceEsito () );
    }

    //    @Test
    //    public void shouldReturnBadRequestWhenAssociatedWithOtherEnti () {
    //        CallerInputDto caller = TestHelper.getCallerAdmin ();
    //
    //        EliminaUtenteInput input = new EliminaUtenteInput ();
    //        input.setCaller ( caller );
    //        input.setId ( TestConstants.ID_UTENTE_ELIMINABILE );
    //
    //        Utente dtoUtente = utenteRepository.findOne ( input.getId () );
    //        dtoUtente.getEnti ().add ( enteRepository.findOne ( 2L ) );
    //        utenteRepository.save ( dtoUtente );
    //        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.UTENTE_NON_ELIMINABILE );
    //
    //        dtoUtente.getEnti ().clear ();
    //        utenteRepository.save ( dtoUtente );
    //        assertResult ( call ( input ), Constants.RESULT_CODES.OK );
    //    }

    @Test
    public void shouldReturnBadRequestWhenAdminOfSomeEnte () {

        EliminaUtenteInput input = new EliminaUtenteInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setId ( TestConstants.ID_UTENTE_ELIMINABILE );

        Ente ente = enteRepository.findOne ( 2L );
        ente.setUtenteAmministratore ( utenteRepository.findOne ( input.getId () ) );
        enteRepository.save ( ente );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.UTENTE_NON_ELIMINABILE );
    }

    @Test
    public void shouldReturnBadRequestWhenIsUtenteCorrente () {

        EliminaUtenteInput input = new EliminaUtenteInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setId ( utenteRepository.findOneByCodiceFiscale ( input.getCaller ().getPrincipal ().getCodiceFiscale () ).getId () );

        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.MODIFICA_UTENTE_CORRENTE_NON_CONSENTITA );
    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        EliminaUtenteInput input = new EliminaUtenteInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setId ( TestConstants.ID_UTENTE_ELIMINABILE );

        EliminaUtenteOutput output = call ( input );

        assertResult ( output, Constants.RESULT_CODES.OK );

    }

    @Test
    public void shouldDeleteFromDB () {
        EliminaUtenteInput i = new EliminaUtenteInput ();
        i.setCaller ( TestHelper.getCallerAdmin () );
        Long id = TestConstants.ID_UTENTE_ELIMINABILE;
        i.setId ( id );
        EliminaUtenteOutput output = call ( i );

        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );
        assertEquals ( null, utenteRepository.findOne ( id ) );
    }

}
