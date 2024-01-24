/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaysim.business.epaysim.EpaysimulatorDatawsBusiness;
import it.csi.epay.epaysim.business.epaysim.EpaysimulatorwsBusiness;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoDettaglioPagopa;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoOrigineErrore;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoOriginePagopa;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoSintesiPagopa;
import it.csi.epay.epaysim.business.epaysim.repository.SimTFlussoOrigineErroreRepository;
import it.csi.epay.epaysim.business.epaysim.repository.SimTFlussoOriginePagopaRepository;
import it.csi.epay.epaysim.business.epaysim.utils.Costanti;
import it.csi.epay.epaysim.dto.CallerInputDTO;
import it.csi.epay.epaysim.dto.PrincipalInputDTO;
import it.csi.epay.epaysim.dto.RicercaFlussoInputDTO;
import it.csi.epay.epaysim.dto.RicercaFlussoOutputResponse;
import it.csi.epay.epaysim.dto.UpdateEsitoStatoElaborazioneFlussoInputDTO;
import it.csi.epay.epaysim.dto.UpdateEsitoStatoElaborazioneFlussoOutputDTO;
import it.csi.epay.epaysim.interfacews.epaysim.CostantiErrori;
import it.csi.epay.epaysim.test.config.EpaysimulatorUnitTestConfigPostgre;
import it.csi.epay.epaysim.test.model.ParentUnitTest;
import it.csi.epay.epaysim.util.LogConfig;


/**
 *
 */

@Transactional
@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaysimulatorUnitTestConfigPostgre.class } )
public class FlussiManagerTest extends ParentUnitTest {

    private static final Logger LOGGER = Logger.getLogger ( LogConfig.HANDLER_TEST );

    private static final String CF_ENTE = "00429440068";

    private static final String ID_MESSAGGIO = "2018-08-31-15:14:47.275-00000001003";

    @Rule
    public ExpectedException thrown = ExpectedException.none ();

    private static final String ID_FLUSSO = "ID_FLUSSO";

    private static final int SIM_FLUSSO_ORIGINE = 0;

    private static final int SIM_FLUSSO_ERRORE = 0;

    @Autowired
    private EpaysimulatorwsBusiness cut;

    @Autowired
    private SimTFlussoOrigineErroreRepository simTFlussoOrigineErroreRepository;

    private SimTFlussoOriginePagopaRepository simTFlussoOriginePagopaRepository;

    @Autowired
    private EpaysimulatorDatawsBusiness epaysimulatorDatawsBusiness;


