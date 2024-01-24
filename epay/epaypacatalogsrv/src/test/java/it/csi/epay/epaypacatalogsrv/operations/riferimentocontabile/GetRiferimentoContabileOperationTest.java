/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.riferimentocontabile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.GetRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.GetRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.GetRiferimentoContabileOutputDto;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.config.TestConstants;
import it.csi.epay.epaypacatalogsrv.test.helper.TestEntityHelper;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class GetRiferimentoContabileOperationTest extends RiferimentoContabileParentOperationTest {

    @Autowired
    private RiferimentoContabileRepository repo;

    private GetRiferimentoContabileOutput call ( GetRiferimentoContabileInput input ) {
        try {
            return getPort ().getRiferimentoContabile ( input );
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

        GetRiferimentoContabileInput input = new GetRiferimentoContabileInput ();
        input.setCaller ( null );
        input.setId ( TestConstants.ID_RC_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        GetRiferimentoContabileInput input = new GetRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_RC_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        GetRiferimentoContabileInput input = new GetRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_RC_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        GetRiferimentoContabileInput input = new GetRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_RC_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        GetRiferimentoContabileInput input = new GetRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_RC_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        GetRiferimentoContabileInput input = new GetRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( TestConstants.ID_RC_VALIDO );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    //    @Test
    //    public void shouldReturnNotAllowedWithUserNotAdmin () {
    //
    //        GetRiferimentoContabileInput input = new GetRiferimentoContabileInput ();
    //        input.setCaller ( TestHelper.getCallerOperatore () );
    //        input.setId ( TestConstants.ID_RC_VALIDO );
    //
    //        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnBadRequestWithInputNull () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        GetRiferimentoContabileInput input = new GetRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( null );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputZero () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        GetRiferimentoContabileInput input = new GetRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( 0L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNegative () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        GetRiferimentoContabileInput input = new GetRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( -3L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotFoundWithInputMissing () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        GetRiferimentoContabileInput input = new GetRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setId ( 99999L );

        assertEquals ( Constants.RESULT_CODES.NOT_FOUND, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        GetRiferimentoContabileInput input = new GetRiferimentoContabileInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setId ( TestConstants.ID_RC_VALIDO );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnAllMandatoryFields () {
        GetRiferimentoContabileInput i = new GetRiferimentoContabileInput ();
        i.setCaller ( TestHelper.getCallerAdmin () );

        assertTrue ( repo.count () > 0 );

        for ( RiferimentoContabile dto: repo.findAll () ) {

            assertNotNull ( dto.getId () );
            i.setId ( dto.getId () );

            GetRiferimentoContabileOutput output = call ( i );
            GetRiferimentoContabileOutputDto o = output.getRiferimentoContabile ();

            TestEntityHelper.assertFieldsEqual ( o, dto,
                "id",
                "annoAccertamento",
                "annoEsercizio",
                "numeroEsercizio",
                "dataFineValidita",
                "dataInizioValidita",
                "datoSpecificoRiscossione",
                "descrizioneDatoSpecificoRiscossione",
                "descrizioneErroreAggiornamento",
                "livelloPdc",
                "numeroAccertamento",
                "numeroArticolo",
                "numeroCapitolo",
                "titolo",
                "categoria",
                "codiceTipologiaDatoSpecificoRiscossione - tipologiaDatoSpecificoRiscossione.codice",
                "descrizioneTipologiaDatoSpecificoRiscossione - tipologiaDatoSpecificoRiscossione.descrizione",
                "tipologia",
                "idCodiceVersamento - codiceVersamento.id",
                "codiceCodiceVersamento - codiceVersamento.codice",
                "descrizioneCodiceVersamento - codiceVersamento.descrizione",
                "codiceVoceEntrata - codiceVersamento.voceEntrata.codice",
                "descrizioneVoceEntrata - codiceVersamento.voceEntrata.descrizione",
                "codiceMacrotipo - codiceVersamento.voceEntrata.macrotipo.codice",
                "descrizioneMacrotipo - codiceVersamento.voceEntrata.macrotipo.descrizione",
                "codiceTematica - codiceVersamento.voceEntrata.tematica.codice",
                "descrizioneTematica - codiceVersamento.voceEntrata.tematica.descrizione",
                "codiceStatoAggiornamento - statoAggiornamento.codice",
                "descrizioneStatoAggiornamento - statoAggiornamento.descrizione" );
        }
    }

}
