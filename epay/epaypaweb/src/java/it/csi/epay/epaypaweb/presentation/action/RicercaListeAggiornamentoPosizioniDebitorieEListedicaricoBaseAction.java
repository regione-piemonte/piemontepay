/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import com.opensymphony.xwork2.Preparable;
import it.csi.epay.epaypaweb.business.interf.GestioneFlussiBusiness;
import it.csi.epay.epaypaweb.dto.*;
import it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.DirezioneEnum;
import it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.presentation.dto.ActionScope;
import it.csi.epay.epaypaweb.presentation.dto.AjaxServiceResponse;
import it.csi.epay.epaypaweb.presentation.dto.DataTableResponse;


import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class RicercaListeAggiornamentoPosizioniDebitorieEListedicaricoBaseAction extends EpaypawebBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;

	private static final String CLASSNAME = RicercaFlussiPosizioniDebitorieDaAggiornareAction.class.getSimpleName ();

    protected Long idFlusso;
    protected Integer idStatoFlusso;
    protected Integer idCodVersamento;
    protected String dataStatoFlussoInizio;
    protected String dataStatoFlussoFine;
    protected Boolean executeSearch;
	protected String iuv;
	protected String cfisc;
	protected String  idPosizioneDebitoriaEsterna;
    protected List<StrutsOptionsSupport> statoFlussoList;

    // parametri tabella
    protected Integer draw;
    protected Integer start;
    protected Integer length;
    protected String sortingDir;
    protected String sortingCol;

    protected DataTableResponse<FlussoLightDto> dataTableResponse;
    protected AjaxServiceResponse ajaxServiceResponse;

    // flag per sapere se l'inserimento risulta attivato dalla home o dalla ricerca flusso
    protected boolean origineHomePerInserimento;

    @EJB
    protected GestioneFlussiBusiness gestioneFlussiBusiness;

    @Override
    public void prepare() throws Exception {
        String methodName = "prepare";
        

        statoFlussoList = new ArrayList<StrutsOptionsSupport>();
        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );
            for (StatoFlussoDto statoFlusso : gestioneFlussiBusiness.getStatoFlussoList(DirezioneEnum.INPUT)) {
                statoFlussoList.add(new StrutsOptionsSupport(statoFlusso.getStatoFlussoEnum().getId(), statoFlusso.getDescrizione()));
            }
        } catch (BusinessException e) {
            rethrow(e);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
    }

    protected String entryRicercaListe(String methodName) {
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        ActionScope actionScope = getSessionContext().getActionScope();
        if (isInit()) {
            idStatoFlusso = null;
            actionScope.reset();

        } else {
            idStatoFlusso = actionScope.getValue("idStatoFlusso");
            idCodVersamento = actionScope.getValue("idCodVersamento");
            dataStatoFlussoInizio = actionScope.getValue("dataStatoFlussoInizio");
            dataStatoFlussoFine = actionScope.getValue("dataStatoFlussoFine");
			iuv = actionScope.getValue("iuv");
			cfisc = actionScope.getValue("cfisc");
			idPosizioneDebitoriaEsterna = actionScope.getValue("idPosizioneDebitoriaEsterna");
            executeSearch = idStatoFlusso != null || idCodVersamento != null || dataStatoFlussoInizio != null || dataStatoFlussoFine != null;
        }

        log.info ( CLASSNAME + " " + methodName + " - STOP" );
        return INPUT;
    }

    protected String validaFiltroRicercaJSON(String methodName) {
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        ajaxServiceResponse = new AjaxServiceResponse();
        ajaxServiceResponse.setResultCode("OK");
        ajaxServiceResponse.setMessage("");

		if ( idStatoFlusso == null && idCodVersamento == null && dataStatoFlussoInizio.isEmpty () && dataStatoFlussoFine.isEmpty () 
		                && StringUtils.isEmpty ( iuv ) && StringUtils.isEmpty ( cfisc )  && StringUtils.isEmpty ( idPosizioneDebitoriaEsterna )) {
			ajaxServiceResponse.setResultCode ( "KO" );
			ajaxServiceResponse.setMessage ( getText ( "message.valorizzare.almeno.un.filtro" ) );
        } else {
            if (!dataStatoFlussoInizio.isEmpty() && !dataStatoFlussoFine.isEmpty()) {
                try {
                    if (sdf.parse(dataStatoFlussoInizio).after(sdf.parse(dataStatoFlussoFine))) {
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

    protected FlussoLightFilterDto buildFlussoLightFilterDto(TipoFlussoEnum tipoFlusso) throws ParseException {
        FlussoLightFilterDto filter = new FlussoLightFilterDto();

        // filtri impostati di default
        filter.setTipoFlusso(tipoFlusso);
        filter.setIdProfilo(getSessionContext().getProfilo().getId());
        filter.setIdEnte(getSessionContext().getEnte().getId());

        if (idCodVersamento != null) {
            List<Integer> idCodVersamentoListOneElement = new ArrayList<Integer>();
            idCodVersamentoListOneElement.add(idCodVersamento);
            filter.setIdCodVersamentoList(idCodVersamentoListOneElement);
        }
        if (idStatoFlusso != null) {
            filter.setStatoFlusso(StatoFlussoEnum.fromId(idStatoFlusso));
        }
        if (!dataStatoFlussoInizio.isEmpty()) {
            filter.setDataUltimaVariazioneDa(sdf.parse(dataStatoFlussoInizio));
        }
        if (!dataStatoFlussoFine.isEmpty()) {
            filter.setDataUltimaVariazioneA(sdf.parse(dataStatoFlussoFine));
        }
		if (! StringUtils.isEmpty ( iuv )) {
			filter.setIuv ( iuv );
		}
		if ( !StringUtils.isEmpty ( cfisc ) ) {
			filter.setCfisc ( cfisc );
		}
		if ( !StringUtils.isEmpty ( idPosizioneDebitoriaEsterna ) ) {
            filter.setIdPosizioneDebitoriaEsterna ( idPosizioneDebitoriaEsterna );
        }
        
		
		

        return filter;
    }

    protected void doSearch( FlussoLightFilterDto filter, List<CriterioOrdinamentoDto<ColumnNameFlussoEnum>> sorting) {
        PaginazioneDto pag = new PaginazioneDto((start / length) + 1, length);

        try {
        	TotalSizeAndLightListDto<FlussoLightDto> totalSizeAndLightList;
            totalSizeAndLightList = gestioneFlussiBusiness.getTotalSizeAndFlussoLightList ( filter, sorting, pag, getSessionContext ().getUtente ().getCod (),
                getSessionContext ().getCodiciVersamento () );
			
            ActionScope actionScope = getSessionContext().getActionScope();

            actionScope.putValue("idStatoFlusso", idStatoFlusso);
            actionScope.putValue("idCodVersamento", idCodVersamento);
            actionScope.putValue("dataStatoFlussoInizio", dataStatoFlussoInizio);
            actionScope.putValue("dataStatoFlussoFine", dataStatoFlussoFine);
            actionScope.putValue("iuv", iuv);
            actionScope.putValue("cfisc", cfisc);
            actionScope.putValue("idPosizioneDebitoriaEsterna", idPosizioneDebitoriaEsterna);

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
    }

    public Long getIdFlusso() {
        return idFlusso;
    }

    public void setIdFlusso(Long idFlusso) {
        this.idFlusso = idFlusso;
    }

    public Integer getIdStatoFlusso() {
        return idStatoFlusso;
    }

    public void setIdStatoFlusso(Integer idStatoFlusso) {
        this.idStatoFlusso = idStatoFlusso;
    }

    public Integer getIdCodVersamento() {
        return idCodVersamento;
    }

    public void setIdCodVersamento(Integer idCodVersamento) {
        this.idCodVersamento = idCodVersamento;
    }

    public String getDataStatoFlussoInizio() {
        return dataStatoFlussoInizio;
    }

    public void setDataStatoFlussoInizio(String dataStatoFlussoInizio) {
        this.dataStatoFlussoInizio = dataStatoFlussoInizio;
    }

    public String getDataStatoFlussoFine() {
        return dataStatoFlussoFine;
    }

    public void setDataStatoFlussoFine(String dataStatoFlussoFine) {
        this.dataStatoFlussoFine = dataStatoFlussoFine;
    }

    public Boolean getExecuteSearch() {
        return executeSearch;
    }

    public void setExecuteSearch(Boolean executeSearch) {
        this.executeSearch = executeSearch;
    }

    public List<StrutsOptionsSupport> getStatoFlussoList() {
        return statoFlussoList;
    }

    public void setStatoFlussoList(List<StrutsOptionsSupport> statoFlussoList) {
        this.statoFlussoList = statoFlussoList;
    }

    public DataTableResponse<FlussoLightDto> getDataTableResponse() {
        return dataTableResponse;
    }

    public void setDataTableResponse(DataTableResponse<FlussoLightDto> dataTableResponse) {
        this.dataTableResponse = dataTableResponse;
    }

    public void setSortingCol(String sortingCol) {
        this.sortingCol = sortingCol;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
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

    public AjaxServiceResponse getAjaxServiceResponse() {
        return ajaxServiceResponse;
    }

    public boolean isOrigineHomePerInserimento() {
        return origineHomePerInserimento;
    }

    public void setOrigineHomePerInserimento(boolean origineHomePerInserimento) {
        this.origineHomePerInserimento = origineHomePerInserimento;
    }

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public String getCfisc () {
		return cfisc;
	}

	public void setCfisc ( String cfisc ) {
		this.cfisc = cfisc;
	}

    
    public String getIdPosizioneDebitoriaEsterna () {
        return idPosizioneDebitoriaEsterna;
    }

    
    public void setIdPosizioneDebitoriaEsterna ( String idPosizioneDebitoriaEsterna ) {
        this.idPosizioneDebitoriaEsterna = idPosizioneDebitoriaEsterna;
    }
	
	
}
