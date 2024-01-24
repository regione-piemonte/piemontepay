/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.provvisorio;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

public class RicercaProvvisorioFiltroVO implements java.io.Serializable {

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

    public RicercaProvvisorioFiltroVO() {
        // TODO Auto-generated constructor stub
    }

    public boolean isEmpty() {
        return ( StringUtils.isBlank(statoFlusso) &&
                        StringUtils.isBlank(identificativoFlusso) &&
                        dataElaborazioneDa == null &&
                        dataElaborazioneA == null);
    }

    public String getValiditaGenerica() {
        return validitaGenerica;
    }

    public void setValiditaGenerica(String validitaGenerica) {
        this.validitaGenerica = validitaGenerica;
    }

    public Date getDataElaborazioneA() {
        return dataElaborazioneA;
    }

    public void setDataElaborazioneA(Date dataElaborazioneA) {
        this.dataElaborazioneA = dataElaborazioneA;
    }

    public Date getDataElaborazioneDa() {
        return dataElaborazioneDa;
    }

    public void setDataElaborazioneDa(Date dataElaborazioneDa) {
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

    public String getIdentificativoFlusso() {
        return identificativoFlusso;
    }

    public void setIdentificativoFlusso(String identificativoFlusso) {
        this.identificativoFlusso = identificativoFlusso;
    }

}
