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

import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCodiceVersamentoPK;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteTematica;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteTematicaPK;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteCodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteTematicaRepository;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.repository.TematicaPpayRepository;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.helper.TestEntityHelper;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class RicercaRiferimentoContabileOperationTest extends RiferimentoContabileParentOperationTest {

    @Autowired
    private RiferimentoContabileRepository repo;

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private TematicaPpayRepository tematicaRepository;

    @Autowired
    private CodiceVersamentoRepository codiceVersamentoRepository;

    @Autowired
    private AssociazioneUtenteTematicaRepository associazioneUtenteTematicaRepository;

    @Autowired
    private AssociazioneUtenteCodiceVersamentoRepository associazioneUtenteCodiceVersamentoRepository;

    private RicercaRiferimentoContabileOutput call ( RicercaRiferimentoContabileInput input ) {
        try {
            return getPort ().ricercaRiferimentoContabile ( input );
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

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( null );
        input.setDescrizioneCodiceVersamento ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.setPrincipal ( null );

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setDescrizioneCodiceVersamento ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( null );

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setDescrizioneCodiceVersamento ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( null );

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setDescrizioneCodiceVersamento ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceFiscale ( "INVALID" );

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setDescrizioneCodiceVersamento ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();
        caller.getPrincipal ().setCodiceEnte ( "INVALID" );

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setDescrizioneCodiceVersamento ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    //    @Test
    //    public void shouldReturnNotAllowedWithUserNotAdmin () {
    //
    //        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
    //        input.setCaller ( TestHelper.getCallerOperatore () );
    //        input.setDescrizioneCodiceVersamento ( "INVALID" );
    //
    //        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    //
    //    }

    @Test
    public void shouldReturnBadRequestWithNullInputFields () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setDescrizioneCodiceVersamento ( null );

        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithEmptyInputFields () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setDescrizioneCodiceVersamento ( "" );

        assertTrue ( EntityUtils.isEmpty ( input ) );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnBadRequestWithBlankInputFields () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setDescrizioneCodiceVersamento ( "  " );

        assertTrue ( EntityUtils.isEmpty ( input ) );
        assertEquals ( Constants.RESULT_CODES.BAD_REQUEST, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnOKWithInputCorrect () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setDescrizioneCodiceVersamento ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNoResultsWithMissingInputFields () {
        CallerInputDto caller = TestHelper.getCallerAdmin ();

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( caller );
        input.setDescrizioneCodiceVersamento ( "INVALID" );

        assertEquals ( new Integer ( 0 ), call ( input ).getNumeroRisultatiTotali () );
    }

    @Test
    public void shouldReturnAllResultsByDirectDescrizioneCodiceVersamento () {

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        for ( RiferimentoContabile dto: repo.findAll () ) {

            input.setDescrizioneCodiceVersamento ( dto.getCodiceVersamento ().getDescrizione () );

            RicercaRiferimentoContabileOutput output = call ( input );
            assertTrue ( output.getNumeroRisultatiTotali () > 0 );

            RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto outputDto = output.getRisultati ().get ( 0 );
            assertEquals ( dto.getCodiceVersamento ().getDescrizione (), outputDto.getDescrizione () );
        }

    }

    @Test
    public void shouldReturnAllResultsByDirectFields () {

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        for ( RiferimentoContabile dto: repo.findAll () ) {

            input.setDescrizioneCodiceVersamento ( dto.getCodiceVersamento ().getDescrizione () );
            input.setCodiceMacrotipo ( dto.getCodiceVersamento ().getVoceEntrata () != null
                            ? dto.getCodiceVersamento ().getVoceEntrata ().getMacrotipo ().getCodice ()
                            : dto.getCodiceVersamento ().getCodiceVersamentoPadre ().getVoceEntrata ().getMacrotipo ().getCodice () );
            input.setCodiceTematica ( dto.getCodiceVersamento ().getVoceEntrata () != null
                            ? dto.getCodiceVersamento ().getVoceEntrata ().getTematica ().getCodice ()
                            : dto.getCodiceVersamento ().getCodiceVersamentoPadre ().getVoceEntrata ().getTematica ().getCodice () );

            if ( dto.getDataFineValidita () == null ) {
                input.setSoloRiferimentiInVita ( true );
            } else {
                input.setSoloRiferimentiInVita ( false );
            }

            RicercaRiferimentoContabileOutput output = call ( input );
            assertTrue ( output.getNumeroRisultatiTotali () > 0 );

            RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto outputDto = output.getRisultati ().get ( 0 );
            assertEquals ( dto.getCodiceVersamento ().getDescrizione (), outputDto.getDescrizione () );
        }

    }

    @Test
    public void shouldReturnAllResultsByDirectFieldsMithMixedCase () {

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        for ( RiferimentoContabile dto: repo.findAll () ) {

            input.setDescrizioneCodiceVersamento ( dto.getCodiceVersamento ().getDescrizione ().toLowerCase () );
            RicercaRiferimentoContabileOutput output = call ( input );
            assertTrue ( output.getNumeroRisultatiTotali () > 0 );
            assertEquals ( dto.getCodiceVersamento ().getDescrizione (), output.getRisultati ().get ( 0 ).getDescrizione () );

            input.setDescrizioneCodiceVersamento ( dto.getCodiceVersamento ().getDescrizione ().toUpperCase () );
            output = call ( input );
            assertTrue ( output.getNumeroRisultatiTotali () > 0 );
            assertEquals ( dto.getCodiceVersamento ().getDescrizione (), output.getRisultati ().get ( 0 ).getDescrizione () );
        }

    }

    @Test
    public void shouldFilterByDescrizioneCodiceVersamento () {

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        for ( RiferimentoContabile dto: repo.findAll () ) {
            input.setDescrizioneCodiceVersamento ( dto.getCodiceVersamento ().getDescrizione () );
            RicercaRiferimentoContabileOutput output = call ( input );
            assertTrue ( output.getNumeroRisultatiTotali () > 0 );
            for ( RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto outputDto: output.getRisultati () ) {
                assertEquals ( dto.getCodiceVersamento ().getDescrizione (), outputDto.getDescrizione () );
            }
        }
    }

    @Test
    public void shouldFilterByCodiceTematica () {

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        for ( RiferimentoContabile dto: repo.findAll () ) {
            input.setCodiceTematica (
                dto.getCodiceVersamento ().getVoceEntrata () != null ? dto.getCodiceVersamento ().getVoceEntrata ().getTematica ().getCodice ()
                                : dto.getCodiceVersamento ().getCodiceVersamentoPadre ().getVoceEntrata ().getTematica ().getCodice () );
            RicercaRiferimentoContabileOutput output = call ( input );
            assertTrue ( output.getNumeroRisultatiTotali () > 0 );

            for ( RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto outputDto: output.getRisultati () ) {
                assertEquals ( input.getCodiceTematica (), outputDto.getCodiceTematica () );
            }
        }
    }

    @Test
    public void shouldFilterByCodiceMacrotipo () {

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        for ( RiferimentoContabile dto: repo.findAll () ) {
            input.setCodiceMacrotipo (
                dto.getCodiceVersamento ().getVoceEntrata () != null ? dto.getCodiceVersamento ().getVoceEntrata ().getMacrotipo ().getCodice ()
                                : dto.getCodiceVersamento ().getCodiceVersamentoPadre ().getVoceEntrata ().getMacrotipo ().getCodice () );
            RicercaRiferimentoContabileOutput output = call ( input );
            assertTrue ( output.getNumeroRisultatiTotali () > 0 );
            for ( RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto outputDto: output.getRisultati () ) {
                assertEquals ( input.getCodiceMacrotipo (), outputDto.getCodiceMacrotipo () );
            }
        }
    }

    @Test
    public void shouldFilterByCodiceVoceEntrata () {

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        for ( RiferimentoContabile dto: repo.findAll () ) {
            input.setCodiceVoceEntrata ( dto.getCodiceVersamento ().getVoceEntrata ().getCodice () );
            RicercaRiferimentoContabileOutput output = call ( input );
            assertTrue ( output.getNumeroRisultatiTotali () > 0 );
            for ( RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto outputDto: output.getRisultati () ) {
                assertEquals ( input.getCodiceVoceEntrata (), outputDto.getCodiceVoceEntrata () );
            }
        }
    }

    @Test
    public void shouldFilterByVisibilitaSuTematica () {

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        Ente ente = enteRepository.findOneByCodiceFiscale ( input.getCaller ().getPrincipal ().getCodiceEnte () );
        Utente utente = utenteRepository.findOneByCodiceFiscale ( input.getCaller ().getPrincipal ().getCodiceFiscale () );

        for ( RiferimentoContabile dto: repo.findAll () ) {
            if ( EntityUtils.isTrue ( dto.getFlagAnnullato () ) ) {
                continue;
            }

            associazioneUtenteCodiceVersamentoRepository.deleteAll ();
            associazioneUtenteTematicaRepository.deleteAll ();

            input.setIdCodiceVersamento ( dto.getCodiceVersamento ().getId () );

            assertTrue ( "Non deve ritornare risultati", call ( input ).getNumeroRisultatiTotali () == 0 );

            // aggiungo visibilita' parziale su tematica ma non sul CV
            AssociazioneUtenteTematica associazioneTematica = new AssociazioneUtenteTematica ();
            AssociazioneUtenteTematicaPK associazioneTematicaPK = new AssociazioneUtenteTematicaPK ();
            associazioneTematica.setId ( associazioneTematicaPK );
            associazioneTematicaPK.setIdEnte ( ente.getId ().intValue () );
            associazioneTematicaPK.setIdUtente ( utente.getId () );
            associazioneTematicaPK.setCodTematica ( dto.getCodiceVersamento ().getVoceEntrata ().getTematica ().getCodice () );
            associazioneTematica.setFlagVisibilitaTotale ( false );
            associazioneTematica.setTematica ( tematicaRepository.findOneByCodice ( associazioneTematicaPK.getCodTematica () ) );
            associazioneUtenteTematicaRepository.save ( associazioneTematica );

            assertTrue ( "Non deve ritornare risultati", call ( input ).getNumeroRisultatiTotali () == 0 );

            associazioneTematica.setFlagVisibilitaTotale ( true );
            associazioneUtenteTematicaRepository.save ( associazioneTematica );

            assertTrue ( "Deve ritornare risultati", call ( input ).getNumeroRisultatiTotali () > 0 );
        }
    }

    @Test
    public void shouldFilterByVisibilitaSuCodiceVersamento () {

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        Ente ente = enteRepository.findOneByCodiceFiscale ( input.getCaller ().getPrincipal ().getCodiceEnte () );
        Utente utente = utenteRepository.findOneByCodiceFiscale ( input.getCaller ().getPrincipal ().getCodiceFiscale () );

        for ( RiferimentoContabile dto: repo.findAll () ) {
            if ( EntityUtils.isTrue ( dto.getFlagAnnullato () ) ) {
                continue;
            }

            associazioneUtenteCodiceVersamentoRepository.deleteAll ();
            associazioneUtenteTematicaRepository.deleteAll ();

            input.setIdCodiceVersamento ( dto.getCodiceVersamento ().getId () );

            assertTrue ( "Non deve ritornare risultati", call ( input ).getNumeroRisultatiTotali () == 0 );

            // aggiungo visibilita' parziale su tematica ma non sul CV
            AssociazioneUtenteTematica associazioneTematica = new AssociazioneUtenteTematica ();
            AssociazioneUtenteTematicaPK associazioneTematicaPK = new AssociazioneUtenteTematicaPK ();
            associazioneTematica.setId ( associazioneTematicaPK );
            associazioneTematicaPK.setIdEnte ( ente.getId ().intValue () );
            associazioneTematicaPK.setIdUtente ( utente.getId () );
            associazioneTematicaPK.setCodTematica ( dto.getCodiceVersamento ().getVoceEntrata ().getTematica ().getCodice () );
            associazioneTematica.setFlagVisibilitaTotale ( false );
            associazioneTematica.setTematica ( tematicaRepository.findOneByCodice ( associazioneTematicaPK.getCodTematica () ) );
            associazioneUtenteTematicaRepository.save ( associazioneTematica );

            assertTrue ( "Non deve ritornare risultati", call ( input ).getNumeroRisultatiTotali () == 0 );

            AssociazioneUtenteCodiceVersamento associazioneCodiceVersamento = new AssociazioneUtenteCodiceVersamento ();
            AssociazioneUtenteCodiceVersamentoPK associazioneCodiceVersamentoPK = new AssociazioneUtenteCodiceVersamentoPK ();
            associazioneCodiceVersamento.setId ( associazioneCodiceVersamentoPK );
            associazioneCodiceVersamentoPK.setIdEnte ( ente.getId ().intValue () );
            associazioneCodiceVersamentoPK.setIdUtente ( utente.getId () );
            associazioneCodiceVersamentoPK.setIdCodiceVersamento ( dto.getCodiceVersamento ().getId ().intValue () );
            associazioneCodiceVersamento
                .setCodiceVersamento ( codiceVersamentoRepository.findOne ( associazioneCodiceVersamentoPK.getIdCodiceVersamento ().longValue () ) );

            associazioneUtenteCodiceVersamentoRepository.save ( associazioneCodiceVersamento );

            assertTrue ( "Deve ritornare risultati", call ( input ).getNumeroRisultatiTotali () > 0 );
        }
    }

    @Test
    public void shouldReturnAllMandatoryFields () {

        RicercaRiferimentoContabileInput input = new RicercaRiferimentoContabileInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );

        assertTrue ( repo.count () > 0 );

        for ( RiferimentoContabile dto: repo.findAll () ) {

            input.setDescrizioneCodiceVersamento ( dto.getCodiceVersamento ().getDescrizione () );

            RicercaRiferimentoContabileOutput output = call ( input );
            assertTrue ( output.getNumeroRisultatiTotali () > 0 );

            RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto o = null;
            for ( RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto risultato: output.getRisultati () ) {
                if ( risultato.getId ().equals ( dto.getId () ) ) {
                    o = risultato;
                    break;
                }
            }

            assertNotNull ( o );

            assertEquals ( dto.getId (), o.getId () );

            assertEquals ( dto.getCodiceVersamento ().getDescrizione (), o.getDescrizione () );

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
                "descrizioneTematica - codiceVersamento.voceEntrata.tematica.descrizione" );
        }

    }

}
