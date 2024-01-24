/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import it.csi.epay.epaypaweb.business.interf.ReportEntiBusiness;
import it.csi.epay.epaypaweb.dto.*;
import it.csi.epay.epaypaweb.dto.common.SalvaPrenotazioneReportEntiDto;
import it.csi.epay.epaypaweb.enumeration.*;
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
    @Result ( name = INPUT, location = "pages/prenota-ricerca-report-flussi-base.jsp" ),
    @Result ( name = "json-table-data", type = "json", params = { "root", "dataTableResponse", "noCache", "true" } ),
    @Result ( name = "validate-result", type = "json", params = { "root", "ajaxServiceResponse", "noCache", "true" } ),
    //@formatter:on
} )
public class PrenotaRicercaFlussiRendicontazioniBaseAction extends BaseRicercaFlussiRendicontazioniAction {

    private static final long serialVersionUID = 1L;

    private static final String CLASSNAME = PrenotaRicercaFlussiRendicontazioniBaseAction.class.getSimpleName();

    private Integer draw;

    private DataTableResponse<ReportBaseDto> dataTableResponse;

    private AjaxServiceResponse ajaxServiceResponse;

    @EJB
    private ReportEntiBusiness reportEntiBusiness;

    @Action("entry-prenota-ricerca-report-flussi-rendicontazione-base")
    @Authorization(cdu = "RIC_FR")
    public String entryRicercaReportFlussi() throws Exception {
        String methodName = "entryRicercaReportFlussi";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        ActionScope actionScope = getSessionContext().getActionScope();
        executeSearch = Boolean.TRUE;

        if ( isInit () ) {
            actionScope.reset();
        } else {
            String tipoFormatoOld = tipoFormato;
            importDataFromSession();

            if ( !StringUtils.isEmpty (tipoFormatoOld) ) {
                tipoFormato = tipoFormatoOld;
            }

            if ( StringUtils.isEmpty (nomeReport) ) {
                nomeReport = UUID.randomUUID().toString();
            }
        }
        log.info ( CLASSNAME + " " + methodName + " - STOP" );
        return INPUT;
    }

    @Action ( "salva-prenotazione-report-flussi-rendicontazione-base-json" )
    public String salvaPrenotazioneReportFlussiBaseJSON () {

        String methodName = "salvaPrenotazioneReportFlussiBaseJSON";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );
        boolean valido = true;
        ajaxServiceResponse = new AjaxServiceResponse ();
        ajaxServiceResponse.setResultCode ( "OK" );
        ajaxServiceResponse.setMessage ( "" );
        if (!StringUtils.isEmpty(nomeReport))
        {
            nomeReport= nomeReport.trim();
        }


        if ( dataRicezioneInizio.isEmpty() && dataRicezioneFine.isEmpty() && ( codiceDescrizionePSP.isEmpty() ) ) {
            ajaxServiceResponse.setResultCode( "KO" );
            ajaxServiceResponse.setMessage( getText( "message.valorizzare.almeno.un.filtro" ) );
            valido = false;
        } else {
            if ( StringUtils.isBlank ( nomeReport ) ) {
                ajaxServiceResponse.setResultCode ( "KO" );
                ajaxServiceResponse.setMessage ( getText ( "message.valorizzare.nome.report" ) );
                valido = false;
            } else if ( !dataRicezioneInizio.isEmpty() && !dataRicezioneFine.isEmpty() ) {
                try {
                    if (sdf.parse( dataRicezioneInizio ).after( sdf.parse( dataRicezioneFine ) ) ) {
                        ajaxServiceResponse.setResultCode( "KO" );
                        ajaxServiceResponse.setMessage( getText( "message.verificare.precedenza.date" ) );
                        valido = false;
                    }
                } catch (ParseException e) {
                    ajaxServiceResponse.setResultCode( "KO" );
                    ajaxServiceResponse.setMessage( getText( "message.error.formato.data") );
                    valido = false;
                }
            }
        }
        if ( valido ) {
            try {
                ReportFilterDto reportFilterDto = getReportFilterDto();
                ReportFlussoRendicontazioneDto reportDTO = ReportFlussoRendicontazioneDto.builder()
                                .withNomeFile(nomeReport)
                                .withNominativoExport ( getNomeReport () )
                                .withDataRicezioneInizio(!StringUtils.isBlank ( dataRicezioneInizio ) ? sdf.parse ( dataRicezioneInizio ) : null )
                                .withDataRicezioneFine(!StringUtils.isBlank ( dataRicezioneFine ) ? sdf.parse ( dataRicezioneFine ) : null )
                                .withCodiceDescrizionePSP(getCodiceDescrizionePSP())
                                .withStatoReport ( StatoReportDto.builder ().withId ( StatoReportEnum.INSERTED.getId () ).build () )
                                .withTipoFileReport ( TipoFileReportDto.builder ().withId ( StringUtils.isEmpty (tipoFormato) ? TipoFormatoFileEnum.XLSX.getId () : TipoFormatoFileEnum.valueOf( tipoFormato ).getId() ).build () )
                                .withTipoReportDto( TipoReportDto.builder().withCodice( TipoReportEnum.RENDICONTAZIONE.getCode () ).build () )
                                .withIdEnte ( reportFilterDto.getIdEnte () )
                                .withIdUtente ( reportFilterDto.getIdUtente () )
                                .withCodiceFiscaleEnte ( reportFilterDto.getCodFiscaleEnte () )
                                .withCodiceFiscaleUtente ( reportFilterDto.getCodFiscaleUtente () ) // verificare
                                .build();


            	SalvaPrenotazioneReportEntiDto salvaPrenotazioneReportEntiDto = new SalvaPrenotazioneReportEntiDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),
            			reportDTO);
            	
				
				reportEntiBusiness.salvaPrenotazioneReport(salvaPrenotazioneReportEntiDto);

