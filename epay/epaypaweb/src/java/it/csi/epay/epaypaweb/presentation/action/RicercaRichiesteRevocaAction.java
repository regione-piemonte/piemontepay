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
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.RichiestaRevocheFilterDto;
import it.csi.epay.epaypaweb.dto.TotalSizeAndLightListDto;
import it.csi.epay.epaypaweb.dto.common.TotalSizeAndFlussoRevocheListRequestDto;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;
import it.csi.epay.epaypaweb.presentation.dto.ActionScope;
import it.csi.epay.epaypaweb.presentation.dto.AjaxServiceResponse;
import it.csi.epay.epaypaweb.presentation.dto.DataTableResponse;


@Namespace("/")
@InterceptorRef("epaypawebStack")
@Results({
    //@formatter:off
    @Result(name = INPUT, location = "pages/ricerca-richieste-revoca.jsp"),
    @Result(name = "json-table-data", type = "json", params = { "root", "dataTableResponse", "noCache", "true" }),
    @Result(name = "validate-result", type = "json", params = { "root", "ajaxServiceResponse", "noCache", "true" })
    //@formatter:on
})
public class RicercaRichiesteRevocaAction extends EpaypawebBaseAction implements Preparable{
    static private final long serialVersionUID = 1L;
    static private final String CLASSNAME = RicercaRichiesteRevocaAction.class.getSimpleName();

    private String denominazioneIstituto;
    private String tipoRevoca;
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

    private DataTableResponse<RichiestaRevocheFilterDto> dataTableResponse;
    private AjaxServiceResponse ajaxServiceResponse;

    @EJB
    private GestioneFlussiBusiness gestioneFlussiBusiness;

    @Override
    public void prepare() {
        String methodName = "prepare";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        tipoPagamentoList = new ArrayList<StrutsOptionsSupport>();
        tipoPagamentoList.add(new StrutsOptionsSupport(1, getText("label.tipo.pagamenti.tutti")));
        tipoPagamentoList.add(new StrutsOptionsSupport(2, getText("label.tipo.pagamenti.pagamenti.spontanei")));
        tipoPagamentoList.add(new StrutsOptionsSupport(3, getText("label.tipo.pagamenti.pagamenti.dovuti")));

        log.info ( CLASSNAME + " " + methodName + " - STOP" );
    }

    @Action("entry-ricerca-richieste-revoca")
    @Authorization(cdu = "RIC_ESP")
    public String entryRicercaRichiesteRevoca() {
        String methodName = "entryRicercaRichiesteRevoca";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        ActionScope actionScope = getSessionContext().getActionScope();
        if (isInit()) {
          //  denominazioneIstituto = ;
            actionScope.reset();

        } else {
            
            tipoRevoca = actionScope.getValue("tipoRevoca");
            dataRicezioneInizio = actionScope.getValue("dataRicezioneInizio");
            dataRicezioneFine = actionScope.getValue("dataRicezioneFine");
            denominazioneIstituto = actionScope.getValue("denominazioneIstituto");
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
    
    @Action("valida-filtro-ricerca-richieste-revoca-json")
    public String validaFiltroRicercaRichiesteRevocaJSON() {
        String methodName = "validaFiltroRicercaFlussiPagamentiJSON";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        ajaxServiceResponse = new AjaxServiceResponse();
        ajaxServiceResponse.setResultCode("OK");
        ajaxServiceResponse.setMessage("");

        //--------------------------------//
        //RDI-048 - START - STOP
        //--------------------------------//

        if (denominazioneIstituto.isEmpty () &&/*tipoRevoca == null && */dataRicezioneInizio.isEmpty() && dataRicezioneFine.isEmpty() && cognome.isEmpty() && iuv.isEmpty ()) {
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
    
    
    @Action("ricerca-revoca-pagamenti-json")
    @Authorization(cdu = "RIC_ESP")
    public String ricercaFlussiPagamentiJSON() throws Exception {
        String methodName = "ricercaFlussiPagamentiJSON";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        RichiestaRevocheFilterDto filter = new RichiestaRevocheFilterDto();


        filter.setDenominazioneIstitutoAttestante ( denominazioneIstituto );
        filter.setIuv ( iuv );
        filter.setTipoRevoca ( 0 );
        if (!dataRicezioneInizio.isEmpty()) {
            filter.setDataOraMessaggioRevocaDa(sdf.parse(dataRicezioneInizio));
        }
        if (!dataRicezioneFine.isEmpty()) {
            filter.setDataOraMessaggioRevocaAl (sdf.parse(dataRicezioneFine));
        }
        filter.setIdentificativoDominio(cognome);





        PaginazioneDto pag = new PaginazioneDto((start / length) + 1, length);

        try {
            TotalSizeAndLightListDto<RichiestaRevocheFilterDto> totalSizeAndLightList;
          	totalSizeAndLightList = gestioneFlussiBusiness.getTotalSizeAndFlussoRevocheList (new TotalSizeAndFlussoRevocheListRequestDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),filter,pag,getSessionContext().getCodiciVersamento()));
            /*
            totalSizeAndLightList = gestioneFlussiBusiness.getTotalSizeAndFlussoRevocheList ( filter, pag,
                getSessionContext ().getUtente ().getCod (), getSessionContext ().getCodiciVersamento () );
			*/

            ActionScope actionScope = getSessionContext().getActionScope();
            
            actionScope.putValue("tipoRevoca", tipoRevoca);
            actionScope.putValue("dataRicezioneInizio", dataRicezioneInizio);
            actionScope.putValue("dataRicezioneFine", dataRicezioneFine);
            actionScope.putValue("denominazioneIstituto", denominazioneIstituto);
            //--------------------------------//
            //RDI-048 - START
            //--------------------------------//
            actionScope.putValue("cognome", cognome);
            actionScope.putValue("iuv",     iuv);
            //--------------------------------//
            //RDI-048 - START
            //--------------------------------//

            Long count = totalSizeAndLightList.getTotalSize();

            dataTableResponse = new DataTableResponse<RichiestaRevocheFilterDto>();
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

    public String getDenominazioneIstituto() {
        return denominazioneIstituto;
    }

    public void setDenominazioneIstituto(String denominazioneIstituto) {
        this.denominazioneIstituto = denominazioneIstituto;
    }

    public String getTipoRevoca() {
        return tipoRevoca;
    }

    public void setTipoRevoca(String tipoRevoca) {
        this.tipoRevoca = tipoRevoca;
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

    public DataTableResponse<RichiestaRevocheFilterDto> getDataTableResponse() {
        return dataTableResponse;
    }

    public void setDataTableResponse(DataTableResponse<RichiestaRevocheFilterDto> dataTableResponse) {
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
