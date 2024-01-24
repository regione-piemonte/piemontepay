/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.util.spreadsheet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import it.csi.epay.epaypaweb.dto.ColonnaTemplateDto;
import it.csi.epay.epaypaweb.dto.ElementoFlussoDto;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.TemplateDto;
import it.csi.epay.epaypaweb.dto.mapper.TemplateMapper;
import it.csi.epay.epaypaweb.enumeration.CampoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum;

public class PageableGeneratorXLSX <T extends ElementoFlussoDto> extends GeneratorXLSX implements PageableSpreadsheetGenerator<T>{

	protected TemplateDto template;
	protected TemplateMapper<T> templateMapper;
	protected OutputStream destination;
	
	public PageableGeneratorXLSX(TemplateDto template, TemplateMapper<T> templateMapper, OutputStream destination,int pageSize) {
		
		this.wb = new SXSSFWorkbook(pageSize);
		this.wb = new SXSSFWorkbook();
		this.sheet = wb.createSheet();
		this.createHelper = wb.getCreationHelper();
		this.cellStyles = new ArrayList<>();
		this.dataOut = new ByteArrayOutputStream();
		
		this.template = template;
		this.templateMapper = templateMapper;
		this.destination = destination;
		
		for (ColonnaTemplateDto col : template.getColonneTemplate()) {
			CampoFlussoEnum campoFlusso = col.getCampoFlusso();
			addColumn(new SpreadsheetColumn(campoFlusso.name(), col.getIntestazione(), col.getFormato(TipoFormatoFileEnum.XLSX), col.getPosizioneOrdinale()));
		}
	}

	@Override
	public void flushData() throws IOException {
		newRow(); //
		wb.write(destination);
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
	}

}
