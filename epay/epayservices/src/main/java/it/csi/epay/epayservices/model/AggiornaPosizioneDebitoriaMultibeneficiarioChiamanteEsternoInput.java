/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.List;


/**
 *
 */

public class AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoInput extends AccessoChiamanteEsternoSincronoSplitInput implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codiceFiscaleEnte;

    private String tipoPagamento;

    private Integer numeroPosizioniDebitorie;

    private List<PosizioneMultibeneficiarioDaAggiornare> elencoPosizioniDaAggiornare;

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
     * @param numeroPosizioniDebitorie the numeroPosizioniDebitorie to set
     */
    public void setNumeroPosizioniDebitorie ( Integer numeroPosizioniDebitorie ) {
        this.numeroPosizioniDebitorie = numeroPosizioniDebitorie;
    }

    /**
     * @return the numeroPosizioniDebitorie
     */
    public Integer getNumeroPosizioniDebitorie () {
        return numeroPosizioniDebitorie;
    }

    /**
     * @return the elencoPosizioniDaAggiornare
     */
    public List<PosizioneMultibeneficiarioDaAggiornare> getElencoPosizioniDaAggiornare () {
        return elencoPosizioniDaAggiornare;
    }

    /**
     * @param elencoPosizioniDaAggiornare the elencoPosizioniDaAggiornare to set
     */
    public void setElencoPosizioniDaAggiornare ( List<PosizioneMultibeneficiarioDaAggiornare> elencoPosizioniDaAggiornare ) {
        this.elencoPosizioniDaAggiornare = elencoPosizioniDaAggiornare;
    }

}
