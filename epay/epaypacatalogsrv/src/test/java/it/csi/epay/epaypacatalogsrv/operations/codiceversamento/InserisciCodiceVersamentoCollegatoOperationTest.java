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
public class InserisciCodiceVersamentoCollegatoOperationTest extends ParentOperationTest {

    @Autowired
    private CodiceVersamentoRepository repo;

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
    public void shouldReturnBadRequestOnCodiceVoceEntrata () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
        input.setCodiceVoceEntrata ( TestConstants.CODICE_VOCE_ENTRATA_VALIDO );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnCodiceTipoPagamento () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
        input.setCodiceTipoPagamento ( TestConstants.CODICE_TIPO_PAGAMENTO_VALIDO );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankCodiceVoceEntrata () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
        input.setCodiceVoceEntrata ( "" );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankCodiceTipoPagamento () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
        input.setCodiceTipoPagamento ( "" );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankDescrizione () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
        input.setDescrizione ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    //    @Test
    //    public void shouldReturnOKOnBlankIban () {
    //
    //        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
    //        input.setIban ( " " );
    //        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    //
    //    }
    //
    //    @Test
    //    public void shouldReturnOKOnBlankBIC () {
    //
    //        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
    //        input.setBic ( " " );
    //        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnBadRequestOnBlankIban () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
        input.setIban ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankBIC () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
        input.setBic ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnZeroIdCodiceVersamentoPadre () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
        input.setIdCodiceVersamentoPadre ( 0L );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnNegativeIdCodiceVersamentoPadre () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
        input.setIdCodiceVersamentoPadre ( -30L );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnMissingIdCodiceVersamentoPadre () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
        input.setIdCodiceVersamentoPadre ( 999999L );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankEmailWithFlagInvioFlussiSelected () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
        input.setFlagInvioFlussi ( true );
        input.setEmail ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnIdCodiceVersamentoPadreOfAnotherCollegato () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
        input.setIdCodiceVersamentoPadre ( TestConstants.ID_CV_COLLEGATO );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldAllowNullEmailWithFlagInvioFlussiNotSelected () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
        input.setFlagInvioFlussi ( false );
        input.setEmail ( null );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestWithFatherAnnullator () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();

        CodiceVersamento padre = repo.findOne ( input.getIdCodiceVersamentoPadre () );
        padre.setFlagAnnullato ( true );
        repo.save ( padre );

        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );
    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnSequentialID () {
        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
        InserisciCodiceVersamentoOutput output = call ( input );

        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );
        assertNotNull ( output.getRisultatoInserimento ().getId () );
        assertTrue ( output.getRisultatoInserimento ().getId () > 0L );

        input = getValidInserisciCodiceVersamentoCollegatoInput ();
        input.setBic ( input.getBic () + "2" );
        input.setDescrizione ( input.getDescrizione () + "2" );
        InserisciCodiceVersamentoOutput output2 = call ( input );

        assertEquals ( Constants.RESULT_CODES.OK, output2.getCodiceEsito () );
        assertNotNull ( output2.getRisultatoInserimento ().getId () );
        assertTrue ( output2.getRisultatoInserimento ().getId () > 0L );

        assertTrue ( output2.getRisultatoInserimento ().getId () > output.getRisultatoInserimento ().getId () );
    }

    @Test
    public void shouldInsertARecord () {
        long countBefore = repo.count ();

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
        InserisciCodiceVersamentoOutput output = call ( input );
        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );

        long countAfter = repo.count ();

        assertTrue ( countAfter > countBefore );
    }

    @Test
    public void shouldInsertAllFields () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
        InserisciCodiceVersamentoOutput output = call ( input );
        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );
        CodiceVersamento dto = repo.findOne ( output.getRisultatoInserimento ().getId () );


        assertEquals ( input.getBic (), dto.getBic () );
        assertEquals ( dto.getCodiceVersamentoPadre ().getTipoPagamento ().getCodice (), dto.getTipoPagamento ().getCodice () );
        assertEquals ( dto.getCodiceVersamentoPadre ().getVoceEntrata ().getCodice (), dto.getVoceEntrata ().getCodice () );
        assertEquals ( input.getDescrizione (), dto.getDescrizione () );
        assertEquals ( input.getEmail (), dto.getEmail () );
        assertEquals ( input.getFlagInvioFlussi (), dto.getFlagInvioFlussi () );
        assertEquals ( input.getIban (), dto.getIban () );

    }

    @Test
    public void shouldInsertVersioningFields () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
        InserisciCodiceVersamentoOutput output = call ( input );
        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );

        CodiceVersamento dto = repo.findOne ( output.getRisultatoInserimento ().getId () );

        assertEquals ( input.getCaller ().getPrincipal ().getCodiceFiscale (), dto.getUtenteInserimento () );
        assertNotNull ( dto.getDataInserimento () );

        assertEquals ( input.getCaller ().getPrincipal ().getCodiceFiscale (), dto.getUtenteModifica () );
        assertNotNull ( dto.getDataModifica () );
    }

    @Test
    public void shouldReturnBadRequestOnSameDescrizioneAsFather () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
        CodiceVersamento dtoPadre = repo.findOne ( input.getIdCodiceVersamentoPadre () );
        input.setDescrizione ( " " + dtoPadre.getDescrizione ().toUpperCase () + "  " );

        InserisciCodiceVersamentoOutput output = call ( input );
        assertResult ( output, Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.CODICE_VERSAMENTO_DESCRIZIONE_UGUALE_PADRE );

        input.setDescrizione ( " " + dtoPadre.getDescrizione ().toUpperCase () + " - DIFFERENT" );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestOnMoreThanNineCollegati () {

        InserisciCodiceVersamentoInput input = getValidInserisciCodiceVersamentoCollegatoInput ();
        input.setIdCodiceVersamentoPadre ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );

        for ( int i = 0; i < 9; i++ ) {
            assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
        }

        InserisciCodiceVersamentoOutput output = call ( input );
        assertResult ( output, Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.CODICE_VERSAMENTO_NUM_COLLEGATI_EXCEEDED );
    }

    private InserisciCodiceVersamentoInput getValidInserisciCodiceVersamentoCollegatoInput () {
        InserisciCodiceVersamentoInput input = new InserisciCodiceVersamentoInput ();

        input.setCaller ( TestHelper.getCallerAdmin () );

        input.setIdCodiceVersamentoPadre ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );

        input.setBic ( "BICC9323" );
        input.setCodiceTipoPagamento ( null );
        input.setCodiceVoceEntrata ( null );
        input.setDescrizione ( "Descrizione COLL 128391238912" );
        input.setEmail ( "test1252189361COLL@test.test" );
        input.setFlagInvioFlussi ( true );
        input.setIban ( "IBANCOLL128581" );

        return input;
    }
}
