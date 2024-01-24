/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dad.impl;

import it.csi.epay.epaypaweb.business.impl.GestioneFlussiBusinessImpl;
import it.csi.epay.epaypaweb.dto.common.PrincipalDto;
import it.csi.epay.epaypaweb.enumeration.AuditActionEnum;
import it.csi.epay.epaypaweb.enumeration.CsiLogAuditOperationEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dad.EPaypaDadBaseImpl;
import it.csi.epay.epaypaweb.persistence.dad.interf.CsiLogAuditDad;
import it.csi.epay.epaypaweb.persistence.dao.interf.AuditActionDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.CsiLogAuditDao;
import it.csi.epay.epaypaweb.persistence.entity.AuditAction;
import it.csi.epay.epaypaweb.persistence.entity.CsiLogAudit;
import it.csi.epay.epaypaweb.util.Util;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CsiLogAuditDadImpl extends EPaypaDadBaseImpl implements CsiLogAuditDad {
	
	private static final int DESCRIPTION_MAX_LENGTH = 2000;
	private static final int OGG_OPER_MAX_LENGTH = 500;
	//private static final int KEY_OPER_MAX_LENGTH = 500;

	private static final String CLASSNAME = GestioneFlussiBusinessImpl.class.getSimpleName();

	@Inject
	private CsiLogAuditDao csiLogAuditDao;

	@Inject
	private AuditActionDao auditActionDao;

	@Override
	public void insert(PrincipalDto dto,String tabella, String oggetto,String descrizione) {
		String methodName = "insert";
		log.info ( CLASSNAME + " " + methodName + " - START" );

		try {
			log.info(String.format("Inserimento di un record nella tabella: '%s'", tabella));

			CsiLogAudit csiLogAudit = createCsiLogAudit(AuditActionEnum.INSERIMENTO, dto.getIdUtente(), dto.getIpAddress(), tabella, oggetto, dto.getCodiceFiscaleUtente(), descrizione, dto.getCodiceApplicazione());
			csiLogAuditDao.persist(csiLogAudit);
		} catch (PersistenceException e) {
			log.error("Errore nel salvataggio: " + e.getMessage());
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

	}

	@Override
	public void update(PrincipalDto dto, String tabella, String oggetto,String descrizione) {
		String methodName = "update";

		log.info ( CLASSNAME + " " + methodName + " - START" );

		try {
			log.info(String.format("Aggiornamento dell'oggetto '%s' nella tabella '%s'", oggetto, tabella));

			CsiLogAudit csiLogAudit = createCsiLogAudit(AuditActionEnum.MODIFICA, dto.getIdUtente(), dto.getIpAddress(), tabella, oggetto, dto.getCodiceFiscaleUtente(), descrizione, dto.getCodiceApplicazione());
			csiLogAuditDao.persist(csiLogAudit);
		} catch (PersistenceException e) {
			log.error("Errore nel salvataggio: " + e.getMessage());
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

	}

	@Override
	public void delete(PrincipalDto dto, String tabella, String oggetto,String descrizione) {
		String methodName = "delete";
		log.info ( CLASSNAME + " " + methodName + " - START" );
		try {
			log.info(String.format("Tracciare l'eliminazione dell'oggetto '%s' nella tabella '%s'", oggetto, tabella));

			CsiLogAudit csiLogAudit = createCsiLogAudit(AuditActionEnum.ELIMINAZIONE, dto.getIdUtente(), dto.getIpAddress(), tabella, oggetto, dto.getCodiceFiscaleUtente(), descrizione, dto.getCodiceApplicazione());
			csiLogAuditDao.persist(csiLogAudit);
		} catch (PersistenceException e) {
			log.error("Errore nel salvataggio: " + e.getMessage());
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

	}

	@Override
	public void extract(PrincipalDto dto, String tabella, String oggetto,String descrizione) {
		String methodName = "extract";
		log.info ( CLASSNAME + " " + methodName + " - START" );
		try {
			log.info(String.format("Tracciare l'eliminazione dell'oggetto '%s' nella tabella '%s'", oggetto, tabella));

			CsiLogAudit csiLogAudit = createCsiLogAudit(AuditActionEnum.ESTRAZIONE, dto.getIdUtente(), dto.getIpAddress(), tabella, oggetto, dto.getCodiceFiscaleUtente(), descrizione, dto.getCodiceApplicazione());
			csiLogAuditDao.persist(csiLogAudit);
		} catch (PersistenceException e) {
			log.error("Errore nel salvataggio: " + e.getMessage());
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

	}
	
	@Override
	public void access(PrincipalDto dto, String tabella, String oggetto,String descrizione) {
		String methodName = "access";
		log.info ( CLASSNAME + " " + methodName + " - START" );
		try {
			log.info(String.format("Tracciare l'accesso dell'utente '%s'", dto.getCodiceFiscaleUtente()));

			CsiLogAudit csiLogAudit = createCsiLogAudit(AuditActionEnum.ACCESSO, dto.getIdUtente(), dto.getIpAddress(), tabella, oggetto, dto.getCodiceFiscaleUtente(), descrizione, dto.getCodiceApplicazione());
			csiLogAuditDao.persist(csiLogAudit);
		} catch (PersistenceException e) {
			log.error("Errore nel salvataggio: " + e.getMessage());
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

	@Override
	public void accessDenied(PrincipalDto dto, String tabella, String oggetto,String descrizione) {
		String methodName = "accessDenied";
		log.info ( CLASSNAME + " " + methodName + " - START" );
		
		try {
			log.info(String.format("Tracciare l'accesso non consentito all'ipAddress '%s'", dto.getIpAddress()));

			CsiLogAudit csiLogAudit = createCsiLogAudit(AuditActionEnum.ACCESSO_NEGATO, dto.getIdUtente(), dto.getIpAddress(), tabella, oggetto, dto.getCodiceFiscaleUtente(), descrizione, dto.getCodiceApplicazione());
			csiLogAuditDao.persist(csiLogAudit);
		} catch (PersistenceException e) {
			log.error("Errore nel salvataggio: " + e.getMessage());
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

	private CsiLogAudit createCsiLogAudit(AuditActionEnum action, Long idUtente, String ipAddress, String table,
			String oggetto,String codiceFiscaleUtente,String descrizione,String codiceApplicazione) {
		String methodName = "createCsiLogAudit";
		log.info ( CLASSNAME + " " + methodName + " - START" );

		AuditAction auditAction = auditActionDao.findByIdAction(action.getId());

		if (auditAction == null) {
			log.info(String.format("Azione con id '%s' non presente sul database", action.getId()));
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
			return null;
		}

		CsiLogAudit dto = new CsiLogAudit();
		dto.setOperazione(auditAction.getDescrizione());
		dto.setAction(auditAction);

		dto.setDataOra(Timestamp.valueOf(LocalDateTime.now()));
		dto.setIdApp(Util.APPLICATION_CODE);
		dto.setUtente(idUtente!=null?idUtente.toString():"");
		dto.setIpAddress(ipAddress); // indirizzo di chi fa l'inserimento

		dto.setOggOper(limitToMaxLength(table,OGG_OPER_MAX_LENGTH)); // tabella del db
		dto.setKeyOper(limitToMaxLength(oggetto, OGG_OPER_MAX_LENGTH)); // chiave dell'entita che viene toccata
		
		dto.setDescrizione(limitToMaxLength(descrizione,DESCRIPTION_MAX_LENGTH));
		dto.setCodiceFiscaleOperatore(codiceFiscaleUtente);
		dto.setCodiceApplicazione(codiceApplicazione);

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return dto;
	}

	
	private String limitToMaxLength ( String txt, int maxLength ) {
        if (( txt == null ) ||(txt.length () <= maxLength)) {
            return txt;
        }
        return txt.substring ( 0, (maxLength - 5)) + "...";
    }
	
	
	@Override
	 public void logAuditOperation(CsiLogAuditOperationEnum operation, PrincipalDto principalDto, String tabella, String oggetto, String descrizione){
	    	
//	    	try {
////	    		transaction.begin ();
	    		
				switch (operation) {
				case ACCESS:
					access(principalDto, tabella, oggetto, descrizione);
					break;
				case ACCESS_DENIED:
					accessDenied(principalDto, tabella, oggetto, descrizione);
					break;
				case EXTRACT:
					extract(principalDto, tabella, oggetto, descrizione);
					break;
				case DELETE:
					delete(principalDto, tabella, oggetto, descrizione);
					break;
				case INSERT:
					insert(principalDto, tabella, oggetto, descrizione);
					break;
				case UPDATE:
					update(principalDto, tabella, oggetto, descrizione);
					break;
				default:
					break;
				}
	    }
}
