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
import it.csi.epay.epaywsosrv.persistence.dao.interf.cooppec.EPaywsoTEnteTempDao;
import it.csi.epay.epaywsosrv.persistence.entity.cooppec.EPaywsoTEnteTemp;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

public class EPaywsoTEnteTempDaoImpl extends EPaywsoDaoBaseImpl<Long, EPaywsoTEnteTemp> implements EPaywsoTEnteTempDao {

    static private final String CLASSNAME = EPaywsoTEnteTempDaoImpl.class.getName ();

    @Override
    public EPaywsoTEnteTemp findOneByIdOperazione ( String idOperazione ) throws PersistenceException {
        String methodName = "findOneByIdOperazione";
        LogAndWatch lw = new LogAndWatch ( log, CLASSNAME, methodName );
        lw.addParam ( "idOperazione", idOperazione );

        EPaywsoTEnteTemp entity = null;

        try {
            lw.start ();

            TypedQuery<EPaywsoTEnteTemp> query = entityManager.createNamedQuery ( "EPaywsoTEnteTemp.findOneByIdOperazione", EPaywsoTEnteTemp.class );
            query.setParameter ( "idOperazione", idOperazione );
            //
            List<EPaywsoTEnteTemp> resultList = query.getResultList ();
            switch ( resultList.size () ) {
            case 0 :
                lw.warn ( "nessun risultato trovato per idOperazione:".concat ( idOperazione ) );
                break;
            case 1 :
                entity = resultList.get ( 0 );
                break;
            default :
                throw new PersistenceFoundMoreThanOneResultException ( "id operazione", "idOperazione", idOperazione );
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
