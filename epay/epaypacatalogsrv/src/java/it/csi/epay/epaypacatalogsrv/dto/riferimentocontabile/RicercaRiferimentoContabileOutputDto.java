/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings ( "unused" )
public class RicercaRiferimentoContabileOutputDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id; 
    
    private String nomeEnte; 
    
    private String tipoEnte; 

    private Integer annoAccertamento; 

    private Integer annoEsercizio;  

    private Date dataFineValidita; 

    private Date dataInizioValidita; 

    private String datoSpecificoRiscossione; 

    private String descrizioneDatoSpecificoRiscossione; 
    
    private String descrizioneDatoSpecificoRiscossioneRifCont; 
    
    
    private String datoSpecificoRiscossioneTassonomia; 
    
    private String descrizioneErroreAggiornamento; 
    
    private String tipoServizio; 

    private String livelloPdc; 

    private Integer numeroAccertamento; 

    private Integer numeroArticolo; 

    private String numeroCapitolo; 

    private String titolo; 

    private String codiceTipologiaDatoSpecificoRiscossione; 

    private String descrizioneTipologiaDatoSpecificoRiscossione; 

    private Long idCodiceVersamento;

    private String codiceCodiceVersamento; 

    private String descrizioneCodiceVersamento; 
    
    
    private String tipologiaCodiceVersamento; 
    
    private String mailCodiceVersamento;
    
    private String noteCodiceVersamento;
    
    private Boolean fatturaCodiceVersamento; 
    
    private String ibanCodiceVersamento; 
    
    private String ibanAppoggioCodiceVersamento;
    
    private String modalitaIntegrazioneCodiceVersamento; 
    
    private Boolean statoCodiceVersamento;
    

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

    private List<RicercaRiferimentoContabileStoricoOutputDto> storico;


    private Boolean flagMbPrimario;

    private Boolean flagMbSecondario;

    private String mbModalita;

    private String mbEnteSecondario;

    private String mbCodiceVersamentoAssociato;


    public List<RicercaRiferimentoContabileStoricoOutputDto> getStorico () {
        return storico;
    }

    public void setStorico ( List<RicercaRiferimentoContabileStoricoOutputDto> storico ) {
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

	/**
	 * @return the nomeEnte
	 */
	public String getNomeEnte() {
		return nomeEnte;
	}

	/**
	 * @param nomeEnte the nomeEnte to set
	 */
	public void setNomeEnte(String nomeEnte) {
		this.nomeEnte = nomeEnte;
	}

	/**
	 * @return the tipoEnte
	 */
	public String getTipoEnte() {
		return tipoEnte;
	}

	/**
	 * @param tipoEnte the tipoEnte to set
	 */
	public void setTipoEnte(String tipoEnte) {
		this.tipoEnte = tipoEnte;
	}

	/**
	 * @return the datoSpecificoRiscossioneTassonomia
	 */
	public String getDatoSpecificoRiscossioneTassonomia() {
		return datoSpecificoRiscossioneTassonomia;
	}

	/**
	 * @param datoSpecificoRiscossioneTassonomia the datoSpecificoRiscossioneTassonomia to set
	 */
	public void setDatoSpecificoRiscossioneTassonomia(String datoSpecificoRiscossioneTassonomia) {
		this.datoSpecificoRiscossioneTassonomia = datoSpecificoRiscossioneTassonomia;
	}

	/**
	 * @return the tipologiaCodiceVersamento
	 */
	public String getTipologiaCodiceVersamento() {
		return tipologiaCodiceVersamento;
	}

	/**
	 * @param tipologiaCodiceVersamento the tipologiaCodiceVersamento to set
	 */
	public void setTipologiaCodiceVersamento(String tipologiaCodiceVersamento) {
		this.tipologiaCodiceVersamento = tipologiaCodiceVersamento;
	}

	/**
	 * @return the mailCodiceVersamento
	 */
	public String getMailCodiceVersamento() {
		return mailCodiceVersamento;
	}

	/**
	 * @param mailCodiceVersamento the mailCodiceVersamento to set
	 */
	public void setMailCodiceVersamento(String mailCodiceVersamento) {
		this.mailCodiceVersamento = mailCodiceVersamento;
	}

	/**
	 * @return the noteCodiceVersamento
	 */
	public String getNoteCodiceVersamento() {
		return noteCodiceVersamento;
	}

	/**
	 * @param noteCodiceVersamento the noteCodiceVersamento to set
	 */
	public void setNoteCodiceVersamento(String noteCodiceVersamento) {
		this.noteCodiceVersamento = noteCodiceVersamento;
	}

	/**
	 * @return the fatturaCodiceVersamento
	 */
	public Boolean getFatturaCodiceVersamento() {
		return fatturaCodiceVersamento;
	}

	/**
	 * @param fatturaCodiceVersamento the fatturaCodiceVersamento to set
	 */
	public void setFatturaCodiceVersamento(Boolean fatturaCodiceVersamento) {
		this.fatturaCodiceVersamento = fatturaCodiceVersamento;
	}

	/**
	 * @return the ibanCodiceVersamento
	 */
	public String getIbanCodiceVersamento() {
		return ibanCodiceVersamento;
	}

	/**
	 * @param ibanCodiceVersamento the ibanCodiceVersamento to set
	 */
	public void setIbanCodiceVersamento(String ibanCodiceVersamento) {
		this.ibanCodiceVersamento = ibanCodiceVersamento;
	}

	/**
	 * @return the ibanAppoggioCodiceVersamento
	 */
	public String getIbanAppoggioCodiceVersamento() {
		return ibanAppoggioCodiceVersamento;
	}

	/**
	 * @param ibanAppoggioCodiceVersamento the ibanAppoggioCodiceVersamento to set
	 */
	public void setIbanAppoggioCodiceVersamento(String ibanAppoggioCodiceVersamento) {
		this.ibanAppoggioCodiceVersamento = ibanAppoggioCodiceVersamento;
	}

	/**
	 * @return the modalitaIntegrazioneCodiceVersamento
	 */
	public String getModalitaIntegrazioneCodiceVersamento() {
		return modalitaIntegrazioneCodiceVersamento;
	}

	/**
	 * @param modalitaIntegrazioneCodiceVersamento the modalitaIntegrazioneCodiceVersamento to set
	 */
	public void setModalitaIntegrazioneCodiceVersamento(String modalitaIntegrazioneCodiceVersamento) {
		this.modalitaIntegrazioneCodiceVersamento = modalitaIntegrazioneCodiceVersamento;
	}

	/**
	 * @return the statoCodiceVersamento
	 */
	public Boolean getStatoCodiceVersamento() {
		return statoCodiceVersamento;
	}

	/**
	 * @param statoCodiceVersamento the statoCodiceVersamento to set
	 */
	public void setStatoCodiceVersamento(Boolean statoCodiceVersamento) {
		this.statoCodiceVersamento = statoCodiceVersamento;
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


    
    public Boolean getFlagMbPrimario () {
        return flagMbPrimario;
    }


    
    public void setFlagMbPrimario ( Boolean flagMbPrimario ) {
        this.flagMbPrimario = flagMbPrimario;
    }


    
    public Boolean getFlagMbSecondario () {
        return flagMbSecondario;
    }


    
    public void setFlagMbSecondario ( Boolean flagMbSecondario ) {
        this.flagMbSecondario = flagMbSecondario;
    }


    
    public String getMbModalita () {
        return mbModalita;
    }


    
    public void setMbModalita ( String mbModalita ) {
        this.mbModalita = mbModalita;
    }


    
    public String getMbEnteSecondario () {
        return mbEnteSecondario;
    }


    
    public void setMbEnteSecondario ( String mbEnteSecondario ) {
        this.mbEnteSecondario = mbEnteSecondario;
    }


    
    public String getMbCodiceVersamentoAssociato () {
        return mbCodiceVersamentoAssociato;
    }


    
    public void setMbCodiceVersamentoAssociato ( String mbCodiceVersamentoAssociato ) {
        this.mbCodiceVersamentoAssociato = mbCodiceVersamentoAssociato;
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

	@Override public String toString () {
		return "RicercaRiferimentoContabileOutputDto{" +
						"id=" + id +
						", nomeEnte='" + nomeEnte + '\'' +
						", tipoEnte='" + tipoEnte + '\'' +
						", annoAccertamento=" + annoAccertamento +
						", annoEsercizio=" + annoEsercizio +
						", dataFineValidita=" + dataFineValidita +
						", dataInizioValidita=" + dataInizioValidita +
						", datoSpecificoRiscossione='" + datoSpecificoRiscossione + '\'' +
						", descrizioneDatoSpecificoRiscossione='" + descrizioneDatoSpecificoRiscossione + '\'' +
						", descrizioneDatoSpecificoRiscossioneRifCont='" + descrizioneDatoSpecificoRiscossioneRifCont + '\'' +
						", datoSpecificoRiscossioneTassonomia='" + datoSpecificoRiscossioneTassonomia + '\'' +
						", descrizioneErroreAggiornamento='" + descrizioneErroreAggiornamento + '\'' +
						", tipoServizio='" + tipoServizio + '\'' +
						", livelloPdc='" + livelloPdc + '\'' +
						", numeroAccertamento=" + numeroAccertamento +
						", numeroArticolo=" + numeroArticolo +
						", numeroCapitolo='" + numeroCapitolo + '\'' +
						", titolo='" + titolo + '\'' +
						", codiceTipologiaDatoSpecificoRiscossione='" + codiceTipologiaDatoSpecificoRiscossione + '\'' +
						", descrizioneTipologiaDatoSpecificoRiscossione='" + descrizioneTipologiaDatoSpecificoRiscossione + '\'' +
						", idCodiceVersamento=" + idCodiceVersamento +
						", codiceCodiceVersamento='" + codiceCodiceVersamento + '\'' +
						", descrizioneCodiceVersamento='" + descrizioneCodiceVersamento + '\'' +
						", tipologiaCodiceVersamento='" + tipologiaCodiceVersamento + '\'' +
						", mailCodiceVersamento='" + mailCodiceVersamento + '\'' +
						", noteCodiceVersamento='" + noteCodiceVersamento + '\'' +
						", fatturaCodiceVersamento=" + fatturaCodiceVersamento +
						", ibanCodiceVersamento='" + ibanCodiceVersamento + '\'' +
						", ibanAppoggioCodiceVersamento='" + ibanAppoggioCodiceVersamento + '\'' +
						", modalitaIntegrazioneCodiceVersamento='" + modalitaIntegrazioneCodiceVersamento + '\'' +
						", statoCodiceVersamento=" + statoCodiceVersamento +
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
						", flagMbPrimario=" + flagMbPrimario +
						", flagMbSecondario=" + flagMbSecondario +
						", mbModalita='" + mbModalita + '\'' +
						", mbEnteSecondario='" + mbEnteSecondario + '\'' +
						", mbCodiceVersamentoAssociato='" + mbCodiceVersamentoAssociato + '\'' +
						'}';
	}
}
