/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import com.opensymphony.xwork2.Preparable;
import it.csi.epay.epaypaweb.business.interf.ReportEntiBusiness;
import it.csi.epay.epaypaweb.dto.*;
import it.csi.epay.epaypaweb.dto.common.RicercaReportEntiDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNamePagamentiEnum;
import it.csi.epay.epaypaweb.enumeration.StatoPagamentoEnum;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;
import it.csi.epay.epaypaweb.presentation.dto.ActionScope;
import it.csi.epay.epaypaweb.presentation.dto.AjaxServiceResponse;
import it.csi.epay.epaypaweb.presentation.dto.DataTableResponse;
import org.apache.struts2.convention.annotation.*;

import javax.ejb.EJB;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.opensymphony.xwork2.Action.INPUT;


@Namespace ( "/" )
@InterceptorRef ( "epaypawebStack" )
@Results ( {
    //@formatter:off
    @Result ( name = INPUT, location = "pages/ricerca-report-enti.jsp" ),
    @Result ( name = "json-table-data", type = "json", params = { "root", "dataTableResponse", "noCache", "true" } ),
    @Result ( name = "validate-result", type = "json", params = { "root", "ajaxServiceResponse", "noCache", "true" } ),
    //@formatter:on
} )
public class RicercaReportEntiAction extends BaseRicercaReportEntiAction implements Preparable {

    private static final long serialVersionUID = 1L;

    private static final String CLASSNAME = RicercaReportEntiAction.class.getSimpleName ();

    private List<StrutsOptionsSupport> tipoPagamentoList;

    private List<StrutsOptionsSupport> tipoFileReportList;
    
    private List<StrutsOptionsSupport> tipoCostiNotificaList;
    
    protected List<StatoPagamentoDto> statoPagamentoList;

    private Integer draw;

    private DataTableResponse<PagamentiDto> dataTableResponse;

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
        
        statoPagamentoList = new ArrayList<> ();
        statoPagamentoList.add ( new StatoPagamentoDto ( StatoPagamentoEnum.CODICE_4.getId (),  StatoPagamentoEnum.CODICE_4.getDes () ) );
        statoPagamentoList.add ( new StatoPagamentoDto ( StatoPagamentoEnum.CODICE_0.getId (),  StatoPagamentoEnum.CODICE_0.getDes () ) );
        
