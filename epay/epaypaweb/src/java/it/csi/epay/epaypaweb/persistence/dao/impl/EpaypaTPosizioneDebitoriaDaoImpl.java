/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.PagamentiFilterDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNamePagamentiEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTPosizioneDebitoriaDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPosizioneDebitoria;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPosizioneDebitoriaLight;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static it.csi.epay.epaypaweb.enumeration.ColumnNamePosizioneDebitoriaEnum.COGNOME;
import static it.csi.epay.epaypaweb.enumeration.ColumnNamePosizioneDebitoriaEnum.ID_FLUSSO;
import static it.csi.epay.epaypaweb.enumeration.ColumnNamePosizioneDebitoriaEnum.IUV;
import static it.csi.epay.epaypaweb.persistence.dao.common.EpaypaTPosizioneDebitoriaCommon.getColumnPath;
import static it.csi.epay.epaypaweb.persistence.dao.common.EpaypaTPosizioneDebitoriaCommon.getColumnPathPD;


public class EpaypaTPosizioneDebitoriaDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTPosizioneDebitoria> implements EpaypaTPosizioneDebitoriaDao {
	private static final String CLASSNAME = EpaypaTPosizioneDebitoriaDaoImpl.class.getName();

	@Override
	public Long countAllByIdFlusso(Long idFlusso) throws PersistenceException {
		String methodName = "countAllByIdFlusso";
		Long num;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			// costruisce la query dinamicamente
			CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> cquery = cbuilder.createQuery(Long.class);
			Root<EpaypaTPosizioneDebitoriaLight> root = cquery.from(EpaypaTPosizioneDebitoriaLight.class);
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
	public List<EpaypaTPosizioneDebitoria> findAllByIdFlusso(Long idFlusso) throws PersistenceException {
		String methodName = "findAllByIdFlusso";
		List<EpaypaTPosizioneDebitoria> entityList;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			TypedQuery<EpaypaTPosizioneDebitoria> query = entityManager.createNamedQuery("EpaypaTPosizioneDebitoria.findAllByIdFlusso", EpaypaTPosizioneDebitoria.class);
			query.setParameter("idFlusso", idFlusso);
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
	public EpaypaTPosizioneDebitoria findPosizioneDebitoriaByIuv(String iuv) throws PersistenceException {
		String methodName = "findPosizioneDebitoriaByIuv";

		EpaypaTPosizioneDebitoria posizioneDebitoria = new EpaypaTPosizioneDebitoria();

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			TypedQuery<EpaypaTPosizioneDebitoria> query = entityManager.createNamedQuery("EpaypaTPosizioneDebitoria.findByIuv", EpaypaTPosizioneDebitoria.class);
			query.setParameter("iuv", iuv);
			//
			
			List<EpaypaTPosizioneDebitoria> posizioniDebitorie = query.getResultList();
			if (posizioniDebitorie.size()!=0)
				posizioneDebitoria = posizioniDebitorie.get(0);


		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return posizioneDebitoria;
	}

	@Override
	public List<EpaypaTPosizioneDebitoria> findAllPagamentiByFilter( PagamentiFilterDto filter, List<CriterioOrdinamentoDto<ColumnNamePagamentiEnum>> ord, PaginazioneDto pag, String codUtente) throws PersistenceException {
		String methodName = "findAllPagamentiByFilter";
		
		

		List<EpaypaTPosizioneDebitoria> entityList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			// costruisce la query dinamicamente
			CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<EpaypaTPosizioneDebitoria> cquery = cbuilder.createQuery(EpaypaTPosizioneDebitoria.class);
			Root<EpaypaTPosizioneDebitoria> root = cquery.from(EpaypaTPosizioneDebitoria.class);
			cquery.select(root);
			List<Predicate> predicates = buildPredicates(filter, cbuilder, root);


		//	TypedQuery<EpaypaTPosizioneDebitoria> query = entityManager.createNamedQuery("EpaypaTPosizioneDebitoria.findAllByIdFlusso", EpaypaTPosizioneDebitoria.class);
		
			if (predicates != null && !predicates.isEmpty()) {
				cquery.where(cbuilder.and(predicates.toArray( new Predicate[0] )));
			}
//			List<Order> orderBys = buildOrderBy(ord, cbuilder, root);
//			if (orderBys != null && !orderBys.isEmpty()) {
//				cquery.orderBy(orderBys);
//			}

			// assegna i parametri di paginazione
			TypedQuery<EpaypaTPosizioneDebitoria> query = entityManager.createQuery(cquery);
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


		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return entityList;
	}
	
	@Override
	public Long countAllPosizioneDebitoriaByFilter (PagamentiFilterDto filter, String codUtente) throws PersistenceException {
		String methodName = "countAllPosizioneDebitoriaByFilter";
		
		

		Long num = null;
		List<EpaypaTPosizioneDebitoria> entityList = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// costruisce la query dinamicamente
			CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> cquery = cbuilder.createQuery(Long.class);
			Root<EpaypaTPosizioneDebitoria> root = cquery.from(EpaypaTPosizioneDebitoria.class);
			cquery.select(cbuilder.count(root));
			List<Predicate> predicates = buildPredicates(filter, cbuilder, root);
			if (predicates != null && !predicates.isEmpty()) {
				cquery.where(cbuilder.and(predicates.toArray( new Predicate[0] )));
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
	
	static public List<Predicate> buildPredicates(PagamentiFilterDto filter, CriteriaBuilder cbuilder, Root<EpaypaTPosizioneDebitoria> root) {
		List<Predicate> predicates = null;

		if (filter != null) {
			predicates = new ArrayList<>();

			if ((filter.getIuv() != null) && (!filter.getIuv().isEmpty())){
				predicates.add(cbuilder.equal(getColumnPathPD(root, IUV), filter.getIuv()));
			}
			
			if ((filter.getCognome()!=null) && (!filter.getCognome().isEmpty())) {
				predicates.add(cbuilder.equal(getColumnPathPD(root, COGNOME), filter.getCognome()));
			}
		}

		return predicates;
	}

    @Override
    public Long countAllPosizioneDebitoriaByIdEnteIdCovPosDebEst ( String posizioneDebitoriaEst, Integer enteId, Integer idCodVersamento )
                    throws PersistenceException {
        String methodName = "countAllPosizioneDebitoriaByIdEnteIdCovPosDebEst";

        Long num;
        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            // costruisce la query dinamicamente
            CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder ();
            CriteriaQuery<Long> cquery = cbuilder.createQuery ( Long.class );
            Root<EpaypaTPosizioneDebitoria> root = cquery.from ( EpaypaTPosizioneDebitoria.class );
            cquery.select ( cbuilder.count ( root ) );
            cquery.where (
                cbuilder.equal ( root.get ( "idPosizioneDebitoriaEst" ), posizioneDebitoriaEst ),
                cbuilder.equal ( root.get ( "epaypaTFlusso" ).get ( "epaypaTEnte" ).get ( "idEnte" ), enteId ),
                cbuilder.equal ( root.get ( "epaypaTFlusso" ).get ( "epaypaTCodiceVersamento" ).get ( "idCodiceVersamento" ), idCodVersamento ) );

            // esegue la query
            num = entityManager.createQuery ( cquery ).getSingleResult ();

        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto", e );
            throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
        return num;
    }

	@Override public EpaypaTPosizioneDebitoria findOneByIdPosizioneDebitoriaEsternaAndIUV ( String iDposizioneDebitoriaEsterna, String iuv, Integer idEnte, Integer idCodiceVersamento )
					throws PersistenceException {
		String methodName = "findOneByIdPosizioneDebitoriaEsternaAndIUV";
		EpaypaTPosizioneDebitoria posizioneDebitoria = new EpaypaTPosizioneDebitoria ();
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			TypedQuery<EpaypaTPosizioneDebitoria> query;
			if ( iDposizioneDebitoriaEsterna.isEmpty () ) {
				query = entityManager.createNamedQuery ( "EpaypaTPosizioneDebitoria.findByIUV", EpaypaTPosizioneDebitoria.class );
				query.setParameter ( "iuv", likeHelper ( iuv ) );
			} else {
				if ( iuv.isEmpty () ) {
					query = entityManager.createNamedQuery ( "EpaypaTPosizioneDebitoria.findByPosizioneDebitoriaEsterna", EpaypaTPosizioneDebitoria.class );
				} else {
					query = entityManager.createNamedQuery ( "EpaypaTPosizioneDebitoria.findByPosizioneDebitoriaEsternaAndIUV", EpaypaTPosizioneDebitoria.class );
					query.setParameter ( "iuv", likeHelper ( iuv ) );
				}
				query.setParameter ( "id_posizione_debitoria_est", likeHelper ( iDposizioneDebitoriaEsterna ) );
			}
			query.setParameter ( "id_ente", idEnte );
			query.setParameter ( "id_cod_versamento", idCodiceVersamento );
			List<EpaypaTPosizioneDebitoria> posizioniDebitorie = query.getResultList ();
			if ( posizioniDebitorie.size () != 0 ) {
				posizioneDebitoria = posizioniDebitorie.get ( 0 );
			} else if ( iDposizioneDebitoriaEsterna.isEmpty () && !iuv.isEmpty () ) {
				query = entityManager.createNamedQuery ( "EpaypaTPosizioneDebitoria.findByIUVAlt", EpaypaTPosizioneDebitoria.class );
				query.setParameter ( "iuv", likeHelper ( iuv ) );
				query.setParameter ( "id_ente", idEnte );
				query.setParameter ( "id_cod_versamento", idCodiceVersamento );
				query.setMaxResults ( 1 );
				posizioneDebitoria = query.getSingleResult ();
			}
		} catch ( Throwable e ) {
			log.error ( "Errore imprevisto", e );
			throw new PersistenceException ( "Errore imprevisto in " + CLASSNAME + "::" + methodName, e );
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return posizioneDebitoria;
	}

	private String likeHelper ( String input ) {
		return "%" + input + "%";
	}
}
