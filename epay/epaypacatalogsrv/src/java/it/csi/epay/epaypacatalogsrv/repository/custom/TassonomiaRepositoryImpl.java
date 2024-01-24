/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import it.csi.epay.epaypacatalogsrv.dto.tassonomia.RicercaTassonomiaInput;
import it.csi.epay.epaypacatalogsrv.model.Tassonomia;
import it.csi.epay.epaypacatalogsrv.model.Tassonomia_;
import it.csi.epay.epaypacatalogsrv.repository.TassonomiaRepository;
import it.csi.epay.epaypacatalogsrv.repository.util.CriteriaBuilderUtil;


public class TassonomiaRepositoryImpl implements TassonomiaRepositoryCustom {

	@Autowired
	private TassonomiaRepository baseRepository;

	@Override
	public List<Tassonomia> ricerca(RicercaTassonomiaInput input, String codTipoEnteCreditore) {

		List<Specification<Tassonomia>> filters = new ArrayList<> ();

		filters.add ( new Specification<Tassonomia> () {

			@Override
			public Predicate toPredicate ( Root<Tassonomia> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
				return builder.equal (
					root.get ( Tassonomia_.codTipoEnteCreditore ), codTipoEnteCreditore );
			}
		} );

		if ( input.getTipoServizio () != null ) {
			filters.add ( new Specification<Tassonomia> () {

				@Override
				public Predicate toPredicate ( Root<Tassonomia> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.equal (
						root.get ( Tassonomia_.tipoServizio ), input.getTipoServizio () );
				}
			} );
		}

		if ( input.getNomeMacroArea () != null ) {
			filters.add ( new Specification<Tassonomia> () {

				@Override
				public Predicate toPredicate ( Root<Tassonomia> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.equal (
						root.get ( Tassonomia_.nomeMacroArea ), input.getNomeMacroArea () );
				}
			} );
		}

		if ( input.getDatiSpecificiIncasso () != null ) {
			filters.add ( new Specification<Tassonomia> () {

				@Override
				public Predicate toPredicate ( Root<Tassonomia> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.equal (
						root.get ( Tassonomia_.datiSpecificiIncasso ), input.getDatiSpecificiIncasso () );
				}
			} );
		}

		if ( input.getDataInizioValidita () != null ) {
			filters.add ( new Specification<Tassonomia> () {

				@Override
				public Predicate toPredicate ( Root<Tassonomia> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.greaterThanOrEqualTo (
						root.get ( Tassonomia_.dataInizioValidita ), input.getDataInizioValidita () );
				}
			} );
		}

		if ( input.getDataFineValidita () != null ) {
			filters.add ( new Specification<Tassonomia> () {

				@Override
				public Predicate toPredicate ( Root<Tassonomia> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
					return builder.lessThanOrEqualTo (
						root.get ( Tassonomia_.dataFineValidita ), input.getDataFineValidita () );
				}
			} );
		}


		return baseRepository.findAll ( CriteriaBuilderUtil.groupAnd ( filters ) );
	}

}
