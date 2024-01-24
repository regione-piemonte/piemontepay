/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.launcher;

import it.csi.epay.epayjob.business.GeneratePdfReceipt;
import it.csi.epay.epayjob.utilities.CommonCliUtil;
import it.csi.epay.epayjob.utilities.EjbUtil;
import it.csi.epay.epayjob.utilities.LogUtil;
import it.csi.epay.epayservices.interfaces.ejb.JobFacade;
import it.csi.epay.epayservices.interfaces.ejb.MailFacade;


public class GeneratePdfReceiptLauncher {

    private static final LogUtil log = new LogUtil ( GeneratePdfReceiptLauncher.class );

    public static void main ( String [] args ) throws Exception {
        log.infoStart ( "GeneratePdfReceiptLauncher" );

        CommonCliUtil cli = new CommonCliUtil ( args );
        EjbUtil ejbUtil = new EjbUtil ( cli.getServers (), cli.getUsername (), cli.getPassword () );
        // JobContext jobContext = new JobContext ( "GeneratePdfReceipt" )

        try {
            JobFacade jobFacade = ejbUtil.getEjb ( JobFacade.class );
            MailFacade mailFacade = ejbUtil.getEjb ( MailFacade.class );
            GeneratePdfReceipt pdf = new GeneratePdfReceipt ( jobFacade, mailFacade );
            pdf.execute ();
        }
        catch ( Exception e ) {
            e.printStackTrace ();
        }
        finally {
            ejbUtil.close ();
        }
        log.infoEnd ( "GeneratePdfReceiptLauncher" );
        System.exit ( 0 );
    }
}
