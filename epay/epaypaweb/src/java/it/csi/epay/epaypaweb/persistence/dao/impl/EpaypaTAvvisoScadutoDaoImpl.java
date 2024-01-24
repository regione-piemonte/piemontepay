/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import static it.csi.epay.epaypaweb.enumeration.ColumnNameAvvisoScadutoEnum.ID_AVVISO_SCADUTO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameAvvisoScadutoEnum.ID_FLUSSO;

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
import it.csi.epay.epaypaweb.enumeration.ColumnNameAvvisoScadutoEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTAvvisoScadutoDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTAvvisoScaduto;


public class EpaypaTAvvisoScadutoDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTAvvisoScaduto> implements EpaypaTAvvisoScadutoDao {
	private static final String CLASSNAME = EpaypaTAvvisoScadutoDaoImpl.class.getName();

	@Override
	public Long countAllByIdFlusso(Long idFlusso) throws PersistenceException {
		String methodName = "countAllByIdFlusso";
		
		

		Long num = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// costruisce la query dinamicamente
			CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> cquery = cbuilder.createQuery(Long.class);
			Root<EpaypaTAvvisoScaduto> root = cquery.from(EpaypaTAvvisoScaduto.class);
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
	public List<EpaypaTAvvisoScaduto> findAllByIdFlusso(Long idFlusso) throws PersistenceException {
		String methodName = "findAllByIdFlusso";
		
		

		List<EpaypaTAvvisoScaduto> entityList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EpaypaTAvvisoScaduto> query = entityManager.createNamedQuery("EpaypaTAvvisoScaduto.findAllByIdFlusso", EpaypaTAvvisoScaduto.class);
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
	public List<EpaypaTAvvisoScaduto> findAllByIdFlusso(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameAvvisoScadutoEnum>> ord, PaginazioneDto pag)
	throws PersistenceException
	{
	//@formatter:on
		String methodName = "findAllByIdFlusso";
		
		
		
		

		List<EpaypaTAvvisoScaduto> entityList = new ArrayList<EpaypaTAvvisoScaduto>();

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if (pag.getNumeroPagina() < 1) {
				log.warn("numero di pagina richiesto zero o negativo");

			} else if (pag.getNumeroRighePerPagina() < 1) {
				log.warn("numero di righe per pagina richieste zero o negativo");

			} else {
				// costruisce la query dinamicamente
				CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
				CriteriaQuery<EpaypaTAvvisoScaduto> cquery = cbuilder.createQuery(EpaypaTAvvisoScaduto.class);
				Root<EpaypaTAvvisoScaduto> root = cquery.from(EpaypaTAvvisoScaduto.class);
				cquery.select(root);
				if (idFlusso != null) {
					cquery.where(cbuilder.equal(getColumnPath(root, ID_FLUSSO), idFlusso));
				}
				List<Order> orderBys = buildOrderBy(ord, cbuilder, root);
				if (orderBys != null && !orderBys.isEmpty()) {
					cquery.orderBy(orderBys);
				}

				// assegna i parametri di paginazione
				TypedQuery<EpaypaTAvvisoScaduto> query = entityManager.createQuery(cquery);

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

	private List<Order> buildOrderBy(List<CriterioOrdinamentoDto<ColumnNameAvvisoScadutoEnum>> ord, CriteriaBuilder cbuilder, Root<EpaypaTAvvisoScaduto> root) {
		List<Order> orderBys = new ArrayList<Order>();

		boolean existsId = false;
		if (ord != null) {
			for (CriterioOrdinamentoDto<ColumnNameAvvisoScadutoEnum> cri : ord) {
				ColumnNameAvvisoScadutoEnum columnEnum = cri.getColumnNameEnum();
				existsId = (columnEnum == ID_AVVISO_SCADUTO) ? true : existsId;

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
			orderBys.add(cbuilder.asc(getColumnPath(root, ID_AVVISO_SCADUTO)));
		}

		return orderBys;
	}

	@SuppressWarnings("rawtypes")
	private Path getColumnPath(Root<EpaypaTAvvisoScaduto> root, ColumnNameAvvisoScadutoEnum columnEnum) {
		Path columnPath = null;

		if (columnEnum != null) {
			switch (columnEnum) {
			case ID_AVVISO_SCADUTO:
				columnPath = root.get("idAvvisoScaduto");
				break;
			case ID_FLUSSO:
				columnPath = root.get("epaypaTFlusso").get("idFlusso");
				break;
			case IUV:
				columnPath = root.get("iuv");
				break;
			case DATA_SCADENZA:
				columnPath = root.get("dtScadenza");
				break;
			case IMPORTO:
				columnPath = root.get("importo");
				break;
			}
		}

		return columnPath;
	}

}
