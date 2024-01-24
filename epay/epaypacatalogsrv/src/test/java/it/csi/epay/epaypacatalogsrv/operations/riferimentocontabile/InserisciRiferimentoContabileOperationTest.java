/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.riferimentocontabile;

import it.csi.epay.epaypacatalogsrv.business.operations.riferimentocontabile.InserisciRiferimentoContabileOperation;
import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.InserisciRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.InserisciRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.config.TestConstants;
import it.csi.epay.epaypacatalogsrv.test.helper.TestDateHelper;
import it.csi.epay.epaypacatalogsrv.test.helper.TestEntityHelper;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class InserisciRiferimentoContabileOperationTest extends RiferimentoContabileParentOperationTest {

    @Autowired
    private RiferimentoContabileRepository repo;

    @Autowired
    private CodiceVersamentoRepository codiceVersamentoRepository;

    @Mock
    private RiferimentoContabileRepository codiceVersamentoMockRepository;

    private InserisciRiferimentoContabileOutput call ( InserisciRiferimentoContabileInput input ) {
        try {
            return getPort ().inserisciRiferimentoContabile ( input );
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

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
        input.setCaller ( null );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
        input.setCaller ( caller );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

	@Test
    public void shouldReturnBadRequestOnWrongCodiceTipologiaDatoSpecificoRiscossione () {

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
//        input.setCodiceTipologiaDatoSpecificoRiscossione ( "INVALID" );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnNullDataInizioValidita () {

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
        input.setDataInizioValidita ( null );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnBadRequestOnDataInizioValiditaLessThanPrecedente () {
        int numTested = 0;
        for ( RiferimentoContabile dto: repo.findAll () ) {
            if ( dto.getDataFineValidita () != null ) {
                continue;
            }

            log ( "shouldReturnBadRequestOnDataInizioValiditaLessThanPrecedente on entity " + dto.getId () + " - " + dto.getCodiceVersamento ().getCodice () );

            numTested++;

            InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
            input.setIdCodiceVersamento ( dto.getCodiceVersamento ().getId () );

			input.setDataInizioValidita ( TestDateHelper.minusDays ( dto.getDataInizioValidita (), 1 ) );
            assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.RIFERIMENTO_CONTABILE_INVALID_DATE_SEQUENCE );

            input.setDataInizioValidita ( TestDateHelper.minusDays ( dto.getDataInizioValidita (), 15 ) );
            assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.RIFERIMENTO_CONTABILE_INVALID_DATE_SEQUENCE );

            input.setDataInizioValidita ( TestDateHelper.plusDays ( dto.getDataInizioValidita (), 3 ) );
            assertResult ( call ( input ), Constants.RESULT_CODES.OK );
        }

        assertTrue ( "should have test case", numTested > 0 );
    }

    @Test
    public void shouldReturnBadRequestOnDatoSpecificoRiscossioneBlankOrNull () {
        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
		assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
        assertResult ( call ( input ), Constants.RESULT_CODES.OK );
    }

    @Test
    public void shouldReturnBadRequestOnDescrizioneDatoSpecificoRiscossioneBlankOrNull () {
        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
        assertResult ( call ( input ), Constants.RESULT_CODES.OK );
    }

    @Test
    public void shouldReturnBadRequestOnIdCodiceVersamentoNotValid () {

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();

        input.setIdCodiceVersamento ( null );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );

        input.setIdCodiceVersamento ( 0L );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );

        input.setIdCodiceVersamento ( -1L );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );
    }

    @Test
    public void shouldReturnBadRequestOnCodiceVersamentoNotFound () {

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();

        input.setIdCodiceVersamento ( 99999L );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );

    }

    @Test
    public void shouldNotReturnBadRequestOnCodiceVersamentoCollegato () {

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();

        input.setIdCodiceVersamento ( TestConstants.ID_CV_COLLEGATO );
        // assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );
        assertResult ( call ( input ), Constants.RESULT_CODES.OK );

        input.setIdCodiceVersamento ( TestConstants.ID_CV_SENZA_COLLEGATI_NON_ASSOCIATO_ENTE );
        assertResult ( call ( input ), Constants.RESULT_CODES.OK );
    }

    @Test
    public void shouldReturnBadRequestOnMoreThanFiveForSameCodiceVersamentoWithSameDSRWithMixedDSRs () {

        int limitToTest = 5;
        Set<Integer> differentCount = new HashSet<> ();
        Map<Long, Integer> countMap = new HashMap<> ();

        List<CodiceVersamento> codiciVersamento = codiceVersamentoRepository.findAll ();
        for ( CodiceVersamento dto: codiciVersamento ) {
            if ( dto.getCodiceVersamentoPadre () != null ) {
                continue;
            }
            if ( !countMap.containsKey ( dto.getId () ) ) {
                countMap.put ( dto.getId (), 0 );
                differentCount.add ( 0 );
            }
        }

        for ( RiferimentoContabile dto: repo.findAll () ) {
            Long key = dto.getCodiceVersamento ().getId ();
            if ( dto.getDataFineValidita () != null ) {
                continue;
            }
            if ( dto.getFlagAnnullato () != null && dto.getFlagAnnullato () ) {
                continue;
            }

            if ( !countMap.containsKey ( key ) ) {
                countMap.put ( key, 0 );
            }
            Integer newValue = countMap.get ( key ) + 1;
            countMap.put ( key, newValue );
            differentCount.add ( newValue );
        }

        for ( Entry<Long, Integer> countEntry: countMap.entrySet () ) {
            InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
            input.setIdCodiceVersamento ( countEntry.getKey () );
            for ( int counter = countEntry.getValue (); counter < limitToTest; counter++ ) {
                assertResult ( call ( input ), Constants.RESULT_CODES.OK );
            }
            assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.CODICE_VERSAMENTO_NUM_RIFERIMENTI_EXCEEDED );
        }

        assertTrue ( "should test with at least 0", differentCount.contains ( 0 ) );
        assertTrue ( "should test with at least 1", differentCount.contains ( 1 ) );
    }

	@Test
    public void shouldNotAcceptEmptyCodiceTipologiaDatoSpecificoRiscossione () {

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
//        assertNotNull ( input.getCodiceTipologiaDatoSpecificoRiscossione () );
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

//        input.setCodiceTipologiaDatoSpecificoRiscossione ( null );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnSequentialID () {
        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
        InserisciRiferimentoContabileOutput output = call ( input );

        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );
        assertNotNull ( output.getRisultatoInserimento ().getId () );
        assertTrue ( output.getRisultatoInserimento ().getId () > 0L );
    }

    @Test
    public void shouldInsertARecord () {
        long countBefore = repo.count ();

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
        InserisciRiferimentoContabileOutput output = call ( input );
        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );

        long countAfter = repo.count ();

        assertTrue ( countAfter > countBefore );
    }

    @Test
    public void shouldInsertAllFields () {

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
        InserisciRiferimentoContabileOutput output = call ( input );
        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );
        RiferimentoContabile dto = repo.findOne ( output.getRisultatoInserimento ().getId () );

        TestEntityHelper.assertFieldsEqual ( input, dto,
            "annoAccertamento",
            "annoEsercizio",
            "numeroEsercizio",
            "dataInizioValidita",
            "datoSpecificoRiscossione",
            "descrizioneDatoSpecificoRiscossione",
            "livelloPdc",
            "numeroAccertamento",
            "numeroArticolo",
            "numeroCapitolo",
            "titolo",
            "categoria",
            "codiceTipologiaDatoSpecificoRiscossione - tipologiaDatoSpecificoRiscossione.codice",
            "tipologia",
            "idCodiceVersamento - codiceVersamento.id" );
    }

    @Test
    public void shouldInsertVersioningFields () {

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
        InserisciRiferimentoContabileOutput output = call ( input );
        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );

        RiferimentoContabile dto = repo.findOne ( output.getRisultatoInserimento ().getId () );

        assertEquals ( input.getCaller ().getPrincipal ().getCodiceFiscale (), dto.getUtenteInserimento () );
        assertNotNull ( dto.getDataInserimento () );

        assertEquals ( input.getCaller ().getPrincipal ().getCodiceFiscale (), dto.getUtenteModifica () );
        assertNotNull ( dto.getDataModifica () );
    }

    @DirtiesContext
    @Test
    public void shouldReturnManagedErrorOnMissingGeneratedId () {

        // mock repository
        InserisciRiferimentoContabileOperation operation = getBean ( InserisciRiferimentoContabileOperation.class );
        ReflectionTestUtils.setField ( operation, "repository", codiceVersamentoMockRepository );
        doReturn ( new RiferimentoContabile () ).when ( codiceVersamentoMockRepository ).save ( any ( RiferimentoContabile.class ) );

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
        assertEquals ( Constants.RESULT_CODES.INTERNAL_ERROR, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestOnMissingNumeroArticolo () {

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
        input.setNumeroArticolo ( null );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );

    }

    @Test
    public void shouldReturnBadRequestOnBadNumeroArticolo () {

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
        input.setNumeroArticolo ( -13 );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );

    }

    @Test
    public void shouldReturnBadRequestOnMissingNumeroCapitolo () {

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
        input.setNumeroCapitolo ( null );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );

    }

    @Test
    public void shouldReturnBadRequestOnBadNumeroCapitolo () {

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
//        input.setNumeroCapitolo ( -13 );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );

    }

    @Test
    public void shouldReturnBadRequestOnMissingOrBlankLivelloPDC () {

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
        input.setLivelloPdc ( null );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );

        input.setLivelloPdc ( "   " );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );
    }

    @Test
    public void shouldReturnBadRequestOnMissingAnnoEsercizio () {

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
        input.setAnnoEsercizio ( null );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.FIELD_REQUIRED );

    }

    @Test
    public void shouldReturnBadRequestOnBadAnnoEsercizio () {

        InserisciRiferimentoContabileInput input = getValidInserisciRiferimentoContabileInput ();
        input.setAnnoEsercizio ( -13 );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );

        input.setAnnoEsercizio ( 0 );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );

        input.setAnnoEsercizio ( 999 );
        assertResult ( call ( input ), Constants.RESULT_CODES.BAD_REQUEST, Constants.MESSAGES.INVALID_FIELD );

        input.setAnnoEsercizio ( 2000 );
        assertResult ( call ( input ), Constants.RESULT_CODES.OK );

    }

    private InserisciRiferimentoContabileInput getValidInserisciRiferimentoContabileInput () {
        InserisciRiferimentoContabileInput input = new InserisciRiferimentoContabileInput ();

        input.setCaller ( TestHelper.getCallerAdmin () );

        input.setAnnoAccertamento ( 2018 );
        input.setAnnoEsercizio ( 2018 );
        input.setDataInizioValidita ( new Date () );
        input.setIdCodiceVersamento ( TestConstants.ID_CV_CON_COLLEGATI_ASSOCIATO_ENTE );
        input.setLivelloPdc ( "LIVELLO PDC" );
        input.setNumeroAccertamento ( 1 );
        input.setNumeroArticolo ( 2 );
        input.setNumeroCapitolo ( "3" );
        input.setTitolo ( "TITOLO" );
        input.setCategoria ( "cat123123" );
        input.setTipologia ( "tip1293192" );

        return input;
    }

}
