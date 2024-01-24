/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaymodric.business.epaymodric.audit.CSILogAuditActionRegister;
import it.csi.epay.epaymodric.business.epaymodric.audit.CSILogAuditApplicationConfiguration;
import it.csi.epay.epaymodric.business.epaymodric.manager.AuditManager;
import it.csi.epay.epaymodric.business.epaymodric.model.AbstractCSILogAuditedParentEntity;
import it.csi.epay.epaymodric.business.epaymodric.model.AuditAction;
import it.csi.epay.epaymodric.business.epaymodric.model.CsiLogAudit;
import it.csi.epay.epaymodric.business.epaymodric.repository.CsiLogAuditRepository;
import it.csi.epay.epaymodric.business.epaymodric.security.PrincipalVO;
import it.csi.epay.epaymodric.business.epaymodric.security.RequestContextUtils;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;


@Component
@Transactional
public class AuditManagerImpl implements AuditManager, ApplicationContextAware {

    private final Logger logger = LogManager.getLogger ( AuditManager.class );

    private final Logger auditTextLogger = LogManager.getLogger ( "it.csi.epay.epaymodric.audit.text" );

    private final Logger auditDataLogger = LogManager.getLogger ( "it.csi.epay.epaymodric.audit.data" );

    private ApplicationContext applicationContext;

    @Autowired
    private CSILogAuditApplicationConfiguration csiILogAuditApplicationConfiguration;

    @Autowired
    private CsiLogAuditRepository csiLogAuditRepository;

    @Override
    public void setApplicationContext ( ApplicationContext applicationContext ) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext () {
        return applicationContext;
    }

    @Override
    public void preExport ( String className, List<String> target ) {

        logger.trace ( "PRE-EXPORT " + className );
        if ( !csiILogAuditApplicationConfiguration.getAuditEnabled () ) {
            return;
        }

        PrincipalVO principal = RequestContextUtils.getPrincipalIfPresent ();
        CsiLogAudit dto = getAudit ( CSILogAuditActionRegister.ESTRAZIONE ,principal );

        dto.setEntitaOggetto ( className.replace("(Download file)".trim(), "") );

        String id = "";
       
        for ( Object o: target ) {
            id += getKey ( o ) + "; ";
        }
        
        if (null != principal && null != principal.getEnte() && null!= principal.getEnte().getId())
		{
        	id= "ID ENTE: "+principal.getEnte().getId()+"; "+id;
		}
        id = id.trim ();
//        id= id.substring(0, id.length()-1);

        dto.setIdOggetto ( id );

        dto.setDescrizione ( "esportazione delle entita' " + className + " con ID " + id );

        save ( dto );
    }

    @Override
    public void prePersist ( Object target ) {

        logger.trace ( "PRE-PERSIST " + target.getClass ().getSimpleName () );
        if ( !csiILogAuditApplicationConfiguration.getAuditEnabled () ) {
            return;
        }

        PrincipalVO principal = RequestContextUtils.getPrincipalIfPresent ();
        CsiLogAudit dto = getAudit ( CSILogAuditActionRegister.INSERIMENTO , principal);

        dto.setEntitaOggetto ( target.getClass ().getSimpleName () );
        dto.setIdOggetto ( getIdOggettto(target, principal) );

        dto.setDescrizione ( "inserimento di una nuova entita' " + target.getClass ().getSimpleName () + " con ID " + dto.getIdOggetto () );

        save ( dto );
    }

	private String getIdOggettto(Object target, PrincipalVO principal) 
	{
		String result= "";
		if (null != principal && null != principal.getEnte() && null!= principal.getEnte().getId())
		{
			result= "ID ENTE: "+principal.getEnte().getId()+"; "+result;
		}

		result= result+ getKey ( target );
		return result;
	}

