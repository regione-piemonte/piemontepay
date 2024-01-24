/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.utente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.enums.EmailEnum;
import it.csi.epay.epaypacatalogsrv.business.operations.utente.AggiornaUtenteOperation;
import it.csi.epay.epaypacatalogsrv.business.service.InvioMailService;
import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.utente.AggiornaUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.utente.AggiornaUtenteOutput;
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
public class AggiornaUtenteOperationTest extends ParentOperationTest {

    @Autowired
    private UtenteRepository utenteRepository;

    @Mock
    InvioMailService invioMailServiceMock;

    private AggiornaUtenteOutput call ( AggiornaUtenteInput input ) {
        try {
            return getPort ().aggiornaUtente ( input );
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

        AggiornaUtenteInput input = new AggiornaUtenteInput ();
        input.setCaller ( null );
        input.setId ( 1L );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        AggiornaUtenteInput input = new AggiornaUtenteInput ();
        input.setCaller ( caller );
        input.setId ( 1L );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        AggiornaUtenteInput input = new AggiornaUtenteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        AggiornaUtenteInput input = new AggiornaUtenteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }


    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        AggiornaUtenteInput input = new AggiornaUtenteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        AggiornaUtenteInput input = new AggiornaUtenteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }


    //    @Test
    //    public void shouldReturnNotAllowedWithUserNotAdmin () {
    //
    //        AggiornaUtenteInput input = new AggiornaUtenteInput ();
    //        input.setCaller ( TestHelper.getCallerOperatore () );
    //        input.setId ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
    //
    //        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnBadRequestWithInputZero () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaUtenteInput input = new AggiornaUtenteInput ();
        input.setCaller ( caller );
        input.setId ( 0L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNull () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaUtenteInput input = new AggiornaUtenteInput ();
        input.setCaller ( caller );
        input.setId ( null );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNegative () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaUtenteInput input = new AggiornaUtenteInput ();
        input.setCaller ( caller );
        input.setId ( -3L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotFoundWithInputMissing () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaUtenteInput input = new AggiornaUtenteInput ();
        input.setCaller ( caller );
        input.setId ( 99999L );

        assertEquals ( Constants.RESULT_CODES.NOT_FOUND, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestOnWrongEmail () {

        AggiornaUtenteInput input = getValidAggiornaUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        input.setEmail ( "emailatimail" );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );

        input.setEmail ( "emailatimail.it" );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );

        input.setEmail ( "emailatimail@localhost" );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );

        input.setEmail ( "abc@abc.abc" );
        assertResult ( call ( input ), Constants.RESULT_CODES.OK );
    }

    @Test
    public void shouldReturnBadRequestOnBlankCodiceFiscale () {
        AggiornaUtenteInput input = getValidAggiornaUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        input.setCodiceFiscale ( " " );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
    }

    @Test
    public void shouldReturnBadRequestOnBlankNome () {
        AggiornaUtenteInput input = getValidAggiornaUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        input.setNome ( " " );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
    }

    @Test
    public void shouldReturnBadRequestOnBlankCognome () {
        AggiornaUtenteInput input = getValidAggiornaUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        input.setCognome ( " " );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
    }

    @Test
    public void shouldNotNotReturnBadRequestOnBlankEmail () {
        AggiornaUtenteInput input = getValidAggiornaUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        input.setEmail ( " " );
        assertResult ( call ( input ), Constants.RESULT_CODES.OK );
    }

    @Test
    public void shouldReturnBadRequestOnNullCodiceFiscale () {
        AggiornaUtenteInput input = getValidAggiornaUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        input.setCodiceFiscale ( null );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
    }

    @Test
    public void shouldReturnBadRequestOnNullNome () {
        AggiornaUtenteInput input = getValidAggiornaUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        input.setNome ( null );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
    }

    @Test
    public void shouldReturnBadRequestOnNullCognome () {
        AggiornaUtenteInput input = getValidAggiornaUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        input.setCognome ( null );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
    }

