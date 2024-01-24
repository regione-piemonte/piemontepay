/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import com.opensymphony.xwork2.Preparable;
import it.csi.epay.epaypaweb.business.interf.ReportEntiBusiness;
import it.csi.epay.epaypaweb.dto.*;
import it.csi.epay.epaypaweb.dto.common.RicercaPrenotazioneReportEntiDto;
import it.csi.epay.epaypaweb.dto.common.SalvaPrenotazioneReportEntiDto;
import it.csi.epay.epaypaweb.enumeration.TipoReportEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;
import it.csi.epay.epaypaweb.presentation.dto.ActionScope;
import it.csi.epay.epaypaweb.presentation.dto.AjaxServiceResponse;
import it.csi.epay.epaypaweb.presentation.dto.DataTableResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.*;

import javax.ejb.EJB;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.opensymphony.xwork2.Action.INPUT;


@Namespace ( "/" )
@InterceptorRef ( "epaypawebStack" )
@Results ( {
    //@formatter:off
    @Result ( name = INPUT, location = "pages/prenota-ricerca-report-enti.jsp" ),
    @Result ( name = "json-table-data", type = "json", params = { "root", "dataTableResponse", "noCache", "true" } ),
    @Result ( name = "validate-result", type = "json", params = { "root", "ajaxServiceResponse", "noCache", "true" } ),
    //@formatter:on
} )
public class PrenotaRicercaReportEntiAction extends BaseRicercaReportEntiAction implements Preparable {

    private static final long serialVersionUID = 1L;

    private static final String CLASSNAME = PrenotaRicercaReportEntiAction.class.getSimpleName ();

    private List<StrutsOptionsSupport> tipoPagamentoList;

    private List<StrutsOptionsSupport> tipoFileReportList;

    private List<StrutsOptionsSupport> tipoCostiNotificaList;

    private Integer draw;

    private DataTableResponse<ReportPagamentiDto> dataTableResponse;

    private AjaxServiceResponse ajaxServiceResponse;

    @EJB
    private ReportEntiBusiness reportEntiBusiness;

    @Override
    public void prepare () {
        String methodName = "prepare";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        tipoPagamentoList = new ArrayList<> ();
        tipoPagamentoList.add ( new StrutsOptionsSupport ( TIPO_PAGAMENTO_TUTTI, getText ( "label.tipo.pagamenti.tutti" ) ) );
        tipoPagamentoList.add ( new StrutsOptionsSupport ( TIPO_PAGAMENTO_SPONTANEI, getText ( "label.tipo.pagamenti.pagamenti.spontanei" ) ) );
        tipoPagamentoList.add ( new StrutsOptionsSupport ( TIPO_PAGAMENTO_DOVUTI, getText ( "label.tipo.pagamenti.pagamenti.dovuti" ) ) );

        tipoFileReportList = new ArrayList<> ();
        tipoFileReportList.add ( new StrutsOptionsSupport ( TIPO_FILE_REPORT_EXCEL, getText ( "label.tipo.file.report.excel" ) ) );
        tipoFileReportList.add ( new StrutsOptionsSupport ( TIPO_FILE_REPORT_CSV, getText ( "label.tipo.file.report.csv" ) ) );
        
        tipoCostiNotificaList = new ArrayList<> ();
        tipoCostiNotificaList.add ( new StrutsOptionsSupport ( COSTI_NOTIFICA_TUTTI, getText ( "label.tipo.notifica.tutti" ) ) );
        tipoCostiNotificaList.add ( new StrutsOptionsSupport ( COSTI_NOTIFICA_PRESENTI, getText ( "label.tipo.notifica.presenti" ) ) );
        tipoCostiNotificaList.add ( new StrutsOptionsSupport ( COSTI_NOTIFICA_NON_PRESENTI, getText ( "label.tipo.notifica.nonPresenti" ) ) );

        log.info ( CLASSNAME + " " + methodName + " - STOP" );
    }

