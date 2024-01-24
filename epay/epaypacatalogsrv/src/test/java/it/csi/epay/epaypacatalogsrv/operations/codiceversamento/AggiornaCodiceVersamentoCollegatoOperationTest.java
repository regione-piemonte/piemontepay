/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.codiceversamento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.AggiornaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.AggiornaCodiceVersamentoOutput;
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
public class AggiornaCodiceVersamentoCollegatoOperationTest extends ParentOperationTest {

    @Autowired
    private CodiceVersamentoRepository repo;

    private AggiornaCodiceVersamentoOutput call ( AggiornaCodiceVersamentoInput input ) {
        try {
            return getPort ().aggiornaCodiceVersamento ( input );
        } catch ( RuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new RuntimeException ( e );
        }
    }

    @Test
    public void shouldReturnBadRequestOnCodiceVoceEntrata () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoCollegatoInput ( TestConstants.ID_CV_COLLEGATO );
        input.setCodiceVoceEntrata ( TestConstants.CODICE_VOCE_ENTRATA_VALIDO );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnCodiceTipoPagamento () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoCollegatoInput ( TestConstants.ID_CV_COLLEGATO );
        input.setCodiceTipoPagamento ( TestConstants.CODICE_TIPO_PAGAMENTO_VALIDO );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankCodiceVoceEntrata () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoCollegatoInput ( TestConstants.ID_CV_COLLEGATO );
        input.setCodiceVoceEntrata ( "" );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankCodiceTipoPagamento () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoCollegatoInput ( TestConstants.ID_CV_COLLEGATO );
        input.setCodiceTipoPagamento ( "" );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankDescrizione () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoCollegatoInput ( TestConstants.ID_CV_COLLEGATO );
        input.setDescrizione ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    //    @Test
    //    public void shouldReturnOKOnBlankIban () {
    //
    //        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoCollegatoInput ( TestConstants.ID_CV_COLLEGATO );
    //        input.setIban ( " " );
    //        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    //
    //    }
    //
    //    @Test
    //    public void shouldReturnOKOnBlankBIC () {
    //
    //        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoCollegatoInput ( TestConstants.ID_CV_COLLEGATO );
    //        input.setBic ( " " );
    //        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnBadRequestOnBlankIban () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoCollegatoInput ( TestConstants.ID_CV_COLLEGATO );
        input.setIban ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankBIC () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoCollegatoInput ( TestConstants.ID_CV_COLLEGATO );
        input.setBic ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankEmailWithFlagInvioFlussiSelected () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoCollegatoInput ( TestConstants.ID_CV_COLLEGATO );
        input.setFlagInvioFlussi ( true );
        input.setEmail ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldAllowNullEmailWithFlagInvioFlussiNotSelected () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoCollegatoInput ( TestConstants.ID_CV_COLLEGATO );
        input.setFlagInvioFlussi ( false );
        input.setEmail ( null );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoCollegatoInput ( TestConstants.ID_CV_COLLEGATO );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldUpdateAllFields () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoCollegatoInput ( TestConstants.ID_CV_COLLEGATO );
        AggiornaCodiceVersamentoOutput output = call ( input );
        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );
        CodiceVersamento dto = repo.findOne ( input.getId () );

        assertEquals ( input.getBic (), dto.getBic () );

        assertEquals ( input.getDescrizione (), dto.getDescrizione () );
        assertEquals ( input.getEmail (), dto.getEmail () );
        assertEquals ( input.getFlagInvioFlussi (), dto.getFlagInvioFlussi () );
        assertEquals ( input.getIban (), dto.getIban () );

    }

    @Test
    public void shouldUpdateVersioningFields () {
        final Long id = TestConstants.ID_CV_COLLEGATO;

        CodiceVersamento dto = repo.findOne ( id );
        dto.setUtenteModifica ( null );
        dto.setDataModifica ( null );
        repo.save ( dto );

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoCollegatoInput ( id );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
        dto = repo.findOne ( id );

        assertEquals ( input.getCaller ().getPrincipal ().getCodiceFiscale (), dto.getUtenteModifica () );
        assertNotNull ( dto.getDataModifica () );
    }

    @Test
    public void shouldReturnBadRequestOnSameDescrizioneAsFather () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoCollegatoInput ( TestConstants.ID_CV_COLLEGATO );
        CodiceVersamento dto = repo.findOne ( input.getId () );
        input.setDescrizione ( " " + dto.getCodiceVersamentoPadre ().getDescrizione ().toUpperCase () + "  " );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

        input.setDescrizione ( " " + dto.getCodiceVersamentoPadre ().getDescrizione ().toUpperCase () + " - DIFFERENT" );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

    private AggiornaCodiceVersamentoInput getValidAggiornaCodiceVersamentoCollegatoInput ( Long id ) {
        AggiornaCodiceVersamentoInput input = new AggiornaCodiceVersamentoInput ();

        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setId ( id );
        input.setBic ( "BICCOLL932590" );
        input.setCodiceTipoPagamento ( null );
        input.setCodiceVoceEntrata ( null );
        input.setDescrizione ( "Descrizione collegato 128391238912" );
        input.setEmail ( "test1252189361collegato@test.test" );
        input.setFlagInvioFlussi ( true );
        input.setIban ( "IBANCOLL128581" );

        return input;
    }
}
