/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings ( "unused" )
public class GetRiferimentoContabileOutputDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer annoAccertamento;

	private Integer annoEsercizio;

	private Date dataFineValidita;

	private Date dataInizioValidita;

	private String datoSpecificoRiscossione;
	
	private String datoSpecificoRiscossioneTassonomia;

	private String descrizioneDatoSpecificoRiscossione;
	
	private String descrizioneDatoSpecificoRiscossioneRifCont;
	
	private String tipoServizioTassonomia;

	private String descrizioneErroreAggiornamento;

	private String livelloPdc;

	private Integer numeroAccertamento;

	private Integer numeroArticolo;

	private String numeroCapitolo;

	private String titolo;

	private String codiceStatoAggiornamento;

	private String descrizioneStatoAggiornamento;

	private String codiceTipologiaDatoSpecificoRiscossione;

	private String descrizioneTipologiaDatoSpecificoRiscossione;

	private Long idCodiceVersamento;

	private String codiceCodiceVersamento;

	private String descrizioneCodiceVersamento;

	private String codiceVoceEntrata;

	private String descrizioneVoceEntrata;

	private String codiceMacrotipo;

	private String descrizioneMacrotipo;

	private String codiceTematica;

	private String descrizioneTematica;

	private String categoria;

	private String tipologia;
    
    private Date dataFineValiditaCodiceTassonomico;

    private Date dataInizioValiditaCodiceTassonomico;
    
	private List<GetRiferimentoContabileStoricoOutputDto> storico;

    private Boolean flagElementiMultibeneficiario;

    private String mbModalita;

    private String mbEnteSecondario;

    private String mbCodiceVersamentoAssociato;
    
    private String mbRiferimentoContabileAssociato;
    
    private String codiceTipoServizioTassonomia;
    
    private Long idTassonomia;

	private String codiceTributo;
    
	public List<GetRiferimentoContabileStoricoOutputDto> getStorico () {
		return storico;
	}

    public void setStorico ( List<GetRiferimentoContabileStoricoOutputDto> storico ) {
		this.storico = storico;
	}

	public String getCategoria () {
		return categoria;
	}

	public void setCategoria ( String categoria ) {
		this.categoria = categoria;
	}

	public String getTipologia () {
		return tipologia;
	}

	public void setTipologia ( String tipologia ) {
		this.tipologia = tipologia;
	}

	public String getCodiceCodiceVersamento () {
		return codiceCodiceVersamento;
	}

	public void setCodiceCodiceVersamento ( String codiceCodiceVersamento ) {
		this.codiceCodiceVersamento = codiceCodiceVersamento;
	}

	public String getCodiceVoceEntrata () {
		return codiceVoceEntrata;
	}

	public void setCodiceVoceEntrata ( String codiceVoceEntrata ) {
		this.codiceVoceEntrata = codiceVoceEntrata;
	}

	public String getDescrizioneVoceEntrata () {
		return descrizioneVoceEntrata;
	}

	public void setDescrizioneVoceEntrata ( String descrizioneVoceEntrata ) {
		this.descrizioneVoceEntrata = descrizioneVoceEntrata;
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

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public Integer getAnnoAccertamento () {
		return annoAccertamento;
	}

	public void setAnnoAccertamento ( Integer annoAccertamento ) {
		this.annoAccertamento = annoAccertamento;
	}

	public Integer getAnnoEsercizio () {
		return annoEsercizio;
	}

	public void setAnnoEsercizio ( Integer annoEsercizio ) {
		this.annoEsercizio = annoEsercizio;
	}

	public Date getDataFineValidita () {
		return dataFineValidita;
	}

	public void setDataFineValidita ( Date dataFineValidita ) {
		this.dataFineValidita = dataFineValidita;
	}

	public Date getDataInizioValidita () {
		return dataInizioValidita;
	}

	public void setDataInizioValidita ( Date dataInizioValidita ) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public String getDatoSpecificoRiscossione () {
		return datoSpecificoRiscossione;
	}

	public void setDatoSpecificoRiscossione ( String datoSpecificoRiscossione ) {
		this.datoSpecificoRiscossione = datoSpecificoRiscossione;
	}

	public String getDescrizioneDatoSpecificoRiscossione () {
		return descrizioneDatoSpecificoRiscossione;
	}

	public void setDescrizioneDatoSpecificoRiscossione ( String descrizioneDatoSpecificoRiscossione ) {
		this.descrizioneDatoSpecificoRiscossione = descrizioneDatoSpecificoRiscossione;
	}

	public String getDescrizioneErroreAggiornamento () {
		return descrizioneErroreAggiornamento;
	}

	public void setDescrizioneErroreAggiornamento ( String descrizioneErroreAggiornamento ) {
		this.descrizioneErroreAggiornamento = descrizioneErroreAggiornamento;
	}

	public String getLivelloPdc () {
		return livelloPdc;
	}

	public void setLivelloPdc ( String livelloPdc ) {
		this.livelloPdc = livelloPdc;
	}

	public Integer getNumeroAccertamento () {
		return numeroAccertamento;
	}

	public void setNumeroAccertamento ( Integer numeroAccertamento ) {
		this.numeroAccertamento = numeroAccertamento;
	}

	public Integer getNumeroArticolo () {
		return numeroArticolo;
	}

	public void setNumeroArticolo ( Integer numeroArticolo ) {
		this.numeroArticolo = numeroArticolo;
	}

	public String getNumeroCapitolo () {
		return numeroCapitolo;
	}

	public void setNumeroCapitolo ( String numeroCapitolo ) {
		this.numeroCapitolo = numeroCapitolo;
	}

	public String getTitolo () {
		return titolo;
	}

	public void setTitolo ( String titolo ) {
		this.titolo = titolo;
	}

	public String getCodiceStatoAggiornamento () {
		return codiceStatoAggiornamento;
	}

	public void setCodiceStatoAggiornamento ( String codiceStatoAggiornamento ) {
		this.codiceStatoAggiornamento = codiceStatoAggiornamento;
	}

	public String getDescrizioneStatoAggiornamento () {
		return descrizioneStatoAggiornamento;
	}

	public void setDescrizioneStatoAggiornamento ( String descrizioneStatoAggiornamento ) {
		this.descrizioneStatoAggiornamento = descrizioneStatoAggiornamento;
	}

	public String getCodiceTipologiaDatoSpecificoRiscossione () {
		return codiceTipologiaDatoSpecificoRiscossione;
	}

	public void setCodiceTipologiaDatoSpecificoRiscossione ( String codiceTipologiaDatoSpecificoRiscossione ) {
		this.codiceTipologiaDatoSpecificoRiscossione = codiceTipologiaDatoSpecificoRiscossione;
	}

	public String getDescrizioneTipologiaDatoSpecificoRiscossione () {
		return descrizioneTipologiaDatoSpecificoRiscossione;
	}

	public void setDescrizioneTipologiaDatoSpecificoRiscossione ( String descrizioneTipologiaDatoSpecificoRiscossione ) {
		this.descrizioneTipologiaDatoSpecificoRiscossione = descrizioneTipologiaDatoSpecificoRiscossione;
	}

	public Long getIdCodiceVersamento () {
		return idCodiceVersamento;
	}

	public void setIdCodiceVersamento ( Long idCodiceVersamento ) {
		this.idCodiceVersamento = idCodiceVersamento;
	}

	public String getDescrizioneCodiceVersamento () {
		return descrizioneCodiceVersamento;
	}

	public void setDescrizioneCodiceVersamento ( String descrizioneCodiceVersamento ) {
		this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
	}

	public String getMbModalita() {
		return mbModalita;
	}

	public void setMbModalita(String mbModalita) {
		this.mbModalita = mbModalita;
	}

	public String getMbEnteSecondario() {
		return mbEnteSecondario;
	}

	public void setMbEnteSecondario(String mbEnteSecondario) {
		this.mbEnteSecondario = mbEnteSecondario;
	}

	public String getMbCodiceVersamentoAssociato() {
		return mbCodiceVersamentoAssociato;
	}

	public void setMbCodiceVersamentoAssociato(String mbCodiceVersamentoAssociato) {
		this.mbCodiceVersamentoAssociato = mbCodiceVersamentoAssociato;
	}

	public Boolean getFlagElementiMultibeneficiario () {
		return flagElementiMultibeneficiario;
	}
	/**
	 * @return the dataFineValiditaCodiceTassonomico
	 */
	public Date getDataFineValiditaCodiceTassonomico() {
		return dataFineValiditaCodiceTassonomico;
	}

	/**
	 * @param dataFineValiditaCodiceTassonomico the dataFineValiditaCodiceTassonomico to set
	 */
	public void setDataFineValiditaCodiceTassonomico(Date dataFineValiditaCodiceTassonomico) {
		this.dataFineValiditaCodiceTassonomico = dataFineValiditaCodiceTassonomico;
	}

	/**
	 * @return the dataInizioValiditaCodiceTassonomico
	 */
	public Date getDataInizioValiditaCodiceTassonomico() {
		return dataInizioValiditaCodiceTassonomico;
	}

	/**
	 * @param dataInizioValiditaCodiceTassonomico the dataInizioValiditaCodiceTassonomico to set
	 */
	public void setDataInizioValiditaCodiceTassonomico(Date dataInizioValiditaCodiceTassonomico) {
		this.dataInizioValiditaCodiceTassonomico = dataInizioValiditaCodiceTassonomico;
	}

	public void setFlagElementiMultibeneficiario ( Boolean flagElementiMultibeneficiario ) {
		this.flagElementiMultibeneficiario = flagElementiMultibeneficiario;
	}

    
    /**
     * @return the mbRiferimentoContabileAssociato
     */
    public String getMbRiferimentoContabileAssociato () {
        return mbRiferimentoContabileAssociato;
    }

    
    /**
     * @param mbRiferimentoContabileAssociato the mbRiferimentoContabileAssociato to set
     */
    public void setMbRiferimentoContabileAssociato ( String mbRiferimentoContabileAssociato ) {
        this.mbRiferimentoContabileAssociato = mbRiferimentoContabileAssociato;
    }

    
    public String getDatoSpecificoRiscossioneTassonomia () {
        return datoSpecificoRiscossioneTassonomia;
    }

    
    public void setDatoSpecificoRiscossioneTassonomia ( String datoSpecificoRiscossioneTassonomia ) {
        this.datoSpecificoRiscossioneTassonomia = datoSpecificoRiscossioneTassonomia;
    }

    
    /**
     * @return the tipoServizioTassonomia
     */
    public String getTipoServizioTassonomia () {
        return tipoServizioTassonomia;
    }

    
    /**
     * @param tipoServizioTassonomia the tipoServizioTassonomia to set
     */
    public void setTipoServizioTassonomia ( String tipoServizioTassonomia ) {
        this.tipoServizioTassonomia = tipoServizioTassonomia;
    }

    
    /**
     * @return the descrizioneDatoSpecificoRiscossioneRifCont
     */
    public String getDescrizioneDatoSpecificoRiscossioneRifCont () {
        return descrizioneDatoSpecificoRiscossioneRifCont;
    }

    
    /**
     * @param descrizioneDatoSpecificoRiscossioneRifCont the descrizioneDatoSpecificoRiscossioneRifCont to set
     */
    public void setDescrizioneDatoSpecificoRiscossioneRifCont ( String descrizioneDatoSpecificoRiscossioneRifCont ) {
        this.descrizioneDatoSpecificoRiscossioneRifCont = descrizioneDatoSpecificoRiscossioneRifCont;
    }

    
    /**
     * @return the codiceTipoServizioTassonomia
     */
    public String getCodiceTipoServizioTassonomia () {
        return codiceTipoServizioTassonomia;
    }

    
    /**
     * @param codiceTipoServizioTassonomia the codiceTipoServizioTassonomia to set
     */
    public void setCodiceTipoServizioTassonomia ( String codiceTipoServizioTassonomia ) {
        this.codiceTipoServizioTassonomia = codiceTipoServizioTassonomia;
    }

    
    /**
     * @return the idTassonomia
     */
    public Long getIdTassonomia () {
        return idTassonomia;
    }

    
    /**
     * @param idTassonomia the idTassonomia to set
     */
    public void setIdTassonomia ( Long idTassonomia ) {
        this.idTassonomia = idTassonomia;
    }

	public String getCodiceTributo () {
		return codiceTributo;
	}

	public void setCodiceTributo ( String codiceTributo ) {
		this.codiceTributo = codiceTributo;
	}

	@Override public String toString () {
		return "GetRiferimentoContabileOutputDto{" +
						"id=" + id +
						", annoAccertamento=" + annoAccertamento +
						", annoEsercizio=" + annoEsercizio +
						", dataFineValidita=" + dataFineValidita +
						", dataInizioValidita=" + dataInizioValidita +
						", datoSpecificoRiscossione='" + datoSpecificoRiscossione + '\'' +
						", datoSpecificoRiscossioneTassonomia='" + datoSpecificoRiscossioneTassonomia + '\'' +
						", descrizioneDatoSpecificoRiscossione='" + descrizioneDatoSpecificoRiscossione + '\'' +
						", descrizioneDatoSpecificoRiscossioneRifCont='" + descrizioneDatoSpecificoRiscossioneRifCont + '\'' +
						", tipoServizioTassonomia='" + tipoServizioTassonomia + '\'' +
						", descrizioneErroreAggiornamento='" + descrizioneErroreAggiornamento + '\'' +
						", livelloPdc='" + livelloPdc + '\'' +
						", numeroAccertamento=" + numeroAccertamento +
						", numeroArticolo=" + numeroArticolo +
						", numeroCapitolo='" + numeroCapitolo + '\'' +
						", titolo='" + titolo + '\'' +
						", codiceStatoAggiornamento='" + codiceStatoAggiornamento + '\'' +
						", descrizioneStatoAggiornamento='" + descrizioneStatoAggiornamento + '\'' +
						", codiceTipologiaDatoSpecificoRiscossione='" + codiceTipologiaDatoSpecificoRiscossione + '\'' +
						", descrizioneTipologiaDatoSpecificoRiscossione='" + descrizioneTipologiaDatoSpecificoRiscossione + '\'' +
						", idCodiceVersamento=" + idCodiceVersamento +
						", codiceCodiceVersamento='" + codiceCodiceVersamento + '\'' +
						", descrizioneCodiceVersamento='" + descrizioneCodiceVersamento + '\'' +
						", codiceVoceEntrata='" + codiceVoceEntrata + '\'' +
						", descrizioneVoceEntrata='" + descrizioneVoceEntrata + '\'' +
						", codiceMacrotipo='" + codiceMacrotipo + '\'' +
						", descrizioneMacrotipo='" + descrizioneMacrotipo + '\'' +
						", codiceTematica='" + codiceTematica + '\'' +
						", descrizioneTematica='" + descrizioneTematica + '\'' +
						", categoria='" + categoria + '\'' +
						", tipologia='" + tipologia + '\'' +
						", dataFineValiditaCodiceTassonomico=" + dataFineValiditaCodiceTassonomico +
						", dataInizioValiditaCodiceTassonomico=" + dataInizioValiditaCodiceTassonomico +
						", storico=" + storico +
						", flagElementiMultibeneficiario=" + flagElementiMultibeneficiario +
						", mbModalita='" + mbModalita + '\'' +
						", mbEnteSecondario='" + mbEnteSecondario + '\'' +
						", mbCodiceVersamentoAssociato='" + mbCodiceVersamentoAssociato + '\'' +
						", mbRiferimentoContabileAssociato='" + mbRiferimentoContabileAssociato + '\'' +
						", codiceTipoServizioTassonomia='" + codiceTipoServizioTassonomia + '\'' +
						", idTassonomia=" + idTassonomia +
						", codiceTributo='" + codiceTributo + '\'' +
						'}';
	}
}
