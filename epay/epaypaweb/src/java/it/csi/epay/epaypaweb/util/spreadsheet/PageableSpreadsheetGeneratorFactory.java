/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.util.spreadsheet;

import java.io.OutputStream;

import it.csi.epay.epaypaweb.dto.ElementoFlussoDto;
import it.csi.epay.epaypaweb.dto.TemplateDto;
import it.csi.epay.epaypaweb.dto.mapper.TemplateMapper;
import it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum;

public class PageableSpreadsheetGeneratorFactory {

	public static <T extends ElementoFlussoDto> PageableSpreadsheetGenerator<T> getPageableSpreadsheetGenerator(TemplateDto template,TemplateMapper<T> templateMapper, OutputStream destinations,TipoFormatoFileEnum tipoFormato,int pageSize) {
		
		PageableSpreadsheetGenerator<T> result = null;
		
		switch (tipoFormato) {
		case CSV:
			result = new PageableGeneratorCSV<T>(template,templateMapper,destinations);
			break;
		case XLSX:
			result = new PageableGeneratorXLSX<T>(template,templateMapper,destinations,pageSize);
			break;
		default:
			throw new IllegalArgumentException("Tipo formato non valido");
				
		}
		
		return result;
	}
}
