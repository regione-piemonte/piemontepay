/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.codiceversamento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.RicercaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.RicercaCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.RicercaCodiceVersamentoOutputDto;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.config.TestConstants;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class RicercaCodiceVersamentoOperationTest extends ParentOperationTest {

    @Autowired
    private CodiceVersamentoRepository repo;

    private RicercaCodiceVersamentoOutput call ( RicercaCodiceVersamentoInput input ) {
        try {
            return getPort ().ricercaCodiceVersamento ( input );
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

        RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
        input.setCaller ( null );
        input.setDescrizione ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setDescrizione ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setDescrizione ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setDescrizione ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setDescrizione ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setDescrizione ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    //    @Test
    //    public void shouldReturnNotAllowedWithUserNotAdmin () {
    //
    //                RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
    //                input.setCaller ( TestHelper.getCallerOperatore () );
    //                input.setDescrizione ( "INVALID" );
    //
    //                assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnBadRequestWithNullInputFields () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setDescrizione ( null );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithEmptyInputFields () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setDescrizione ( "" );
        input.setIban ( "" );

        assertTrue ( EntityUtils.isEmpty ( input ) );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithBlankInputFields () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setDescrizione ( "  " );
        input.setIban ( "  " );

        assertTrue ( EntityUtils.isEmpty ( input ) );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnOKWithInputCorrect () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setDescrizione ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNoResultsWithMissingInputFields () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setDescrizione ( "INVALID" );

        assertEquals ( new Integer ( 0 ), call ( input ).getNumeroRisultatiTotali () );
    }

    @Test
    public void shouldReturnAllResultsByDirectDescrizione () {

                RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
                input.setCaller ( TestHelper.getCallerAdmin () );

                for ( CodiceVersamento dto: repo.findAll () ) {
                    if ( dto.getCodiceVersamentoPadre () != null ) {
                        continue;
                    }

                    assertNotNull ( dto.getCodice () );
                    input.setDescrizione ( dto.getDescrizione () );

                    RicercaCodiceVersamentoOutput output = call ( input );
                    assertEquals ( new Integer ( 1 ), output.getNumeroRisultatiTotali () );

                    RicercaCodiceVersamentoOutputDto outputDto = output.getRisultati ().get ( 0 );
                    assertEquals ( dto.getDescrizione (), outputDto.getDescrizione () );
                }

    }

    @Test
    public void shouldReturnAllResultsByDirectFields () {

                RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
                input.setCaller ( TestHelper.getCallerAdmin () );

                for ( CodiceVersamento dto: repo.findAll () ) {
                    if ( dto.getCodiceVersamentoPadre () != null ) {
                        continue;
                    }

                    input.setDescrizione ( dto.getDescrizione () );
                    input.setIban ( dto.getIban () );

                    input.setCodiceVoceEntrata ( dto.getVoceEntrata () != null ? dto.getVoceEntrata ().getCodice () : null );

                    RicercaCodiceVersamentoOutput output = call ( input );
                    assertEquals ( new Integer ( 1 ), output.getNumeroRisultatiTotali () );

                    RicercaCodiceVersamentoOutputDto outputDto = output.getRisultati ().get ( 0 );
                    assertEquals ( dto.getDescrizione (), outputDto.getDescrizione () );
                }

    }

    @Test
    public void shouldReturnAllResultsByDirectFieldsMithMixedCase () {

                RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
                input.setCaller ( TestHelper.getCallerAdmin () );

                for ( CodiceVersamento dto: repo.findAll () ) {
                    if ( dto.getCodiceVersamentoPadre () != null ) {
                        continue;
                    }

                    input.setDescrizione ( dto.getDescrizione ().toLowerCase () );
                    input.setIban ( dto.getIban ().toLowerCase () );
                    input.setCodiceVoceEntrata ( dto.getVoceEntrata () != null ? dto.getVoceEntrata ().getCodice () : null );

                    RicercaCodiceVersamentoOutput output = call ( input );
                    assertEquals ( new Integer ( 1 ), output.getNumeroRisultatiTotali () );
                    assertEquals ( dto.getDescrizione (), output.getRisultati ().get ( 0 ).getDescrizione () );

                    input.setDescrizione ( dto.getDescrizione ().toUpperCase () );
                    input.setIban ( dto.getIban ().toUpperCase () );
                    input.setCodiceVoceEntrata ( dto.getVoceEntrata () != null ? dto.getVoceEntrata ().getCodice () : null );

                    output = call ( input );
                    assertEquals ( new Integer ( 1 ), output.getNumeroRisultatiTotali () );
                    assertEquals ( dto.getDescrizione (), output.getRisultati ().get ( 0 ).getDescrizione () );
                }

    }

    @Test
    public void shouldFilterByDescrizione () {

                RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
                input.setCaller ( TestHelper.getCallerAdmin () );

                getJdbcTemplate ().update ( "update " + TestConstants.TABLE_PREFIX +
                    "t_codice_versamento set descrizione='0'" );

                assertEquals ( 3, getJdbcTemplate ().update (
                    "update " + TestConstants.TABLE_PREFIX + "t_codice_versamento set " +
                        " descrizione='XX' where id <= 3" ) );

                input.setDescrizione ( "xx" );

                RicercaCodiceVersamentoOutput output = call ( input );
                assertEquals ( new Integer ( 3 ), output.getNumeroRisultatiTotali () );
                for ( RicercaCodiceVersamentoOutputDto dto: output.getRisultati () ) {
                    assertEquals ( "XX", dto.getDescrizione () );
                }

    }

    @Test
    public void shouldFilterByIBAN () {

                RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
                input.setCaller ( TestHelper.getCallerAdmin () );

                getJdbcTemplate ().update ( "update " + TestConstants.TABLE_PREFIX +
                    "t_codice_versamento set iban='0'" );

                assertEquals ( 3, getJdbcTemplate ().update (
                    "update " + TestConstants.TABLE_PREFIX + "t_codice_versamento set " +
                        " iban='XX' where id <= 3" ) );

                input.setIban ( "xx" );

                RicercaCodiceVersamentoOutput output = call ( input );
                assertEquals ( new Integer ( 3 ), output.getNumeroRisultatiTotali () );
                for ( RicercaCodiceVersamentoOutputDto dto: output.getRisultati () ) {
                    assertEquals ( "XX", dto.getIban () );
                }

    }

    @Test
    public void shouldFilterByCodiceTematica () {

        RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        input.setCodiceTematica ( "F000" );

        int count = 0;
        for ( RicercaCodiceVersamentoOutputDto el: call ( input ).getRisultati () ) {
            count++;
        }

        assertEquals ( 4, count );

    }

    @Test
    public void shouldFilterByCodiceMacrotipo () {

        RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        input.setCodiceMacrotipo ( "R001" );

        int count = 0;
        for ( RicercaCodiceVersamentoOutputDto el: call ( input ).getRisultati () ) {
            count++;
        }

        assertEquals ( 4, count );
    }

    @Test
    public void shouldFilterByCodiceVoceEntrata () {

        RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        input.setCodiceVoceEntrata ( TestConstants.CODICE_CV_CON_COLLEGATI_ASSOCIATO_ENTE );
        assertEquals ( new Integer ( 1 ), call ( input ).getNumeroRisultatiTotali () );

        input.setCodiceVoceEntrata ( TestConstants.CODICE_CV_SENZA_COLLEGATI_ASSOCIATO_ENTE );
        assertEquals ( new Integer ( 1 ), call ( input ).getNumeroRisultatiTotali () );

        input.setCodiceVoceEntrata ( TestConstants.CODICE_CV_CON_COLLEGATI_NON_ASSOCIATO_ENTE );
        assertEquals ( new Integer ( 1 ), call ( input ).getNumeroRisultatiTotali () );

        input.setCodiceVoceEntrata ( TestConstants.CODICE_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );
        assertEquals ( new Integer ( 1 ), call ( input ).getNumeroRisultatiTotali () );
    }

    @Test
    public void shouldReturnAllMandatoryFields () {

        RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        assertTrue ( repo.count () > 0 );

        for ( CodiceVersamento dto: repo.findAll () ) {
            if ( dto.getCodiceVersamentoPadre () != null ) {
                continue;
            }
            if ( !dto.getEnte ().getCodiceFiscale ().equals ( input.getCaller ().getPrincipal ().getCodiceEnte () ) ) {
                continue;
            }

            input.setDescrizione ( dto.getDescrizione () );

            RicercaCodiceVersamentoOutput output = call ( input );
            assertEquals ( new Integer ( 1 ), output.getNumeroRisultatiTotali () );

            RicercaCodiceVersamentoOutputDto o = output.getRisultati ().get ( 0 );
            assertEquals ( dto.getId (), o.getId () );
            assertEquals ( dto.getDescrizione (), o.getDescrizione () );
            assertEquals ( dto.getIban (), o.getIban () );

            if ( dto.getTipoPagamento () != null ) {
                assertEquals ( dto.getTipoPagamento ().getCodice (), o.getCodiceTipoPagamento () );
                assertEquals ( dto.getTipoPagamento ().getDescrizione (), o.getDescrizioneTipoPagamento () );
            } else {
                assertEquals ( null, o.getCodiceTipoPagamento () );
                assertEquals ( null, o.getDescrizioneTipoPagamento () );
            }

            if ( dto.getVoceEntrata () != null ) {
                assertEquals ( dto.getVoceEntrata ().getId (), o.getIdVoceEntrata () );
                assertEquals ( dto.getVoceEntrata ().getCodice (), o.getCodiceVoceEntrata () );
                assertEquals ( dto.getVoceEntrata ().getDescrizione (), o.getDescrizioneVoceEntrata () );

                if ( dto.getVoceEntrata ().getMacrotipo () != null ) {
                    assertEquals ( dto.getVoceEntrata ().getMacrotipo ().getCodice (), o.getCodiceMacrotipo () );
                    assertEquals ( dto.getVoceEntrata ().getMacrotipo ().getDescrizione (), o.getDescrizioneMacrotipo () );
                } else {
                    assertEquals ( null, o.getCodiceMacrotipo () );
                    assertEquals ( null, o.getDescrizioneMacrotipo () );
                }

                if ( dto.getVoceEntrata ().getTematica () != null ) {
                    assertEquals ( dto.getVoceEntrata ().getTematica ().getCodice (), o.getCodiceTematica () );
                    assertEquals ( dto.getVoceEntrata ().getTematica ().getDescrizione (), o.getDescrizioneTematica () );
                } else {
                    assertEquals ( null, o.getCodiceTematica () );
                    assertEquals ( null, o.getDescrizioneTematica () );
                }

            } else {
                assertEquals ( null, o.getIdVoceEntrata () );
                assertEquals ( null, o.getCodiceVoceEntrata () );
                assertEquals ( null, o.getDescrizioneVoceEntrata () );
            }
        }

    }

    @Test
    public void shouldNotReturnCodiciCollegati () {

        int testedPadri = 0;
        int testedCollegati = 0;

        for ( CodiceVersamento dto: repo.findAll () ) {
            RicercaCodiceVersamentoInput input = new RicercaCodiceVersamentoInput ();
            input.setCaller ( TestHelper.getCallerAdmin () );

            input.setDescrizione ( dto.getDescrizione () );

            RicercaCodiceVersamentoOutput output = call ( input );

            if ( dto.getCodiceVersamentoPadre () == null ) {
                testedPadri++;
                assertEquals ( new Integer ( 1 ), output.getNumeroRisultatiTotali () );
            } else {
                testedCollegati++;
                assertEquals ( new Integer ( 0 ), output.getNumeroRisultatiTotali () );
            }
        }

        assertTrue ( testedPadri > 0 );
        assertTrue ( testedCollegati > 0 );
    }

}
