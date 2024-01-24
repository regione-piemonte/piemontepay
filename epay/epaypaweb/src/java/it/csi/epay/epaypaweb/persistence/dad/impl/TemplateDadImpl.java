/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dad.impl;

import javax.inject.Inject;

import it.csi.epay.epaypaweb.dto.TemplateDto;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dad.EPaypaDadBaseImpl;
import it.csi.epay.epaypaweb.persistence.dad.interf.TemplateDad;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTEnteDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTTemplateDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTTemplate;
import it.csi.epay.epaypaweb.util.Costanti;


public class TemplateDadImpl extends EPaypaDadBaseImpl implements TemplateDad {

	private static final String CLASSNAME = TemplateDadImpl.class.getSimpleName ();

	@Inject
	private EpaypaTTemplateDao epaypaTTemplateDao;
	
	@Inject
	private EpaypaTEnteDao epaypaTEnteDao;

	@Override
	public TemplateDto findTemplate(Integer idEnte, Integer idTipoFlusso) throws PersistenceException {
		String methodName = "findTemplate";
		
		
		

		TemplateDto template = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			// se non trovo il template per quell'ente restituisco null invece di nullpointer.
			EpaypaTTemplate tmp = epaypaTTemplateDao.findOneByIdEnteTipoFlusso(idEnte, idTipoFlusso);
            if ( null != tmp ) {
                template = toTemplateDto ( tmp );
            }
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return template;
	}

    @Override
    public TemplateDto findTemplate ( Integer idTipoFlusso ) throws PersistenceException {
        String methodName = "findTemplate";
        
        

        TemplateDto template = null;
        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );
            template = toTemplateDto ( epaypaTTemplateDao.findOneByTipoFlusso ( idTipoFlusso ) );
        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return template;
    }

	@Override
	public TemplateDto findTemplate(Integer idEnte, String nomeTemplate) throws PersistenceException {
		String methodName = "findTemplate";
		
		

		TemplateDto template = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			template = toTemplateDto(epaypaTTemplateDao.findOneByIdEnteNomeTemplate(idEnte, nomeTemplate));
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return template;
	}

	@Override
	public TemplateDto findTemplateByEnteDiDefault(Integer idTipoFlusso) throws PersistenceException {
		String methodName = "findTemplateByEnteDiDefault";
		
		

		TemplateDto template = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			Integer idEnteDefault = epaypaTEnteDao.findIdEnteByCodFiscale(Costanti.CODICE_FISCALE_ENTE_DEFAULT);
			if ( idEnteDefault != null ) {
				template = toTemplateDto(epaypaTTemplateDao.findOneByIdEnteTipoFlusso ( idEnteDefault, idTipoFlusso ) );
			} else {
				log.warn ( "ID ENTE DI DEFAULT NON TROVATO" );
				template = toTemplateDto ( epaypaTTemplateDao.findOneByTipoFlusso ( idTipoFlusso ) );
			}
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return template;
	}

}
