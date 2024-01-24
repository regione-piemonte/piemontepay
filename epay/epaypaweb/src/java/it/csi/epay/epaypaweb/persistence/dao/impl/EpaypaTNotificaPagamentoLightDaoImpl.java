/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import static it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum.ID_FLUSSO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum.ID_NOTIFICA_PAGAMENTO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum.COGNOME_OR_RAGIONE_SOCIALE_DEBITORE;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum.NOME_DEBITORE;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum.CONCAT_COGNOME_NOME_OR_RAGIONE_SOCIALE_DEBITORE;
import static it.csi.epay.epaypaweb.persistence.dao.common.EpaypaTNotificaPagamentoCommon.getColumnPath;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTNotificaPagamentoLightDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTNotificaPagamentoLight;


//@formatter:off
public class EpaypaTNotificaPagamentoLightDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTNotificaPagamentoLight> implements EpaypaTNotificaPagamentoLightDao {
	private static final String CLASSNAME = EpaypaTNotificaPagamentoDaoImpl.class.getName();

	@Override
	public List<EpaypaTNotificaPagamentoLight> findAllLightByIdFlusso(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ord, PaginazioneDto pag)
	throws PersistenceException
	{
		String methodName = "findAllLightByIdFlusso";
		
		
		
		

		List<EpaypaTNotificaPagamentoLight> entityList = new ArrayList<EpaypaTNotificaPagamentoLight>();

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if (pag.getNumeroPagina() < 1) {
				log.warn("numero di pagina richiesto zero o negativo");

			} else if (pag.getNumeroRighePerPagina() < 1) {
				log.warn("numero di righe per pagina richieste zero o negativo");

			} else {
				// costruisce la query dinamicamente
				CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
				CriteriaQuery<EpaypaTNotificaPagamentoLight> cquery = cbuilder.createQuery(EpaypaTNotificaPagamentoLight.class);
				Root<EpaypaTNotificaPagamentoLight> root = cquery.from(EpaypaTNotificaPagamentoLight.class);
				cquery.select(root);
				if (idFlusso != null) {
					cquery.where(cbuilder.equal(getColumnPath(root, ID_FLUSSO), idFlusso));
				}
				List<Order> orderBys = buildOrderBy(ord, cbuilder, root);
				if (orderBys != null && !orderBys.isEmpty()) {
					cquery.orderBy(orderBys);
				}

				// assegna i parametri di paginazione
				TypedQuery<EpaypaTNotificaPagamentoLight> query = entityManager.createQuery(cquery);
				query.setFirstResult((pag.getNumeroPagina() - 1) * pag.getNumeroRighePerPagina());
				query.setMaxResults(pag.getNumeroRighePerPagina());

				// esegue la query
				entityList = query.getResultList();
			}
		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return entityList;
	}

	private List<Order> buildOrderBy(List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ord, CriteriaBuilder cbuilder, Root<EpaypaTNotificaPagamentoLight> root) {
		List<Order> orderBys = new ArrayList<Order>();

		boolean existsId = false;
		if (ord != null) {
			for (CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum> cri : ord) {
				ColumnNameNotificaPagamentoEnum columnEnum = cri.getColumnNameEnum();
				existsId = (columnEnum == ID_NOTIFICA_PAGAMENTO) ? true : existsId;

				Expression<?> expression;
				if (columnEnum == CONCAT_COGNOME_NOME_OR_RAGIONE_SOCIALE_DEBITORE)
					expression = cbuilder.function("concat_ws", String.class, cbuilder.literal(" "),
						getColumnPath(root, COGNOME_OR_RAGIONE_SOCIALE_DEBITORE),
						getColumnPath(root, NOME_DEBITORE)
					);
				else
					expression = getColumnPath(root, columnEnum);

				switch (cri.getSortTypeEnum()) {
				case ASC:
					orderBys.add(cbuilder.asc(expression));
					break;
				case DESC:
					orderBys.add(cbuilder.desc(expression));
					break;
				}
			}
		}

		// aggiunge come ultimo criterio l'ordinamento per id
		if (!existsId) {
			orderBys.add(cbuilder.asc(getColumnPath(root, ID_NOTIFICA_PAGAMENTO)));
		}

		return orderBys;
	}

}
