/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.impl.cooppec;

import java.util.List;

import javax.persistence.TypedQuery;

import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.exception.PersistenceFoundMoreThanOneResultException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBaseImpl;
import it.csi.epay.epaywsosrv.persistence.dao.interf.cooppec.EPaywsoDErroreDao;
import it.csi.epay.epaywsosrv.persistence.entity.cooppec.EPaywsoDErrore;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

public class EPaywsoDErroreDaoImpl extends EPaywsoDaoBaseImpl<Integer, EPaywsoDErrore> implements EPaywsoDErroreDao {

    static private final String CLASSNAME = EPaywsoDErroreDaoImpl.class.getName ();

    public EPaywsoDErrore findByCodiceErrore ( String codiceErrore ) throws PersistenceException {
        log.debug ( "findByCodiceErrore" );
        String methodName = "findByCodiceErrore";

        LogAndWatch lw = new LogAndWatch ( log, CLASSNAME, methodName );
        lw.addParam ( "codiceErrore", codiceErrore );

        EPaywsoDErrore entity = null;

        try {
            lw.start ();

            TypedQuery<EPaywsoDErrore> query = entityManager.createNamedQuery ( "EPaywsoDErrore.findByCodiceErrore", EPaywsoDErrore.class );
            query.setParameter ( "codiceErrore", codiceErrore );
            //
            List<EPaywsoDErrore> resultList = query.getResultList ();
            switch ( resultList.size () ) {
            case 0 :
                lw.warn ( "nessun risultato trovato per codiceErrore:".concat ( codiceErrore ) );
                break;
            case 1 :
                entity = resultList.get ( 0 );
                break;
            default :
                throw new PersistenceFoundMoreThanOneResultException ( "codice errore", "codiceErrore", codiceErrore );
            }
        } catch ( Throwable e ) {
            lw.error ( "Errore imprevisto", e );
            throw new PersistenceException ("000", "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            lw.stop ();
        }

        return entity;
    }
}
