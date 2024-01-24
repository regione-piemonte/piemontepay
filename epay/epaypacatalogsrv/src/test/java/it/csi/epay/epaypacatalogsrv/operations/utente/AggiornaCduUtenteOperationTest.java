/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.utente;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.utente.AggiornaCduUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.utente.AggiornaCduUtenteOutput;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCdu;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteCduRepository;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteCodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteTematicaRepository;
import it.csi.epay.epaypacatalogsrv.repository.CsiLogAuditRepository;
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
public class AggiornaCduUtenteOperationTest extends ParentOperationTest {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private AssociazioneUtenteCduRepository associazioneUtenteCduRepository;

    @Autowired
    private AssociazioneUtenteTematicaRepository associazioneUtenteTematicaRepository;

    @Autowired
    private AssociazioneUtenteCodiceVersamentoRepository associazioneUtenteCodiceVersamentoRepository;

    @Autowired
    private CsiLogAuditRepository csiLogAuditRepository;

    private AggiornaCduUtenteOutput call ( AggiornaCduUtenteInput input ) {
        try {
            return getPort ().aggiornaCduUtente ( input );
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

        AggiornaCduUtenteInput input = new AggiornaCduUtenteInput ();
        input.setCaller ( null );
        input.setId ( 1L );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        AggiornaCduUtenteInput input = new AggiornaCduUtenteInput ();
        input.setCaller ( caller );
        input.setId ( 1L );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        AggiornaCduUtenteInput input = new AggiornaCduUtenteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        AggiornaCduUtenteInput input = new AggiornaCduUtenteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        AggiornaCduUtenteInput input = new AggiornaCduUtenteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        AggiornaCduUtenteInput input = new AggiornaCduUtenteInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    //    @Test
    //    public void shouldReturnNotAllowedWithUserNotAdmin () {
    //
    //        AggiornaCduUtenteInput input = new AggiornaCduUtenteInput ();
    //        input.setCaller ( TestHelper.getCallerOperatore () );
    //        input.setId ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
    //
    //        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnBadRequestWithInputZero () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaCduUtenteInput input = new AggiornaCduUtenteInput ();
        input.setCaller ( caller );
        input.setId ( 0L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNull () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaCduUtenteInput input = new AggiornaCduUtenteInput ();
        input.setCaller ( caller );
        input.setId ( null );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNegative () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaCduUtenteInput input = new AggiornaCduUtenteInput ();
        input.setCaller ( caller );
        input.setId ( -3L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotFoundWithInputMissing () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaCduUtenteInput input = new AggiornaCduUtenteInput ();
        input.setCaller ( caller );
        input.setId ( 99999L );

        assertEquals ( Constants.RESULT_CODES.NOT_FOUND, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestOnEmptyCodiceCdu () {
        AggiornaCduUtenteInput input = getValidAggiornaCduUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        input.getCodiciCdu ().add ( "" );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );
    }

    @Test
    public void shouldReturnBadRequestOnBlankCodiceCdu () {
        AggiornaCduUtenteInput input = getValidAggiornaCduUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        input.getCodiciCdu ().add ( "" );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );
    }

    //    @Test
    //    public void shouldReturnNotAuthorizedOnUtenteNonAssociatoAdEnte () {
    //
    //        AggiornaCduUtenteInput input = getValidAggiornaCduUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
    //
    //        associazioneUtenteCduRepository.deleteByIdUtente ( input.getId () );
    //        associazioneUtenteCodiceVersamentoRepository.deleteByIdUtente ( input.getId () );
    //        associazioneUtenteTematicaRepository.deleteByIdUtente ( input.getId () );
    //
    //        Utente utente = utenteRepository.findOne ( input.getId () );
    //        utente.getEnti ().clear ();
    //        utenteRepository.save ( utente );
    //        utenteRepository.flush ();
    //
    //        assertResult ( call ( input ), Constants.RESULT_CODES.NOT_ALLOWED, Constants.MESSAGES.UTENTE_ENTE_NOT_ASSOCIATED );
    //    }

    @Test
    public void shouldUpdateOnDB () {

        AggiornaCduUtenteInput input = getValidAggiornaCduUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        Ente ente = enteRepository.findOneByCodiceFiscale ( input.getCaller ().getPrincipal ().getCodiceEnte () );

        List<AssociazioneUtenteCdu> associazioni;

        input.getCodiciCdu ().clear ();
        assertResult ( call ( input ), Constants.RESULT_CODES.OK );
        associazioneUtenteCduRepository.flush ();
        csiLogAuditRepository.flush ();

        associazioni = associazioneUtenteCduRepository.findByIdUtenteAndIdEnte ( input.getId (), ente.getId ().intValue () );
        assertEquals ( 0, associazioni.size () );

        input.getCodiciCdu ().clear ();
        input.getCodiciCdu ().add ( Constants.USE_CASES.LOGIN );
        input.getCodiciCdu ().add ( Constants.USE_CASES.RICERCA_UTENTE );

        assertResult ( call ( input ), Constants.RESULT_CODES.OK );
        associazioneUtenteCduRepository.flush ();
        csiLogAuditRepository.flush ();

        associazioni = associazioneUtenteCduRepository.findByIdUtenteAndIdEnte ( input.getId (), ente.getId ().intValue () );
        assertEquals ( 2, associazioni.size () );

        input.getCodiciCdu ().clear ();
        input.getCodiciCdu ().add ( Constants.USE_CASES.LOGIN );

        assertResult ( call ( input ), Constants.RESULT_CODES.OK );
        associazioneUtenteCduRepository.flush ();
        csiLogAuditRepository.flush ();

        associazioni = associazioneUtenteCduRepository.findByIdUtenteAndIdEnte ( input.getId (), ente.getId ().intValue () );
        assertEquals ( 1, associazioni.size () );
    }

    @Test
    public void shouldReturnNotFoundOnMissingCodiceCdu () {
        AggiornaCduUtenteInput input = getValidAggiornaCduUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        input.getCodiciCdu ().add ( "MISSING_USE_CASE" );
        assertResult ( call ( input ), Constants.RESULT_CODES.NOT_FOUND, Constants.MESSAGES.CDU_NOT_FOUND );
    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        AggiornaCduUtenteInput input = getValidAggiornaCduUtenteInput ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    private AggiornaCduUtenteInput getValidAggiornaCduUtenteInput ( Long id ) {
        AggiornaCduUtenteInput input = new AggiornaCduUtenteInput ();

        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setId ( id );

        input.setCodiciCdu ( new ArrayList<> () );
        input.getCodiciCdu ().add ( Constants.USE_CASES.LOGIN );

        return input;
    }
}
