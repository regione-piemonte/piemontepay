/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.service.PropagazioneExecutorService;
import it.csi.epay.epaypacatalogsrv.business.service.PropagazioneService;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneCommitDTO;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneDTO;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneDatiDTO;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneRollbackDTO;
import it.csi.epay.epaypacatalogsrv.dto.enums.AzioneDaPropagare;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoCommitFruitore;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoInvioFruitore;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoPropagazione;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoRollbackFruitore;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.config.TestConstants;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class PropagazioneServiceInMemoryTest extends ParentOperationTest {

    @Autowired
    private PropagazioneService propagazioneService;

    @Autowired
    private EnteRepository enteRepository;

    protected Ente getEnteDaPropagare () {
        return enteRepository.findOne ( TestConstants.ID_ENTE_VALIDO );
    }

    @Before
    public void beforeWso2Test () {
        setConfigurazione ( "WSO2_ENABLE", "true" );
    }

    @After
    public void afterWso2Test () {
        setConfigurazione ( "WSO2_ENABLE", "false" );
    }

    @Test
    public void testServiceOK () throws Exception {

        log ( "TEST - TUTTO OK" );

        PropagazioneExecutorService propagazioneExecutorServiceMock = Mockito.mock ( PropagazioneExecutorService.class );

        doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "Tutto bene, mi piace" ) )
            .doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "Tutto bene, mi piace" ) )
            .doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "Tutto bene, mi piace" ) )
            .when ( propagazioneExecutorServiceMock ).propagaEnte ( any (), any (), any (), any (), any (), any (), any () );

        doReturn ( new EsitoPropagazioneCommitDTO ( EsitoCommitFruitore.COMPLETED, "Tutto bene, committato bene, grazie per l'interesse" ) )
            .doReturn ( new EsitoPropagazioneCommitDTO ( EsitoCommitFruitore.COMPLETED, "Tutto bene, committato bene, grazie per l'interesse" ) )
            .doReturn ( new EsitoPropagazioneCommitDTO ( EsitoCommitFruitore.COMPLETED, "Tutto bene, committato bene, grazie per l'interesse" ) )
            .when ( propagazioneExecutorServiceMock )
            .propagaCommit ( any (), any (), any () );

        doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .when ( propagazioneExecutorServiceMock )
            .propagaRollback ( any (), any (), any () );

        ReflectionTestUtils.setField ( propagazioneService, "propagazioneExecutorService", propagazioneExecutorServiceMock );

        EsitoPropagazioneDTO esito = propagazioneService.propagaEnte ( getEnteDaPropagare (), AzioneDaPropagare.MODIFICA, any (), any () );

        assertEquals ( EsitoPropagazione.OK, esito.getEsito () );
    }

    @Test
    public void testServiceFailPhase1Sub1 () throws Exception {

        PropagazioneExecutorService propagazioneExecutorServiceMock = Mockito.mock ( PropagazioneExecutorService.class );

        log ( "TEST - FAIL FASE 1 FRUITORE 1" );

        doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.FAILED, "404 NOT FOUND" ) )
            .doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "completato con successo" ) )
            .doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "completato con successo" ) )
            .when ( propagazioneExecutorServiceMock ).propagaEnte ( any (), any (), any (), any (), any (), any (), any () );

        doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .when ( propagazioneExecutorServiceMock )
            .propagaCommit ( any (), any (), any () );

        doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .when ( propagazioneExecutorServiceMock )
            .propagaRollback ( any (), any (), any () );

        ReflectionTestUtils.setField ( propagazioneService, "propagazioneExecutorService", propagazioneExecutorServiceMock );

        EsitoPropagazioneDTO esito = propagazioneService.propagaEnte ( getEnteDaPropagare (), AzioneDaPropagare.MODIFICA, any (), any () );

        assertEquals ( EsitoPropagazione.KO, esito.getEsito () );
    }

    @Test
    public void testServiceFailPhase1Sub2 () throws Exception {

        PropagazioneExecutorService propagazioneExecutorServiceMock = Mockito.mock ( PropagazioneExecutorService.class );

        log ( "TEST - FAIL FASE 1 FRUITORE 2" );

        doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "completato con successo" ) )
            .doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.FAILED, "404 NOT FOUND" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .when ( propagazioneExecutorServiceMock ).propagaEnte ( any (), any (), any (), any (), any (), any (), any () );

        doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .when ( propagazioneExecutorServiceMock )
            .propagaCommit ( any (), any (), any () );

        doReturn ( new EsitoPropagazioneRollbackDTO ( EsitoRollbackFruitore.COMPLETED, "rollbackato con successo" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .when ( propagazioneExecutorServiceMock )
            .propagaRollback ( any (), any (), any () );

        ReflectionTestUtils.setField ( propagazioneService, "propagazioneExecutorService", propagazioneExecutorServiceMock );

        EsitoPropagazioneDTO esito = propagazioneService.propagaEnte ( getEnteDaPropagare (), AzioneDaPropagare.MODIFICA, any (), any () );

        assertEquals ( EsitoPropagazione.KO, esito.getEsito () );
    }

    @Test
    public void testServiceFailPhase1Sub3 () throws Exception {

        PropagazioneExecutorService propagazioneExecutorServiceMock = Mockito.mock ( PropagazioneExecutorService.class );

        log ( "TEST - FAIL FASE 1 FRUITORE 3" );

        doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "completato con successo" ) )
            .doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "completato con successo" ) )
            .doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.FAILED, "404 NOT FOUND" ) )
            .when ( propagazioneExecutorServiceMock ).propagaEnte ( any (), any (), any (), any (), any (), any (), any () );

        doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .when ( propagazioneExecutorServiceMock )
            .propagaCommit ( any (), any (), any () );

        doReturn ( new EsitoPropagazioneRollbackDTO ( EsitoRollbackFruitore.COMPLETED, "rollbackato con successo" ) )
            .doReturn ( new EsitoPropagazioneRollbackDTO ( EsitoRollbackFruitore.COMPLETED, "rollbackato con successo" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .when ( propagazioneExecutorServiceMock )
            .propagaRollback ( any (), any (), any () );

        ReflectionTestUtils.setField ( propagazioneService, "propagazioneExecutorService", propagazioneExecutorServiceMock );

        EsitoPropagazioneDTO esito = propagazioneService.propagaEnte ( getEnteDaPropagare (), AzioneDaPropagare.MODIFICA, any (), any () );

        assertEquals ( EsitoPropagazione.KO, esito.getEsito () );
    }

    @Test
    public void testServiceFailPhase1Sub2FailRollback1 () throws Exception {

        PropagazioneExecutorService propagazioneExecutorServiceMock = Mockito.mock ( PropagazioneExecutorService.class );

        log ( "TEST - FAIL FASE 1 FRUITORE 2 FAIL ROLLBACK 1" );

        doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "completato con successo" ) )
            .doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.FAILED, "404 NOT FOUND" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .when ( propagazioneExecutorServiceMock ).propagaEnte ( any (), any (), any (), any (), any (), any (), any () );

        doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .when ( propagazioneExecutorServiceMock )
            .propagaCommit ( any (), any (), any () );

        doReturn ( new EsitoPropagazioneRollbackDTO ( EsitoRollbackFruitore.FAILED, "503 SOME RANDOM ERROR" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .when ( propagazioneExecutorServiceMock )
            .propagaRollback ( any (), any (), any () );

        ReflectionTestUtils.setField ( propagazioneService, "propagazioneExecutorService", propagazioneExecutorServiceMock );

        EsitoPropagazioneDTO esito = propagazioneService.propagaEnte ( getEnteDaPropagare (), AzioneDaPropagare.MODIFICA, any (), any () );

        assertEquals ( EsitoPropagazione.KO, esito.getEsito () );
    }

    @Test
    public void testServiceFailPhase1Sub3FailRollback1and2 () throws Exception {

        PropagazioneExecutorService propagazioneExecutorServiceMock = Mockito.mock ( PropagazioneExecutorService.class );

        log ( "TEST - FAIL FASE 1 FRUITORE 3 FAIL ROLLBACK 1,2" );

        doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "completato con successo" ) )
            .doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "completato con successo" ) )
            .doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.FAILED, "404 NOT FOUND" ) )
            .when ( propagazioneExecutorServiceMock ).propagaEnte ( any (), any (), any (), any (), any (), any (), any () );

        doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .when ( propagazioneExecutorServiceMock )
            .propagaCommit ( any (), any (), any () );

        doReturn ( new EsitoPropagazioneRollbackDTO ( EsitoRollbackFruitore.FAILED, "503 SOME RANDOM ERROR" ) )
            .doReturn ( new EsitoPropagazioneRollbackDTO ( EsitoRollbackFruitore.REJECTED, "TOO LATE" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .when ( propagazioneExecutorServiceMock )
            .propagaRollback ( any (), any (), any () );

        ReflectionTestUtils.setField ( propagazioneService, "propagazioneExecutorService", propagazioneExecutorServiceMock );

        EsitoPropagazioneDTO esito = propagazioneService.propagaEnte ( getEnteDaPropagare (), AzioneDaPropagare.MODIFICA, any (), any () );

        assertEquals ( EsitoPropagazione.KO, esito.getEsito () );
    }

    @Test
    public void testServiceFailPhase1Sub3FailRollback2 () throws Exception {

        PropagazioneExecutorService propagazioneExecutorServiceMock = Mockito.mock ( PropagazioneExecutorService.class );

        log ( "TEST - FAIL FASE 1 FRUITORE 3 FAIL ROLLBACK 2" );

        doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "completato con successo" ) )
            .doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "completato con successo" ) )
            .doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.FAILED, "404 NOT FOUND" ) )
            .when ( propagazioneExecutorServiceMock ).propagaEnte ( any (), any (), any (), any (), any (), any (), any () );

        doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .when ( propagazioneExecutorServiceMock )
            .propagaCommit ( any (), any (), any () );

        doReturn ( new EsitoPropagazioneRollbackDTO ( EsitoRollbackFruitore.COMPLETED, "rolled back" ) )
            .doReturn ( new EsitoPropagazioneRollbackDTO ( EsitoRollbackFruitore.REJECTED, "I DON'T WANT" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .when ( propagazioneExecutorServiceMock )
            .propagaRollback ( any (), any (), any () );

        ReflectionTestUtils.setField ( propagazioneService, "propagazioneExecutorService", propagazioneExecutorServiceMock );

        EsitoPropagazioneDTO esito = propagazioneService.propagaEnte ( getEnteDaPropagare (), AzioneDaPropagare.MODIFICA, any (), any () );

        assertEquals ( EsitoPropagazione.KO, esito.getEsito () );
    }

    @Test
    public void testServiceFailPhase2Sub1 () throws Exception {

        PropagazioneExecutorService propagazioneExecutorServiceMock = Mockito.mock ( PropagazioneExecutorService.class );

        log ( "TEST - FAIL FASE 2 FRUITORE 1" );

        doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "completato con successo" ) )
            .doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "completato con successo" ) )
            .doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "completato con successo" ) )
            .when ( propagazioneExecutorServiceMock ).propagaEnte ( any (), any (), any (), any (), any (), any (), any () );

        doReturn ( new EsitoPropagazioneCommitDTO ( EsitoCommitFruitore.REJECTED, "DATABASE IS ON FIRE" ) )
            .doReturn ( new EsitoPropagazioneCommitDTO ( EsitoCommitFruitore.COMPLETED, "Operazione committata con successo" ) )
            .doReturn ( new EsitoPropagazioneCommitDTO ( EsitoCommitFruitore.COMPLETED, "Operazione committata con successo" ) )
            .when ( propagazioneExecutorServiceMock )
            .propagaCommit ( any (), any (), any () );

        doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .when ( propagazioneExecutorServiceMock )
            .propagaRollback ( any (), any (), any () );

        ReflectionTestUtils.setField ( propagazioneService, "propagazioneExecutorService", propagazioneExecutorServiceMock );

        EsitoPropagazioneDTO esito = propagazioneService.propagaEnte ( getEnteDaPropagare (), AzioneDaPropagare.MODIFICA, any (), any () );

        assertEquals ( EsitoPropagazione.OK, esito.getEsito () );
    }

    @Test
    public void testServiceFailPhase2Sub1and2 () throws Exception {

        PropagazioneExecutorService propagazioneExecutorServiceMock = Mockito.mock ( PropagazioneExecutorService.class );

        log ( "TEST - FAIL FASE 2 FRUITORE 1,2" );

        doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "completato con successo" ) )
            .doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "completato con successo" ) )
            .doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "completato con successo" ) )
            .when ( propagazioneExecutorServiceMock ).propagaEnte ( any (), any (), any (), any (), any (), any (), any () );

        doReturn ( new EsitoPropagazioneCommitDTO ( EsitoCommitFruitore.REJECTED, "DATABASE IS ON FIRE" ) )
            .doReturn ( new EsitoPropagazioneCommitDTO ( EsitoCommitFruitore.REJECTED, "DATABASE IS GONE" ) )
            .doReturn ( new EsitoPropagazioneCommitDTO ( EsitoCommitFruitore.COMPLETED, "Operazione committata con successo" ) )
            .when ( propagazioneExecutorServiceMock )
            .propagaCommit ( any (), any (), any () );

        doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .when ( propagazioneExecutorServiceMock )
            .propagaRollback ( any (), any (), any () );

        ReflectionTestUtils.setField ( propagazioneService, "propagazioneExecutorService", propagazioneExecutorServiceMock );

        EsitoPropagazioneDTO esito = propagazioneService.propagaEnte ( getEnteDaPropagare (), AzioneDaPropagare.MODIFICA, any (), any () );

        assertEquals ( EsitoPropagazione.OK, esito.getEsito () );
    }

    @Test
    public void testServiceFailPhase2Sub1and2and3 () throws Exception {

        PropagazioneExecutorService propagazioneExecutorServiceMock = Mockito.mock ( PropagazioneExecutorService.class );

        log ( "TEST - FAIL FASE 2 FRUITORE 1,2,3" );

        doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "completato con successo" ) )
            .doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "completato con successo" ) )
            .doReturn ( new EsitoPropagazioneDatiDTO ( EsitoInvioFruitore.COMPLETED, "completato con successo" ) )
            .when ( propagazioneExecutorServiceMock ).propagaEnte ( any (), any (), any (), any (), any (), any (), any () );

        doReturn ( new EsitoPropagazioneCommitDTO ( EsitoCommitFruitore.REJECTED, "DATABASE IS ON FIRE" ) )
            .doReturn ( new EsitoPropagazioneCommitDTO ( EsitoCommitFruitore.REJECTED, "DATABASE IS GONE" ) )
            .doReturn ( new EsitoPropagazioneCommitDTO ( EsitoCommitFruitore.REJECTED, "DATABASE IS SICK" ) )
            .when ( propagazioneExecutorServiceMock )
            .propagaCommit ( any (), any (), any () );

        doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .doThrow ( new RuntimeException ( "SHOULD NOT CALL THIS" ) )
            .when ( propagazioneExecutorServiceMock )
            .propagaRollback ( any (), any (), any () );

        ReflectionTestUtils.setField ( propagazioneService, "propagazioneExecutorService", propagazioneExecutorServiceMock );

        EsitoPropagazioneDTO esito = propagazioneService.propagaEnte ( getEnteDaPropagare (), AzioneDaPropagare.MODIFICA, any (), any () );

        assertEquals ( EsitoPropagazione.OK, esito.getEsito () );
    }

}
