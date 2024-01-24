/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.repository.custom.UtenteRepositoryCustom;


public interface UtenteRepository extends JpaRepository<Utente, Long>, JpaSpecificationExecutor<Utente>, UtenteRepositoryCustom {

    Utente findOneByCodiceFiscale ( String codiceFiscale );

    @Query ( value = "SELECT DISTINCT(r.id_ente) FROM epaycat_r_utente_ente r WHERE r.id_utente = ?1", nativeQuery = true )
    List<Number> getRawIdEntiAssociatiByIdUtente ( Long idUtente );

    @Query ( value = "SELECT DISTINCT(r.id_utente) FROM epaycat_r_utente_ente r WHERE r.id_ente = ?1", nativeQuery = true )
    List<Number> getRawIdUtentiAssociatiByIdEnte ( Long idEnte );

    @Query ( "SELECT u FROM Utente u WHERE u.codiceFiscale = ?1" )
    Utente findOneCompletoByCodiceFiscale ( String codiceFiscale );

    List<Utente> findByUtenteModifica ( String utenteModifica );
}
