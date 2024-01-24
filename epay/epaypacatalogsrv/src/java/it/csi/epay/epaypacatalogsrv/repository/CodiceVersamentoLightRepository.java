/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import it.csi.epay.epaypacatalogsrv.model.CodiceVersamentoLight;
import it.csi.epay.epaypacatalogsrv.repository.custom.CodiceVersamentoLightRepositoryCustom;

public interface CodiceVersamentoLightRepository extends
	JpaRepository<CodiceVersamentoLight, Long>,
	JpaSpecificationExecutor<CodiceVersamentoLight>,
	CodiceVersamentoLightRepositoryCustom {

	/* DEV/CSI_PAG-2416 - BEGIN */
	// cambiata la query aggiungendo al costruttore del model c.codice e c.descrizione
	/* DEV/CSI_PAG-2416 - END */
	@Query ( "SELECT NEW it.csi.epay.epaypacatalogsrv.model.CodiceVersamentoLight(c.id, c.codice, c.descrizione) FROM CodiceVersamentoLight c WHERE c.ente.id = ?1 AND c.codice = ?2 AND (flagAnnullato IS NULL or flagAnnullato = false)" )
    CodiceVersamentoLight findByCodiceAndIdEnte ( Long idEnte, String codice );
}