    @Action ( "entry-prenota-ricerca-report-enti" )
    @Authorization ( cdu = "RIC_ENTI" )
    public String entryRicercaReportEnti() {
        String methodName = "entryRicercaReportEnti";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        ActionScope actionScope = getSessionContext().getActionScope();
        executeSearch = Boolean.TRUE;
        if ( isInit () ) {
            idTipoPagamento = TIPO_PAGAMENTO_TUTTI;
            idTipoCostiNotifica = COSTI_NOTIFICA_TUTTI;
            idFormatoFile = TIPO_FILE_REPORT_EXCEL;
            actionScope.reset();
        } else {
            importFilterFromSession ();
            if ( StringUtils.isEmpty (nomeReport) ) {
                nomeReport = UUID.randomUUID().toString();
            }
        }
        log.info ( CLASSNAME + " " + methodName + " - STOP" );
        return INPUT;
    }

    @Action ( "valida-filtro-prenota-ricerca-report-enti-json" )
    public String validaFiltroRicercaReportEntiJSON () {
        String methodName = "validaFiltroRicercaReportEntiJSON";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );
        boolean valido = true;
        ajaxServiceResponse = new AjaxServiceResponse ();
        ajaxServiceResponse.setResultCode ( "OK" );
        ajaxServiceResponse.setMessage ( "" );

        if ( idTipoPagamento == TIPO_PAGAMENTO_TUTTI && idCodVersamento == null &&
                        dataPagamentoInizio.isEmpty () &&
                        dataPagamentoFine.isEmpty () &&
                        cognome.isEmpty () &&
                        iuv.isEmpty () &&
                        codiceFiscale.isEmpty ()  && idTipoCostiNotifica == COSTI_NOTIFICA_TUTTI) {
            ajaxServiceResponse.setResultCode ( "KO" );
            ajaxServiceResponse.setMessage ( getText ( "message.valorizzare.almeno.un.filtro" ) );
            valido = false;
        } else {
            if ( StringUtils.isBlank ( nomeReport ) ) {
                ajaxServiceResponse.setResultCode ( "KO" );
                ajaxServiceResponse.setMessage ( getText ( "message.valorizzare.nome.report" ) );
                valido = false;
            } else if ( !dataPagamentoInizio.isEmpty () && !dataPagamentoFine.isEmpty () ) {
                try {
                    if ( sdf.parse ( dataPagamentoInizio ).after ( sdf.parse ( dataPagamentoFine ) ) ) {
                        ajaxServiceResponse.setResultCode ( "KO" );
                        ajaxServiceResponse.setMessage ( getText ( "message.verificare.precedenza.date" ) );
                        valido = false;
                    }
                } catch ( ParseException e ) {
                    ajaxServiceResponse.setResultCode ( "KO" );
                    ajaxServiceResponse.setMessage ( getText ( "message.error.formato.data" ) );
                    valido = false;
                }
            }
        }
        if ( valido ) {
            PagamentiFilterDto filter = getFilterFromBean ();
            try {
                // creazione DTO report
            	
            	 
            	ReportPagamentiDto reportPagamenti= ReportPagamentiDto.builder ()
                .withCodiceFiscale ( getCodiceFiscale () )
                .withCodiceFiscaleEnte ( filter.getProfilazioneUtente ().getEnte ().getCodFiscale () )
                .withCodiceFiscaleUtente ( filter.getProfilazioneUtente ().getUtente ().getCod () ) // verificare
                .withCognome ( filter.getCognome () )
                .withDataPagamentoFine ( filter.getDataPagamentoFine () )
                .withDataPagamentoInizio ( filter.getDataPagamentoInizio () )
                    .withDataScadenzaPagamentoInizio ( filter.getDataPagamentoScadenzaInizio () )
                    .withDataScadenzaPagamentoFine ( filter.getDataPagamentoScadenzaFine () )
                .withIdCodiceVersamento ( filter.getIdCodiceVersamento () )
                .withIdEnte ( filter.getProfilazioneUtente ().getEnte ().getId () )
                .withIdUtente ( filter.getProfilazioneUtente ().getUtente ().getId () )
                .withIuv ( filter.getIuv () )
                .withNominativoExport ( getNomeReport () )
                .withStatoReport ( StatoReportDto.builder ().withId ( 1 ).build () )
                    .withTipoFileReport ( TipoFileReportDto.builder ().withId ( filter.getIdFormatoFile () ).build () )
                .withTipoPagamento ( getIdTipoPagamento () )
                .withTipoReportDto( TipoReportDto.builder().withCodice( TipoReportEnum.PAGAMENTI.getCode() ).build() )
                .withCostiNotifica(getIdTipoCostiNotifica())
                .build ();
            	
            	SalvaPrenotazioneReportEntiDto salvaPrenotazioneReportEntiDto = new SalvaPrenotazioneReportEntiDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),
            			reportPagamenti);
            	
            	
            	
