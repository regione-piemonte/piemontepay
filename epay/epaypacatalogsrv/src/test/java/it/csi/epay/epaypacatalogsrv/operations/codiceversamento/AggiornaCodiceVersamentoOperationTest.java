/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.codiceversamento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.AggiornaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.AggiornaCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.config.TestConstants;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class AggiornaCodiceVersamentoOperationTest extends ParentOperationTest {

    @Autowired
    private CodiceVersamentoRepository repo;

    @Autowired
    private EnteRepository enteRepository;

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
    public void shouldReturnBadRequestWithNullInput () {
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( null ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCaller () {

        AggiornaCodiceVersamentoInput input = new AggiornaCodiceVersamentoInput ();
        input.setCaller ( null );
        input.setId ( 1L );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        AggiornaCodiceVersamentoInput input = new AggiornaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( 1L );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        AggiornaCodiceVersamentoInput input = new AggiornaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        AggiornaCodiceVersamentoInput input = new AggiornaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }


    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        AggiornaCodiceVersamentoInput input = new AggiornaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        AggiornaCodiceVersamentoInput input = new AggiornaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    //    @Test
    //    public void shouldReturnNotAllowedWithUserNotAdmin () {
    //
    //        AggiornaCodiceVersamentoInput input = new AggiornaCodiceVersamentoInput ();
    //        input.setCaller ( TestHelper.getCallerOperatore () );
    //        input.setId ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );
    //
    //        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnBadRequestWithInputZero () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaCodiceVersamentoInput input = new AggiornaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( 0L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNull () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaCodiceVersamentoInput input = new AggiornaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( null );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNegative () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaCodiceVersamentoInput input = new AggiornaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( -3L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotFoundWithInputMissing () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        AggiornaCodiceVersamentoInput input = new AggiornaCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( 99999L );

        assertEquals ( Constants.RESULT_CODES.NOT_FOUND, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestOnWrongCodiceVoceEntrata () {
        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );
        input.setCodiceVoceEntrata ( "INVALID" );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestOnMalformedEmail () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );;
        input.setEmail ( "asdasdsads" );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankEmail () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );;
        input.setEmail ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnWrongCodiceTipoPagamento () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );;
        input.setCodiceTipoPagamento ( "INVALID" );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnWrongCodiceTipologiaAccertamento () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );;
        input.setCodiceTipoPagamento ( "INVALID" );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankDescrizione () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );;
        input.setDescrizione ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    //    @Test
    //    public void shouldReturnOKOnBlankIban () {
    //
    //        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );;
    //        input.setIban ( " " );
    //        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    //
    //    }
    @Test
    public void shouldReturnBadRequestOnBlankIban () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );;
        input.setIban ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    //    @Test
    //    public void shouldReturnOKOnBlankBIC () {
    //
    //        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );;
    //        input.setBic ( " " );
    //        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    //
    //    }
    @Test
    public void shouldReturnBadRequestOnBlankBIC () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );;
        input.setBic ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnNullFlagInvioFlussi () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );;
        input.setFlagInvioFlussi ( null );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnBlankEmailWithFlagInvioFlussiSelected () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );;
        input.setFlagInvioFlussi ( true );
        input.setEmail ( " " );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnNullCodiceTipoPagamento () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );;
        input.setCodiceTipoPagamento ( null );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnNullCodiceVoceEntrata () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );;
        input.setCodiceVoceEntrata ( null );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldAllowNullEmailWithFlagInvioFlussiNotSelected () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );;
        input.setFlagInvioFlussi ( false );
        input.setEmail ( null );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );;
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnNotFoundOnAnnullato () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        CodiceVersamento entity = repo.findOne ( input.getId () );
        entity.setFlagAnnullato ( true );
        repo.save ( entity );

        assertEquals ( Constants.RESULT_CODES.NOT_FOUND, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldUpdateAllFields () {

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );;
        AggiornaCodiceVersamentoOutput output = call ( input );
        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );
        CodiceVersamento dto = repo.findOne ( input.getId () );

        assertEquals ( input.getBic (), dto.getBic () );
        assertEquals ( input.getCodiceTipoPagamento (), dto.getTipoPagamento ().getCodice () );
        assertEquals ( input.getCodiceVoceEntrata (), dto.getVoceEntrata ().getCodice () );
        assertEquals ( input.getDescrizione (), dto.getDescrizione () );
        assertEquals ( input.getEmail (), dto.getEmail () );
        assertEquals ( input.getFlagInvioFlussi (), dto.getFlagInvioFlussi () );
        assertEquals ( input.getIban (), dto.getIban () );
    }

    @Test
    public void shouldUpdateVersioningFields () {
        final Long id = TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE;

        CodiceVersamento dto = repo.findOne ( id );
        dto.setUtenteModifica ( null );
        dto.setDataModifica ( null );
        repo.save ( dto );

        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( id );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
        dto = repo.findOne ( id );

        assertEquals ( input.getCaller ().getPrincipal ().getCodiceFiscale (), dto.getUtenteModifica () );
        assertNotNull ( dto.getDataModifica () );

    }

    @Test
    public void shouldReturnBadRequestOnChangingCodiceVoceEntrataAlreadyUsedForEnte () {
        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );
        input.setCodiceVoceEntrata ( TestConstants.CODICE_CV_SENZA_COLLEGATI_ASSOCIATO_ENTE );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

        // ma deve funzionare se l'altro e' stato annullato
        Ente ente = enteRepository.findOneByCodiceFiscale ( input.getCaller ().getPrincipal ().getCodiceEnte () );

        List<CodiceVersamento> entities = repo.findByEnte_IdAndCodice ( ente.getId (), input.getCodiceVoceEntrata () );
        for ( CodiceVersamento entity: entities ) {
            entity.setFlagAnnullato ( true );
            repo.save ( entity );
        }

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestOnChangingCodiceVoceEntrataWhenHavingAssociati () {
        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );
        input.setCodiceVoceEntrata ( TestConstants.CODICE_CV_LIBERO );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldChangeCodiceOnChangingVoceEntrata () {
        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );
        input.setCodiceVoceEntrata ( TestConstants.CODICE_CV_LIBERO );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        CodiceVersamento dto = repo.findOne ( input.getId () );
        assertEquals ( input.getCodiceVoceEntrata (), dto.getCodice () );
    }

    @Test
    public void shouldChangeCodiceTipoPagamentoOnCollegati () {
        AggiornaCodiceVersamentoInput input = getValidAggiornaCodiceVersamentoInput ( TestConstants.ID_CV_CON_COLLEGATI_NON_ASSOCIATO_ENTE );
        input.setCodiceTipoPagamento ( "PS" );

        CodiceVersamento dto = repo.findOne ( input.getId () );
        input.setCodiceVoceEntrata ( dto.getVoceEntrata ().getCodice () );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

        dto = repo.findOne ( input.getId () );
        assertEquals ( input.getCodiceTipoPagamento (), dto.getTipoPagamento ().getCodice () );
        assertTrue ( "should have some codice collegato", dto.getCodiciVersamentoCollegati ().size () > 0 );

        for ( CodiceVersamento collegato: dto.getCodiciVersamentoCollegati () ) {
            assertEquals ( input.getCodiceTipoPagamento (), collegato.getTipoPagamento ().getCodice () );
        }
    }

    private AggiornaCodiceVersamentoInput getValidAggiornaCodiceVersamentoInput ( Long id ) {
        AggiornaCodiceVersamentoInput input = new AggiornaCodiceVersamentoInput ();

        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setId ( id );
        input.setBic ( "BIC932590" );
        input.setCodiceTipoPagamento ( "LDC" );

        if ( id != null && id > 0L ) {
            CodiceVersamento dto = repo.findOne ( id );
            if ( dto != null ) {
                if ( dto.getVoceEntrata () != null ) {
                    input.setCodiceVoceEntrata ( dto.getVoceEntrata ().getCodice () );
                } else {
                    input.setCodiceVoceEntrata ( null );
                }
            }
        } else {
            throw new RuntimeException ( "INVALID CODICE ENTRATA / DTO TEST PAIR" );
        }

        input.setDescrizione ( "Descrizione 128391238912" );
        input.setEmail ( "test1252189361@test.test" );
        input.setFlagInvioFlussi ( true );
        input.setIban ( "IBAN128581" );

        return input;
    }
}
