/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.impl.cooppec;

import java.util.List;

import javax.persistence.TypedQuery;

import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBaseImpl;
import it.csi.epay.epaywsosrv.persistence.dao.interf.cooppec.EPaywsoTCodiceVersamentoTempDao;
import it.csi.epay.epaywsosrv.persistence.entity.cooppec.EPaywsoTCodiceVersamentoTemp;
import it.csi.epay.epaywsosrv.util.LogAndWatch;


public class EPaywsoTCodiceVersamentoTempDaoImpl extends EPaywsoDaoBaseImpl<Long, EPaywsoTCodiceVersamentoTemp> implements EPaywsoTCodiceVersamentoTempDao {

    static private final String CLASSNAME = EPaywsoTCodiceVersamentoTempDaoImpl.class.getName ();

    @Override
    public void save ( List<EPaywsoTCodiceVersamentoTemp> codiceVersamentoTempList ) throws PersistenceException {
        for ( EPaywsoTCodiceVersamentoTemp epaywsoTCodiceVersamentoTemp: codiceVersamentoTempList ) {
            persist ( epaywsoTCodiceVersamentoTemp );
        }
    }

    @Override
    public void delete ( List<EPaywsoTCodiceVersamentoTemp> catalogoTempList ) throws PersistenceException {
        log.debug ( "delete" );
        for ( EPaywsoTCodiceVersamentoTemp epaywsoTCodiceVersamentoTemp: catalogoTempList ) {
            remove ( epaywsoTCodiceVersamentoTemp );
        }

    }

    public List<EPaywsoTCodiceVersamentoTemp> findAllByIdOperazione ( String idOperazione ) throws PersistenceException {
        log.debug ( "findAllByIdOperazione" );
        
        String methodName = "findAllByIdOperazione";

        LogAndWatch lw = new LogAndWatch ( log, CLASSNAME, methodName );
        lw.addParam ( "idOperazione", idOperazione );

        List<EPaywsoTCodiceVersamentoTemp> resultList = null;
        
        try {
            lw.start ();

            TypedQuery<EPaywsoTCodiceVersamentoTemp> query = entityManager.createNamedQuery ( "EPaywsoTCodiceVersamentoTemp.findAllByIdOperazione", EPaywsoTCodiceVersamentoTemp.class );
            query.setParameter ( "idOperazione", idOperazione );

            resultList = query.getResultList ();
            
            if(resultList.size () == 0) {
                lw.warn ( "nessun risultato trovato per idOperazione:".concat ( idOperazione ) );
            }
        } catch ( Throwable e ) {
            lw.error ( "Errore imprevisto", e );
            throw new PersistenceException ("000", "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            lw.stop ();
        }

        return resultList;        
    }

}
