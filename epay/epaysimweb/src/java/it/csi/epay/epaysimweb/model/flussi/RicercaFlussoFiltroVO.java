/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.model.flussi;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

public class RicercaFlussoFiltroVO implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // campo finto per mostrare messaggi non relativi ad un campo specifico 
    // (sono sicuro che esiste un modo migliore per fare questa cosa)
    private String validitaGenerica;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataElaborazioneA;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataElaborazioneDa;

    private String statoFlusso;
    private String identificativoFlusso;

    public RicercaFlussoFiltroVO() {
        // TODO Auto-generated constructor stub
    }

    public boolean isEmpty () {
        return statoFlusso == null &&
                        StringUtils.isBlank ( identificativoFlusso ) &&
                        dataElaborazioneDa == null &&
                        dataElaborazioneA == null;
    }

    /**
     * @return the validitaGenerica
     */
    public String getValiditaGenerica () {
        return validitaGenerica;
    }

    /**
     * @param validitaGenerica the validitaGenerica to set
     */
    public void setValiditaGenerica ( String validitaGenerica ) {
        this.validitaGenerica = validitaGenerica;
    }

    /**
     * @return the dataElaborazioneA
     */
    public Date getDataElaborazioneA () {
        return dataElaborazioneA;
    }

    /**
     * @param dataElaborazioneA the dataElaborazioneA to set
     */
    public void setDataElaborazioneA ( Date dataElaborazioneA ) {
        this.dataElaborazioneA = dataElaborazioneA;
    }

    /**
     * @return the dataElaborazioneDa
     */
    public Date getDataElaborazioneDa () {
        return dataElaborazioneDa;
    }

    /**
     * @param dataElaborazioneDa the dataElaborazioneDa to set
     */
    public void setDataElaborazioneDa ( Date dataElaborazioneDa ) {
        this.dataElaborazioneDa = dataElaborazioneDa;
    }

    /**
     * @return the statoFlusso
     */
    public String getStatoFlusso () {
        return statoFlusso;
    }

    /**
     * @param statoFlusso the statoFlusso to set
     */
    public void setStatoFlusso ( String statoFlusso ) {
        this.statoFlusso = statoFlusso;
    }

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


}
