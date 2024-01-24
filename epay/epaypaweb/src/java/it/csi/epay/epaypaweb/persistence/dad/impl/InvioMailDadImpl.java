/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dad.impl;

import javax.inject.Inject;

import it.csi.epay.epaypaweb.dto.InvioMailDto;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dad.EPaypaDadBaseImpl;
import it.csi.epay.epaypaweb.persistence.dad.interf.InvioMailDad;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaDTipoInvioMailDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTInvioMailDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTInvioMail;


public class InvioMailDadImpl extends EPaypaDadBaseImpl implements InvioMailDad {
	static private final String CLASSNAME = InvioMailDadImpl.class.getSimpleName();

	@Inject
	private EpaypaDTipoInvioMailDao epaypaDTipoInvioMailDao;

	@Inject
	private EpaypaTInvioMailDao epaypaTInvioMailDao;

	@Override
	public Long insertInvioMail(InvioMailDto dto) throws PersistenceException {
		String methodName = "insertInvioMail";
		
		

		Long id = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if (dto != null) {
				EpaypaTInvioMail entity = new EpaypaTInvioMail();
				entity.setDestinatarioTo(dto.getTo());
				entity.setDestinatarioCc(dto.getCc());
				entity.setEpaypaDTipoInvioMail(epaypaDTipoInvioMailDao.findOne(dto.getTipoMailEnum().getId()));
				entity.setDtInserimento(getTimestampNow());
				entity.setIdFlusso(dto.getIdFlusso());

				epaypaTInvioMailDao.persist(entity);

				id = entity.getIdInvioMail();
			}
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return id;
	}

	@Override
	public InvioMailDto findProssimoInvioMail(int seconds) throws PersistenceException {
		String methodName = "findProssimoInvioMail";
		
		

		InvioMailDto invioMail = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaTInvioMail entity = epaypaTInvioMailDao.findNextMailToSend(seconds);

			invioMail = toInvioMailDto(entity);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return invioMail;
	}

	@Override
	public boolean updateInvioMail(InvioMailDto dto) throws PersistenceException {
		String methodName = "updateInvioMail";
		
		

		boolean updateDone = false;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if (dto != null) {

				EpaypaTInvioMail entity = epaypaTInvioMailDao.findOne(dto.getId());

				if (entity != null) {
					if (dto.getId() != null) {
						entity.setIdInvioMail(dto.getId());
					}
					if (dto.getTo() != null) {
						entity.setDestinatarioTo(dto.getTo());
					}
					if (dto.getCc() != null) {
						entity.setDestinatarioCc(dto.getCc());
					}
					if (dto.getTipoMailEnum() != null) {
						entity.setEpaypaDTipoInvioMail(epaypaDTipoInvioMailDao.findOne(dto.getTipoMailEnum().getId()));
					}
					if (dto.getDataInserimento() != null) {
						entity.setDtInserimento(toTimestamp(dto.getDataInserimento()));
					}
					if (dto.getIdFlusso() != null) {
						entity.setIdFlusso(dto.getIdFlusso());
					}
					entity.setDtUltimoTentativo(getTimestampNow());
					if (dto.getEsitoUltimoTentativo() != null) {
						entity.setEsitoUltimoTentativo(dto.getEsitoUltimoTentativo());
					}
					if (dto.getErroreInvio() != null) {
						entity.setErroreInvio(dto.getErroreInvio());
					}

					epaypaTInvioMailDao.merge(entity);
					updateDone = true;
				}
			}
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return updateDone;
	}

	@Override
	public void deleteInvioMail(Long idInvioMail) throws PersistenceException {
		String methodName = "deleteInvioMail";
		
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EpaypaTInvioMail epaypaTInvioMail = new EpaypaTInvioMail();
			epaypaTInvioMail.setIdInvioMail(idInvioMail);

			epaypaTInvioMailDao.remove(epaypaTInvioMail);

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

}
