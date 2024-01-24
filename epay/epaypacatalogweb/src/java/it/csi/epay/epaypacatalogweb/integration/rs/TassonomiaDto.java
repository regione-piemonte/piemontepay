/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.rs;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;


public class TassonomiaDto implements Serializable {

	private static final long serialVersionUID = 340952119844037648L;

	 @JsonProperty("id")
	private Long id;

	 @JsonProperty("codTipoEnteCreditore")
    private String codTipoEnteCreditore;
    
	 @JsonProperty("tipoEnteCreditore")
    private String tipoEnteCreditore;
    
	 @JsonProperty("macroArea")
    private String macroArea;
    
	 @JsonProperty("nomeMacroArea")
    private String nomeMacroArea;
    
	 @JsonProperty("descrMacroArea")
    private String descrMacroArea;
    
	 @JsonProperty("codTipologiaServizio")
    private String codTipologiaServizio;
    
	 @JsonProperty("tipoServizio")
    private String tipoServizio;
    
	 @JsonProperty("motivoGiuridicoRiscossione")
    private String motivoGiuridicoRiscossione;
    
	 @JsonProperty("descrTipoServizio")
    private String descrTipoServizio;
    
	 @JsonProperty("nVersioneTassonomia")
    private String nVersioneTassonomia;
    
	 @JsonProperty("datiSpecificiIncasso")
    private String datiSpecificiIncasso;

	 @JsonProperty("dataInizioValidita")
    private Date dataInizioValidita;

	 @JsonProperty("dataFineValidita")
    private Date dataFineValidita;
    
	 @JsonProperty("flagAggiornato")
    private Boolean flagAggiornato;
	 
	 



    public TassonomiaDto () {
    }



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}



	/**
	 * @return the codTipoEnteCreditore
	 */
	public String getCodTipoEnteCreditore() {
		return codTipoEnteCreditore;
	}



	/**
	 * @param codTipoEnteCreditore the codTipoEnteCreditore to set
	 */
	public void setCodTipoEnteCreditore(String codTipoEnteCreditore) {
		this.codTipoEnteCreditore = codTipoEnteCreditore;
	}



	/**
	 * @return the tipoEnteCreditore
	 */
	public String getTipoEnteCreditore() {
		return tipoEnteCreditore;
	}



	/**
	 * @param tipoEnteCreditore the tipoEnteCreditore to set
	 */
	public void setTipoEnteCreditore(String tipoEnteCreditore) {
		this.tipoEnteCreditore = tipoEnteCreditore;
	}





	/**
	 * @return the macroArea
	 */
	public String getMacroArea() {
		return macroArea;
	}



	/**
	 * @param macroArea the macroArea to set
	 */
	public void setMacroArea(String macroArea) {
		this.macroArea = macroArea;
	}



	/**
	 * @return the nomeMacroArea
	 */
	public String getNomeMacroArea() {
		return nomeMacroArea;
	}



	/**
	 * @param nomeMacroArea the nomeMacroArea to set
	 */
	public void setNomeMacroArea(String nomeMacroArea) {
		this.nomeMacroArea = nomeMacroArea;
	}



	/**
	 * @return the descrMacroArea
	 */
	public String getDescrMacroArea() {
		return descrMacroArea;
	}



	/**
	 * @param descrMacroArea the descrMacroArea to set
	 */
	public void setDescrMacroArea(String descrMacroArea) {
		this.descrMacroArea = descrMacroArea;
	}



	/**
	 * @return the codTipologiaServizio
	 */
	public String getCodTipologiaServizio() {
		return codTipologiaServizio;
	}



	/**
	 * @param codTipologiaServizio the codTipologiaServizio to set
	 */
	public void setCodTipologiaServizio(String codTipologiaServizio) {
		this.codTipologiaServizio = codTipologiaServizio;
	}



	/**
	 * @return the tipoServizio
	 */
	public String getTipoServizio() {
		return tipoServizio;
	}



	/**
	 * @param tipoServizio the tipoServizio to set
	 */
	public void setTipoServizio(String tipoServizio) {
		this.tipoServizio = tipoServizio;
	}



	/**
	 * @return the motivoGiuridicoRiscossione
	 */
	public String getMotivoGiuridicoRiscossione() {
		return motivoGiuridicoRiscossione;
	}



	/**
	 * @param motivoGiuridicoRiscossione the motivoGiuridicoRiscossione to set
	 */
	public void setMotivoGiuridicoRiscossione(String motivoGiuridicoRiscossione) {
		this.motivoGiuridicoRiscossione = motivoGiuridicoRiscossione;
	}



	/**
	 * @return the descrTipoServizio
	 */
	public String getDescrTipoServizio() {
		return descrTipoServizio;
	}



	/**
	 * @param descrTipoServizio the descrTipoServizio to set
	 */
	public void setDescrTipoServizio(String descrTipoServizio) {
		this.descrTipoServizio = descrTipoServizio;
	}



	/**
	 * @return the nVersioneTassonomia
	 */
	public String getnVersioneTassonomia() {
		return nVersioneTassonomia;
	}



	/**
	 * @param nVersioneTassonomia the nVersioneTassonomia to set
	 */
	public void setnVersioneTassonomia(String nVersioneTassonomia) {
		this.nVersioneTassonomia = nVersioneTassonomia;
	}



	/**
	 * @return the datiSpecificiIncasso
	 */
	public String getDatiSpecificiIncasso() {
		return datiSpecificiIncasso;
	}



	/**
	 * @param datiSpecificiIncasso the datiSpecificiIncasso to set
	 */
	public void setDatiSpecificiIncasso(String datiSpecificiIncasso) {
		this.datiSpecificiIncasso = datiSpecificiIncasso;
	}

	

	/**
	 * @return the dataInizioValidita
	 */
	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}



	/**
	 * @param dataInizioValidita the dataInizioValidita to set
	 */
	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}



	/**
	 * @return the dataFineValidita
	 */
	public Date getDataFineValidita() {
		return dataFineValidita;
	}



	/**
	 * @param dataFineValidita the dataFineValidita to set
	 */
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}



	/**
	 * @return the flagAggiornato
	 */
	public Boolean getFlagAggiornato() {
		return flagAggiornato;
	}



	/**
	 * @param flagAggiornato the flagAggiornato to set
	 */
	public void setFlagAggiornato(Boolean flagAggiornato) {
		this.flagAggiornato = flagAggiornato;
	}

  
}
