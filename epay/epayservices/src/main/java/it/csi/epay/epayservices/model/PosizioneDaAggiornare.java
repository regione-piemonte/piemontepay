/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class PosizioneDaAggiornare implements Serializable {

    private static final long serialVersionUID = 4772299633029031248L;

    private String tipoAggiornamento;

    private String idPosizioneDebitoria;

    private Date dataScadenza;

    private Date dataInizioValidita;

    private Date dataFineValidita;

    private BigDecimal importoTotale;

    private List<ComponentiImporto> componentiImporto;

    private String descrizioneCausaleVersamento;

    private String motivazione;

    private List<RiferimentiPagamento> riferimentiPagamento;

    private SoggettoPagatore soggettoPagatore;

    /**
     * @return the tipoAggiornamento
     */
    public String getTipoAggiornamento () {
        return tipoAggiornamento;
    }

    /**
     * @return the idPosizioneDebitoria
     */
    public String getIdPosizioneDebitoria () {
        return idPosizioneDebitoria;
    }

    /**
     * @return the dataScadenza
     */
    public Date getDataScadenza () {
        return dataScadenza;
    }

    /**
     * @return the dataInizioValidita
     */
    public Date getDataInizioValidita () {
        return dataInizioValidita;
    }

    /**
     * @return the dataFineValidita
     */
    public Date getDataFineValidita () {
        return dataFineValidita;
    }

    /**
     * @return the importoTotale
     */
    public BigDecimal getImportoTotale () {
        return importoTotale;
    }

    /**
     * @return the componentiImporto
     */
    public List<ComponentiImporto> getComponentiImporto () {
        return componentiImporto;
    }

    /**
     * @return the descrizioneCausaleVersamento
     */
    public String getDescrizioneCausaleVersamento () {
        return descrizioneCausaleVersamento;
    }

    /**
     * @return the motivazione
     */
    public String getMotivazione () {
        return motivazione;
    }

    /**
     * @return the riferimentiPagamento
     */
    public List<RiferimentiPagamento> getRiferimentiPagamento () {
        return riferimentiPagamento;
    }

    /**
     * @return the soggettoPagatore
     */
    public SoggettoPagatore getSoggettoPagatore () {
        return soggettoPagatore;
    }

    /**
     * @param tipoAggiornamento the tipoAggiornamento to set
     */
    public void setTipoAggiornamento ( String tipoAggiornamento ) {
        this.tipoAggiornamento = tipoAggiornamento;
    }

    /**
     * @param idPosizioneDebitoria the idPosizioneDebitoria to set
     */
    public void setIdPosizioneDebitoria ( String idPosizioneDebitoria ) {
        this.idPosizioneDebitoria = idPosizioneDebitoria;
    }

    /**
     * @param dataScadenza the dataScadenza to set
     */
    public void setDataScadenza ( Date dataScadenza ) {
        this.dataScadenza = dataScadenza;
    }

    /**
     * @param dataInizioValidita the dataInizioValidita to set
     */
    public void setDataInizioValidita ( Date dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    /**
     * @param dataFineValidita the dataFineValidita to set
     */
    public void setDataFineValidita ( Date dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

    /**
     * @param importoTotale the importoTotale to set
     */
    public void setImportoTotale ( BigDecimal importoTotale ) {
        this.importoTotale = importoTotale;
    }

    /**
     * @param componentiImporto the componentiImporto to set
     */
    public void setComponentiImporto ( List<ComponentiImporto> componentiImporto ) {
        this.componentiImporto = componentiImporto;
    }

    /**
     * @param descrizioneCausaleVersamento the descrizioneCausaleVersamento to set
     */
    public void setDescrizioneCausaleVersamento ( String descrizioneCausaleVersamento ) {
        this.descrizioneCausaleVersamento = descrizioneCausaleVersamento;
    }

    /**
     * @param motivazione the motivazione to set
     */
    public void setMotivazione ( String motivazione ) {
        this.motivazione = motivazione;
    }

    /**
     * @param riferimentiPagamento the riferimentiPagamento to set
     */
    public void setRiferimentiPagamento ( List<RiferimentiPagamento> riferimentiPagamento ) {
        this.riferimentiPagamento = riferimentiPagamento;
    }

    /**
     * @param soggettoPagatore the soggettoPagatore to set
     */
    public void setSoggettoPagatore ( SoggettoPagatore soggettoPagatore ) {
        this.soggettoPagatore = soggettoPagatore;
    }

}
