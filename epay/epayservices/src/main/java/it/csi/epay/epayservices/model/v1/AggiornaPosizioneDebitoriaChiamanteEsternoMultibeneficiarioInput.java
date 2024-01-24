/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model.v1;

import it.csi.epay.epayservices.model.ComponentiImporto;
import it.csi.epay.epayservices.model.ComponentiImportoSecondario;
import it.csi.epay.epayservices.model.RiferimentiPagamento;
import it.csi.epay.epayservices.model.SoggettoPagatore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 *
 */

public class AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput extends AccessoChiamanteEsternoSincronoSplitInput implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 6198248829369183212L;

    private String codiceFiscaleEnte;

    private String tipoPagamento;

    private Date dataScadenza;

    private Date dataInizioValidita;

    private Date dataFineValidita;

    private BigDecimal importoTotale;

    private List<ComponentiImporto> componentiImporto;

    private String descrizioneCausaleVersamento;

    private String motivazione;

    private List<RiferimentiPagamento> riferimentiPagamento;

    private SoggettoPagatore soggettoPagatore;

    private BigDecimal importoPrincipale;

    private BigDecimal importoSecondario;

    private List<ComponentiImportoSecondario> componentiImportoSecondario;
    

    private Boolean requiresCostUpdate;
    
   
    public Boolean getRequiresCostUpdate() {
		return requiresCostUpdate;
	}

	public void setRequiresCostUpdate(Boolean requiresCostUpdate) {
		this.requiresCostUpdate = requiresCostUpdate;
	}

    /**
     * @return the codiceFiscaleEnte
     */
    public String getCodiceFiscaleEnte () {
        return codiceFiscaleEnte;
    }

    /**
     * @param codiceFiscaleEnte the codiceFiscaleEnte to set
     */
    public void setCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
    }

    /**
     * @return the tipoPagamento
     */
    public String getTipoPagamento () {
        return tipoPagamento;
    }

    /**
     * @param tipoPagamento the tipoPagamento to set
     */
    public void setTipoPagamento ( String tipoPagamento ) {
        this.tipoPagamento = tipoPagamento;
    }

    /**
     * @return the dataScadenza
     */
    public Date getDataScadenza () {
        return dataScadenza;
    }

    /**
     * @param dataScadenza the dataScadenza to set
     */
    public void setDataScadenza ( Date dataScadenza ) {
        this.dataScadenza = dataScadenza;
    }

    /**
     * @return the dataInizioValidita
     */
    public Date getDataInizioValidita () {
        return dataInizioValidita;
    }

    /**
     * @param dataInizioValidita the dataInizioValidita to set
     */
    public void setDataInizioValidita ( Date dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    /**
     * @return the dataFineValidita
     */
    public Date getDataFineValidita () {
        return dataFineValidita;
    }

    /**
     * @param dataFineValidita the dataFineValidita to set
     */
    public void setDataFineValidita ( Date dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

    /**
     * @return the importoTotale
     */
    public BigDecimal getImportoTotale () {
        return importoTotale;
    }

    /**
     * @param importoTotale the importoTotale to set
     */
    public void setImportoTotale ( BigDecimal importoTotale ) {
        this.importoTotale = importoTotale;
    }

    /**
     * @return the componentiImporto
     */
    public List<ComponentiImporto> getComponentiImporto () {
        return componentiImporto;
    }

    /**
     * @param componentiImporto the componentiImporto to set
     */
    public void setComponentiImporto ( List<ComponentiImporto> componentiImporto ) {
        this.componentiImporto = componentiImporto;
    }

    /**
     * @return the descrizioneCausaleVersamento
     */
    public String getDescrizioneCausaleVersamento () {
        return descrizioneCausaleVersamento;
    }

    /**
     * @param descrizioneCausaleVersamento the descrizioneCausaleVersamento to set
     */
    public void setDescrizioneCausaleVersamento ( String descrizioneCausaleVersamento ) {
        this.descrizioneCausaleVersamento = descrizioneCausaleVersamento;
    }

    /**
     * @return the motivazione
     */
    public String getMotivazione () {
        return motivazione;
    }

    /**
     * @param motivazione the motivazione to set
     */
    public void setMotivazione ( String motivazione ) {
        this.motivazione = motivazione;
    }

    /**
     * @return the riferimentiPagamento
     */
    public List<RiferimentiPagamento> getRiferimentiPagamento () {
        return riferimentiPagamento;
    }

    /**
     * @param riferimentiPagamento the riferimentiPagamento to set
     */
    public void setRiferimentiPagamento ( List<RiferimentiPagamento> riferimentiPagamento ) {
        this.riferimentiPagamento = riferimentiPagamento;
    }

    /**
     * @return the soggettoPagatore
     */
    public SoggettoPagatore getSoggettoPagatore () {
        return soggettoPagatore;
    }

    /**
     * @param soggettoPagatore the soggettoPagatore to set
     */
    public void setSoggettoPagatore ( SoggettoPagatore soggettoPagatore ) {
        this.soggettoPagatore = soggettoPagatore;
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

}
