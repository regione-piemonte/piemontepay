/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.codiceversamento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.operations.codiceversamento.EliminaCodiceVersamentoOperation;
import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.EliminaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.EliminaCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCodiceVersamentoPK;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteCodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.config.TestConstants;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class EliminaCodiceVersamentoOperationTest extends ParentOperationTest {

    @Autowired
    private CodiceVersamentoRepository repo;

    @Mock
    private CodiceVersamentoRepository codiceVersamentoRepositoryMock;

    @Autowired
    private RiferimentoContabileRepository riferimentoContabileRepository;

    @Autowired
    private AssociazioneUtenteCodiceVersamentoRepository associazioneUtenteCodiceVersamentoRepository;

    private EliminaCodiceVersamentoOutput call ( EliminaCodiceVersamentoInput input ) {
        try {
            return getPort ().eliminaCodiceVersamento ( input );
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

        EliminaCodiceVersamentoInput input = new EliminaCodiceVersamentoInput ();
        input.setCaller ( null );
        input.setId ( 1L );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        EliminaCodiceVersamentoInput input = new EliminaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        EliminaCodiceVersamentoInput input = new EliminaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        EliminaCodiceVersamentoInput input = new EliminaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }


    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        EliminaCodiceVersamentoInput input = new EliminaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        EliminaCodiceVersamentoInput input = new EliminaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }


    //    @Test
    //    public void shouldReturnNotAllowedWithUserNotAdmin () {
    //
    //        EliminaCodiceVersamentoInput input = new EliminaCodiceVersamentoInput ();
    //        input.setCaller ( TestHelper.getCallerOperatore () );
    //        input.setId ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );
    //
    //        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnBadRequestWithInputZero () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        EliminaCodiceVersamentoInput input = new EliminaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( 0L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNull () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        EliminaCodiceVersamentoInput input = new EliminaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( null );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNegative () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        EliminaCodiceVersamentoInput input = new EliminaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( -3L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotFoundWithInputMissing () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        EliminaCodiceVersamentoInput input = new EliminaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( 99999L );

        assertEquals ( Constants.RESULT_CODES.NOT_FOUND, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestOnAssociatoUtenti () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        EliminaCodiceVersamentoInput input = new EliminaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );

        AssociazioneUtenteCodiceVersamento newAssociazione = new AssociazioneUtenteCodiceVersamento ();
        AssociazioneUtenteCodiceVersamentoPK newAssociazioneId = new AssociazioneUtenteCodiceVersamentoPK ();
        newAssociazione.setId ( newAssociazioneId );

        newAssociazioneId.setIdCodiceVersamento ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE.intValue () );
        newAssociazioneId.setIdEnte ( TestConstants.ID_ENTE_VALIDO.intValue () );
        newAssociazioneId.setIdUtente ( TestConstants.ID_UTENTE_VALIDO_NON_CALLER );

        associazioneUtenteCodiceVersamentoRepository.save ( newAssociazione );

        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.CODICE_VERSAMENTO_NON_ELIMINABILE_ASSOCIATO_UTENTE );

        associazioneUtenteCodiceVersamentoRepository.delete ( newAssociazione );

        assertResult ( call ( input ), Constants.RESULT_CODES.OK );
    }

    @Test
    public void shouldReturnBadRequestOnAssociatoRiferimenti () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        EliminaCodiceVersamentoInput input = new EliminaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_CV_CON_RIFERIMENTI_3 );

        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST,
            Constants.MESSAGES.CODICE_VERSAMENTO_NON_ELIMINABILE_ASSOCIATO_RIFERIMENTI_CONTABILI );
    }

    @Test
    public void shouldReturnNotFoundOnAnnullato () {

        EliminaCodiceVersamentoInput input = new EliminaCodiceVersamentoInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setId ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        CodiceVersamento entity = repo.findOne ( input.getId () );
        entity.setFlagAnnullato ( true );
        repo.save ( entity );

        assertEquals ( Constants.RESULT_CODES.NOT_FOUND, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        EliminaCodiceVersamentoInput input = new EliminaCodiceVersamentoInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setId ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );

        EliminaCodiceVersamentoOutput output = call ( input );

        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );

    }

    @Test
    public void shouldSetFlagAnnullato () {
        EliminaCodiceVersamentoInput i = new EliminaCodiceVersamentoInput ();
        i.setCaller ( TestHelper.getCallerAdmin () );
        Long id = TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE;
        i.setId ( id );
        EliminaCodiceVersamentoOutput output = call ( i );

        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );
        assertEquals ( true, repo.findOne ( id ).getFlagAnnullato () );
    }

    @Test
    public void shouldReturnBadRequestWhenThereAreCodiciCollegati () {
        EliminaCodiceVersamentoInput i = new EliminaCodiceVersamentoInput ();
        i.setCaller ( TestHelper.getCallerAdmin () );
        Long id = TestConstants.ID_CV_CON_COLLEGATI_NON_ASSOCIATO_ENTE;
        i.setId ( id );
        EliminaCodiceVersamentoOutput output = call ( i );

        assertResult ( output, Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.CODICE_VERSAMENTO_NON_ELIMINABILE_ASSOCIATO_COLLEGATI );
        assertTrue ( repo.findOne ( id ) != null );
    }

    @Test
    public void shouldNotReturnBadRequestWhenThereAreCodiciCollegatiAnnullati () {
        riferimentoContabileRepository.deleteAll ();
        riferimentoContabileRepository.flush ();

        EliminaCodiceVersamentoInput i = new EliminaCodiceVersamentoInput ();
        i.setCaller ( TestHelper.getCallerAdmin () );
        Long id = TestConstants.ID_CV_CON_COLLEGATI_NON_ASSOCIATO_ENTE;
        i.setId ( id );
        EliminaCodiceVersamentoOutput output = call ( i );

        assertResult ( output, Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.CODICE_VERSAMENTO_NON_ELIMINABILE_ASSOCIATO_COLLEGATI );
        CodiceVersamento dto = repo.findOne ( id );

        for ( CodiceVersamento collegato: dto.getCodiciVersamentoCollegati () ) {
            collegato.setFlagAnnullato ( true );
            repo.save ( collegato );
        }

        output = call ( i );

        assertResult ( output, Constants.RESULT_CODES.OK );
        dto = repo.findOne ( id );
        assertEquals ( 0, dto.getCodiciVersamentoCollegati ().size () );
    }

    @Test
    public void shouldReturnBadRequestWhenThereAreEntiCollegati () {
        EliminaCodiceVersamentoInput i = new EliminaCodiceVersamentoInput ();
        i.setCaller ( TestHelper.getCallerAdmin () );
        Long id = TestConstants.ID_CV_SENZA_COLLEGATI_ASSOCIATO_ENTE;
        i.setId ( id );
        EliminaCodiceVersamentoOutput output = call ( i );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, output.getCodiceEsito () );
        assertTrue ( repo.findOne ( id ) != null );
    }

    @DirtiesContext
    @Test
    public void shouldReturnBadRequestOnUnexpectedDataIntegrityViolationException () {

        EliminaCodiceVersamentoInput input = new EliminaCodiceVersamentoInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setId ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );

        CodiceVersamento entity = repo.findOne ( input.getId () );

        // mock repository
        EliminaCodiceVersamentoOperation operation = getBean ( EliminaCodiceVersamentoOperation.class );
        ReflectionTestUtils.setField ( operation, "repository", codiceVersamentoRepositoryMock );

        doThrow ( new DataIntegrityViolationException ( "TEST EXCEPTION" ) ).when ( codiceVersamentoRepositoryMock ).delete ( any ( CodiceVersamento.class ) );
        doThrow ( new DataIntegrityViolationException ( "TEST EXCEPTION" ) ).when ( codiceVersamentoRepositoryMock ).save ( any ( CodiceVersamento.class ) );
        doReturn ( entity ).when ( codiceVersamentoRepositoryMock ).findOne ( anyLong () );

        EliminaCodiceVersamentoOutput output = call ( input );

        assertResult ( output, Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.CODICE_VERSAMENTO_NON_ELIMINABILE );

    }

}
