/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.xml.datatype.XMLGregorianCalendar;

import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.RichiestaDiRevocaDto;
import it.csi.epay.epaypaweb.dto.RichiestaRevocheFilterDto;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBaseImpl;
import it.csi.epay.epaypaweb.persistence.dao.common.EpaypaTRrCommon;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTRrDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTNotificaPagamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRr;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTSoggetto;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTTipoRevoca;


public class EpaypaTRrDaoImpl extends EpaypaDaoBaseImpl<Long, EpaypaTRr> implements EpaypaTRrDao {
	static private final String CLASSNAME = EpaypaTRrDaoImpl.class.getSimpleName();
	@Override
	public Long countAllByFilter ( RichiestaRevocheFilterDto filter, String codUtente ) throws PersistenceException {
		String methodName = "countAllByFilter";
		
		
		

		Long num = null;

		try {

			// costruisce la query dinamicamente
			CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> cquery = cbuilder.createQuery(Long.class);
			Root<EpaypaTRr> root = cquery.from(EpaypaTRr.class);
			cquery.select(cbuilder.count(root));
			List<Predicate> predicates = EpaypaTRrCommon.buildPredicates(filter, cbuilder, root);
			//  Predicate userRestrictions = EpaypaTRrCommon.buildUserRestrictions(codUtente, cbuilder, root);
			//            if (userRestrictions != null) {
			//                predicates.add(userRestrictions);
			//            }
			if (predicates != null && !predicates.isEmpty()) {
				cquery.where(cbuilder.and(predicates.toArray(new Predicate[predicates.size()])));
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
	@SuppressWarnings ( "deprecation" )
	public void insertRR ( RichiestaDiRevocaDto richiestaRevoca, EpaypaTTipoRevoca tipoRevocaEntity ) throws PersistenceException {

		EpaypaTRr transazioneTtRrEntity = new EpaypaTRr ();


		transazioneTtRrEntity.setApplicationId ( richiestaRevoca.getApplicationId () );
		transazioneTtRrEntity.setCodiceContestoPagamento ( richiestaRevoca.getCodiceContestoPagamento() );
		transazioneTtRrEntity.setCodiceIdentificativoUnivocoAttestante  ( richiestaRevoca.getCodiceContestoPagamento ());

		XMLGregorianCalendar cal=richiestaRevoca.getDataOraMessaggioRevoca ();
		Timestamp ts = new Timestamp ( cal.getYear () , cal.getMonth (), cal.getDay (), cal.getHour (), cal.getMinute (), cal.getSecond (), 0 );
		transazioneTtRrEntity.setDataOraMessaggioRevoca ( ts );

		transazioneTtRrEntity.setDenominazioneIstitutoAttestante ( richiestaRevoca.getIstitutoAttestante ().getRagioneSociale () );
		transazioneTtRrEntity.setIdentificativoMessaggioRevoca ( richiestaRevoca.getIdentificativoMessaggioRevoca () );
		transazioneTtRrEntity.setIuv  ( richiestaRevoca.getIUV());
		transazioneTtRrEntity.setIdentificativoDominio ( richiestaRevoca.getIdentificativoDominio () );


		transazioneTtRrEntity.setTipoRevoca( tipoRevocaEntity.getId() );
		transazioneTtRrEntity.setImportoTotaleTevocato ( richiestaRevoca.getImportoPagato () );
		transazioneTtRrEntity.setXmlRr ( richiestaRevoca.getXml () );
		//   transazioneTtRrEntity.setTipoRevoca ( tipoRevocaEntity.getId () );

		transazioneTtRrEntity.setIdRr ( generateUniqueId()    );

		//entityManager.persist ( transazioneTtRrEntity );

		entityManager.getTransaction ().commit ();
	}

	public static long generateUniqueId() {      
		UUID idOne = UUID.randomUUID();
		String str=""+idOne;        
		long uid=str.hashCode();
		String filterStr=""+uid;
		str=filterStr.replaceAll("-", "");
		return Long.parseLong(str);
	}

	@Override
	//@formatter:off
	public List<EpaypaTRr> findAllLightByFilter(RichiestaRevocheFilterDto filter, PaginazioneDto pag, String codUtente)
					throws PersistenceException
					//@formatter:on
					{
		String methodName = "findAllLightByFilter";
		
		
		//    lw.addParam("ord", ord);
		
		

		List<EpaypaTRr> entityList = new ArrayList<EpaypaTRr>();

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if (pag != null && pag.getNumeroPagina() != null && pag.getNumeroPagina() < 1) {
				log.warn("numero di pagina richiesto zero o negativo");

			} else if (pag != null && pag.getNumeroRighePerPagina() != null && pag.getNumeroRighePerPagina() < 1) {
				log.warn("numero di righe per pagina richieste zero o negativo");

			} else {               
				// costruisce la query dinamicamente
				CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
				CriteriaQuery<EpaypaTRr> cquery = cbuilder.createQuery(EpaypaTRr.class);
				Root<EpaypaTRr> root = cquery.from(EpaypaTRr.class);
				cquery.select(root);
				List<Predicate> predicates = EpaypaTRrCommon.buildPredicates(filter, cbuilder, root);
				//List<Predicate> predicates = buildPredicates(filter, cbuilder, root);
				// Predicate userRestrictions = buildUserRestrictions(codUtente, cbuilder, root);
				// if (userRestrictions != null) {
				//                    predicates.add(userRestrictions);
				//                }
				if (predicates != null && !predicates.isEmpty()) {
					cquery.where(cbuilder.and(predicates.toArray(new Predicate[predicates.size()])));
				}


				// assegna i parametri di paginazione
				TypedQuery<EpaypaTRr> query = entityManager.createQuery(cquery);
				if (pag != null) {
					if (pag.getNumeroPagina() != null) {
						query.setFirstResult((pag.getNumeroPagina() - 1) * pag.getNumeroRighePerPagina());
					}
					if (pag.getNumeroRighePerPagina() != null) {
						//EPAY-120-2
						//                        if(pag.getNumeroRighePerPagina() > 500)
						//                            query.setMaxResults((int) MAX_RESULTS);
						//                        else 
						query.setMaxResults(pag.getNumeroRighePerPagina());
					}
				}

				// esegue la query
				entityList = query.getResultList();


				//<!-- CSI_PAG-184 -->
				for (EpaypaTRr e : entityList) {
					if(e.getIdRr ()!=null) {//TODO
						TypedQuery<EpaypaTNotificaPagamento> queryNotificaPagamento = entityManager.createNamedQuery("EpaypaTNotificaPagamento.findAllRevoca", EpaypaTNotificaPagamento.class);
						queryNotificaPagamento.setParameter("idRr", e.getIdRr ());
						List<EpaypaTNotificaPagamento> entityListNotificaPagamento = new ArrayList<EpaypaTNotificaPagamento>();
						entityListNotificaPagamento = queryNotificaPagamento.getResultList();
						for (EpaypaTNotificaPagamento tr:entityListNotificaPagamento) {

							if(tr.getEpaypaTSoggettoDebitore ()!=null) {
								String nomecognome=tr.getEpaypaTSoggettoDebitore ().getNome ()+" "+tr.getEpaypaTSoggettoDebitore ().getCognomeRagSoc ();
								String codiceFiscale=tr.getEpaypaTSoggettoDebitore ().getIdUnivocoFiscale ();
								e.setNomeCognome ( nomecognome );
								e.setCodiceFiscale ( codiceFiscale );
								entityList.set ( entityList.indexOf ( e ) , e );

							}
						}
					}
				}






			}
		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			throw new PersistenceException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return entityList;
					}

	private List<Long> findByCognomeOrRagioneSociale(String CognomeOrRagioneSociale) {

		List<EpaypaTSoggetto> entityList = new ArrayList<EpaypaTSoggetto>();

		//EpaypaTSoggetto s,EpaypaTNotificaPagamento n
		CriteriaBuilder cbuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<EpaypaTSoggetto> cquery = cbuilder.createQuery(EpaypaTSoggetto.class);
		Root<EpaypaTSoggetto> root = cquery.from(EpaypaTSoggetto.class);
		cquery.select(root);

		List<Predicate> predicates = null;

		if (CognomeOrRagioneSociale != null) {
			predicates = new ArrayList<Predicate>();

			if (CognomeOrRagioneSociale != null && !CognomeOrRagioneSociale.isEmpty ()) {
				predicates.add(cbuilder.like(root.<String>get ( "cognomeRagSoc" ), CognomeOrRagioneSociale));
			}

		}
		if (predicates != null && !predicates.isEmpty()) {
			cquery.where(cbuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		}

		// assegna i parametri di paginazione
		TypedQuery<EpaypaTSoggetto> query = entityManager.createQuery(cquery);
		// esegue la query
		entityList = query.getResultList();

		List<Long> idRRList= new ArrayList<> ();
		for(EpaypaTSoggetto soggetto : entityList) {

			idRRList.addAll ( 
				entityManager.createQuery("SELECT distinct e.idRR FROM EpaypaTNotificaPagamento e where epaypaTSoggettoDebitore= :id_soggetto_debitore")
				.setParameter ( "id_soggetto_debitore", soggetto.getIdSoggetto () )
				.getResultList() );


		}


		return idRRList;
	}


}
