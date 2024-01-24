/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.math.BigDecimal;
import java.util.List;


public class PosizioneMultibeneficiarioDaAggiornare extends PosizioneDaAggiornare {

    /**
     *
     */
    private static final long serialVersionUID = 3980009373061009124L;

    private BigDecimal importoPrincipale;

    private BigDecimal importoSecondario;

    private List<ComponentiImporto> componentiImportoSecondario;

    /**
     * @return the importoPrincipale
     */
    public BigDecimal getImportoPrincipale () {
        return importoPrincipale;
    }

    /**
     * @return the importoSecondario
     */
    public BigDecimal getImportoSecondario () {
        return importoSecondario;
    }

    /**
     * @return the componentiImportoSecondario
     */
    public List<ComponentiImporto> getComponentiImportoSecondario () {
        return componentiImportoSecondario;
    }

    /**
     * @param importoPrincipale the importoPrincipale to set
     */
    public void setImportoPrincipale ( BigDecimal importoPrincipale ) {
        this.importoPrincipale = importoPrincipale;
    }

    /**
     * @param importoSecondario the importoSecondario to set
     */
    public void setImportoSecondario ( BigDecimal importoSecondario ) {
        this.importoSecondario = importoSecondario;
    }

    /**
     * @param componentiImportoSecondario the componentiImportoSecondario to set
     */
    public void setComponentiImportoSecondario ( List<ComponentiImporto> componentiImportoSecondario ) {
        this.componentiImportoSecondario = componentiImportoSecondario;
    }

}
