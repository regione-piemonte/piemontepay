/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.repository.custom;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

import it.csi.epay.epaymodric.business.epaymodric.model.CblDStatoElaborazione;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDStatoElaborazione_;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDStatoFlusso_;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTCodiceVersamento;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTCodiceVersamento_;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTElaborazione;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTElaborazione_;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte_;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine_;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoSintesi;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoSintesi_;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;


/**
 *
 */

public interface CustomSpecifications {

    static Specification<CblTElaborazione> esistonoElaborazioniTerminateOggiSpecification ( String idEnte ) {
        return new Specification<CblTElaborazione> () {

            @Override
            public Predicate toPredicate ( Root<CblTElaborazione> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {

                Predicate predicate = cb.conjunction ();

                Join<CblTElaborazione, CblTEnte> joinRootEnte = root.join ( CblTElaborazione_.cblTEnte, JoinType.INNER );
                Join<CblTElaborazione, CblDStatoElaborazione> joinRootStatoElaborazione = root.join ( CblTElaborazione_.cblDStatoElaborazione, JoinType.INNER );

                predicate = cb.and (
                    cb.equal ( joinRootEnte.get ( CblTEnte_.idEnte ), idEnte ),
                    cb.equal ( root.get ( CblTElaborazione_.dataFine ).as ( java.sql.Date.class ), cb.currentDate () ),
                    cb.equal ( joinRootStatoElaborazione.get ( CblDStatoElaborazione_.codiceStato ), Costanti.STATO_CBL_T_ELABORAZIONE_TERMINATA ),
                    cb.notEqual ( joinRootEnte.get ( CblTEnte_.periodicitaSchedulazione ), Costanti.PERIODICITA_SCHEDULAZIONE_SINGOLO_FLUSSO_CBL_T_ENTE ) );
                return predicate;
            }
        };
    }

    static Specification<CblTFlussoOrigine> selezioneFlussiAggiornatiESchedulati ( List<Long> idStatoFlusso ) {
        return new Specification<CblTFlussoOrigine> () {

            @Override
            public Predicate toPredicate ( Root<CblTFlussoOrigine> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
                Predicate predicate = cb.conjunction ();

                Join<CblTFlussoOrigine, CblTEnte> joinRootEnte = root.join ( CblTFlussoOrigine_.cblTEnte, JoinType.INNER );

                predicate = cb.and (
                    cb.notEqual ( joinRootEnte.get ( CblTEnte_.periodicitaSchedulazione ), 0 ),
                    root.get ( CblTFlussoOrigine_.cblDStatoFlusso ).get ( CblDStatoFlusso_.id ).in ( idStatoFlusso ) );
                return predicate;
            }
        };
    }
    
    static Specification<CblTCodiceVersamento> codiciAssociatiAFlussi ( String codiceFiscaleEnte ){
    	return new Specification<CblTCodiceVersamento> () {
			
			@Override
			public Predicate toPredicate ( Root<CblTCodiceVersamento> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
				Predicate predicate = builder.conjunction();
				
				Subquery<CblTFlussoSintesi> subqueryFlussoSintesi = query.subquery ( CblTFlussoSintesi.class );
				Root<CblTFlussoSintesi> rootFlussoSintesi = subqueryFlussoSintesi.from( CblTFlussoSintesi.class );
				subqueryFlussoSintesi.select ( rootFlussoSintesi.get ( CblTFlussoSintesi_.codiceVersamento.getName () ) );
				Root<CblTEnte> rootEnte = subqueryFlussoSintesi.from ( CblTEnte.class );
				
				Predicate predicateFlussoSintesi = builder.conjunction ();
				predicateFlussoSintesi = builder.equal (rootFlussoSintesi.get ( CblTFlussoSintesi_.cblTEnte ).as(String.class), rootEnte.get ( CblTEnte_.idEnte.getName() ) );
				subqueryFlussoSintesi.where ( predicateFlussoSintesi );
				
				query.distinct(true);
				Expression<String> codiceVersamento = root.get( CblTCodiceVersamento_.codiceVersamento );
				predicate = builder.and( builder.equal ( root.get ( CblTCodiceVersamento_.codiceFiscaleEnte ), codiceFiscaleEnte ), codiceVersamento.in ( subqueryFlussoSintesi ) );
				return predicate;
			}
		};
    }

}
