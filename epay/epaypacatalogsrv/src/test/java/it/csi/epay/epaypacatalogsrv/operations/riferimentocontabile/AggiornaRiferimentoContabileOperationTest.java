/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.riferimentocontabile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.service.RiferimentoContabileService;
import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.AggiornaRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.AggiornaRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.config.TestConstants;
import it.csi.epay.epaypacatalogsrv.test.helper.TestEntityHelper;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class AggiornaRiferimentoContabileOperationTest extends RiferimentoContabileParentOperationTest {

    @Autowired
    private RiferimentoContabileRepository repo;

    @Autowired
    private RiferimentoContabileService service;

    private AggiornaRiferimentoContabileOutput call ( AggiornaRiferimentoContabileInput input ) {
        try {
            return getPort ().aggiornaRiferimentoContabile ( input );
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

        AggiornaRiferimentoContabileInput input = new AggiornaRiferimentoContabileInput ();
        input.setCaller ( null );
        input.setId ( 1L );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        AggiornaRiferimentoContabileInput input = new AggiornaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( 1L );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        AggiornaRiferimentoContabileInput input = new AggiornaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_RC_MODIFICABILE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        AggiornaRiferimentoContabileInput input = new AggiornaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_RC_MODIFICABILE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }


    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        AggiornaRiferimentoContabileInput input = new AggiornaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_RC_MODIFICABILE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        AggiornaRiferimentoContabileInput input = new AggiornaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_RC_MODIFICABILE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    //    @Test
    //    public void shouldReturnNotAllowedWithUserNotAdmin () {
    //
    //        AggiornaRiferimentoContabileInput input = new AggiornaRiferimentoContabileInput ();
    //        input.setCaller ( TestHelper.getCallerOperatore () );
    //        input.setId ( TestConstants.ID_RC_MODIFICABILE );
    //
    //        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnBadRequestWithInputZero () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaRiferimentoContabileInput input = new AggiornaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( 0L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNull () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaRiferimentoContabileInput input = new AggiornaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( null );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNegative () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaRiferimentoContabileInput input = new AggiornaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( -3L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotFoundWithInputMissing () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaRiferimentoContabileInput input = new AggiornaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( 99999L );

        assertEquals ( Constants.RESULT_CODES.NOT_FOUND, call ( input ).getCodiceEsito () );
    }

    //    @Test
    //    public void shouldReturnBadRequestOnWrongCodiceTipologiaDatoSpecificoRiscossione () {
    //
    //        AggiornaRiferimentoContabileInput input = getValidAggiornaRiferimentoContabileInput ( TestConstants.ID_RC_MODIFICABILE );
    //        input.setCodiceTipologiaDatoSpecificoRiscossione ( "INVALID" );
    //        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnBadRequestOnEntityNotAlive () {

        AggiornaRiferimentoContabileInput input = getValidAggiornaRiferimentoContabileInput ( TestConstants.ID_RC_NON_IN_VITA );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.RIFERIMENTO_CONTABILE_NON_IN_VITA );

    }

    @Test
    public void shouldReturnOKWithoutPrecedente () {
        int numTested = 0;
        for ( RiferimentoContabile dto: repo.findAll () ) {
            if ( dto.getDataFineValidita () != null ) {
                continue;
            }

            RiferimentoContabile precedente = service.getPosizionePrecedente ( dto );
            if ( precedente == null ) {
                numTested++;

                AggiornaRiferimentoContabileInput input = getValidAggiornaRiferimentoContabileInput ( dto.getId () );
                assertResult ( call ( input ), Constants.RESULT_CODES.OK );
            }
        }

        assertTrue ( "should have test case", numTested > 0 );
    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        AggiornaRiferimentoContabileInput input = getValidAggiornaRiferimentoContabileInput ( TestConstants.ID_RC_MODIFICABILE );;
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldUpdateAllFields () {

        AggiornaRiferimentoContabileInput input = getValidAggiornaRiferimentoContabileInput ( TestConstants.ID_RC_MODIFICABILE );
        AggiornaRiferimentoContabileOutput output = call ( input );
        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );
        RiferimentoContabile dto = repo.findOne ( input.getId () );

        TestEntityHelper.assertFieldsEqual ( input, dto,
            "id",
            "annoAccertamento",
            "annoEsercizio",
            "numeroEsercizio",
            "dataInizioValidita",
            "livelloPdc",
            "numeroAccertamento",
            "numeroArticolo",
            "numeroCapitolo",
            "titolo",
            "categoria",
            "tipologia" );
    }

    @Test
    public void shouldUpdateVersioningFields () {
        final Long id = TestConstants.ID_RC_MODIFICABILE;

        RiferimentoContabile dto = repo.findOne ( id );
        dto.setUtenteModifica ( null );
        dto.setDataModifica ( null );
        repo.save ( dto );

        AggiornaRiferimentoContabileInput input = getValidAggiornaRiferimentoContabileInput ( id );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
        dto = repo.findOne ( id );

        assertEquals ( input.getCaller ().getPrincipal ().getCodiceFiscale (), dto.getUtenteModifica () );
        assertNotNull ( dto.getDataModifica () );

    }

    @Test
    public void shouldReturnBadRequestOnMissingNumeroArticolo () {

        AggiornaRiferimentoContabileInput input = getValidAggiornaRiferimentoContabileInput ( TestConstants.ID_RC_MODIFICABILE );
        input.setNumeroArticolo ( null );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );

    }

    @Test
    public void shouldReturnBadRequestOnBadNumeroArticolo () {

        AggiornaRiferimentoContabileInput input = getValidAggiornaRiferimentoContabileInput ( TestConstants.ID_RC_MODIFICABILE );
        input.setNumeroArticolo ( -13 );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );

    }

    @Test
    public void shouldReturnBadRequestOnMissingNumeroCapitolo () {

        AggiornaRiferimentoContabileInput input = getValidAggiornaRiferimentoContabileInput ( TestConstants.ID_RC_MODIFICABILE );
        input.setNumeroCapitolo ( null );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );

    }

    @Test
    public void shouldReturnBadRequestOnBadNumeroCapitolo () {

        AggiornaRiferimentoContabileInput input = getValidAggiornaRiferimentoContabileInput ( TestConstants.ID_RC_MODIFICABILE );
//        input.setNumeroCapitolo ( -13 );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );

    }

    @Test
    public void shouldReturnBadRequestOnMissingOrBlankLivelloPDC () {

        AggiornaRiferimentoContabileInput input = getValidAggiornaRiferimentoContabileInput ( TestConstants.ID_RC_MODIFICABILE );
        input.setLivelloPdc ( null );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );

        input.setLivelloPdc ( "   " );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
    }

    private AggiornaRiferimentoContabileInput getValidAggiornaRiferimentoContabileInput ( Long id ) {
        AggiornaRiferimentoContabileInput input = new AggiornaRiferimentoContabileInput ();

        input.setCaller ( TestHelper.getCallerAdmin () );

        input.setAnnoAccertamento ( 2018 );
        input.setId ( id );
        input.setLivelloPdc ( "LIVELLO PDC" );
        input.setNumeroAccertamento ( 1 );
        input.setNumeroArticolo ( 2 );
        input.setNumeroCapitolo ( "3" );
        input.setTitolo ( "TITOLO" );
        input.setCategoria ( "cat123123" );
        input.setTipologia ( "tip1293192" );

        return input;
    }
}
