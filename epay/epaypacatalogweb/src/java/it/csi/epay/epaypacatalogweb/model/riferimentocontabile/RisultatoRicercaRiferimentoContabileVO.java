/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.riferimentocontabile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class RisultatoRicercaRiferimentoContabileVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nomeEnte;

    private String tipoEnte;

    private Integer annoAccertamento;

    private Integer annoEsercizio;

    private Date dataFineValidita;

    private Date dataInizioValidita;

    private String datoSpecificoRiscossione;

    private String datoSpecificoRiscossioneTassonomia;

    private String descrizioneDatoSpecificoRiscossione;

    private String descrizioneErroreAggiornamento;

    private String livelloPdc;

    private Integer numeroAccertamento;

    private Integer numeroArticolo;

    private String numeroCapitolo;

    private String titolo;

    private String categoria;

    private String codiceTipologiaDatoSpecificoRiscossione;

    private String descrizioneTipologiaDatoSpecificoRiscossione;

    private String tipologia;

    private Long idCodiceVersamento;

    private String codiceCodiceVersamento;

    private String descrizioneCodiceVersamento;

    private String ibanAppoggioCodiceVersamento;

    private String ibanCodiceVersamento;

    private boolean fatturaCodiceVersamento;

    private String mailCodiceVersamento;

    private String modalitaIntegrazioneCodiceVersamento;

    private String noteCodiceVersamento;

    private boolean statoCodiceVersamento;

    private String tipologiaCodiceVersamento;

    private String codiceVoceEntrata;

    private String descrizioneVoceEntrata;

    private String codiceMacrotipo;

    private String descrizioneMacrotipo;

    private String codiceTematica;

    private String descrizioneTematica;

    private Boolean inVita;

    private Boolean flagPrincipale;

    private String tipoServizio;

    private Date dataFineValiditaCodiceTassonomico;

    private Boolean flagMbPrimario;

    private Boolean flagMbSecondario;

    private Date dataInizioValiditaCodiceTassonomico;

    private List<RisultatoRicercaRiferimentoContabileStoricoVO> storico;

	public boolean isStorico () {
		return false;
	}
    /**
     * @return the id
     */
    public Long getId () {
        return id;
    }

    /**
     * @return the nomeEnte
     */
    public String getNomeEnte () {
        return nomeEnte;
    }

    /**
     * @return the tipoEnte
     */
    public String getTipoEnte () {
        return tipoEnte;
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
     * @return the datoSpecificoRiscossioneTassonomia
     */
    public String getDatoSpecificoRiscossioneTassonomia () {
        return datoSpecificoRiscossioneTassonomia;
    }

    /**
     * @return the descrizioneDatoSpecificoRiscossione
     */
    public String getDescrizioneDatoSpecificoRiscossione () {
        return descrizioneDatoSpecificoRiscossione;
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
     * @return the ibanAppoggioCodiceVersamento
     */
    public String getIbanAppoggioCodiceVersamento () {
        return ibanAppoggioCodiceVersamento;
    }

    /**
     * @return the ibanCodiceVersamento
     */
    public String getIbanCodiceVersamento () {
        return ibanCodiceVersamento;
    }

    /**
     * @return the fatturaCodiceVersamento
     */
    public boolean isFatturaCodiceVersamento () {
        return fatturaCodiceVersamento;
    }

    /**
     * @return the mailCodiceVersamento
     */
    public String getMailCodiceVersamento () {
        return mailCodiceVersamento;
    }

    /**
     * @return the modalitaIntegrazioneCodiceVersamento
     */
    public String getModalitaIntegrazioneCodiceVersamento () {
        return modalitaIntegrazioneCodiceVersamento;
    }

    /**
     * @return the noteCodiceVersamento
     */
    public String getNoteCodiceVersamento () {
        return noteCodiceVersamento;
    }

    /**
     * @return the statoCodiceVersamento
     */
    public boolean isStatoCodiceVersamento () {
        return statoCodiceVersamento;
    }

    /**
     * @return the tipologiaCodiceVersamento
     */
    public String getTipologiaCodiceVersamento () {
        return tipologiaCodiceVersamento;
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
     * @return the tipoServizio
     */
    public String getTipoServizio () {
        return tipoServizio;
    }

    /**
     * @return the dataFineValiditaCodiceTassonomico
     */
    public Date getDataFineValiditaCodiceTassonomico () {
        return dataFineValiditaCodiceTassonomico;
    }

    /**
     * @return the flagMbPrimario
     */
    public Boolean getFlagMbPrimario () {
        return flagMbPrimario;
    }

    /**
     * @return the flagMbSecondario
     */
    public Boolean getFlagMbSecondario () {
        return flagMbSecondario;
    }

    /**
     * @return the dataInizioValiditaCodiceTassonomico
     */
    public Date getDataInizioValiditaCodiceTassonomico () {
        return dataInizioValiditaCodiceTassonomico;
    }

    /**
     * @return the storico
     */
    public List<RisultatoRicercaRiferimentoContabileStoricoVO> getStorico () {
        return storico;
    }

    /**
     * @param id the id to set
     */
    public void setId ( Long id ) {
        this.id = id;
    }

    /**
     * @param nomeEnte the nomeEnte to set
     */
    public void setNomeEnte ( String nomeEnte ) {
        this.nomeEnte = nomeEnte;
    }

    /**
     * @param tipoEnte the tipoEnte to set
     */
    public void setTipoEnte ( String tipoEnte ) {
        this.tipoEnte = tipoEnte;
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
     * @param datoSpecificoRiscossioneTassonomia the datoSpecificoRiscossioneTassonomia to set
     */
    public void setDatoSpecificoRiscossioneTassonomia ( String datoSpecificoRiscossioneTassonomia ) {
        this.datoSpecificoRiscossioneTassonomia = datoSpecificoRiscossioneTassonomia;
    }

    /**
     * @param descrizioneDatoSpecificoRiscossione the descrizioneDatoSpecificoRiscossione to set
     */
    public void setDescrizioneDatoSpecificoRiscossione ( String descrizioneDatoSpecificoRiscossione ) {
        this.descrizioneDatoSpecificoRiscossione = descrizioneDatoSpecificoRiscossione;
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
     * @param ibanAppoggioCodiceVersamento the ibanAppoggioCodiceVersamento to set
     */
    public void setIbanAppoggioCodiceVersamento ( String ibanAppoggioCodiceVersamento ) {
        this.ibanAppoggioCodiceVersamento = ibanAppoggioCodiceVersamento;
    }

    /**
     * @param ibanCodiceVersamento the ibanCodiceVersamento to set
     */
    public void setIbanCodiceVersamento ( String ibanCodiceVersamento ) {
        this.ibanCodiceVersamento = ibanCodiceVersamento;
    }

    /**
     * @param fatturaCodiceVersamento the fatturaCodiceVersamento to set
     */
    public void setFatturaCodiceVersamento ( boolean fatturaCodiceVersamento ) {
        this.fatturaCodiceVersamento = fatturaCodiceVersamento;
    }

    /**
     * @param mailCodiceVersamento the mailCodiceVersamento to set
     */
    public void setMailCodiceVersamento ( String mailCodiceVersamento ) {
        this.mailCodiceVersamento = mailCodiceVersamento;
    }

    /**
     * @param modalitaIntegrazioneCodiceVersamento the modalitaIntegrazioneCodiceVersamento to set
     */
    public void setModalitaIntegrazioneCodiceVersamento ( String modalitaIntegrazioneCodiceVersamento ) {
        this.modalitaIntegrazioneCodiceVersamento = modalitaIntegrazioneCodiceVersamento;
    }

    /**
     * @param noteCodiceVersamento the noteCodiceVersamento to set
     */
    public void setNoteCodiceVersamento ( String noteCodiceVersamento ) {
        this.noteCodiceVersamento = noteCodiceVersamento;
    }

    /**
     * @param statoCodiceVersamento the statoCodiceVersamento to set
     */
    public void setStatoCodiceVersamento ( boolean statoCodiceVersamento ) {
        this.statoCodiceVersamento = statoCodiceVersamento;
    }

    /**
     * @param tipologiaCodiceVersamento the tipologiaCodiceVersamento to set
     */
    public void setTipologiaCodiceVersamento ( String tipologiaCodiceVersamento ) {
        this.tipologiaCodiceVersamento = tipologiaCodiceVersamento;
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
     * @param tipoServizio the tipoServizio to set
     */
    public void setTipoServizio ( String tipoServizio ) {
        this.tipoServizio = tipoServizio;
    }

    /**
     * @param dataFineValiditaCodiceTassonomico the dataFineValiditaCodiceTassonomico to set
     */
    public void setDataFineValiditaCodiceTassonomico ( Date dataFineValiditaCodiceTassonomico ) {
        this.dataFineValiditaCodiceTassonomico = dataFineValiditaCodiceTassonomico;
    }

    /**
     * @param flagMbPrimario the flagMbPrimario to set
     */
    public void setFlagMbPrimario ( Boolean flagMbPrimario ) {
        this.flagMbPrimario = flagMbPrimario;
    }

    /**
     * @param flagMbSecondario the flagMbSecondario to set
     */
    public void setFlagMbSecondario ( Boolean flagMbSecondario ) {
        this.flagMbSecondario = flagMbSecondario;
    }

    /**
     * @param dataInizioValiditaCodiceTassonomico the dataInizioValiditaCodiceTassonomico to set
     */
    public void setDataInizioValiditaCodiceTassonomico ( Date dataInizioValiditaCodiceTassonomico ) {
        this.dataInizioValiditaCodiceTassonomico = dataInizioValiditaCodiceTassonomico;
    }

    /**
     * @param storico the storico to set
     */
    public void setStorico ( List<RisultatoRicercaRiferimentoContabileStoricoVO> storico ) {
        this.storico = storico;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        StringBuilder builder = new StringBuilder ();
        builder.append ( "RisultatoRicercaRiferimentoContabileVO [id=" );
        builder.append ( id );
        builder.append ( ", nomeEnte=" );
        builder.append ( nomeEnte );
        builder.append ( ", tipoEnte=" );
        builder.append ( tipoEnte );
        builder.append ( ", annoAccertamento=" );
        builder.append ( annoAccertamento );
        builder.append ( ", annoEsercizio=" );
        builder.append ( annoEsercizio );
        builder.append ( ", dataFineValidita=" );
        builder.append ( dataFineValidita );
        builder.append ( ", dataInizioValidita=" );
        builder.append ( dataInizioValidita );
        builder.append ( ", datoSpecificoRiscossione=" );
        builder.append ( datoSpecificoRiscossione );
        builder.append ( ", datoSpecificoRiscossioneTassonomia=" );
        builder.append ( datoSpecificoRiscossioneTassonomia );
        builder.append ( ", descrizioneDatoSpecificoRiscossione=" );
        builder.append ( descrizioneDatoSpecificoRiscossione );
        builder.append ( ", descrizioneErroreAggiornamento=" );
        builder.append ( descrizioneErroreAggiornamento );
        builder.append ( ", livelloPdc=" );
        builder.append ( livelloPdc );
        builder.append ( ", numeroAccertamento=" );
        builder.append ( numeroAccertamento );
        builder.append ( ", numeroArticolo=" );
        builder.append ( numeroArticolo );
        builder.append ( ", numeroCapitolo=" );
        builder.append ( numeroCapitolo );
        builder.append ( ", titolo=" );
        builder.append ( titolo );
        builder.append ( ", categoria=" );
        builder.append ( categoria );
        builder.append ( ", codiceTipologiaDatoSpecificoRiscossione=" );
        builder.append ( codiceTipologiaDatoSpecificoRiscossione );
        builder.append ( ", descrizioneTipologiaDatoSpecificoRiscossione=" );
        builder.append ( descrizioneTipologiaDatoSpecificoRiscossione );
        builder.append ( ", tipologia=" );
        builder.append ( tipologia );
        builder.append ( ", idCodiceVersamento=" );
        builder.append ( idCodiceVersamento );
        builder.append ( ", codiceCodiceVersamento=" );
        builder.append ( codiceCodiceVersamento );
        builder.append ( ", descrizioneCodiceVersamento=" );
        builder.append ( descrizioneCodiceVersamento );
        builder.append ( ", ibanAppoggioCodiceVersamento=" );
        builder.append ( ibanAppoggioCodiceVersamento );
        builder.append ( ", ibanCodiceVersamento=" );
        builder.append ( ibanCodiceVersamento );
        builder.append ( ", fatturaCodiceVersamento=" );
        builder.append ( fatturaCodiceVersamento );
        builder.append ( ", mailCodiceVersamento=" );
        builder.append ( mailCodiceVersamento );
        builder.append ( ", modalitaIntegrazioneCodiceVersamento=" );
        builder.append ( modalitaIntegrazioneCodiceVersamento );
        builder.append ( ", noteCodiceVersamento=" );
        builder.append ( noteCodiceVersamento );
        builder.append ( ", statoCodiceVersamento=" );
        builder.append ( statoCodiceVersamento );
        builder.append ( ", tipologiaCodiceVersamento=" );
        builder.append ( tipologiaCodiceVersamento );
        builder.append ( ", codiceVoceEntrata=" );
        builder.append ( codiceVoceEntrata );
        builder.append ( ", descrizioneVoceEntrata=" );
        builder.append ( descrizioneVoceEntrata );
        builder.append ( ", codiceMacrotipo=" );
        builder.append ( codiceMacrotipo );
        builder.append ( ", descrizioneMacrotipo=" );
        builder.append ( descrizioneMacrotipo );
        builder.append ( ", codiceTematica=" );
        builder.append ( codiceTematica );
        builder.append ( ", descrizioneTematica=" );
        builder.append ( descrizioneTematica );
        builder.append ( ", inVita=" );
        builder.append ( inVita );
        builder.append ( ", flagPrincipale=" );
        builder.append ( flagPrincipale );
        builder.append ( ", tipoServizio=" );
        builder.append ( tipoServizio );
        builder.append ( ", dataFineValiditaCodiceTassonomico=" );
        builder.append ( dataFineValiditaCodiceTassonomico );
        builder.append ( ", flagMbPrimario=" );
        builder.append ( flagMbPrimario );
        builder.append ( ", flagMbSecondario=" );
        builder.append ( flagMbSecondario );
        builder.append ( ", dataInizioValiditaCodiceTassonomico=" );
        builder.append ( dataInizioValiditaCodiceTassonomico );
        builder.append ( ", storico=" );
        builder.append ( storico );
        builder.append ( "]" );
        return builder.toString ();
    }

}