    @Test
    public void shouldNotReturnBadRequestOnNullEmail () {
        AggiornaUtenteInput input = getValidAggiornaUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        input.setEmail ( null );
        assertResult ( call ( input ), Constants.RESULT_CODES.OK );
    }

    @Test
    public void shouldReturnBadRequestWhenIsUtenteCorrente () {

        AggiornaUtenteInput input = getValidAggiornaUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setId ( utenteRepository.findOneByCodiceFiscale ( input.getCaller ().getPrincipal ().getCodiceFiscale () ).getId () );

        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.MODIFICA_UTENTE_CORRENTE_NON_CONSENTITA );
    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        AggiornaUtenteInput input = getValidAggiornaUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );;
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldSendMailWithDataFineValiditaSetted () {

        // mock invio mail service
        AggiornaUtenteOperation operation = getBean ( AggiornaUtenteOperation.class );
        ReflectionTestUtils.setField ( operation, "invioMailService", invioMailServiceMock );

        AggiornaUtenteInput input = getValidAggiornaUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );;

        input.setDataFineValidita ( java.util.Date.from (
            LocalDate.of ( 2020, 1, 1 ).atStartOfDay ().atZone ( ZoneId.systemDefault () ).toInstant () ) );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        // verifica che abbia inviato la mail
        Mockito.verify ( invioMailServiceMock, Mockito.times ( 1 ) ).inviaMail (
            Mockito.eq ( EmailEnum.DISATTIVAZIONE_UTENTE ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );

    }

    @DirtiesContext
    @Test
    public void shouldSendMailWithDataFineValiditaChanging () {

        // mock invio mail service
        AggiornaUtenteOperation operation = getBean ( AggiornaUtenteOperation.class );
        ReflectionTestUtils.setField ( operation, "invioMailService", invioMailServiceMock );

        AggiornaUtenteInput input = getValidAggiornaUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        // setto la data di fine validita'
        input.setDataFineValidita ( java.util.Date.from(
            LocalDate.of ( 2021, 1, 1 ).atStartOfDay ().atZone ( ZoneId.systemDefault () ).toInstant () ) );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        // verifica che abbia inviato la mail
        Mockito.verify ( invioMailServiceMock, Mockito.times ( 1 ) ).inviaMail (
            Mockito.eq ( EmailEnum.DISATTIVAZIONE_UTENTE ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );

        // aggiorno ma mantenendo la stessa data di fine validita'
        input.setCognome ( input.getCognome () + "X" );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        // verifica che NON abbia inviato la mail
        Mockito.verify ( invioMailServiceMock, Mockito.times ( 1 ) ).inviaMail (
            Mockito.eq ( EmailEnum.DISATTIVAZIONE_UTENTE ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );

        // setto una nuova data di fine validita' differente dalla prima
        input.setDataFineValidita ( java.util.Date.from (
            LocalDate.of ( 2020, 1, 1 ).atStartOfDay ().atZone ( ZoneId.systemDefault () ).toInstant () ) );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        // verifica che abbia inviato la mail
        Mockito.verify ( invioMailServiceMock, Mockito.times ( 2 ) ).inviaMail (
            Mockito.eq ( EmailEnum.DISATTIVAZIONE_UTENTE ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );

        // cancello la data di fine validita'
        input.setDataFineValidita ( null );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        // verifica che NON abbia inviato la mail
        Mockito.verify ( invioMailServiceMock, Mockito.times ( 2 ) ).inviaMail (
            Mockito.eq ( EmailEnum.DISATTIVAZIONE_UTENTE ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );

        // reimposto la data di fine validita'
        input.setDataFineValidita ( java.util.Date.from (
            LocalDate.of ( 2020, 1, 1 ).atStartOfDay ().atZone ( ZoneId.systemDefault () ).toInstant () ) );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        // verifica che abbia inviato la mail
        Mockito.verify ( invioMailServiceMock, Mockito.times ( 3 ) ).inviaMail (
            Mockito.eq ( EmailEnum.DISATTIVAZIONE_UTENTE ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );

    }

    @Test
    public void shouldNotSendMailWithDataFineValiditaChangingWithoutEmail () {

        // mock invio mail service
        AggiornaUtenteOperation operation = getBean ( AggiornaUtenteOperation.class );
        ReflectionTestUtils.setField ( operation, "invioMailService", invioMailServiceMock );

        AggiornaUtenteInput input = getValidAggiornaUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        input.setEmail ( null );

        // setto la data di fine validita'
        input.setDataFineValidita ( java.util.Date.from (
            LocalDate.of ( 2021, 1, 1 ).atStartOfDay ().atZone ( ZoneId.systemDefault () ).toInstant () ) );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        // verifica che abbia inviato la mail
        Mockito.verify ( invioMailServiceMock, Mockito.times ( 0 ) ).inviaMail (
            Mockito.eq ( EmailEnum.DISATTIVAZIONE_UTENTE ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );

        // aggiorno ma mantenendo la stessa data di fine validita'
        input.setCognome ( input.getCognome () + "X" );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        // verifica che NON abbia inviato la mail
        Mockito.verify ( invioMailServiceMock, Mockito.times ( 0 ) ).inviaMail (
            Mockito.eq ( EmailEnum.DISATTIVAZIONE_UTENTE ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );

        // setto una nuova data di fine validita' differente dalla prima
        input.setDataFineValidita ( java.util.Date.from (
            LocalDate.of ( 2020, 1, 1 ).atStartOfDay ().atZone ( ZoneId.systemDefault () ).toInstant () ) );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        // verifica che abbia inviato la mail
        Mockito.verify ( invioMailServiceMock, Mockito.times ( 0 ) ).inviaMail (
            Mockito.eq ( EmailEnum.DISATTIVAZIONE_UTENTE ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );

        // cancello la data di fine validita'
        input.setDataFineValidita ( null );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        // verifica che NON abbia inviato la mail
        Mockito.verify ( invioMailServiceMock, Mockito.times ( 0 ) ).inviaMail (
            Mockito.eq ( EmailEnum.DISATTIVAZIONE_UTENTE ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );

        // reimposto la data di fine validita'
        input.setDataFineValidita ( java.util.Date.from (
            LocalDate.of ( 2020, 1, 1 ).atStartOfDay ().atZone ( ZoneId.systemDefault () ).toInstant () ) );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        // verifica che abbia inviato la mail
        Mockito.verify ( invioMailServiceMock, Mockito.times ( 0 ) ).inviaMail (
            Mockito.eq ( EmailEnum.DISATTIVAZIONE_UTENTE ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );

    }

    @Test
    public void shouldUpdateAllFields () {

        AggiornaUtenteInput input = getValidAggiornaUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        AggiornaUtenteOutput output = call ( input );
        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );
        Utente dto = utenteRepository.findOne ( input.getId () );

        TestEntityHelper.assertFieldsEqual ( input, dto,
            "id",
            "nome",
            "cognome",
            "codiceFiscale",
            "email",
            "dataFineValidita",
            "dataInizioValidita" );
    }

    @Test
    public void shouldUpdateVersioningFields () {
        final Long id = TestConstants.ID_UTENTE_VALIDO_NON_CALLER;

        Utente dto = utenteRepository.findOne ( id );
        dto.setUtenteModifica ( null );
        dto.setDataModifica ( null );
        utenteRepository.save ( dto );

        AggiornaUtenteInput input = getValidAggiornaUtenteInput ( id );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
        dto = utenteRepository.findOne ( id );

        assertEquals ( input.getCaller ().getPrincipal ().getCodiceFiscale (), dto.getUtenteModifica () );
        assertNotNull ( dto.getDataModifica () );

    }

    private AggiornaUtenteInput getValidAggiornaUtenteInput ( Long id ) {
        AggiornaUtenteInput input = new AggiornaUtenteInput ();

        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setId ( id );

        input.setCodiceFiscale ( "TSTTST01C22F123U" );
        input.setNome ( "NOME" );
        input.setCognome ( "COGNOME" );
        input.setEmail ( "test1252189361@test.test" );
        input.setDataInizioValidita ( new Date () );
        input.setDataFineValidita ( null );

        return input;
    }
}
