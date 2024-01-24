/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import static it.csi.epay.epaypaweb.enumeration.ColumnNameRiversamentoEnum.ID_FLUSSO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameRiversamentoEnum.ID_RIVERSAMENTO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameRiversamentoEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTRiversamentoDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRiversamento;


public class EpaypaTRiversamentoDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTRiversamento> implements EpaypaTRiversamentoDao {
	private static final String CLASSNAME = EpaypaTRiversamentoDaoImpl.class.getName();

	@Override
	public Long countAllByIdFlusso(Long idFlusso) throws PersistenceException {
		String methodName = "countAllByIdFlusso";
		
		

		Long num = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// costruisce la query dinamicamente
			CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> cquery = cbuilder.createQuery(Long.class);
			Root<EpaypaTRiversamento> root = cquery.from(EpaypaTRiversamento.class);
			cquery.select(cbuilder.count(root));
			if (idFlusso != null) {
				cquery.where(cbuilder.equal(getColumnPath(root, ID_FLUSSO), idFlusso));
			}

			// esegue la query
			num = entityManager.createQuery(cquery).getSingleResult();

		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return num;
	}

	@Override
	public List<EpaypaTRiversamento> findAllByIdFlusso(Long idFlusso) throws PersistenceException {
		String methodName = "findAllByIdFlusso";
		
		

		List<EpaypaTRiversamento> entityList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EpaypaTRiversamento> query = entityManager.createNamedQuery("EpaypaTRiversamento.findAllByIdFlusso", EpaypaTRiversamento.class);
			query.setParameter("idFlusso", idFlusso);
			//
			entityList = query.getResultList();

		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return entityList;
	}

	@Override
	//@formatter:off
	public List<EpaypaTRiversamento> findAllByIdFlusso(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameRiversamentoEnum>> ord, PaginazioneDto pag)
	throws PersistenceException
	{
	//@formatter:on
		String methodName = "findAllLightByIdFlusso";
		
		
		
		

		List<EpaypaTRiversamento> entityList = new ArrayList<EpaypaTRiversamento>();

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if (pag.getNumeroPagina() < 1) {
				log.warn("numero di pagina richiesto zero o negativo");

			} else if (pag.getNumeroRighePerPagina() < 1) {
				log.warn("numero di righe per pagina richieste zero o negativo");

			} else {
				// costruisce la query dinamicamente
				CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
				CriteriaQuery<EpaypaTRiversamento> cquery = cbuilder.createQuery(EpaypaTRiversamento.class);
				Root<EpaypaTRiversamento> root = cquery.from(EpaypaTRiversamento.class);
				cquery.select(root);
				if (idFlusso != null) {
					cquery.where(cbuilder.equal(getColumnPath(root, ID_FLUSSO), idFlusso));
				}
				List<Order> orderBys = buildOrderBy(ord, cbuilder, root);
				if (orderBys != null && !orderBys.isEmpty()) {
					cquery.orderBy(orderBys);
				}

				// assegna i parametri di paginazione
				TypedQuery<EpaypaTRiversamento> query = entityManager.createQuery(cquery);

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

	private List<Order> buildOrderBy(List<CriterioOrdinamentoDto<ColumnNameRiversamentoEnum>> ord, CriteriaBuilder cbuilder, Root<EpaypaTRiversamento> root) {
		List<Order> orderBys = new ArrayList<Order>();

		boolean existsId = false;
		if (ord != null) {
			for (CriterioOrdinamentoDto<ColumnNameRiversamentoEnum> cri : ord) {
				ColumnNameRiversamentoEnum columnEnum = cri.getColumnNameEnum();
				existsId = (columnEnum == ID_RIVERSAMENTO) ? true : existsId;

				switch (cri.getSortTypeEnum()) {
				case ASC:
					orderBys.add(cbuilder.asc(getColumnPath(root, columnEnum)));
					break;
				case DESC:
					orderBys.add(cbuilder.desc(getColumnPath(root, columnEnum)));
					break;
				}
			}
		}

		// aggiunge come ultimo criterio l'ordinamento per id
		if (!existsId) {
			orderBys.add(cbuilder.asc(getColumnPath(root, ID_RIVERSAMENTO)));
		}

		return orderBys;
	}

	@SuppressWarnings("rawtypes")
	private Path getColumnPath(Root<EpaypaTRiversamento> root, ColumnNameRiversamentoEnum columnEnum) {
		Path columnPath = null;

		if (columnEnum != null) {
			switch (columnEnum) {
			case ID_RIVERSAMENTO:
				columnPath = root.get("idRiversamento");
				break;
			case ID_FLUSSO:
				columnPath = root.get("epaypaTRendicontazione").get("idFlusso");
				break;
			case IUV:
				columnPath = root.get("iuv");
				break;
			case IUR:
				columnPath = root.get("iur");
				break;
			case INDICE_DATI_RT:
				columnPath = root.get("indiceDatiRt");
				break;
			case IMPORTO_PAGATO:
				columnPath = root.get("importoPagato");
				break;
			case COD_ESITO:
				columnPath = root.get("codEsito");
				break;
			case DATA_ESITO:
				columnPath = root.get("dtEsito");
				break;
			}
		}

		return columnPath;
	}

}
