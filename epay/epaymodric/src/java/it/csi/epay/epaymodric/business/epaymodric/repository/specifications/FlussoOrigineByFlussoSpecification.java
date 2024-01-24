/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.repository.specifications;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine_;


/**
 * Specification inerente i flussi origine (cbl_t_flusso_origine) - recupero per lista flussi
 */
public class FlussoOrigineByFlussoSpecification implements Specification<CblTFlussoOrigine> {

    String identificativoIstitutoRicevente;

    List<String> listaFlussi;


    public FlussoOrigineByFlussoSpecification ( String identificativoIstitutoRicevente, List<String> listaFlussi ) {
        super ();
        this.identificativoIstitutoRicevente = identificativoIstitutoRicevente;
        this.listaFlussi = listaFlussi;
    }

    @Override
    public Predicate toPredicate ( Root<CblTFlussoOrigine> root, CriteriaQuery<?> cq, CriteriaBuilder cb ) {
        Predicate predicate = cb.equal ( root.get ( CblTFlussoOrigine_.identificativoIstitutoRicevente ), identificativoIstitutoRicevente );
        predicate = cb.and ( predicate, root.get ( CblTFlussoOrigine_.identificativoFlusso ).in ( listaFlussi ) );

        //Mi posiziono alle ore 1 del giorno corrente o del precedente se sono per es. alle 12:30
        Calendar now = Calendar.getInstance ();
        now.set ( Calendar.HOUR_OF_DAY, 1 );
        now.set ( Calendar.MINUTE, 0 );
        now.set ( Calendar.SECOND, 0 );

        //Se sono per es. alle 12:00 mi posiziono all'1 del giorno precedente (-24 ore)
        if ( now.after ( new Date () ) ) {
            now.add ( Calendar.HOUR, -24 );
        }

        //Vista la RDI-01 i flussi da elaborare devono essere filtrati per data
        //Visto che la PSP (banca) ha tempo fino alle 01 del giorno corrente per emettere
        //un annullo tecnico devo prendere (rimuovere da entities) i flussi con dataora_flusso
        //LA CHIAMATA E' FATTA OGNI 5 ORE (18000 SECS.)
        //
        predicate = cb.and ( predicate, cb.lessThan ( root.get ( CblTFlussoOrigine_.dataoraFlusso.getName () ), now.getTime () ) );

        return predicate;
    }

}
