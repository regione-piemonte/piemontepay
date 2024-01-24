/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionContext;

import it.csi.epay.epaypaweb.business.interf.GestioneFlussiBusiness;
import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.FlussoLightDto;
import it.csi.epay.epaypaweb.dto.FlussoLightFilterDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.TotalSizeAndLightListDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.SortTypeEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;
import it.csi.epay.epaypaweb.presentation.dto.ActionScope;
import it.csi.epay.epaypaweb.presentation.dto.AjaxServiceResponse;
import it.csi.epay.epaypaweb.presentation.dto.DataTableResponse;


@Namespace("/")
@InterceptorRef("epaypawebStack")
@Results({
    //@formatter:off
    @Result(name = INPUT, location = "pages/ricerca-flussi-rendicontazioni.jsp"),
    @Result(name = "json-table-data", type = "json", params = { "root", "dataTableResponse", "noCache", "true" }),
    @Result(name = "validate-result", type = "json", params = { "root", "ajaxServiceResponse", "noCache", "true" }),
    @Result(name = SUCCESS, type = "redirect", location = "entry-prenota-ricerca-report-flussi-rendicontazione-base.do", 
                 params = { "init", "false", "tipoFormato", "${tipoFormato}"} ),
    
    //@formatter:on
})
public class RicercaFlussiRendicontazioniAction extends BaseRicercaFlussiRendicontazioniAction {//EpaypawebBaseAction {
    static private final long serialVersionUID = 1L;
    static private final String CLASSNAME = RicercaFlussiRendicontazioniAction.class.getSimpleName();

    // parametri tabella
    private Integer draw;

    private DataTableResponse<FlussoLightDto> dataTableResponse;
    private AjaxServiceResponse ajaxServiceResponse;

    @EJB
    private GestioneFlussiBusiness gestioneFlussiBusiness;

    @Action("entry-ricerca-flussi-rendicontazione-base")
    @Authorization(cdu = "RIC_FR")
    public String entryRicercaFlussiRendicontazioni() {
        String methodName = "entryRicercaFlussiRendicontazioni";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        ActionScope actionScope = getSessionContext().getActionScope();
        if (isInit()) {
            actionScope.reset();

        } else {
            dataRicezioneInizio = actionScope.getValue("dataRicezioneInizio");
            dataRicezioneFine = actionScope.getValue("dataRicezioneFine");
            codiceDescrizionePSP = actionScope.getValue("codiceDescrizionePSP");
            executeSearch = Boolean.TRUE;
        }

        log.info ( CLASSNAME + " " + methodName + " - STOP" );
        return INPUT;
    }

    @Action("valida-filtro-ricerca-flussi-rendicontazioni-json")
    public String validaFiltroRicercaFlussiRendicontazione() {
        String methodName = "validaFiltroRicercaFlussiRendicontazione";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        ajaxServiceResponse = new AjaxServiceResponse();
        ajaxServiceResponse.setResultCode("OK");
        ajaxServiceResponse.setMessage("");

        if (dataRicezioneInizio.isEmpty() && dataRicezioneFine.isEmpty() && (codiceDescrizionePSP.isEmpty())) {
            ajaxServiceResponse.setResultCode("KO");
            ajaxServiceResponse.setMessage(getText("message.valorizzare.almeno.un.filtro"));

        } else {
            if (!dataRicezioneInizio.isEmpty() && !dataRicezioneFine.isEmpty()) {
                try {
                    if (sdf.parse(dataRicezioneInizio).after(sdf.parse(dataRicezioneFine))) {
                        ajaxServiceResponse.setResultCode("KO");
                        ajaxServiceResponse.setMessage(getText("message.verificare.precedenza.date"));
                    }
                } catch (ParseException e) {
                    ajaxServiceResponse.setResultCode("KO");
                    ajaxServiceResponse.setMessage(getText("message.error.formato.data"));
                }
            }
        }

        log.info ( CLASSNAME + " " + methodName + " - STOP" );
        return "validate-result";
    }

