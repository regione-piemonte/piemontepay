/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTElaborazione;


public interface RicercaElaborazionePrecedenteRepository extends PagingAndSortingRepository<CblTElaborazione, Long>, Repository<CblTElaborazione, Long> {

    //Ricerca per IdEnte e UtenteInserimento
    public Page<CblTElaborazione> findByCblTEnteIdEnte ( String idEnte, String utenteElaborazione, Pageable pageable );

    public Page<CblTElaborazione> findByCblTEnteIdEnte ( String idEnte, Pageable pageable );

    //Ricerca per IdEnte ,(dataInizio >= :dataInizio and c.dataInizio<= :dataFine)
    public Page<CblTElaborazione> findByCblTEnteIdEnteAndDataInizioBetween ( String idEnte, Timestamp dataInizio, Timestamp dataFine, Pageable pageable );

    //Ricerca per idEnte e statoElaborazione
    public Page<CblTElaborazione> findByCblTEnteIdEnteAndCblDStatoElaborazioneCodiceStatoIgnoreCase ( String idEnte, String statoElaborazione,
        Pageable pageable );

}