    private void checkCampiTecnici ( int SIM, boolean update ) {

        SimTFlussoOriginePagopa origine = simTFlussoOriginePagopaRepository.findOneByIdentificativoFlusso ( ID_FLUSSO );
        SimTFlussoOrigineErrore errore = simTFlussoOrigineErroreRepository.findOneByIdentificativoFlusso ( ID_FLUSSO );

        List<SimTFlussoSintesiPagopa> sintesi = new ArrayList<> ();
        List<SimTFlussoDettaglioPagopa> dettaglio = new ArrayList<> ();

        if ( origine != null ) {
            sintesi = origine.getSimTFlussoSintesiPagopas ();
        }

        if ( sintesi != null ) {
            for ( SimTFlussoSintesiPagopa simTFlussoSintesiPagopa: sintesi ) {
                List<SimTFlussoDettaglioPagopa> dettagli = simTFlussoSintesiPagopa.getSimTFlussoDettaglioPagopas ();

                dettaglio.addAll ( dettagli );
            }
        }

        String userIns = null;
        String userMod = null;
        Date dateIns = null;
        Date dateMod = null;
        String cfEnteRicevente = null;

        if ( ( SIM == SIM_FLUSSO_ERRORE ) && ( errore != null ) ) {
            userIns = errore.getUtenteInserimento ();
            dateIns = errore.getDataInserimento ();
            cfEnteRicevente = errore.getCfEnteRicevente ();

            if ( update )
                dateMod = errore.getDataModifica ();
            if ( update )
                userMod = errore.getUtenteModifica ();

            assertNotNull ( " Il cfEnteRicevente deve essere sempre valorizzato." + "FLUSSO ERRORE", cfEnteRicevente );
            checkCampiTecniciAssert ( "FLUSSO ERRORE", dateMod, userMod, dateIns, userIns );
        }

        if ( SIM == SIM_FLUSSO_ORIGINE ) {

            if ( origine != null ) {
                userIns = origine.getUtenteInserimento ();
                dateIns = origine.getDataInserimento ();
                //                cfEnteRicevente = origine.getCfEnteRicevente ();

                if ( update )
                    dateMod = origine.getDataModifica ();
                if ( update )
                    userMod = origine.getUtenteModifica ();

                assertNotNull ( " Il cfEnteRicevente deve essere sempre valorizzato." + "FLUSSO ORIGINE", cfEnteRicevente );
                checkCampiTecniciAssert ( "FLUSSO ORIGINE - ID " + origine.getId (), dateMod, userMod, dateIns, userIns );
            }

            if ( sintesi != null ) {
                for ( SimTFlussoSintesiPagopa simTFlussoSintesiPagopa: sintesi ) {
                    userIns = simTFlussoSintesiPagopa.getUtenteInserimento ();
                    dateIns = simTFlussoSintesiPagopa.getDataInserimento ();

                    if ( update )
                        dateMod = simTFlussoSintesiPagopa.getDataModifica ();
                    if ( update )
                        userMod = simTFlussoSintesiPagopa.getUtenteModifica ();

                    checkCampiTecniciAssert ( "FLUSSO SINTESI - ID " + simTFlussoSintesiPagopa.getId (), dateMod, userMod, dateIns, userIns );
                }
            }

            if ( dettaglio != null ) {
                for ( SimTFlussoDettaglioPagopa simTFlussoDettaglioPagopa: dettaglio ) {
                    userIns = simTFlussoDettaglioPagopa.getUtenteInserimento ();
                    dateIns = simTFlussoDettaglioPagopa.getDataInserimento ();

                    if ( update )
                        dateMod = simTFlussoDettaglioPagopa.getDataModifica ();
                    if ( update )
                        userMod = simTFlussoDettaglioPagopa.getUtenteModifica ();

                    checkCampiTecniciAssert ( "FLUSSO DETTAGLIO - ID " + simTFlussoDettaglioPagopa.getId (), dateMod, userMod, dateIns, userIns );
                }
            }

        }
    }

    private void checkCampiTecniciAssert ( String contesto, Date dateMod, String userMod, Date dateIns, String userIns ) {
        String ambito = " Contesto: " + contesto;
        assertNotNull ( " L'utente deve essere sempre valorizzato." + ambito, userIns );
        assertNotNull ( " La data inserimento deve essere sempre valorizzata." + ambito, dateIns );
        assertNotNull ( " La data modifica deve essere sempre valorizzata." + ambito, dateMod );
        assertNotNull ( " L'utente modifica deve essere sempre valorizzato." + ambito, userMod );
    }

    //Ricerca flusso START

    @Test
    public void testRicercaFlussoKO () throws Exception {
        RicercaFlussoInputDTO ricercaFlussoInputDTO = new RicercaFlussoInputDTO ();
        ricercaFlussoInputDTO.setIdentificativoFlusso ( "NO CODE" );

        RicercaFlussoOutputResponse result = epaysimulatorDatawsBusiness.ricercaFlusso ( ricercaFlussoInputDTO );

        assertNotNull ( " l'oggetto di ritorno deve essere sempre valorizzato.", result );
        assertNotNull ( " l'oggetto di ritorno Result deve essere sempre valorizzato.", result.getTestata () );
        assertNotNull ( " l'oggetto di ritorno deve avere sempre valorizzato un codice di ritorno.", result.getCodiceEsito () );
        assertTrue ( " Il codice di ritorno deve essere: " + CostantiErrori.WS_ESITO_OK_NO_RESULT,
            CostantiErrori.WS_ESITO_OK_NO_RESULT.equals ( result.getCodiceEsito () ) );

        assertNotNull ( " l'oggetto di ritorno deve essere sempre valorizzato.", result );
    }

