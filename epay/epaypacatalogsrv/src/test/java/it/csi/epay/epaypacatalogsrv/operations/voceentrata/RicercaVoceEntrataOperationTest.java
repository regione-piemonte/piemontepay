/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.voceentrata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataInput;
import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataOutput;
import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataOutputDto;
import it.csi.epay.epaypacatalogsrv.model.VoceEntrata;
import it.csi.epay.epaypacatalogsrv.repository.VoceEntrataRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.config.TestConstants;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class RicercaVoceEntrataOperationTest extends ParentOperationTest {

    @Autowired
    private VoceEntrataRepository repo;

    private RicercaVoceEntrataOutput call ( RicercaVoceEntrataInput input ) {
        try {
            return getPort ().ricercaVoceEntrata ( input );
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

        RicercaVoceEntrataInput input = new RicercaVoceEntrataInput ();
        input.setCaller ( null );
        input.setCodice ( TestConstants.CODICE_VOCE_ENTRATA_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        RicercaVoceEntrataInput input = new RicercaVoceEntrataInput ();
        input.setCaller ( caller );
        input.setCodice ( TestConstants.CODICE_VOCE_ENTRATA_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        RicercaVoceEntrataInput input = new RicercaVoceEntrataInput ();
        input.setCaller ( caller );
        input.setCodice ( TestConstants.CODICE_VOCE_ENTRATA_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceVoceEntrata () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        RicercaVoceEntrataInput input = new RicercaVoceEntrataInput ();
        input.setCaller ( caller );
        input.setCodice ( TestConstants.CODICE_VOCE_ENTRATA_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }


    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        RicercaVoceEntrataInput input = new RicercaVoceEntrataInput ();
        input.setCaller ( caller );
        input.setCodice ( TestConstants.CODICE_VOCE_ENTRATA_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceVoceEntrata () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        RicercaVoceEntrataInput input = new RicercaVoceEntrataInput ();
        input.setCaller ( caller );
        input.setCodice ( TestConstants.CODICE_VOCE_ENTRATA_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }


    //    @Test
    //    public void shouldReturnNotAllowedWithUserNotAdmin () {
    //
    //        RicercaVoceEntrataInput input = new RicercaVoceEntrataInput ();
    //        input.setCaller ( TestHelper.getCallerOperatore () );
    //        input.setCodice ( TestConstants.CODICE_VOCE_ENTRATA_VALIDO );
    //
    //        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnOKWithInputCorrect () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        RicercaVoceEntrataInput input = new RicercaVoceEntrataInput ();
        input.setCaller ( caller );
        input.setCodice ( TestConstants.CODICE_VOCE_ENTRATA_VALIDO );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNoResultsWithMissingInputFields () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        RicercaVoceEntrataInput input = new RicercaVoceEntrataInput ();
        input.setCaller ( caller );
        input.setCodice ( "INVALID" );

        assertEquals ( new Integer ( 0 ), call ( input ).getNumeroRisultatiTotali () );
    }

    @Test
    public void shouldReturnAllResultsByDirectCode () {

        RicercaVoceEntrataInput input = new RicercaVoceEntrataInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        for ( VoceEntrata dto: repo.findAll () ) {
            assertNotNull ( dto.getCodice () );
            input.setCodice ( dto.getCodice () );

            RicercaVoceEntrataOutput output = call ( input );
            assertEquals ( new Integer ( 1 ), output.getNumeroRisultatiTotali () );

            RicercaVoceEntrataOutputDto outputDto = output.getRisultati ().get ( 0 );
            assertEquals ( dto.getCodice (), outputDto.getCodice () );
        }
    }

    @Test
    public void shouldReturnAllResultsByDirectFields () {

        RicercaVoceEntrataInput input = new RicercaVoceEntrataInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        for ( VoceEntrata dto: repo.findAll () ) {
            input.setCodice ( dto.getCodice () );
            input.setDescrizione ( dto.getDescrizione () );
            input.setCodiceMacrotipo ( dto.getMacrotipo ().getCodice () );
            input.setCodiceTematica ( dto.getTematica ().getCodice () );

            RicercaVoceEntrataOutput output = call ( input );
            assertEquals ( new Integer ( 1 ), output.getNumeroRisultatiTotali () );

            RicercaVoceEntrataOutputDto outputDto = output.getRisultati ().get ( 0 );
            assertEquals ( dto.getCodice (), outputDto.getCodice () );
        }
    }

    @Test
    public void shouldReturnAllResultsByDirectFieldsMithMixedCase () {

        RicercaVoceEntrataInput input = new RicercaVoceEntrataInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        for ( VoceEntrata dto: repo.findAll () ) {
            input.setCodice ( dto.getCodice () );
            input.setDescrizione ( dto.getDescrizione ().toUpperCase () );
            input.setCodiceMacrotipo ( dto.getMacrotipo ().getCodice () );
            input.setCodiceTematica ( dto.getTematica ().getCodice () );

            RicercaVoceEntrataOutput output = call ( input );
            assertEquals ( new Integer ( 1 ), output.getNumeroRisultatiTotali () );
            assertEquals ( dto.getCodice (), output.getRisultati ().get ( 0 ).getCodice () );

            input.setCodice ( dto.getCodice () );
            input.setDescrizione ( dto.getDescrizione ().toLowerCase () );
            input.setCodiceMacrotipo ( dto.getMacrotipo ().getCodice () );
            input.setCodiceTematica ( dto.getTematica ().getCodice () );

            output = call ( input );
            assertEquals ( new Integer ( 1 ), output.getNumeroRisultatiTotali () );
            assertEquals ( dto.getCodice (), output.getRisultati ().get ( 0 ).getCodice () );
        }
    }

    @Test
    public void shouldFilterByDescrizione () {

        RicercaVoceEntrataInput input = new RicercaVoceEntrataInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        getJdbcTemplate ().update ( "update epaycat_t_voce_entrata set descrizione='0'" );

        assertEquals ( 3, getJdbcTemplate ().update (
            "update epaycat_t_voce_entrata set descrizione='XX' where id <= 3" ) );

        input.setDescrizione ( "xx" );

        RicercaVoceEntrataOutput output = call ( input );
        assertEquals ( new Integer ( 3 ), output.getNumeroRisultatiTotali () );
        for ( RicercaVoceEntrataOutputDto dto: output.getRisultati () ) {
            assertEquals ( "XX", dto.getDescrizione () );
        }
    }

    @Test
    public void shouldFilterByCodiceTematica () {

        RicercaVoceEntrataInput input = new RicercaVoceEntrataInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        getJdbcTemplate ().update ( "update epaycat_t_voce_entrata set codice_tematica_ppay='" + TestConstants.CODICE_TEMATICA_VALIDA_2 + "'" );

        assertEquals ( 3, getJdbcTemplate ().update (
            "update epaycat_t_voce_entrata set codice_tematica_ppay='" + TestConstants.CODICE_TEMATICA_VALIDA_1 + "' where id <= 3" ) );

        input.setCodiceTematica ( TestConstants.CODICE_TEMATICA_VALIDA_1 );

        RicercaVoceEntrataOutput output = call ( input );
        assertEquals ( new Integer ( 3 ), output.getNumeroRisultatiTotali () );
        for ( RicercaVoceEntrataOutputDto dto: output.getRisultati () ) {
            assertEquals ( TestConstants.CODICE_TEMATICA_VALIDA_1, dto.getCodiceTematica () );
        }
    }

    @Test
    public void shouldFilterByCodiceMacrotipo () {

        RicercaVoceEntrataInput input = new RicercaVoceEntrataInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        getJdbcTemplate ().update ( "update epaycat_t_voce_entrata set codice_macrotipo_ppay='" + TestConstants.CODICE_MACROTIPO_VALIDO_2 + "'" );

        assertEquals ( 3, getJdbcTemplate ().update (
            "update epaycat_t_voce_entrata set codice_macrotipo_ppay='" + TestConstants.CODICE_MACROTIPO_VALIDO_1 + "' where id <= 3" ) );

        input.setCodiceMacrotipo ( TestConstants.CODICE_MACROTIPO_VALIDO_1 );

        RicercaVoceEntrataOutput output = call ( input );
        assertEquals ( new Integer ( 3 ), output.getNumeroRisultatiTotali () );
        for ( RicercaVoceEntrataOutputDto dto: output.getRisultati () ) {
            assertEquals ( TestConstants.CODICE_MACROTIPO_VALIDO_1, dto.getCodiceMacrotipo () );
        }
    }

    @Test
    public void shouldReturnAllMandatoryFields () {

        RicercaVoceEntrataInput input = new RicercaVoceEntrataInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        assertTrue ( repo.count () > 0 );

        for ( VoceEntrata dto: repo.findAll () ) {
            input.setCodice ( dto.getCodice () );

            RicercaVoceEntrataOutput output = call ( input );
            assertEquals ( new Integer ( 1 ), output.getNumeroRisultatiTotali () );

            RicercaVoceEntrataOutputDto outputDto = output.getRisultati ().get ( 0 );
            assertEquals ( dto.getId (), outputDto.getId () );
            assertEquals ( dto.getCodice (), outputDto.getCodice () );
            assertEquals ( dto.getDescrizione (), outputDto.getDescrizione () );
            assertEquals ( dto.getMacrotipo () != null ? dto.getMacrotipo ().getCodice () : null, outputDto.getCodiceMacrotipo () );
            assertEquals ( dto.getTematica () != null ? dto.getTematica ().getCodice () : null, outputDto.getCodiceTematica () );
        }
    }
}
