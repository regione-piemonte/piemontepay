/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


// classe che contiene i dati di input da mandare al webservice

@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoInputWsRicercaFlussoOrigine" )
public class DTOInputWsRicercaFlussoOrigine extends DTOInputDate {

    private static final long serialVersionUID = 1L;

    private String identificativoIstitutoRicevente;

    private String identificativoFlusso;
    
    private String iuv;
    
    private String idCodVersamento;
    
    private String idStatoFlusso;

    private String psp;

    private List<String> identificativiFlusso;

    //parametri paginazione.
    private Integer draw;
    
    private Integer start;
    
    private Integer length;
    
    private String sortingCol;
    
    private String sortingDir;
    
    public String getIdentificativoFlusso () {
        return identificativoFlusso;
    }

    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public String getIdStatoFlusso () {
        return idStatoFlusso;
    }

    public void setIdStatoFlusso ( String idStatoFlusso ) {
        this.idStatoFlusso = idStatoFlusso;
    }

    public String getIdentificativoIstitutoRicevente () {
        return identificativoIstitutoRicevente;
    }

    public void setIdentificativoIstitutoRicevente ( String identificativoIstitutoRicevente ) {
        this.identificativoIstitutoRicevente = identificativoIstitutoRicevente;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ( super.toString () );
        stringBuffer.append ( "\n " );
        stringBuffer.append ( "identificativoFlusso: [" + identificativoFlusso + "]" );
        stringBuffer.append ( "iuv: [" + iuv + "]" );
        stringBuffer.append ( "idStatoFlusso: [" + idStatoFlusso + "]" );
        stringBuffer.append ( "idCodVersamento: [" + idCodVersamento + "]" );
        stringBuffer.append ( "dataElaborazioneDa: [" + getDataElaborazioneDa() + "]" );
        stringBuffer.append ( "dataElaborazioneA: [" + getDataElaborazioneA() + "]" );
        stringBuffer.append ( "dataRicezioneDa: [" + getDataRicezioneDa() + "]" );
        stringBuffer.append ( "dataRicezioneA: [" + getDataRicezioneA() + "]" );
        stringBuffer.append ( "psp: [" + psp + "]" );
        return stringBuffer.toString ();
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

	public String getSortingCol() {
		return sortingCol;
	}

	public void setSortingCol(String sortingCol) {
		this.sortingCol = sortingCol;
	}

	public String getSortingDir() {
		return sortingDir;
	}

	public void setSortingDir(String sortingDir) {
		this.sortingDir = sortingDir;
	}
    
}