    @Action("ricerca-flussi-rendicontazioni-json")
    @Authorization(cdu = "RIC_FR")
    public String ricercaFlussiRendicontazioniJSON() throws Exception {
        String methodName = "ricercaFlussiRendicontazioneJSON";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        FlussoLightFilterDto filter = new FlussoLightFilterDto();

        // filtri impostati di default
        filter.setTipoFlusso(TipoFlussoEnum.TRASMETTI_FLUSSO_RENDICONTAZIONE);
        filter.setIdProfilo(getSessionContext().getProfilo().getId());
        filter.setIdEnte(getSessionContext().getEnte().getId());

        if (!dataRicezioneInizio.isEmpty()) {
            filter.setDataInserimentoDa(sdf.parse(dataRicezioneInizio));
        }
        if (!dataRicezioneFine.isEmpty()) {
            filter.setDataInserimentoA(sdf.parse(dataRicezioneFine));
        }
        if (!codiceDescrizionePSP.isEmpty()) {
        	filter.setCodiceDenominazioneMittente(codiceDescrizionePSP);
        }

        List<CriterioOrdinamentoDto<ColumnNameFlussoEnum>> sorting = null;
        if (sortingCol != null && sortingDir != null) {
            sorting = new ArrayList<CriterioOrdinamentoDto<ColumnNameFlussoEnum>>();
            CriterioOrdinamentoDto<ColumnNameFlussoEnum> sortingItem = new CriterioOrdinamentoDto<ColumnNameFlussoEnum>();

            if ("idMessaggio".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameFlussoEnum.ID_MESSAGGIO);
            else if ("dataInserimento".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameFlussoEnum.DATA_INSERIMENTO);
            else if ("dataRegolamento".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameFlussoEnum.DATA_REGOLAMENTO);
            else if ("idAndDesTipoMittente".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameFlussoEnum.TIPO_ID_MITTENTE);
            else if ("codIdUnivocoMittente".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameFlussoEnum.COD_ID_UNIVOCO_MITTENTE);
            else if ("denominazioneMittente".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameFlussoEnum.DENOMINAZIONE_MITTENTE);
            else if ("numeroElementi".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameFlussoEnum.NUMERO_ELEMENTI);
            else if ("importoTotale".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameFlussoEnum.IMPORTO_TOTALE);

            sortingItem.setSortTypeEnum("asc".equals(sortingDir) ? SortTypeEnum.ASC : SortTypeEnum.DESC);
            sorting.add(sortingItem);
        }

        PaginazioneDto pag = new PaginazioneDto((start / length) + 1, length);

        try {
        	TotalSizeAndLightListDto<FlussoLightDto> totalSizeAndLightList;
            totalSizeAndLightList = gestioneFlussiBusiness.getTotalSizeAndFlussoLightList ( filter, sorting, pag, getSessionContext ().getUtente ().getCod (),
                getSessionContext ().getCodiciVersamento () );
            
            saveDataIntoScopeAndSession();

            dataTableResponse = new DataTableResponse<FlussoLightDto>();
            dataTableResponse.setRecordsTotal(totalSizeAndLightList.getTotalSize().intValue());
            dataTableResponse.setRecordsFiltered(totalSizeAndLightList.getTotalSize().intValue());
            dataTableResponse.setDraw(draw);
            dataTableResponse.setData(totalSizeAndLightList.getLightList());

            log.debug("lightList:" + totalSizeAndLightList.getLightList());

        } catch (BusinessException e) {
            log.error("Errore imprevisto", e);
            dataTableResponse.setError(e.getMessage());
        }

        log.info ( CLASSNAME + " " + methodName + " - STOP" );
        return "json-table-data";
    }
    
    @Action("valida-prenota-ricerca-report-flussi-rendicontazione-base")
    @Authorization(cdu = "RIC_FR")
    public String validaPrenotaRicercaReportFlussi() throws Exception {
    	String methodName = "validaPrenotaRicercaReportFlussi";
    	
    	log.info ( CLASSNAME + " " + methodName + " - START" );

    	ActionScope actionScope = getSessionContext().getActionScope();
//    	Map<String, Object> session = ActionContext.getContext ().getSession ();
    	
    	FlussoLightFilterDto filter = new FlussoLightFilterDto();

    	// filtri impostati di default
    	filter.setTipoFlusso(TipoFlussoEnum.TRASMETTI_FLUSSO_RENDICONTAZIONE);
    	filter.setIdProfilo(getSessionContext().getProfilo().getId());
    	filter.setIdEnte(getSessionContext().getEnte().getId());
    	dataRicezioneInizio = actionScope.getValue("dataRicezioneInizio");
    	dataRicezioneFine = actionScope.getValue("dataRicezioneFine");
    	codiceDescrizionePSP = actionScope.getValue("codiceDescrizionePSP");
    	if (null != dataRicezioneInizio && !dataRicezioneInizio.isEmpty()) {
    		filter.setDataInserimentoDa(sdf.parse(dataRicezioneInizio));
    	}
    	if (null != dataRicezioneFine && !dataRicezioneFine.isEmpty()) {
    		filter.setDataInserimentoA(sdf.parse(dataRicezioneFine));
    	}
    	if (null != codiceDescrizionePSP && !codiceDescrizionePSP.isEmpty()) {
    		filter.setCodiceDenominazioneMittente(codiceDescrizionePSP);
    	}
    	try {

    		if (!gestioneFlussiBusiness.isNumeroFlussiElaborabile(filter, getSessionContext ().getUtente ().getCod () ))
    		{
    			executeSearch = Boolean.FALSE;
    			esitoElaborazione = "warning";
    			messaggioEsitoElaborazione = getText("message.num.report.non.elaborabile.inizio")
    					+gestioneFlussiBusiness.getCountNumMaxFlussiReport()+getText("message.num.report.non.elaborabile.fine");

    			log.info ( CLASSNAME + " " + methodName + " - STOP" );
    			return INPUT;
    		}
    	}	
    	catch (BusinessException e) {
    		log.error("Errore imprevisto", e);
    		dataTableResponse.setError(e.getMessage());
    	}

    	log.info ( CLASSNAME + " " + methodName + " - STOP" );
    	return SUCCESS;	
    }

    public DataTableResponse<FlussoLightDto> getDataTableResponse() {
        return dataTableResponse;
    }

    public void setDataTableResponse(DataTableResponse<FlussoLightDto> dataTableResponse) {
        this.dataTableResponse = dataTableResponse;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public AjaxServiceResponse getAjaxServiceResponse() {
        return ajaxServiceResponse;
    }

}
