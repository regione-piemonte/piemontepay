/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.launcher;

import it.csi.epay.epayjob.business.TrasmettiRichiesteDiRevoca;
import it.csi.epay.epayjob.utilities.CommonCliUtil;
import it.csi.epay.epayjob.utilities.EjbUtil;
import it.csi.epay.epayjob.utilities.LogUtil;
import it.csi.epay.epayservices.interfaces.ejb.JobFacade;
import it.csi.epay.epayservices.interfaces.ejb.MailFacade;

import javax.naming.NamingException;

/**
 *
 */
public class TrasmettiRichiesteDiRevocaLauncher extends AbstractLauncher {

    private static final LogUtil log = new LogUtil ( TrasmettiRichiesteDiRevocaLauncher.class );

    /**
     * @param args
     * @throws NamingException 
     */
    public static void main ( String [] args ) throws NamingException {

        final String methodName = "main";
        log.infoStart ( methodName );
        CommonCliUtil cli = new CommonCliUtil ( args );
        EjbUtil ejbUtil = new EjbUtil ( cli.getServers () );
        try {

            log.info ( methodName, "server ws : " + cli.getServers () );
            String host = cli.getServers ();
            log.info ( methodName, "end point : " + host );

            log.info ( methodName, "Chiamata al servizio TrasmettiRichiesteDiRevoca in corso..." );

            try {
                JobFacade jobFacade = ejbUtil.getEjb ( JobFacade.class );
                MailFacade mailFacade = ejbUtil.getEjb ( MailFacade.class );

                TrasmettiRichiesteDiRevoca instance = new TrasmettiRichiesteDiRevoca ( jobFacade, mailFacade );

                instance.execute ();
            } catch ( Throwable t ) {
                log.error ( "Errore nell'esecuzione di TrasmettiRichiesteDiRevoca", t );
                t.printStackTrace ();
            }
        } catch ( Exception e ) {
            log.error ( methodName, e );
        } finally {
            ejbUtil.close ();
        }
        log.infoEnd ( methodName );
        System.exit ( 0 );
    }

}
