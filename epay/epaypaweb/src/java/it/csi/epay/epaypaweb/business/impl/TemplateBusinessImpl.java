/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.business.impl;

import it.csi.epay.epaypaweb.business.EPaypaBusinessBase;
import it.csi.epay.epaypaweb.business.interf.TemplateBusiness;
import it.csi.epay.epaypaweb.dto.*;
import it.csi.epay.epaypaweb.dto.mapper.TemplateMapper;
import it.csi.epay.epaypaweb.enumeration.CampoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.persistence.dad.interf.TemplateDad;
import it.csi.epay.epaypaweb.util.spreadsheet.GeneratorCSV;
import it.csi.epay.epaypaweb.util.spreadsheet.GeneratorXLSX;
import it.csi.epay.epaypaweb.util.spreadsheet.SpreadsheetColumn;
import it.csi.epay.epaypaweb.util.spreadsheet.SpreadsheetGenerator;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TemplateBusinessImpl extends EPaypaBusinessBase implements TemplateBusiness {

	private static final String CLASSNAME = TemplateBusinessImpl.class.getSimpleName ();

	@Inject
	TemplateDad templateDad;

	@Override
    public TemplateDto getTemplate(Integer idEnte, Integer idTipoFlusso) throws BusinessException {
		String methodName = "getTemplate";
		
		
		

		TemplateDto resDto = null;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

            if ( idEnte == null ) {
                resDto = templateDad.findTemplateByEnteDiDefault( idTipoFlusso );

            } else {
                resDto = templateDad.findTemplate ( idEnte, idTipoFlusso );

                if ( resDto == null ) {
                    resDto = templateDad.findTemplateByEnteDiDefault( idTipoFlusso );
                }
            }

		} catch (Throwable e) {
			throw new BusinessException("Errore imprevisto in " + CLASSNAME + "::" + methodName, e);

		} finally {
			log.info("result:" + resDto);
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return resDto;
	}

	//@formatter:on
	@Override
	public <T extends ElementoFlussoDto> SpreadsheetGenerator generate(FlussoCompletoDto<T> flussoCompleto, TemplateDto template, TemplateMapper<T> templateMapper, TipoFormatoFileEnum tipoFormato) {
		String methodName = "generateCSV";
		
		
		
		

		SpreadsheetGenerator gen = null;
		switch (tipoFormato) {
		case CSV:
			gen = new GeneratorCSV();
			break;
		case XLSX:
			gen = new GeneratorXLSX();
			break;
		}

		if (gen != null) {
			for (ColonnaTemplateDto col : template.getColonneTemplate()) {
				CampoFlussoEnum campoFlusso = col.getCampoFlusso();
				gen.addColumn(new SpreadsheetColumn(campoFlusso.name(), col.getIntestazione(), col.getFormato(tipoFormato), col.getPosizioneOrdinale()));
			}

			FlussoDto head = flussoCompleto.getFlusso();
			for (T item : flussoCompleto.getItemList()) {
				gen.newRow();
				for (ColonnaTemplateDto col : template.getColonneTemplate()) {
					CampoFlussoEnum campoFlusso = col.getCampoFlusso();
					gen.setColumnValue(campoFlusso.name(), templateMapper.getValue(head, item, campoFlusso));
				}
			}
		}

		return gen;
	}

	@Override
    public String buildFilename(FlussoDto flussoDto, TipoFormatoFileEnum tipoFormato) {
		StringBuilder filename = null;

		if (flussoDto != null) {
			filename = new StringBuilder();

			// prefix
			switch (flussoDto.getTipoFlusso()) {
			case NOTIFICHE_PAGAMENTO:
				filename = new StringBuilder("NPAG_");
				break;
			case AGGIORNAMENTO_POSIZIONI_DEBITORIE:
				filename = new StringBuilder("AGPD_");
				break;
			case AVVISI_SCADUTI:
				filename = new StringBuilder("AVSC_");
				break;
			case LISTE_DI_CARICO:
				filename = new StringBuilder("LIDC_");
				break;
			case TRASMETTI_FLUSSO_RENDICONTAZIONE:
				filename = new StringBuilder("FREND_");
				break;
            case REPORT_PAGAMENTI_ENTE_XLS :
            case REPORT_PAGAMENTI_ENTE_CSV :
                filename = new StringBuilder ( "REPORT_PAGAMENTI" );
                break;
			}

			// body
			switch (flussoDto.getTipoFlusso()) {
			case NOTIFICHE_PAGAMENTO:
			case AGGIORNAMENTO_POSIZIONI_DEBITORIE:
			case AVVISI_SCADUTI:
			case LISTE_DI_CARICO:
				StringBuilder eventuallyPSorAT_ = new StringBuilder();
				if (flussoDto.getPagamentiSpontanei() != null) {
					eventuallyPSorAT_.append(flussoDto.getPagamentiSpontanei() ? "PS" : "AT");
					eventuallyPSorAT_.append("_");
				}
				filename.append(flussoDto.getCodFiscaleEnte())
						.append("_")
						.append(flussoDto.getCodVersamento())
						.append("_")
						.append(eventuallyPSorAT_)
						.append(flussoDto.getIdMessaggio());
				break;
			case TRASMETTI_FLUSSO_RENDICONTAZIONE:
				filename.append(flussoDto.getCodFiscaleEnte())
						.append("_")
						.append(flussoDto.getIdMessaggio());
				break;
			}

			// extension
			switch (tipoFormato) {
			case CSV:
				filename.append(".csv");
				break;
			case XLSX:
				filename.append(".xlsx");
				break;
			}
		}

		return filename != null ? filename.toString() : null;
	}
	//@formatter:off

}
