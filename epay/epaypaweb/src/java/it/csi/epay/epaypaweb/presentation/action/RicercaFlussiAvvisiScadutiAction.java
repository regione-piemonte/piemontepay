/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import static com.opensymphony.xwork2.Action.INPUT;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

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
    @Result(name = INPUT, location = "pages/ricerca-flussi-avvisiscaduti.jsp"),
    @Result(name = "json-table-data", type = "json", params = { "root", "dataTableResponse", "noCache", "true" }),
    @Result(name = "validate-result", type = "json", params = { "root", "ajaxServiceResponse", "noCache", "true" })
    //@formatter:on
})
public class RicercaFlussiAvvisiScadutiAction extends EpaypawebBaseAction {
    static private final long serialVersionUID = 1L;
    static private final String CLASSNAME = RicercaFlussiAvvisiScadutiAction.class.getSimpleName();

    private Integer idCodVersamento;
    private String dataRicezioneInizio;
    private String dataRicezioneFine;
    private Boolean executeSearch;

    // parametri tabella
    private Integer draw;
    private Integer start;
    private Integer length;
    private String sortingDir;
    private String sortingCol;

    private DataTableResponse<FlussoLightDto> dataTableResponse;
    private AjaxServiceResponse ajaxServiceResponse;

    @EJB
    private GestioneFlussiBusiness gestioneFlussiBusiness;

    @Action("entry-ricerca-flussi-avvisiscaduti")
    @Authorization(cdu = "RIC_AS")
    public String entryRicercaFlussiAvvisiScaduti() {
        String methodName = "entryRicercaFlussiAvvisiScaduti";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        ActionScope actionScope = getSessionContext().getActionScope();
        if (isInit()) {
            actionScope.reset();

        } else {
            idCodVersamento = actionScope.getValue("idCodVersamento");
            dataRicezioneInizio = actionScope.getValue("dataRicezioneInizio");
            dataRicezioneFine = actionScope.getValue("dataRicezioneFine");
            executeSearch = Boolean.TRUE;
        }

        log.info ( CLASSNAME + " " + methodName + " - STOP" );
        return INPUT;
    }

    @Action("valida-filtro-ricerca-flussi-avvisiscaduti-json")
    public String validaFiltroRicercaFlussiAvvisiScadutiJSON() {
        String methodName = "validaFiltroRicercaFlussiAvvisiScadutiJSON";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        ajaxServiceResponse = new AjaxServiceResponse();
        ajaxServiceResponse.setResultCode("OK");
        ajaxServiceResponse.setMessage("");

        if (idCodVersamento == null && dataRicezioneInizio.isEmpty() && dataRicezioneFine.isEmpty()) {
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

    @Action("ricerca-flussi-avvisiscaduti-json")
    @Authorization(cdu = "RIC_AS")
    public String ricercaFlussiAvvisiScadutiJSON() throws Exception {
        String methodName = "ricercaFlussiAvvisiScadutiJSON";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        FlussoLightFilterDto filter = new FlussoLightFilterDto();

        // filtri impostati di default
        filter.setTipoFlusso(TipoFlussoEnum.AVVISI_SCADUTI);
        filter.setIdProfilo(getSessionContext().getProfilo().getId());
        filter.setIdEnte(getSessionContext().getEnte().getId());

        if (idCodVersamento != null) {
            List<Integer> idCodVersamentoListOneElement = new ArrayList<Integer>();
            idCodVersamentoListOneElement.add(idCodVersamento);
            filter.setIdCodVersamentoList(idCodVersamentoListOneElement);
        }

        if (!dataRicezioneInizio.isEmpty()) {
            filter.setDataInserimentoDa(sdf.parse(dataRicezioneInizio));
        }
        if (!dataRicezioneFine.isEmpty()) {
            filter.setDataInserimentoA(sdf.parse(dataRicezioneFine));
        }

        List<CriterioOrdinamentoDto<ColumnNameFlussoEnum>> sorting = null;
        if (sortingCol != null && sortingDir != null) {
            sorting = new ArrayList<CriterioOrdinamentoDto<ColumnNameFlussoEnum>>();
            CriterioOrdinamentoDto<ColumnNameFlussoEnum> sortingItem = new CriterioOrdinamentoDto<ColumnNameFlussoEnum>();

            if ("idMessaggio".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameFlussoEnum.ID_MESSAGGIO);
            else if ("codVersamento".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameFlussoEnum.ID_CODICE_VERSAMENTO);
            else if ("numeroElementi".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameFlussoEnum.NUMERO_ELEMENTI);
            else if ("importoTotale".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameFlussoEnum.IMPORTO_TOTALE);
            else if ("dataInserimento".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameFlussoEnum.DATA_INSERIMENTO);

            sortingItem.setSortTypeEnum("asc".equals(sortingDir) ? SortTypeEnum.ASC : SortTypeEnum.DESC);
            sorting.add(sortingItem);
        }

        PaginazioneDto pag = new PaginazioneDto((start / length) + 1, length);

        try {
        	TotalSizeAndLightListDto<FlussoLightDto> totalSizeAndLightList;
            totalSizeAndLightList = gestioneFlussiBusiness.getTotalSizeAndFlussoLightList ( filter, sorting, pag, getSessionContext ().getUtente ().getCod (),
                getSessionContext ().getCodiciVersamento () );
            
            ActionScope actionScope = getSessionContext().getActionScope();
            actionScope.putValue("idCodVersamento", idCodVersamento);
            actionScope.putValue("dataRicezioneInizio", dataRicezioneInizio);
            actionScope.putValue("dataRicezioneFine", dataRicezioneFine);

            Long count = totalSizeAndLightList.getTotalSize();

            dataTableResponse = new DataTableResponse<FlussoLightDto>();
            dataTableResponse.setRecordsTotal(count.intValue());
            dataTableResponse.setRecordsFiltered(count.intValue());
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

    public Integer getIdCodVersamento() {
        return idCodVersamento;
    }

    public void setIdCodVersamento(Integer idCodVersamento) {
        this.idCodVersamento = idCodVersamento;
    }

    public String getDataRicezioneInizio() {
        return dataRicezioneInizio;
    }

    public void setDataRicezioneInizio(String dataRicezioneInizio) {
        this.dataRicezioneInizio = dataRicezioneInizio;
    }

    public String getDataRicezioneFine() {
        return dataRicezioneFine;
    }

    public void setDataRicezioneFine(String dataRicezioneFine) {
        this.dataRicezioneFine = dataRicezioneFine;
    }

    public Boolean getExecuteSearch() {
        return executeSearch;
    }

    public void setExecuteSearch(Boolean executeSearch) {
        this.executeSearch = executeSearch;
    }

    public DataTableResponse<FlussoLightDto> getDataTableResponse() {
        return dataTableResponse;
    }

    public void setDataTableResponse(DataTableResponse<FlussoLightDto> dataTableResponse) {
        this.dataTableResponse = dataTableResponse;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public String getSortingDir() {
        return sortingDir;
    }

    public void setSortingDir(String sortingDir) {
        this.sortingDir = sortingDir;
    }

    public String getSortingCol() {
        return sortingCol;
    }

    public void setSortingCol(String sortingCol) {
        this.sortingCol = sortingCol;
    }

    public AjaxServiceResponse getAjaxServiceResponse() {
        return ajaxServiceResponse;
    }

}
