/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.util.spreadsheet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import it.csi.epay.epaypaweb.dto.ColonnaTemplateDto;
import it.csi.epay.epaypaweb.dto.ElementoFlussoDto;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.TemplateDto;
import it.csi.epay.epaypaweb.dto.mapper.TemplateMapper;
import it.csi.epay.epaypaweb.enumeration.CampoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum;

public class PageableGeneratorCSV <T extends ElementoFlussoDto> extends GeneratorCSV implements PageableSpreadsheetGenerator<T>{
	
	protected TemplateDto template;
	protected TemplateMapper<T> templateMapper;
	protected OutputStream destination;
	protected boolean titleInserted;
	
	public PageableGeneratorCSV(TemplateDto template, TemplateMapper<T> templateMapper, OutputStream destination) {
		super();
		this.template = template;
		this.templateMapper = templateMapper;
		this.destination = destination;
		this.titleInserted = false;
		
		for (ColonnaTemplateDto col : template.getColonneTemplate()) {
			CampoFlussoEnum campoFlusso = col.getCampoFlusso();
			addColumn(new SpreadsheetColumn(campoFlusso.name(), col.getIntestazione(), col.getFormato(TipoFormatoFileEnum.CSV), col.getPosizioneOrdinale()));
		}
	}
	
	@Override
	protected void insertColumnTitles() {
		if (!titleInserted) {
			super.insertColumnTitles();
			titleInserted = true;
		}
	}

	@Override
	public void addData(FlussoCompletoDto<T> flussoCompleto) throws IOException {
		addData(flussoCompleto,false);
		
	}
	
	@Override
	public void addData(FlussoCompletoDto<T> flussoCompleto, boolean forceDataFlush) throws IOException {
		FlussoDto head = flussoCompleto.getFlusso();
		for (T item : flussoCompleto.getItemList()) {
			newRow();
			for (ColonnaTemplateDto col : template.getColonneTemplate()) {
				CampoFlussoEnum campoFlusso = col.getCampoFlusso();
				setColumnValue(campoFlusso.name(), templateMapper.getValue(head, item, campoFlusso));
			}
		}
		if(forceDataFlush) {
			flushData();
		}
	}
	
	@Override
	public void flushData() throws IOException {
		
		byte[] data = getData();
		if(data != null && data.length > 0) {
			destination.write(data);
		}
		dataOut = new StringBuilder();
	}

}
