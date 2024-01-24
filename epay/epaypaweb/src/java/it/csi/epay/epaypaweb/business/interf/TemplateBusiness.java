/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.business.interf;

import javax.ejb.Local;

import it.csi.epay.epaypaweb.dto.ElementoFlussoDto;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.TemplateDto;
import it.csi.epay.epaypaweb.dto.mapper.TemplateMapper;
import it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.util.spreadsheet.SpreadsheetGenerator;

//@formatter:off
/** logica di business */
@Local
public interface TemplateBusiness {

	/** restituisce il template cercandolo per id ente e id tipo flusso */
	public TemplateDto getTemplate(Integer idEnte, Integer idTipoFlusso) throws BusinessException;

	/** resituisce il generatore inizializzato dai dati del flusso per il formato di output specificato */
	public <T extends ElementoFlussoDto> SpreadsheetGenerator generate(FlussoCompletoDto<T> flussoCompleto, TemplateDto template, TemplateMapper<T> mapper, TipoFormatoFileEnum tipoFormato);

	/** costruisce il nome del file del flusso */
	public String buildFilename(FlussoDto flussoDto, TipoFormatoFileEnum tipoFormato);

}
//@formatter:on
