/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

//import it.csi.epay.epaymodric.business.epaymodric.manager.service.MotoreDiRiconciliazioneService;
import it.csi.epay.epaymodric.config.PersistenceJPAConfigUnitTest;


@PersistenceContext ( )
@ContextConfiguration ( classes = { PersistenceJPAConfigUnitTest.class }, loader = AnnotationConfigContextLoader.class )
public class MotoreDiRiconciliazioneTest extends AbstractTransactionalJUnit4SpringContextTests {

    private static final String IDENTIFICATIVO_FLUSSO = "2018-05-18BCITITMM-0000000034";

//    private static final String ID_ENTE = "0001";

//    @Autowired
//    MotoreDiRiconciliazioneService motoreDiRiconciliazioneService;

    @Test
    public void eseguiSingoloFlusso () {
        try {
            List<String> listaIdentificativiFlusso = new ArrayList<String> ();
            listaIdentificativiFlusso.add ( IDENTIFICATIVO_FLUSSO );
//            motoreDiRiconciliazioneService.eseguiRiconciliazione ( true, ID_ENTE, listaIdentificativiFlusso );
            System.out.println ( "eseguiSingoloFlusso END"  );
        } catch ( Exception e ) {
            e.printStackTrace ();
        }
    }

    @Test
    public void eseguiElaborazioneNoFlussi () {
        try {
//            motoreDiRiconciliazioneService.eseguiRiconciliazione ( false, ID_ENTE, null );
            System.out.println ( "eseguiElaborazioneNoFlussi END"  );
        } catch ( Exception e ) {
            e.printStackTrace ();
        }
    }

    @Test
    public void eseguiElaborazioneConFlussi () {
        try {
            List<String> listaIdentificativiFlusso = new LinkedList<String> ();
            listaIdentificativiFlusso.add ( IDENTIFICATIVO_FLUSSO );
//            motoreDiRiconciliazioneService.eseguiRiconciliazione ( false, ID_ENTE, listaIdentificativiFlusso );
            System.out.println ( "eseguiElaborazioneConFlussi END"  );
        } catch ( Exception e ) {
            e.printStackTrace ();
        }
    }

}
