/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;


public class EsitoChiamataEsterna implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3697583317139894087L;

    private String codice;

    private String descrizione;

	public static final String OPERAZIONE_COMPLETATA_CON_SUCCESSO = "000";

	public static final String ERRORE_GENERICO = "100";

	public static final String ERRORE_GESTIONALE_NON_AUTORIZZATO_AL_TIPO_PAGAMENTO = "200";

	public static final String ERRORE_DATI_COMPONENTI_NON_CONGRUENTI = "300";


    public String getCodice () {
        return codice;
    }

    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

}
