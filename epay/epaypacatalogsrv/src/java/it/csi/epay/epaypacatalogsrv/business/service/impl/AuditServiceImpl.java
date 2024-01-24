/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.audit.CSILogAuditActionRegister;
import it.csi.epay.epaypacatalogsrv.business.service.AuditService;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.model.AbstractCSILogAuditedParentEntity;
import it.csi.epay.epaypacatalogsrv.model.AuditAction;
import it.csi.epay.epaypacatalogsrv.model.CsiLogAudit;
import it.csi.epay.epaypacatalogsrv.repository.CsiLogAuditRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalVO;


@Component
@Transactional
public class AuditServiceImpl implements AuditService {

    private final Logger logger = LogManager.getLogger ( AuditService.class );

    private final Logger auditTextLogger = LogManager.getLogger ( "audit.text" );

    private final Logger auditDataLogger = LogManager.getLogger ( "audit.data" );

    @Autowired
    private CsiLogAuditRepository csiLogAuditRepository;

    @Override
    public void postAccesso ( CallerInputDto caller, PrincipalVO principal ) {

        if ( logger.isTraceEnabled () ) {
            logger.trace ( "POST-ACCESSO " + caller );
        }

        CsiLogAudit dto = getAudit ( CSILogAuditActionRegister.ACCESSO );

        dto.setEntitaOggetto ( CallerInputDto.class.getSimpleName () );
        dto.setIdOggetto ( caller.toString () );

        dto.setIndirizzoIp ( principal.getIpOrigine () );
        dto.setCodiceFiscaleOperatore ( principal.getUtente ().getCodiceFiscale () );
        dto.setIdUtente ( principal.getUtente ().getId ().intValue () );

        dto.setDescrizione (
            "accesso al sistema garantito per l'utente " + caller.getPrincipal ().getCodiceFiscale ()
                + " con credenziali: " + caller.toString () );

        if ( caller != null && caller.getCodiceApplicativo () != null ) {
            dto.setIdApplicazione ( caller.getCodiceApplicativo () );
        }

        save ( dto );
    }

    @Override
    public void postAccessoNegato ( CallerInputDto caller, Exception e ) {

        if ( logger.isTraceEnabled () ) {
            logger.trace ( "POST-ACCESSO-NEGATO " + caller );
        }

        CsiLogAudit dto = getAudit ( CSILogAuditActionRegister.ACCESSO_NEGATO );

        dto.setEntitaOggetto ( CallerInputDto.class.getSimpleName () );
        dto.setIdOggetto ( caller != null ? caller.toString () : "NULL" );

        if ( caller != null && caller.getPrincipal () != null ) {
            dto.setCodiceFiscaleOperatore ( caller.getPrincipal ().getCodiceFiscale () );
        }

        if ( caller != null && caller.getCodiceApplicativo () != null ) {
            dto.setIdApplicazione ( caller.getCodiceApplicativo () );
        }

        if ( caller == null ) {
            dto.setDescrizione (
                "accesso al sistema NEGATO per credenziali nulle "
                    + " : " + e.getMessage () );
        } else {
            if ( caller.getPrincipal () == null ) {
                dto.setDescrizione (
                    "accesso al sistema NEGATO per utente anonimo con credenziali: " + caller.toString ()
                        + " : " + e.getMessage () );
            } else {
                dto.setDescrizione (
                    "accesso al sistema NEGATO per l'utente " + caller.getPrincipal ().getCodiceFiscale ()
                        + " con credenziali: " + caller
                        + " : " + e.getMessage () );
            }
        }

        save ( dto );
    }

    @Override
    public void preExport ( String className, String descricption, List<String> target ) {

        logger.trace ( "PRE-EXPORT " + className );

        CsiLogAudit dto = getAudit ( CSILogAuditActionRegister.ESTRAZIONE );

        dto.setEntitaOggetto (className);

        String id = "";
        for ( Object o: target ) {
            id += getKey ( o ) + (!getKey ( o ).startsWith("FILTRO")? "; ": "");
        }
        id = id.trim ();

        dto.setIdOggetto ( id );

        dto.setDescrizione ( "esportazione delle entita' " + className +descricption+" con ID " + id );

        save ( dto );
    }
    
    

