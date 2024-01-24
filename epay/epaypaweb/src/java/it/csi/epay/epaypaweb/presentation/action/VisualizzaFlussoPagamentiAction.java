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
import it.csi.epay.epaypaweb.dto.NotificaPagamentoLightDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.TotalSizeAndLightListDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum;
import it.csi.epay.epaypaweb.enumeration.SortTypeEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;
import it.csi.epay.epaypaweb.presentation.dto.ActionScope;
import it.csi.epay.epaypaweb.presentation.dto.DataTableResponse;
import it.csi.epay.epaypaweb.presentation.dto.StatoCursore;


@Namespace("/")
@InterceptorRef("epaypawebStack")
@Results({
//@formatter:off
	@Result(name = INPUT, location = "pages/visualizza-flusso-pagamenti.jsp"),
	@Result(name = "json-table-data", type = "json", params = { "root", "dataTableResponse", "noCache", "true" })
//@formatter:on
})
public class VisualizzaFlussoPagamentiAction extends VisualizzaFlussoBaseAction {
	static private final long serialVersionUID = 1L;
	static private final String CLASSNAME = VisualizzaFlussoPagamentiAction.class.getSimpleName();

	private DataTableResponse<NotificaPagamentoLightDto> dataTableResponse;

	@EJB
	private GestioneFlussiBusiness gestioneFlussiBusiness;

	@Action("entry-visualizza-flusso-pagamenti")
	@Authorization(cdu = "DET_ESP")
	@SkipValidation
	@SuppressWarnings("unchecked")
	public String entryVisualizzaFlussoPagamenti() {
		String methodName = "entryVisualizzaFlussoPagamenti";
		

		String result = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			ActionScope actionScope = getSessionContext().getActionScope();
			if (isInit()) {
				// ramo percorso se si arriva dalla ricerca
				actionScope.putValue("idFlusso", idFlusso);
				sortingDir = "asc";
				sortingIdx = 3;

			} else {
				// ramo percorso al ritorno dal dettaglio: riposiziona la tabella nella pagina che contiene l'ultimo dettaglio visitato con scorrimento
				idFlusso = actionScope.getValue("idFlusso");
				//
				StatoCursore<ColumnNameNotificaPagamentoEnum> statoCursore = (StatoCursore<ColumnNameNotificaPagamentoEnum>) getSessionContext().getStatoCursore();
				if (statoCursore != null) {
					restartFrom = statoCursore.getNumeroRighePerPagina() * (statoCursore.getNumeroPagina() - 1);
					sortingDir = statoCursore.getCriteriOrdinamento().get(0).getSortTypeEnum() == SortTypeEnum.ASC ? "asc" : "desc";
					switch (statoCursore.getCriteriOrdinamento().get(0).getColumnNameEnum()) {
					default:
					case ID_POSIZIONE_DEBITORIA:
						sortingIdx = 0;
						break;
					case IUV:
						sortingIdx = 1;
						break;
					case IMPORTO_PAGATO:
						sortingIdx = 2;
						break;
					case DATA_ESITO_PAGAMENTO:
						sortingIdx = 3;
						break;
					case DESCRIZIONE_CAUSALE_VERSAMENTO:
						sortingIdx = 4;
						break;
					case ID_FISCALE_DEBITORE:
						sortingIdx = 5;
						break;
					case CONCAT_COGNOME_NOME_OR_RAGIONE_SOCIALE_DEBITORE:
						sortingIdx = 6;
						break;
					}
					pageLength = statoCursore.getNumeroRighePerPagina();
				}
			}

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

	@Action("visualizza-flusso-pagamenti-json")
	@Authorization(cdu = "DET_ESP")
	@SkipValidation
	public String visualizzaFlussoPagamentiJSON() throws Exception {
		String methodName = "visualizzaFlussoPagamentiJSON";
		
		log.info ( CLASSNAME + " " + methodName + " - START" );

		List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> sorting = null;
		if (sortingCol != null && sortingDir != null) {
			sorting = new ArrayList<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>>();
			CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum> sortingItem = new CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>();

			if ("idPosizioneDebitoria".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameNotificaPagamentoEnum.ID_POSIZIONE_DEBITORIA);
			else if ("IUV".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameNotificaPagamentoEnum.IUV);
			else if ("importoPagato".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameNotificaPagamentoEnum.IMPORTO_PAGATO);
			else if ("dataEsitoPagamento".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameNotificaPagamentoEnum.DATA_ESITO_PAGAMENTO);
			else if ("desCausaleVersamento".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameNotificaPagamentoEnum.DESCRIZIONE_CAUSALE_VERSAMENTO);
			else if ("soggettoDebitore.idUnivocoFiscale".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameNotificaPagamentoEnum.ID_FISCALE_DEBITORE);
			else if ("soggettoDebitore.cognomeNomeOrRagioneSociale".equals(sortingCol))
				sortingItem.setColumnNameEnum(ColumnNameNotificaPagamentoEnum.CONCAT_COGNOME_NOME_OR_RAGIONE_SOCIALE_DEBITORE);

			sortingItem.setSortTypeEnum("asc".equals(sortingDir) ? SortTypeEnum.ASC : SortTypeEnum.DESC);
			sorting.add(sortingItem);
		}

		PaginazioneDto pag = new PaginazioneDto((start / length) + 1, length);

		try {
			TotalSizeAndLightListDto<NotificaPagamentoLightDto> totalSizeAndLightList = gestioneFlussiBusiness.getTotalSizeAndNotificaPagamentoLightList(idFlusso, sorting, pag);

			Integer count = totalSizeAndLightList.getTotalSize().intValue();
			List<NotificaPagamentoLightDto> lightList = totalSizeAndLightList.getLightList();

			dataTableResponse = new DataTableResponse<NotificaPagamentoLightDto>();
			dataTableResponse.setRecordsTotal(count);
			dataTableResponse.setRecordsFiltered(count);
			dataTableResponse.setDraw(draw);
			dataTableResponse.setData(lightList);

			log.debug("lightList:" + lightList);

			StatoCursore<ColumnNameNotificaPagamentoEnum> statoCursore;
			statoCursore = new StatoCursore<ColumnNameNotificaPagamentoEnum>(idFlusso, collectIds(lightList), pag.getNumeroPagina(), pag.getNumeroRighePerPagina(), count, sorting);
			getSessionContext().setStatoCursore(statoCursore);

		} catch (BusinessException e) {
			log.error("Errore imprevisto", e);
		}

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return "json-table-data";
	}

	public DataTableResponse<NotificaPagamentoLightDto> getDataTableResponse() {
		return dataTableResponse;
	}

	public void setDataTableResponse(DataTableResponse<NotificaPagamentoLightDto> dataTableResponse) {
		this.dataTableResponse = dataTableResponse;
	}

}
