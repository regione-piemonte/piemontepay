/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository.custom;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.data.jpa.domain.Specification;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTProvvisorio;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOProvvisorio;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaProvvisori;


public class ProvvisoriRepositorySpecImpl implements ProvvisoriRepositorySpecCustom {

    Logger logger = LogManager.getLogger ( this.getClass () );

    @Override
    public List<DTOProvvisorio> cercaPerFiltro ( final DTOInputWsRicercaProvvisori ricerca ) {

        @SuppressWarnings ( "unused" )
        Specification<CblTProvvisorio> spec = new Specification<CblTProvvisorio> () {

            @Override
            public Predicate toPredicate ( Root<CblTProvvisorio> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
                return null;
            }
        };
        return null;
    }
}
