/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model.v1;

import java.io.Serializable;
import java.util.Date;


/**
 *
 */

public class AccessoChiamanteEsternoSincronoSplitInput implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date timestampChiamata;

    private String ipChiamante;

    private String codiceChiamante;

    private String iuv;

    public Date getTimestampChiamata () {
        return timestampChiamata;
    }

    public void setTimestampChiamata ( Date timestampChiamata ) {
        this.timestampChiamata = timestampChiamata;
    }

    public String getIpChiamante () {
        return ipChiamante;
    }

    public void setIpChiamante ( String ipChiamante ) {
        this.ipChiamante = ipChiamante;
    }

    public String getCodiceChiamante () {
        return codiceChiamante;
    }

    public void setCodiceChiamante ( String codiceChiamante ) {
        this.codiceChiamante = codiceChiamante;
    }

    /**
     * @return the iuv
     */
    public String getIuv () {
        return iuv;
    }

    /**
     * @param iuv the iuv to set
     */
    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    @Override
    public String toString () {
        return "AccessoChiamanteEsternoSincronoSplitInput ["
            + ( timestampChiamata != null ? "timestampChiamata=" + timestampChiamata + ", " : "" )
            + ( ipChiamante != null ? "ipChiamante=" + ipChiamante + ", " : "" )
            + ( codiceChiamante != null ? "codiceChiamante=" + codiceChiamante + ", " : "" )
            + ( iuv != null ? "identificativoPagamento=" + iuv + ", " : "" )
            + "]";
    }

}
