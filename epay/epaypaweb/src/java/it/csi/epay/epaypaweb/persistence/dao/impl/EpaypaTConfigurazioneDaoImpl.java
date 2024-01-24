/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.exception.PersistenceFoundMoreThanOneResultException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTConfigurazioneDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTConfigurazione;



public class EpaypaTConfigurazioneDaoImpl extends EpaypaDaoBaseImpl<Integer, EpaypaTConfigurazione> implements EpaypaTConfigurazioneDao {
	static private final String CLASSNAME = EpaypaTConfigurazioneDaoImpl.class.getSimpleName();

    @Override
    public EpaypaTConfigurazione findConfigurazioneByCodiceAndCodFiscaleEnte ( String codice, String codFiscaleEnte ) throws PersistenceException {
        String methodName = "findConfigurazioneByCodiceAndCodFiscaleEnte";
        
        
        

        EpaypaTConfigurazione configurazione = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            TypedQuery<EpaypaTConfigurazione> query
                = entityManager.createNamedQuery ( "EpaypaTConfigurazione.findConfigurazioneByCodiceAndCodFiscaleEnte",
                    EpaypaTConfigurazione.class );
            query.setParameter ( "codFiscaleEnte", codFiscaleEnte );
            query.setParameter ( "codice", codice );
            //
            List<EpaypaTConfigurazione> resultList = query.getResultList ();
            switch ( resultList.size () ) {
            case 0 :
                log.warn ( "nessun risultato trovato per codice: ".concat ( codice ).concat ( "e codFiscaleEnte" ).concat ( codFiscaleEnte ) );
                break;
            case 1 :
                configurazione = resultList.get ( 0 );
                break;
            default :
                throw new PersistenceFoundMoreThanOneResultException ( "codice", "(codice,codFiscaleEnte)", "(" + codice + "," + codFiscaleEnte + ")" );
            }
        } catch ( Throwable e ) {
            throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return configurazione;
    }

}
