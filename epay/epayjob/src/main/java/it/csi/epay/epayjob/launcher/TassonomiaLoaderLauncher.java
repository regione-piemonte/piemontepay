/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.launcher;

import javax.naming.NamingException;

import org.apache.commons.lang3.exception.ExceptionUtils;

import it.csi.epay.epayjob.business.TassonomiaLoader;
import it.csi.epay.epayjob.model.JobContext;
import it.csi.epay.epayjob.utilities.CommonCliUtil;
import it.csi.epay.epayjob.utilities.EjbUtil;

/**
 * Batch per RDI-41: Job caricamento dati tassonomia
 */
public class TassonomiaLoaderLauncher {

    public static void main ( String [] args ) throws NamingException {
        CommonCliUtil commonCliUtil = new CommonCliUtil ( args );
        EjbUtil ejbUtil = new EjbUtil ( commonCliUtil.getServers (), commonCliUtil.getUsername (), commonCliUtil.getPassword () );
        JobContext jobContext = new JobContext ( "CaricamentoDatiTassonomia" );

        try {
            jobContext.infoStart ();
            TassonomiaLoader tassonomiaLoader = new TassonomiaLoader ( jobContext );
            tassonomiaLoader.execute ();

        } catch ( Throwable t ) {
            jobContext.error ( "Errore nell'esecuzione del job", t );
            jobContext.addProblem (
                "Si e' verificato un errore non gestito: " + t.getClass ().getName () + " - " + t.getMessage () + "\r\n" + ExceptionUtils.getStackTrace ( t ) );
        } finally {
            jobContext.infoEnd ();
            ejbUtil.close ();
        }
        System.exit ( 0 );
    }
}
