/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl.cooppec;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.exception.PersistenceFoundMoreThanOneResultException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.cooppec.EpaypaTEnteTempDao;
import it.csi.epay.epaypaweb.persistence.entity.cooppec.EpaypaTEnteTemp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.TypedQuery;
import java.util.List;


public class EpaypaTEnteTempDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTEnteTemp> implements EpaypaTEnteTempDao {

    static protected Logger log = LogManager.getLogger ( EpaypaTEnteTempDaoImpl.class.getName () );

    static private final String CLASSNAME = EpaypaTEnteTempDaoImpl.class.getName ();

    @Override
    public EpaypaTEnteTemp findOneByIdOperazione ( String idOperazione ) throws PersistenceException {
        String methodName = "findOneByIdOperazione";
        
        

        EpaypaTEnteTemp entity = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            TypedQuery<EpaypaTEnteTemp> query = entityManager.createNamedQuery ( "EpaypaTEnteTemp.findOneByIdOperazione", EpaypaTEnteTemp.class );
            query.setParameter ( "idOperazione", idOperazione );
            //
            List<EpaypaTEnteTemp> resultList = query.getResultList ();
            switch ( resultList.size () ) {
            case 0 :
                log.warn ( "nessun risultato trovato per idOperazione:".concat ( idOperazione ) );
                break;
            case 1 :
                entity = resultList.get ( 0 );
                break;
            default :
                throw new PersistenceFoundMoreThanOneResultException ( "id operazione", "idOperazione", idOperazione );
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
