/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl.cooppec;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.exception.PersistenceFoundMoreThanOneResultException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.cooppec.EpaypaDErroreDao;
import it.csi.epay.epaypaweb.persistence.entity.cooppec.EpaypaDErrore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.TypedQuery;
import java.util.List;


public class EpaypaDErroreDaoImpl extends EpaypaDaoBaseImpl<Integer, EpaypaDErrore> implements EpaypaDErroreDao {

    static protected Logger log = LogManager.getLogger ( EpaypaDErroreDaoImpl.class.getName () );

    static private final String CLASSNAME = EpaypaDErroreDaoImpl.class.getName ();

    public EpaypaDErrore findByCodiceErrore ( String codiceErrore ) throws PersistenceException {
        log.debug ( "findByCodiceErrore" );
        String methodName = "findByCodiceErrore";

        
        

        EpaypaDErrore entity = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            TypedQuery<EpaypaDErrore> query = entityManager.createNamedQuery ( "EpaypaDErrore.findByCodiceErrore", EpaypaDErrore.class );
            query.setParameter ( "codiceErrore", codiceErrore );
            //
            List<EpaypaDErrore> resultList = query.getResultList ();
            switch ( resultList.size () ) {
            case 0 :
                log.warn ( "nessun risultato trovato per codiceErrore:".concat ( codiceErrore ) );
                break;
            case 1 :
                entity = resultList.get ( 0 );
                break;
            default :
                throw new PersistenceFoundMoreThanOneResultException ( "codice errore", "codiceErrore", codiceErrore );
            }
        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto", e );
            throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return entity;
    }
}
