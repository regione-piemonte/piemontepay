/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.autorizzazione;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteCorrenteInput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteCorrenteOutput;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class GetProfilazioneUtenteCorrenteOperationTest extends ParentOperationTest {

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    private GetProfilazioneUtenteCorrenteOutput call ( GetProfilazioneUtenteCorrenteInput input ) {
        try {
            return getPort ().getProfilazioneUtenteCorrente ( input );
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
    public void shouldReturnBadRequestWithoutCaller () {

        GetProfilazioneUtenteCorrenteInput input = new GetProfilazioneUtenteCorrenteInput ();
        input.setCaller ( null );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        GetProfilazioneUtenteCorrenteInput input = new GetProfilazioneUtenteCorrenteInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        GetProfilazioneUtenteCorrenteInput input = new GetProfilazioneUtenteCorrenteInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithoutCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        GetProfilazioneUtenteCorrenteInput input = new GetProfilazioneUtenteCorrenteInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        GetProfilazioneUtenteCorrenteInput input = new GetProfilazioneUtenteCorrenteInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        GetProfilazioneUtenteCorrenteInput input = new GetProfilazioneUtenteCorrenteInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }


    @Test
    public void shouldReturnOKWithInputCorrect () {

        GetProfilazioneUtenteCorrenteInput input = new GetProfilazioneUtenteCorrenteInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }


    //    @Test
    //    public void shouldReturnNotAllowedOnUtenteNotAssocatiatedWithEnte () {
    //        CallerInputDto caller = TestHelper.getCallerAdmin ();
    //
    //        GetInfoUtenteInput input = new GetInfoUtenteInput ();
    //        input.setCaller ( caller );
    //        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    //
    //        testDataProviderRepository.deleteAssociazioniUtentiEnti ();
    //
    //        assertResult ( call ( input ), Constants.RESULT_CODES.NOT_ALLOWED, Constants.MESSAGES.UTENTE_ENTE_NOT_ASSOCIATED );
    //    }


    @Test
    public void shouldReturnNotAllowedWithFruitoreWithoutScope () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setCodiceApplicativo ( Constants.FRUITORI.EPAYMODRIC );

        GetProfilazioneUtenteCorrenteInput input = new GetProfilazioneUtenteCorrenteInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );

        caller.setCodiceApplicativo ( Constants.FRUITORI.EPAYMODRICWEB );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }
}
