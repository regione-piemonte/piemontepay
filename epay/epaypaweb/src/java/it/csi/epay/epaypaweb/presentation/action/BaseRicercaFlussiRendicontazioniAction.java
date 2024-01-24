/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.ReportFilterDto;
import it.csi.epay.epaypaweb.enumeration.TipoReportEnum;
import it.csi.epay.epaypaweb.presentation.dto.ActionScope;
import it.csi.epay.epaypaweb.presentation.dto.SessionContext;

public class BaseRicercaFlussiRendicontazioniAction extends EpaypawebBaseAction {

	private static final long serialVersionUID = 1L;

	protected static final String CLASSNAME = BaseRicercaFlussiRendicontazioniAction.class.getSimpleName();
	
	protected static final String PREFIX_SESSION = "ricercaFlussiRendicontazioniBase";
	
	protected Long idFile;
    protected String nomeReport;
	protected String dataRicezioneInizio;
	protected String dataRicezioneFine;
	protected String codiceDescrizionePSP;
	protected String tipoFormato;
	
	protected Boolean executeSearch;
    
 // parametri tabella
	protected Integer start;
	protected Integer length;
	protected String sortingDir;
	protected String sortingCol;
	
	protected void saveDataIntoScopeAndSession () {
		ActionScope actionScope = getSessionContext ().getActionScope ();
		actionScope.putValue ( "nomeReport", nomeReport );
        actionScope.putValue ( "idFile", idFile );
        actionScope.putValue ( "dataRicezioneInizio", dataRicezioneInizio );
        actionScope.putValue ( "dataRicezioneFine", dataRicezioneFine );
        actionScope.putValue ( "codiceDescrizionePSP", codiceDescrizionePSP );
        actionScope.putValue ( "tipoFormato", tipoFormato );
        actionScope.putValue ( "sortingCol", sortingCol );
        actionScope.putValue ( "sortingDir", sortingDir );
        actionScope.putValue ( "start", start );
        actionScope.putValue ( "length", length );

        Map<String, Object> session = ActionContext.getContext ().getSession ();
		session.put ( PREFIX_SESSION + "nomeReport", nomeReport );
        session.put ( PREFIX_SESSION + "idFile", idFile );
        session.put ( PREFIX_SESSION + "dataRicezioneInizio", dataRicezioneInizio );
        session.put ( PREFIX_SESSION + "dataRicezioneFine", dataRicezioneFine );
        session.put ( PREFIX_SESSION + "codiceDescrizionePSP", codiceDescrizionePSP );
        session.put ( PREFIX_SESSION + "tipoFormato", tipoFormato );
        session.put ( PREFIX_SESSION + "sortingCol", sortingCol );
        session.put ( PREFIX_SESSION + "sortingDir", sortingDir );
        session.put ( PREFIX_SESSION + "start", start );
        session.put ( PREFIX_SESSION + "length", length );
	}
	
	protected void importDataFromScope () {
		ActionScope actionScope = getSessionContext ().getActionScope ();
        nomeReport = actionScope.getValue ( "nomeReport" );
        idFile = actionScope.getValue ( "idFile" );
        dataRicezioneInizio = actionScope.getValue ( "dataRicezioneInizio" );
        dataRicezioneFine = actionScope.getValue ( "dataRicezioneFine" );
        codiceDescrizionePSP = actionScope.getValue ( "codiceDescrizionePSP" );
        tipoFormato = actionScope.getValue ( "tipoFormato" );
        sortingCol = actionScope.getValue ( "sortingCol" );
        sortingDir = actionScope.getValue ( "sortingDir" );
        start = actionScope.getValue ( "start" );
        length = actionScope.getValue ( "length" );
	}
	 
	protected void importDataFromSession () {
		 Map<String, Object> session = ActionContext.getContext ().getSession ();
		 nomeReport = (String) session.getOrDefault ( PREFIX_SESSION + "nomeReport", null );
		 idFile = (Long) session.getOrDefault ( PREFIX_SESSION + "idFile", null );
		 dataRicezioneInizio = (String) session.getOrDefault( PREFIX_SESSION + "dataRicezioneInizio", null);
		 dataRicezioneFine = (String) session.getOrDefault( PREFIX_SESSION + "dataRicezioneFine", null);
		 codiceDescrizionePSP = (String) session.getOrDefault( PREFIX_SESSION + "codiceDescrizionePSP", null);
		 tipoFormato = (String) session.getOrDefault( PREFIX_SESSION + "tipoFormato", null);
		 sortingCol = (String) session.getOrDefault ( PREFIX_SESSION + "sortingCol", null );
		 sortingDir = (String) session.getOrDefault ( PREFIX_SESSION + "sortingDir", null );
		 start = (Integer) session.getOrDefault ( PREFIX_SESSION + "start", null );
		 length = (Integer) session.getOrDefault ( PREFIX_SESSION + "length", null );
		 
	 }
	
	protected ReportFilterDto getReportFilterDto() {
		SessionContext sessionContext = getSessionContext ();
		ReportFilterDto reportFilterDto =  ReportFilterDto.builder()
														.withTipoReportEnum(TipoReportEnum.RENDICONTAZIONE)
														.withIdEnte( null != sessionContext ? sessionContext.getEnte ().getId () : null )
														.withCodFiscaleEnte(null != sessionContext ? sessionContext.getEnte ().getCodFiscale () : null)
														.withIdUtente(null != sessionContext ? sessionContext.getUtente ().getId () : null)
														.withCodFiscaleUtente(null != sessionContext ? sessionContext.getUtente ().getCod() : null)
														.build();
		
		return reportFilterDto;
	}
	
	protected PaginazioneDto getPaginazioneFromBean () {
		PaginazioneDto pag = new PaginazioneDto ( ( start / length ) + 1, length );
	    return pag;
	}
	
	public String getDataRicezioneInizio() {
		return dataRicezioneInizio;
	}
	public Long getIdFile() {
		return idFile;
	}
	public void setIdFile(Long idFile) {
		this.idFile = idFile;
	}
	public String getNomeReport() {
		return nomeReport;
	}
	public void setNomeReport(String nomeReport) {
		this.nomeReport = nomeReport;
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
	public String getCodiceDescrizionePSP() {
		return codiceDescrizionePSP;
	}
	public void setCodiceDescrizionePSP(String codiceDescrizionePSP) {
		this.codiceDescrizionePSP = codiceDescrizionePSP;
	}
	public Boolean getExecuteSearch() {
		return executeSearch;
	}
	public void setExecuteSearch(Boolean executeSearch) {
		this.executeSearch = executeSearch;
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

	public String getTipoFormato() {
		return tipoFormato;
	}

	public void setTipoFormato(String tipoFormato) {
		this.tipoFormato = tipoFormato;
	}

}