                ajaxServiceResponse.setMessage ( getText ( "message.success.salva.prenotazione.report" ) );
            } catch (ParseException e) {
                log.error ( "Errore nel parsing della data", e );
                ajaxServiceResponse.setResultCode ( "KO" );
                ajaxServiceResponse
                .setMessage ( "Errore nel parsing della data" );
            } catch (BusinessException e) {
                log.error ( "Errore imprevisto", e );
                ajaxServiceResponse.setResultCode ( "KO" );
                ajaxServiceResponse
                .setMessage ( getText ( "message.error.salva.prenotazione.report", new String [] { e.getClass ().getName (), e.getMessage () } ) );

            }
        }

        log.info ( CLASSNAME + " " + methodName + " - STOP" );
        return "validate-result";
    }

    @Action ( "valida-filtro-prenota-ricerca-report-flussi-rendicontazione-base-json" )
    public String validaFiltroRicercaReportEntiJSON () {

        ajaxServiceResponse = new AjaxServiceResponse ();
        ajaxServiceResponse.setResultCode ( "OK" );
        ajaxServiceResponse.setMessage ( "" );

        return "validate-result";
    }


    @Action ( "prenota-ricerca-report-flussi-base-json" )
    @Authorization ( cdu = "RIC_FR" )
    public String ricercaReportFlussiBaseJSON () throws Exception {

        String methodName = "ricercaReportFlussiBaseJSON";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );
        ReportFilterDto reportFilterDto = getReportFilterDto();

        ajaxServiceResponse = new AjaxServiceResponse ();
        ajaxServiceResponse.setResultCode ( "OK" );
        ajaxServiceResponse.setMessage ( "" );
        if(null == start) {
            setStart ( 0 );
        }
        if(null == length) {
            setLength ( 5 );
        }

        List<CriterioOrdinamentoDto<ColumnNameReportEnum>> sorting = null;
        if (sortingCol != null && sortingDir != null) {
            sorting = new ArrayList<CriterioOrdinamentoDto<ColumnNameReportEnum>>();
            CriterioOrdinamentoDto<ColumnNameReportEnum> sortingItem = new CriterioOrdinamentoDto<ColumnNameReportEnum>();

            if ("id".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameReportEnum.ID);
            else if ("dataInserimento".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameReportEnum.DATA_INSERIMENTO);
            else if ("dataModifica".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameReportEnum.DATA_MODIFICA);
            else if ("idEnte".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameReportEnum.ID_ENTE);
            else if ("idUtente".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameReportEnum.ID_UTENTE);
            else if ("codiceFiscaleEnte".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameReportEnum.CODICE_FISCALE_ENTE);
            else if ("codiceFiscaleUtente".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameReportEnum.CODICE_FISCALE_UTENTE);
            else if ("nominativoExport".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameReportEnum.NOMINATIVO_EXPORT);
            else if ("statoReport".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameReportEnum.ID_STATO);
            else if ("nomeFile".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameReportEnum.ID_FILE);
            else if ("tipoFileReport".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameReportEnum.ID_TIPO_FILE);
            else if ("tipoReport".equals(sortingCol))
                sortingItem.setColumnNameEnum(ColumnNameReportEnum.ID_TIPO_REPORT);

            sortingItem.setSortTypeEnum("asc".equals(sortingDir) ? SortTypeEnum.ASC : SortTypeEnum.DESC);
            sorting.add(sortingItem);
        }

        PaginazioneDto pag = getPaginazioneFromBean ();

        try {
            TotalSizeAndLightListDto<ReportBaseDto> totalSizeAndLightList;

            totalSizeAndLightList = reportEntiBusiness.ricercaReport ( reportFilterDto, sorting, pag );

            saveDataIntoScopeAndSession();

            dataTableResponse = new DataTableResponse<> ();
            dataTableResponse.setRecordsTotal ( totalSizeAndLightList.getTotalSize ().intValue () );
            dataTableResponse.setRecordsFiltered ( totalSizeAndLightList.getTotalSize ().intValue () );
            dataTableResponse.setData ( totalSizeAndLightList.getLightList() );
            dataTableResponse.setDraw ( draw );

        }catch ( Exception e ) {
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

    public DataTableResponse<ReportBaseDto> getDataTableResponse () {
        return dataTableResponse;
    }

    public void setDataTableResponse ( DataTableResponse<ReportBaseDto> dataTableResponse ) {
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
