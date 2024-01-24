/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;


public interface EnteRepository extends JpaRepository<CblTEnte, Long> {

    static final String QUERY_ENTI_ELABORABILI = "select * \r\n" +
        "from cbl_t_ente \r\n" +
        "where \r\n" +
        "id_ente not in (\r\n" +
        "select distinct id_ente \r\n" +
        "from \r\n" +
        "cbl_t_elaborazione \r\n" +
        "where \r\n" +
        "stato_elaborazione = 'SCHEDULATA'\r\n" +
        "or\r\n" +
        "stato_elaborazione = 'AVVIATA'\r\n" +
        ")";

    static final String QUERY_MAX_ID_ENTE = "select cast(regexp_replace(id_ente, '\\D*', '', 'g') as integer) as replaced " +
        "from cbl_t_ente " +
        "where regexp_replace(id_ente, '\\D*', '', 'g') != '' " +
        "order by replaced desc " +
        "limit 1";

    public CblTEnte findByIdEnte ( String idEnte );

    public CblTEnte findByCodiceFiscale ( String codiceFiscale );

    @Query ( value = QUERY_ENTI_ELABORABILI,
                    nativeQuery = true )
    public List<CblTEnte> recuperaEntiElaborabili ();

    @Query ( value = QUERY_MAX_ID_ENTE,
                    nativeQuery = true )
    public Integer getEnteMaxId ();

}
