/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import com.opensymphony.xwork2.ActionContext;
import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.PagamentiFilterDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNamePagamentiEnum;
import it.csi.epay.epaypaweb.enumeration.SortTypeEnum;
import it.csi.epay.epaypaweb.presentation.dto.ActionScope;
import it.csi.epay.epaypaweb.presentation.dto.SessionContext;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BaseRicercaReportEntiAction extends EpaypawebBaseAction {

    protected static final long serialVersionUID = 1L;

    protected static final String CLASSNAME = BaseRicercaReportEntiAction.class.getSimpleName ();

    protected static final int TIPO_PAGAMENTO_TUTTI = 1;

    protected static final int TIPO_PAGAMENTO_SPONTANEI = 2;

    protected static final int TIPO_PAGAMENTO_DOVUTI = 3;

    protected static final int TEMPLATE_PAGAMENTI_ID = 7;

    protected static final int TIPO_FILE_REPORT_EXCEL = 2;

    protected static final int TIPO_FILE_REPORT_CSV = 1;
    
    protected static final int COSTI_NOTIFICA_TUTTI = 2;

    protected static final int COSTI_NOTIFICA_PRESENTI= 1;

    protected static final int COSTI_NOTIFICA_NON_PRESENTI = 0;

    /*
     * filtri di ricerca
     */
    protected Long idFile;

    protected String nomeReport;

    protected Integer idTipoPagamento;

    protected Integer idFormatoFile;

    protected Integer idCodVersamento;

    protected String dataPagamentoInizio;

    protected String dataPagamentoFine;

    protected String dataPagamentoScadenzaInizio;

    protected String dataPagamentoScadenzaFine;

    protected String cognome;

    protected String iuv;

    protected String codiceFiscale;

    protected Boolean executeSearch;

    protected Integer start;

    protected Integer length;
    
    protected Integer idStatoPagamento;
    
    protected Integer idTipoCostiNotifica;
    
  
    protected String sortingDir;

    protected String sortingCol;

    protected static final String PREFIX_SESSION = "ricercaPagamenti";

    protected PagamentiFilterDto getFilterFromBean () {
        PagamentiFilterDto filter = new PagamentiFilterDto ();

        // filtri impostati di default

        filter.setIdCodiceVersamento ( idCodVersamento );
        filter.setCodiceFiscale ( codiceFiscale );
        filter.setCognome ( cognome );
        filter.setIuv ( iuv );
        filter.setIdStatoPagamento  ( idStatoPagamento );
        


        try {
            filter.setDataPagamentoInizio ( !StringUtils.isBlank ( dataPagamentoInizio ) ? sdf.parse ( dataPagamentoInizio ) : null );
        } catch ( ParseException e ) {
            throw new RuntimeException ( "Errore nel parsing della data", e );
        }
        try {
            filter.setDataPagamentoFine ( !StringUtils.isBlank ( dataPagamentoFine ) ? sdf.parse ( dataPagamentoFine ) : null );
        } catch ( ParseException e ) {
            throw new RuntimeException ( "Errore nel parsing della data", e );
        }
        try {
            filter.setDataPagamentoScadenzaInizio( !StringUtils.isBlank( dataPagamentoScadenzaInizio ) ? sdf.parse( dataPagamentoScadenzaInizio ) : null);
        } catch ( ParseException e ) {
            throw new RuntimeException ( "Errore nel parsing della data", e );
        }
        try {
            filter.setDataPagamentoScadenzaFine( !StringUtils.isBlank( dataPagamentoScadenzaFine ) ? sdf.parse( dataPagamentoScadenzaFine ) : null);
        } catch ( ParseException e ) {
            throw new RuntimeException ( "Errore nel parsing della data", e );
        }
        filter.setPagamentiSpontanei (
            ( idTipoPagamento == null || idTipoPagamento == TIPO_PAGAMENTO_TUTTI ) ? null : idTipoPagamento == TIPO_PAGAMENTO_SPONTANEI );
        /*
        switch (idTipoCostiNotifica) {
		case COSTI_NOTIFICA_PRESENTI:
			filter.setCostiNotifica(Boolean.TRUE);
			break;
		case COSTI_NOTIFICA_NON_PRESENTI:
			filter.setCostiNotifica(null);
			break;

		default:
			break;
		}
        */
        filter.setCostiNotifica(
        		( idTipoCostiNotifica == null || idTipoCostiNotifica == COSTI_NOTIFICA_TUTTI ) ? null : idTipoCostiNotifica == COSTI_NOTIFICA_PRESENTI);

        SessionContext sessionContext = getSessionContext ();
        if ( sessionContext != null ) {
            filter.setProfilazioneUtente ( sessionContext.generaProfilazioneUtenteDto () );
        }

        filter.setIdFormatoFile ( idFormatoFile );

        return filter;
    }

    protected List<CriterioOrdinamentoDto<ColumnNamePagamentiEnum>> getCriterioOrdinamentoFromBean () {
        List<CriterioOrdinamentoDto<ColumnNamePagamentiEnum>> sorting = null;

        if ( sortingCol != null && sortingDir != null ) {
            sorting = new ArrayList<> ();
            CriterioOrdinamentoDto<ColumnNamePagamentiEnum> sortingItem = new CriterioOrdinamentoDto<> ();

            if ( "iuv".equals ( sortingCol ) ) {
                sortingItem.setColumnNameEnum ( ColumnNamePagamentiEnum.IUV );
            }

            else if ( "causale".equals ( sortingCol ) ) {
                sortingItem.setColumnNameEnum ( ColumnNamePagamentiEnum.CAUSALE );
            }

            else if ( "descrizioneCausaleVersamento".equals ( sortingCol ) ) {
                sortingItem.setColumnNameEnum ( ColumnNamePagamentiEnum.DESCRIZIONE_CAUSALE_VERSAMENTO );
            }

            else if ( "importoDovuto".equals ( sortingCol ) ) {
                sortingItem.setColumnNameEnum ( ColumnNamePagamentiEnum.IMPORTO_DOVUTO );
            }

            else if ( "importoPagato".equals ( sortingCol ) ) {
                sortingItem.setColumnNameEnum ( ColumnNamePagamentiEnum.IMPORTO_PAGATO );
            }

            else if ( "codiceCodiceVersamento".equals ( sortingCol ) ) {
                sortingItem.setColumnNameEnum ( ColumnNamePagamentiEnum.COD_VERSAMENTO );
            }

            else if ( "dataScadenza".equals ( sortingCol ) ) {
                sortingItem.setColumnNameEnum ( ColumnNamePagamentiEnum.DATA_SCADENZA );
            }

            else if ( "dataPagamento".equals ( sortingCol ) ) {
                sortingItem.setColumnNameEnum ( ColumnNamePagamentiEnum.DATA_PAGAMENTO );
            }
            //
            else if ( "descrizioneStatoPagamento".equals ( sortingCol ) ) {
                sortingItem.setColumnNameEnum ( ColumnNamePagamentiEnum.STATO_PAGAMENTO );
            }


            sortingItem.setSortTypeEnum ( "asc".equals ( sortingDir ) ? SortTypeEnum.ASC : SortTypeEnum.DESC );
            sorting.add ( sortingItem );
        }

        return sorting;
    }

    protected PaginazioneDto getPaginazioneFromBean () {
		return new PaginazioneDto ( ( start / length ) + 1, length );
    }

    protected void saveIdFormatoFileIntoScopeAndSession ( Integer idFormatoFile ) {
        this.idFormatoFile = idFormatoFile;
        ActionScope actionScope = getSessionContext ().getActionScope ();
        actionScope.putValue ( "idFormatoFile", idFormatoFile );
        Map<String, Object> session = ActionContext.getContext ().getSession ();
        session.put ( PREFIX_SESSION + "idFormatoFile", idFormatoFile );
    }

    protected void saveFilterIntoScopeAndSession () {

        ActionScope actionScope = getSessionContext ().getActionScope ();
        actionScope.putValue ( "nomeReport", nomeReport );
        actionScope.putValue ( "idFile", idFile );
        actionScope.putValue ( "idTipoPagamento", idTipoPagamento );
        actionScope.putValue ( "idTipoCostiNotifica", idTipoCostiNotifica );
        if ( null != idFormatoFile ) {
            actionScope.putValue ( "idFormatoFile", idFormatoFile );
        }
        actionScope.putValue ( "idCodVersamento", idCodVersamento );
        actionScope.putValue ( "dataPagamentoInizio", dataPagamentoInizio );
        actionScope.putValue ( "dataPagamentoFine", dataPagamentoFine );
        actionScope.putValue ( "dataPagamentoScadenzaInizio", dataPagamentoScadenzaInizio);
        actionScope.putValue ( "dataPagamentoScadenzaFine", dataPagamentoScadenzaFine);
        actionScope.putValue ( "codiceFiscale", codiceFiscale );
        actionScope.putValue ( "cognome", cognome );
        actionScope.putValue ( "iuv", iuv );
        actionScope.putValue ( "idStatoPagamento", idStatoPagamento );
        actionScope.putValue ( "sortingCol", sortingCol );
        actionScope.putValue ( "sortingDir", sortingDir );
        actionScope.putValue ( "start", start );
        actionScope.putValue ( "length", length );

        Map<String, Object> session = ActionContext.getContext ().getSession ();
        session.put ( PREFIX_SESSION + "nomeReport", nomeReport );
        session.put ( PREFIX_SESSION + "idFile", idFile );
        session.put ( PREFIX_SESSION + "idTipoPagamento", idTipoPagamento );
        session.put ( PREFIX_SESSION + "idTipoCostiNotifica", idTipoCostiNotifica );
        if ( null != idFormatoFile ) {
            session.put ( PREFIX_SESSION + "idFormatoFile", idFormatoFile );
        }
        session.put ( PREFIX_SESSION + "idCodVersamento", idCodVersamento );
        session.put ( PREFIX_SESSION + "dataPagamentoInizio", dataPagamentoInizio );
        session.put ( PREFIX_SESSION + "dataPagamentoFine", dataPagamentoFine );
        session.put ( PREFIX_SESSION + "dataPagamentoScadenzaInizio", dataPagamentoScadenzaInizio );
        session.put ( PREFIX_SESSION + "dataPagamentoScadenzaFine", dataPagamentoScadenzaFine );
        session.put ( PREFIX_SESSION + "codiceFiscale", codiceFiscale );
        session.put ( PREFIX_SESSION + "cognome", cognome );
        session.put ( PREFIX_SESSION + "iuv", iuv );
        session.put ( PREFIX_SESSION + "idStatoPagamento", idStatoPagamento );
        session.put ( PREFIX_SESSION + "sortingCol", sortingCol );
        session.put ( PREFIX_SESSION + "sortingDir", sortingDir );
        session.put ( PREFIX_SESSION + "start", start );
        session.put ( PREFIX_SESSION + "length", length );
    }

    protected void importFilterFromScope () {
        ActionScope actionScope = getSessionContext ().getActionScope ();
        nomeReport = actionScope.getValue ( "nomeReport" );
        idFile = actionScope.getValue ( "idFile" );
        idTipoPagamento = actionScope.getValue ( "idTipoPagamento" );
        idTipoCostiNotifica = actionScope.getValue ( "idTipoCostiNotifica" );
        idFormatoFile = actionScope.getValue ( "idFormatoFile" );
        idCodVersamento = actionScope.getValue ( "idCodVersamento" );
        dataPagamentoInizio = actionScope.getValue ( "dataPagamentoInizio" );
        dataPagamentoFine = actionScope.getValue ( "dataPagamentoFine" );
        dataPagamentoScadenzaInizio = actionScope.getValue ( "dataPagamentoScadenzaInizio" );
        dataPagamentoScadenzaFine = actionScope.getValue ( "dataPagamentoScadenzaFine" );
        codiceFiscale = actionScope.getValue ( "codiceFiscale" );
        cognome = actionScope.getValue ( "cognome" );
        iuv = actionScope.getValue ( "iuv" );
        idStatoPagamento = actionScope.getValue ( "idStatoPagamento" );
        sortingCol = actionScope.getValue ( "sortingCol" );
        sortingDir = actionScope.getValue ( "sortingDir" );
        start = actionScope.getValue ( "start" );
        length = actionScope.getValue ( "length" );
    }

    protected void importFilterFromSession () {
        Map<String, Object> session = ActionContext.getContext ().getSession ();

        nomeReport = (String) session.getOrDefault ( PREFIX_SESSION + "nomeReport", null );
        idFile = (Long) session.getOrDefault ( PREFIX_SESSION + "idFile", null );
        idTipoPagamento = (Integer) session.getOrDefault ( PREFIX_SESSION + "idTipoPagamento", null );
        idTipoCostiNotifica = (Integer) session.getOrDefault ( PREFIX_SESSION + "idTipoCostiNotifica", null );
        idFormatoFile = (Integer) session.getOrDefault ( PREFIX_SESSION + "idFormatoFile", null );
        idCodVersamento = (Integer) session.getOrDefault ( PREFIX_SESSION + "idCodVersamento", null );
        dataPagamentoInizio = (String) session.getOrDefault ( PREFIX_SESSION + "dataPagamentoInizio", null );
        dataPagamentoFine = (String) session.getOrDefault ( PREFIX_SESSION + "dataPagamentoFine", null );
        dataPagamentoScadenzaInizio = (String) session.getOrDefault ( PREFIX_SESSION + "dataPagamentoScadenzaInizio", null );
        dataPagamentoScadenzaFine = (String) session.getOrDefault ( PREFIX_SESSION + "dataPagamentoScadenzaFine", null );
        codiceFiscale = (String) session.getOrDefault ( PREFIX_SESSION + "codiceFiscale", null );
        cognome = (String) session.getOrDefault ( PREFIX_SESSION + "cognome", null );
        iuv = (String) session.getOrDefault ( PREFIX_SESSION + "iuv", null );
        idStatoPagamento = (Integer) session.getOrDefault ( PREFIX_SESSION + "idStatoPagamento", null );
        sortingCol = (String) session.getOrDefault ( PREFIX_SESSION + "sortingCol", null );
        sortingDir = (String) session.getOrDefault ( PREFIX_SESSION + "sortingDir", null );
        start = (Integer) session.getOrDefault ( PREFIX_SESSION + "start", null );
        length = (Integer) session.getOrDefault ( PREFIX_SESSION + "length", null );
    }

    public String getNomeReport () {
        return nomeReport;
    }

    public void setNomeReport ( String nomeReport ) {
        this.nomeReport = nomeReport;
    }

    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }

    public Integer getIdTipoPagamento () {
        return idTipoPagamento;
    }

    public void setIdTipoPagamento ( Integer idTipoPagamento ) {
        this.idTipoPagamento = idTipoPagamento;
    }

    public Integer getIdCodVersamento () {
        return idCodVersamento;
    }

    public void setIdCodVersamento ( Integer idCodVersamento ) {
        this.idCodVersamento = idCodVersamento;
    }

    public Boolean getExecuteSearch () {
        return executeSearch;
    }

    public void setExecuteSearch ( Boolean executeSearch ) {
        this.executeSearch = executeSearch;
    }

    public String getDataPagamentoInizio () {
        return dataPagamentoInizio;
    }

    public void setDataPagamentoInizio ( String dataPagamentoInizio ) {
        this.dataPagamentoInizio = dataPagamentoInizio;
    }

    public String getDataPagamentoFine () {
        return dataPagamentoFine;
    }

    public void setDataPagamentoFine ( String dataPagamentoFine ) {
        this.dataPagamentoFine = dataPagamentoFine;
    }

    public String getDataPagamentoScadenzaInizio() {
        return dataPagamentoScadenzaInizio;
    }

    public void setDataPagamentoScadenzaInizio(String dataPagamentoScadenzaInizio) {
        this.dataPagamentoScadenzaInizio = dataPagamentoScadenzaInizio;
    }

    public String getDataPagamentoScadenzaFine() {
        return dataPagamentoScadenzaFine;
    }

    public void setDataPagamentoScadenzaFine(String dataPagamentoScadenzaFine) {
        this.dataPagamentoScadenzaFine = dataPagamentoScadenzaFine;
    }

    public Integer getStart () {
        return start;
    }

    public void setStart ( Integer start ) {
        this.start = start;
    }

    public Integer getLength () {
        return length;
    }

    public void setLength ( Integer length ) {
        this.length = length;
    }

    public String getSortingDir () {
        return sortingDir;
    }

    public void setSortingDir ( String sortingDir ) {
        this.sortingDir = sortingDir;
    }

    public String getSortingCol () {
        return sortingCol;
    }

    public void setSortingCol ( String sortingCol ) {
        this.sortingCol = sortingCol;
    }

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

    public Long getIdFile () {
        return idFile;
    }

    public void setIdFile ( Long idFile ) {
        this.idFile = idFile;
    }

    public Integer getIdFormatoFile () {
        return idFormatoFile;
    }

    public void setIdFormatoFile ( Integer idFormatoFile ) {
        this.idFormatoFile = idFormatoFile;
    }

    
    public Integer getIdStatoPagamento () {
        return idStatoPagamento;
    }

    
    public void setIdStatoPagamento ( Integer idStatoPagamento ) {
        this.idStatoPagamento = idStatoPagamento;
    }

	public Integer getIdTipoCostiNotifica() {
		return idTipoCostiNotifica;
	}

	public void setIdTipoCostiNotifica(Integer idTipoCostiNotifica) {
		this.idTipoCostiNotifica = idTipoCostiNotifica;
	}

    
   

 

}
