/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.launcher;

import org.apache.commons.lang3.exception.ExceptionUtils;

import it.csi.epay.epayjob.business.TrasmettiNotificheTipizzateCittaFacile;
import it.csi.epay.epayjob.model.JobContext;
import it.csi.epay.epayjob.utilities.CommonCliUtil;
import it.csi.epay.epayjob.utilities.EjbUtil;
import it.csi.epay.epayservices.interfaces.ejb.ConfigurazioneFacade;
import it.csi.epay.epayservices.interfaces.ejb.JobFacade;

/**
 * Batch per RDI-43: Batch per l'invio delle posizioni debitorie per citta' facile.
 */
public class TrasmettiNotificheTipizzateCittaFacileLauncher {

    public static void main ( String [] args ) throws Exception {

        CommonCliUtil cli = new CommonCliUtil ( args );
        //        EjbUtil ejbUtil = new EjbUtil ( "localhost:24447", "ejbrmiclient2", "ejbrmiclient2!" )
        EjbUtil ejbUtil = new EjbUtil ( cli.getServers (), cli.getUsername (), cli.getPassword () );

        JobFacade jobFacade = ejbUtil.getEjb ( JobFacade.class );
        ConfigurazioneFacade configurazioneFacade = ejbUtil.getEjb ( ConfigurazioneFacade.class );
        
        JobContext context = new JobContext ( "TrasmissioneNotificheIOAPP" );

        try {
            context.infoStart ();
            TrasmettiNotificheTipizzateCittaFacile tnp = new TrasmettiNotificheTipizzateCittaFacile ( context, jobFacade , configurazioneFacade);
            tnp.execute ();

            if ( !context.getProblemi ().isEmpty () ) {
//                mailFacade.inviaMail ( context.estraiDto () );
            }
        } catch ( Throwable t ) {
            context.error ( "Errore nell'esecuzione del job", t );

            context.addProblem (
                "Si e' verificato un errore non gestito: " + t.getClass ().getName () + " - " + t.getMessage () + "\r\n" + ExceptionUtils.getStackTrace ( t ) );
//            mailFacade.inviaMail ( context.estraiDto () );

        } finally {
            context.infoEnd ();
            ejbUtil.close ();
        }
        System.exit ( 0 );
    }

}
