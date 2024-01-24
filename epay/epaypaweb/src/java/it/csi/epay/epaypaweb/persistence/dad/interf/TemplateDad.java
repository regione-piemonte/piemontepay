/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dad.interf;

import it.csi.epay.epaypaweb.dto.TemplateDto;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dad.EPaypaDadBase;

public interface TemplateDad extends EPaypaDadBase {

	public TemplateDto findTemplate(Integer idEnte, String nomeTemplate) throws PersistenceException;
	public TemplateDto findTemplate(Integer idEnte, Integer idTipoFlusso) throws PersistenceException;

    TemplateDto findTemplate ( Integer idTipoFlusso ) throws PersistenceException;
    
    TemplateDto findTemplateByEnteDiDefault ( Integer idTipoFlusso ) throws PersistenceException;
}
