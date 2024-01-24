/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.FlussoLightFilterDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.common.EpaypaTFlussoCommon;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTFlussoLightDao;
import it.csi.epay.epaypaweb.persistence.entity.filter.EpaypaTFlussoFilter;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFlussoLight;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum.ID_FLUSSO;


public class EpaypaTFlussoLightDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTFlussoLight> implements EpaypaTFlussoLightDao {
	static private final String CLASSNAME = EpaypaTFlussoLightDaoImpl.class.getSimpleName();
	
	@Override
	public List<Long> findIdFlussoByFilter(FlussoLightFilterDto filter, String codUtente) throws PersistenceException {
		String methodName = "findIdFlussoByFilter";
		List<Long> idFlussi;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> cquery = cbuilder.createQuery(Long.class);
			Root<EpaypaTFlussoLight> root = cquery.from(EpaypaTFlussoLight.class);
			cquery.select(root.get("idFlusso"));
			List<Predicate> predicates = EpaypaTFlussoCommon.buildPredicates(filter, cbuilder, root);
			Predicate userRestrictions = EpaypaTFlussoCommon.buildUserRestrictions(codUtente, cbuilder, root);
			if (userRestrictions != null) {
				predicates.add(userRestrictions);
			}
			if (predicates != null && !predicates.isEmpty()) {
				cquery.where(cbuilder.and(predicates.toArray( new Predicate[0] )));
			}
			TypedQuery<Long> query = entityManager.createQuery(cquery);
			// esegue la query
			idFlussi = query.getResultList();
		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		return idFlussi;
	}

	@Override
	public List<EpaypaTFlussoFilter> findAllbyFilter ( FlussoLightFilterDto filter, List<CriterioOrdinamentoDto<ColumnNameFlussoEnum>> ord, PaginazioneDto pag,
					String codUtente ) throws PersistenceException {
		String methodName = "findAllbyFilter";
		List<EpaypaTFlussoFilter> entityList = new ArrayList<> ();
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			if ( pag != null && pag.getNumeroPagina () != null && pag.getNumeroPagina () < 1 ) {
				log.warn ( "numero di pagina richiesto zero o negativo" );
			} else if ( pag != null && pag.getNumeroRighePerPagina () != null && pag.getNumeroRighePerPagina () < 1 ) {
				log.warn ( "numero di righe per pagina richieste zero o negativo" );
			} else {
				CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder ();
				CriteriaQuery<EpaypaTFlussoFilter> cquery = cbuilder.createQuery ( EpaypaTFlussoFilter.class );
				Root<EpaypaTFlussoFilter> root = cquery.from ( EpaypaTFlussoFilter.class );
				cquery.select ( root );
				List<Predicate> predicates = EpaypaTFlussoCommon.buildPredicatesF ( filter, cbuilder, root );
				Predicate userRestrictions = EpaypaTFlussoCommon.buildUserRestrictionsF ( codUtente, cbuilder, root );
				if ( userRestrictions != null ) {
					predicates.add ( userRestrictions );
				}
				if ( predicates != null && !predicates.isEmpty () ) {
					cquery.where ( cbuilder.and ( predicates.toArray ( new Predicate[0] ) ) );
				}
				List<Order> orderBys = buildOrderByF ( ord, cbuilder, root );
				if ( !orderBys.isEmpty () ) {
					cquery.orderBy ( orderBys );
				}
				// assegna i parametri di paginazione
				TypedQuery<EpaypaTFlussoFilter> query = entityManager.createQuery ( cquery );
				if ( pag != null ) {
					if ( pag.getNumeroPagina () != null ) {
						query.setFirstResult ( ( pag.getNumeroPagina () - 1 ) * pag.getNumeroRighePerPagina () );
					}
					if ( pag.getNumeroRighePerPagina () != null ) {
						query.setMaxResults ( pag.getNumeroRighePerPagina () );
					}
				}
				// esegue la query
				entityList = query.getResultList ();
			}
		} catch ( Throwable e ) {
			log.error ( "Errore imprevisto", e );
			throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return entityList;
	}

