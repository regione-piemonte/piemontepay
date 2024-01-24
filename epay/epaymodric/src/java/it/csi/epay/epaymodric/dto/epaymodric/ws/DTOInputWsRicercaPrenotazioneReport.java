/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoInputWsRicercaPrenotazioneReport" )
public class DTOInputWsRicercaPrenotazioneReport extends DTOInputDate {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String nominativoExport;

    private Integer identificativoFlusso;

    private String causaleProvvisorio;

    private String iuv;

    private Integer idCodiceVersamento;

    private String idStatoFlusso;

    private String psp;

    public String getNominativoExport () {
        return nominativoExport;
    }

    public void setNominativoExport ( String nominativoExport ) {
        this.nominativoExport = nominativoExport;
    }

    public Integer getIdentificativoFlusso () {
        return identificativoFlusso;
    }

    public void setIdentificativoFlusso ( Integer identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public String getCausaleProvvisorio () {
        return causaleProvvisorio;
    }

    public void setCausaleProvvisorio ( String causaleProvvisorio ) {
        this.causaleProvvisorio = causaleProvvisorio;
    }

    public String getIuv () {
        return iuv;
    }

    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    public Integer getIdCodiceVersamento () {
        return idCodiceVersamento;
    }

    public void setIdCodiceVersamento ( Integer idCodiceVersamento ) {
        this.idCodiceVersamento = idCodiceVersamento;
    }

    public String getIdStatoFlusso () {
        return idStatoFlusso;
    }

    public void setIdStatoFlusso ( String idStatoFlusso ) {
        this.idStatoFlusso = idStatoFlusso;
    }

    public String getPsp () {
        return psp;
    }

    public void setPsp ( String psp ) {
        this.psp = psp;
    }
}
