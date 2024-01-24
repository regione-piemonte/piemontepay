/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.riferimentocontabile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings ( "unused" )
public class RiferimentoContabileVO implements Serializable {

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

    private String codiceDatoSpecificoRiscossione;

    private String descrizioneErroreAggiornamento;

    private String livelloPdc;

    private Integer numeroAccertamento;

    private Integer numeroArticolo;

    private String numeroCapitolo;

    private String titolo;

    private String categoria;

    private String codiceStatoAggiornamento;

    private String descrizioneStatoAggiornamento;

    private String codiceTipologiaDatoSpecificoRiscossione;

    private String descrizioneTipologiaDatoSpecificoRiscossione;

    private Long idTassonomia;

    private String tipologia;

    private Long idCodiceVersamento;

    private String codiceCodiceVersamento;

    private String descrizioneCodiceVersamento;

    private String codiceVoceEntrata;

    private String descrizioneVoceEntrata;

    private String codiceMacrotipo;

    private String descrizioneMacrotipo;

    private String codiceTematica;

    private String descrizioneTematica;

    private Boolean inVita;

    private Boolean flagPrincipale;

    private Date dataFineValiditaCodiceTassonomico;

    private Date dataInizioValiditaCodiceTassonomico;

    private String tipoPagamento;

    private Boolean flagValoreInizialeIdCodiceVersamento;

    private Boolean flagElementiMultibeneficiario;

    private Boolean flagAssociaRifContSecondario;

    private Boolean flagAssociaRifContPrimario;

    private String enteSecondarioAssociato;

    private String covSecondarioAssociato;

    private Long idRifContabileSecondarioAssociato;
    
    private String rifContabileSecondarioAssociato;

    private Boolean flagAssociaRifContSecondarioValue;

    private Boolean flagAssociaRifContPrimarioValue;

    private List<RiferimentoContabileStoricoVO> storico;

    private String mbModalita;

    private String mbEnteSecondario;

    private String mbCodiceVersamentoAssociato;
    
    private String tipoServizioTassonomia;

