/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.epay.epaypacatalogsrv.model.Errore;

public interface ErroreRepository extends JpaRepository<Errore, Long> {

	List<Errore> findByCodiceApplicativo(String codiceApplicativo);
	List<Errore> findByCodiceLinguaAndCodiceApplicativo(String codiceLingua, String codiceApplicativo);
	Errore findOneByCodiceApplicativoAndCodiceLinguaAndCodice(String codiceApplicativo, String codiceLingua, String codice);
}
