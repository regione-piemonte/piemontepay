/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.flusso;

import java.util.Date;
import java.util.List;

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

    private List<String> identificativiFlusso;
    
    private String iuv;
    
    private String idCodVersamento;
    //LF 02/01/2019

    @DateTimeFormat ( pattern = "dd/MM/yyyy" )
    private Date dataRicezioneA;

    @DateTimeFormat ( pattern = "dd/MM/yyyy" )
    private Date dataRicezioneDa;

    private String psp;

    private Integer annoAccertamento;

    //parametri paginazione.
    private Integer draw;
    private Integer start;
    private Integer length;
    
    public RicercaFlussoFiltroVO() {
        // TODO Auto-generated constructor stub
    }

    public boolean isEmpty() {
    	return ( StringUtils.isBlank(identificativoFlusso) &&
    			StringUtils.isBlank(iuv) &&
    			StringUtils.isBlank(idCodVersamento) &&
    			StringUtils.isBlank(statoFlusso) &&
    			dataElaborazioneDa == null &&
    			dataElaborazioneA == null  
    			&& dataRicezioneA == null && 
    			dataRicezioneDa == null 
    			&& StringUtils.isBlank ( psp ));
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
    
    

    public String getIuv() {
		return iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

	public String getIdCodVersamento() {
		return idCodVersamento;
	}

	public void setIdCodVersamento(String idCodVersamento) {
		this.idCodVersamento = idCodVersamento;
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

    public List<String> getIdentificativiFlusso () {
        return identificativiFlusso;
    }

    public void setIdentificativiFlusso ( List<String> identificativiFlusso ) {
        this.identificativiFlusso = identificativiFlusso;
    }

    public String getPsp () {
        return psp;
    }

    public void setPsp ( String psp ) {
        this.psp = psp;
    }

    public Date getDataRicezioneA () {
        return dataRicezioneA;
    }

    public void setDataRicezioneA ( Date dataRicezioneA ) {
        this.dataRicezioneA = dataRicezioneA;
    }

    public Date getDataRicezioneDa () {
        return dataRicezioneDa;
    }

    public void setDataRicezioneDa ( Date dataRicezioneDa ) {
        this.dataRicezioneDa = dataRicezioneDa;
    }
    
    public Integer getAnnoAccertamento () {
        return annoAccertamento;
    }

    public void setAnnoAccertamento ( Integer annoAccertamento ) {
        this.annoAccertamento = annoAccertamento;
    }

    /**
     * @return the draw
     */
    public Integer getDraw () {
        return draw;
    }

    /**
     * @param draw the draw to set
     */
    public void setDraw ( Integer draw ) {
        this.draw = draw;
    }

    /**
     * @return the start
     */
    public Integer getStart () {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart ( Integer start ) {
        this.start = start;
    }

    /**
     * @return the length
     */
    public Integer getLength () {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength ( Integer length ) {
        this.length = length;
    }

}
