/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataDisponibileInput;
import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataInput;
import it.csi.epay.epaypacatalogsrv.model.MacrotipoPpay_;
import it.csi.epay.epaypacatalogsrv.model.TematicaPpay_;
import it.csi.epay.epaypacatalogsrv.model.VoceEntrata;
import it.csi.epay.epaypacatalogsrv.model.VoceEntrata_;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.VoceEntrataRepository;
import it.csi.epay.epaypacatalogsrv.repository.util.CriteriaBuilderUtil;

public class VoceEntrataRepositoryImpl implements VoceEntrataRepositoryCustom {

    @Autowired
    private VoceEntrataRepository baseRepository;

    @Autowired
    private CodiceVersamentoRepository codiceVersamentoRepository;

	@Override
    public List<VoceEntrata> ricerca(RicercaVoceEntrataInput input) {

		List<Specification<VoceEntrata>> filters = new ArrayList<>();

        if ( !StringUtils.isBlank ( input.getCodice () ) ) {
            filters.add ( CriteriaBuilderUtil.likeCaseInsensitive ( VoceEntrata_.codice, input.getCodice () ) );
		}
        if ( !StringUtils.isBlank ( input.getDescrizione () ) ) {
            filters.add ( CriteriaBuilderUtil.likeCaseInsensitive ( VoceEntrata_.descrizione, input.getDescrizione () ) );
		}
        if ( !StringUtils.isBlank ( input.getCodiceMacrotipo () ) ) {
			filters.add(new Specification<VoceEntrata>() {
				@Override
				public Predicate toPredicate(Root<VoceEntrata> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
					return builder.equal(
                        builder.lower ( root.get ( VoceEntrata_.macrotipo ).get ( MacrotipoPpay_.codice ) ),
						input.getCodiceMacrotipo().toLowerCase()
					);
				}
			});
		}
        if ( !StringUtils.isBlank ( input.getCodiceTematica () ) ) {
			filters.add(new Specification<VoceEntrata>() {
				@Override
				public Predicate toPredicate(Root<VoceEntrata> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
					return builder.equal(
                        builder.lower ( root.get ( VoceEntrata_.tematica ).get ( TematicaPpay_.codice ) ),
						input.getCodiceTematica().toLowerCase()
					);
				}
			});
		}
        
        filters.add(new Specification<VoceEntrata>() {
        	// forzo il prefetch di tematiche e macrotipi in join
			@Override
			public Predicate toPredicate(Root<VoceEntrata> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				root.fetch(VoceEntrata_.tematica, JoinType.LEFT);
				root.fetch(VoceEntrata_.macrotipo, JoinType.LEFT);
				return builder.conjunction();
			}
		});
        
		List<VoceEntrata> records = baseRepository.findAll(CriteriaBuilderUtil.groupAnd(filters));
		return records;
	}

    @Override
    public List<VoceEntrata> ricercaDisponibili ( RicercaVoceEntrataDisponibileInput input, Long idEnte ) {
        List<VoceEntrata> records = ricerca ( input );

        List<String> codiciEsistenti = codiceVersamentoRepository.getCodiciUtilizzatiByIdEnte ( idEnte );

        List<VoceEntrata> filtrati = new ArrayList<> ();

        for ( VoceEntrata record: records ) {
            if ( !codiciEsistenti.contains ( record.getCodice () ) ) {
                filtrati.add ( record );
            }
        }

        return filtrati;
    }
}
