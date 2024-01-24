/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import static it.csi.epay.epaypaweb.enumeration.ColumnNamePosizioneDebitoriaEnum.ID_FLUSSO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNamePosizioneDebitoriaEnum.ID_POSIZIONE_DEBITORIA;
import static it.csi.epay.epaypaweb.persistence.dao.common.EpaypaTPosizioneDebitoriaCommon.getColumnPath;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNamePosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTPosizioneDebitoriaLightDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPosizioneDebitoriaLight;


public class EpaypaTPosizioneDebitoriaLightDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTPosizioneDebitoriaLight> implements EpaypaTPosizioneDebitoriaLightDao {
	private static final String CLASSNAME = EpaypaTPosizioneDebitoriaLightDaoImpl.class.getName();

	@Override
	//@formatter:off
	public List<EpaypaTPosizioneDebitoriaLight> findAllLightByIdFlusso(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNamePosizioneDebitoriaEnum>> ord, PaginazioneDto pag)
	throws PersistenceException
	{
	//@formatter:on
		String methodName = "findAllLightByIdFlusso";
		
		
		
		

		List<EpaypaTPosizioneDebitoriaLight> entityList = new ArrayList<EpaypaTPosizioneDebitoriaLight>();

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if (pag.getNumeroPagina() < 1) {
				log.warn("numero di pagina richiesto zero o negativo");

			} else if (pag.getNumeroRighePerPagina() < 1) {
				log.warn("numero di righe per pagina richieste zero o negativo");

			} else {
				// costruisce la query dinamicamente
				CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
				CriteriaQuery<EpaypaTPosizioneDebitoriaLight> cquery = cbuilder.createQuery(EpaypaTPosizioneDebitoriaLight.class);
				Root<EpaypaTPosizioneDebitoriaLight> root = cquery.from(EpaypaTPosizioneDebitoriaLight.class);
				cquery.select(root);
				if (idFlusso != null) {
					cquery.where(cbuilder.equal(getColumnPath(root, ID_FLUSSO), idFlusso));
				}
				List<Order> orderBys = buildOrderBy(ord, cbuilder, root);
				if (orderBys != null && !orderBys.isEmpty()) {
					cquery.orderBy(orderBys);
				}

				// assegna i parametri di paginazione
				TypedQuery<EpaypaTPosizioneDebitoriaLight> query = entityManager.createQuery(cquery);

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

	private List<Order> buildOrderBy(List<CriterioOrdinamentoDto<ColumnNamePosizioneDebitoriaEnum>> ord, CriteriaBuilder cbuilder, Root<EpaypaTPosizioneDebitoriaLight> root) {
		List<Order> orderBys = new ArrayList<Order>();

		boolean existsId = false;
		if (ord != null) {
			for (CriterioOrdinamentoDto<ColumnNamePosizioneDebitoriaEnum> cri : ord) {
				ColumnNamePosizioneDebitoriaEnum columnEnum = cri.getColumnNameEnum();
				existsId = (columnEnum == ID_POSIZIONE_DEBITORIA) ? true : existsId;

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
			orderBys.add(cbuilder.asc(getColumnPath(root, ID_POSIZIONE_DEBITORIA)));
		}

		return orderBys;
	}

}
