/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTSchedulerLock;

public interface SchedulerLockRepository extends JpaRepository<CblTSchedulerLock,Integer>{
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
	@Query( value = "select lock from CblTSchedulerLock lock where lock.idSchedulerLock = 1")
	public CblTSchedulerLock getLock();
	
}
/*
public interface SchedulerLockRepository {
	
	public CblTSchedulerLock getLock() throws SchedulerException;
	
}
*/
