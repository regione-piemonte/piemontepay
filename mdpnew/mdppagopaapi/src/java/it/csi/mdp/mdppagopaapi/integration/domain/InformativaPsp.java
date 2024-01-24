/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the informativa_psp database table.
 * 
 */
@Entity
@Table(name="informativa_psp")
@NamedQuery(name="InformativaPsp.findAll", query="SELECT i FROM InformativaPsp i")
public class InformativaPsp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INFORMATIVA_PSP_IDINFORMATIVAPSP_GENERATOR", sequenceName="INFORMATIVA_PSP_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INFORMATIVA_PSP_IDINFORMATIVAPSP_GENERATOR")
	private Integer idinformativapsp;

	private String condizionieconomichemassime;

	private Timestamp datainiziovalidita;

	private Timestamp datainserimento;

	private Timestamp datapubblicazione;

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

	//bi-directional many-to-one association to FasciaCostoServizio
	@OneToMany(mappedBy="informativaPsp")
	private List<FasciaCostoServizio> fasciaCostoServizios;

	//bi-directional many-to-one association to InformazioniServizio
	@OneToMany(mappedBy="informativaPsp")
	private List<InformazioniServizio> informazioniServizios;

	public InformativaPsp() {
	}

	public Integer getIdinformativapsp() {
		return this.idinformativapsp;
	}

	public void setIdinformativapsp(Integer idinformativapsp) {
		this.idinformativapsp = idinformativapsp;
	}

	public String getCondizionieconomichemassime() {
		return this.condizionieconomichemassime;
	}

	public void setCondizionieconomichemassime(String condizionieconomichemassime) {
		this.condizionieconomichemassime = condizionieconomichemassime;
	}

	public Timestamp getDatainiziovalidita() {
		return this.datainiziovalidita;
	}

	public void setDatainiziovalidita(Timestamp datainiziovalidita) {
		this.datainiziovalidita = datainiziovalidita;
	}

	public Timestamp getDatainserimento() {
		return this.datainserimento;
	}

	public void setDatainserimento(Timestamp datainserimento) {
		this.datainserimento = datainserimento;
	}

	public Timestamp getDatapubblicazione() {
		return this.datapubblicazione;
	}

	public void setDatapubblicazione(Timestamp datapubblicazione) {
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

	public List<FasciaCostoServizio> getFasciaCostoServizios() {
		return this.fasciaCostoServizios;
	}

	public void setFasciaCostoServizios(List<FasciaCostoServizio> fasciaCostoServizios) {
		this.fasciaCostoServizios = fasciaCostoServizios;
	}

	public FasciaCostoServizio addFasciaCostoServizio(FasciaCostoServizio fasciaCostoServizio) {
		getFasciaCostoServizios().add(fasciaCostoServizio);
		fasciaCostoServizio.setInformativaPsp(this);

		return fasciaCostoServizio;
	}

	public FasciaCostoServizio removeFasciaCostoServizio(FasciaCostoServizio fasciaCostoServizio) {
		getFasciaCostoServizios().remove(fasciaCostoServizio);
		fasciaCostoServizio.setInformativaPsp(null);

		return fasciaCostoServizio;
	}

	public List<InformazioniServizio> getInformazioniServizios() {
		return this.informazioniServizios;
	}

	public void setInformazioniServizios(List<InformazioniServizio> informazioniServizios) {
		this.informazioniServizios = informazioniServizios;
	}

	public InformazioniServizio addInformazioniServizio(InformazioniServizio informazioniServizio) {
		getInformazioniServizios().add(informazioniServizio);
		informazioniServizio.setInformativaPsp(this);

		return informazioniServizio;
	}

	public InformazioniServizio removeInformazioniServizio(InformazioniServizio informazioniServizio) {
		getInformazioniServizios().remove(informazioniServizio);
		informazioniServizio.setInformativaPsp(null);

		return informazioniServizio;
	}

}
