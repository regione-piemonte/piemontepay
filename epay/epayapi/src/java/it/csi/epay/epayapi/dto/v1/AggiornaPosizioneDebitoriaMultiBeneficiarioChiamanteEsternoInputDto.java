/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.csi.epay.epayservices.model.ComponentiImporto;
import it.csi.epay.epayservices.model.ComponentiImportoSecondario;
import it.csi.epay.epayservices.model.PersonaFisica;
import it.csi.epay.epayservices.model.PersonaGiuridica;
import it.csi.epay.epayservices.model.SoggettoPagatore;
import it.csi.epay.epayservices.model.v1.AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@SuppressWarnings ( "unused" )
public class AggiornaPosizioneDebitoriaMultiBeneficiarioChiamanteEsternoInputDto implements Serializable {

	private static final long serialVersionUID = 3367072975682798811L;

	private Date timestampChiamata;

	private String ipChiamante;

	private String codiceChiamante;

	private Date dataScadenza;

	private Date dataInizioValidita;

	private Date dataFineValidita;

	@JsonProperty ( "importoTotale" )
	private BigDecimal importo;

	@JsonProperty ( "componentiPagamentoEntePrimario" )
	private List<ComponentiImporto> componentiImporto;

	private String causale;

	private String motivazione;

	protected String nome;

	protected String cognome;

	protected String ragioneSociale;

	protected String email;

	protected String codiceFiscalePartitaIVAPagatore;

	@JsonProperty ( "importoTotaleEntePrimario" )
	private BigDecimal importoPrincipale;

	@JsonProperty ( "importoTotaleEntiSecondari" )
	private BigDecimal importoSecondario;

	private String identificativoPagamento;

	private String notePerIlPagatore;

	@JsonProperty ( "componentiPagamentoEntiSecondari" )
	private List<ComponentiImportoSecondario> componentiImportoSecondario;
	

    private Boolean requiresCostUpdate;
    
   
    public Boolean getRequiresCostUpdate() {
		return requiresCostUpdate;
	}

	public void setRequiresCostUpdate(Boolean requiresCostUpdate) {
		this.requiresCostUpdate = requiresCostUpdate;
	}

	public Date getTimestampChiamata () {
		return timestampChiamata;
	}

	public void setTimestampChiamata ( Date timestampChiamata ) {
		this.timestampChiamata = timestampChiamata;
	}

	public String getIpChiamante () {
		return ipChiamante;
	}

	public void setIpChiamante ( String ipChiamante ) {
		this.ipChiamante = ipChiamante;
	}

	public String getCodiceChiamante () {
		return codiceChiamante;
	}

	public void setCodiceChiamante ( String codiceChiamante ) {
		this.codiceChiamante = codiceChiamante;
	}

	public Date getDataScadenza () {
		return dataScadenza;
	}

