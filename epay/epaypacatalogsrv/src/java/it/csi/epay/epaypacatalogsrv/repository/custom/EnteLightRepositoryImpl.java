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
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento_;
import it.csi.epay.epaypacatalogsrv.model.EnteLight;
import it.csi.epay.epaypacatalogsrv.model.EnteLight_;
import it.csi.epay.epaypacatalogsrv.model.Ente_;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile_;
import it.csi.epay.epaypacatalogsrv.repository.EnteLightRepository;


public class EnteLightRepositoryImpl implements EnteLightRepositoryCustom {

    @Autowired
    private EnteLightRepository enteLightRepository;

    @Override
    public List<EnteLight> ricercaEntiConRiferimentoContabileSecondario (String enteDaEscludere) {

        Specification<EnteLight> spec = new Specification<EnteLight> () {

            @Override
            public Predicate toPredicate ( Root<EnteLight> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
                Subquery<RiferimentoContabile> subquery = query.subquery ( RiferimentoContabile.class );
                Root<RiferimentoContabile> rootRifContabile = subquery.from ( RiferimentoContabile.class );
                Join<RiferimentoContabile, CodiceVersamento> codiceVersamento = rootRifContabile.join ( RiferimentoContabile_.codiceVersamento );

                Date now = new Date ();

                Predicate predicate = cb.and (

                    cb.or (
                        cb.isFalse ( rootRifContabile.get ( RiferimentoContabile_.flagAnnullato ) ),
                        cb.isNull ( rootRifContabile.get ( RiferimentoContabile_.flagAnnullato ) ) ),

                    cb.lessThanOrEqualTo  ( rootRifContabile.get ( RiferimentoContabile_.dataInizioValidita ), now ),
                    cb.or (
                        cb.greaterThanOrEqualTo ( rootRifContabile.get ( RiferimentoContabile_.dataFineValidita ), now ),
                        cb.isNull ( rootRifContabile.get ( RiferimentoContabile_.dataFineValidita ) ) ),
                    cb.equal ( root.get ( EnteLight_.id ), codiceVersamento.get ( CodiceVersamento_.ente ).get ( Ente_.id ) ),
                    cb.notEqual( root.get ( EnteLight_.codiceFiscale ), enteDaEscludere ),
                    cb.isTrue ( codiceVersamento.get ( CodiceVersamento_.flagMbSecondario ) ),
                    cb.isTrue ( rootRifContabile.get ( RiferimentoContabile_.flagMbSecondario ) )

                );
                subquery.select ( rootRifContabile ).where ( predicate );
                predicate = cb.exists ( subquery );
                List<Order> orderBys = new ArrayList<> ();
                orderBys.add ( cb.asc ( root.get ( EnteLight_.denominazione ) ) );
                query.orderBy ( orderBys );

                return predicate;
            }
        };
        return enteLightRepository.findAll ( spec );
    }

    @Override
    public List<EnteLight> findEntiWithRiferimentiContabiliInScadenza ( Long maxNumDay ) {

        Specification<EnteLight> spec = new Specification<EnteLight> () {

            @Override
            public Predicate toPredicate ( Root<EnteLight> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
                Subquery<RiferimentoContabile> subquery = query.subquery ( RiferimentoContabile.class );
                Root<RiferimentoContabile> rootRifContabile = subquery.from ( RiferimentoContabile.class );
                Join<RiferimentoContabile, CodiceVersamento> codiceVersamento = rootRifContabile.join ( RiferimentoContabile_.codiceVersamento );

                Date now = new Date ();
                //                Integer maxNumDay = 3;//passare come parametro? Mettere in una tabella di configurazione?
                Date dataMax = DateUtils.addDays ( now, maxNumDay.intValue () );

                Predicate predicate = cb.and (
                    cb.or (
                        cb.isFalse ( codiceVersamento.get ( CodiceVersamento_.flagAnnullato ) ),
                        cb.isNull ( codiceVersamento.get ( CodiceVersamento_.flagAnnullato ) ) ),
                    cb.or (
                        cb.isFalse ( rootRifContabile.get ( RiferimentoContabile_.flagAnnullato ) ),
                        cb.isNull ( rootRifContabile.get ( RiferimentoContabile_.flagAnnullato ) ) ),
                    cb.lessThan ( rootRifContabile.get ( RiferimentoContabile_.dataInizioValidita ), now ),
                    cb.between ( rootRifContabile.get ( RiferimentoContabile_.dataFineValidita ), now, dataMax ),
                    cb.equal ( root.get ( EnteLight_.id ), codiceVersamento.get ( CodiceVersamento_.ente ).get ( Ente_.id ) )

                );
                subquery.select ( rootRifContabile ).where ( predicate );
                predicate = cb.exists ( subquery );

                return predicate;
            }
        };
        return enteLightRepository.findAll ( spec );
    }

}
