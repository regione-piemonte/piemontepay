/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.ente;

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
import it.csi.epay.epaypacatalogsrv.dto.ente.GetEnteInput;
import it.csi.epay.epaypacatalogsrv.dto.ente.GetEnteOutput;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class GetEnteOperationTest extends ParentOperationTest {

    @Autowired
    private EnteRepository repo;

    private GetEnteOutput call ( GetEnteInput input ) {
        try {
            return getPort ().getEnte ( input );
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

        GetEnteInput input = new GetEnteInput ();
        input.setCaller ( null );
        input.setId ( 1L );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        GetEnteInput input = new GetEnteInput ();
        input.setCaller ( caller );
        input.setId ( 1L );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        GetEnteInput input = new GetEnteInput ();
        input.setCaller ( caller );
        input.setId ( 1L );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        GetEnteInput input = new GetEnteInput ();
        input.setCaller ( caller );
        input.setId ( 1L );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        GetEnteInput input = new GetEnteInput ();
        input.setCaller ( caller );
        input.setId ( 1L );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        GetEnteInput input = new GetEnteInput ();
        input.setCaller ( caller );
        input.setId ( 1L );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    //    @Test
    //    public void shouldReturnNotAllowedWithUserNotAdmin () {
    //
    //                GetEnteInput input = new GetEnteInput ();
    //                input.setCaller ( TestHelper.getCallerOperatore () );
    //                input.setId ( 1L );
    //
    //                assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnBadRequestWithInputNull () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        GetEnteInput input = new GetEnteInput ();
        input.setCaller ( caller );
        input.setId ( null );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputZero () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        GetEnteInput input = new GetEnteInput ();
        input.setCaller ( caller );
        input.setId ( 0L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithInputNegative () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        GetEnteInput input = new GetEnteInput ();
        input.setCaller ( caller );
        input.setId ( -3L );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotFoundWithInputMissing () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        GetEnteInput input = new GetEnteInput ();
        input.setCaller ( caller );
        input.setId ( 99999L );

        assertEquals ( Constants.RESULT_CODES.NOT_FOUND, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

                GetEnteInput input = new GetEnteInput ();
                input.setCaller ( TestHelper.getCallerAdmin () );
                input.setId ( 1L );

                assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnAllMandatoryFields () {
        GetEnteInput input = new GetEnteInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        assertTrue ( repo.count () > 0 );

        for ( Ente dto: repo.findAll () ) {
            assertNotNull ( dto.getId () );
            input.setId ( dto.getId () );

            GetEnteOutput output = call ( input );

            assertEquals ( input.getId (), output.getEnte ().getId () );
            assertEquals ( dto.getCodiceFiscale (), output.getEnte ().getCodiceFiscale () );
            assertEquals ( dto.getCap (), output.getEnte ().getCap () );
            assertEquals ( dto.getModalitaAcquisizioneProvvisori () != null ? dto.getModalitaAcquisizioneProvvisori ().getCodice () : null,
                            output.getEnte ().getCodiceModalitaAcquisizioneProvvisori () );
            assertEquals ( dto.getPeriodicitaSchedulazioneRiconciliazione () != null ? dto.getPeriodicitaSchedulazioneRiconciliazione ().getCodice () : null,
                            output.getEnte ().getCodicePeriodicitaSchedulazioneRiconciliazione () );
            assertEquals ( dto.getStatoAggiornamentoEnte () != null ? dto.getStatoAggiornamentoEnte ().getCodice () : null,
                            output.getEnte ().getCodiceStatoAggiornamento () );
            assertEquals ( dto.getTipologiaAccertamento () != null ? dto.getTipologiaAccertamento ().getCodice () : null,
                            output.getEnte ().getCodiceTipologiaAccertamento () );
            assertEquals ( dto.getDenominazione (), output.getEnte ().getDenominazione () );
            assertEquals ( dto.getStatoAggiornamentoEnte () != null ? dto.getStatoAggiornamentoEnte ().getDescrizione () : null,
                            output.getEnte ().getDescrizioneStatoAggiornamento () );
            assertEquals ( dto.getFlagAccertamento (), output.getEnte ().getFlagAccertamento () );
            assertEquals ( dto.getFlagEntePlurintermediato (), output.getEnte ().getFlagEntePlurintermediato () );
            assertEquals ( dto.getFlagQualsiasiCodiceVersamento (), output.getEnte ().getFlagQualsiasiCodiceVersamento () );
            assertEquals ( dto.getFlagRicezioneErrori (), output.getEnte ().getFlagRicezioneErrori () );
            assertEquals ( dto.getFlagRicezioneFlussoBaseRendicontazione (), output.getEnte ().getFlagRicezioneFlussoBaseRendicontazione () );

            assertEquals ( dto.getFlagRiconciliazioneVersamenti (), output.getEnte ().getFlagRiconciliazioneVersamenti () );
            assertEquals ( dto.getGiornoSchedulazione (), output.getEnte ().getGiornoSchedulazione () );

            assertEquals ( dto.getIndirizzo (), output.getEnte ().getIndirizzo () );
            assertEquals ( dto.getLocalita (), output.getEnte ().getLocalita () );
            assertEquals ( dto.getLogoContentType (), output.getEnte ().getLogoContentType () );
            assertEquals ( dto.getLogoFileName (), output.getEnte ().getLogoFileName () );
            assertEquals ( dto.getLogoFileSize (), output.getEnte ().getLogoFileSize () );
            assertEquals ( dto.getSiglaProvincia (), output.getEnte ().getSiglaProvincia () );
        }

    }
}
