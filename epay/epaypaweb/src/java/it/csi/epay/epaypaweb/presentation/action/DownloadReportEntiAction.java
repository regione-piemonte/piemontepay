/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import static com.opensymphony.xwork2.Action.SUCCESS;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import it.csi.epay.epaypaweb.business.interf.ReportEntiBusiness;
import it.csi.epay.epaypaweb.business.interf.TemplateBusiness;
import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.PagamentiExportDto;
import it.csi.epay.epaypaweb.dto.PagamentiFilterDto;
import it.csi.epay.epaypaweb.dto.TemplateDto;
import it.csi.epay.epaypaweb.dto.common.RicercaReportEntiDto;
import it.csi.epay.epaypaweb.dto.mapper.TemplateMapperReportEnti;
import it.csi.epay.epaypaweb.enumeration.ColumnNamePagamentiEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum;
import it.csi.epay.epaypaweb.exception.RecordNumberGreaterThanThresholdException;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;



@Namespace ( "/" )
@InterceptorRef ( "epaypawebStack" )

@Results ( {
    //@formatter:off
    @Result(name = "REFRESH", type = "chain", location = "entry-ricerca-report-enti"),
    @Result ( name = SUCCESS, type = "stream", params = { "contentType", "${tipoFormato.contentType}", "inputName", "inputStream", "contentDisposition",
    "attachment; filename=\"${filename}\"" } )
    //@formatter:on
} )
public class DownloadReportEntiAction extends BaseRicercaReportEntiAction {

    private static final long serialVersionUID = 1L;

    private static final String CLASSNAME = DownloadReportEntiAction.class.getSimpleName ();

    private static final String REPORT_SIZE_TOO_LARGE = "Attenzione. Per estrazioni con volumi simili alla richiesta appena eseguita e' necessario eseguirla in modalita asincrona, si prega di utilizzare il seguente <a href=\"./entry-prenota-ricerca-report-enti.do\">link</a> per attivarla. Grazie";

    private static final String SYSTEM_ERROR = "system-error";

    @EJB
    private TemplateBusiness templateBusiness;

    @EJB
    private ReportEntiBusiness reportEntiBusiness;

