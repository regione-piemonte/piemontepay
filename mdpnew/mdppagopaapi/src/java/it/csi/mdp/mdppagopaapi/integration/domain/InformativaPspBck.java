/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the informativa_psp_bck database table.
 * 
 */
@Entity
@Table(name="informativa_psp_bck")
@NamedQuery(name="InformativaPspBck.findAll", query="SELECT i FROM InformativaPspBck i")
public class InformativaPspBck implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INFORMATIVA_PSP_BCK_IDINFORMATIVAPSP_GENERATOR", sequenceName="INFORMATIVA_PSP_BCK_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INFORMATIVA_PSP_BCK_IDINFORMATIVAPSP_GENERATOR")
	private long idinformativapsp;

	private String condizionieconomichemassime;

	private String datainiziovalidita;

	@Temporal(TemporalType.DATE)
	private Date datainserimento;

	private String datapubblicazione;

	private String descrizioneservizio;

	private String disponibilitaservizio;

	private String identificativocanale;

	private String identificativoflusso;

	private String identificativointermediario;

	private String identificativopsp;

	private BigDecimal modellopagamento;

	private BigDecimal ordinamento;

	private String origine;

	private BigDecimal priorita;

	private String ragionesociale;

	private String statoinserimento;

	private BigDecimal stornopagamento;

	private String tipoversamento;

	private String urlinformazionicanale;

	private String urlinformazionipsp;

	public InformativaPspBck() {
	}

	public long getIdinformativapsp() {
		return this.idinformativapsp;
	}

	public void setIdinformativapsp(long idinformativapsp) {
		this.idinformativapsp = idinformativapsp;
	}

	public String getCondizionieconomichemassime() {
		return this.condizionieconomichemassime;
	}

	public void setCondizionieconomichemassime(String condizionieconomichemassime) {
		this.condizionieconomichemassime = condizionieconomichemassime;
	}

	public String getDatainiziovalidita() {
		return this.datainiziovalidita;
	}

	public void setDatainiziovalidita(String datainiziovalidita) {
		this.datainiziovalidita = datainiziovalidita;
	}

	public Date getDatainserimento() {
		return this.datainserimento;
	}

	public void setDatainserimento(Date datainserimento) {
		this.datainserimento = datainserimento;
	}

	public String getDatapubblicazione() {
		return this.datapubblicazione;
	}

	public void setDatapubblicazione(String datapubblicazione) {
		this.datapubblicazione = datapubblicazione;
	}

	public String getDescrizioneservizio() {
		return this.descrizioneservizio;
	}

	public void setDescrizioneservizio(String descrizioneservizio) {
		this.descrizioneservizio = descrizioneservizio;
	}

	public String getDisponibilitaservizio() {
		return this.disponibilitaservizio;
	}

	public void setDisponibilitaservizio(String disponibilitaservizio) {
		this.disponibilitaservizio = disponibilitaservizio;
	}

	public String getIdentificativocanale() {
		return this.identificativocanale;
	}

	public void setIdentificativocanale(String identificativocanale) {
		this.identificativocanale = identificativocanale;
	}

	public String getIdentificativoflusso() {
		return this.identificativoflusso;
	}

	public void setIdentificativoflusso(String identificativoflusso) {
		this.identificativoflusso = identificativoflusso;
	}

	public String getIdentificativointermediario() {
		return this.identificativointermediario;
	}

	public void setIdentificativointermediario(String identificativointermediario) {
		this.identificativointermediario = identificativointermediario;
	}

	public String getIdentificativopsp() {
		return this.identificativopsp;
	}

	public void setIdentificativopsp(String identificativopsp) {
		this.identificativopsp = identificativopsp;
	}

	public BigDecimal getModellopagamento() {
		return this.modellopagamento;
	}

	public void setModellopagamento(BigDecimal modellopagamento) {
		this.modellopagamento = modellopagamento;
	}

	public BigDecimal getOrdinamento() {
		return this.ordinamento;
	}

	public void setOrdinamento(BigDecimal ordinamento) {
		this.ordinamento = ordinamento;
	}

	public String getOrigine() {
		return this.origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public BigDecimal getPriorita() {
		return this.priorita;
	}

	public void setPriorita(BigDecimal priorita) {
		this.priorita = priorita;
	}

	public String getRagionesociale() {
		return this.ragionesociale;
	}

	public void setRagionesociale(String ragionesociale) {
		this.ragionesociale = ragionesociale;
	}

	public String getStatoinserimento() {
		return this.statoinserimento;
	}

	public void setStatoinserimento(String statoinserimento) {
		this.statoinserimento = statoinserimento;
	}

	public BigDecimal getStornopagamento() {
		return this.stornopagamento;
	}

	public void setStornopagamento(BigDecimal stornopagamento) {
		this.stornopagamento = stornopagamento;
	}

	public String getTipoversamento() {
		return this.tipoversamento;
	}

	public void setTipoversamento(String tipoversamento) {
		this.tipoversamento = tipoversamento;
	}

	public String getUrlinformazionicanale() {
		return this.urlinformazionicanale;
	}

	public void setUrlinformazionicanale(String urlinformazionicanale) {
		this.urlinformazionicanale = urlinformazionicanale;
	}

	public String getUrlinformazionipsp() {
		return this.urlinformazionipsp;
	}

	public void setUrlinformazionipsp(String urlinformazionipsp) {
		this.urlinformazionipsp = urlinformazionipsp;
	}

}
