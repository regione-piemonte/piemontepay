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
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import it.csi.epay.epaypacatalogsrv.model.CodiceVersamentoLight;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamentoLight_;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento_;
import it.csi.epay.epaypacatalogsrv.model.Ente_;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile_;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoLightRepository;

public class CodiceVersamentoLightRepositoryImpl implements CodiceVersamentoLightRepositoryCustom{

    @Autowired
    private CodiceVersamentoLightRepository codiceVersamentoLightRepository;
    
    
    @Override
	public List<CodiceVersamentoLight> ricercaCovSecondariPerEnte(Long idEnte) {
		
		 Specification<CodiceVersamentoLight> spec = new Specification<CodiceVersamentoLight> () {

	            @Override
	            public Predicate toPredicate ( Root<CodiceVersamentoLight> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
	                Subquery<RiferimentoContabile> subquery = query.subquery ( RiferimentoContabile.class );
	                Root<RiferimentoContabile> rootRifContabile = subquery.from ( RiferimentoContabile.class );
//	                Join<RiferimentoContabile, CodiceVersamento> codiceVersamento = rootRifContabile.join ( RiferimentoContabile_.codiceVersamento );
//
	                Date now = new Date ();
//
	                Predicate predicate = cb.and (
	                  
	                    cb.or (
	                        cb.isFalse ( rootRifContabile.get ( RiferimentoContabile_.flagAnnullato ) ),
	                        cb.isNull ( rootRifContabile.get ( RiferimentoContabile_.flagAnnullato ) ) )
//	                    
	                    ,cb.lessThanOrEqualTo ( rootRifContabile.get ( RiferimentoContabile_.dataInizioValidita ), now )
	                    ,cb.or (
							 cb.greaterThanOrEqualTo( rootRifContabile.get ( RiferimentoContabile_.dataFineValidita), now ),
							cb.isNull ( rootRifContabile.get ( RiferimentoContabile_.dataFineValidita ) ) 
							)
	                    ,cb.equal ( root.get ( CodiceVersamentoLight_.ente ).get ( Ente_.id ), idEnte )
	                    ,cb.equal ( root.get ( CodiceVersamentoLight_.id ), rootRifContabile.get (RiferimentoContabile_.codiceVersamento ).get(CodiceVersamento_.id ))
	                    , cb.isTrue( root.get ( CodiceVersamentoLight_.flagMbSecondario))
	                   , cb.isTrue( rootRifContabile.get ( RiferimentoContabile_.flagMbSecondario))
	                    
//
	                );
	                subquery.select ( rootRifContabile ).where ( predicate );
	                predicate = cb.exists ( subquery );
	                
	                List<Order> orderBys = new ArrayList<Order>() ;
	                orderBys.add(cb.asc( root.get (  CodiceVersamentoLight_.codice)));
	                orderBys.add(cb.asc( root.get (  CodiceVersamentoLight_.descrizione)));
	                query.orderBy(orderBys) ;
	                

	                return predicate;
	            }
	        };
	        return codiceVersamentoLightRepository.findAll ( spec );
	}
    
   
	
	
	
}
