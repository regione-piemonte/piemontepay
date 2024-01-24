/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model.v1;

import java.io.Serializable;


/**
 *
 */

public class EliminaPosizioneDebitoriaChiamanteEsternoInput extends AccessoChiamanteEsternoSincronoSplitInput implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -272357966201291864L;

    private String codiceFiscaleEnte;

    private String tipoPagamento;

    private String motivazione;

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

}
