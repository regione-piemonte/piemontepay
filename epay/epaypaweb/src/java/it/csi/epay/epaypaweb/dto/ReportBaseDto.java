/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epaypaweb.dto.common.AuditEnabledParameter;

public abstract class ReportBaseDto implements Serializable, AuditEnabledParameter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7411432158923079275L;

	
	protected Long id;

	protected String codiceFiscaleEnte;

	protected String codiceFiscaleUtente;

	protected Integer idEnte;

	protected Long idUtente;

	protected String nominativoExport;
    
	protected StatoReportDto statoReport;

	protected TipoFileReportDto tipoFileReport;

	protected Long idFile;

	protected String nomeFile;

	protected Date dataInserimento;

	protected Date dataModifica;
    
	protected TipoReportDto tipoReportDto;
	
	public abstract void loadDatiFiltroReportDto( List<DatiFiltroReportDto>  datiFiltroReportDtoList );
	public abstract List<DatiFiltroReportDto> buildDatiFiltroReportDto();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodiceFiscaleEnte() {
		return codiceFiscaleEnte;
	}

	public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
		this.codiceFiscaleEnte = codiceFiscaleEnte;
	}

	public String getCodiceFiscaleUtente() {
		return codiceFiscaleUtente;
	}

	public void setCodiceFiscaleUtente(String codiceFiscaleUtente) {
		this.codiceFiscaleUtente = codiceFiscaleUtente;
	}

	public Integer getIdEnte() {
		return idEnte;
	}

	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}

	public Long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	public String getNominativoExport() {
		return nominativoExport;
	}

	public void setNominativoExport(String nominativoExport) {
		this.nominativoExport = nominativoExport;
	}

	public StatoReportDto getStatoReport() {
		return statoReport;
	}

	public void setStatoReport(StatoReportDto statoReport) {
		this.statoReport = statoReport;
	}

	public TipoFileReportDto getTipoFileReport() {
		return tipoFileReport;
	}

	public void setTipoFileReport(TipoFileReportDto tipoFileReport) {
		this.tipoFileReport = tipoFileReport;
	}

	public Long getIdFile() {
		return idFile;
	}

	public void setIdFile(Long idFile) {
		this.idFile = idFile;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Date getDataModifica() {
		return dataModifica;
	}

	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}

	public TipoReportDto getTipoReportDto() {
		return tipoReportDto;
	}

	public void setTipoReportDto(TipoReportDto tipoReportDto) {
		this.tipoReportDto = tipoReportDto;
	}
	
	
//	@Override
//	public String getAuditMessage() {
//		StringBuilder sb = new StringBuilder();
//        
////        if(!StringUtils.isEmpty(getCodiceFiscaleEnte())) {
////        	sb.append(", ").append("codiceFiscaleEnte:'").append(getCodiceFiscaleEnte()).append("'");
////        }
////        
////        if(!StringUtils.isEmpty(getCodiceFiscaleUtente())) {
////        	sb.append(", ").append("codiceFiscaleUtente:'").append(getCodiceFiscaleUtente()).append("'");
////        }
////        
////        if(getIdEnte()!=null) {
////        	sb.append(", ").append("idEnte:'").append(getIdEnte()).append("'");
////        }
////        
////        if(getIdUtente()!=null) {
////        	sb.append(", ").append("idUtente:'").append(getIdUtente()).append("'");
////        }
//        
//        if(!StringUtils.isEmpty(getNominativoExport())) {
//        	sb.append(", ").append("nominativoExport:'").append(getNominativoExport()).append("'");
//        }
//        
//        if(getStatoReport()!=null) {
//        	sb.append(", ").append("statoReport:'").append(getStatoReport().getCodice()).append("'");
//        }
//        
//        if(getTipoFileReport()!=null) {
//        	sb.append(", ").append("tipoFileReport:'").append(getTipoFileReport().getCodice()).append("'");
//        }
//        
//        if(getDataInserimentoDa()!=null) {
//        	sb.append(", ").append("dataInserimentoDa:'").append(getDataInserimentoDa()).append("'");
//        }
//        if(getDataInserimentoA()!=null) {
//        	sb.append(", ").append("dataInserimentoA:'").append(getDataInserimentoA()).append("'");
//        }
//        if(getDataUltimaVariazioneDa()!=null) {
//        	sb.append(", ").append("dataUltimaVariazioneDa:'").append(getDataUltimaVariazioneDa()).append("'");
//        }
//        if(getDataUltimaVariazioneA()!=null) {
//        	sb.append(", ").append("dataUltimaVariazioneA:'").append(getDataUltimaVariazioneA()).append("'");
//        }
//        if(getUtenteUltimaVariazione()!=null) {
//        	sb.append(", ").append("utenteUltimaVariazione:'").append(getUtenteUltimaVariazione()).append("'");
//        }
//        if(!StringUtils.isEmpty(getCodEsito())) {
//        	sb.append(", ").append("codiceEsito:'").append(getCodEsito()).append("'");
//        }
//        if(!StringUtils.isEmpty(getCognome())) {
//        	sb.append(", ").append("cognome:'").append(getCognome()).append("'");
//        }
//        if(!StringUtils.isEmpty(getIuv())) {
//        	sb.append(", ").append("iuv:'").append(getIuv()).append("'");
//        }
//        if(!StringUtils.isEmpty(getDetEsito())) {
//        	sb.append(", ").append("detEsito:'").append(getDetEsito()).append("'");
//        }
//        if(!StringUtils.isEmpty(getCodiceDenominazioneMittente())) {
//        	sb.append(", ").append("codiceDenominazioneMittente:'").append(getCodiceDenominazioneMittente()).append("'");
//        }
//        return sb.toString();	
//	}
	
}
