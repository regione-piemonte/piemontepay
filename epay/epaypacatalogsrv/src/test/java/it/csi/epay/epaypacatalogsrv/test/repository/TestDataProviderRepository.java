/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.csi.epay.epaypacatalogsrv.model.Utente;


public interface TestDataProviderRepository extends JpaRepository<Utente, Long> {

    @Query ( value = "SELECT codice_fiscale FROM epaycat_t_utente " +
                    "WHERE codice_fiscale IS NOT NULL ORDER BY id ASC", nativeQuery = true )
    List<String> findCodiciFiscaliUtenti ();

    @Query ( value = "SELECT id FROM epaycat_t_ente " +
                    "WHERE id IS NOT NULL ORDER BY id ASC", nativeQuery = true )
    List<Long> findIdEnti ();

    @Query ( value = "SELECT codice_fiscale FROM epaycat_t_ente " +
                    "WHERE codice_fiscale IS NOT NULL ORDER BY id ASC", nativeQuery = true )
    List<String> findCodiciFiscaliEnti ();

    @Modifying
    @Query ( value = "DELETE FROM epaycat_r_ruolo_cdu WHERE codice_cdu LIKE CONCAT('%', ?1, '%')", nativeQuery = true )
    void sganciaCduByCodiceCduLike ( String pattern );

    @Modifying
    @Query ( value = "DELETE FROM epaycat_r_ruolo_cdu WHERE codice_ruolo LIKE CONCAT('%', ?1, '%')", nativeQuery = true )
    void sganciaCduByCodiceRuoloLike ( String pattern );

    @Modifying
    @Query ( value = "DELETE FROM epaycat_r_utente_ente WHERE 1 = 1", nativeQuery = true )
    void deleteAssociazioniUtentiEnti ();

    @Modifying
    @Query ( value = "DELETE FROM epaycat_r_utente_ruolo WHERE 1 = 1", nativeQuery = true )
    void deleteAssociazioniUtentiRuoli ();
}
