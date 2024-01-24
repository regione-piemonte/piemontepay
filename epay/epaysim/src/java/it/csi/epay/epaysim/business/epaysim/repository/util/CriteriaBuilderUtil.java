/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.repository.util;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;


public interface CriteriaBuilderUtil {

    public static <T> Specification<T> equalSpecification ( SingularAttribute<T, String> attribute, String input ) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                return builder.equal ( root.get ( attribute ), input );
            }
        };
    }

    public static <T> Specification<T> likeSpecification ( SingularAttribute<T, String> attribute, String input ) {
        Assert.hasLength ( "likeSpecification::Input parameter shuld NOT be empty!", input );
        return new Specification<T> () {

            @Override
            public Predicate toPredicate ( Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
                return builder.like ( builder.lower ( root.get ( attribute ) ), "%" + input.toLowerCase () + "%" );
            }
        };
    }

    public static <T> Specification<T> equalSpecification ( SingularAttribute<T, Long> attribute, Long input ) {
        return new Specification<T> () {

            @Override
            public Predicate toPredicate ( Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
                return builder.equal ( root.get ( attribute ), input );
            }
        };
    }

    public static <T> Specification<T> join2 ( SingularAttribute<T, String> attribute, String input ) {
        return new Specification<T> () {

            @Override
            public Predicate toPredicate ( Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
                return builder.equal ( root.get ( attribute ), input );
            }
        };
    }

    public static <T, LT> Specification<T> join ( LT input, String attr, JoinType joinType ) {
        return new Specification<T> () {

            @SuppressWarnings ( "unused" )
            @Override
            public Predicate toPredicate ( Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
                Join<T, LT> userProd = root.join ( attr, joinType );
                return builder.equal ( root, input );
            }
        };
    }



}
