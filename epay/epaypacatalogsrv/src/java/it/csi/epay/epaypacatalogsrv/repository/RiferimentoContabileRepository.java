/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.Tassonomia;
import it.csi.epay.epaypacatalogsrv.repository.custom.RiferimentoContabileRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface RiferimentoContabileRepository extends
												JpaRepository<RiferimentoContabile, Long>,
												JpaSpecificationExecutor<RiferimentoContabile>,
												RiferimentoContabileRepositoryCustom {

	List<RiferimentoContabile> findByCodiceVersamento_IdOrderByDataInizioValiditaDesc ( Long idCodiceVersamento );

	List<RiferimentoContabile> findByUtenteModifica ( String utenteModifica );

	@Query ( value = "select * from epaycat_t_riferimento_contabile etrc where id_tassonomia = ?1 and anno_esercizio = date_part('year', now()) and (flag_annullato isnull or flag_annullato=false) and (data_fine_validita isnull or data_fine_validita >=now())", nativeQuery = true )
	List<RiferimentoContabile> getByTassonomiaAndNotAnnullato ( long idTassonomia );

	List<RiferimentoContabile> findByTassonomia ( Tassonomia tassonomia );
}