    @Override
    public void prePersist ( Object target ) {

        logger.trace ( "PRE-PERSIST " + target.getClass ().getSimpleName () );

        CsiLogAudit dto = getAudit ( CSILogAuditActionRegister.INSERIMENTO );

        dto.setEntitaOggetto ( target.getClass ().getSimpleName () );
        dto.setIdOggetto ( getKey ( target ) );

        dto.setDescrizione ( "inserimento di una nuova entita' " + target.getClass ().getSimpleName () + " con ID " + dto.getIdOggetto () );

        save ( dto );
    }

    @Override
    public void preUpdate ( Object target ) {

        logger.trace ( "PRE-UPDATE " + target.getClass ().getSimpleName () );

        CsiLogAudit dto = getAudit ( CSILogAuditActionRegister.MODIFICA );

        dto.setEntitaOggetto ( target.getClass ().getSimpleName () );
        dto.setIdOggetto ( getKey ( target ) );

        dto.setDescrizione ( "aggiornamento dell' entita' " + target.getClass ().getSimpleName () + " con ID " + dto.getIdOggetto () );

        save ( dto );
    }

    @Override
    public void preRemove ( Object target ) {

        logger.trace ( "PRE-REMOVE " + target.getClass ().getSimpleName () );

        CsiLogAudit dto = getAudit ( CSILogAuditActionRegister.ELIMINAZIONE );

        dto.setEntitaOggetto ( target.getClass ().getSimpleName () );
        dto.setIdOggetto ( getKey ( target ) );

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
            } else if ( raw instanceof Object ) {
                if ( raw.getClass ().getName ().startsWith ( "it.csi" ) ) {
                    // return raw.getClass ().getSimpleName () + ":" + mapper.writeValueAsString ( raw );
                    return raw.toString ();
                } else {
                    return raw.toString ();
                }
            } else {
                return String.valueOf ( raw );
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

    private CsiLogAudit getAudit ( CSILogAuditActionRegister action ) {
        CsiLogAudit dto = new CsiLogAudit ();

        dto.setId ( null );
        dto.setIdApplicazione ( Constants.APPLICATION_CODE );
        dto.setCodiceApplicazione ( "?" );
        dto.setDataOra ( Timestamp.valueOf ( LocalDateTime.now () ) );

        PrincipalVO principal = SecurityUtils.getPrincipalIfPresent ();

        if ( principal != null ) {
            dto.setIndirizzoIp ( principal.getIpOrigine () );
            dto.setCodiceFiscaleOperatore ( principal.getUtente ().getCodiceFiscale () );
            dto.setIdUtente ( principal.getUtente ().getId ().intValue () );
            if ( principal.getFruitore () != null ) {
                dto.setIdApplicazione ( principal.getFruitore ().getCodice () );
            }
        } else {
            dto.setIndirizzoIp ( null );
            dto.setCodiceFiscaleOperatore ( "SYSTEM" );
            dto.setIdUtente ( 0 );
        }

        dto.setAction ( new AuditAction () );
        dto.getAction ().setId ( action.getId () );
        dto.getAction ().setDescrizione ( action.name () );

        dto.setDescrizioneOperazione ( action.name () );

        // dto.setDescrizione ( descrizione );
        // dto.setDescrizioneOperazione ( descrizioneOperazione );
        // dto.setEntitaOggetto ( entitaOggetto );
        // dto.setIdOggetto ( idOggetto );

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
            throw e;
        }
    }

    private String limit ( String raw, int num ) {
        if ( raw == null ) {
            return null;
        }
        if ( raw.length () <= num ) {
            return raw;
        }
        return raw.substring ( 0, num - 5 ) + "...";
    }

}
