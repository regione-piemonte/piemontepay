/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.voceentrata;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataDisponibileInput;
import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataDisponibileOutput;
import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataDisponibileOutputDto;
import it.csi.epay.epaypacatalogsrv.repository.VoceEntrataRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.config.TestConstants;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class RicercaVoceEntrataDisponibileOperationTest extends ParentOperationTest {

    @Autowired
    private VoceEntrataRepository repo;

    private RicercaVoceEntrataDisponibileOutput call ( RicercaVoceEntrataDisponibileInput input ) {
        try {
            return getPort ().ricercaVoceEntrataDisponibile ( input );
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

        RicercaVoceEntrataDisponibileInput input = new RicercaVoceEntrataDisponibileInput ();
        input.setCaller ( null );
        input.setCodice ( TestConstants.CODICE_VOCE_ENTRATA_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        RicercaVoceEntrataDisponibileInput input = new RicercaVoceEntrataDisponibileInput ();
        input.setCaller ( caller );
        input.setCodice ( TestConstants.CODICE_VOCE_ENTRATA_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        RicercaVoceEntrataDisponibileInput input = new RicercaVoceEntrataDisponibileInput ();
        input.setCaller ( caller );
        input.setCodice ( TestConstants.CODICE_VOCE_ENTRATA_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceVoceEntrata () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        RicercaVoceEntrataDisponibileInput input = new RicercaVoceEntrataDisponibileInput ();
        input.setCaller ( caller );
        input.setCodice ( TestConstants.CODICE_VOCE_ENTRATA_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }


    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        RicercaVoceEntrataDisponibileInput input = new RicercaVoceEntrataDisponibileInput ();
        input.setCaller ( caller );
        input.setCodice ( TestConstants.CODICE_VOCE_ENTRATA_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceVoceEntrata () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        RicercaVoceEntrataDisponibileInput input = new RicercaVoceEntrataDisponibileInput ();
        input.setCaller ( caller );
        input.setCodice ( TestConstants.CODICE_VOCE_ENTRATA_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }


    //    @Test
    //    public void shouldReturnNotAllowedWithUserNotAdmin () {
    //
    //        RicercaVoceEntrataDisponibileInput input = new RicercaVoceEntrataDisponibileInput ();
    //        input.setCaller ( TestHelper.getCallerOperatore () );
    //        input.setCodice ( TestConstants.CODICE_VOCE_ENTRATA_VALIDO );
    //
    //        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnOKWithInputCorrect () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        RicercaVoceEntrataDisponibileInput input = new RicercaVoceEntrataDisponibileInput ();
        input.setCaller ( caller );
        input.setCodice ( TestConstants.CODICE_VOCE_ENTRATA_VALIDO );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNoResultsWithMissingInputFields () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        RicercaVoceEntrataDisponibileInput input = new RicercaVoceEntrataDisponibileInput ();
        input.setCaller ( caller );
        input.setCodice ( "INVALID" );

        assertEquals ( new Integer ( 0 ), call ( input ).getNumeroRisultatiTotali () );
    }

    @Test
    public void shouldFilterByDescrizione () {

        RicercaVoceEntrataDisponibileInput input = new RicercaVoceEntrataDisponibileInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        getJdbcTemplate ().update ( "update epaycat_t_voce_entrata set descrizione='0'" );

        assertEquals ( 3, getJdbcTemplate ().update (
            "update epaycat_t_voce_entrata set descrizione='XX' where id <= 3" ) );

        input.setDescrizione ( "xx" );

        RicercaVoceEntrataDisponibileOutput output = call ( input );
        assertEquals ( new Integer ( 3 ), output.getNumeroRisultatiTotali () );
        for ( RicercaVoceEntrataDisponibileOutputDto dto: output.getRisultati () ) {
            assertEquals ( "XX", dto.getDescrizione () );
        }
    }

    @Test
    public void shouldFilterByCodiceTematica () {

        RicercaVoceEntrataDisponibileInput input = new RicercaVoceEntrataDisponibileInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        getJdbcTemplate ().update ( "update epaycat_t_voce_entrata set codice_tematica_ppay='" + TestConstants.CODICE_TEMATICA_VALIDA_2 + "'" );

        assertEquals ( 3, getJdbcTemplate ().update (
            "update epaycat_t_voce_entrata set codice_tematica_ppay='" + TestConstants.CODICE_TEMATICA_VALIDA_1 + "' where id <= 3" ) );

        input.setCodiceTematica ( TestConstants.CODICE_TEMATICA_VALIDA_1 );

        RicercaVoceEntrataDisponibileOutput output = call ( input );
        assertEquals ( new Integer ( 3 ), output.getNumeroRisultatiTotali () );
        for ( RicercaVoceEntrataDisponibileOutputDto dto: output.getRisultati () ) {
            assertEquals ( TestConstants.CODICE_TEMATICA_VALIDA_1, dto.getCodiceTematica () );
        }
    }

    @Test
    public void shouldFilterByCodiceMacrotipo () {

        RicercaVoceEntrataDisponibileInput input = new RicercaVoceEntrataDisponibileInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        getJdbcTemplate ().update ( "update epaycat_t_voce_entrata set codice_macrotipo_ppay='" + TestConstants.CODICE_MACROTIPO_VALIDO_2 + "'" );

        assertEquals ( 3, getJdbcTemplate ().update (
            "update epaycat_t_voce_entrata set codice_macrotipo_ppay='" + TestConstants.CODICE_MACROTIPO_VALIDO_1 + "' where id <= 3" ) );

        input.setCodiceMacrotipo ( TestConstants.CODICE_MACROTIPO_VALIDO_1 );

        RicercaVoceEntrataDisponibileOutput output = call ( input );
        assertEquals ( new Integer ( 3 ), output.getNumeroRisultatiTotali () );
        for ( RicercaVoceEntrataDisponibileOutputDto dto: output.getRisultati () ) {
            assertEquals ( TestConstants.CODICE_MACROTIPO_VALIDO_1, dto.getCodiceMacrotipo () );
        }
    }

}