	@Override
	public List<EpaypaTFlussoLight> findAllLightByFilter(FlussoLightFilterDto filter, List<CriterioOrdinamentoDto<ColumnNameFlussoEnum>> ord, PaginazioneDto pag, String codUtente)
	throws PersistenceException {
		String methodName = "findAllLightByFilter";
		List<EpaypaTFlussoLight> entityList = new ArrayList<> ();
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			if (pag != null && pag.getNumeroPagina() != null && pag.getNumeroPagina() < 1) {
				log.warn("numero di pagina richiesto zero o negativo");
			} else if (pag != null && pag.getNumeroRighePerPagina() != null && pag.getNumeroRighePerPagina() < 1) {
				log.warn("numero di righe per pagina richieste zero o negativo");
			} else {
				// costruisce la query dinamicamente
				CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
				CriteriaQuery<EpaypaTFlussoLight> cquery = cbuilder.createQuery(EpaypaTFlussoLight.class);
				Root<EpaypaTFlussoLight> root = cquery.from(EpaypaTFlussoLight.class);
				cquery.select(root);
				List<Predicate> predicates = EpaypaTFlussoCommon.buildPredicates(filter, cbuilder, root);
				Predicate userRestrictions = EpaypaTFlussoCommon.buildUserRestrictions(codUtente, cbuilder, root);
				if (userRestrictions != null) {
					predicates.add(userRestrictions);
				}
				if (predicates != null && !predicates.isEmpty()) {
					cquery.where(cbuilder.and(predicates.toArray( new Predicate[0] )));
				}
				List<Order> orderBys = buildOrderBy(ord, cbuilder, root);
				if ( !orderBys.isEmpty() ) {
					cquery.orderBy(orderBys);
				}
				// assegna i parametri di paginazione
				TypedQuery<EpaypaTFlussoLight> query = entityManager.createQuery(cquery);
				if (pag != null) {
					if (pag.getNumeroPagina() != null) {
						query.setFirstResult((pag.getNumeroPagina() - 1) * pag.getNumeroRighePerPagina());
					}
					if (pag.getNumeroRighePerPagina() != null) {
						query.setMaxResults(pag.getNumeroRighePerPagina());
					}
				}
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

	private List<Order> buildOrderBy(List<CriterioOrdinamentoDto<ColumnNameFlussoEnum>> ord, CriteriaBuilder cbuilder, Root<EpaypaTFlussoLight> root) {
		List<Order> orderBys = new ArrayList<> ();
		boolean existsId = false;
		if (ord != null) {
			for (CriterioOrdinamentoDto<ColumnNameFlussoEnum> cri : ord) {
				ColumnNameFlussoEnum columnEnum = cri.getColumnNameEnum();
				existsId = columnEnum == ID_FLUSSO || existsId;
				switch (cri.getSortTypeEnum()) {
				case ASC:
					orderBys.add(cbuilder.asc(EpaypaTFlussoCommon.getColumnPath(root, columnEnum)));
					break;
				case DESC:
					orderBys.add(cbuilder.desc(EpaypaTFlussoCommon.getColumnPath(root, columnEnum)));
					break;
				}
			}
		}
		// aggiunge come ultimo criterio l'ordinamento per id
		if (!existsId) {
			orderBys.add(cbuilder.asc(EpaypaTFlussoCommon.getColumnPath(root, ID_FLUSSO)));
		}
		return orderBys;
	}

	private List<Order> buildOrderByF ( List<CriterioOrdinamentoDto<ColumnNameFlussoEnum>> ord, CriteriaBuilder cbuilder, Root<EpaypaTFlussoFilter> root ) {
		List<Order> orderBys = new ArrayList<> ();
		boolean existsId = false;
		if ( ord != null ) {
			for ( CriterioOrdinamentoDto<ColumnNameFlussoEnum> cri : ord ) {
				ColumnNameFlussoEnum columnEnum = cri.getColumnNameEnum ();
				existsId = columnEnum == ID_FLUSSO || existsId;
				switch ( cri.getSortTypeEnum () ) {
				case ASC:
					orderBys.add ( cbuilder.asc ( EpaypaTFlussoCommon.getColumnPathF ( root, columnEnum ) ) );
					break;
				case DESC:
					orderBys.add ( cbuilder.desc ( EpaypaTFlussoCommon.getColumnPathF ( root, columnEnum ) ) );
					break;
				}
			}
		}
		// aggiunge come ultimo criterio l'ordinamento per id
		if ( !existsId ) {
			orderBys.add ( cbuilder.asc ( EpaypaTFlussoCommon.getColumnPathF ( root, ID_FLUSSO ) ) );
		}
		return orderBys;
	}
}