                reportEntiBusiness.salvaPrenotazioneReport ( salvaPrenotazioneReportEntiDto );
            } catch ( BusinessException e ) {
                log.error ( "Errore imprevisto", e );
                ajaxServiceResponse.setResultCode ( "KO" );
                ajaxServiceResponse
                .setMessage ( getText ( "message.error.salva.prenotazione.report", new String [] { e.getClass ().getName (), e.getMessage () } ) );
            }
        }
        log.info ( CLASSNAME + " " + methodName + " - STOP" );
        return "validate-result";
    }

    @Action ( "prenota-ricerca-report-enti-json" )
    @Authorization ( cdu = "RIC_ENTI" )
    public String ricercaReportEntiJSON () throws Exception {
        String methodName = "ricercaReportEntiJSON";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );
        PagamentiFilterDto filter = getFilterFromBean ();
        ajaxServiceResponse = new AjaxServiceResponse ();
        ajaxServiceResponse.setResultCode ( "OK" );
        ajaxServiceResponse.setMessage ( "" );
        if(null == start) {
            setStart ( 0 );
        }
        if(null == length) {
            setLength ( 5 );
        }

        PaginazioneDto pag = getPaginazioneFromBean ();

        try {
            TotalSizeAndLightListDto<ReportPagamentiDto> totalSizeAndLightList;
            
            		
            		 RicercaPrenotazioneReportEntiDto ricercaPrenotazioneReportEntiDto = 
            		 new RicercaPrenotazioneReportEntiDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),
            				 filter.getProfilazioneUtente ().getEnte ().getId (), pag, TipoReportEnum.PAGAMENTI );

            totalSizeAndLightList = reportEntiBusiness.ricercaReport ( ricercaPrenotazioneReportEntiDto);

            saveFilterIntoScopeAndSession ();

            dataTableResponse = new DataTableResponse<> ();
            dataTableResponse.setRecordsTotal ( totalSizeAndLightList.getTotalSize ().intValue () );
            dataTableResponse.setRecordsFiltered ( totalSizeAndLightList.getTotalSize ().intValue () );
            dataTableResponse.setData ( totalSizeAndLightList.getLightList () );
            dataTableResponse.setDraw ( draw );

        } catch ( Exception e ) {
            log.error ( "Errore imprevisto", e );
            dataTableResponse.setError ( e.getMessage () );
            ajaxServiceResponse.setResultCode ( "KO" );
            ajaxServiceResponse.setMessage ( getText ( "message.error.ricerca.report.prenotati", new String [] { e.getClass ().getName (), e.getMessage () } ) );
        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return "json-table-data";
    }

    @Override
    public Boolean getExecuteSearch () {
        return executeSearch;
    }

    @Override
    public void setExecuteSearch ( Boolean executeSearch ) {
        this.executeSearch = executeSearch;
    }

    public List<StrutsOptionsSupport> getTipoPagamentoList () {
        return tipoPagamentoList;
    }

    public void setTipoPagamentoList ( List<StrutsOptionsSupport> tipoPagamentoList ) {
        this.tipoPagamentoList = tipoPagamentoList;
    }

    public List<StrutsOptionsSupport> getTipoFileReportList () {
        return tipoFileReportList;
    }

    public void setTipoFileReportList ( List<StrutsOptionsSupport> tipoFileReportList ) {
        this.tipoFileReportList = tipoFileReportList;
    }

    public List<StrutsOptionsSupport> getTipoCostiNotificaList() {
		return tipoCostiNotificaList;
	}

	public void setTipoCostiNotificaList(List<StrutsOptionsSupport> tipoCostiNotificaList) {
		this.tipoCostiNotificaList = tipoCostiNotificaList;
	}

	public DataTableResponse<ReportPagamentiDto> getDataTableResponse () {
        return dataTableResponse;
    }

    public void setDataTableResponse ( DataTableResponse<ReportPagamentiDto> dataTableResponse ) {
        this.dataTableResponse = dataTableResponse;
    }

    public Integer getDraw () {
        return draw;
    }

    public void setDraw ( Integer draw ) {
        this.draw = draw;
    }

    public AjaxServiceResponse getAjaxServiceResponse () {
        return ajaxServiceResponse;
    }

}
