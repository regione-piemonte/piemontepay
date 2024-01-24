/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/** dto facade <-> business <-> persistence */
public class PosizioneDebitoriaDto extends PosizioneDebitoriaLightDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idFlusso;

	// dati della posizione debitoria
	private String idPosizioneDebitoriaEsterna;

	private Integer annoRiferimento;

	private Date dataInizioValidita;

	private Date dataFineValidita;

	private String desRata;

	private String notePerPagatore;

	private String codAvviso;

	private String codEsito;

	private String detEsito;

	private BigDecimal importoPrincipale;

	private BigDecimal importoSecondarioAltroEnte;

	// dati dei componenti importo
	private List<ComponenteImportoDto> componenteImportoDtoList;

	private List<RiferimentoPagamentoDto> riferimentoPagamentoDtoList;

	public PosizioneDebitoriaDto () {
		super ();
	}

	public PosizioneDebitoriaDto ( Long id ) {
		super ( id );
	}

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

	public BigDecimal getImportoPrincipale () {
		return importoPrincipale;
	}

	public void setImportoPrincipale ( BigDecimal importoPrincipale ) {
		this.importoPrincipale = importoPrincipale;
	}

	public BigDecimal getImportoSecondarioAltroEnte () {
		return importoSecondarioAltroEnte;
	}

	public void setImportoSecondarioAltroEnte ( BigDecimal importoSecondarioAltroEnte ) {
		this.importoSecondarioAltroEnte = importoSecondarioAltroEnte;
	}

	@Override
	public String toString () {
		return "PosizioneDebitoriaDto [idFlusso=" + idFlusso + ", idPosizioneDebitoriaEsterna=" + idPosizioneDebitoriaEsterna + ", annoRiferimento="
						+ annoRiferimento + ", dataInizioValidita=" + dataInizioValidita + ", dataFineValidita=" + dataFineValidita + ", desRata=" + desRata
						+ ", notePerPagatore=" + notePerPagatore + ", codAvviso=" + codAvviso + ", codEsito=" + codEsito + ", detEsito=" + detEsito + ", importoPrincipale="
						+ importoPrincipale + ", importoSecondarioAltroEnte=" + importoSecondarioAltroEnte + ", componenteImportoDtoList=" + componenteImportoDtoList
						+ ", riferimentoPagamentoDtoList=" + riferimentoPagamentoDtoList + "]";
	}


}