	public void setDataScadenza ( Date dataScadenza ) {
		this.dataScadenza = dataScadenza;
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

	public List<ComponentiImporto> getComponentiImporto () {
		return componentiImporto;
	}

	public void setComponentiImporto ( List<ComponentiImporto> componentiImporto ) {
		this.componentiImporto = componentiImporto;
	}

	public String getMotivazione () {
		return motivazione;
	}

	public void setMotivazione ( String motivazione ) {
		this.motivazione = motivazione;
	}

	/**
	 * @return the causale
	 */
	public String getCausale () {
		return causale;
	}

	/**
	 * @param causale the causale to set
	 */
	public void setCausale ( String causale ) {
		this.causale = causale;
	}

	/**
	 * @return the importo
	 */
	public BigDecimal getImporto () {
		return importo;
	}

	/**
	 * @param importo the importo to set
	 */
	public void setImporto ( BigDecimal importo ) {
		this.importo = importo;
	}

	/**
	 * @return the nome
	 */
	public String getNome () {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome ( String nome ) {
		this.nome = nome;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome () {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome ( String cognome ) {
		this.cognome = cognome;
	}

	/**
	 * @return the ragioneSociale
	 */
	public String getRagioneSociale () {
		return ragioneSociale;
	}

	/**
	 * @param ragioneSociale the ragioneSociale to set
	 */
	public void setRagioneSociale ( String ragioneSociale ) {
		this.ragioneSociale = ragioneSociale;
	}

	/**
	 * @return the email
	 */
	public String getEmail () {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail ( String email ) {
		this.email = email;
	}

	/**
	 * @return the codiceFiscalePartitaIVAPagatore
	 */
	public String getCodiceFiscalePartitaIVAPagatore () {
		return codiceFiscalePartitaIVAPagatore;
	}

	/**
	 * @param codiceFiscalePartitaIVAPagatore the codiceFiscalePartitaIVAPagatore to set
	 */
	public void setCodiceFiscalePartitaIVAPagatore ( String codiceFiscalePartitaIVAPagatore ) {
		this.codiceFiscalePartitaIVAPagatore = codiceFiscalePartitaIVAPagatore;
	}

	/**
	 * @return the importoPrincipale
	 */
	public BigDecimal getImportoPrincipale () {
		return importoPrincipale;
	}

	/**
	 * @param importoPrincipale the importoPrincipale to set
	 */
	public void setImportoPrincipale ( BigDecimal importoPrincipale ) {
		this.importoPrincipale = importoPrincipale;
	}

	/**
	 * @return the importoSecondario
	 */
	public BigDecimal getImportoSecondario () {
		return importoSecondario;
	}

	/**
	 * @param importoSecondario the importoSecondario to set
	 */
	public void setImportoSecondario ( BigDecimal importoSecondario ) {
		this.importoSecondario = importoSecondario;
	}

	/**
	 * @return the componentiImportoSecondario
	 */
	public List<ComponentiImportoSecondario> getComponentiImportoSecondario () {
		return componentiImportoSecondario;
	}

	/**
	 * @param componentiImportoSecondario the componentiImportoSecondario to set
	 */
	public void setComponentiImportoSecondario ( List<ComponentiImportoSecondario> componentiImportoSecondario ) {
		this.componentiImportoSecondario = componentiImportoSecondario;
	}

	public String getIdentificativoPagamento () {
		return identificativoPagamento;
	}

	public void setIdentificativoPagamento ( String identificativoPagamento ) {
		this.identificativoPagamento = identificativoPagamento;
	}

	public String getNotePerIlPagatore () {
		return notePerIlPagatore;
	}

	public void setNotePerIlPagatore ( String notePerIlPagatore ) {
		this.notePerIlPagatore = notePerIlPagatore;
	}

	public AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput getAggiornaPosizioneDebitoriaChiamanteEsternoInput ( String organization,
					String paymentType,
					String iuv ) {
		AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput input = new AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput ();
		input.setTimestampChiamata ( this.timestampChiamata );
		input.setIpChiamante ( this.ipChiamante );
		input.setCodiceChiamante ( this.codiceChiamante );
		input.setIuv ( iuv );
		input.setCodiceFiscaleEnte ( organization );
		input.setTipoPagamento ( paymentType );
		input.setDataScadenza ( this.dataScadenza );
		input.setDataInizioValidita ( this.dataInizioValidita );
		input.setDataFineValidita ( this.dataFineValidita );
		input.setImportoTotale ( this.importo );
		input.setComponentiImporto ( this.componentiImporto );
		input.setDescrizioneCausaleVersamento ( this.causale );
		input.setMotivazione ( this.motivazione );
		input.setSoggettoPagatore ( new SoggettoPagatore () );
		input.setImportoSecondario ( this.importoSecondario );
		input.setImportoPrincipale ( this.importoPrincipale );
		input.setComponentiImportoSecondario ( this.componentiImportoSecondario );
		input.getSoggettoPagatore ().setEmail ( this.email );
		input.getSoggettoPagatore ().setIdentificativoUnivocoFiscale ( this.codiceFiscalePartitaIVAPagatore );
		if ( StringUtils.hasText ( this.ragioneSociale ) ) {
			input.getSoggettoPagatore ().setPersonaGiuridica ( new PersonaGiuridica () );
			input.getSoggettoPagatore ().getPersonaGiuridica ().setRagioneSociale ( this.ragioneSociale );
		} else if ( StringUtils.hasText ( this.cognome ) ) {
			input.getSoggettoPagatore ().setPersonaFisica ( new PersonaFisica () );
			input.getSoggettoPagatore ().getPersonaFisica ().setNome ( this.nome );
			input.getSoggettoPagatore ().getPersonaFisica ().setCognome ( this.cognome );
		}
		input.setRequiresCostUpdate ( this.requiresCostUpdate );
		return input;
	}

	@Override
	public String toString () {
		return "AggiornaPosizioneDebitoriaChiamanteEsternoInputDto{" +
						"timestampChiamata=" + timestampChiamata +
						", ipChiamante='" + ipChiamante + '\'' +
						", codiceChiamante='" + codiceChiamante + '\'' +
						", dataScadenza=" + dataScadenza +
						", dataInizioValidita=" + dataInizioValidita +
						", dataFineValidita=" + dataFineValidita +
						", importoTotale=" + importo +
						", componentiImporto=" + componentiImporto +
						", descrizioneCausaleVersamento='" + causale + '\'' +
						", motivazione='" + motivazione + '\'' +
						'}';
	}
}
