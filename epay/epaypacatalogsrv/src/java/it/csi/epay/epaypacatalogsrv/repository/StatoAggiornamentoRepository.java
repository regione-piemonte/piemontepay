/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.epay.epaypacatalogsrv.model.StatoAggiornamento;


public interface StatoAggiornamentoRepository extends JpaRepository<StatoAggiornamento, Long> {

    public final static String CODICE_DEFAULT = "NONE";

    public final static String CODICE_OK = "OK";

    public final static String CODICE_KO = "KO";

    StatoAggiornamento findOneByCodice ( String codice );
}
