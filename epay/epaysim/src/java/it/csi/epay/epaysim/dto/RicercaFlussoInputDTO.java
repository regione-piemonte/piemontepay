/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.dto;

import java.util.Date;


public class RicercaFlussoInputDTO extends ParentInput {

    private static final long serialVersionUID = 1L;

    private String identificativoFlusso;

    private String statoElaborazioneFlusso;

    private Date dateStart;

    private Date dateEnd;

    /**
     * @return the identificativoFlusso
     */
    public String getIdentificativoFlusso () {
        return identificativoFlusso;
    }

    /**
     * @param identificativoFlusso the identificativoFlusso to set
     */
    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    /**
     * @return the statoElaborazioneFlusso
     */
    public String getStatoElaborazioneFlusso () {
        return statoElaborazioneFlusso;
    }

    /**
     * @param statoElaborazioneFlusso the statoElaborazioneFlusso to set
     */
    public void setStatoElaborazioneFlusso ( String statoElaborazioneFlusso ) {
        this.statoElaborazioneFlusso = statoElaborazioneFlusso;
    }

    /**
     * @return the dateStart
     */
    public Date getDateStart () {
        return dateStart;
    }

    /**
     * @param dateStart the dateStart to set
     */
    public void setDateStart ( Date dateStart ) {
        this.dateStart = dateStart;
    }

    /**
     * @return the dateEnd
     */
    public Date getDateEnd () {
        return dateEnd;
    }

    /**
     * @param dateEnd the dateEnd to set
     */
    public void setDateEnd ( Date dateEnd ) {
        this.dateEnd = dateEnd;
    }


}
