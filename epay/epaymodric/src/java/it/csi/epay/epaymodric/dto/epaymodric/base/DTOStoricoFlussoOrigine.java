/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.dto.epaymodric.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.business.epaymodric.bo.TipoAcqusizioneDTO;


/**
 * @author vsgro
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoStoricoFlussoOrigine" )
public class DTOStoricoFlussoOrigine extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String contatoreTentativi;

    private String dataInserimento;

    private String dataoraFlusso;

    private String ibanRiversamentoFlusso;

    private String idElaborazione;

    private String idIstitutoRicevente;

    private String idStatoFlusso;

    private String identificativoFlusso;

    private String identificativoIstitutoRicevente;

    private String identificativoPsp;

    private String importoTotalePagamenti;

    private String numeroTotalePagamenti;

    private String statoFlusso;

    private String xmlFlusso;

    private String dataInsVar;

    private String utenteInsVar;

    private Integer numeroTotalePagamentiIntermediati;

    private BigDecimal importoTotalePagamentiIntermediati;

    private Date dataRegolamento;

    private String identificativoUnivocoRegolamento;

    private TipoAcqusizioneDTO tipoAcqusizione;

    public String getId () {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public String getContatoreTentativi () {
        return contatoreTentativi;
    }

    public void setContatoreTentativi ( String contatoreTentativi ) {
        this.contatoreTentativi = contatoreTentativi;
    }

    public String getDataoraFlusso () {
        return dataoraFlusso;
    }

    public void setDataoraFlusso ( String dataoraFlusso ) {
        this.dataoraFlusso = dataoraFlusso;
    }

    public String getIbanRiversamentoFlusso () {
        return ibanRiversamentoFlusso;
    }

    public void setIbanRiversamentoFlusso ( String ibanRiversamentoFlusso ) {
        this.ibanRiversamentoFlusso = ibanRiversamentoFlusso;
    }

    public String getIdElaborazione () {
        return idElaborazione;
    }

    public void setIdElaborazione ( String idElaborazione ) {
        this.idElaborazione = idElaborazione;
    }

    public String getIdIstitutoRicevente () {
        return idIstitutoRicevente;
    }

    public void setIdIstitutoRicevente ( String idIstitutoRicevente ) {
        this.idIstitutoRicevente = idIstitutoRicevente;
    }

    public String getIdStatoFlusso () {
        return idStatoFlusso;
    }

    public void setIdStatoFlusso ( String idStatoFlusso ) {
        this.idStatoFlusso = idStatoFlusso;
    }

    public String getIdentificativoFlusso () {
        return identificativoFlusso;
    }

    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public String getIdentificativoIstitutoRicevente () {
        return identificativoIstitutoRicevente;
    }

    public void setIdentificativoIstitutoRicevente ( String identificativoIstitutoRicevente ) {
        this.identificativoIstitutoRicevente = identificativoIstitutoRicevente;
    }

    public String getIdentificativoPsp () {
        return identificativoPsp;
    }

    public void setIdentificativoPsp ( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
    }

    public String getImportoTotalePagamenti () {
        return importoTotalePagamenti;
    }

    public void setImportoTotalePagamenti ( String importoTotalePagamenti ) {
        this.importoTotalePagamenti = importoTotalePagamenti;
    }

    public String getNumeroTotalePagamenti () {
        return numeroTotalePagamenti;
    }

    public void setNumeroTotalePagamenti ( String numeroTotalePagamenti ) {
        this.numeroTotalePagamenti = numeroTotalePagamenti;
    }

    public String getStatoFlusso () {
        return statoFlusso;
    }

    public void setStatoFlusso ( String statoFlusso ) {
        this.statoFlusso = statoFlusso;
    }

    public String getXmlFlusso () {
        return xmlFlusso;
    }

    public void setXmlFlusso ( String xmlFlusso ) {
        this.xmlFlusso = xmlFlusso;
    }

    public String getDataInsVar () {
        return dataInsVar;
    }

    public void setDataInsVar ( String dataInsVar ) {
        this.dataInsVar = dataInsVar;
    }

    public String getUtenteInsVar () {
        return utenteInsVar;
    }

    public void setUtenteInsVar ( String utenteInsVar ) {
        this.utenteInsVar = utenteInsVar;
    }

    @Override
    public String toString () {
        //FIXME [VS] DTOInputWSStoricoFlussoOrigine da implementare toString
        StringBuffer stringBuffer = new StringBuffer ( super.toString () );
        stringBuffer.append ( "id: [" + id + "]" );
        stringBuffer.append ( "contatoreTentativi: [" + contatoreTentativi + "]" );
        stringBuffer.append ( "dataInserimento: [" + dataInserimento + "]" );
        stringBuffer.append ( "dataoraFlusso: [" + dataoraFlusso + "]" );
        stringBuffer.append ( "ibanRiversamentoFlusso: [" + ibanRiversamentoFlusso + "]" );
        stringBuffer.append ( "idElaborazione: [" + idElaborazione + "]" );
        stringBuffer.append ( "idIstitutoRicevente: [" + idIstitutoRicevente + "]" );
        stringBuffer.append ( "idStatoFlusso: [" + idStatoFlusso + "]" );
        stringBuffer.append ( "identificativoFlusso: [" + identificativoFlusso + "]" );
        stringBuffer.append ( "identificativoIstitutoRicevente: [" + identificativoIstitutoRicevente + "]" );
        stringBuffer.append ( "identificativoPsp: [" + identificativoPsp + "]" );
        stringBuffer.append ( "importoTotalePagamenti: [" + importoTotalePagamenti + "]" );
        stringBuffer.append ( "numeroTotalePagamenti: [" + numeroTotalePagamenti + "]" );
        stringBuffer.append ( "statoFlusso: [" + statoFlusso + "]" );
        stringBuffer.append ( "xmlFlusso: [" + xmlFlusso + "]" );
        stringBuffer.append ( "dataInsVar: [" + dataInsVar + "]" );
        stringBuffer.append ( "utenteInsVar: [" + utenteInsVar + "]" );
        return stringBuffer.toString ();
    }

    public Integer getNumeroTotalePagamentiIntermediati () {
        return numeroTotalePagamentiIntermediati;
    }

    public void setNumeroTotalePagamentiIntermediati ( Integer numeroTotalePagamentiIntermediati ) {
        this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
    }

    public BigDecimal getImportoTotalePagamentiIntermediati () {
        return importoTotalePagamentiIntermediati;
    }

    public void setImportoTotalePagamentiIntermediati ( BigDecimal importoTotalePagamentiIntermediati ) {
        this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
    }

    public Date getDataRegolamento () {
        return dataRegolamento;
    }

    public void setDataRegolamento ( Date dataRegolamento ) {
        this.dataRegolamento = dataRegolamento;
    }

    public String getIdentificativoUnivocoRegolamento () {
        return identificativoUnivocoRegolamento;
    }

    public void setIdentificativoUnivocoRegolamento ( String identificativoUnivocoRegolamento ) {
        this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
    }

    public TipoAcqusizioneDTO getTipoAcqusizione () {
        return tipoAcqusizione;
    }

    public void setTipoAcqusizione ( TipoAcqusizioneDTO tipoAcqusizione ) {
        this.tipoAcqusizione = tipoAcqusizione;
    }

}