    @Test
    public void testRicercaFlussoOK () throws Exception {
        RicercaFlussoInputDTO ricercaFlussoInputDTO = new RicercaFlussoInputDTO ();

        RicercaFlussoOutputResponse result = epaysimulatorDatawsBusiness.ricercaFlusso ( ricercaFlussoInputDTO );
        assertNotNull ( " l'oggetto di ritorno deve essere sempre valorizzato.", result );
        assertNotNull ( " l'oggetto di ritorno Result deve essere sempre valorizzato.", result.getTestata () );
        assertNotNull ( " l'oggetto di ritorno deve avere sempre valorizzato un codice di ritorno.", result.getCodiceEsito () );
        assertTrue ( " Il codice di ritorno deve essere: " + CostantiErrori.WS_ESITO_OK_DEFAULT,
            CostantiErrori.WS_ESITO_OK_DEFAULT.equals ( result.getCodiceEsito () ) );

        checkCampiTecnici ( SIM_FLUSSO_ORIGINE, false );
    }

    //Ricerca flusso END


    @Test
    public void testUpdateEsitoStatoElaborazioneFlussoOK () throws Exception {

        List<String> flussiTest = new ArrayList<> ();
        flussiTest.add ( "50" );

        UpdateEsitoStatoElaborazioneFlussoOutputDTO result
        = epaysimulatorDatawsBusiness.updateEsitoStatoElaborazioneFlusso ( prepareUpdateEsitoStatoElaborazioneFlussoInputDTO ( flussiTest ) );
        assertNotNull ( " L'oggetto di ritorno deve essere sempre valorizzato.", result );
        assertTrue ( "Il codice esito deve essere valorizzato (OK).", Costanti.RESULT_CODE_OK.equals ( result.getCodiceEsito () ) );
        assertTrue ( "Il codice stato deve essere valorizzato (403).",
            CostantiErrori.WS_ESITO_OK_DEFAULT.equals ( result.getCodiceStato ().toString () ) );

        checkCampiTecnici ( SIM_FLUSSO_ORIGINE, true );
    }

    @Test
    public void testUpdateEsitoStatoElaborazioneFlussoNotFound () throws Exception {
        List<String> flussiTest = new ArrayList<> ();
        flussiTest.add ( "51" );

        UpdateEsitoStatoElaborazioneFlussoOutputDTO result
        = epaysimulatorDatawsBusiness.updateEsitoStatoElaborazioneFlusso ( prepareUpdateEsitoStatoElaborazioneFlussoInputDTO ( flussiTest ) );
        assertNotNull ( " L'oggetto di ritorno deve essere sempre valorizzato.", result );
        assertTrue ( "Il codice esito deve essere valorizzato (RESULT_CODE_OPERATION_COMPLETED_WITH_ERROR).",
            Costanti.RESULT_CODE_OPERATION_COMPLETED_WITH_ERROR.equals ( result.getCodiceEsito () ) );
        assertTrue ( "Il codice stato deve essere valorizzato (310).",
            CostantiErrori.ELABORAZIONE_TERMINATA_CON_ERRORI.equals ( result.getCodiceStato ().toString () ) );
    }

    private UpdateEsitoStatoElaborazioneFlussoInputDTO prepareUpdateEsitoStatoElaborazioneFlussoInputDTO ( List<String> flussiOrigineList ) {
        UpdateEsitoStatoElaborazioneFlussoInputDTO ret = new UpdateEsitoStatoElaborazioneFlussoInputDTO ();
        ret.setCaller ( new CallerInputDTO ( "Test", new PrincipalInputDTO ( CF_ENTE, CF_ENTE, "TEST" ) ) );
        ret.setIdentificativiFlussoOrigineList ( flussiOrigineList );
        ret.setNuovoStato ( Boolean.TRUE );
        return ret;
    }





}