    @Override
    public void preUpdate ( Object target ) {

        logger.trace ( "PRE-UPDATE " + target.getClass ().getSimpleName () );
        if ( !csiILogAuditApplicationConfiguration.getAuditEnabled () ) {
            return;
        }

        PrincipalVO principal = RequestContextUtils.getPrincipalIfPresent ();
        CsiLogAudit dto = getAudit ( CSILogAuditActionRegister.MODIFICA, principal );

        dto.setEntitaOggetto ( target.getClass ().getSimpleName () );
        dto.setIdOggetto ( getIdOggettto(target, principal) );

        dto.setDescrizione ( "aggiornamento dell' entita' " + target.getClass ().getSimpleName () + " con ID " + dto.getIdOggetto () );

        save ( dto );
    }

    @Override
    public void preRemove ( Object target ) {

        logger.trace ( "PRE-REMOVE " + target.getClass ().getSimpleName () );
        if ( !csiILogAuditApplicationConfiguration.getAuditEnabled () ) {
            return;
        }

        PrincipalVO principal = RequestContextUtils.getPrincipalIfPresent ();
        CsiLogAudit dto = getAudit ( CSILogAuditActionRegister.ELIMINAZIONE, principal );

        dto.setEntitaOggetto ( target.getClass ().getSimpleName () );
        dto.setIdOggetto ( getIdOggettto(target, principal) );

        dto.setDescrizione ( "eliminazione dell' entita' " + target.getClass ().getSimpleName () + " con ID " + dto.getIdOggetto () );

        save ( dto );
    }

    private String represent ( Object raw ) {
        if ( raw == null ) {
            return "null";
        }
        try {
            if ( raw instanceof String ) {
                return "\"" + (String) raw + "\"";
            } else {
				return raw.toString ();
			}
        } catch ( Exception e ) {
            return String.valueOf ( raw );
        }
    }

    private String getKey ( Object o ) {
        if ( o == null ) {
            return "null";
        } else if ( o instanceof AbstractCSILogAuditedParentEntity ) {
            return ( (AbstractCSILogAuditedParentEntity) o ).getPrimaryKeyRepresentation ();
        } else {
            return String.valueOf ( o );
        }
    }

    private CsiLogAudit getAudit ( CSILogAuditActionRegister action,PrincipalVO principal ) {
        CsiLogAudit dto = new CsiLogAudit ();

        dto.setId ( null );
        dto.setIdApplicazione ( Costanti.CODICE_APPLICAZIONE );
        dto.setCodiceApplicazione ( "?" );
        dto.setDataOra ( Timestamp.valueOf ( LocalDateTime.now () ) );

       

        if ( principal != null ) {
            dto.setIndirizzoIp ( principal.getIpOrigine () );
            dto.setCodiceFiscaleOperatore ( principal.getUtente ().getCodiceFiscale () );
            dto.setIdUtente ( principal.getUtente ().getId ().intValue () );
            dto.setIdApplicazione ( principal.getCodiceApplicativoChiamante () );

        } else {
            dto.setIndirizzoIp ( null );
            dto.setCodiceFiscaleOperatore ( "SYSTEM" );
            dto.setIdUtente ( 0 );
        }

        dto.setAction ( new AuditAction () );
        dto.getAction ().setId ( action.getId () );
        dto.getAction ().setDescrizione ( action.name () );

        dto.setDescrizioneOperazione ( action.name () );

        return dto;
    }

    private void save ( CsiLogAudit raw ) {
        if ( logger.isDebugEnabled () ) {
            logger.debug ( "[AUDIT] saving audit entity " + represent ( raw ) );
        }

        raw.setDescrizione ( limit ( raw.getDescrizione (), 2000 ) );

        try {
            if ( auditTextLogger.isInfoEnabled () ) {
                auditTextLogger.info ( raw.getDescrizione () );
            }
            if ( auditDataLogger.isInfoEnabled () ) {
                auditDataLogger.info ( represent ( raw ) );
            }

            csiLogAuditRepository.save ( raw );
        } catch ( Exception e ) {
            logger.error ( "errore nel salvataggio della voce di audit", e );
            throw new RuntimeException ( e );
        }
    }

    private String limit ( String raw, int num ) {
        if ( raw == null ) {
            return raw;
        }
        if ( raw.length () <= num ) {
            return raw;
        }
        return raw.substring ( 0, num - 5 ) + "...";
    }

}
