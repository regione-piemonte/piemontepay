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

import com.opensymphony.xwork2.Preparable;

import it.csi.epay.epaypaweb.business.interf.GestioneFlussiBusiness;
import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.FlussoLightDto;
import it.csi.epay.epaypaweb.dto.FlussoLightFilterDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.TotalSizeAndLightListDto;
import it.csi.epay.epaypaweb.dto.common.TotalSizeAndFlussoNotificheListRequestDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum;
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
    @Result(name = INPUT, location = "pages/ricerca-flussi-pagamenti.jsp"),
    @Result(name = "json-table-data", type = "json", params = { "root", "dataTableResponse", "noCache", "true" }),
    @Result(name = "validate-result", type = "json", params = { "root", "ajaxServiceResponse", "noCache", "true" })
    //@formatter:on
})
public class RicercaFlussiPagamentiAction extends EpaypawebBaseAction implements Preparable {

    private static final long serialVersionUID = 1L;

    private static final String CLASSNAME = RicercaFlussiPagamentiAction.class.getSimpleName ();

    private Integer idTipoPagamento;
    private Integer idCodVersamento;
    private String dataRicezioneInizio;
    private String dataRicezioneFine;
    //--------------------------------//
    //RDI-048 - START
    //--------------------------------//
    private String cognome;
    private String iuv;
    //--------------------------------//
    //RDI-048 - STOP
    //--------------------------------//
    private Boolean executeSearch;
    private List<StrutsOptionsSupport> tipoPagamentoList;

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

    @Override
    public void prepare() {
        String methodName = "prepare";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        tipoPagamentoList = new ArrayList<> ();
        tipoPagamentoList.add(new StrutsOptionsSupport(1, getText("label.tipo.pagamenti.tutti")));
        tipoPagamentoList.add(new StrutsOptionsSupport(2, getText("label.tipo.pagamenti.pagamenti.spontanei")));
        tipoPagamentoList.add(new StrutsOptionsSupport(3, getText("label.tipo.pagamenti.pagamenti.dovuti")));

        log.info ( CLASSNAME + " " + methodName + " - STOP" );
    }

    @Action("entry-ricerca-flussi-pagamenti")
    @Authorization(cdu = "RIC_ESP")
    public String entryRicercaFlussiPagamenti() {
        String methodName = "entryRicercaFlussiPagamenti";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        ActionScope actionScope = getSessionContext().getActionScope();
        if (isInit()) {
            idTipoPagamento = 1;
            actionScope.reset();

        } else {
            idTipoPagamento = actionScope.getValue("idTipoPagamento");
            idCodVersamento = actionScope.getValue("idCodVersamento");
            dataRicezioneInizio = actionScope.getValue("dataRicezioneInizio");
            dataRicezioneFine = actionScope.getValue("dataRicezioneFine");

            //--------------------------------//
            //RDI-048 - START
            //--------------------------------//
            cognome = actionScope.getValue("cognome");
            iuv = actionScope.getValue("iuv");
            //--------------------------------//
            //RDI-048 - STOP
            //--------------------------------//

            executeSearch = Boolean.TRUE;
        }

        log.info ( CLASSNAME + " " + methodName + " - STOP" );
        return INPUT;
    }

