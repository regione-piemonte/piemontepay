/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoMittenteEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import static it.csi.epay.epaypaweb.util.Util.date2strdatetime;
import static it.csi.epay.epaypaweb.util.Util.quote;

/** dto facade <-> business <-> persistence */
public class FlussoLightDto extends ElementoFlussoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private TipoFlussoEnum tipoFlusso;
	private String desTipoFlusso;
	private StatoFlussoEnum statoFlusso;
	private String desStatoFlusso;
	private String idMessaggio;
	private String codFiscaleEnte;
	private String denominazioneEnte;
	private String codVersamento;
	private String desCodVersamento;
	private Integer numeroElementi;
	private BigDecimal importoTotale;
	private Boolean pagamentiSpontanei;
	private Date dataInserimento; // valorizzato solo in lettura
	private Date dataUltimaVariazione; // valorizzato solo in lettura
	private String utenteUltimaVariazione;
	private String codEsito;
	private String detEsito;
	
	private String statoFlussoAggregato;
	private String datiAggiuntiviStato;
	
	//--------------------------------//
    //RDI-048 - START
    //--------------------------------//	
	private String cognome;
	private String iuv;
	private String posizioneDebitoria;
	private BigDecimal importoPagato;
	private Date dataEsitoPagamento;
	private String causaleVersamento;
	private String cfPivaSoggettoDebitore;
	private Long idNotificaPagamento;
	 //--------------------------------//
    //RDI-048 - STOP
    //--------------------------------//
	
	//<!-- CSI_PAG-184 -->
    private String revoca;
   //--------------------------------//
	
	// di comodo solo per l'inserimento delle liste di carico e delle posizioni debitorie da aggiornare dal front-end
	private Date dataPerInserimentoInizioValidita;
	private Date dataPerInserimentoFineValidita;

	// solo per la rendicontazione
	private String idFlussoRendicontazione;
	private Date dataOraCreazioneFlusso;
	private String idUnivocoRegolamento;
	private Date dataRegolamento;
	private TipoMittenteEnum tipoMittente;
	private String codIdUnivocoMittente;
	private String denominazioneMittente;
	private String codBicPsp;

	private Boolean flagPrimario;
	
	public FlussoLightDto() {
	}

	public FlussoLightDto(Long id) {
		super(id);
	}

	public TipoFlussoEnum getTipoFlusso() {
		return tipoFlusso;
	}

	public void setTipoFlusso(TipoFlussoEnum tipoFlusso) {
		this.tipoFlusso = tipoFlusso;
	}

	public String getDesTipoFlusso() {
		return (desTipoFlusso != null) ? desTipoFlusso : "";
	}

	public void setDesTipoFlusso(String desTipoFlusso) {
		this.desTipoFlusso = desTipoFlusso;
	}

	public StatoFlussoEnum getStatoFlusso() {
		return statoFlusso;
	}

	public void setStatoFlusso(StatoFlussoEnum statoFlusso) {
		this.statoFlusso = statoFlusso;
	}

	public String getDesStatoFlusso() {
		return (desStatoFlusso != null) ? desStatoFlusso : "";
	}

	public void setDesStatoFlusso(String desStatoFlusso) {
		this.desStatoFlusso = desStatoFlusso;
	}

	public String getIdMessaggio() {
		return idMessaggio;
	}

	public void setIdMessaggio(String idMessaggio) {
		this.idMessaggio = idMessaggio;
	}

	public String getCodFiscaleEnte() {
		return codFiscaleEnte;
	}

	public void setCodFiscaleEnte(String codFiscaleEnte) {
		this.codFiscaleEnte = codFiscaleEnte;
	}

	public String getDenominazioneEnte() {
		return denominazioneEnte;
	}

	public void setDenominazioneEnte(String denominazioneEnte) {
		this.denominazioneEnte = denominazioneEnte;
	}

	public String getCodVersamento() {
		return codVersamento;
	}

	public void setCodVersamento(String codVersamento) {
		this.codVersamento = codVersamento;
	}

	public String getDesCodVersamento() {
		return (desCodVersamento != null) ? desCodVersamento : "";
	}

	public void setDesCodVersamento(String desCodVersamento) {
		this.desCodVersamento = desCodVersamento;
	}

	public Integer getNumeroElementi() {
		return numeroElementi;
	}

	public void setNumeroElementi(Integer numeroElementi) {
		this.numeroElementi = numeroElementi;
	}

	public BigDecimal getImportoTotale() {
		return importoTotale;
	}

	public void setImportoTotale(BigDecimal importoTotale) {
		this.importoTotale = importoTotale;
	}

	public Boolean getPagamentiSpontanei() {
		return pagamentiSpontanei;
	}

	public void setPagamentiSpontanei(Boolean pagamentiSpontanei) {
		this.pagamentiSpontanei = pagamentiSpontanei;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Date getDataUltimaVariazione() {
		return dataUltimaVariazione;
	}

	public void setDataUltimaVariazione(Date dataUltimaVariazione) {
		this.dataUltimaVariazione = dataUltimaVariazione;
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
	

	public String getStatoFlussoAggregato() {
		return statoFlussoAggregato;
	}

	public void setStatoFlussoAggregato(String statoFlussoAggregato) {
		this.statoFlussoAggregato = statoFlussoAggregato;
	}

	public String getDatiAggiuntiviStato() {
		return datiAggiuntiviStato;
	}

	public void setDatiAggiuntiviStato(String datiAggiuntiviStato) {
		this.datiAggiuntiviStato = datiAggiuntiviStato;
	}

	public Date getDataPerInserimentoInizioValidita() {
		return dataPerInserimentoInizioValidita;
	}

	public void setDataPerInserimentoInizioValidita(Date dataPerInserimentoInizioValidita) {
		this.dataPerInserimentoInizioValidita = dataPerInserimentoInizioValidita;
	}

	public Date getDataPerInserimentoFineValidita() {
		return dataPerInserimentoFineValidita;
	}

	public void setDataPerInserimentoFineValidita(Date dataPerInserimentoFineValidita) {
		this.dataPerInserimentoFineValidita = dataPerInserimentoFineValidita;
	}

	public String getIdFlussoRendicontazione() {
		return idFlussoRendicontazione;
	}

	public void setIdFlussoRendicontazione(String idFlussoRendicontazione) {
		this.idFlussoRendicontazione = idFlussoRendicontazione;
	}

	public Date getDataOraCreazioneFlusso() {
		return dataOraCreazioneFlusso;
	}

	public void setDataOraCreazioneFlusso(Date dataOraCreazioneFlusso) {
		this.dataOraCreazioneFlusso = dataOraCreazioneFlusso;
	}

	public String getIdUnivocoRegolamento() {
		return idUnivocoRegolamento;
	}

	public void setIdUnivocoRegolamento(String idUnivocoRegolamento) {
		this.idUnivocoRegolamento = idUnivocoRegolamento;
	}

	public Date getDataRegolamento() {
		return dataRegolamento;
	}

	public void setDataRegolamento(Date dataRegolamento) {
		this.dataRegolamento = dataRegolamento;
	}

	public TipoMittenteEnum getTipoMittente() {
		return tipoMittente;
	}

	public void setTipoMittente(TipoMittenteEnum tipoMittente) {
		this.tipoMittente = tipoMittente;
	}

	public String getIdAndDesTipoMittente() {
		if (tipoMittente != null)
			return tipoMittente.getId() + " - " + tipoMittente.getDes();
		else
			return "";
	}

	public String getCodIdUnivocoMittente() {
		return codIdUnivocoMittente;
	}

	public void setCodIdUnivocoMittente(String codIdUnivocoMittente) {
		this.codIdUnivocoMittente = codIdUnivocoMittente;
	}

	public String getDenominazioneMittente() {
		return denominazioneMittente;
	}

	public void setDenominazioneMittente(String denominazioneMittente) {
		this.denominazioneMittente = denominazioneMittente;
	}

	public String getCodBicPsp() {
		return codBicPsp;
	}

	public void setCodBicPsp(String codBicPsp) {
		this.codBicPsp = codBicPsp;
	}

    public String getIuv () {
        return iuv;
    }

    
    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }
    
    public String getCognome() {
        return cognome;
    }

    
    public void setCognome ( String cognome ) {
        this.cognome = cognome;
    }

    
    public String getPosizioneDebitoria () {
        return posizioneDebitoria;
    }

    
    public void setPosizioneDebitoria ( String posizioneDebitoria ) {
        this.posizioneDebitoria = posizioneDebitoria;
    }

    
    public BigDecimal getImportoPagato () {
        return importoPagato;
    }

    
    public void setImportoPagato ( BigDecimal importoPagato ) {
        this.importoPagato = importoPagato;
    }

    
    public Date getDataEsitoPagamento () {
        return dataEsitoPagamento;
    }

    
    public void setDataEsitoPagamento ( Date dataEsitoPagamento ) {
        this.dataEsitoPagamento = dataEsitoPagamento;
    }

    
    public String getCausaleVersamento () {
        return causaleVersamento;
    }

    
    public void setCausaleVersamento ( String causaleVersamento ) {
        this.causaleVersamento = causaleVersamento;
    }

    
    public String getCfPivaSoggettoDebitore () {
        return cfPivaSoggettoDebitore;
    }

    
    public void setCfPivaSoggettoDebitore ( String cfPivaSoggettoDebitore ) {
        this.cfPivaSoggettoDebitore = cfPivaSoggettoDebitore;
    }

    public Long getIdNotificaPagamento () {
        return idNotificaPagamento;
    }

    
    public void setIdNotificaPagamento ( Long idNotificaPagamento ) {
        this.idNotificaPagamento = idNotificaPagamento;
    }

    public String getRevoca () {
        return revoca;
    }

    
    public void setRevoca ( String revoca ) {
        this.revoca = revoca;
    }

    public Boolean getFlagPrimario () {
        return flagPrimario;
    }

    public void setFlagPrimario ( Boolean flagPrimario ) {
        this.flagPrimario = flagPrimario;
    }

    @Override
	public String toString() {
		return "{ " + toSuperString() + " }";
	}

	@Override
	public String toSuperString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("id:").append(getId()).append(COMMA);
		s.append("tipoFlusso:").append(tipoFlusso).append(COMMA);
		s.append("desTipoFlusso:").append(quote(desTipoFlusso)).append(COMMA);
		s.append("statoFlusso:").append(statoFlusso).append(COMMA);
		s.append("desStatoFlusso:").append(quote(desStatoFlusso)).append(COMMA);
		s.append("idMessaggio:").append(quote(idMessaggio)).append(COMMA);
		s.append("codFiscaleEnte:").append(quote(codFiscaleEnte)).append(COMMA);
		s.append("denominazioneEnte:").append(quote(denominazioneEnte)).append(COMMA);
		s.append("codVersamento:").append(quote(codVersamento)).append(COMMA);
		s.append("desCodVersamento:").append(quote(desCodVersamento)).append(COMMA);
		s.append("numeroElementi:").append(numeroElementi).append(COMMA);
		s.append("importoTotale:").append(importoTotale).append(COMMA);
		s.append("pagamentiSpontanei:").append(pagamentiSpontanei).append(COMMA);
		s.append("dataInserimento:").append(date2strdatetime(dataInserimento)).append(COMMA);
		s.append("dataUltimaVariazione:").append(date2strdatetime(dataUltimaVariazione)).append(COMMA);
		s.append("utenteUltimaVariazione:").append(quote(utenteUltimaVariazione)).append(COMMA);
		s.append("codEsito:").append(quote(codEsito)).append(COMMA);
		s.append("detEsito:").append(quote(detEsito)).append(COMMA);
		s.append("statoFlussoAggregato:").append(quote(statoFlussoAggregato)).append(COMMA);
		s.append("datiAggiuntiviStato:").append(quote(datiAggiuntiviStato)).append(COMMA);
		s.append("dataPerInserimentoInizioValidita").append(date2strdatetime(dataPerInserimentoInizioValidita)).append(COMMA);
		s.append("dataPerInserimentoFineValidita").append(date2strdatetime(dataPerInserimentoFineValidita)).append(COMMA);
		s.append("codIdUnivocoMittente:").append(quote(codIdUnivocoMittente)).append(COMMA);
		s.append("idFlussoRendicontazione:").append(quote(idFlussoRendicontazione)).append(COMMA);
		s.append("dataOraCreazioneFlusso:").append(date2strdatetime(dataOraCreazioneFlusso)).append(COMMA);
		s.append("idUnivocoRegolamento:").append(idUnivocoRegolamento).append(COMMA);
		s.append("dataRegolamento:").append(date2strdatetime(dataRegolamento)).append(COMMA);
		s.append("tipoMittente:").append(tipoMittente).append(COMMA);
		s.append("codIdUnivocoMittente:").append(codIdUnivocoMittente).append(COMMA);
		s.append("denominazioneMittente:").append(quote(denominazioneMittente)).append(COMMA);
        		
	    //--------------------------------//
	    //RDI-048 - START
	    //--------------------------------//    
		s.append("cognome:").append(quote(cognome)).append(COMMA);
        s.append("idNotificaPagamento:").append(idNotificaPagamento).append(COMMA);
        s.append("iuv:").append(quote(iuv)).append(COMMA);
        s.append("posizioneDebitoria:").append(quote(posizioneDebitoria)).append(COMMA);
        s.append("importoPagato:").append((importoPagato)).append(COMMA);
        s.append("dataEsitoPagamento:").append(date2strdatetime(dataEsitoPagamento)).append(COMMA);
        s.append("causaleVersamento:").append(quote(causaleVersamento)).append(COMMA);
        s.append("cfPivaSoggettoDebitore:").append(quote(cfPivaSoggettoDebitore)).append(COMMA);
        //--------------------------------//
        //RDI-048 - STOP
        //--------------------------------//    
        //<!-- CSI_PAG-184 -->
        s.append("revoca:").append(quote(revoca)).append(COMMA);
        //--------------------------------//
        
		s.append("codBicPsp:").append(quote(codBicPsp));
		return s.toString();
	}

}
