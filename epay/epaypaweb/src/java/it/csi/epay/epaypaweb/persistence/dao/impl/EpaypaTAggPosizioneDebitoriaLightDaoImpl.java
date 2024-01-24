/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import static it.csi.epay.epaypaweb.enumeration.ColumnNameAggPosizioneDebitoriaEnum.CODICE_ESITO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameAggPosizioneDebitoriaEnum.CONCAT_CODICEESITO_DETTAGLIOESITO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameAggPosizioneDebitoriaEnum.DETTAGLIO_ESITO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameAggPosizioneDebitoriaEnum.ID_AGG_POSIZIONE_DEBITORIA;
import static it.csi.epay.epaypaweb.enumeration.ColumnNameAggPosizioneDebitoriaEnum.ID_FLUSSO;
import static it.csi.epay.epaypaweb.persistence.dao.common.EpaypaTAggPosizioneDebitoriaCommon.getColumnPath;

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
import it.csi.epay.epaypaweb.enumeration.ColumnNameAggPosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTAggPosizioneDebitoriaLightDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTAggPosizioneDebitoriaLight;


public class EpaypaTAggPosizioneDebitoriaLightDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTAggPosizioneDebitoriaLight> implements EpaypaTAggPosizioneDebitoriaLightDao {
	private static final String CLASSNAME = EpaypaTAggPosizioneDebitoriaLightDaoImpl.class.getName();

	@Override
	//@formatter:off
	public List<EpaypaTAggPosizioneDebitoriaLight> findAllLightByIdFlusso(Long idFlusso, List<CriterioOrdinamentoDto<ColumnNameAggPosizioneDebitoriaEnum>> ord, PaginazioneDto pag)
	throws PersistenceException
	{
	//@formatter:on
		String methodName = "findAllLightByIdFlusso";
		
		
		
		

		List<EpaypaTAggPosizioneDebitoriaLight> entityList = new ArrayList<> ();

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if (pag.getNumeroPagina() < 1) {
				log.warn("numero di pagina richiesto zero o negativo");

			} else if (pag.getNumeroRighePerPagina() < 1) {
				log.warn("numero di righe per pagina richieste zero o negativo");

			} else {
				// costruisce la query dinamicamente
				CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
				CriteriaQuery<EpaypaTAggPosizioneDebitoriaLight> cquery = cbuilder.createQuery(EpaypaTAggPosizioneDebitoriaLight.class);
				Root<EpaypaTAggPosizioneDebitoriaLight> root = cquery.from(EpaypaTAggPosizioneDebitoriaLight.class);
				cquery.select(root);
				if (idFlusso != null) {
					cquery.where(cbuilder.equal(getColumnPath(root, ID_FLUSSO), idFlusso));
				}
				List<Order> orderBys = buildOrderBy(ord, cbuilder, root);
				if (orderBys != null && !orderBys.isEmpty()) {
					cquery.orderBy(orderBys);
				}

				// assegna i parametri di paginazione
				TypedQuery<EpaypaTAggPosizioneDebitoriaLight> query = entityManager.createQuery(cquery);

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

	//@formatter:off
	private List<Order> buildOrderBy(List<CriterioOrdinamentoDto<ColumnNameAggPosizioneDebitoriaEnum>> ord, CriteriaBuilder cbuilder, Root<EpaypaTAggPosizioneDebitoriaLight> root) {
		List<Order> orderBys = new ArrayList<> ();

		boolean existsId = false;
		if (ord != null) {
			for (CriterioOrdinamentoDto<ColumnNameAggPosizioneDebitoriaEnum> cri : ord) {
				ColumnNameAggPosizioneDebitoriaEnum columnEnum = cri.getColumnNameEnum();
				existsId = ( columnEnum == ID_AGG_POSIZIONE_DEBITORIA ) || existsId;

				Expression<?> expression;
				if (columnEnum == CONCAT_CODICEESITO_DETTAGLIOESITO)
					expression = cbuilder.function("concat_ws", String.class, cbuilder.literal(" "),
						getColumnPath(root, CODICE_ESITO),
						getColumnPath(root, DETTAGLIO_ESITO)
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
			orderBys.add(cbuilder.asc(getColumnPath(root, ID_AGG_POSIZIONE_DEBITORIA)));
		}

		return orderBys;
	}
	//@formatter:on

}
