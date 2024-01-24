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
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTDatiAvvisoPagamentoDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTDatiAvvisoPagamento;



public class EpaypaTDatiAvvisoPagamentoDaoImpl extends EpaypaDaoBaseImpl<Integer, EpaypaTDatiAvvisoPagamento> implements EpaypaTDatiAvvisoPagamentoDao {
	static private final String CLASSNAME = EpaypaTDatiAvvisoPagamentoDaoImpl.class.getSimpleName();

    @Override
    public EpaypaTDatiAvvisoPagamento findDatiAvvisoPagamentoByCodiceVersamento (String codFiscaleEnte, String codiceVersamento ) throws PersistenceException {
        String methodName = "findDatiAvvisoPagamentoByIdCodiceVersamento";
        
        
        

        EpaypaTDatiAvvisoPagamento datiAvvisoPagamento = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            TypedQuery<EpaypaTDatiAvvisoPagamento> query
                = entityManager.createNamedQuery ( "EpaypaTDatiAvvisoPagamento.findDatiAvvisoPagamentoByCodiceVersamentoAndEnte",
                    EpaypaTDatiAvvisoPagamento.class );
            query.setParameter ( "codiceVersamento", codiceVersamento );
            query.setParameter ( "codFiscaleEnte", codFiscaleEnte );

            List<EpaypaTDatiAvvisoPagamento> resultList = query.getResultList ();
            switch ( resultList.size () ) {
            case 0 :
                log.warn ( "nessun risultato trovato per codiceVersamento:".concat ( codiceVersamento ) );
                break;
            case 1 :
                datiAvvisoPagamento = resultList.get ( 0 );
                break;
            default :
                throw new PersistenceFoundMoreThanOneResultException ( "codiceVersamento", "codiceVersamento", codiceVersamento );
            }
        } catch ( Throwable e ) {
            throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return datiAvvisoPagamento;
    }

}
