/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.business.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epayapi.business.AuditService;
import it.csi.epay.epayapi.dto.audit.AuditOperation;
import it.csi.epay.epayapi.dto.security.ClientInfo;
import it.csi.epay.epayapi.integration.domain.CsiLogAudit;
import it.csi.epay.epayapi.integration.repository.CsiLogAuditRepository;
import it.csi.epay.epayapi.util.Constants;
import it.csi.epay.epayapi.util.SecurityUtils;


/**
 * Implementazione del servizio per la gestione dell'audit
 */
@Service
public class AuditServiceImpl implements AuditService {

    private static final String LOG_PREFIX = "[AUDIT] ";

    private static final Logger logger = LogManager.getLogger ( AuditService.class );

    @Autowired
    private CsiLogAuditRepository repository;

    @Transactional
    @Override
    public void saveAudit ( AuditOperation operation ) {
        saveAudit ( operation, null, null );
    }

    @Transactional
    @Override
    public void saveAudit ( AuditOperation operation, String subject ) {
        saveAudit ( operation, null, subject );
    }

    @Transactional
    @Override
    public void saveAudit ( AuditOperation operation, String id, String subject ) {

        Long auditId = null;

        try {
            CsiLogAudit audit = buildAuditRecord ( operation, id, subject );

            audit = repository.save ( audit );
            auditId = audit.getIdLog ();
            logger.debug ( "saved audit " + audit );
        } catch ( Exception e ) {

            logger.warn ( LOG_PREFIX + "error saving audit", e );
        }

        logger.info ( LOG_PREFIX + "[" + auditId + "] " + operation.name () + " id=" + id + ", subject=" + subject );

    }

    private CsiLogAudit buildAuditRecord ( AuditOperation operation, String id, String subject ) {
        HttpServletRequest request = SecurityUtils.getCurrentRequest ();
        ClientInfo client = SecurityUtils.getCurrentClient ();

        CsiLogAudit audit = new CsiLogAudit ();

        audit.setIdLog ( null );
        audit.setDataOra ( new Date () );
        audit.setIdApp ( Constants.COMPONENT_NAME );
        audit.setIpAddress ( request != null ? request.getRemoteAddr () : null );
        audit.setUtente ( client != null ? client.getCodice () : "SYSTEM" );

        audit.setOperazione ( operation != null ? operation.name () : "UNKNOWN" );
        audit.setKeyOper ( id != null ? id : "N/A" );
        audit.setOggOper ( subject != null ? subject : "N/A" );

        return audit;
    }
}
