/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.csi.epay.epaypacatalogsrv.model.VisibilitaCodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.VisibilitaCodiceVersamentoPK;


public interface VisibilitaCodiceVersamentoRepository extends JpaRepository<VisibilitaCodiceVersamento, VisibilitaCodiceVersamentoPK> {
	

    @Query ( "SELECT u FROM VisibilitaCodiceVersamento u WHERE u.pk.idUtente = ?1 AND u.pk.idEnte = ?2" )
	List<VisibilitaCodiceVersamento> findByIdUtenteIdEnte(Long idUtente, Long idEnte);

}
