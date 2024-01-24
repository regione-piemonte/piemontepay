/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 *
 */

public class DatiSingolaRevocaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal importo;

    private String iur;

    private String causale;

    private String datiAggiuntivi;

    public BigDecimal getImporto () {
        return importo;
    }

    public void setImporto ( BigDecimal importo ) {
        this.importo = importo;
    }

    public String getIur () {
        return iur;
    }

    public void setIur ( String iur ) {
        this.iur = iur;
    }

    public String getCausale () {
        return causale;
    }

    public void setCausale ( String causale ) {
        this.causale = causale;
    }

    public String getDatiAggiuntivi () {
        return datiAggiuntivi;
    }

    public void setDatiAggiuntivi ( String datiAggiuntivi ) {
        this.datiAggiuntivi = datiAggiuntivi;
    }

}
