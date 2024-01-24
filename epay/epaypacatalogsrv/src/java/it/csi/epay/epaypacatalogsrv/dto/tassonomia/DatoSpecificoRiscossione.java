/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.tassonomia;
import java.io.Serializable;


public class DatoSpecificoRiscossione implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codice;

    private String descrizione;

    private ComponentiAccertamento componentiAccertamento;
    //anno e numero accertamento

    /**
     * @return the codice
     */
    public String getCodice () {
        return codice;
    }

    /**
     * @param codice the codice to set
     */
    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    /**
     * @return the descrizione
     */
    public String getDescrizione () {
        return descrizione;
    }

    /**
     * @param descrizione the descrizione to set
     */
    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    /**
     * @return the componentiAccertamento
     */
    public ComponentiAccertamento getComponentiAccertamento () {
        return componentiAccertamento;
    }

    /**
     * @param componentiAccertamento the componentiAccertamento to set
     */
    public void setComponentiAccertamento ( ComponentiAccertamento componentiAccertamento ) {
        this.componentiAccertamento = componentiAccertamento;
    }

}
