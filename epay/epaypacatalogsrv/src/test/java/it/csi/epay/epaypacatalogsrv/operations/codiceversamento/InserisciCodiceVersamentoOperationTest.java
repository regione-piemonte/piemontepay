/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.codiceversamento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.operations.codiceversamento.InserisciCodiceVersamentoOperation;
import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.InserisciCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.InserisciCodiceVersamentoOutput;
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
public class InserisciCodiceVersamentoOperationTest extends ParentOperationTest {

    @Autowired
    private CodiceVersamentoRepository repo;

    @Mock
    private CodiceVersamentoRepository codiceVersamentoMockRepository;

    private InserisciCodiceVersamentoOutput call ( InserisciCodiceVersamentoInput input ) {
        try {
            return getPort ().inserisciCodiceVersamento ( input );
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

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        input.setCaller ( null );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }


    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }


    //    @Test
    //    public void shouldReturnNotAllowedWithUserNotAdmin () {
    //
    //        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
    //        input.setCaller ( TestHelper.getCallerOperatore () );
    //
    //        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnBadRequestOnWrongCodiceVoceEntrata () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        input.setCodiceVoceEntrata ( "INVALID" );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnWrongCodiceTipoPagamento () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        input.setCodiceTipoPagamento ( "INVALID" );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnWrongCodiceTipologiaAccertamento () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        input.setCodiceTipoPagamento ( "INVALID" );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankDescrizione () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        input.setDescrizione ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    //    @Test
    //    public void shouldReturnOKOnBlankIban () {
    //
    //        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
    //        input.setIban ( " " );
    //        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    //
    //    }
    //
    //    @Test
    //    public void shouldReturnOKOnBlankBIC () {
    //
    //        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
    //        input.setBic ( " " );
    //        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    //
    //    }
    @Test
    public void shouldReturnBadRequestOnBlankIban () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        input.setIban ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankBIC () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        input.setBic ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankEmailWithFlagInvioFlussiSelected () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        input.setFlagInvioFlussi ( true );
        input.setEmail ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestOnNullCodiceTipoPagamento () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        input.setCodiceTipoPagamento ( null );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnNullCodiceVoceEntrata () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        input.setCodiceVoceEntrata ( null );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnNullFlagInvioFlussi () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        input.setFlagInvioFlussi ( null );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldAllowNullEmailWithFlagInvioFlussiNotSelected () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        input.setFlagInvioFlussi ( false );
        input.setEmail ( null );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnMalformedEmail () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        input.setEmail ( "asdasdsads" );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankEmail () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        input.setEmail ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnCodiceEntrataAlreadyUsed () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        input.setCodiceVoceEntrata ( TestConstants.CODICE_CV_CON_COLLEGATI_ASSOCIATO_ENTE );

        InserisciCodiceVersamentoOutput output = call ( input );
        assertResult ( output, Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.CODICE_VERSAMENTO_ALREADY_EXISTING );

    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnSequentialID () {
        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        InserisciCodiceVersamentoOutput output = call ( input );

        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );
        assertNotNull ( output.getRisultatoInserimento ().getId () );
        assertTrue ( output.getRisultatoInserimento ().getId () > 0L );
    }

    @Test
    public void shouldInsertARecord () {
        long countBefore = repo.count ();

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        InserisciCodiceVersamentoOutput output = call ( input );
        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );

        long countAfter = repo.count ();

        assertTrue ( countAfter > countBefore );
    }

    @Test
    public void shouldInsertAllFields () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        InserisciCodiceVersamentoOutput output = call ( input );
        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );
        CodiceVersamento dto = repo.findOne ( output.getRisultatoInserimento ().getId () );

        assertEquals ( input.getBic (), dto.getBic () );
        assertEquals ( input.getCodiceTipoPagamento (), dto.getTipoPagamento ().getCodice () );
        assertEquals ( input.getCodiceVoceEntrata (), dto.getVoceEntrata ().getCodice () );
        assertEquals ( input.getDescrizione (), dto.getDescrizione () );
        assertEquals ( input.getEmail (), dto.getEmail () );
        assertEquals ( input.getFlagInvioFlussi (), dto.getFlagInvioFlussi () );
        assertEquals ( input.getIban (), dto.getIban () );

    }

    @Test
    public void shouldInsertVersioningFields () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        InserisciCodiceVersamentoOutput output = call ( input );
        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );

        CodiceVersamento dto = repo.findOne ( output.getRisultatoInserimento ().getId () );

        assertEquals ( input.getCaller ().getPrincipal ().getCodiceFiscale (), dto.getUtenteInserimento () );
        assertNotNull ( dto.getDataInserimento () );

        assertEquals ( input.getCaller ().getPrincipal ().getCodiceFiscale (), dto.getUtenteModifica () );
        assertNotNull ( dto.getDataModifica () );
    }

    @DirtiesContext
    @Test
    public void shouldReturnManagedErrorOnMissingGeneratedId () {

        // mock repository
        InserisciCodiceVersamentoOperation operation = getBean ( InserisciCodiceVersamentoOperation.class );
        ReflectionTestUtils.setField ( operation, "repository", codiceVersamentoMockRepository );
        doReturn ( new CodiceVersamento () ).when ( codiceVersamentoMockRepository ).save ( any ( CodiceVersamento.class ) );

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoInput ();
        assertEquals ( Constants.RESULT_CODES.INTERNAL_ERROR, call ( input ).getCodiceEsito () );
    }

    private InserisciCodiceVersamentoInput getValidInserisciCodiceVersamentoInput () {
        InserisciCodiceVersamentoInput input = new InserisciCodiceVersamentoInput ();

        input.setCaller ( TestHelper.getCallerAdmin () );

        input.setBic ( "BIC932590" );
        input.setCodiceTipoPagamento ( "LDC" );
        input.setCodiceVoceEntrata ( "GS00" );
        input.setDescrizione ( "Descrizione 128391238912" );
        input.setEmail ( "test1252189361@test.test" );
        input.setFlagInvioFlussi ( true );
        input.setIban ( "IBAN128581" );

        return input;
    }

}
