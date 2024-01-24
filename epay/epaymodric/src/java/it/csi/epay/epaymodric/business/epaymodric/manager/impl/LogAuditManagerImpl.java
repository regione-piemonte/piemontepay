/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaymodric.business.epaymodric.manager.LogAuditManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.LogAuditUtility;
import it.csi.epay.epaymodric.business.epaymodric.model.CsiLogAudit;
import it.csi.epay.epaymodric.business.epaymodric.repository.LogAuditRepository;
import it.csi.epay.epaymodric.business.epaymodric.utils.ConversionUtils;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOAuditAction;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsLogAudit;


@Service
public class LogAuditManagerImpl implements LogAuditManager {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    LogAuditRepository logAuditRepository;

    @Override
    public DTOInputWsLogAudit salvaLogAudit ( DTOInputWsLogAudit dtoLogAudit ) {

        DTOInputWsLogAudit audit = new DTOInputWsLogAudit ();

        try {
            CsiLogAudit csiLogaudit = LogAuditUtility.trasformaInCsiLogAudit(dtoLogAudit);

            csiLogaudit = logAuditRepository.save ( csiLogaudit );

            audit.setCodappmodify ( csiLogaudit.getCodiceApplicazione () );
            audit.setDataOra ( ConversionUtils.convertTimeStampToString ( csiLogaudit.getDataOra () ) );
            audit.setDescrizione ( csiLogaudit.getDescrizione () );
            audit.setDtoAuditAction ( new DTOAuditAction () );
            audit.getDtoAuditAction ().setIdaction ( csiLogaudit.getAction ().getId () );
            audit.setUniqueid ( csiLogaudit.getId () );
            audit.setUtente ( csiLogaudit.getIdUtente () );
        } catch (Exception e) {
            logger.info ( "Errore durante la trasformazione in DTO " + e.getMessage () );
        }

        return audit;
    }

    //    // Recupera tutti CsiLogAudit
    //    @Override
    //    @Transactional
    //    public List<LogAudit> getAllCsiLogAudit () {
    //        logger.info ( "### Inizio metodo:: getAllCsiLogAudit della classe LogAuditManagerImpl###" );
    //        List<CsiLogAudit> listaCsiLogAudit = new ArrayList<CsiLogAudit> ();
    //        listaCsiLogAudit = logAuditRepository.findAll ();
    //        List<LogAudit> listaLogAudit = new ArrayList<LogAudit> ();
    //        LogAudit logaudit = null;
    //        for ( CsiLogAudit csiLogAudit: listaCsiLogAudit ) {
    //            logaudit = new LogAudit ();
    //            logaudit.setAuditaction ( new ActionAudit () );
    //            logaudit.getAuditaction ().setIdaction ( csiLogAudit.getAuditaction ().getIdaction () );
    //            logaudit.getAuditaction ().setDescription ( csiLogAudit.getAuditaction ().getDescription () );
    //            logaudit.setDataOra ( csiLogAudit.getDataOra () );
    //            logaudit.setCodappmodify ( csiLogAudit.getCodappmodify () );
    //            logaudit.setUniqueid ( csiLogAudit.getUniqueid () );
    //            logaudit.setUtente ( csiLogAudit.getUtente () );
    //            listaLogAudit.add ( logaudit );
    //        }
    //        logger.info ( "### Fine metodo:: getAllCsiLogAudit della classe LogAuditManagerImpl###" );
    //        return listaLogAudit;
    //    }
    //
    //    @Override
    //    @Transactional
    //    public List<LogAudit> getCsiLogAudit ( String idAction ) {
    //        LogAudit logaudit = null;
    //        List<LogAudit> listaLogAudit = new ArrayList<LogAudit> ();
    //        try {
    //            List<CsiLogAudit> csiLogAudit = logAuditRepository.findByAuditaction_idaction ( idAction );
    //            logaudit = null;
    //
    //            for ( CsiLogAudit csilog: csiLogAudit ) {
    //                logaudit = new LogAudit ();
    //                logaudit.setAuditaction ( new ActionAudit () );
    //                logaudit.getAuditaction ().setIdaction ( csilog.getAuditaction ().getIdaction () );
    //                logaudit.getAuditaction ().setDescription ( csilog.getAuditaction ().getDescription () );
    //                logaudit.setDataOra ( csilog.getDataOra () );
    //                logaudit.setCodappmodify ( csilog.getCodappmodify () );
    //                logaudit.setUniqueid ( csilog.getUniqueid () );
    //                logaudit.setUtente ( csilog.getUtente () );
    //                listaLogAudit.add ( logaudit );
    //            }
    //
    //        } catch ( Exception e ) {
    //            logger.info ( "Errore durante il recupero del logAudit" );
    //        }
    //        return listaLogAudit;
    //    }

}