	private String codiceTributo;

    
    /**
     * @return the id
     */
    public Long getId () {
        return id;
    }

    
    /**
     * @return the annoAccertamento
     */
    public Integer getAnnoAccertamento () {
        return annoAccertamento;
    }

    
    /**
     * @return the annoEsercizio
     */
    public Integer getAnnoEsercizio () {
        return annoEsercizio;
    }

    
    /**
     * @return the dataFineValidita
     */
    public Date getDataFineValidita () {
        return dataFineValidita;
    }

    
    /**
     * @return the dataInizioValidita
     */
    public Date getDataInizioValidita () {
        return dataInizioValidita;
    }

    
    /**
     * @return the datoSpecificoRiscossione
     */
    public String getDatoSpecificoRiscossione () {
        return datoSpecificoRiscossione;
    }

    
    /**
     * @return the descrizioneDatoSpecificoRiscossione
     */
    public String getDescrizioneDatoSpecificoRiscossione () {
        return descrizioneDatoSpecificoRiscossione;
    }

    
    /**
     * @return the codiceDatoSpecificoRiscossione
     */
    public String getCodiceDatoSpecificoRiscossione () {
        return codiceDatoSpecificoRiscossione;
    }

    
    /**
     * @return the descrizioneErroreAggiornamento
     */
    public String getDescrizioneErroreAggiornamento () {
        return descrizioneErroreAggiornamento;
    }

    
    /**
     * @return the livelloPdc
     */
    public String getLivelloPdc () {
        return livelloPdc;
    }

    
    /**
     * @return the numeroAccertamento
     */
    public Integer getNumeroAccertamento () {
        return numeroAccertamento;
    }

    
    /**
     * @return the numeroArticolo
     */
    public Integer getNumeroArticolo () {
        return numeroArticolo;
    }

    
    /**
     * @return the numeroCapitolo
     */
    public String getNumeroCapitolo () {
        return numeroCapitolo;
    }

    
    /**
     * @return the titolo
     */
    public String getTitolo () {
        return titolo;
    }

    
    /**
     * @return the categoria
     */
    public String getCategoria () {
        return categoria;
    }

    
    /**
     * @return the codiceStatoAggiornamento
     */
    public String getCodiceStatoAggiornamento () {
        return codiceStatoAggiornamento;
    }

    
    /**
     * @return the descrizioneStatoAggiornamento
     */
    public String getDescrizioneStatoAggiornamento () {
        return descrizioneStatoAggiornamento;
    }

    
    /**
     * @return the codiceTipologiaDatoSpecificoRiscossione
     */
    public String getCodiceTipologiaDatoSpecificoRiscossione () {
        return codiceTipologiaDatoSpecificoRiscossione;
    }

    
    /**
     * @return the descrizioneTipologiaDatoSpecificoRiscossione
     */
    public String getDescrizioneTipologiaDatoSpecificoRiscossione () {
        return descrizioneTipologiaDatoSpecificoRiscossione;
    }

    
    /**
     * @return the idTassonomia
     */
    public Long getIdTassonomia () {
        return idTassonomia;
    }

    
    /**
     * @return the tipologia
     */
    public String getTipologia () {
        return tipologia;
    }

    
    /**
     * @return the idCodiceVersamento
     */
    public Long getIdCodiceVersamento () {
        return idCodiceVersamento;
    }

    
    /**
     * @return the codiceCodiceVersamento
     */
    public String getCodiceCodiceVersamento () {
        return codiceCodiceVersamento;
    }

    
    /**
     * @return the descrizioneCodiceVersamento
     */
    public String getDescrizioneCodiceVersamento () {
        return descrizioneCodiceVersamento;
    }

    
    /**
     * @return the codiceVoceEntrata
     */
    public String getCodiceVoceEntrata () {
        return codiceVoceEntrata;
    }

    
    /**
     * @return the descrizioneVoceEntrata
     */
    public String getDescrizioneVoceEntrata () {
        return descrizioneVoceEntrata;
    }

    
    /**
     * @return the codiceMacrotipo
     */
    public String getCodiceMacrotipo () {
        return codiceMacrotipo;
    }

    
    /**
     * @return the descrizioneMacrotipo
     */
    public String getDescrizioneMacrotipo () {
        return descrizioneMacrotipo;
    }

    
    /**
     * @return the codiceTematica
     */
    public String getCodiceTematica () {
        return codiceTematica;
    }

    
    /**
     * @return the descrizioneTematica
     */
    public String getDescrizioneTematica () {
        return descrizioneTematica;
    }

    
    /**
     * @return the inVita
     */
    public Boolean getInVita () {
        return inVita;
    }

    
    /**
     * @return the flagPrincipale
     */
    public Boolean getFlagPrincipale () {
        return flagPrincipale;
    }

    
    /**
     * @return the dataFineValiditaCodiceTassonomico
     */
    public Date getDataFineValiditaCodiceTassonomico () {
        return dataFineValiditaCodiceTassonomico;
    }

    
    /**
     * @return the dataInizioValiditaCodiceTassonomico
     */
    public Date getDataInizioValiditaCodiceTassonomico () {
        return dataInizioValiditaCodiceTassonomico;
    }

    
    /**
     * @return the tipoPagamento
     */
    public String getTipoPagamento () {
        return tipoPagamento;
    }

    
    /**
     * @return the flagValoreInizialeIdCodiceVersamento
     */
    public Boolean getFlagValoreInizialeIdCodiceVersamento () {
        return flagValoreInizialeIdCodiceVersamento;
    }

    
    /**
     * @return the flagElementiMultibeneficiario
     */
    public Boolean getFlagElementiMultibeneficiario () {
        return flagElementiMultibeneficiario;
    }

    
    /**
     * @return the flagAssociaRifContSecondario
     */
    public Boolean getFlagAssociaRifContSecondario () {
        return flagAssociaRifContSecondario;
    }

    
    /**
     * @return the flagAssociaRifContPrimario
     */
    public Boolean getFlagAssociaRifContPrimario () {
        return flagAssociaRifContPrimario;
    }

    
    /**
     * @return the enteSecondarioAssociato
     */
    public String getEnteSecondarioAssociato () {
        return enteSecondarioAssociato;
    }

    
    /**
     * @return the covSecondarioAssociato
     */
    public String getCovSecondarioAssociato () {
        return covSecondarioAssociato;
    }

    
    /**
     * @return the idRifContabileSecondarioAssociato
     */
    public Long getIdRifContabileSecondarioAssociato () {
        return idRifContabileSecondarioAssociato;
    }

    
    /**
     * @return the flagAssociaRifContSecondarioValue
     */
    public Boolean getFlagAssociaRifContSecondarioValue () {
        return flagAssociaRifContSecondarioValue;
    }

    
    /**
     * @return the flagAssociaRifContPrimarioValue
     */
    public Boolean getFlagAssociaRifContPrimarioValue () {
        return flagAssociaRifContPrimarioValue;
    }

    
    /**
     * @return the storico
     */
    public List<RiferimentoContabileStoricoVO> getStorico () {
        return storico;
    }

    
    /**
     * @return the mbModalita
     */
    public String getMbModalita () {
        return mbModalita;
    }

    
    /**
     * @return the mbEnteSecondario
     */
    public String getMbEnteSecondario () {
        return mbEnteSecondario;
    }

    
    /**
     * @return the mbCodiceVersamentoAssociato
     */
    public String getMbCodiceVersamentoAssociato () {
        return mbCodiceVersamentoAssociato;
    }

    
    /**
     * @param id the id to set
     */
    public void setId ( Long id ) {
        this.id = id;
    }

    
    /**
     * @param annoAccertamento the annoAccertamento to set
     */
    public void setAnnoAccertamento ( Integer annoAccertamento ) {
        this.annoAccertamento = annoAccertamento;
    }

    
    /**
     * @param annoEsercizio the annoEsercizio to set
     */
    public void setAnnoEsercizio ( Integer annoEsercizio ) {
        this.annoEsercizio = annoEsercizio;
    }

    
    /**
     * @param dataFineValidita the dataFineValidita to set
     */
    public void setDataFineValidita ( Date dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

    
    /**
     * @param dataInizioValidita the dataInizioValidita to set
     */
    public void setDataInizioValidita ( Date dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    
    /**
     * @param datoSpecificoRiscossione the datoSpecificoRiscossione to set
     */
    public void setDatoSpecificoRiscossione ( String datoSpecificoRiscossione ) {
        this.datoSpecificoRiscossione = datoSpecificoRiscossione;
    }

    
    /**
     * @param descrizioneDatoSpecificoRiscossione the descrizioneDatoSpecificoRiscossione to set
     */
    public void setDescrizioneDatoSpecificoRiscossione ( String descrizioneDatoSpecificoRiscossione ) {
        this.descrizioneDatoSpecificoRiscossione = descrizioneDatoSpecificoRiscossione;
    }

    
    /**
     * @param codiceDatoSpecificoRiscossione the codiceDatoSpecificoRiscossione to set
     */
    public void setCodiceDatoSpecificoRiscossione ( String codiceDatoSpecificoRiscossione ) {
        this.codiceDatoSpecificoRiscossione = codiceDatoSpecificoRiscossione;
    }

    
    /**
     * @param descrizioneErroreAggiornamento the descrizioneErroreAggiornamento to set
     */
    public void setDescrizioneErroreAggiornamento ( String descrizioneErroreAggiornamento ) {
        this.descrizioneErroreAggiornamento = descrizioneErroreAggiornamento;
    }

    
    /**
     * @param livelloPdc the livelloPdc to set
     */
    public void setLivelloPdc ( String livelloPdc ) {
        this.livelloPdc = livelloPdc;
    }

    
    /**
     * @param numeroAccertamento the numeroAccertamento to set
     */
    public void setNumeroAccertamento ( Integer numeroAccertamento ) {
        this.numeroAccertamento = numeroAccertamento;
    }

    
    /**
     * @param numeroArticolo the numeroArticolo to set
     */
    public void setNumeroArticolo ( Integer numeroArticolo ) {
        this.numeroArticolo = numeroArticolo;
    }

    
    /**
     * @param numeroCapitolo the numeroCapitolo to set
     */
    public void setNumeroCapitolo ( String numeroCapitolo ) {
        this.numeroCapitolo = numeroCapitolo;
    }

    
    /**
     * @param titolo the titolo to set
     */
    public void setTitolo ( String titolo ) {
        this.titolo = titolo;
    }

    
    /**
     * @param categoria the categoria to set
     */
    public void setCategoria ( String categoria ) {
        this.categoria = categoria;
    }

    
    /**
     * @param codiceStatoAggiornamento the codiceStatoAggiornamento to set
     */
    public void setCodiceStatoAggiornamento ( String codiceStatoAggiornamento ) {
        this.codiceStatoAggiornamento = codiceStatoAggiornamento;
    }

    
    /**
     * @param descrizioneStatoAggiornamento the descrizioneStatoAggiornamento to set
     */
    public void setDescrizioneStatoAggiornamento ( String descrizioneStatoAggiornamento ) {
        this.descrizioneStatoAggiornamento = descrizioneStatoAggiornamento;
    }

    
    /**
     * @param codiceTipologiaDatoSpecificoRiscossione the codiceTipologiaDatoSpecificoRiscossione to set
     */
    public void setCodiceTipologiaDatoSpecificoRiscossione ( String codiceTipologiaDatoSpecificoRiscossione ) {
        this.codiceTipologiaDatoSpecificoRiscossione = codiceTipologiaDatoSpecificoRiscossione;
    }

    
    /**
     * @param descrizioneTipologiaDatoSpecificoRiscossione the descrizioneTipologiaDatoSpecificoRiscossione to set
     */
    public void setDescrizioneTipologiaDatoSpecificoRiscossione ( String descrizioneTipologiaDatoSpecificoRiscossione ) {
        this.descrizioneTipologiaDatoSpecificoRiscossione = descrizioneTipologiaDatoSpecificoRiscossione;
    }

    
    /**
     * @param idTassonomia the idTassonomia to set
     */
    public void setIdTassonomia ( Long idTassonomia ) {
        this.idTassonomia = idTassonomia;
    }

    
    /**
     * @param tipologia the tipologia to set
     */
    public void setTipologia ( String tipologia ) {
        this.tipologia = tipologia;
    }

    
    /**
     * @param idCodiceVersamento the idCodiceVersamento to set
     */
    public void setIdCodiceVersamento ( Long idCodiceVersamento ) {
        this.idCodiceVersamento = idCodiceVersamento;
    }

    
    /**
     * @param codiceCodiceVersamento the codiceCodiceVersamento to set
     */
    public void setCodiceCodiceVersamento ( String codiceCodiceVersamento ) {
        this.codiceCodiceVersamento = codiceCodiceVersamento;
    }

    
    /**
     * @param descrizioneCodiceVersamento the descrizioneCodiceVersamento to set
     */
    public void setDescrizioneCodiceVersamento ( String descrizioneCodiceVersamento ) {
        this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
    }

    
    /**
     * @param codiceVoceEntrata the codiceVoceEntrata to set
     */
    public void setCodiceVoceEntrata ( String codiceVoceEntrata ) {
        this.codiceVoceEntrata = codiceVoceEntrata;
    }

    
    /**
     * @param descrizioneVoceEntrata the descrizioneVoceEntrata to set
     */
    public void setDescrizioneVoceEntrata ( String descrizioneVoceEntrata ) {
        this.descrizioneVoceEntrata = descrizioneVoceEntrata;
    }

    
    /**
     * @param codiceMacrotipo the codiceMacrotipo to set
     */
    public void setCodiceMacrotipo ( String codiceMacrotipo ) {
        this.codiceMacrotipo = codiceMacrotipo;
    }

    
    /**
     * @param descrizioneMacrotipo the descrizioneMacrotipo to set
     */
    public void setDescrizioneMacrotipo ( String descrizioneMacrotipo ) {
        this.descrizioneMacrotipo = descrizioneMacrotipo;
    }

    
    /**
     * @param codiceTematica the codiceTematica to set
     */
    public void setCodiceTematica ( String codiceTematica ) {
        this.codiceTematica = codiceTematica;
    }

    
    /**
     * @param descrizioneTematica the descrizioneTematica to set
     */
    public void setDescrizioneTematica ( String descrizioneTematica ) {
        this.descrizioneTematica = descrizioneTematica;
    }

    
    /**
     * @param inVita the inVita to set
     */
    public void setInVita ( Boolean inVita ) {
        this.inVita = inVita;
    }

    
    /**
     * @param flagPrincipale the flagPrincipale to set
     */
    public void setFlagPrincipale ( Boolean flagPrincipale ) {
        this.flagPrincipale = flagPrincipale;
    }

    
    /**
     * @param dataFineValiditaCodiceTassonomico the dataFineValiditaCodiceTassonomico to set
     */
    public void setDataFineValiditaCodiceTassonomico ( Date dataFineValiditaCodiceTassonomico ) {
        this.dataFineValiditaCodiceTassonomico = dataFineValiditaCodiceTassonomico;
    }

    
    /**
     * @param dataInizioValiditaCodiceTassonomico the dataInizioValiditaCodiceTassonomico to set
     */
    public void setDataInizioValiditaCodiceTassonomico ( Date dataInizioValiditaCodiceTassonomico ) {
        this.dataInizioValiditaCodiceTassonomico = dataInizioValiditaCodiceTassonomico;
    }

    
    /**
     * @param tipoPagamento the tipoPagamento to set
     */
    public void setTipoPagamento ( String tipoPagamento ) {
        this.tipoPagamento = tipoPagamento;
    }

    
    /**
     * @param flagValoreInizialeIdCodiceVersamento the flagValoreInizialeIdCodiceVersamento to set
     */
    public void setFlagValoreInizialeIdCodiceVersamento ( Boolean flagValoreInizialeIdCodiceVersamento ) {
        this.flagValoreInizialeIdCodiceVersamento = flagValoreInizialeIdCodiceVersamento;
    }

    
    /**
     * @param flagElementiMultibeneficiario the flagElementiMultibeneficiario to set
     */
    public void setFlagElementiMultibeneficiario ( Boolean flagElementiMultibeneficiario ) {
        this.flagElementiMultibeneficiario = flagElementiMultibeneficiario;
    }

    
    /**
     * @param flagAssociaRifContSecondario the flagAssociaRifContSecondario to set
     */
    public void setFlagAssociaRifContSecondario ( Boolean flagAssociaRifContSecondario ) {
        this.flagAssociaRifContSecondario = flagAssociaRifContSecondario;
    }

    
    /**
     * @param flagAssociaRifContPrimario the flagAssociaRifContPrimario to set
     */
    public void setFlagAssociaRifContPrimario ( Boolean flagAssociaRifContPrimario ) {
        this.flagAssociaRifContPrimario = flagAssociaRifContPrimario;
    }

    
    /**
     * @param enteSecondarioAssociato the enteSecondarioAssociato to set
     */
    public void setEnteSecondarioAssociato ( String enteSecondarioAssociato ) {
        this.enteSecondarioAssociato = enteSecondarioAssociato;
    }

    
    /**
     * @param covSecondarioAssociato the covSecondarioAssociato to set
     */
    public void setCovSecondarioAssociato ( String covSecondarioAssociato ) {
        this.covSecondarioAssociato = covSecondarioAssociato;
    }

    
    /**
     * @param idRifContabileSecondarioAssociato the idRifContabileSecondarioAssociato to set
     */
    public void setIdRifContabileSecondarioAssociato ( Long idRifContabileSecondarioAssociato ) {
        this.idRifContabileSecondarioAssociato = idRifContabileSecondarioAssociato;
    }

    
    /**
     * @param flagAssociaRifContSecondarioValue the flagAssociaRifContSecondarioValue to set
     */
    public void setFlagAssociaRifContSecondarioValue ( Boolean flagAssociaRifContSecondarioValue ) {
        this.flagAssociaRifContSecondarioValue = flagAssociaRifContSecondarioValue;
    }

    
    /**
     * @param flagAssociaRifContPrimarioValue the flagAssociaRifContPrimarioValue to set
     */
    public void setFlagAssociaRifContPrimarioValue ( Boolean flagAssociaRifContPrimarioValue ) {
        this.flagAssociaRifContPrimarioValue = flagAssociaRifContPrimarioValue;
    }

    
    /**
     * @param storico the storico to set
     */
    public void setStorico ( List<RiferimentoContabileStoricoVO> storico ) {
        this.storico = storico;
    }

    
    /**
     * @param mbModalita the mbModalita to set
     */
    public void setMbModalita ( String mbModalita ) {
        this.mbModalita = mbModalita;
    }

    
    /**
     * @param mbEnteSecondario the mbEnteSecondario to set
     */
    public void setMbEnteSecondario ( String mbEnteSecondario ) {
        this.mbEnteSecondario = mbEnteSecondario;
    }

    
    /**
     * @param mbCodiceVersamentoAssociato the mbCodiceVersamentoAssociato to set
     */
    public void setMbCodiceVersamentoAssociato ( String mbCodiceVersamentoAssociato ) {
        this.mbCodiceVersamentoAssociato = mbCodiceVersamentoAssociato;
    }
    
    


    
    /**
     * @return the rifContabileSecondarioAssociato
     */
    public String getRifContabileSecondarioAssociato () {
        return rifContabileSecondarioAssociato;
    }


    
    /**
     * @param rifContabileSecondarioAssociato the rifContabileSecondarioAssociato to set
     */
    public void setRifContabileSecondarioAssociato ( String rifContabileSecondarioAssociato ) {
        this.rifContabileSecondarioAssociato = rifContabileSecondarioAssociato;
    }

    
    /**
     * @return the datoSpecificoRiscossioneTassonomia
     */
    public String getDatoSpecificoRiscossioneTassonomia () {
        return datoSpecificoRiscossioneTassonomia;
    }


    
    /**
     * @param datoSpecificoRiscossioneTassonomia the datoSpecificoRiscossioneTassonomia to set
     */
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

	public String getCodiceTributo () {
		return codiceTributo;
	}

	public void setCodiceTributo ( String codiceTributo ) {
		this.codiceTributo = codiceTributo;
	}

	/* (non-Javadoc)
     * @see java.lang.Object#toString()
     */

	@Override public String toString () {
		return "RiferimentoContabileVO{" +
						"id=" + id +
						", annoAccertamento=" + annoAccertamento +
						", annoEsercizio=" + annoEsercizio +
						", dataFineValidita=" + dataFineValidita +
						", dataInizioValidita=" + dataInizioValidita +
						", datoSpecificoRiscossione='" + datoSpecificoRiscossione + '\'' +
						", datoSpecificoRiscossioneTassonomia='" + datoSpecificoRiscossioneTassonomia + '\'' +
						", descrizioneDatoSpecificoRiscossione='" + descrizioneDatoSpecificoRiscossione + '\'' +
						", descrizioneDatoSpecificoRiscossioneRifCont='" + descrizioneDatoSpecificoRiscossioneRifCont + '\'' +
						", codiceDatoSpecificoRiscossione='" + codiceDatoSpecificoRiscossione + '\'' +
						", descrizioneErroreAggiornamento='" + descrizioneErroreAggiornamento + '\'' +
						", livelloPdc='" + livelloPdc + '\'' +
						", numeroAccertamento=" + numeroAccertamento +
						", numeroArticolo=" + numeroArticolo +
						", numeroCapitolo='" + numeroCapitolo + '\'' +
						", titolo='" + titolo + '\'' +
						", categoria='" + categoria + '\'' +
						", codiceStatoAggiornamento='" + codiceStatoAggiornamento + '\'' +
						", descrizioneStatoAggiornamento='" + descrizioneStatoAggiornamento + '\'' +
						", codiceTipologiaDatoSpecificoRiscossione='" + codiceTipologiaDatoSpecificoRiscossione + '\'' +
						", descrizioneTipologiaDatoSpecificoRiscossione='" + descrizioneTipologiaDatoSpecificoRiscossione + '\'' +
						", idTassonomia=" + idTassonomia +
						", tipologia='" + tipologia + '\'' +
						", idCodiceVersamento=" + idCodiceVersamento +
						", codiceCodiceVersamento='" + codiceCodiceVersamento + '\'' +
						", descrizioneCodiceVersamento='" + descrizioneCodiceVersamento + '\'' +
						", codiceVoceEntrata='" + codiceVoceEntrata + '\'' +
						", descrizioneVoceEntrata='" + descrizioneVoceEntrata + '\'' +
						", codiceMacrotipo='" + codiceMacrotipo + '\'' +
						", descrizioneMacrotipo='" + descrizioneMacrotipo + '\'' +
						", codiceTematica='" + codiceTematica + '\'' +
						", descrizioneTematica='" + descrizioneTematica + '\'' +
						", inVita=" + inVita +
						", flagPrincipale=" + flagPrincipale +
						", dataFineValiditaCodiceTassonomico=" + dataFineValiditaCodiceTassonomico +
						", dataInizioValiditaCodiceTassonomico=" + dataInizioValiditaCodiceTassonomico +
						", tipoPagamento='" + tipoPagamento + '\'' +
						", flagValoreInizialeIdCodiceVersamento=" + flagValoreInizialeIdCodiceVersamento +
						", flagElementiMultibeneficiario=" + flagElementiMultibeneficiario +
						", flagAssociaRifContSecondario=" + flagAssociaRifContSecondario +
						", flagAssociaRifContPrimario=" + flagAssociaRifContPrimario +
						", enteSecondarioAssociato='" + enteSecondarioAssociato + '\'' +
						", covSecondarioAssociato='" + covSecondarioAssociato + '\'' +
						", idRifContabileSecondarioAssociato=" + idRifContabileSecondarioAssociato +
						", rifContabileSecondarioAssociato='" + rifContabileSecondarioAssociato + '\'' +
						", flagAssociaRifContSecondarioValue=" + flagAssociaRifContSecondarioValue +
						", flagAssociaRifContPrimarioValue=" + flagAssociaRifContPrimarioValue +
						", storico=" + storico +
						", mbModalita='" + mbModalita + '\'' +
						", mbEnteSecondario='" + mbEnteSecondario + '\'' +
						", mbCodiceVersamentoAssociato='" + mbCodiceVersamentoAssociato + '\'' +
						", tipoServizioTassonomia='" + tipoServizioTassonomia + '\'' +
						", codiceTributo='" + codiceTributo + '\'' +
						'}';
	}
}
