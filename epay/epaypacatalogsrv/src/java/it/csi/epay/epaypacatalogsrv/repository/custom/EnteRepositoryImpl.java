/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository.custom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.util.CollectionUtils;

import it.csi.epay.epaypacatalogsrv.dto.ente.GetEnteInput;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento_;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.Ente_;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile_;
import it.csi.epay.epaypacatalogsrv.model.EnteLight;
import it.csi.epay.epaypacatalogsrv.model.EnteLight_;
import it.csi.epay.epaypacatalogsrv.model.Ente_;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile_;
import it.csi.epay.epaypacatalogsrv.repository.EnteLightRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;

public class EnteRepositoryImpl implements EnteRepositoryCustom {

    @Autowired
    private EnteRepository baseRepository;

	@Override
    public List<Ente> search ( GetEnteInput input ) {

		List<Specification<Ente>> filters = new ArrayList<>();

        //        if ( !StringUtils.isBlank ( input.getCodiceFiscale () ) ) {
        //            filters.add ( CriteriaBuilderUtil.likeCaseInsensitive ( Ente_.codiceFiscale, input.getCodiceFiscale () ) );
        //		}
        //        if ( !StringUtils.isBlank ( input.getDenominazione () ) ) {
        //            filters.add ( CriteriaBuilderUtil.likeCaseInsensitive ( Ente_.denominazione, input.getDenominazione () ) );
        //		}
        //        if ( !StringUtils.isBlank ( input.getLocalita () ) ) {
        //            filters.add ( CriteriaBuilderUtil.likeCaseInsensitive ( Ente_.localita, input.getLocalita () ) );
        //		}
        //        if ( !StringUtils.isBlank ( input.getIndirizzo () ) ) {
        //            filters.add ( CriteriaBuilderUtil.likeCaseInsensitive ( Ente_.indirizzo, input.getIndirizzo () ) );
        //		}
        //        if ( !StringUtils.isBlank ( input.getCap () ) ) {
        //            filters.add ( CriteriaBuilderUtil.likeCaseInsensitive ( Ente_.cap, input.getCap () ) );
        //		}
        //        if ( !StringUtils.isBlank ( input.getSiglaProvincia () ) ) {
        //            filters.add ( CriteriaBuilderUtil.likeCaseInsensitive ( Ente_.siglaProvincia, input.getSiglaProvincia () ) );
        //		}

		List<Ente> records;

		if (!CollectionUtils.isEmpty ( filters)) {
			Specification<Ente> filterSpecification = filters.get(0);
	        for (int i = 1; i < filters.size(); i++) {
	        	filterSpecification = Specifications.where(filterSpecification).and(filters.get(i));
	        }

			records = baseRepository.findAll(filterSpecification);
		} else {
			records = baseRepository.findAll();
		}

		return records;
	}

//	@Override
//	public List<Ente> ricercaEntiConRiferimentoContabileSecondario() {
//		
//		Specification<Ente> spec = new Specification<Ente> () {
//
//    		@Override
//    		public Predicate toPredicate ( Root<Ente> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
////    			//                Join<RiferimentoContabile, CodiceVersamento> codiceVersamento = root.join ( RiferimentoContabile_.codiceVersamento );
////
//              Date now = new Date();
//              
//              Predicate predicate = cb.conjunction ();
////              Join<Ente, CodiceVersamento> jointEnteCodiceVersamento = root.join ( Ente_.codiciVersamento);
//              Subquery<RiferimentoContabile> subquery = query.subquery(RiferimentoContabile.class);
//			  Root<RiferimentoContabile> rootRifContabile = subquery.from(RiferimentoContabile.class);
//			  Predicate subqueryPredicate = cb.conjunction ();
//			  
//			  
//			  subqueryPredicate = cb.equal(rootRifContabile.get(RiferimentoContabile_.codiceVersamento).get ( CodiceVersamento_.ente ).get ( Ente_.id ), root.get(Ente_.id));
//			  
//			  subqueryPredicate = cb.and ( subqueryPredicate,
//					  cb.or (
//							  cb.isFalse (rootRifContabile.get ( RiferimentoContabile_.flagAnnullato )),
//						        cb.isNull (rootRifContabile.get ( RiferimentoContabile_.flagAnnullato ))
//						)
//					  , cb.lessThanOrEqualTo( rootRifContabile.get ( RiferimentoContabile_.dataInizioValidita ),now )
//					  ,cb.or (
//							 cb.greaterThanOrEqualTo( rootRifContabile.get ( RiferimentoContabile_.dataFineValidita), now ),
//							cb.isNull ( rootRifContabile.get ( RiferimentoContabile_.dataFineValidita ) ) 
//							)
//					  ,cb.isTrue( rootRifContabile.get ( RiferimentoContabile_.flagMbSecondario))
//					  
//					 				  
//					  );
//			  subquery.select(rootRifContabile).where(subqueryPredicate);
//			  predicate = cb.and ( predicate, cb.exists ( subquery ) );
//              
////			  predicate =cb.and (predicate,  
////				cb.or (
////						cb.isFalse (jointEnteCodiceVersamento.get ( CodiceVersamento_.flagAnnullato )),
////				        cb.isNull (jointEnteCodiceVersamento.get ( CodiceVersamento_.flagAnnullato ))
////						)) ;
////
////    			return  cb.and (
////    					cb.or (
////    							cb.isFalse ( root.get ( CodiceVersamento_.flagAnnullato ) ),
////    							cb.isNull ( root.get ( CodiceVersamento_.flagAnnullato ) ) 
////    							)
//////    					cb.lessThanOrEqualTo( root.get ( RiferimentoContabile_.dataInizioValidita), now ),
//////    					cb.or (
//////    							 cb.greaterThanOrEqualTo( root.get ( RiferimentoContabile_.dataFineValidita), now ),
//////    							cb.isNull ( root.get ( RiferimentoContabile_.dataFineValidita ) ) 
//////    							),
//////    					cb.equal(root.get ( RiferimentoContabile_.annoEsercizio),annoEsercizio ),
//////
//////    					cb.equal (root.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.id ),idCodiceVersamento )
//////
////    					);
//			  
//			  return predicate;
//    		}
//    	};
//    	return  baseRepository.findAll(spec);
//		
//	}
//	
//	
////	@Override
////	public List<Ente> findEntiWithRiferimentiContabiliInScadenza() {
////		
////		
////		 Specification<Ente> spec = new Specification<Ente> () {
////
////			@Override
////			public Predicate toPredicate( Root<Ente> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
////				    query.distinct(true);
////				
////				  Predicate predicate = cb.conjunction ();
////				  
////				  Join<Ente, CodiceVersamento> jointEnteCodiceVersamento = root.join ( Ente_.codiciVersamentoCompleti);
////				  
//////				  Predicate annFalse=  cb.equal (jointEnteCodiceVersamento.get ( CodiceVersamento_.flagAnnullato ), new Boolean(false) ) ;
//////				  
//////				  Predicate annNull=  cb.isNull (jointEnteCodiceVersamento.get ( CodiceVersamento_.flagAnnullato )) ;
//////				  
//////				  Predicate predicateOrCovAnn = cb.or ( annFalse, annNull );
//////				  
//////				  predicate = cb.and(predicateOrCovAnn);
////				  
////				  
////				  predicate =cb.and (predicate,  
////							cb.or (
////									cb.isFalse (jointEnteCodiceVersamento.get ( CodiceVersamento_.flagAnnullato )),
////							        cb.isNull (jointEnteCodiceVersamento.get ( CodiceVersamento_.flagAnnullato ))
////									)) ;
////				  
////				  
////				  Subquery<RiferimentoContabile> subquery = query.subquery(RiferimentoContabile.class);
////				  Root<RiferimentoContabile> rootRifContabile = subquery.from(RiferimentoContabile.class);
////				  
////				  Predicate subqueryPredicate = cb.conjunction ();
//////				  
////				  Date now = new Date ();
////				  Integer maxNumDay = new Integer(3);//passare come parametro? Mettere in una tabella di configurazione?
////			      Date dataMax= DateUtils.addDays(now, null != maxNumDay? maxNumDay.intValue(): 0);
////				  
////				  subqueryPredicate = cb.equal(rootRifContabile.get(RiferimentoContabile_.codiceVersamento).get ( CodiceVersamento_.ente ).get ( Ente_.id ), root.get(Ente_.id));
////				  
////				  subqueryPredicate = cb.and ( subqueryPredicate,
////				  cb.or (
////						  cb.isFalse (rootRifContabile.get ( RiferimentoContabile_.flagAnnullato )),
////					        cb.isNull (rootRifContabile.get ( RiferimentoContabile_.flagAnnullato ))
////					)
//////				  ,
////				  ,cb.lessThan( rootRifContabile.get ( RiferimentoContabile_.dataInizioValidita ),now )
////				  ,cb.between( rootRifContabile.get ( RiferimentoContabile_.dataFineValidita ), now, dataMax)
////				  
////				  );
////				  
////
//////				  
////				  subquery.select(rootRifContabile).where(subqueryPredicate);
//////				  
////				  predicate = cb.and ( predicate, cb.exists ( subquery ) );
////				  
////				 
//////				  subqueryPredicate = cb.and ( subqueryPredicate,
//////				  cb.or (
//////						  cb.isNull ( rootRifContabile.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.flagAnnullato )),
//////						  cb.isFalse ( rootRifContabile.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.flagAnnullato ))
//////					),
//////				  cb.lessThan( rootRifContabile.get ( RiferimentoContabile_.dataFineValidita ),now ),
//////				  cb.between( rootRifContabile.get ( RiferimentoContabile_.dataFineValidita ), now, dataMax)
//////				  
//////				  );
////				  
//////				  Predicate predicate1_2 = cb.and(
//////						  cb.or (
//////								  cb.isNull ( rootRifContabile.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.flagAnnullato )),
//////								  cb.isFalse ( rootRifContabile.get ( RiferimentoContabile_.codiceVersamento ).get ( CodiceVersamento_.flagAnnullato ))
//////	    					)
//////
//////						  );
////				 
//////				  
//////				  Predicate predicate1_3 = cb.and (
//////	    					cb.lessThan( rootRifContabile.get ( RiferimentoContabile_.dataFineValidita ),now )
//////	    					);  
//////						  
//////				  Predicate predicate1_4 = 	 cb.and (
//////	    					cb.between( rootRifContabile.get ( RiferimentoContabile_.dataFineValidita ), now, dataMax)
//////	    					);
////
//////				  subquery.select(rootRifContabile).where(predicate1, predicate2);
////				  
////				  
//////				  Subquery<Operation> subquery = query.subquery(Operation.class);
//////                  Root<Operation> operation = subquery.from(Operation.class);
//////
//////                  Predicate predicate1_1 = builder.equal(operation.get("verificateur1").get("id"), root.get("id"));
//////                  Predicate predicate1_2 = builder.equal(operation.get("verificateur2").get("id"), root.get("id"));
//////  
//////                  Predicate predicate1 = builder.or(predicate1_1, predicate1_2); 
//////                  Predicate predicate2 = operation.get("id").in(operationIds).not();
//////  
//////                  subquery.select(operation).where(predicate1, predicate2);
//////  
//////                  return builder.exists(subquery);
////				  
////				  
//////				  Root<RiferimentoContabile> rootCov = query.from ( RiferimentoContabile.class );
//////				  
//////				  Join<RiferimentoContabile, CodiceVersamento> jointCodiceVersamentoRifContabile = 
//////						  jointEnteCodiceVersamento.join ( RiferimentoContabile_.codiceVersamento);
////				  
//////             	 Join<CodiceVersamento, RiferimentoContabile> joinRifContabile = root.join( "");
////				  
//////		            predicate = cb.and ( predicate, cb.equal ( jointEnteCodiceVersamento.get ( CodiceVersamento_.flagAnnullato ), new Boolean(false) ) );
//////		            predicate = ;
////		            
//////		            Join<Ente, CodiceVersamento> covAttivo = root.join ( Ente_.codiciVersamento );
//////		            
//////                    Predicate predicateIdentificativoPSP = cb.like ( cb.lower ( psp.get ( CblTPsp_.identificativoPsp ) ),
//////                        "%" + ricerca.getPsp ().toLowerCase () + "%" );
//////                    Predicate predicateDescrizionePSP = cb.like ( cb.lower ( psp.get ( CblTPsp_.denominazionePsp ) ),
//////                        "%" + ricerca.getPsp ().toLowerCase () + "%" );
//////                    Predicate predicateOrPsp = cb.or ( predicateIdentificativoPSP, predicateDescrizionePSP );
//////                    predicateSub = cb.and ( predicateSub, predicateOrPsp );
////                    
////                    
////				return predicate;
////			}
////			 
////			 
////		 };
////		 
////		 
////		 List<Ente> enti= baseRepository.findAll(spec);
////		
////		return enti;
////	}
////}
//	
	
	
}
