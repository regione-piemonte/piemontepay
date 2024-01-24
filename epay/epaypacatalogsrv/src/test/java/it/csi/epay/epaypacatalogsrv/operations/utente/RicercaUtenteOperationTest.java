/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.utente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.utente.RicercaUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.utente.RicercaUtenteOutput;
import it.csi.epay.epaypacatalogsrv.dto.utente.RicercaUtenteOutputDto;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.config.TestConstants;
import it.csi.epay.epaypacatalogsrv.test.helper.TestEntityHelper;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class RicercaUtenteOperationTest extends ParentOperationTest {

    @Autowired
    private UtenteRepository repo;

    private RicercaUtenteOutput call ( RicercaUtenteInput input ) {
        try {
            return getPort ().ricercaUtente ( input );
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

        RicercaUtenteInput input = new RicercaUtenteInput ();
        input.setCaller ( null );
        input.setNome ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        RicercaUtenteInput input = new RicercaUtenteInput ();
        input.setCaller ( caller );
        input.setNome ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        RicercaUtenteInput input = new RicercaUtenteInput ();
        input.setCaller ( caller );
        input.setNome ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        RicercaUtenteInput input = new RicercaUtenteInput ();
        input.setCaller ( caller );
        input.setNome ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        RicercaUtenteInput input = new RicercaUtenteInput ();
        input.setCaller ( caller );
        input.setNome ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        RicercaUtenteInput input = new RicercaUtenteInput ();
        input.setCaller ( caller );
        input.setNome ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }


    //    @Test
    //    public void shouldReturnNotAllowedWithUserNotAdmin () {
    //
    //        RicercaUtenteInput input = new RicercaUtenteInput ();
    //        input.setCaller ( TestHelper.getCallerOperatore () );
    //        input.setNome ( "INVALID" );
    //
    //        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnBadRequestWithNullInputFields () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        RicercaUtenteInput input = new RicercaUtenteInput ();
        input.setCaller ( caller );
        input.setNome ( null );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithEmptyInputFields () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        RicercaUtenteInput input = new RicercaUtenteInput ();
        input.setCaller ( caller );
        input.setNome ( "" );
        input.setCognome ( "" );

        assertTrue ( EntityUtils.isEmpty ( input ) );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithBlankInputFields () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        RicercaUtenteInput input = new RicercaUtenteInput ();
        input.setCaller ( caller );
        input.setNome ( "  " );
        input.setCognome ( "  " );

        assertTrue ( EntityUtils.isEmpty ( input ) );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithBadIdCodiceVersamento () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        RicercaUtenteInput input = new RicercaUtenteInput ();
        input.setCaller ( caller );

        input.setIdCodiceVersamento ( 0L );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );

        input.setIdCodiceVersamento ( -32L );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );
    }

    @Test
    public void shouldReturnOKWithInputCorrect () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        RicercaUtenteInput input = new RicercaUtenteInput ();
        input.setCaller ( caller );
        input.setNome ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNoResultsWithMissingInputFields () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        RicercaUtenteInput input = new RicercaUtenteInput ();
        input.setCaller ( caller );
        input.setCodiceFiscale ( "INVALID" );

        assertEquals ( new Integer ( 0 ), call ( input ).getNumeroRisultatiTotali () );
    }

    @Test
    public void shouldReturnAllResultsByDirectCodiceFiscale () {

        RicercaUtenteInput input = new RicercaUtenteInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        for ( Utente dto: repo.findAll () ) {

            assertNotNull ( dto.getCodiceFiscale () );
            input.setCodiceFiscale ( dto.getCodiceFiscale () );

            RicercaUtenteOutput output = call ( input );
            assertEquals ( new Integer ( 1 ), output.getNumeroRisultatiTotali () );

            RicercaUtenteOutputDto outputDto = output.getRisultati ().get ( 0 );
            assertEquals ( dto.getCodiceFiscale (), outputDto.getCodiceFiscale () );
        }

    }

    @Test
    public void shouldReturnAllResultsByDirectFields () {

        RicercaUtenteInput input = new RicercaUtenteInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        for ( Utente dto: repo.findAll () ) {

            assertNotNull ( dto.getCodiceFiscale () );
            input.setCodiceFiscale ( dto.getCodiceFiscale () );
            input.setNome ( dto.getNome () );
            input.setCognome ( dto.getCognome () );
            input.setEmail ( dto.getEmail () );

            RicercaUtenteOutput output = call ( input );
            assertEquals ( new Integer ( 1 ), output.getNumeroRisultatiTotali () );

            RicercaUtenteOutputDto outputDto = output.getRisultati ().get ( 0 );
            assertEquals ( dto.getCodiceFiscale (), outputDto.getCodiceFiscale () );
        }

    }

    @Test
    public void shouldReturnAllResultsByDirectFieldsMithMixedCase () {

        RicercaUtenteInput input = new RicercaUtenteInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        for ( Utente dto: repo.findAll () ) {

            input.setCodiceFiscale ( dto.getCodiceFiscale ().toLowerCase () );
            input.setNome ( dto.getNome ().toLowerCase () );
            input.setCognome ( dto.getCognome ().toLowerCase () );
            input.setEmail ( dto.getEmail ().toLowerCase () );

            RicercaUtenteOutput output = call ( input );
            assertEquals ( new Integer ( 1 ), output.getNumeroRisultatiTotali () );
            assertEquals ( dto.getCodiceFiscale (), output.getRisultati ().get ( 0 ).getCodiceFiscale () );

            input.setCodiceFiscale ( dto.getCodiceFiscale ().toUpperCase () );
            input.setNome ( dto.getNome ().toUpperCase () );
            input.setCognome ( dto.getCognome ().toUpperCase () );
            input.setEmail ( dto.getEmail ().toUpperCase () );

            output = call ( input );
            assertEquals ( new Integer ( 1 ), output.getNumeroRisultatiTotali () );
            assertEquals ( dto.getCodiceFiscale (), output.getRisultati ().get ( 0 ).getCodiceFiscale () );
        }

    }

    @Test
    public void shouldFilterByNome () {

        RicercaUtenteInput input = new RicercaUtenteInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        getJdbcTemplate ().update ( "update " + TestConstants.TABLE_PREFIX +
            "t_utente set nome='0'" );

        assertEquals ( 3, getJdbcTemplate ().update (
            "update " + TestConstants.TABLE_PREFIX + "t_utente set " +
                " nome='XX' where id <= 3" ) );

        input.setNome ( "xx" );

        RicercaUtenteOutput output = call ( input );
        assertEquals ( new Integer ( 3 ), output.getNumeroRisultatiTotali () );
        for ( RicercaUtenteOutputDto dto: output.getRisultati () ) {
            assertEquals ( "XX", dto.getNome () );
        }

    }

    @Test
    public void shouldFilterByCognome () {

        RicercaUtenteInput input = new RicercaUtenteInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        getJdbcTemplate ().update ( "update " + TestConstants.TABLE_PREFIX +
            "t_utente set cognome='0'" );

        assertEquals ( 3, getJdbcTemplate ().update (
            "update " + TestConstants.TABLE_PREFIX + "t_utente set " +
                " cognome='XX' where id <= 3" ) );

        input.setCognome ( "xx" );

        RicercaUtenteOutput output = call ( input );
        assertEquals ( new Integer ( 3 ), output.getNumeroRisultatiTotali () );
        for ( RicercaUtenteOutputDto dto: output.getRisultati () ) {
            assertEquals ( "XX", dto.getCognome () );
        }

    }

    @Test
    public void shouldFilterByEmail () {

        RicercaUtenteInput input = new RicercaUtenteInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        getJdbcTemplate ().update ( "update " + TestConstants.TABLE_PREFIX +
            "t_utente set email='0'" );

        assertEquals ( 3, getJdbcTemplate ().update (
            "update " + TestConstants.TABLE_PREFIX + "t_utente set " +
                " email='XX' where id <= 3" ) );

        input.setEmail ( "xx" );

        RicercaUtenteOutput output = call ( input );
        assertEquals ( new Integer ( 3 ), output.getNumeroRisultatiTotali () );
        for ( RicercaUtenteOutputDto dto: output.getRisultati () ) {
            assertEquals ( "XX", dto.getEmail () );
        }

    }

    @Test
    public void shouldFilterBySolUtentiInVita () {

        RicercaUtenteInput input = new RicercaUtenteInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setNome ( "DEMO" );

        input.setSoloUtentiInVita ( true );

        for ( Utente dto: repo.findAll () ) {
            if ( dto.getCodiceFiscale ().equals ( input.getCaller ().getPrincipal ().getCodiceFiscale () ) ) {
                continue;
            }
            dto.setDataFineValidita ( new Date () );
            repo.save ( dto );
        }

        RicercaUtenteOutput output = call ( input );
        assertEquals ( new Integer ( 1 ), output.getNumeroRisultatiTotali () );

        for ( Utente dto: repo.findAll () ) {
            if ( dto.getCodiceFiscale ().equals ( input.getCaller ().getPrincipal ().getCodiceFiscale () ) ) {
                continue;
            }
            dto.setDataFineValidita ( null );
            repo.save ( dto );
        }

        output = call ( input );
        assertEquals ( new Integer ( new Long ( repo.count () ).intValue () ), output.getNumeroRisultatiTotali () );

        input.setSoloUtentiInVita ( false );

        for ( Utente dto: repo.findAll () ) {
            if ( dto.getCodiceFiscale ().equals ( input.getCaller ().getPrincipal ().getCodiceFiscale () ) ) {
                continue;
            }
            dto.setDataFineValidita ( new Date () );
            repo.save ( dto );
        }

        output = call ( input );
        assertEquals ( new Integer ( new Long ( repo.count () ).intValue () ), output.getNumeroRisultatiTotali () );

    }

    @Test
    public void shouldReturnAllMandatoryFields () {

        RicercaUtenteInput input = new RicercaUtenteInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        assertTrue ( repo.count () > 0 );

        for ( Utente dto: repo.findAll () ) {

            input.setCodiceFiscale ( dto.getCodiceFiscale () );

            RicercaUtenteOutput output = call ( input );
            assertEquals ( new Integer ( 1 ), output.getNumeroRisultatiTotali () );

            RicercaUtenteOutputDto o = output.getRisultati ().get ( 0 );

            TestEntityHelper.assertFieldsEqual ( o, dto,
                "id",
                "nome",
                "cognome",
                "codiceFiscale",
                "email",
                "dataFineValidita",
                "dataInizioValidita" );
        }

    }

}
