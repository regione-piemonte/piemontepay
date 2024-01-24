/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.codiceversamento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.GetCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.GetCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.GetCodiceVersamentoOutputDto;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.config.TestConstants;
import it.csi.epay.epaypacatalogsrv.test.helper.TestEntityHelper;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class GetCodiceVersamentoOperationTest extends ParentOperationTest {

    @Autowired
    private CodiceVersamentoRepository repo;

    private GetCodiceVersamentoOutput call ( GetCodiceVersamentoInput input ) {
        try {
            return getPort ().getCodiceVersamento ( input );
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

        GetCodiceVersamentoInput input = new GetCodiceVersamentoInput ();
        input.setCaller ( null );
        input.setId ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        GetCodiceVersamentoInput input = new GetCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        GetCodiceVersamentoInput input = new GetCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        GetCodiceVersamentoInput input = new GetCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        GetCodiceVersamentoInput input = new GetCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        GetCodiceVersamentoInput input = new GetCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    //    @Test
    //    public void shouldReturnNotAllowedWithUserNotAdmin () {
    //
    //        GetCodiceVersamentoInput input = new GetCodiceVersamentoInput ();
    //        input.setCaller ( TestHelper.getCallerOperatore () );
    //        input.setId ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );
    //
    //        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnBadRequestWithInputNull () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        GetCodiceVersamentoInput input = new GetCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( null );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputZero () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        GetCodiceVersamentoInput input = new GetCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( 0L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNegative () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        GetCodiceVersamentoInput input = new GetCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( -3L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotFoundWithInputMissing () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        GetCodiceVersamentoInput input = new GetCodiceVersamentoInput ();
        input.setCaller ( caller );
        input.setId ( 99999L );

        assertEquals ( Constants.RESULT_CODES.NOT_FOUND, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        GetCodiceVersamentoInput input = new GetCodiceVersamentoInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setId ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnAllMandatoryFields () {
        GetCodiceVersamentoInput i = new GetCodiceVersamentoInput ();
        i.setCaller ( TestHelper.getCallerAdmin () );

        assertTrue ( repo.count () > 0 );

        for ( CodiceVersamento dto: repo.findAll () ) {
            if ( dto.getCodiceVersamentoPadre () == null ) {
                continue;
            }

            assertNotNull ( dto.getId () );
            i.setId ( dto.getId () );

            GetCodiceVersamentoOutput output = call ( i );
            GetCodiceVersamentoOutputDto o = output.getCodiceVersamento ();

            TestEntityHelper.assertFieldsEqual ( dto, o,
                "id",
                "bic",
                "codice",
                "descrizione",
                "email",
                "flagInvioFlussi",
                "iban",
                "statoAggiornamento.codice - codiceStatoAggiornamento",
                "statoAggiornamento.descrizione - descrizioneStatoAggiornamento",
                "voceEntrata.id - idVoceEntrata",
                "voceEntrata.codice - codiceVoceEntrata",
                "voceEntrata.descrizione - descrizioneVoceEntrata",
                "tipoPagamento.codice - codiceTipoPagamento",
                "tipoPagamento.descrizione - descrizioneTipoPagamento",
                "voceEntrata.tematica.codice - codiceTematica",
                "voceEntrata.tematica.descrizione - descrizioneTematica",
                "voceEntrata.macrotipo.codice - codiceMacrotipo",
                "voceEntrata.macrotipo.descrizione - descrizioneMacrotipo" );
        }
    }

    @Test
    public void shouldReturnCodiciCollegati () {
        GetCodiceVersamentoInput i = new GetCodiceVersamentoInput ();
        i.setCaller ( TestHelper.getCallerAdmin () );

        i.setId ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );
        GetCodiceVersamentoOutput output = call ( i );
        GetCodiceVersamentoOutputDto o = output.getCodiceVersamento ();
        assertEquals ( i.getId (), o.getId () );
        assertTrue ( o.getCodiciVersamentoCollegati ().size () > 0 );

        i.setId ( TestConstants.ID_CV_CON_COLLEGATI_NON_ASSOCIATO_ENTE );
        output = call ( i );
        o = output.getCodiceVersamento ();
        assertTrue ( o.getCodiciVersamentoCollegati ().size () > 0 );

        i.setId ( TestConstants.ID_CV_SENZA_COLLEGATI_ASSOCIATO_ENTE );
        output = call ( i );
        o = output.getCodiceVersamento ();
        assertTrue ( o.getCodiciVersamentoCollegati ().size () == 0 );

        i.setId ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );
        output = call ( i );
        o = output.getCodiceVersamento ();
        assertTrue ( o.getCodiciVersamentoCollegati ().size () == 0 );
    }

    @Test
    public void shouldNotReturnCodiceVersamentoPadre () {
        GetCodiceVersamentoInput i = new GetCodiceVersamentoInput ();
        i.setCaller ( TestHelper.getCallerAdmin () );

        for ( CodiceVersamento dto: repo.findAll () ) {
            if ( dto.getCodiceVersamentoPadre () != null ) {
                continue;
            }

            i.setId ( dto.getId () );
            GetCodiceVersamentoOutput output = call ( i );
            GetCodiceVersamentoOutputDto o = output.getCodiceVersamento ();

            assertNull ( o.getCodiceVersamentoPadre () );
        }
    }
}