        tipoCostiNotificaList = new ArrayList<> ();
        tipoCostiNotificaList.add ( new StrutsOptionsSupport ( COSTI_NOTIFICA_TUTTI, getText ( "label.tipo.notifica.tutti" ) ) );
        tipoCostiNotificaList.add ( new StrutsOptionsSupport ( COSTI_NOTIFICA_PRESENTI, getText ( "label.tipo.notifica.presenti" ) ) );
        tipoCostiNotificaList.add ( new StrutsOptionsSupport ( COSTI_NOTIFICA_NON_PRESENTI, getText ( "label.tipo.notifica.nonPresenti" ) ) );
        log.info ( CLASSNAME + " " + methodName + " - STOP" );
    }

    @Action ( "entry-ricerca-report-enti" )
    @Authorization ( cdu = "RIC_ENTI" )
    public String entryRicercaReportEnti () {
        String methodName = "entryRicercaReportEnti";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        ActionScope actionScope = getSessionContext ().getActionScope ();
        if ( isInit () ) {
        	idTipoCostiNotifica = COSTI_NOTIFICA_TUTTI;
            idTipoPagamento = TIPO_PAGAMENTO_TUTTI;
            actionScope.reset ();

        } else {
            importFilterFromScope ();

            executeSearch = Boolean.TRUE;
        }

        log.info ( CLASSNAME + " " + methodName + " - STOP" );
        return INPUT;
    }

    @Action ( "valida-filtro-ricerca-report-enti-json" )
    public String validaFiltroRicercaReportEntiJSON () {
        String methodName = "validaFiltroRicercaReportEntiJSON";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        ajaxServiceResponse = new AjaxServiceResponse ();
        ajaxServiceResponse.setResultCode ( "OK" );
        ajaxServiceResponse.setMessage ( "" );

        if ( idTipoPagamento == TIPO_PAGAMENTO_TUTTI && idCodVersamento == null &&
                        dataPagamentoInizio.isEmpty () &&
                        dataPagamentoFine.isEmpty () &&
                        dataPagamentoScadenzaInizio.isEmpty() &&
                        dataPagamentoScadenzaFine.isEmpty() &&
                        cognome.isEmpty () &&
                        iuv.isEmpty () &&
                        codiceFiscale.isEmpty ()
                        &&  idStatoPagamento == null && idTipoCostiNotifica == COSTI_NOTIFICA_TUTTI
                        ) {
            ajaxServiceResponse.setResultCode ( "KO" );
            ajaxServiceResponse.setMessage ( getText ( "message.valorizzare.almeno.un.filtro" ) );

        } else {
            if ( !dataPagamentoInizio.isEmpty () && !dataPagamentoFine.isEmpty ()) {
                try {
                    if ( sdf.parse ( dataPagamentoInizio ).after ( sdf.parse ( dataPagamentoFine ) )) {
                        ajaxServiceResponse.setResultCode ( "KO" );
                        ajaxServiceResponse.setMessage ( getText ( "message.verificare.precedenza.date" ) );
                    }
                } catch ( ParseException e ) {
                    ajaxServiceResponse.setResultCode ( "KO" );
                    ajaxServiceResponse.setMessage ( getText ( "message.error.formato.data" ) );
                }
            } else if(!dataPagamentoScadenzaInizio.isEmpty () && !dataPagamentoScadenzaFine.isEmpty ()) {
                try {
                    if (sdf.parse ( dataPagamentoScadenzaInizio ).after ( sdf.parse ( dataPagamentoScadenzaFine ) )) {
                        ajaxServiceResponse.setResultCode ( "KO" );
                        ajaxServiceResponse.setMessage ( getText ( "message.verificare.precedenza.date" ) );
                    }
                } catch ( ParseException e ) {
                    ajaxServiceResponse.setResultCode ( "KO" );
                    ajaxServiceResponse.setMessage ( getText ( "message.error.formato.data" ) );
                }
            }
        }

        log.info ( CLASSNAME + " " + methodName + " - STOP" );
        return "validate-result";
    }

    @Action ( "ricerca-report-enti-json" )
    @Authorization ( cdu = "RIC_ENTI" )
    public String ricercaReportEntiJSON () {
        String methodName = "ricercaReportEntiJSON";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        PagamentiFilterDto filter = getFilterFromBean ();

        PaginazioneDto pag = getPaginazioneFromBean ();
        List<CriterioOrdinamentoDto<ColumnNamePagamentiEnum>> sorting = getCriterioOrdinamentoFromBean ();

        try {
            TotalSizeAndLightListDto<PagamentiDto> totalSizeAndLightList;

            RicercaReportEntiDto ricercaReportEntiDto = new RicercaReportEntiDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),
					filter);

            
            totalSizeAndLightList = reportEntiBusiness.ricerca ( ricercaReportEntiDto, sorting, pag );

            saveFilterIntoScopeAndSession ();

            dataTableResponse = new DataTableResponse<> ();
            dataTableResponse.setRecordsTotal ( totalSizeAndLightList.getTotalSize ().intValue () );
            dataTableResponse.setRecordsFiltered ( totalSizeAndLightList.getTotalSize ().intValue () );
            dataTableResponse.setData ( totalSizeAndLightList.getLightList () );
            dataTableResponse.setDraw ( draw );

        } catch ( Exception e ) {
            log.error ( "Errore imprevisto", e );
            dataTableResponse.setError ( e.getMessage () );

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

    public DataTableResponse<PagamentiDto> getDataTableResponse () {
        return dataTableResponse;
    }

    public void setDataTableResponse ( DataTableResponse<PagamentiDto> dataTableResponse ) {
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

    
    public List<StatoPagamentoDto> getStatoPagamentoList () {
        return statoPagamentoList;
    }

    
    public void setStatoPagamentoList ( List<StatoPagamentoDto> statoPagamentoList ) {
        this.statoPagamentoList = statoPagamentoList;
    }

	public List<StrutsOptionsSupport> getTipoCostiNotificaList() {
		return tipoCostiNotificaList;
	}

	public void setTipoCostiNotificaList(List<StrutsOptionsSupport> tipoCostiNotificaList) {
		this.tipoCostiNotificaList = tipoCostiNotificaList;
	}
    
    

}