    @Action ( "scarica-xls-pagamenti" )
    @Authorization ( cdu = "RIC_ENTI" )
    public String scaricaXls () {
        String methodName = "scaricaXls";
        

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            importFilterFromSession ();
            saveIdFormatoFileIntoScopeAndSession ( TIPO_FILE_REPORT_EXCEL );
            PagamentiFilterDto filter = getFilterFromBean ();

            List<CriterioOrdinamentoDto<ColumnNamePagamentiEnum>> sorting = getCriterioOrdinamentoFromBean ();
            
            RicercaReportEntiDto ricercaReportEntiDto = new RicercaReportEntiDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),
					filter);

            List<PagamentiExportDto> totalSizeAndLightList = reportEntiBusiness.esporta ( ricercaReportEntiDto, sorting );

            //            if(totalSizeAndLightList.size()>500) {
            //				esitoElaborazione = "warning";
            //            	messaggioEsitoElaborazione = message;
            //				return "REFRESH";
            //            }
            log.info ( "risultato export scaricaXLSXPagamenti: numero elementi " + totalSizeAndLightList.size () );

            TemplateMapperReportEnti reportEntiTemplate = new TemplateMapperReportEnti ();

            //Recupero il template della lista di carico
            TemplateDto template = templateBusiness.getTemplate ( getSessionContext ().getEnte ().getId (), TEMPLATE_PAGAMENTI_ID );

            if ( template != null ) {
                FlussoCompletoDto<PagamentiExportDto> flussoCompleto = new FlussoCompletoDto<> ();
                FlussoDto flusso = new FlussoDto ();
                flusso.setTipoFlusso ( TipoFlussoEnum.REPORT_PAGAMENTI_ENTE_XLS );
                flussoCompleto.setFlusso ( flusso );

                flussoCompleto.setItemList ( totalSizeAndLightList );

                byte [] data = templateBusiness.generate ( flussoCompleto, template, reportEntiTemplate, TipoFormatoFileEnum.XLSX ).getData ();
                filename = templateBusiness.buildFilename ( flussoCompleto.getFlusso (), TipoFormatoFileEnum.XLSX );
                inputStream = new ByteArrayInputStream ( data );

            } else {
                addActionError ( getText ( "nessun.template.configurato" ) );
                return SYSTEM_ERROR;
            }

        } catch ( RecordNumberGreaterThanThresholdException e ) {
            log.warn( "Numero di record per l'export superiore alla soglia massima necessario export asincrono");
            esitoElaborazione = "warning";
            messaggioEsitoElaborazione = REPORT_SIZE_TOO_LARGE;
            return "REFRESH";

        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto", e );
            addActionError ( getText ( "system.error", new String [] { e.getClass ().getName (), e.getMessage () } ) );
            return SYSTEM_ERROR;

        } finally {
            setFileDownloadTokenCookie ( pleaseWaitTokenValue );
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return SUCCESS;
    }

    @Override
    @Action ( "scarica-csv-pagamenti" )
    @Authorization ( cdu = "RIC_ENTI" )
    public String execute () {
        String methodName = "execute";
        

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            importFilterFromSession ();
            saveIdFormatoFileIntoScopeAndSession ( TIPO_FILE_REPORT_CSV );
            PagamentiFilterDto filter = getFilterFromBean ();

            List<CriterioOrdinamentoDto<ColumnNamePagamentiEnum>> sorting = getCriterioOrdinamentoFromBean ();
            RicercaReportEntiDto ricercaReportEntiDto = new RicercaReportEntiDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),
					filter);

            List<PagamentiExportDto> totalSizeAndLightList = reportEntiBusiness.esporta ( ricercaReportEntiDto, sorting );

            log.info ( "risultato export scaricaCSVPagamenti: numero elementi " + totalSizeAndLightList.size () );

            TemplateMapperReportEnti reportEntiTemplate = new TemplateMapperReportEnti ();

            //Recupero il template della lista di carico
            TemplateDto template = templateBusiness.getTemplate ( getSessionContext ().getEnte ().getId (), TEMPLATE_PAGAMENTI_ID );

            if ( template == null ) {
                // rollback a default (regione piemonte)
                template = templateBusiness.getTemplate ( 1, TEMPLATE_PAGAMENTI_ID );
            }

            if ( template != null ) {
                FlussoCompletoDto<PagamentiExportDto> flussoCompleto = new FlussoCompletoDto<> ();
                FlussoDto flusso = new FlussoDto ();
                flusso.setTipoFlusso ( TipoFlussoEnum.REPORT_PAGAMENTI_ENTE_CSV );
                flussoCompleto.setFlusso ( flusso );

                flussoCompleto.setItemList ( totalSizeAndLightList );

                byte [] data = templateBusiness.generate ( flussoCompleto, template, reportEntiTemplate, TipoFormatoFileEnum.CSV ).getData ();
                filename = templateBusiness.buildFilename ( flussoCompleto.getFlusso (), TipoFormatoFileEnum.CSV );
                inputStream = new ByteArrayInputStream ( data );

            } else {
                addActionError ( getText ( "nessun.template.configurato" ) );
                return SYSTEM_ERROR;
            }

        } catch ( RecordNumberGreaterThanThresholdException e ) {
            log.warn( "Numero di record per l'export superiore alla soglia massima ");
            esitoElaborazione = "warning";
            messaggioEsitoElaborazione = REPORT_SIZE_TOO_LARGE;
            return "REFRESH";

        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto", e );
            addActionError ( getText ( "system.error", new String [] { e.getClass ().getName (), e.getMessage () } ) );
            return SYSTEM_ERROR;

        } finally {
            setFileDownloadTokenCookie ( pleaseWaitTokenValue );
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return SUCCESS;
    }

    protected String filename;

    protected ByteArrayInputStream inputStream;

    protected String pleaseWaitTokenValue;

    public String getFilename () {
        return filename;
    }

    public ByteArrayInputStream getInputStream () {
        return inputStream;
    }

    public String getPleaseWaitTokenValue () {
        return pleaseWaitTokenValue;
    }

    public void setPleaseWaitTokenValue ( String pleaseWaitTokenValue ) {
        this.pleaseWaitTokenValue = pleaseWaitTokenValue;
    }

    protected void setFileDownloadTokenCookie ( String pleaseWaitTokenValue ) {
        HttpServletResponse response = ServletActionContext.getResponse ();
        response.addCookie ( new Cookie ( "pleaseWaitToken", pleaseWaitTokenValue ) );
    }

    public TemplateBusiness getTemplateBusiness () {
        return templateBusiness;
    }

    public void setTemplateBusiness ( TemplateBusiness templateBusiness ) {
        this.templateBusiness = templateBusiness;
    }

    public void setFilename ( String filename ) {
        this.filename = filename;
    }

    public void setInputStream ( ByteArrayInputStream inputStream ) {
        this.inputStream = inputStream;
    }

}
