/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.codiceversamento;

import java.io.Serializable;
import java.util.List;


public class RicercaCodiceVersamentoOutputDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String codice;

	private String descrizione;

	private String codiceTematica;

	private String descrizioneTematica;

	private String codiceMacrotipo;

	private String descrizioneMacrotipo;

	private Long idVoceEntrata;

	private String codiceVoceEntrata;

	private String descrizioneVoceEntrata;

	private String codiceTipoPagamento;

	private String descrizioneTipoPagamento;

	private String iban;

	private String bic;

	private String email;

	private Boolean flagInvioFlussi;

	private Boolean tematicaEsclusa;

	private String ibanAppoggio;

	private String bicAppoggio;

	private Boolean flagPresenzaBollettinoPostale;

	private Boolean ibanPostale;

	private Boolean ibanAppoggioPostale;

	private Boolean fattura;

	private Long idPadre;

	private Boolean flagMbSecondario;

	private Boolean flagMbPrimario;

	/**
	 * @return the ibanAppoggio
	 */
	 public String getIbanAppoggio() {
		return ibanAppoggio;
	}

	 /**
	  * @param ibanAppoggio the ibanAppoggio to set
	  */
	 public void setIbanAppoggio(String ibanAppoggio) {
		 this.ibanAppoggio = ibanAppoggio;
	 }

	 /**
	  * @return the bicAppoggio
	  */
	 public String getBicAppoggio() {
		 return bicAppoggio;
	 }

	 /**
	  * @param bicAppoggio the bicAppoggio to set
	  */
	 public void setBicAppoggio(String bicAppoggio) {
		 this.bicAppoggio = bicAppoggio;
	 }


	 private List<RicercaCodiceVersamentoOutputDto> codiciVersamentoCollegati;


	 @Override
	 public String toString () {
		 return "RicercaCodiceVersamentoOutputDto [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", codiceTematica=" + codiceTematica
						 + ", descrizioneTematica=" + descrizioneTematica + ", codiceMacrotipo=" + codiceMacrotipo + ", descrizioneMacrotipo=" + descrizioneMacrotipo
						 + ", idVoceEntrata=" + idVoceEntrata + ", codiceVoceEntrata=" + codiceVoceEntrata + ", descrizioneVoceEntrata=" + descrizioneVoceEntrata
						 + ", codiceTipoPagamento=" + codiceTipoPagamento + ", descrizioneTipoPagamento=" + descrizioneTipoPagamento + ", iban=" + iban + ", bic=" + bic
						 + ", email=" + email + ", flagInvioFlussi=" + flagInvioFlussi + ", tematicaEsclusa=" + tematicaEsclusa + ", codiciVersamentoCollegati="
						 + codiciVersamentoCollegati + "]";
	 }

	 public List<RicercaCodiceVersamentoOutputDto> getCodiciVersamentoCollegati () {
		 return codiciVersamentoCollegati;
	 }

	 public void setCodiciVersamentoCollegati ( List<RicercaCodiceVersamentoOutputDto> codiciVersamentoCollegati ) {
		 this.codiciVersamentoCollegati = codiciVersamentoCollegati;
	 }

	 public String getBic () {
		 return bic;
	 }

	 public void setBic ( String bic ) {
		 this.bic = bic;
	 }

	 public String getEmail () {
		 return email;
	 }

	 public void setEmail ( String email ) {
		 this.email = email;
	 }

	 public Boolean getFlagInvioFlussi () {
		 return flagInvioFlussi;
	 }

	 public void setFlagInvioFlussi ( Boolean flagInvioFlussi ) {
		 this.flagInvioFlussi = flagInvioFlussi;
	 }

	 public String getCodice () {
		 return codice;
	 }

	 public void setCodice ( String codice ) {
		 this.codice = codice;
	 }

	 public String getCodiceVoceEntrata () {
		 return codiceVoceEntrata;
	 }

	 public void setCodiceVoceEntrata ( String codiceVoceEntrata ) {
		 this.codiceVoceEntrata = codiceVoceEntrata;
	 }

	 public Long getId () {
		 return id;
	 }

	 public void setId ( Long id ) {
		 this.id = id;
	 }

	 public String getDescrizione () {
		 return descrizione;
	 }

	 public void setDescrizione ( String descrizione ) {
		 this.descrizione = descrizione;
	 }

	 public String getCodiceTematica () {
		 return codiceTematica;
	 }

	 public void setCodiceTematica ( String codiceTematica ) {
		 this.codiceTematica = codiceTematica;
	 }

	 public String getDescrizioneTematica () {
		 return descrizioneTematica;
	 }

	 public void setDescrizioneTematica ( String descrizioneTematica ) {
		 this.descrizioneTematica = descrizioneTematica;
	 }

	 public String getCodiceMacrotipo () {
		 return codiceMacrotipo;
	 }

	 public void setCodiceMacrotipo ( String codiceMacrotipo ) {
		 this.codiceMacrotipo = codiceMacrotipo;
	 }

	 public String getDescrizioneMacrotipo () {
		 return descrizioneMacrotipo;
	 }

	 public void setDescrizioneMacrotipo ( String descrizioneMacrotipo ) {
		 this.descrizioneMacrotipo = descrizioneMacrotipo;
	 }

	 public Long getIdVoceEntrata () {
		 return idVoceEntrata;
	 }

	 public void setIdVoceEntrata ( Long idVoceEntrata ) {
		 this.idVoceEntrata = idVoceEntrata;
	 }

	 public String getDescrizioneVoceEntrata () {
		 return descrizioneVoceEntrata;
	 }

	 public void setDescrizioneVoceEntrata ( String descrizioneVoceEntrata ) {
		 this.descrizioneVoceEntrata = descrizioneVoceEntrata;
	 }

	 public String getCodiceTipoPagamento () {
		 return codiceTipoPagamento;
	 }

	 public void setCodiceTipoPagamento ( String codiceTipoPagamento ) {
		 this.codiceTipoPagamento = codiceTipoPagamento;
	 }

	 public String getDescrizioneTipoPagamento () {
		 return descrizioneTipoPagamento;
	 }

	 public void setDescrizioneTipoPagamento ( String descrizioneTipoPagamento ) {
		 this.descrizioneTipoPagamento = descrizioneTipoPagamento;
	 }

	 public String getIban () {
		 return iban;
	 }

	 public void setIban ( String iban ) {
		 this.iban = iban;
	 }


	 public Boolean getTematicaEsclusa () {
		 return tematicaEsclusa;
	 }


	 public void setTematicaEsclusa ( Boolean tematicaEsclusa ) {
		 this.tematicaEsclusa = tematicaEsclusa;
	 }

	 public Boolean getFlagPresenzaBollettinoPostale () {
		 return flagPresenzaBollettinoPostale;
	 }

	 public void setFlagPresenzaBollettinoPostale ( Boolean flagPresenzaBollettinoPostale ) {
		 this.flagPresenzaBollettinoPostale = flagPresenzaBollettinoPostale;
	 }

	 /**
	  * @return the ibanPostale
	  */
	 public Boolean getIbanPostale() {
		 return ibanPostale;
	 }

	 /**
	  * @param ibanPostale the ibanPostale to set
	  */
	 public void setIbanPostale(Boolean ibanPostale) {
		 this.ibanPostale = ibanPostale;
	 }

	 /**
	  * @return the ibanAppoggioPostale
	  */
	 public Boolean getIbanAppoggioPostale() {
		 return ibanAppoggioPostale;
	 }

	 /**
	  * @param ibanAppoggioPostale the ibanAppoggioPostale to set
	  */
	 public void setIbanAppoggioPostale(Boolean ibanAppoggioPostale) {
		 this.ibanAppoggioPostale = ibanAppoggioPostale;
	 }

	 public Boolean getFattura () {
		 return fattura;
	 }

	 public void setFattura ( Boolean fattura ) {
		 this.fattura = fattura;
	 }

	 public Long getIdPadre() {
		 return idPadre;
	 }

	 public void setIdPadre(Long idPadre) {
		 this.idPadre = idPadre;
	 }


	public Boolean getFlagMbSecondario () {
		 return flagMbSecondario;
	 }

	public void setFlagMbSecondario ( Boolean flagMbSecondario ) {
		 this.flagMbSecondario = flagMbSecondario;
	 }

	public Boolean getFlagMbPrimario () {
		 return flagMbPrimario;
	 }

	public void setFlagMbPrimario ( Boolean flagMbPrimario ) {
		 this.flagMbPrimario = flagMbPrimario;
	 }
}
