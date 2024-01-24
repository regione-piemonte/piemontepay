/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.repository.custom;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.util.Assert;

import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoDettaglioPagopa;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoOriginePagopa;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoOriginePagopa_;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoSintesiPagopa;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoSintesiPagopa_;
import it.csi.epay.epaysim.business.epaysim.repository.SimTFlussoOriginePagopaRepository;
import it.csi.epay.epaysim.business.epaysim.repository.util.CriteriaBuilderUtil;
import it.csi.epay.epaysim.business.epaysim.utils.Costanti;
import it.csi.epay.epaysim.dto.RicercaFlussoInputDTO;


/**
 *
 */

public class SimTFlussoOriginePagopaRepositoryImpl implements SimTFlussoOriginePagopaRepositoryCustom {

    @Autowired
    private SimTFlussoOriginePagopaRepository repository;

    @Override
    public List<SimTFlussoOriginePagopa> findAll ( final RicercaFlussoInputDTO ricercaFlussoInputDTO ) {

        List<SimTFlussoOriginePagopa> result = null;
        List<Specification<SimTFlussoOriginePagopa>> filters = new ArrayList<> ();

        filters.add ( new Specification<SimTFlussoOriginePagopa> () {

            @Override
            public Predicate toPredicate ( Root<SimTFlussoOriginePagopa> root, CriteriaQuery<?> query,
                CriteriaBuilder builder ) {
                //Filtro per ente
                return builder.and ( builder.equal ( root.get ( SimTFlussoOriginePagopa_.cfEnteCreditore ),
                    ricercaFlussoInputDTO.getCaller ().getPrincipal ().getCodiceEnte () ) );
            }
        } );

        if ( !StringUtils.isEmpty ( ricercaFlussoInputDTO.getIdentificativoFlusso () ) ) {
            //            filters.add ( new Specification<SimTFlussoOriginePagopa> () {
            //
            //                @Override
            //                public Predicate toPredicate ( Root<SimTFlussoOriginePagopa> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
            //                    return builder.equal ( root.get ( SimTFlussoOriginePagopa_.identificativoFlusso ), ricercaFlussoInputDTO.getIdentificativoFlusso () );
            //                }
            //            } );
            filters.add (
                CriteriaBuilderUtil.likeSpecification ( SimTFlussoOriginePagopa_.identificativoFlusso, ricercaFlussoInputDTO.getIdentificativoFlusso () ) );
        }

        if ( ricercaFlussoInputDTO.getDateStart () != null ) {
            filters.add ( new Specification<SimTFlussoOriginePagopa> () {

                @Override
                public Predicate toPredicate ( Root<SimTFlussoOriginePagopa> root, CriteriaQuery<?> query,
                    CriteriaBuilder builder ) {
                    return builder.greaterThanOrEqualTo ( root.get ( SimTFlussoOriginePagopa_.dataInserimento ),
                        new Timestamp ( ricercaFlussoInputDTO.getDateStart ().getTime () ) );
                }
            } );
        }

        if ( ricercaFlussoInputDTO.getDateEnd () != null ) {
            filters.add ( new Specification<SimTFlussoOriginePagopa> () {

                @Override
                public Predicate toPredicate ( Root<SimTFlussoOriginePagopa> root, CriteriaQuery<?> query,
                    CriteriaBuilder builder ) {
                    return builder.lessThanOrEqualTo ( root.get ( SimTFlussoOriginePagopa_.dataInserimento ),
                        new Timestamp ( ricercaFlussoInputDTO.getDateEnd ().getTime () ) );
                }
            } );
        }

        if ( !StringUtils.isEmpty ( ricercaFlussoInputDTO.getStatoElaborazioneFlusso () ) ) {
            if ( !Costanti.STATO_FLUSSO_ALL.equals ( ricercaFlussoInputDTO.getStatoElaborazioneFlusso () ) ) {
                filters.add ( new Specification<SimTFlussoOriginePagopa> () {

                    @Override
                    public Predicate toPredicate ( Root<SimTFlussoOriginePagopa> root, CriteriaQuery<?> query,
                        CriteriaBuilder builder ) {
                        return builder.equal ( root.get ( SimTFlussoOriginePagopa_.statoElaborazioneFlusso ),
                            Costanti.STATO_FLUSSO_OK.equals ( ricercaFlussoInputDTO.getStatoElaborazioneFlusso () ) );
                    }
                } );
            }
        } else {
            filters.add ( new Specification<SimTFlussoOriginePagopa> () {

                @Override
                public Predicate toPredicate ( Root<SimTFlussoOriginePagopa> root, CriteriaQuery<?> query,
                    CriteriaBuilder builder ) {
                    return builder.isNull ( root.get ( SimTFlussoOriginePagopa_.statoElaborazioneFlusso ) );
                }
            } );
        }

        if ( filters.isEmpty () ) {
            result = repository.findAll ();
        } else {
            Specification<SimTFlussoOriginePagopa> filterSpecification = filters.get ( 0 );
            for ( int i = 1; i < filters.size (); i++ ) {
                filterSpecification = Specifications.where ( filterSpecification ).and ( filters.get ( i ) );
            }

            result = repository.findAll ( filterSpecification );
        }

        return result;
    }

    @Override
    public SimTFlussoOriginePagopa findOneWithchildren ( Long idSimTFlussoOriginePagopa ) {
        Assert.notNull ( idSimTFlussoOriginePagopa, "idSimTFlussoOriginePagopa deve essere sempre valorizzato. " );
        return repository.findOne ( new Specification<SimTFlussoOriginePagopa> () {

            @SuppressWarnings ( "unused" )
            @Override
            public Predicate toPredicate ( Root<SimTFlussoOriginePagopa> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
                Join<SimTFlussoOriginePagopa, SimTFlussoSintesiPagopa> joinRootResult
                = root.join ( SimTFlussoOriginePagopa_.simTFlussoSintesiPagopas, JoinType.LEFT );
                Join<SimTFlussoSintesiPagopa, SimTFlussoDettaglioPagopa> joinChildResult
                = joinRootResult.join ( SimTFlussoSintesiPagopa_.simTFlussoDettaglioPagopas, JoinType.LEFT );
                return builder.equal ( root, idSimTFlussoOriginePagopa );
            }
        } );
    }
}
