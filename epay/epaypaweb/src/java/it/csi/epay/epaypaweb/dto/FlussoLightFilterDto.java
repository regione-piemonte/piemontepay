/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import it.csi.epay.epaypaweb.dto.common.AuditEnabledParameter;
import it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static it.csi.epay.epaypaweb.util.Util.date2strdatetime;
import static it.csi.epay.epaypaweb.util.Util.quote;

/** dto facade <-> business <-> persistence */
public class FlussoLightFilterDto implements Serializable, AuditEnabledParameter {
	private static final long serialVersionUID = 1L;

	private Integer idProfilo;
	private Integer idEnte;
	private String idMessaggio;
	private TipoFlussoEnum tipoFlusso;
	private StatoFlussoEnum statoFlusso;
	private Boolean pagamentiSpontanei;
	private List<Integer> idCodVersamentoList;
	private Date dataInserimentoDa;
	private Date dataInserimentoA;
	private Date dataUltimaVariazioneDa;
	private Date dataUltimaVariazioneA;
	private String utenteUltimaVariazione;
	private String codEsito;
	private String detEsito;
	private String denomIstituto;
	private String codiceDenominazioneMittente;

	private String cfisc;
	private String idPosizioneDebitoriaEsterna;

	
	//--------------------------------//
    //RDI-048 - START
    //--------------------------------//
	private String cognome;
	private String iuv;
	 //--------------------------------//
    //RDI-048 - STOP
    //--------------------------------//

	public Integer getIdProfilo() {
		return idProfilo;
	}

	public void setIdProfilo(Integer idProfilo) {
		this.idProfilo = idProfilo;
	}

