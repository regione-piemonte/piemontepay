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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import it.csi.epay.epaysim.business.epaysim.model.SimTProvvisorio;
import it.csi.epay.epaysim.business.epaysim.model.SimTProvvisorio_;
import it.csi.epay.epaysim.business.epaysim.repository.ProvvisorioRepository;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.RicercaProvvisoriPagoPARequest;


public class ProvvisorioRepositoryImpl implements ProvvisorioRepositoryCustom {

    @Autowired
    private ProvvisorioRepository provvisorioRepository;

    //    public List<Provvisorio> search ( final RicercaProvvisorioInputDTO ricercaProvvisorioInputDTO ) {
    //        List<Provvisorio> result = null;
    //        List<Specification<Provvisorio>> filters = new ArrayList<> ();
    //
    //        if ( !StringUtils.isEmpty ( ricercaProvvisorioInputDTO.getCfEnteCreditore () ) ) {
    //            filters.add ( new Specification<Provvisorio> () {
    //
    //                @Override
    //                public Predicate toPredicate ( Root<Provvisorio> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
    //                    return builder.like (
    //                        builder.lower (
    //                            root.get ( SimTProvvisorio_.codiceFiscaleEnte ) ),
    //                        "%" + ricercaProvvisorioInputDTO.getCfEnteCreditore ().toLowerCase () + "%" );
    //                }
    //            } );
    //        }
    //
    //        if ( !StringUtils.isEmpty ( ricercaProvvisorioInputDTO.getIdentificativoFlusso () ) ) {
    //            filters.add ( new Specification<Provvisorio> () {
    //
    //                @Override
    //                public Predicate toPredicate ( Root<Provvisorio> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
    //                    return builder.like (
    //                        builder.lower (
    //                            root.get ( SimTProvvisorio_.identificativoFlusso ) ),
    //                        "%" + ricercaProvvisorioInputDTO.getIdentificativoFlusso ().toLowerCase () + "%" );
    //                }
    //            } );
    //        }
    //
    //        return result;
    //    }

    @Override
    public List<SimTProvvisorio> search ( final RicercaProvvisoriPagoPARequest ricercaProvvisoriPagoPARequest ) {
        List<SimTProvvisorio> result = null;
        List<Specification<SimTProvvisorio>> filters = new ArrayList<> ();

        if ( !StringUtils.isEmpty ( ricercaProvvisoriPagoPARequest.getCFEnteCreditore () ) ) {
            filters.add ( new Specification<SimTProvvisorio> () {

                @Override
                public Predicate toPredicate ( Root<SimTProvvisorio> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
                    return builder.like (
                        builder.lower (
                            root.get ( SimTProvvisorio_.codiceFiscaleEnte ) ),
                        "%" + ricercaProvvisoriPagoPARequest.getCFEnteCreditore ().toLowerCase () + "%" );
                }
            } );
        }

        if ( ricercaProvvisoriPagoPARequest.getElencoCausaliVersamenti () != null
                        && ricercaProvvisoriPagoPARequest.getElencoCausaliVersamenti ().getCausaleVersamento () != null
                        && ricercaProvvisoriPagoPARequest.getElencoCausaliVersamenti ().getCausaleVersamento ().size () > 0 ) {

            filters.add ( new Specification<SimTProvvisorio> () {

                @Override
                public Predicate toPredicate ( Root<SimTProvvisorio> root, CriteriaQuery<?> query,
                    CriteriaBuilder builder ) {

                    Predicate pred = builder.disjunction ();
                    for ( String codiceVersamento: ricercaProvvisoriPagoPARequest.getElencoCausaliVersamenti ().getCausaleVersamento () ) {
                        pred = builder.or ( pred,
                            builder.like ( builder.lower ( root.get ( SimTProvvisorio_.causale ) ), "%" + codiceVersamento.toLowerCase () + "%" ) );
                    }
                    return pred;
                }
            } );
        }

        if ( ricercaProvvisoriPagoPARequest.getAnnoEsercizio () > 0 ) {
            filters.add ( new Specification<SimTProvvisorio> () {

                @Override
                public Predicate toPredicate ( Root<SimTProvvisorio> root, CriteriaQuery<?> query,
                    CriteriaBuilder builder ) {
                    return builder.equal ( root.get ( SimTProvvisorio_.annoEsercizio ),
                        ricercaProvvisoriPagoPARequest.getAnnoEsercizio () );
                }
            } );
        }

        if ( ricercaProvvisoriPagoPARequest.getDataProvvisorioDal () != null ) {
            filters.add ( new Specification<SimTProvvisorio> () {

                @Override
                public Predicate toPredicate ( Root<SimTProvvisorio> root, CriteriaQuery<?> query,
                    CriteriaBuilder builder ) {
                    return builder.greaterThanOrEqualTo ( root.get ( SimTProvvisorio_.dataMovimento ),
                        new Timestamp ( ricercaProvvisoriPagoPARequest.getDataProvvisorioDal ().toGregorianCalendar ()
                            .getTimeInMillis () ) );
                }
            } );
        }

        if ( ricercaProvvisoriPagoPARequest.getDataProvvisorioAl () != null ) {
            filters.add ( new Specification<SimTProvvisorio> () {

                @Override
                public Predicate toPredicate ( Root<SimTProvvisorio> root, CriteriaQuery<?> query,
                    CriteriaBuilder builder ) {
                    return builder.lessThanOrEqualTo ( root.get ( SimTProvvisorio_.dataMovimento ),
                        new Timestamp ( ricercaProvvisoriPagoPARequest.getDataProvvisorioAl ().toGregorianCalendar ()
                            .getTimeInMillis () ) );
                }
            } );
        }

        if ( ricercaProvvisoriPagoPARequest.getNumeroProvvisorioDal () != null ) {
            filters.add ( new Specification<SimTProvvisorio> () {

                @Override
                public Predicate toPredicate ( Root<SimTProvvisorio> root, CriteriaQuery<?> query,
                    CriteriaBuilder builder ) {
                    return builder.greaterThanOrEqualTo ( root.get ( SimTProvvisorio_.numeroProvvisorio ),
                        ricercaProvvisoriPagoPARequest.getNumeroProvvisorioDal ().intValue () );
                }
            } );
        }

        if ( ricercaProvvisoriPagoPARequest.getNumeroProvvisorioAl () != null ) {
            filters.add ( new Specification<SimTProvvisorio> () {

                @Override
                public Predicate toPredicate ( Root<SimTProvvisorio> root, CriteriaQuery<?> query,
                    CriteriaBuilder builder ) {
                    return builder.lessThanOrEqualTo ( root.get ( SimTProvvisorio_.numeroProvvisorio ),
                        ricercaProvvisoriPagoPARequest.getNumeroProvvisorioAl ().intValue () );
                }
            } );
        }

        if ( ricercaProvvisoriPagoPARequest.getStato () != null ) {
            filters.add ( new Specification<SimTProvvisorio> () {

                @Override
                public Predicate toPredicate ( Root<SimTProvvisorio> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
                    return builder.equal ( root.get ( SimTProvvisorio_.stato ), ricercaProvvisoriPagoPARequest.getStato ().value () );
                }
            } );
        }

        if ( filters.isEmpty () ) {
            result = provvisorioRepository.findAll ();
        } else {
            Specification<SimTProvvisorio> filterSpecification = filters.get ( 0 );
            for ( int i = 1; i < filters.size (); i++ ) {
                filterSpecification = Specifications.where ( filterSpecification ).and ( filters.get ( i ) );
            }

            result = provvisorioRepository.findAll ( filterSpecification );
        }

        return result;
    }
}
