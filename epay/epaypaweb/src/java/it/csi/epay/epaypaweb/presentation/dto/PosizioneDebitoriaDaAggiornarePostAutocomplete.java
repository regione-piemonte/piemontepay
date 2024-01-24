/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.dto;

import it.csi.epay.epaypaweb.dto.ComponenteImportoDto;
import it.csi.epay.epaypaweb.dto.RiferimentoPagamentoDto;
import it.csi.epay.epaypaweb.dto.SoggettoAnagraficoDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class PosizioneDebitoriaDaAggiornarePostAutocomplete implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idFlusso;

	private String idPosizioneDebitoriaEsterna;

	private Integer annoRiferimento;

	private Date dataInizioValidita;

	private Date dataFineValidita;

	private String motivazione;

	private String desRata;

	private String notePerPagatore;

	private String codAvviso;

	private String codEsito;

	private String detEsito;

	private List<ComponenteImportoDto> componenteImportoDtoList;

	private List<RiferimentoPagamentoDto> riferimentoPagamentoDtoList;

	private String iuv;

	private BigDecimal importoTotale;

	private String desCausaleVersamento;

	private Date dataScadenza;

	private SoggettoAnagraficoDto soggettoDebitore;

	private BigDecimal importoPrincipale;

	private BigDecimal importoSecondario;

	private boolean isUpdatable;

	private String stato;

	private String codDescription;

	public Long getIdFlusso () {
		return idFlusso;
	}

	public void setIdFlusso ( Long idFlusso ) {
		this.idFlusso = idFlusso;
	}

	public String getIdPosizioneDebitoriaEsterna () {
		return idPosizioneDebitoriaEsterna;
	}

	public void setIdPosizioneDebitoriaEsterna ( String idPosizioneDebitoriaEsterna ) {
		this.idPosizioneDebitoriaEsterna = idPosizioneDebitoriaEsterna;
	}

	public Integer getAnnoRiferimento () {
		return annoRiferimento;
	}

	public void setAnnoRiferimento ( Integer annoRiferimento ) {
		this.annoRiferimento = annoRiferimento;
	}

	public Date getDataInizioValidita () {
		return dataInizioValidita;
	}

	public void setDataInizioValidita ( Date dataInizioValidita ) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Date getDataFineValidita () {
		return dataFineValidita;
	}

	public void setDataFineValidita ( Date dataFineValidita ) {
		this.dataFineValidita = dataFineValidita;
	}

	public String getDesRata () {
		return desRata;
	}

	public void setDesRata ( String desRata ) {
		this.desRata = desRata;
	}

	public String getNotePerPagatore () {
		return notePerPagatore;
	}

	public void setNotePerPagatore ( String notePerPagatore ) {
		this.notePerPagatore = notePerPagatore;
	}

	public String getCodAvviso () {
		return codAvviso;
	}

	public void setCodAvviso ( String codAvviso ) {
		this.codAvviso = codAvviso;
	}

	public String getCodEsito () {
		return codEsito;
	}

	public void setCodEsito ( String codEsito ) {
		this.codEsito = codEsito;
	}

	public String getDetEsito () {
		return detEsito;
	}

	public void setDetEsito ( String detEsito ) {
		this.detEsito = detEsito;
	}

	public List<ComponenteImportoDto> getComponenteImportoDtoList () {
		return componenteImportoDtoList;
	}

	public void setComponenteImportoDtoList ( List<ComponenteImportoDto> componenteImportoDtoList ) {
		this.componenteImportoDtoList = componenteImportoDtoList;
	}

	public List<RiferimentoPagamentoDto> getRiferimentoPagamentoDtoList () {
		return riferimentoPagamentoDtoList;
	}

	public void setRiferimentoPagamentoDtoList ( List<RiferimentoPagamentoDto> riferimentoPagamentoDtoList ) {
		this.riferimentoPagamentoDtoList = riferimentoPagamentoDtoList;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public BigDecimal getImportoTotale () {
		return importoTotale;
	}

	public void setImportoTotale ( BigDecimal importoTotale ) {
		this.importoTotale = importoTotale;
	}

	public String getDesCausaleVersamento () {
		return desCausaleVersamento;
	}

	public void setDesCausaleVersamento ( String desCausaleVersamento ) {
		this.desCausaleVersamento = desCausaleVersamento;
	}

	public Date getDataScadenza () {
		return dataScadenza;
	}

	public void setDataScadenza ( Date dataScadenza ) {
		this.dataScadenza = dataScadenza;
	}

	public SoggettoAnagraficoDto getSoggettoDebitore () {
		return soggettoDebitore;
	}

	public void setSoggettoDebitore ( SoggettoAnagraficoDto soggettoDebitore ) {
		this.soggettoDebitore = soggettoDebitore;
	}

	public String getMotivazione () {
		return motivazione;
	}

	public void setMotivazione ( String motivazione ) {
		this.motivazione = motivazione;
	}

	public BigDecimal getImportoPrincipale () {
		return importoPrincipale;
	}

	public void setImportoPrincipale ( BigDecimal importoPrincipale ) {
		this.importoPrincipale = importoPrincipale;
	}

	public BigDecimal getImportoSecondario () {
		return importoSecondario;
	}

	public void setImportoSecondario ( BigDecimal importoSecondario ) {
		this.importoSecondario = importoSecondario;
	}

	public boolean isUpdatable () {
		return isUpdatable;
	}

	public void setUpdatable ( boolean updatable ) {
		isUpdatable = updatable;
	}

	public String getStato () {
		return stato;
	}

	public void setStato ( String stato ) {
		this.stato = stato;
	}

	public String getCodDescription () {
		return codDescription;
	}

	public void setCodDescription ( String codDescription ) {
		this.codDescription = codDescription;
	}

	@Override public String toString () {
		return "PosizioneDebitoriaDaAggiornarePostAutocomplete{" +
						"idFlusso=" + idFlusso +
						", idPosizioneDebitoriaEsterna='" + idPosizioneDebitoriaEsterna + '\'' +
						", annoRiferimento=" + annoRiferimento +
						", dataInizioValidita=" + dataInizioValidita +
						", dataFineValidita=" + dataFineValidita +
						", motivazione='" + motivazione + '\'' +
						", desRata='" + desRata + '\'' +
						", notePerPagatore='" + notePerPagatore + '\'' +
						", codAvviso='" + codAvviso + '\'' +
						", codEsito='" + codEsito + '\'' +
						", detEsito='" + detEsito + '\'' +
						", componenteImportoDtoList=" + componenteImportoDtoList +
						", riferimentoPagamentoDtoList=" + riferimentoPagamentoDtoList +
						", iuv='" + iuv + '\'' +
						", importoTotale=" + importoTotale +
						", desCausaleVersamento='" + desCausaleVersamento + '\'' +
						", dataScadenza=" + dataScadenza +
						", soggettoDebitore=" + soggettoDebitore +
						", importoPrincipale=" + importoPrincipale +
						", importoSecondario=" + importoSecondario +
						", isUpdatable=" + isUpdatable +
						", stato='" + stato + '\'' +
						", codDescription='" + codDescription + '\'' +
						'}';
	}
}
