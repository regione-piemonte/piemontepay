/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaymodric.business.epaymodric.bo.ScheduledJob;
import it.csi.epay.epaymodric.business.epaymodric.manager.JobSchedulerManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.ScheduledJobUtility;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTScheduledJob;
import it.csi.epay.epaymodric.business.epaymodric.repository.ScheduledJobRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.SchedulerLockRepository;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.SchedulerException;

@Service
@Transactional
public class JobSchedulerManagerImpl implements JobSchedulerManager {
	
	private final Logger logger = LogManager.getLogger ( this.getClass () );
	
	@Autowired
	private SchedulerLockRepository schedulerLockRepository;
	
	@Autowired
	private ScheduledJobRepository scheduledJobRepository;

	@Override
	@Transactional ( readOnly = true )
	public void obtainLock(long timestamp) throws SchedulerException {
		
//		logger.info ( "JobSchedulerManagerImpl.obtainLock: INIZIO: " + timestamp );
		try {
			schedulerLockRepository.getLock();
//			logger.info ( "JobSchedulerManagerImpl.obtainLock: LOCK OK: " + timestamp );
		} catch (Exception e) {
			logger.error("JobSchedulerManagerImpl.obtainLock: ERRORE IN FASE DI ACQUISIZIONE DEL LOCK: " + timestamp, e);
			throw new SchedulerException(e);
	
		}
//		logger.info ( "JobSchedulerManagerImpl.obtainLock: FINE: " + timestamp );
	}

	@Override
	public List<ScheduledJob> findAllJobs() {
		
		List<ScheduledJob> dtoList = new LinkedList<> ();
		
		List<CblTScheduledJob> entityList = scheduledJobRepository.findAll();
		if ( entityList != null && entityList.size() > 0) {

			//entityList.forEach ( r -> dtoList.add ( ScheduledJobUtility.getScheduledJob ( r ) ) );
			for ( CblTScheduledJob job : entityList ) {
				dtoList.add ( ScheduledJobUtility.getScheduledJob ( job ) );
			}
		}
		
		return dtoList;
	}

	@Override
	public List<ScheduledJob> findAllActiveJobs() throws SchedulerException {
		List<ScheduledJob> dtoList = new LinkedList<> ();
		
		List<CblTScheduledJob> entityList = scheduledJobRepository.findAllActiveJobs();
		if ( entityList != null && entityList.size() > 0) {
			for ( CblTScheduledJob job : entityList ) {
				dtoList.add ( ScheduledJobUtility.getScheduledJob ( job ) );
			}
		}
		
		return dtoList;
	}

	@Override
	public void updateJob(ScheduledJob scheduledJob) {
		CblTScheduledJob cblTScheduledJob = scheduledJobRepository.findOne(scheduledJob.getId());
		if ( cblTScheduledJob == null ) {
			throw new IllegalArgumentException("Scheduled job entity not found by id  " + scheduledJob.getId());
		}
		
		cblTScheduledJob.setInizioUltimaEsecuzione(( scheduledJob.getInizioUltimaEsecuzione() != null ? new Timestamp(scheduledJob.getInizioUltimaEsecuzione().getTime()) : null ));
		cblTScheduledJob.setFineUltimaEsecuzione(( scheduledJob.getFineUltimaEsecuzione() != null ? new Timestamp(scheduledJob.getFineUltimaEsecuzione().getTime()) : null ));

		if (scheduledJob.getStatus() != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				scheduledJob.getStatus().storeToXML(baos, null);
				cblTScheduledJob.setStatus(baos.toString());
				baos.close();

			} catch ( IOException e ) {
				logger.error( "JobSchedulerManagerImpl.updateJob: ERRORE IN FASE DI SERIALIZZAZIONE DELLO STATO DEL JOB ID: " + scheduledJob.getId() , e );
			}
		}
		scheduledJobRepository.save(cblTScheduledJob);
	}
}