    @Action("valida-filtro-ricerca-flussi-pagamenti-json")
    public String validaFiltroRicercaFlussiPagamentiJSON() {
        String methodName = "validaFiltroRicercaFlussiPagamentiJSON";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        ajaxServiceResponse = new AjaxServiceResponse();
        ajaxServiceResponse.setResultCode("OK");
        ajaxServiceResponse.setMessage("");

        //--------------------------------//
        //RDI-048 - START - STOP
        //--------------------------------//
        if (idTipoPagamento == 1 && idCodVersamento == null && dataRicezioneInizio.isEmpty() && dataRicezioneFine.isEmpty() && cognome.isEmpty() && iuv.isEmpty ()) {
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

    @Action("ricerca-flussi-pagamenti-json")
    @Authorization(cdu = "RIC_ESP")
    public String ricercaFlussiPagamentiJSON() throws Exception {
        String methodName = "ricercaFlussiPagamentiJSON";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        FlussoLightFilterDto filter = new FlussoLightFilterDto();

        // filtri impostati di default
        filter.setTipoFlusso(TipoFlussoEnum.NOTIFICHE_PAGAMENTO);
        filter.setIdProfilo(getSessionContext().getProfilo().getId());
        filter.setIdEnte(getSessionContext().getEnte().getId());

        if (idCodVersamento != null) {
            List<Integer> idCodVersamentoListOneElement = new ArrayList<Integer>();
            idCodVersamentoListOneElement.add(idCodVersamento);
            filter.setIdCodVersamentoList(idCodVersamentoListOneElement);
        }
        switch (idTipoPagamento) {
        case 1:
            filter.setPagamentiSpontanei(null);
            break;
        case 2:
            filter.setPagamentiSpontanei(Boolean.TRUE);
            break;
        case 3:
            filter.setPagamentiSpontanei(Boolean.FALSE);
            break;
        }

        if (!dataRicezioneInizio.isEmpty()) {
            filter.setDataInserimentoDa(sdf.parse(dataRicezioneInizio));
        }
        if (!dataRicezioneFine.isEmpty()) {
            filter.setDataInserimentoA(sdf.parse(dataRicezioneFine));
        }

        //--------------------------------//
        //RDI-048 - START
        //--------------------------------//
        filter.setCognome(cognome);
        filter.setIuv(iuv);
        //--------------------------------//
        //RDI-048 - STOP
        //--------------------------------//
        List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> sorting = null;
        if (sortingCol != null && sortingDir != null) {
            sorting = new ArrayList<> ();
            CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum> sortingItem = new CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>();

            if ("posizioneDebitoria".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameNotificaPagamentoEnum.ID_POSIZIONE_DEBITORIA);
            else if ("iuv".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameNotificaPagamentoEnum.IUV);
            else if ("importoPagato".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameNotificaPagamentoEnum.IMPORTO_PAGATO);
            else if ("dataEsitoPagamento".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameNotificaPagamentoEnum.DATA_ESITO_PAGAMENTO);
            else if ("causaleVersamento".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameNotificaPagamentoEnum.DESCRIZIONE_CAUSALE_VERSAMENTO);
            else if ("cfPivaSoggettoDebitore".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameNotificaPagamentoEnum.ID_FISCALE_DEBITORE);
            else if ("cognome".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameNotificaPagamentoEnum.COGNOME_OR_RAGIONE_SOCIALE_DEBITORE);
            else if ("codVersamento".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameNotificaPagamentoEnum.COD_VERSAMENTO);
            else if ("dataInserimento".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameNotificaPagamentoEnum.DATA_INSERIMENTO);

            sortingItem.setSortTypeEnum("asc".equals(sortingDir) ? SortTypeEnum.ASC : SortTypeEnum.DESC);
            sorting.add(sortingItem);
        }

        PaginazioneDto pag = new PaginazioneDto((start / length) + 1, length);

        try {
            TotalSizeAndLightListDto<FlussoLightDto> totalSizeAndLightList;

            TotalSizeAndFlussoNotificheListRequestDto totalSizeAndFlussoNotificheListRequestDto = new TotalSizeAndFlussoNotificheListRequestDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),
                filter,sorting,pag,
                getSessionContext ().getCodiciVersamento ());

            totalSizeAndLightList = gestioneFlussiBusiness.getTotalSizeAndFlussoNotificheList ( totalSizeAndFlussoNotificheListRequestDto );

            /*
            totalSizeAndLightList = gestioneFlussiBusiness.getTotalSizeAndFlussoNotificheList ( filter, sorting, pag,
                    getSessionContext ().getUtente ().getCod (), getSessionContext ().getCodiciVersamento () );
             */

            ActionScope actionScope = getSessionContext().getActionScope();
            actionScope.putValue("idTipoPagamento", idTipoPagamento);
            actionScope.putValue("idCodVersamento", idCodVersamento);
            actionScope.putValue("dataRicezioneInizio", dataRicezioneInizio);
            actionScope.putValue("dataRicezioneFine", dataRicezioneFine);

            //--------------------------------//
            //RDI-048 - START
            //--------------------------------//
            actionScope.putValue("cognome", cognome);
            actionScope.putValue("iuv",     iuv);
            //--------------------------------//
            //RDI-048 - START
            //--------------------------------//

            Long count = totalSizeAndLightList.getTotalSize();

            dataTableResponse = new DataTableResponse<> ();
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

    public Integer getIdTipoPagamento() {
        return idTipoPagamento;
    }

    public void setIdTipoPagamento(Integer idTipoPagamento) {
        this.idTipoPagamento = idTipoPagamento;
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

    public List<StrutsOptionsSupport> getTipoPagamentoList() {
        return tipoPagamentoList;
    }

    public void setTipoPagamentoList(List<StrutsOptionsSupport> tipoPagamentoList) {
        this.tipoPagamentoList = tipoPagamentoList;
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

    //--------------------------------//
    //RDI-048 - START
    //--------------------------------//
    public String getCognome () {
        return cognome;
    }

    public void setCognome ( String cognome ) {
        this.cognome = cognome;
    }

    public String getIuv () {
        return iuv;
    }

    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }
    //--------------------------------//
    //RDI-048 - STOP
    //--------------------------------//

}
