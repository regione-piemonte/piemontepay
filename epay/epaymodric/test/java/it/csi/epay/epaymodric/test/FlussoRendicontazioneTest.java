/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import it.csi.epay.epaymodric.business.epaymodric.manager.FlussoRendicontazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.repository.EnteRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.FlussoOrigineRepository;
import it.csi.epay.epaymodric.config.PersistenceJPAConfigUnitTest;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException;


@PersistenceContext ( )
@ContextConfiguration ( classes = { PersistenceJPAConfigUnitTest.class }, loader = AnnotationConfigContextLoader.class )
public class FlussoRendicontazioneTest extends AbstractTransactionalJUnit4SpringContextTests {

    private final Logger logger = Logger.getLogger ( this.getClass () );

    private static final String IDENTIFICATIVO_FLUSSO = "2018-05-18BCITITMM-0000000034";

    private static final String ID_ENTE = "0001";

    @Autowired
    private FlussoRendicontazioneManager flussoRendicontazioneService;

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private FlussoOrigineRepository flussoOrigineRepository;

    @Test
    public void eseguiSpacchettaSingoloFlusso () throws Exception {
        logger.info ( "FlussoRendicontazioneTest.eseguiSpacchettaSingoloFlusso : INIZIO" );

        CblTEnte ente = enteRepository.findByIdEnte ( ID_ENTE );
        List<CblTFlussoOrigine> flussoOrigine
            = flussoOrigineRepository.findByIdentificativoFlussoAndIdentificativoIstitutoRicevente ( IDENTIFICATIVO_FLUSSO, ente.getCodiceFiscale () );

        assertTrue ( "Flusso origine presente", flussoOrigine.size () > 0 );

        flussoRendicontazioneService.spacchettaSingoloFlussoUndo ( ente, flussoOrigine.get ( 0 ) );

        try {
            flussoRendicontazioneService.spacchettaSingoloFlussoConTestBusiness ( ente, flussoOrigine.get ( 0 ) );
        } catch ( EpaymodricException e ) {
            logger.error ( "Errore in spacchettamento del flusso", e );
        }

        //TEST Numero record di dettaglio inseriti
        /*
         * CblDStatoFlusso cblDStatoFlussoPre2 = statoFlussoRepository.findByCodiceStato ( StatoFlussoEnum.BOZZA.getCodice () ); List<CblTFlussoSintesi>
         * cblTFlussoSintesi = flussoSintesiRepository.findByCblTFlussoOrigineIdentificativoFlussoAndCblTEnteAndCblTFlussoOrigineCblDStatoFlusso (
         * flussoOrigine.get ( 0 ).getIdentificativoFlusso (), ente, cblDStatoFlussoPre2 ); List<CblTFlussoDettaglio> cblTFlussoDettaglio =
         * flussoDettaglioRepository.findByCblTFlussoSintesiIn ( cblTFlussoSintesi ); assertTrue ( "Dettagli trovati : " + cblTFlussoDettaglio.size () ,
         * cblTFlussoDettaglio.size () == 81 ); flussoRendicontazioneService.spacchettaSingoloFlussoUndo ( ente, flussoOrigine.get ( 0 ) );
         */

        logger.info ( "FlussoRendicontazioneTest.eseguiSpacchettaSingoloFlusso : FINE" );
    }

}
