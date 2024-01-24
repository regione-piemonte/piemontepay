/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository.util;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;


public interface CriteriaBuilderUtil {

	public static <T> Specification<T> likeCaseInsensitive(
			SingularAttribute<T, String> attribute, String input) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(
					builder.lower(
						root.get(attribute)
					),
					"%" + input.toLowerCase() + "%"
				);
			}
		};
	}

	public static <T> Specification<T> equalsCaseInsensitive(
			SingularAttribute<T, String> attribute, String input) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(
					builder.lower(
						root.get(attribute)
					),
					"%" + input.toLowerCase() + "%"
				);
			}
		};
	}

    public static <T> Specification<T> isNull ( SingularAttribute<T, T> attribute ) {
        return new Specification<T> () {

            @Override
            public Predicate toPredicate ( Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
                return builder.isNull ( root.get ( attribute ) );
            }
        };
    }

    public static <T> Specification<T> notNull ( SingularAttribute<T, T> attribute ) {
        return new Specification<T> () {

            @Override
            public Predicate toPredicate ( Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
                return builder.isNotNull ( root.get ( attribute ) );
            }
        };
    }

	public static <T> Specification<T> groupAnd(List<Specification<T>> filters) {
		if (filters == null) {
			return new Specification<T>() {
				@Override
				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
					return builder.isTrue(builder.literal(true));
				}
			};
		}

		if (filters.size() > 0) {
			Specification<T> filterSpecification = filters.get(0);
	        for (int i = 1; i < filters.size(); i++) {
	        	filterSpecification = Specifications.where(filterSpecification).and(filters.get(i));
	        }

			return filterSpecification;
		} else {
			return new Specification<T>() {
				@Override
				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
					return builder.isTrue(builder.literal(true));
				}
			};
		}
	}

}
