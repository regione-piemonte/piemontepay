/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.RicercaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento_;
import it.csi.epay.epaypacatalogsrv.model.Ente_;
import it.csi.epay.epaypacatalogsrv.model.MacrotipoPpay_;
import it.csi.epay.epaypacatalogsrv.model.ModalitaIntegrazione_;
import it.csi.epay.epaypacatalogsrv.model.TematicaPpay_;
import it.csi.epay.epaypacatalogsrv.model.VoceEntrata;
import it.csi.epay.epaypacatalogsrv.model.VoceEntrata_;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.util.CriteriaBuilderUtil;


public class CodiceVersamentoRepositoryImpl implements CodiceVersamentoRepositoryCustom {

	@Autowired
	private CodiceVersamentoRepository baseRepository;

	@Override
	public List<CodiceVersamento> ricerca ( RicercaCodiceVersamentoInput input, Long idEnte ) {

		List<Specification<CodiceVersamento>> filters = new ArrayList<> ();

		filters.add ( new Specification<CodiceVersamento> () {

			@Override
			public Predicate toPredicate ( Root<CodiceVersamento> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
				return builder.or (
					builder.isNull ( root.get ( CodiceVersamento_.flagAnnullato ) ),
					builder.isFalse ( root.get ( CodiceVersamento_.flagAnnullato ) ) );
			}
		} );

		filters.add ( new Specification<CodiceVersamento> () {

			@Override
			public Predicate toPredicate ( Root<CodiceVersamento> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
				return builder.equal (
					root.get ( CodiceVersamento_.ente ).get ( Ente_.id ), idEnte );
			}
		} );

		//        filters.add ( CriteriaBuilderUtil.isNull ( CodiceVersamento_.codiceVersamentoPadre ) );

		if ( input.getId () != null ) {
			filters.add ( new Specification<CodiceVersamento> () {

				@Override
				public Predicate toPredicate ( Root<CodiceVersamento> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.equal (
						root.get ( CodiceVersamento_.id ), input.getId () );
				}
			} );
		}

		if ( !StringUtils.isBlank ( input.getCodice () ) ) {
			filters.add ( CriteriaBuilderUtil.likeCaseInsensitive ( CodiceVersamento_.codice, input.getCodice () ) );
		}

		if ( !StringUtils.isBlank ( input.getDescrizione () ) ) {
			filters.add ( CriteriaBuilderUtil.likeCaseInsensitive ( CodiceVersamento_.descrizione, input.getDescrizione () ) );
		}

		if ( !StringUtils.isBlank ( input.getIban () ) ) {
			filters.add ( CriteriaBuilderUtil.likeCaseInsensitive ( CodiceVersamento_.iban, input.getIban () ) );
		}

		if ( !StringUtils.isBlank ( input.getCodiceVoceEntrata () ) ) {
			filters.add ( new Specification<CodiceVersamento> () {

				@Override
				public Predicate toPredicate ( Root<CodiceVersamento> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.equal (
						builder.lower ( root.get ( CodiceVersamento_.voceEntrata ).get ( VoceEntrata_.codice ) ),
						input.getCodiceVoceEntrata ().toLowerCase () );
				}
			} );
		}

		if ( !StringUtils.isBlank ( input.getCodiceModalitaIntegrazione () ) ) {
			filters.add ( new Specification<CodiceVersamento> () {

				@Override
				public Predicate toPredicate ( Root<CodiceVersamento> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.equal (
						builder.lower ( root.get ( CodiceVersamento_.modalitaIntegrazione ).get ( ModalitaIntegrazione_.codice ) ),
						input.getCodiceVoceEntrata ().toLowerCase () );
				}
			} );
		}

		if ( !StringUtils.isBlank ( input.getCodiceMacrotipo () ) ) {
			filters.add ( new Specification<CodiceVersamento> () {

				@Override
				public Predicate toPredicate ( Root<CodiceVersamento> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.equal (
						builder.lower ( root.get ( CodiceVersamento_.voceEntrata )
							.get ( VoceEntrata_.macrotipo )
							.get ( MacrotipoPpay_.codice ) ),
						input.getCodiceMacrotipo ().toLowerCase () );
				}
			} );
		}

		if ( !StringUtils.isBlank ( input.getCodiceTematica () ) ) {
			filters.add ( new Specification<CodiceVersamento> () {

				@Override
				public Predicate toPredicate ( Root<CodiceVersamento> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.equal (
						builder.lower ( root.get ( CodiceVersamento_.voceEntrata )
							.get ( VoceEntrata_.tematica )
							.get ( TematicaPpay_.codice ) ),
						input.getCodiceTematica ().toLowerCase () );
				}
			} );
		}
		//
		//        filters.add ( new Specification<CodiceVersamento> () {
		//
		//            @Override
		//            public Predicate toPredicate ( Root<CodiceVersamento> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
		//
		//                Fetch<CodiceVersamento, CodiceVersamento> fetchedCVC = root.fetch ( CodiceVersamento_.codiciVersamentoCollegati, JoinType.LEFT );
		//                fetchedCVC.fetch ( CodiceVersamento_.statoAggiornamento, JoinType.LEFT );
		//
		//                Fetch<CodiceVersamento, CodiceVersamento> fetchedCVP = root.fetch ( CodiceVersamento_.codiceVersamentoPadre, JoinType.LEFT );
		//                fetchedCVP.fetch ( CodiceVersamento_.statoAggiornamento, JoinType.LEFT );
		//
		//                root.fetch ( CodiceVersamento_.tipoPagamento, JoinType.LEFT );
		//                root.fetch ( CodiceVersamento_.statoAggiornamento, JoinType.LEFT );
		//
		//                Fetch<CodiceVersamento, VoceEntrata> fetchedVE = root.fetch ( CodiceVersamento_.voceEntrata, JoinType.LEFT );
		//                fetchedVE.fetch ( VoceEntrata_.tematica, JoinType.LEFT );
		//                fetchedVE.fetch ( VoceEntrata_.macrotipo, JoinType.LEFT );
		//
		//                return builder.conjunction ();
		//            }
		//        } );
		
		filters.add ( new Specification<CodiceVersamento> () {
            @Override
            public Predicate toPredicate ( Root<CodiceVersamento> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {

                root.fetch ( CodiceVersamento_.tipoPagamento, JoinType.LEFT );
                root.fetch ( CodiceVersamento_.statoAggiornamento, JoinType.LEFT );
                root.fetch ( CodiceVersamento_.modalitaIntegrazione, JoinType.LEFT );
                root.fetch ( CodiceVersamento_.statoMultibeneficiario, JoinType.LEFT );
                root.fetch ( CodiceVersamento_.codiceVersamentoPadre, JoinType.LEFT );

                Fetch<CodiceVersamento, VoceEntrata> fetchedVE = root.fetch ( CodiceVersamento_.voceEntrata, JoinType.LEFT );
                fetchedVE.fetch ( VoceEntrata_.tematica, JoinType.LEFT );
                fetchedVE.fetch ( VoceEntrata_.macrotipo, JoinType.LEFT );

                return builder.conjunction ();
            }
        } );
			
		List<CodiceVersamento> records = baseRepository.findAll ( CriteriaBuilderUtil.groupAnd ( filters ) );
		return records;
	}

//	@Override
//	public List<CodiceVersamento> ricercaCovSecondariPerEnte(Long idEnte) {
//		
//		 Specification<CodiceVersamento> spec = new Specification<CodiceVersamento> () {
//
//	            @Override
//	            public Predicate toPredicate ( Root<CodiceVersamento> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
//	                Subquery<RiferimentoContabile> subquery = query.subquery ( RiferimentoContabile.class );
//	                Root<RiferimentoContabile> rootRifContabile = subquery.from ( RiferimentoContabile.class );
////	                Join<RiferimentoContabile, CodiceVersamentoLight> codiceVersamento = rootRifContabile.join ( RiferimentoContabile_.codiceVersamento );
////
//	                Date now = new Date ();
////
//	                Predicate predicate = cb.and (
//	                  
//	                    cb.or (
//	                        cb.isFalse ( rootRifContabile.get ( RiferimentoContabile_.flagAnnullato ) ),
//	                        cb.isNull ( rootRifContabile.get ( RiferimentoContabile_.flagAnnullato ) ) )
////	                    
//	                    ,cb.lessThan ( rootRifContabile.get ( RiferimentoContabile_.dataInizioValidita ), now )
//	                    ,cb.or (
//							 cb.greaterThanOrEqualTo( rootRifContabile.get ( RiferimentoContabile_.dataFineValidita), now ),
//							cb.isNull ( rootRifContabile.get ( RiferimentoContabile_.dataFineValidita ) ) 
//							)
//	                    ,cb.equal ( root.get ( CodiceVersamento_.ente ).get ( Ente_.id ), idEnte )
//	                    ,cb.equal ( root.get ( CodiceVersamento_.id ), rootRifContabile.get (RiferimentoContabile_.codiceVersamento ).get(CodiceVersamento_.id ))
//	                    , cb.isTrue( root.get ( CodiceVersamento_.flagMbSecondario))
//	                   , cb.isTrue( rootRifContabile.get ( RiferimentoContabile_.flagMbSecondario))
//	                    
////	                    cb.equal ( root.get ( CodiceVersamento_. ), codiceVersamento.get ( CodiceVersamento_.ente ).get ( Ente_.id ) )
////	                    cb.equal ( root.get ( EnteLight_.id ), codiceVersamento.get ( CodiceVersamento_.ente ).get ( Ente_.id ) )
////	                   , cb.isTrue( rootRifContabile.get ( RiferimentoContabile_.flagMbSecondario))
////
//	                );
//	                subquery.select ( rootRifContabile ).where ( predicate );
//	                predicate = cb.exists ( subquery );
//	                
//	                List<Order> orderBys = new ArrayList<Order>() ;
//	                orderBys.add(cb.asc( root.get (  CodiceVersamento_.codice)));
//	                orderBys.add(cb.asc( root.get (  CodiceVersamento_.descrizione)));
//	                query.orderBy(orderBys) ;
//	                
//
//	                return predicate;
//	            }
//	        };
//	        return baseRepository.findAll ( spec );
//	}
}
