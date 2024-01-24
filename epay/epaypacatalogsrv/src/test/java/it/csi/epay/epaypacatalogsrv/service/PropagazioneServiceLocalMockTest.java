/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.service.PropagazioneService;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneDTO;
import it.csi.epay.epaypacatalogsrv.dto.enums.AzioneDaPropagare;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoPropagazione;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class PropagazioneServiceLocalMockTest extends ParentOperationTest {

    @Autowired
    private PropagazioneService propagazioneService;

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private CodiceVersamentoRepository codiceVersamentoRepository;

    @Autowired
    private RiferimentoContabileRepository riferimentoContabileRepository;

    @After
    public void afterWso2Test () {
        setConfigurazione ( "WSO2_ENABLE", "false" );
    }

    @Before
    public void beforeWso2Test () {
        setConfigurazione ( "WSO2_ENABLE", "true" );
    }

    protected Ente getEnteDaPropagare () {
        return enteRepository.findOneByCodiceFiscale ( "80087670016" );
    }

    @Test
    public void testServiceInserisciCodiceVersamentoInserisciLocalMock () throws Exception {
        Ente ente = getEnteDaPropagare ();
        List<CodiceVersamento> listcodice = codiceVersamentoRepository.findByEnte_Id ( ente.getId (), new Sort ( "id" ) );
        for ( CodiceVersamento codiceVersamento: listcodice ) {
            //            EsitoPropagazioneDTO esito = propagazioneService.propagaCodiceVersamento ( codiceVersamento, AzioneDaPropagare.INSERIMENTO );
            //            assertEquals ("Errore: propagaCodiceVersamento1", EsitoPropagazione.OK, esito.getEsito () );
            //
            //            EsitoPropagazioneDTO esitoMod = propagazioneService.propagaCodiceVersamento ( codiceVersamento, AzioneDaPropagare.MODIFICA );
            //            assertEquals ("Errore: propagaCodiceVersamento2", EsitoPropagazione.OK, esitoMod.getEsito () );
            
            List<RiferimentoContabile> riferimentoContabiles
                = riferimentoContabileRepository.findByCodiceVersamento_IdOrderByDataInizioValiditaDesc ( codiceVersamento.getId () );
            for ( RiferimentoContabile riferimentoContabile: riferimentoContabiles ) {
                EsitoPropagazioneDTO esitoPropIns = propagazioneService.propagaRiferimentoContabile ( riferimentoContabile, AzioneDaPropagare.INSERIMENTO );
                assertEquals ( "Errore: propagaRiferimentoContabile", EsitoPropagazione.OK, esitoPropIns.getEsito () );
                EsitoPropagazioneDTO esitoPropUpd = propagazioneService.propagaRiferimentoContabile ( riferimentoContabile, AzioneDaPropagare.MODIFICA );
                assertEquals ( "Errore: propagaRiferimentoContabile", EsitoPropagazione.OK, esitoPropUpd.getEsito () );
            }
        }
        

    }

    //    @Test
    //    public void testServiceInserisciCodiceVersamentoInserisciLocalMock () throws Exception {
    //        Ente ente = getEnteDaPropagare ();
    //        List<CodiceVersamento> listcodice = codiceVersamentoRepository.findByEnte_Id ( ente.getId (), new Sort ( "id" ) );
    //        for ( CodiceVersamento codiceVersamento: listcodice ) {
    //            EsitoPropagazioneDTO esito = propagazioneService.propagaCodiceVersamento ( codiceVersamento, AzioneDaPropagare.INSERIMENTO );
    //            assertEquals ("Errore: propagaCodiceVersamento1", EsitoPropagazione.OK, esito.getEsito () );
    //            EsitoPropagazioneDTO esitoMod = propagazioneService.propagaCodiceVersamento ( codiceVersamento, AzioneDaPropagare.MODIFICA );
    //            assertEquals ("Errore: propagaCodiceVersamento2", EsitoPropagazione.OK, esitoMod.getEsito () );
    //
    //            List<RiferimentoContabile> riferimentoContabiles
    //                = riferimentoContabileRepository.findByCodiceVersamento_IdOrderByDataInizioValiditaDesc ( codiceVersamento.getId () );
    //            for ( RiferimentoContabile riferimentoContabile: riferimentoContabiles ) {
    //                EsitoPropagazioneDTO esitoProp = propagazioneService.propagaRiferimentoContabile ( riferimentoContabile, AzioneDaPropagare.INSERIMENTO );
    //                assertEquals ("Errore: propagaRiferimentoContabile", EsitoPropagazione.OK, esitoProp.getEsito () );
    //            }
    //        }
    //
    //
    //    }

    //    @Test
    //    public void testServiceLocalMock () throws Exception {
    //
    //        for ( int i = 0; i < 1; i++ ) {
    //            log ( "TEST - LOCAL MOCK" );
    //
    //            EsitoPropagazioneDTO esito = propagazioneService.propagaEnte (
    //                getEnteDaPropagare (),
    //                AzioneDaPropagare.MODIFICA );
    //
    //            Thread.sleep ( 1 );
    //        }
    //    }

}
