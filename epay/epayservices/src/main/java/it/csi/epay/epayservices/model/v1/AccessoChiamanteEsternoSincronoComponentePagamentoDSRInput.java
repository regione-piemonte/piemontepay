/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model.v1;

public class AccessoChiamanteEsternoSincronoComponentePagamentoDSRInput extends AccessoChiamanteEsternoSincronoComponentePagamentoInput {

    private static final long serialVersionUID = -6903007449346036409L;

    private String datiSpecificiRiscossione;

    /**
     * @return the datiSpecificiRiscossione
     */
    public String getDatiSpecificiRiscossione () {
        return datiSpecificiRiscossione;
    }

    /**
     * @param datiSpecificiRiscossione the datiSpecificiRiscossione to set
     */
    public void setDatiSpecificiRiscossione ( String datiSpecificiRiscossione ) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        StringBuilder builder = new StringBuilder ();
        builder.append ( "AccessoChiamanteEsternoSincronoComponentePagamentoDSRInput [datiSpecificiRiscossione=" );
        builder.append ( datiSpecificiRiscossione );
        builder.append ( ", toString()=" );
        builder.append ( super.toString () );
        builder.append ( "]" );
        return builder.toString ();
    }

}
