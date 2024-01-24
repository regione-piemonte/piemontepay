/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.dto;

import it.csi.mdp.mdpetl.util.LogUtil;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class FlussoRendicontazione extends BaseDTO {

	private static final long serialVersionUID = 1046749593678092606L;

	LogUtil log = new LogUtil(FlussoRendicontazione.class);

	private Integer id;
	private String identificativoFlusso;
	private String identificativoPSP;
	private String versioneOggetto;
	private Timestamp dataOraFlusso;
	private String identificativoUnivocoRegolamento;
	private Timestamp dataRegolamento;
	private String identificativoIstitutoMittente;
	private String denominazioneMittente;
	private String identificativoIstitutoRicevente;
	private String denominazioneRicevente;
	private Integer numeroTotalePagamenti;
	private BigDecimal importoTotalePagamenti;
	private Timestamp dataInserimento;
	private Timestamp dataModifica;
	private String codiceBicBancaDiRiversamento;
	private Integer statoInvioFlussoBase;
	private Integer statoInvioFlussoEsteso;
	
	private String xml;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIdentificativoFlusso() {
		return identificativoFlusso;
	}
	public void setIdentificativoFlusso(String identificativoFlusso) {
		this.identificativoFlusso = identificativoFlusso;
	}
	public String getIdentificativoPSP() {
		return identificativoPSP;
	}
	public void setIdentificativoPSP(String identificativoPSP) {
		this.identificativoPSP = identificativoPSP;
	}
	public String getVersioneOggetto() {
		return versioneOggetto;
	}
	public void setVersioneOggetto(String versioneOggetto) {
		this.versioneOggetto = versioneOggetto;
	}
	public Timestamp getDataOraFlusso() {
		return dataOraFlusso;
	}
	public void setDataOraFlusso(Timestamp dataOraFlusso) {
		this.dataOraFlusso = dataOraFlusso;
	}
	public String getIdentificativoUnivocoRegolamento() {
		return identificativoUnivocoRegolamento;
	}
	public void setIdentificativoUnivocoRegolamento(String identificativoUnivocoRegolamento) {
		this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
	}
	public Timestamp getDataRegolamento() {
		return dataRegolamento;
	}
	public void setDataRegolamento(Timestamp dataRegolamento) {
		this.dataRegolamento = dataRegolamento;
	}
	public String getIdentificativoIstitutoMittente() {
		return identificativoIstitutoMittente;
	}
	public void setIdentificativoIstitutoMittente(String identificativoIstitutoMittente) {
		this.identificativoIstitutoMittente = identificativoIstitutoMittente;
	}
	public String getIdentificativoIstitutoRicevente() {
		return identificativoIstitutoRicevente;
	}
	public void setIdentificativoIstitutoRicevente(String identificativoIstitutoRicevente) {
		this.identificativoIstitutoRicevente = identificativoIstitutoRicevente;
	}
	public Integer getNumeroTotalePagamenti() {
		return numeroTotalePagamenti;
	}
	public void setNumeroTotalePagamenti(Integer numeroTotalePagamenti) {
		this.numeroTotalePagamenti = numeroTotalePagamenti;
	}
	public BigDecimal getImportoTotalePagamenti() {
		return importoTotalePagamenti;
	}
	public void setImportoTotalePagamenti(BigDecimal importoTotalePagamenti) {
		this.importoTotalePagamenti = importoTotalePagamenti;
	}
	public Timestamp getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(Timestamp dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public Timestamp getDataModifica() {
		return dataModifica;
	}
	public void setDataModifica(Timestamp dataModifica) {
		this.dataModifica = dataModifica;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	public String getDenominazioneMittente() {
		return denominazioneMittente;
	}
	public void setDenominazioneMittente(String denominazioneMittente) {
		this.denominazioneMittente = denominazioneMittente;
	}
	public String getDenominazioneRicevente() {
		return denominazioneRicevente;
	}
	public void setDenominazioneRicevente(String denominazioneRicevente) {
		this.denominazioneRicevente = denominazioneRicevente;
	}
	public String getCodiceBicBancaDiRiversamento() {
		return codiceBicBancaDiRiversamento;
	}
	public void setCodiceBicBancaDiRiversamento(String codiceBicBancaDiRiversamento) {
		this.codiceBicBancaDiRiversamento = codiceBicBancaDiRiversamento;
	}
    
    public Integer getStatoInvioFlussoBase () {
        return statoInvioFlussoBase;
    }
    
    public void setStatoInvioFlussoBase ( Integer statoInvioFlussoBase ) {
        this.statoInvioFlussoBase = statoInvioFlussoBase;
    }
    
    public Integer getStatoInvioFlussoEsteso () {
        return statoInvioFlussoEsteso;
    }
    
    public void setStatoInvioFlussoEsteso ( Integer statoInvioFlussoEsteso ) {
        this.statoInvioFlussoEsteso = statoInvioFlussoEsteso;
    }
	
	
	
}
