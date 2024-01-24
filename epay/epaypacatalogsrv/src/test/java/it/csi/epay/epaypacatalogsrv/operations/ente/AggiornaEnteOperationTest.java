/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.ente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.ente.AggiornaEnteInput;
import it.csi.epay.epaypacatalogsrv.dto.ente.AggiornaEnteOutput;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.Utente;
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
public class AggiornaEnteOperationTest extends ParentOperationTest {

    @Autowired
    private EnteRepository repo;

    @Autowired
    private UtenteRepository repoUtente;

    private AggiornaEnteOutput call ( AggiornaEnteInput input ) {
        try {
            return getPort ().aggiornaEnte ( input );
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

        AggiornaEnteInput input = new AggiornaEnteInput ();
        input.setCaller ( null );
        input.setId ( TestConstants.ID_ENTE_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        AggiornaEnteInput input = new AggiornaEnteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_ENTE_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        AggiornaEnteInput input = new AggiornaEnteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_ENTE_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        AggiornaEnteInput input = new AggiornaEnteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_ENTE_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        AggiornaEnteInput input = new AggiornaEnteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_ENTE_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        AggiornaEnteInput input = new AggiornaEnteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_ENTE_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    //    @Test
    //    public void shouldReturnNotAllowedWithUserNotAdmin () {
    //
    //        AggiornaEnteInput input = new AggiornaEnteInput ();
    //        input.setCaller ( TestHelper.getCallerOperatore () );
    //        input.setId ( TestConstants.ID_ENTE_VALIDO );
    //
    //        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnNotAllowedWithUserNotAdminOfSpecificEnte () {

        Long id = TestConstants.ID_ENTE_VALIDO;

        AggiornaEnteInput input = getValidAggiornaEnteInput ( id );
        Utente utenteChiamante = repoUtente.findOneByCodiceFiscale ( input.getCaller ().getPrincipal ().getCodiceFiscale () );
        Utente unAltroUtente = repoUtente.findOne ( utenteChiamante.getId () + 1 );

        Ente dto = repo.findOne ( id );
        dto.setUtenteAmministratore ( utenteChiamante );
        repo.save ( dto );
        repo.flush ();

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        dto = repo.findOne ( id );
        dto.setUtenteAmministratore ( unAltroUtente );
        repo.save ( dto );
        repo.flush ();

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNull () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaEnteInput input = new AggiornaEnteInput ();
        input.setCaller ( caller );
        input.setId ( null );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputZero () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaEnteInput input = new AggiornaEnteInput ();
        input.setCaller ( caller );
        input.setId ( 0L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNegative () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaEnteInput input = new AggiornaEnteInput ();
        input.setCaller ( caller );
        input.setId ( -3L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotFoundWithInputMissing () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaEnteInput input = new AggiornaEnteInput ();
        input.setCaller ( caller );
        input.setId ( 99999L );

        assertEquals ( Constants.RESULT_CODES.NOT_FOUND, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnLogoNonPresente () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );

        input.setLogoContent ( null );
        input.setLogoContentType ( null );
        input.setLogoFileName ( null );
        input.setLogoFileSize ( null );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        Ente entity = repo.findOne ( input.getId () );
        entity.setLogoContent ( null );
        entity.setLogoContentType ( null );
        entity.setLogoFileName ( null );
        entity.setLogoFileSize ( null );
        repo.save ( entity );

        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );

    }

    @Test
    public void shouldReturnBadRequestOnMalformedEmail () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setEmail ( "asdasdsads" );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankEmail () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setEmail ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnWrongCodiceModalitaAcquisizioneProvvisori () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setCodiceModalitaAcquisizioneProvvisori ( "INVALID" );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnWrongCodicePeriodicitaSchedulazioneRiconciliazione () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setCodicePeriodicitaSchedulazioneRiconciliazione ( "INVALID" );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnWrongCodiceTipologiaAccertamento () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setCodiceTipologiaAccertamento ( "INVALID" );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnGiornoSchedulazioneMonthlyTooHigh () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setFlagRiconciliazioneVersamenti ( true );
        input.setCodicePeriodicitaSchedulazioneRiconciliazione ( "MEN" );
        input.setGiornoSchedulazione ( 32 );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnGiornoSchedulazioneMonthlyTooLow () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setFlagRiconciliazioneVersamenti ( true );
        input.setCodicePeriodicitaSchedulazioneRiconciliazione ( "MEN" );
        input.setGiornoSchedulazione ( 0 );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnGiornoSchedulazioneWeeklyTooHigh () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setFlagRiconciliazioneVersamenti ( true );
        input.setCodicePeriodicitaSchedulazioneRiconciliazione ( "SET" );
        input.setGiornoSchedulazione ( 8 );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnGiornoSchedulazioneYearlyTooLow () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setFlagRiconciliazioneVersamenti ( true );
        input.setCodicePeriodicitaSchedulazioneRiconciliazione ( "ANN" );
        input.setGiornoSchedulazione ( 0 );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnGiornoSchedulazioneYearlyTooHigh () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setFlagRiconciliazioneVersamenti ( true );
        input.setCodicePeriodicitaSchedulazioneRiconciliazione ( "ANN" );
        input.setGiornoSchedulazione ( 367 );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnOkOnValidGiornoSchedulazioneYearly () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setFlagRiconciliazioneVersamenti ( true );
        input.setCodicePeriodicitaSchedulazioneRiconciliazione ( "ANN" );
        input.setGiornoSchedulazione ( 365 );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankDenominazione () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setDenominazione ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankIndirizzo () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setIndirizzo ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankLocalita () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setLocalita ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankCap () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setCap ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankSiglaProvincia () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setSiglaProvincia ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnNullCodiceModalitaAcquisizioneProvvisori () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setFlagRiconciliazioneVersamenti ( true );
        input.setCodiceModalitaAcquisizioneProvvisori ( null );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnNullCodicePeriodicitaSchedulazioneRiconciliazione () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setFlagRiconciliazioneVersamenti ( true );
        input.setCodicePeriodicitaSchedulazioneRiconciliazione ( null );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnNullCodiceTipologiaAccertamento () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setFlagRiconciliazioneVersamenti ( true );
        input.setCodiceTipologiaAccertamento ( null );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnNullFlussi () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setFlagRicezioneFlussoBaseRendicontazione ( true );
        input.setFlagQualsiasiCodiceVersamento ( false );
        input.setIdCodiciVersamentoAssociati ( null );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnEmptyFlussi () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setFlagRicezioneFlussoBaseRendicontazione ( true );
        input.setFlagQualsiasiCodiceVersamento ( false );
        input.setIdCodiciVersamentoAssociati ( new ArrayList<> () );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

        input.getIdCodiciVersamentoAssociati ().add ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnFlussoAssociatoNull () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setFlagRicezioneFlussoBaseRendicontazione ( true );
        input.setFlagQualsiasiCodiceVersamento ( false );
        input.setIdCodiciVersamentoAssociati ( new ArrayList<> () );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnIdFlussoAssociatoNull () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setFlagRicezioneFlussoBaseRendicontazione ( true );
        input.setFlagQualsiasiCodiceVersamento ( false );
        input.setIdCodiciVersamentoAssociati ( new ArrayList<> () );
        input.getIdCodiciVersamentoAssociati ().add ( null );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnIdFlussoAssociatoZero () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setFlagRicezioneFlussoBaseRendicontazione ( true );
        input.setFlagQualsiasiCodiceVersamento ( false );
        input.setIdCodiciVersamentoAssociati ( new ArrayList<> () );
        input.getIdCodiciVersamentoAssociati ().add ( 0L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnIdFlussoAssociatoNegative () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setFlagRicezioneFlussoBaseRendicontazione ( true );
        input.setFlagQualsiasiCodiceVersamento ( false );
        input.setIdCodiciVersamentoAssociati ( new ArrayList<> () );
        input.getIdCodiciVersamentoAssociati ().add ( -15L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnIdFlussoAssociatoMissing () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setFlagRicezioneFlussoBaseRendicontazione ( true );
        input.setFlagQualsiasiCodiceVersamento ( false );
        input.setIdCodiciVersamentoAssociati ( new ArrayList<> () );
        input.getIdCodiciVersamentoAssociati ().add ( 1L );
        input.getIdCodiciVersamentoAssociati ().add ( 2L );
        input.getIdCodiciVersamentoAssociati ().add ( 99999L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnOkOnAtLeastOneFlusso () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setFlagRicezioneFlussoBaseRendicontazione ( true );
        input.setFlagQualsiasiCodiceVersamento ( false );
        input.setIdCodiciVersamentoAssociati ( new ArrayList<> () );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnOkOnNoFlussiButQualsiasiSelezionato () {

        AggiornaEnteInput input = getValidAggiornaEnteInput ( TestConstants.ID_ENTE_VALIDO );
        input.setFlagRicezioneFlussoBaseRendicontazione ( true );
        input.setFlagQualsiasiCodiceVersamento ( true );
        input.setIdCodiciVersamentoAssociati ( new ArrayList<> () );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldClearFieldsWhenFlagRiconciliazioneVersamentiIsFalse () {
        final Long id = TestConstants.ID_ENTE_VALIDO;
        AggiornaEnteInput input = getValidAggiornaEnteInput ( id );
        input.setFlagRiconciliazioneVersamenti ( false );

        assertEquals ( true, input.getFlagAccertamento () );
        assertEquals ( true, input.getFlagRicezioneErrori () );
        assertNotNull ( input.getCodicePeriodicitaSchedulazioneRiconciliazione () );
        assertNotNull ( input.getCodiceTipologiaAccertamento () );
        assertNotNull ( input.getCodiceModalitaAcquisizioneProvvisori () );
        assertNotNull ( input.getGiornoSchedulazione () );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        Ente dto = repo.findOne ( id );

        assertEquals ( false, dto.getFlagRiconciliazioneVersamenti () );
        assertEquals ( false, dto.getFlagAccertamento () );
        assertEquals ( false, dto.getFlagRicezioneErrori () );
        assertEquals ( null, dto.getPeriodicitaSchedulazioneRiconciliazione () );
        assertEquals ( null, dto.getTipologiaAccertamento () );
        assertEquals ( null, dto.getModalitaAcquisizioneProvvisori () );
        assertEquals ( null, dto.getGiornoSchedulazione () );

    }

    @Test
    public void shouldClearFieldsWhenFlagRicezioneFlussoBaseIsNullAndQualsiasiIsTrue () {
        final Long id = TestConstants.ID_ENTE_VALIDO;
        AggiornaEnteInput input = getValidAggiornaEnteInput ( id );
        input.setFlagRicezioneFlussoBaseRendicontazione ( false );
        input.setFlagQualsiasiCodiceVersamento ( true );
        input.setIdCodiciVersamentoAssociati ( new ArrayList<> () );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        Ente dto = repo.findOne ( id );

        assertEquals ( false, dto.getFlagRicezioneFlussoBaseRendicontazione () );
        assertEquals ( true, dto.getFlagQualsiasiCodiceVersamento () );
        assertEquals ( 0, dto.getCodiciVersamento ().size () );

    }

    @Test
    public void shouldClearFieldsWhenFlagRicezioneFlussoBaseIsTrueAndQualsiasiIsTrue () {
        final Long id = TestConstants.ID_ENTE_VALIDO;
        AggiornaEnteInput input = getValidAggiornaEnteInput ( id );
        input.setFlagRicezioneFlussoBaseRendicontazione ( true );
        input.setFlagQualsiasiCodiceVersamento ( true );
        input.setIdCodiciVersamentoAssociati ( new ArrayList<> () );
        input.getIdCodiciVersamentoAssociati ().add ( TestConstants.ID_ENTE_VALIDO );
        input.getIdCodiciVersamentoAssociati ().add ( 2L );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        Ente dto = repo.findOne ( id );

        assertEquals ( true, dto.getFlagRicezioneFlussoBaseRendicontazione () );
        assertEquals ( true, dto.getFlagQualsiasiCodiceVersamento () );
        assertEquals ( 0, dto.getCodiciVersamento ().size () );

    }

    @Test
    public void shouldClearGiornoSchedulazioneWhenPeriodicitaIsGiornaliera () {
        final Long id = TestConstants.ID_ENTE_VALIDO;
        AggiornaEnteInput input = getValidAggiornaEnteInput ( id );
        input.setFlagRiconciliazioneVersamenti ( true );
        input.setCodicePeriodicitaSchedulazioneRiconciliazione ( "GIO" );
        input.setGiornoSchedulazione ( 3 );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        Ente dto = repo.findOne ( id );

        assertEquals ( true, dto.getFlagRiconciliazioneVersamenti () );
        assertEquals ( input.getCodicePeriodicitaSchedulazioneRiconciliazione (), dto.getPeriodicitaSchedulazioneRiconciliazione ().getCodice () );
        assertEquals ( null, dto.getGiornoSchedulazione () );
    }

    @Test
    public void shouldTopGiornoSchedulazioneToMaxWhenPeriodicitaIsNotGiornaliera () {
        final Long id = TestConstants.ID_ENTE_VALIDO;
        AggiornaEnteInput input = getValidAggiornaEnteInput ( id );
        input.setFlagRiconciliazioneVersamenti ( true );

        String [] periodicitaPossibili = new String [] { "MEN", "BIM", "TRI", "QUA", "SEM" };
        for ( String periodicita: periodicitaPossibili ) {
            input.setCodicePeriodicitaSchedulazioneRiconciliazione ( periodicita );
            input.setGiornoSchedulazione ( 32 );

            assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );

            input.setGiornoSchedulazione ( 30 );

            assertResult ( call ( input ), Constants.RESULT_CODES.OK );
        }
    }

    @Test
    public void shouldReturnBadRequestOnNullGiornoSchedulazioneWhenPeriodicitaIsNotGiornaliera () {
        final Long id = TestConstants.ID_ENTE_VALIDO;
        AggiornaEnteInput input = getValidAggiornaEnteInput ( id );
        input.setFlagRiconciliazioneVersamenti ( true );
        input.setCodicePeriodicitaSchedulazioneRiconciliazione ( "SET" );
        input.setGiornoSchedulazione ( null );

        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
    }

    @Test
    public void shouldUpdateVersioningFields () {
        final Long id = TestConstants.ID_ENTE_VALIDO;
        Ente dto = repo.findOne ( id );
        dto.setUtenteModifica ( null );
        dto.setDataModifica ( null );
        repo.save ( dto );

        AggiornaEnteInput input = getValidAggiornaEnteInput ( id );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
        dto = repo.findOne ( id );

        assertEquals ( input.getCaller ().getPrincipal ().getCodiceFiscale (), dto.getUtenteModifica () );
        assertNotNull ( dto.getDataModifica () );

    }

    @Test
    public void shouldAddEntryIntoStoricoEnte () {
        final Long id = TestConstants.ID_ENTE_VALIDO;
        int previousCount = getJdbcTemplate ().queryForInt ( "select count(*) from epaycat_t_storico_ente where id_ente = ?", id );

        AggiornaEnteInput input = getValidAggiornaEnteInput ( id );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        int afterCount = getJdbcTemplate ().queryForInt ( "select count(*) from epaycat_t_storico_ente where id_ente = ?", id );
        assertEquals ( previousCount + 1, afterCount );

    }

    @Test
    public void shouldNotClearLogoWhenCancellaLogoIsTrue () {
        final Long id = TestConstants.ID_ENTE_CON_LOGO;

        Ente dto = repo.findOne ( id );
        assertNotNull ( dto.getLogoContent () );
        assertNotNull ( dto.getLogoContentType () );
        assertNotNull ( dto.getLogoFileName () );
        assertNotNull ( dto.getLogoFileSize () );

        AggiornaEnteInput input = getValidAggiornaEnteInput ( id );
        input.setCancellaLogo ( true );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
        //
        //        dto = repo.findOne ( id );
        //        assertNull ( dto.getLogoContent () );
        //        assertNull ( dto.getLogoContentType () );
        //        assertNull ( dto.getLogoFileName () );
        //        assertNull ( dto.getLogoFileSize () );
    }

    @Test
    public void shouldKeepLogoWhenLogoInputAreNull () {
        final Long id = TestConstants.ID_ENTE_CON_LOGO;

        Ente dto = repo.findOne ( id );
        assertNotNull ( dto.getLogoContent () );
        assertNotNull ( dto.getLogoContentType () );
        assertNotNull ( dto.getLogoFileName () );
        assertNotNull ( dto.getLogoFileSize () );

        AggiornaEnteInput input = getValidAggiornaEnteInput ( id );

        input.setCancellaLogo ( false );
        input.setLogoContent ( null );
        input.setLogoContentType ( null );
        input.setLogoFileName ( null );
        input.setLogoFileSize ( null );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        dto = repo.findOne ( id );
        assertNotNull ( dto.getLogoContent () );
        assertNotNull ( dto.getLogoContentType () );
        assertNotNull ( dto.getLogoFileName () );
        assertNotNull ( dto.getLogoFileSize () );
    }

    private AggiornaEnteInput getValidAggiornaEnteInput ( Long id ) {

        AggiornaEnteInput input = new AggiornaEnteInput ();

        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setId ( id );

        input.setCap ( "1000" + id );
        input.setCodiceModalitaAcquisizioneProvvisori ( "CAM" );
        input.setCodicePeriodicitaSchedulazioneRiconciliazione ( "MEN" );
        input.setCodiceTipologiaAccertamento ( "UNI" );
        input.setDenominazione ( "DENOMINAZIONE " + id );
        input.setFlagAccertamento ( true );
        input.setFlagEntePlurintermediato ( true );
        input.setFlagQualsiasiCodiceVersamento ( true );
        input.setFlagRicezioneErrori ( true );
        input.setFlagRicezioneFlussoBaseRendicontazione ( true );
        input.setFlagRicezioneFlussoRendicontazione ( true );
        input.setFlagRiconciliazioneVersamenti ( true );
        input.setGiornoSchedulazione ( 1 );
        input.setIndirizzo ( "INDIRIZZO " + id );
        input.setLocalita ( "LOCALITA " + id );
        input.setLogoContent ( new byte [] { 1, 2, 3 } );
        input.setLogoContentType ( "image/jpg" );
        input.setLogoFileName ( "fake_logo.jpg" );
        input.setLogoFileSize ( 3 );
        input.setSiglaProvincia ( "P" + id );

        input.setIdCodiciVersamentoAssociati ( new ArrayList<> () );
        input.getIdCodiciVersamentoAssociati ().add ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );

        input.setCancellaLogo ( false );

        return input;
    }
}
