/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTScheduledJob;

public interface ScheduledJobRepository extends JpaRepository<CblTScheduledJob,Integer>{

	@Query( value = "SELECT e " + 
			"FROM CblTScheduledJob e " + 
			"WHERE e.active = true " + 
			"AND ( ( e.fineUltimaEsecuzione IS NOT NULL ) and  ( EXTRACT ( epoch FROM ( current_timestamp - e.fineUltimaEsecuzione ) ) > e.interval ) OR e.inizioUltimaEsecuzione IS NULL ) ")
	public List<CblTScheduledJob> findAllActiveJobs();
}
