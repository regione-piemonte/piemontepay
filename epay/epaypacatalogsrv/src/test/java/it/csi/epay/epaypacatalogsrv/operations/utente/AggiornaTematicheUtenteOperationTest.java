/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.utente;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

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
import it.csi.epay.epaypacatalogsrv.business.operations.utente.AggiornaTematicheUtenteOperation;
import it.csi.epay.epaypacatalogsrv.business.service.InvioMailService;
import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.utente.AggiornaTematicheUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.utente.AggiornaTematicheUtenteOutput;
import it.csi.epay.epaypacatalogsrv.dto.utente.AggiornaTematicheUtenteTematicaInputDto;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.config.TestConstants;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class AggiornaTematicheUtenteOperationTest extends ParentOperationTest {

    @Autowired
    private UtenteRepository utenteRepository;

    @Mock
    InvioMailService invioMailServiceMock;

    private AggiornaTematicheUtenteOutput call ( AggiornaTematicheUtenteInput input ) {
        try {
            return getPort ().aggiornaTematicheUtente ( input );
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

        AggiornaTematicheUtenteInput input = new AggiornaTematicheUtenteInput ();
        input.setCaller ( null );
        input.setId ( 1L );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        AggiornaTematicheUtenteInput input = new AggiornaTematicheUtenteInput ();
        input.setCaller ( caller );
        input.setId ( 1L );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        AggiornaTematicheUtenteInput input = new AggiornaTematicheUtenteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        AggiornaTematicheUtenteInput input = new AggiornaTematicheUtenteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }


    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        AggiornaTematicheUtenteInput input = new AggiornaTematicheUtenteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        AggiornaTematicheUtenteInput input = new AggiornaTematicheUtenteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    //    @Test
    //    public void shouldReturnNotAllowedWithUserNotAdmin () {
    //
    //        AggiornaTematicheUtenteInput input = new AggiornaTematicheUtenteInput ();
    //        input.setCaller ( TestHelper.getCallerOperatore () );
    //        input.setId ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
    //
    //        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnBadRequestWithInputZero () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaTematicheUtenteInput input = new AggiornaTematicheUtenteInput ();
        input.setCaller ( caller );
        input.setId ( 0L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNull () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaTematicheUtenteInput input = new AggiornaTematicheUtenteInput ();
        input.setCaller ( caller );
        input.setId ( null );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNegative () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaTematicheUtenteInput input = new AggiornaTematicheUtenteInput ();
        input.setCaller ( caller );
        input.setId ( -3L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotFoundWithInputMissing () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaTematicheUtenteInput input = new AggiornaTematicheUtenteInput ();
        input.setCaller ( caller );
        input.setId ( 99999L );

        assertEquals ( Constants.RESULT_CODES.NOT_FOUND, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestOnNullTematica () {
        AggiornaTematicheUtenteInput input = getValidAggiornaTematicheUtenteInputTotale ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        input.getTematiche ().add ( AggiornaTematicheUtenteTematicaInputDto.conVisibilitaCompleta ( null ) );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );
    }

    @Test
    public void shouldReturnBadRequestOnEmptyCodiceTematica () {
        AggiornaTematicheUtenteInput input = getValidAggiornaTematicheUtenteInputTotale ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        input.getTematiche ().add ( AggiornaTematicheUtenteTematicaInputDto.conVisibilitaCompleta ( "" ) );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );
    }

    @Test
    public void shouldReturnBadRequestOnBlankCodiceTematica () {
        AggiornaTematicheUtenteInput input = getValidAggiornaTematicheUtenteInputTotale ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        input.getTematiche ().add ( AggiornaTematicheUtenteTematicaInputDto.conVisibilitaCompleta ( "  " ) );

        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );
    }

    @Test
    public void shouldUpdateOnDB () {
        //
        //        AggiornaTematicheUtenteInput input = getValidAggiornaTematicheUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        //
        //        Ente ente = enteRepository.findOneByCodiceFiscale ( input.getCaller ().getPrincipal ().getCodiceEnte () );
        //
        //        List<AssociazioneUtenteTematica> associazioni;
        //
        //        input.getTematiche ().clear ();
        //        assertResult ( call ( input ), Constants.RESULT_CODES.OK );
        //
        //        associazioni = associazioneUtenteTematicaRepository.findByIdUtenteAndIdEnte ( input.getId (), ente.getId ().intValue () );
        //        assertEquals ( 0, associazioni.size () );
        //
        //        input.getTematiche ().clear ();
        //        input.getTematiche ().add ( AggiornaTematicheUtenteTematicaInputDto.conVisibilitaCompleta ( TestConstants.CODICE_TEMATICA_VALIDA_F ) );
        //        input.getTematiche ().add ( AggiornaTematicheUtenteTematicaInputDto.conVisibilitaCompleta ( TestConstants.CODICE_TEMATICA_VALIDA_2 ) );
        //
        //        assertResult ( call ( input ), Constants.RESULT_CODES.OK );
        //        associazioni = associazioneUtenteTematicaRepository.findByIdUtenteAndIdEnte ( input.getId (), ente.getId ().intValue () );
        //        assertEquals ( 2, associazioni.size () );
        //
        //        input.getTematiche ().clear ();
        //        input.getTematiche ().add ( AggiornaTematicheUtenteTematicaInputDto.conVisibilitaCompleta ( TestConstants.CODICE_TEMATICA_VALIDA_F ) );
        //
        //        assertResult ( call ( input ), Constants.RESULT_CODES.OK );
        //        associazioni = associazioneUtenteTematicaRepository.findByIdUtenteAndIdEnte ( input.getId (), ente.getId ().intValue () );
        //        assertEquals ( 1, associazioni.size () );
    }

    @Test
    public void shouldReturnNotFoundOnMissingCodiceTematica () {
        AggiornaTematicheUtenteInput input = getValidAggiornaTematicheUtenteInputTotale ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        input.getTematiche ().add ( AggiornaTematicheUtenteTematicaInputDto.conVisibilitaCompleta ( TestConstants.CODICE_TEMATICA_VALIDA_F + "_MISSING" ) );

        assertResult ( call ( input ), Constants.RESULT_CODES.NOT_FOUND, Constants.MESSAGES.TEMATICA_NOT_FOUND );
    }

    @Test
    public void shouldReturnNotFoundOnMissingIdCodiceVersamento () {
        AggiornaTematicheUtenteInput input = getValidAggiornaTematicheUtenteInputParzialeMultipli ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        input.getTematiche ().get ( 0 ).getIdCodiciVersamento ().add ( 999999L );

        assertResult ( call ( input ), Constants.RESULT_CODES.NOT_FOUND,
            Constants.MESSAGES.CODICE_VERSAMENTO_NOT_FOUND );
    }

    @Test
    public void shouldReturnBadRequestOnVisibilitaTotaleAndCodiciVersamento () {
        AggiornaTematicheUtenteInput input = getValidAggiornaTematicheUtenteInputTotale ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        input.getTematiche ().clear ();

        input.getTematiche ().add ( AggiornaTematicheUtenteTematicaInputDto.conVisibilitaCompleta ( TestConstants.CODICE_TEMATICA_VALIDA_F ) );
        input.getTematiche ().get ( 0 ).getIdCodiciVersamento ().add ( 1L );

        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FORBIDDEN_FIELD );

        input.getTematiche ().get ( 0 ).getIdCodiciVersamento ().clear ();

        assertResult ( call ( input ), Constants.RESULT_CODES.OK );

        input.getTematiche ().get ( 0 ).setIdCodiciVersamento ( null );
        assertResult ( call ( input ), Constants.RESULT_CODES.OK );

    }

    @Test
    public void shouldAcceptNullTematiche () {

        AggiornaTematicheUtenteInput input = getValidAggiornaTematicheUtenteInputTotale ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        input.setTematiche ( null );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldAcceptEmptyTematiche () {

        AggiornaTematicheUtenteInput input = getValidAggiornaTematicheUtenteInputTotale ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        input.setTematiche ( new ArrayList<> () );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnOKAddingAndRemovingTematiche () {

        AggiornaTematicheUtenteInput input = getValidAggiornaTematicheUtenteInputTotale ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        input.getTematiche ().clear ();

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnOKAddingAndRemovingTematicheMultiple () {

        AggiornaTematicheUtenteInput input = getValidAggiornaTematicheUtenteInputTotaleMultiple ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        input.getTematiche ().clear ();

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnOKRemovingVisibilitaTotale () {

        AggiornaTematicheUtenteInput input = getValidAggiornaTematicheUtenteInputTotale ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        input = getValidAggiornaTematicheUtenteInputParzialeMultipli ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnOKAddingVisibilitaTotale () {

        AggiornaTematicheUtenteInput input = getValidAggiornaTematicheUtenteInputParzialeMultipli ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        input = getValidAggiornaTematicheUtenteInputTotale ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnOKRemovingIdCodiceVersamento () {

        AggiornaTematicheUtenteInput input = getValidAggiornaTematicheUtenteInputParzialeMultipli ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        input.getTematiche ().get ( 0 ).getIdCodiciVersamento ().remove ( 0 );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        input.getTematiche ().get ( 0 ).getIdCodiciVersamento ().remove ( 0 );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        input = getValidAggiornaTematicheUtenteInputParzialeMultipli ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnOKRemovingIdCodiceVersamentoMultipli () {

        AggiornaTematicheUtenteInput input = getValidAggiornaTematicheUtenteInputParzialeMultipli ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        input.getTematiche ().get ( 0 ).getIdCodiciVersamento ().remove ( 0 );
        input.getTematiche ().get ( 0 ).getIdCodiciVersamento ().remove ( 0 );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        input = getValidAggiornaTematicheUtenteInputParzialeMultipli ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnOKAddingIdCodiceVersamentoMultipli () {

        AggiornaTematicheUtenteInput input2 = getValidAggiornaTematicheUtenteInputParzialeMultipli ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        AggiornaTematicheUtenteInput input = getValidAggiornaTematicheUtenteInputParzialeMultipli ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        input.getTematiche ().get ( 0 ).getIdCodiciVersamento ().clear ();
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        input.getTematiche ().get ( 0 ).getIdCodiciVersamento ().add (
            input2.getTematiche ().get ( 0 ).getIdCodiciVersamento ().get ( 0 ) );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        input.getTematiche ().get ( 0 ).getIdCodiciVersamento ().add (
            input2.getTematiche ().get ( 0 ).getIdCodiciVersamento ().get ( 1 ) );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnOKWithInputCorrectTotale () {

        AggiornaTematicheUtenteInput input = getValidAggiornaTematicheUtenteInputTotale ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnOKWithInputCorrectTotaleWithoutEmail () {

        AggiornaTematicheUtenteInput input = getValidAggiornaTematicheUtenteInputTotale ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        Utente utente = utenteRepository.findOne ( input.getId () );
        utente.setEmail ( null );
        utenteRepository.save ( utente );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnOKWithInputCorrectParziale () {

        AggiornaTematicheUtenteInput input = getValidAggiornaTematicheUtenteInputParzialeMultipli ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @DirtiesContext
    @Test
    public void shouldSendEmailCorrectly () {

        Long idUtenteTest = TestConstants.ID_UTENTE_VALIDO_NON_CALLER;

        AggiornaTematicheUtenteInput input = getValidAggiornaTematicheUtenteInputTotale ( idUtenteTest );
        input.getTematiche ().clear ();
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        // ora niente di associato

        // mock invio mail service
        AggiornaTematicheUtenteOperation operation = getBean ( AggiornaTematicheUtenteOperation.class );
        ReflectionTestUtils.setField ( operation, "invioMailService", invioMailServiceMock );
        Integer counterAutorizzazione = 0;
        Integer counterRevoca = 0;

        // aggiungo delle tematiche
        input = getValidAggiornaTematicheUtenteInputTotale ( idUtenteTest );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        // verifica che abbia inviato la mail
        Mockito.verify ( invioMailServiceMock, Mockito.times ( ++counterAutorizzazione ) ).inviaMail (
            Mockito.eq ( EmailEnum.AUTORIZZAZIONE_TEMATICA ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );
        Mockito.verify ( invioMailServiceMock, Mockito.times ( counterRevoca ) ).inviaMail (
            Mockito.eq ( EmailEnum.REVOCA_TEMATICA ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );

        // richiamo senza variare
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
        Mockito.verify ( invioMailServiceMock, Mockito.times ( counterAutorizzazione ) ).inviaMail (
            Mockito.eq ( EmailEnum.AUTORIZZAZIONE_TEMATICA ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );
        Mockito.verify ( invioMailServiceMock, Mockito.times ( counterRevoca ) ).inviaMail (
            Mockito.eq ( EmailEnum.REVOCA_TEMATICA ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );

        // elimino delle tematiche
        input.getTematiche ().clear ();
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
        Mockito.verify ( invioMailServiceMock, Mockito.times ( counterAutorizzazione ) ).inviaMail (
            Mockito.eq ( EmailEnum.AUTORIZZAZIONE_TEMATICA ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );
        Mockito.verify ( invioMailServiceMock, Mockito.times ( ++counterRevoca ) ).inviaMail (
            Mockito.eq ( EmailEnum.REVOCA_TEMATICA ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );

        // richiamo senza variare
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
        Mockito.verify ( invioMailServiceMock, Mockito.times ( counterAutorizzazione ) ).inviaMail (
            Mockito.eq ( EmailEnum.AUTORIZZAZIONE_TEMATICA ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );
        Mockito.verify ( invioMailServiceMock, Mockito.times ( counterRevoca ) ).inviaMail (
            Mockito.eq ( EmailEnum.REVOCA_TEMATICA ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );

        // ora niente di associato

        // associo una tematica parziale ed un codice versamento
        input = getValidAggiornaTematicheUtenteInputParziale ( idUtenteTest );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
        Mockito.verify ( invioMailServiceMock, Mockito.times ( ++counterAutorizzazione ) ).inviaMail (
            Mockito.eq ( EmailEnum.AUTORIZZAZIONE_TEMATICA ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );
        Mockito.verify ( invioMailServiceMock, Mockito.times ( counterRevoca ) ).inviaMail (
            Mockito.eq ( EmailEnum.REVOCA_TEMATICA ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );

        // associo un altro codice versamento
        input = getValidAggiornaTematicheUtenteInputParzialeMultipli ( idUtenteTest );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
        Mockito.verify ( invioMailServiceMock, Mockito.times ( ++counterAutorizzazione ) ).inviaMail (
            Mockito.eq ( EmailEnum.AUTORIZZAZIONE_TEMATICA ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );
        Mockito.verify ( invioMailServiceMock, Mockito.times ( counterRevoca ) ).inviaMail (
            Mockito.eq ( EmailEnum.REVOCA_TEMATICA ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );

        // elimino delle tematiche
        input.getTematiche ().clear ();
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
        Mockito.verify ( invioMailServiceMock, Mockito.times ( ++counterRevoca ) ).inviaMail (
            Mockito.eq ( EmailEnum.REVOCA_TEMATICA ), Mockito.any (), Mockito.anyObject (), Mockito.anyObject () );

        // ora nulla di associato
    }

    private AggiornaTematicheUtenteInput getValidAggiornaTematicheUtenteInputTotale ( Long id ) {
        AggiornaTematicheUtenteInput input = new AggiornaTematicheUtenteInput ();

        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setId ( id );

        input.setTematiche ( new ArrayList<> () );
        input.getTematiche ().add (
            AggiornaTematicheUtenteTematicaInputDto.conVisibilitaCompleta (
                TestConstants.CODICE_TEMATICA_VALIDA_F ) );

        return input;
    }

    private AggiornaTematicheUtenteInput getValidAggiornaTematicheUtenteInputTotaleMultiple ( Long id ) {
        AggiornaTematicheUtenteInput input = new AggiornaTematicheUtenteInput ();

        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setId ( id );

        input.setTematiche ( new ArrayList<> () );
        input.getTematiche ().add (
            AggiornaTematicheUtenteTematicaInputDto.conVisibilitaCompleta (
                TestConstants.CODICE_TEMATICA_VALIDA_F ) );
        input.getTematiche ().add (
            AggiornaTematicheUtenteTematicaInputDto.conVisibilitaCompleta (
                TestConstants.CODICE_TEMATICA_VALIDA_B ) );

        return input;
    }

    private AggiornaTematicheUtenteInput getValidAggiornaTematicheUtenteInputParzialeMultipli ( Long id ) {
        AggiornaTematicheUtenteInput input = new AggiornaTematicheUtenteInput ();

        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setId ( id );

        input.setTematiche ( new ArrayList<> () );
        input.getTematiche ().add (
            AggiornaTematicheUtenteTematicaInputDto.conVisibilitaParziale (
                TestConstants.CODICE_TEMATICA_VALIDA_F ) );

        input.getTematiche ().get ( 0 ).getIdCodiciVersamento ().add (
            TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );

        input.getTematiche ().get ( 0 ).getIdCodiciVersamento ().add (
            TestConstants.ID_CV_SENZA_COLLEGATI_ASSOCIATO_ENTE );

        return input;
    }

    private AggiornaTematicheUtenteInput getValidAggiornaTematicheUtenteInputParziale ( Long id ) {
        AggiornaTematicheUtenteInput input = new AggiornaTematicheUtenteInput ();

        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setId ( id );

        input.setTematiche ( new ArrayList<> () );
        input.getTematiche ().add (
            AggiornaTematicheUtenteTematicaInputDto.conVisibilitaParziale (
                TestConstants.CODICE_TEMATICA_VALIDA_F ) );

        input.getTematiche ().get ( 0 ).getIdCodiciVersamento ().add (
            TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );

        return input;
    }
}
