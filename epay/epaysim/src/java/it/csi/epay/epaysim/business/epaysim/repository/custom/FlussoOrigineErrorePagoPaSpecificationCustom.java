/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.repository.custom;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoOrigineErrore;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoOrigineErrore_;
import it.csi.epay.epaysim.dto.RicercaFlussoErroreInputDTO;
import it.csi.epay.epaysim.util.DateUtils;


/**
 *
 */

public interface FlussoOrigineErrorePagoPaSpecificationCustom {

    static Specification<SimTFlussoOrigineErrore> ricercaFlussoOrigineInErroreSpecification ( RicercaFlussoErroreInputDTO inputRicercaFlussoErrore ) {
        return new Specification<SimTFlussoOrigineErrore> () {

            @Override
            public Predicate toPredicate ( Root<SimTFlussoOrigineErrore> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {

                Predicate predicate = cb.conjunction ();
                if ( null != inputRicercaFlussoErrore.getId () ) {
                    //ricerca per ID
                    predicate = cb.and (
                        cb.equal ( root.get ( SimTFlussoOrigineErrore_.id ), inputRicercaFlussoErrore.getId () ) );
                } else { //ricerca per altri campi

                    if ( StringUtils.hasText ( inputRicercaFlussoErrore.getIdentificativoFlusso () ) ) {
                        predicate = cb.and (
                            cb.like ( cb.lower ( root.get ( SimTFlussoOrigineErrore_.identificativoFlusso ) ),
                                "%" + inputRicercaFlussoErrore.getIdentificativoFlusso ().toLowerCase () + "%" ) );
                    }
                    //Ricerca per DataFlussoDa
                    if ( null != inputRicercaFlussoErrore.getDataFlussoDa () ) {
                        predicate = cb.and ( predicate,
                            cb.greaterThanOrEqualTo ( root.get ( SimTFlussoOrigineErrore_.dataoraFlusso ),
                                DateUtils.xmlGregorianCalendarToDate ( inputRicercaFlussoErrore.getDataFlussoDa () ) ) );
                    }
                    //Ricerca per DataFlussoA
                    if ( null != inputRicercaFlussoErrore.getDataFlussoA () ) {
                        predicate = cb.and ( predicate,
                            cb.lessThanOrEqualTo ( root.get ( SimTFlussoOrigineErrore_.dataoraFlusso ),
                                DateUtils.xmlGregorianCalendarToDate ( inputRicercaFlussoErrore.getDataFlussoA () ) ) );
                    }
                    //Filtro predefinito per ente
                    predicate = cb.and ( predicate, cb.equal ( root.get ( SimTFlussoOrigineErrore_.cfEnteRicevente ),
                        inputRicercaFlussoErrore.getCaller ().getPrincipal ().getCodiceEnte () ) );
                }

                return query.multiselect (
                                root.get ( SimTFlussoOrigineErrore_.cfEnteRicevente.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.codiceVersamento.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.contatoreTentativi.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.dataoraFlusso.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.dataRegolamento.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.descrizioneErrore.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.ibanRiversamentoFlusso.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.id.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.idElaborazione.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.identificativoFlusso.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.identificativoPsp.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.identificativoUnivocoRegolamento.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.idMessaggio.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.idStatoFlusso.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.importoTotalePagamenti.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.importoTotalePagamentiIntermediati.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.numeroTotalePagamenti.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.numeroTotalePagamentiIntermediati.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.dataInserimento.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.dataModifica.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.utenteInserimento.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.utenteModifica.getName () ),
                                root.get ( SimTFlussoOrigineErrore_.codiceErrore.getName () ) )
                    .where ( predicate ).getRestriction ();
            }
        };
    }

}
