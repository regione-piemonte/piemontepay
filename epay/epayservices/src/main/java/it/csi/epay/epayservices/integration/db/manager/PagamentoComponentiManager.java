/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import it.csi.epay.epayservices.integration.db.entities.EpayTPagamentoComponenti;
import it.csi.epay.epayservices.integration.db.entities.EpayTPagamentoSecondarioComponenti;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.PagamentoComponenti;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless(name="PagamentoComponentiManager", mappedName = "PagamentoComponentiManager")
public class PagamentoComponentiManager extends _Manager {

    @PersistenceContext
    private EntityManager entityManager;

    public PagamentoComponenti getPagamentoComponente(Long id) {
        try {
            EpayTPagamentoComponenti tPagamentoComponente = entityManager.find(EpayTPagamentoComponenti.class, id);
			return map(tPagamentoComponente, PagamentoComponenti.class);
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<PagamentoComponenti> getPagamentoComponente(Long idPagamento, Integer progressivo) throws NoDataException {
        try {
            TypedQuery<EpayTPagamentoComponenti> query;
            if (progressivo != null) {
                query = entityManager.createNamedQuery("EpayTPagamentoComponenti.elencoComponentiByIdPagamento", EpayTPagamentoComponenti.class);
                query.setParameter("idPagamento", idPagamento);
            } else {
                query = entityManager.createNamedQuery("EpayTPagamentoComponenti.elencoComponentiByIdPagamentoEProgressivo", EpayTPagamentoComponenti.class);
                query.setParameter("idPagamento", idPagamento);
                query.setParameter("progressivo", progressivo);
            }
            List<EpayTPagamentoComponenti> tPagamentoComponenti = query.getResultList();
			return mapList(tPagamentoComponenti, PagamentoComponenti.class);
        } catch (NoResultException nre) {
            throw new NoDataException("Nessun dettaglio (componente) pagamento trovato", nre.getCause());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteAllPagamentoComponente(Long idPagamento) {
        try {
            TypedQuery<EpayTPagamentoComponenti> query = entityManager.createNamedQuery("EpayTPagamentoComponenti.elencoComponentiByIdPagamento", EpayTPagamentoComponenti.class);
            query.setParameter("idPagamento", idPagamento);
            List<EpayTPagamentoComponenti> tPagamentoComponenti = query.getResultList();
            for (EpayTPagamentoComponenti epayTPagamentoComponenti : tPagamentoComponenti) {
                entityManager.remove(epayTPagamentoComponenti);
            }
            entityManager.flush();
        } catch (NoResultException ignored ) {
            ;
        }
    }

    //CSI_PAG-1888 INIZIO
    @TransactionAttribute ( TransactionAttributeType.REQUIRES_NEW )
    public void deleteAllPagamentoComponenteSecondario ( Long idPagamentoSecondario ) {
        try {
            TypedQuery<EpayTPagamentoSecondarioComponenti> query
                = entityManager.createNamedQuery ( "EpayTPagamentoSecondarioComponenti.elencoComponentiByIdPagamentoSecondario",
                    EpayTPagamentoSecondarioComponenti.class );
            query.setParameter ( "idPagamentoSecondario", idPagamentoSecondario );
            List<EpayTPagamentoSecondarioComponenti> tPagamentoSecondarioComponenti = query.getResultList ();
            for ( EpayTPagamentoSecondarioComponenti epayTPagamentoSecondarioComponenti: tPagamentoSecondarioComponenti ) {
                entityManager.remove ( epayTPagamentoSecondarioComponenti );
            }
            entityManager.flush ();
        } catch ( NoResultException ignored ) {
            ;
        }
    }
    //CSI_PAG-1888 FINE

	public List<PagamentoComponenti> getComponentiPerBatchAggPosDeb ( String tassonomiaEsistente ) throws NoDataException {
		try {
			TypedQuery<EpayTPagamentoComponenti> query;
			query = entityManager.createNamedQuery ( "EpayTPagamentoComponenti.elencoComponentiPerBatchAggPosDeb", EpayTPagamentoComponenti.class );
			query.setParameter ( "tassonomiaEsistente", tassonomiaEsistente );
			List<EpayTPagamentoComponenti> tPagamentoComponenti = query.getResultList ();
			return mapList ( tPagamentoComponenti, PagamentoComponenti.class );
		} catch ( NoResultException nre ) {
			throw new NoDataException ( "Nessun dettaglio (componente) pagamento trovato per batch agg pos deb!", nre.getCause () );
		}
	}

	public void aggiornaComponentiPerBatchAggPosDeb (List<PagamentoComponenti> componenti, String tassonomiaEsistente, String tassonomiaNuova) {
		for ( PagamentoComponenti componente : componenti ) {
			TypedQuery<EpayTPagamentoComponenti> query;
			query = entityManager.createNamedQuery ( "EpayTPagamentoComponenti.aggiornaComponentiPerBatchAggPosDeb", EpayTPagamentoComponenti.class );
			query.setParameter ( "tassonomiaEsistente", tassonomiaEsistente );
			query.setParameter ( "tassonomiaNuova", tassonomiaNuova );
			query.setParameter ( "idComponente", componente.getIdComponente () );
			query.setParameter ( "datiSpecificiRiscossione", componente.getDatiSpecificiRiscossione () );
			query.executeUpdate ();
		}
	}
}
