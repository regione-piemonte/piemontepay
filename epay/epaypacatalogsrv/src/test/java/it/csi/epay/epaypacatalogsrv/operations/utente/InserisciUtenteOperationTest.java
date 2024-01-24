/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.utente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.operations.utente.InserisciUtenteOperation;
import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.utente.InserisciUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.utente.InserisciUtenteOutput;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.helper.TestEntityHelper;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class InserisciUtenteOperationTest extends ParentOperationTest {

    @Autowired
    private UtenteRepository repo;

    @Mock
    private UtenteRepository utenteMockRepository;

    private InserisciUtenteOutput call ( InserisciUtenteInput input ) {
        try {
            return getPort ().inserisciUtente ( input );
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

        InserisciUtenteInput input = getValidInserisciUtenteInput ();
        input.setCaller ( null );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        InserisciUtenteInput input = getValidInserisciUtenteInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        InserisciUtenteInput input = getValidInserisciUtenteInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        InserisciUtenteInput input = getValidInserisciUtenteInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        InserisciUtenteInput input = getValidInserisciUtenteInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        InserisciUtenteInput input = getValidInserisciUtenteInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    //    @Test
    //    public void shouldReturnNotAllowedWithUserNotAdmin () {
    //
    //        InserisciUtenteInput input = getValidInserisciUtenteInput ();
    //        input.setCaller ( TestHelper.getCallerOperatore () );
    //
    //        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnBadRequestOnWrongEmail () {

        InserisciUtenteInput input = getValidInserisciUtenteInput ();
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
        InserisciUtenteInput input = getValidInserisciUtenteInput ();
        input.setCodiceFiscale ( " " );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
    }

    @Test
    public void shouldReturnBadRequestOnBlankNome () {
        InserisciUtenteInput input = getValidInserisciUtenteInput ();
        input.setNome ( " " );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
    }

    @Test
    public void shouldReturnBadRequestOnBlankCognome () {
        InserisciUtenteInput input = getValidInserisciUtenteInput ();
        input.setCognome ( " " );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
    }

    @Test
    public void shouldNotReturnBadRequestOnBlankEmail () {
        InserisciUtenteInput input = getValidInserisciUtenteInput ();
        input.setEmail ( " " );
        assertResult ( call ( input ), Constants.RESULT_CODES.OK );
    }

    @Test
    public void shouldReturnBadRequestOnNullCodiceFiscale () {
        InserisciUtenteInput input = getValidInserisciUtenteInput ();
        input.setCodiceFiscale ( null );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
    }

    @Test
    public void shouldReturnBadRequestOnNullNome () {
        InserisciUtenteInput input = getValidInserisciUtenteInput ();
        input.setNome ( null );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
    }

    @Test
    public void shouldReturnBadRequestOnNullCognome () {
        InserisciUtenteInput input = getValidInserisciUtenteInput ();
        input.setCognome ( null );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
    }

    @Test
    public void shouldNotReturnBadRequestOnNullEmail () {
        InserisciUtenteInput input = getValidInserisciUtenteInput ();
        input.setEmail ( null );
        assertResult ( call ( input ), Constants.RESULT_CODES.OK );
    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        InserisciUtenteInput input = getValidInserisciUtenteInput ();
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnSequentialID () {
        InserisciUtenteInput input = getValidInserisciUtenteInput ();
        InserisciUtenteOutput output = call ( input );

        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );
        assertNotNull ( output.getRisultatoInserimento ().getId () );
        assertTrue ( output.getRisultatoInserimento ().getId () > 0L );
    }

    @Test
    public void shouldInsertARecord () {
        long countBefore = repo.count ();

        InserisciUtenteInput input = getValidInserisciUtenteInput ();
        InserisciUtenteOutput output = call ( input );
        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );

        long countAfter = repo.count ();

        assertTrue ( countAfter > countBefore );
    }

    @Test
    public void shouldInsertAllFields () {

        InserisciUtenteInput input = getValidInserisciUtenteInput ();
        InserisciUtenteOutput output = call ( input );
        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );
        Utente dto = repo.findOne ( output.getRisultatoInserimento ().getId () );

        TestEntityHelper.assertFieldsEqual ( input, dto,
            "nome",
            "cognome",
            "codiceFiscale",
            "email",
            "dataFineValidita",
            "dataInizioValidita" );
    }

    @Test
    public void shouldInsertVersioningFields () {

        InserisciUtenteInput input = getValidInserisciUtenteInput ();
        InserisciUtenteOutput output = call ( input );
        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );

        Utente dto = repo.findOne ( output.getRisultatoInserimento ().getId () );

        assertEquals ( input.getCaller ().getPrincipal ().getCodiceFiscale (), dto.getUtenteInserimento () );
        assertNotNull ( dto.getDataInserimento () );

        assertEquals ( input.getCaller ().getPrincipal ().getCodiceFiscale (), dto.getUtenteModifica () );
        assertNotNull ( dto.getDataModifica () );
    }

    @DirtiesContext
    @Test
    public void shouldReturnManagedErrorOnMissingGeneratedId () {

        // mock repository
        InserisciUtenteOperation operation = getBean ( InserisciUtenteOperation.class );
        ReflectionTestUtils.setField ( operation, "repository", utenteMockRepository );
        doReturn ( new Utente () ).when ( utenteMockRepository ).save ( any ( Utente.class ) );

        InserisciUtenteInput input = getValidInserisciUtenteInput ();
        assertEquals ( Constants.RESULT_CODES.INTERNAL_ERROR, call ( input ).getCodiceEsito () );
    }

    private InserisciUtenteInput getValidInserisciUtenteInput () {
        InserisciUtenteInput input = new InserisciUtenteInput ();

        input.setCaller ( TestHelper.getCallerAdmin () );

        input.setCodiceFiscale ( "TSTTST01C22F123U" );
        input.setNome ( "NOME" );
        input.setCognome ( "COGNOME" );
        input.setEmail ( "test1252189361@test.test" );
        input.setDataInizioValidita ( new Date () );
        input.setDataFineValidita ( null );

        return input;
    }

}
