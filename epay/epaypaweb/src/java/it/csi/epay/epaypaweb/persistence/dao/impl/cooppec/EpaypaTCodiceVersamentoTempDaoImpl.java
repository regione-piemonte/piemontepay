/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl.cooppec;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.exception.PersistencePersistException;
import it.csi.epay.epaypaweb.exception.PersistenceRemoveException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.cooppec.EpaypaTCodiceVersamentoTempDao;
import it.csi.epay.epaypaweb.persistence.entity.cooppec.EpaypaTCodiceVersamentoTemp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.TypedQuery;
import java.util.List;


public class EpaypaTCodiceVersamentoTempDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTCodiceVersamentoTemp> implements EpaypaTCodiceVersamentoTempDao {

    static protected Logger log = LogManager.getLogger ( EpaypaTCodiceVersamentoTempDaoImpl.class.getName () );

    static private final String CLASSNAME = EpaypaTCodiceVersamentoTempDaoImpl.class.getName ();

    @Override
    public void save ( List<EpaypaTCodiceVersamentoTemp> codiceVersamentoTempList ) throws PersistencePersistException {
        for ( EpaypaTCodiceVersamentoTemp epaypaTCodiceVersamentoTemp: codiceVersamentoTempList ) {
            persist ( epaypaTCodiceVersamentoTemp );
        }
    }

    @Override
    public void delete ( List<EpaypaTCodiceVersamentoTemp> catalogoTempList ) throws PersistenceRemoveException {
        log.debug ( "delete" );
        for ( EpaypaTCodiceVersamentoTemp epaypaTCodiceVersamentoTemp: catalogoTempList ) {
            remove ( epaypaTCodiceVersamentoTemp );
        }

    }

    public List<EpaypaTCodiceVersamentoTemp> findAllByIdOperazione ( String idOperazione ) throws PersistenceException {
        log.debug ( "findAllByIdOperazione" );
        
        String methodName = "findAllByIdOperazione";

        
        

        List<EpaypaTCodiceVersamentoTemp> resultList = null;
        
        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            TypedQuery<EpaypaTCodiceVersamentoTemp> query = entityManager.createNamedQuery ( "EpaypaTCodiceVersamentoTemp.findAllByIdOperazione", EpaypaTCodiceVersamentoTemp.class );
            query.setParameter ( "idOperazione", idOperazione );

            resultList = query.getResultList ();
            
            if(resultList.size () == 0) {
                log.warn ( "nessun risultato trovato per idOperazione:".concat ( idOperazione ) );
            }
        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto", e );
            throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return resultList;        
    }

}
