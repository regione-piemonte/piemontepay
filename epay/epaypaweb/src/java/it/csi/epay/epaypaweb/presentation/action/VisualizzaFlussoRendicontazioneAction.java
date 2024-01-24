/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import static com.opensymphony.xwork2.Action.INPUT;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;

import it.csi.epay.epaypaweb.business.interf.GestioneFlussiBusiness;
import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.RiversamentoDto;
import it.csi.epay.epaypaweb.dto.TotalSizeAndLightListDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameRiversamentoEnum;
import it.csi.epay.epaypaweb.enumeration.SortTypeEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;
import it.csi.epay.epaypaweb.presentation.dto.ActionScope;
import it.csi.epay.epaypaweb.presentation.dto.DataTableResponse;


@Namespace("/")
@InterceptorRef("epaypawebStack")
@Results({
//@formatter:off
	@Result(name = INPUT, location = "pages/visualizza-flusso-rendicontazione.jsp"),
	@Result(name = "json-table-data", type = "json", params = { "root", "dataTableResponse", "noCache", "true" })
//@formatter:on
})
public class VisualizzaFlussoRendicontazioneAction extends VisualizzaFlussoBaseAction {
	static private final long serialVersionUID = 1L;
	static private final String CLASSNAME = VisualizzaFlussoRendicontazioneAction.class.getSimpleName();

	private DataTableResponse<RiversamentoDto> dataTableResponse;

	@EJB
	private GestioneFlussiBusiness gestioneFlussiBusiness;

	@Action("entry-visualizza-flusso-rendicontazione")
	@Authorization(cdu = "DET_FR")
	@SkipValidation
	public String entryVisualizzaFlussoRendicontazione() {
		String methodName = "entryVisualizzaFlussoRendicontazione";
		

		String result = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			ActionScope actionScope = getSessionContext().getActionScope();
			if (isInit())
				actionScope.putValue("idFlusso", idFlusso);
			else
				idFlusso = actionScope.getValue("idFlusso");

			flussoSelezionato = gestioneFlussiBusiness.getFlussoLight(idFlusso);
			result = INPUT;

		} catch (BusinessException e) {
			log.error("Errore imprevisto", e);
			addActionError(getText("system.error", new String[] { e.getClass().getName(), e.getMessage() }));
			result = "system-error";

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return result;
	}

	@Action("visualizza-flusso-rendicontazione-json")
	@Authorization(cdu = "DET_FR")
	@SkipValidation
	public String visualizzaFlussoRendicontazioneJSON() throws Exception {
		String methodName = "visualizzaFlussoRendicontazioneJSON";
		
		log.info ( CLASSNAME + " " + methodName + " - START" );

		List<CriterioOrdinamentoDto<ColumnNameRiversamentoEnum>> sorting = null;
		if (sortingCol != null && sortingDir != null) {
			sorting = new ArrayList<CriterioOrdinamentoDto<ColumnNameRiversamentoEnum>>();
			CriterioOrdinamentoDto<ColumnNameRiversamentoEnum> sortingItem = new CriterioOrdinamentoDto<ColumnNameRiversamentoEnum>();

			if ("IUV".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameRiversamentoEnum.IUV);
			else if ("IUR".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameRiversamentoEnum.IUR);
			else if ("indicePagamento".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameRiversamentoEnum.INDICE_DATI_RT);
			else if ("importoPagato".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameRiversamentoEnum.IMPORTO_PAGATO);
			else if ("codAndDesEsito".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameRiversamentoEnum.COD_ESITO);
			else if ("dataEsito".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameRiversamentoEnum.DATA_ESITO);

			sortingItem.setSortTypeEnum("asc".equals(sortingDir) ? SortTypeEnum.ASC : SortTypeEnum.DESC);
			sorting.add(sortingItem);
		}

		PaginazioneDto pag = new PaginazioneDto((start / length) + 1, length);

		try {
			TotalSizeAndLightListDto<RiversamentoDto> totalSizeAndLightList = gestioneFlussiBusiness.getTotalSizeAndRiversamentoList(idFlusso, sorting, pag);

			Long count = totalSizeAndLightList.getTotalSize();

			dataTableResponse = new DataTableResponse<RiversamentoDto>();
			dataTableResponse.setRecordsTotal(count.intValue());
			dataTableResponse.setRecordsFiltered(count.intValue());
			dataTableResponse.setDraw(draw);
			dataTableResponse.setData(totalSizeAndLightList.getLightList());

			log.debug("lightList:" + totalSizeAndLightList.getLightList());

		} catch (BusinessException e) {
			log.error("Errore imprevisto", e);
		}

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return "json-table-data";
	}

	public DataTableResponse<RiversamentoDto> getDataTableResponse() {
		return dataTableResponse;
	}

	public void setDataTableResponse(DataTableResponse<RiversamentoDto> dataTableResponse) {
		this.dataTableResponse = dataTableResponse;
	}

}
