/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReportNodoSpc implements Serializable {

	private static final long serialVersionUID = -5337439484416182409L;
	
	public static final String ID_PSP = "id_psp";
	public static final String ID_CANALE = "id_canale";
	public static final String RAGIONE_SOCIALE = "ragionesociale";
	public static final String DESCRIZIONE_SERVIZIO = "descrizioneservizio";
	public static final String TIPO_VERSAMENTO = "tipoversamento";
	public static final String CONDIZIONI_ECONOMICHE_MASSIME = "condizionieconomichemassime";
	public static final String NUM_TRANSAZIONI = "num_transazioni";
	public static final String TRANSATO = "transato";
	
	
	private String idPsp;
	private String idCanale;
	private String ragioneSociale;
	private String descrizioneServizio;
	private String tipoVersamento;
	private String condizioniEconomicheMassime;
	private Long numTransazioni;
	private BigDecimal transato;
	
	/**
	 * @return the idPsp
	 */
	public String getIdPsp() {
		return idPsp;
	}
	/**
	 * @param idPsp the idPsp to set
	 */
	public void setIdPsp(String idPsp) {
		this.idPsp = idPsp;
	}
	/**
	 * @return the idCanale
	 */
	public String getIdCanale() {
		return idCanale;
	}
	/**
	 * @param idCanale the idCanale to set
	 */
	public void setIdCanale(String idCanale) {
		this.idCanale = idCanale;
	}
	/**
	 * @return the ragioneSociale
	 */
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	/**
	 * @param ragioneSociale the ragioneSociale to set
	 */
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	/**
	 * @return the descrizioneServizio
	 */
	public String getDescrizioneServizio() {
		return descrizioneServizio;
	}
	/**
	 * @param descrizioneServizio the descrizioneServizio to set
	 */
	public void setDescrizioneServizio(String descrizioneServizio) {
		this.descrizioneServizio = descrizioneServizio;
	}
	/**
	 * @return the tipoVersamento
	 */
	public String getTipoVersamento() {
		return tipoVersamento;
	}
	/**
	 * @param tipoVersamento the tipoVersamento to set
	 */
	public void setTipoVersamento(String tipoVersamento) {
		this.tipoVersamento = tipoVersamento;
	}
	/**
	 * @return the condizioniEconomicheMassime
	 */
	public String getCondizioniEconomicheMassime() {
		return condizioniEconomicheMassime;
	}
	/**
	 * @param condizioniEconomicheMassime the condizioniEconomicheMassime to set
	 */
	public void setCondizioniEconomicheMassime(String condizioniEconomicheMassime) {
		this.condizioniEconomicheMassime = condizioniEconomicheMassime;
	}
	/**
	 * @return the numTransazioni
	 */
	public Long getNumTransazioni() {
		return numTransazioni;
	}
	/**
	 * @param numTransazioni the numTransazioni to set
	 */
	public void setNumTransazioni(Long numTransazioni) {
		this.numTransazioni = numTransazioni;
	}
	/**
	 * @return the transato
	 */
	public BigDecimal getTransato() {
		return transato;
	}
	/**
	 * @param transato the transato to set
	 */
	public void setTransato(BigDecimal transato) {
		this.transato = transato;
	}	
}