	public Integer getIdEnte() {
		return idEnte;
	}

	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}

	public String getIdMessaggio() {
		return idMessaggio;
	}

	public void setIdMessaggio(String idMessaggio) {
		this.idMessaggio = idMessaggio;
	}

	public TipoFlussoEnum getTipoFlusso() {
		return tipoFlusso;
	}

	public void setTipoFlusso(TipoFlussoEnum tipoFlusso) {
		this.tipoFlusso = tipoFlusso;
	}

	public StatoFlussoEnum getStatoFlusso() {
		return statoFlusso;
	}

	public void setStatoFlusso(StatoFlussoEnum statoFlusso) {
		this.statoFlusso = statoFlusso;
	}

	public Boolean getPagamentiSpontanei() {
		return pagamentiSpontanei;
	}

	public void setPagamentiSpontanei(Boolean pagamentiSpontanei) {
		this.pagamentiSpontanei = pagamentiSpontanei;
	}

	public List<Integer> getIdCodVersamentoList() {
		return idCodVersamentoList;
	}

	public void setIdCodVersamentoList(List<Integer> idCodVersamentoList) {
		this.idCodVersamentoList = idCodVersamentoList;
	}

	public Date getDataInserimentoDa() {
		return dataInserimentoDa;
	}

	public void setDataInserimentoDa(Date dataInserimentoDa) {
		this.dataInserimentoDa = dataInserimentoDa;
	}

	public Date getDataInserimentoA() {
		return dataInserimentoA;
	}

	public void setDataInserimentoA(Date dataInserimentoA) {
		this.dataInserimentoA = dataInserimentoA;
	}

	public Date getDataUltimaVariazioneDa() {
		return dataUltimaVariazioneDa;
	}

	public void setDataUltimaVariazioneDa(Date dataUltimaVariazioneDa) {
		this.dataUltimaVariazioneDa = dataUltimaVariazioneDa;
	}

	public Date getDataUltimaVariazioneA() {
		return dataUltimaVariazioneA;
	}

	public void setDataUltimaVariazioneA(Date dataUltimaVariazioneA) {
		this.dataUltimaVariazioneA = dataUltimaVariazioneA;
	}

	public String getUtenteUltimaVariazione() {
		return utenteUltimaVariazione;
	}

	public void setUtenteUltimaVariazione(String utenteUltimaVariazione) {
		this.utenteUltimaVariazione = utenteUltimaVariazione;
	}

	public String getCodEsito() {
		return codEsito;
	}

	public void setCodEsito(String codEsito) {
		this.codEsito = codEsito;
	}

	public String getDetEsito() {
		return detEsito;
	}

	public void setDetEsito(String detEsito) {
		this.detEsito = detEsito;
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
    
    public String getDenomIstituto () {
        return denomIstituto;
    }

    
    public void setDenomIstituto ( String denomIstituto ) {
        this.denomIstituto = denomIstituto;
    }

    public String getCodiceDenominazioneMittente() {
		return codiceDenominazioneMittente;
	}

	public void setCodiceDenominazioneMittente(String codiceDenominazioneMittente) {
		this.codiceDenominazioneMittente = codiceDenominazioneMittente;
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

    @Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("idProfilo:").append(idProfilo).append(COMMA);
		s.append("idEnte:").append(idEnte).append(COMMA);
		s.append("idMessaggio:").append(quote(idMessaggio)).append(COMMA);
		s.append("tipoFlusso:").append(tipoFlusso).append(COMMA);
		s.append("statoFlusso:").append(statoFlusso).append(COMMA);
		s.append("pagamentiSpontanei:").append(pagamentiSpontanei).append(COMMA);
		s.append("idCodVersamentoList:").append(idCodVersamentoList).append(COMMA);
		s.append("dataInserimentoDa:").append(date2strdatetime(dataInserimentoDa)).append(COMMA);
		s.append("dataInserimentoA:").append(date2strdatetime(dataInserimentoA)).append(COMMA);
		s.append("dataUltimaVariazioneDa:").append(date2strdatetime(dataUltimaVariazioneDa)).append(COMMA);
		s.append("dataUltimaVariazioneA:").append(date2strdatetime(dataUltimaVariazioneA)).append(COMMA);
		s.append("utenteUltimaVariazione:").append(quote(utenteUltimaVariazione)).append(COMMA);
		s.append("codEsito:").append(quote(codEsito)).append(COMMA);
        s.append("cognome:").append(quote(cognome)).append(COMMA);
        s.append("iuv:").append(quote(iuv)).append(COMMA);
		s.append("detEsito:").append(quote(detEsito)).append(COMMA);
		s.append("idPosizioneDebitoriaEsterna:").append(quote(idPosizioneDebitoriaEsterna));
		s.append(" }");
		return s.toString();
	}

	@Override
	public String getAuditMessage() {
		StringBuilder sb = new StringBuilder();
        if(getIdProfilo()!=null) {
        	sb.append("idProfilo:'").append(getIdProfilo()).append("'");
        }
        if(getIdEnte()!=null) {
        	sb.append(", ").append("idEnte:'").append(getIdEnte()).append("'");
        }
        if(!StringUtils.isEmpty(getIdMessaggio())) {
        	sb.append(", ").append("idMessaggio:'").append(getIdMessaggio()).append("'");
        }
        if(getTipoFlusso()!=null) {
        	sb.append(", ").append("tipoFlusso:'").append(getTipoFlusso()).append("'");
        }
        if(getStatoFlusso()!=null) {
        	sb.append(", ").append("statoFlusso:'").append(getStatoFlusso()).append("'");
        }
        if(getPagamentiSpontanei()!=null) {
        	sb.append(", ").append("pagamentiSpontanei:'").append(getPagamentiSpontanei()).append("'");
        }
        if(getIdCodVersamentoList()!=null) {
        	if(getIdCodVersamentoList().size()>1) {
        		sb.append(", ").append("idCodiciVersamento:'ALL'");
        	}else if(getIdCodVersamentoList().size()==1) {
        		sb.append(", ").append("idCodiciVersamento:'"+getIdCodVersamentoList().get(0)).append("'");
        	}
        }
        if(getDataInserimentoDa()!=null) {
        	sb.append(", ").append("dataInserimentoDa:'").append(getDataInserimentoDa()).append("'");
        }
        if(getDataInserimentoA()!=null) {
        	sb.append(", ").append("dataInserimentoA:'").append(getDataInserimentoA()).append("'");
        }
        if(getDataUltimaVariazioneDa()!=null) {
        	sb.append(", ").append("dataUltimaVariazioneDa:'").append(getDataUltimaVariazioneDa()).append("'");
        }
        if(getDataUltimaVariazioneA()!=null) {
        	sb.append(", ").append("dataUltimaVariazioneA:'").append(getDataUltimaVariazioneA()).append("'");
        }
        if(getUtenteUltimaVariazione()!=null) {
        	sb.append(", ").append("utenteUltimaVariazione:'").append(getUtenteUltimaVariazione()).append("'");
        }
        if(!StringUtils.isEmpty(getCodEsito())) {
        	sb.append(", ").append("codiceEsito:'").append(getCodEsito()).append("'");
        }
        if(!StringUtils.isEmpty(getCognome())) {
        	sb.append(", ").append("cognome:'").append(getCognome()).append("'");
        }
        if(!StringUtils.isEmpty(getIuv())) {
        	sb.append(", ").append("iuv:'").append(getIuv()).append("'");
        }
        if(!StringUtils.isEmpty(getDetEsito())) {
        	sb.append(", ").append("detEsito:'").append(getDetEsito()).append("'");
        }
        if(!StringUtils.isEmpty(getCodiceDenominazioneMittente())) {
        	sb.append(", ").append("codiceDenominazioneMittente:'").append(getCodiceDenominazioneMittente()).append("'");
        }
        if(!StringUtils.isEmpty(getIdPosizioneDebitoriaEsterna ())) {
            sb.append(", ").append("idPosizioneDebitoriaEsterna:'").append(getIdPosizioneDebitoriaEsterna ()).append("'");
        }
        
        
        
        
        String message= sb.toString();
		if (!StringUtils.isEmpty(message) && message.length()>1)
		{
			message= message.substring(1);
		}
        return message;	
	}

}
