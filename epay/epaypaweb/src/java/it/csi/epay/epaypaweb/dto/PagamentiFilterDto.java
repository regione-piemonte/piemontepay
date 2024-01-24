/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epaypaweb.dto.common.AuditEnabledParameter;


public class PagamentiFilterDto implements Serializable, AuditEnabledParameter {

    private static final long serialVersionUID = 1L;

    private ProfilazioneUtenteDto profilazioneUtente;

    private Boolean pagamentiSpontanei;

    private Date dataPagamentoInizio;

    private Date dataPagamentoFine;

    private Date dataPagamentoScadenzaInizio;

    private Date dataPagamentoScadenzaFine;

    private String codiceFiscale;

    private String cognome;

    private Integer idCodiceVersamento;
    
    private String codiceVersamento;

    private String iuv;

    private String iuvEsatto;

    private Integer idFormatoFile;
    
    private Integer idStatoPagamento;
    
    private Boolean costiNotifica;

    public ProfilazioneUtenteDto getProfilazioneUtente () {
        return profilazioneUtente;
    }

    public void setProfilazioneUtente ( ProfilazioneUtenteDto profilazioneUtente ) {
        this.profilazioneUtente = profilazioneUtente;
    }

    public String getIuvEsatto () {
        return iuvEsatto;
    }

    public void setIuvEsatto ( String iuvEsatto ) {
        this.iuvEsatto = iuvEsatto;
    }

    public Boolean getPagamentiSpontanei () {
        return pagamentiSpontanei;
    }

    public void setPagamentiSpontanei ( Boolean pagamentiSpontanei ) {
        this.pagamentiSpontanei = pagamentiSpontanei;
    }

    public Date getDataPagamentoInizio () {
        return dataPagamentoInizio;
    }

    public void setDataPagamentoInizio ( Date dataPagamentoInizio ) {
        this.dataPagamentoInizio = dataPagamentoInizio;
    }

    public Date getDataPagamentoFine () {
        return dataPagamentoFine;
    }

    public void setDataPagamentoFine ( Date dataPagamentoFine ) {
        this.dataPagamentoFine = dataPagamentoFine;
    }

    public Date getDataPagamentoScadenzaInizio() {
        return dataPagamentoScadenzaInizio;
    }

    public void setDataPagamentoScadenzaInizio(Date dataPagamentoScadenzaInizio) {
        this.dataPagamentoScadenzaInizio = dataPagamentoScadenzaInizio;
    }

    public Date getDataPagamentoScadenzaFine() {
        return dataPagamentoScadenzaFine;
    }

    public void setDataPagamentoScadenzaFine(Date dataPagamentoScadenzaFine) {
        this.dataPagamentoScadenzaFine = dataPagamentoScadenzaFine;
    }

    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getCognome () {
        return cognome;
    }

    public void setCognome ( String cognome ) {
        this.cognome = cognome;
    }

    public Integer getIdCodiceVersamento () {
        return idCodiceVersamento;
    }

    public void setIdCodiceVersamento ( Integer idCodiceVersamento ) {
        this.idCodiceVersamento = idCodiceVersamento;
    }

    public String getIuv () {
        return iuv;
    }

    public void setIuv ( String iuv ) {
        this.iuv = iuv;
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

    /**
     * @return the codiceVersamento
     */
    public String getCodiceVersamento () {
        return codiceVersamento;
    }

    /**
     * @param codiceVersamento the codiceVersamento to set
     */
    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }
    
    

    public Boolean getCostiNotifica() {
		return costiNotifica;
	}

	public void setCostiNotifica(Boolean costiNotifica) {
		this.costiNotifica = costiNotifica;
	}

	@Override
    public String toString () {
        return "PagamentiFilterDto [profilazioneUtente=" + profilazioneUtente + ", pagamentiSpontanei=" + pagamentiSpontanei + ", dataPagamentoInizio="
                        + dataPagamentoInizio + ", dataPagamentoFine=" + dataPagamentoFine + ", dataPagamentoScadenzaInizio=" + dataPagamentoScadenzaInizio
                        + ", dataPagamentoScadenzaFine=" + dataPagamentoScadenzaFine + ", codiceFiscale=" + codiceFiscale + ", cognome=" + cognome + ", idCodiceVersamento="
                        + idCodiceVersamento + ", iuv=" + iuv + ", iuvEsatto=" + iuvEsatto + ", idFormatoFile=" + idFormatoFile  
                        + ", idStatoPagamento=" + idStatoPagamento + ", Costi notifica " + costiNotifica +
                        "]";
    }

    @Override
	public String getAuditMessage() {
		StringBuilder sb = new StringBuilder();
		
		 if(getProfilazioneUtente()!=null) 
		 {
			 if(getProfilazioneUtente().getUtente()!=null && getProfilazioneUtente().getUtente().getId() != null) {
		        	sb.append(", ").append("idProfilo:'").append(getProfilazioneUtente().getUtente().getId()).append("'");
		        }
		        if(getProfilazioneUtente().getEnte()!=null && getProfilazioneUtente().getEnte().getId() != null) {
		        	sb.append(", ").append("idEnte:'").append(getProfilazioneUtente().getEnte().getId()).append("'");
		        }
		 }
		 
		
		 if(getPagamentiSpontanei()!=null) {
			 sb.append(", ").append("pagamentiSpontanei:'").append(getPagamentiSpontanei()).append("'");
	     }
		 if(getCostiNotifica()!=null) {
			 sb.append(", ").append("costiNotifica:'").append(getCostiNotifica()).append("'");
		 }
        
        if(getDataPagamentoInizio()!=null) {
        	sb.append(", ").append("dataPagamentoInizio:'").append(getDataPagamentoInizio()).append("'");
        }
        if(getDataPagamentoFine()!=null) {
        	sb.append(", ").append("dataPagamentoFine:'").append(getDataPagamentoFine()).append("'");
        }
        
        if(getDataPagamentoScadenzaInizio()!=null) {
        	sb.append(", ").append("dataPagamentoScadenzaInizio:'").append(getDataPagamentoScadenzaInizio()).append("'");
        }
        if(getDataPagamentoScadenzaFine()!=null) {
        	sb.append(", ").append("dataPagamentoScadenzaFine:'").append(getDataPagamentoScadenzaFine()).append("'");
        }
        
        if(!StringUtils.isEmpty(getCodiceFiscale())) {
        	sb.append(", ").append("codiceFiscale:'").append(getCodiceFiscale()).append("'");
        }
        
        if(!StringUtils.isEmpty(getCognome())) {
        	sb.append(", ").append("cognome:'").append(getCognome()).append("'");
        }
        
        if(getIdCodiceVersamento() != null) {
        	sb.append(", ").append("idCodiceVersamento:'").append(getIdCodiceVersamento()).append("'");
        }
        
        if(!StringUtils.isEmpty(getIuv())) {
        	sb.append(", ").append("iuv:'").append(getIuv()).append("'");
        }
        
        if(!StringUtils.isEmpty(getIuvEsatto())) {
        	sb.append(", ").append("iuvEsatto:'").append(getIuvEsatto()).append("'");
        }
        if(null!= getIdStatoPagamento ()) {
            sb.append(", ").append("idStatoPagamento:'").append(getIdStatoPagamento ()).append("'");
        }
        
        String message= sb.toString();
		if (!StringUtils.isEmpty(message) && message.length()>1)
		{
			message= message.substring(1);
		}
       
		
		
        return message;	
	}
}
