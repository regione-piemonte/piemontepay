/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.04.15 at 05:05:41 PM CEST 
//

package it.csi.mdp.core.business.dto.multicarrello;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "RPTData", propOrder = {
    "autenticazioneSoggetto",
    "idStazioneRichiedente",
    "soggettoVersante",
    "soggettoPagatore",
    "datiVersamento",
    "applicationId",
    "datiAccertamento" //TAG_PPU - 2019 - RDI 004 - RDI 005
} )
public class RPTData implements Serializable {

    private static final long serialVersionUID = 4107690289650146073L;

    @XmlElement ( required = true )
    protected String autenticazioneSoggetto;

    protected String idStazioneRichiedente;

    protected SoggettoVersante soggettoVersante;

    @XmlElement ( required = true )
    protected SoggettoPagatore soggettoPagatore;

    @XmlElement ( required = true )
    protected DatiVersamentoRPT datiVersamento;

    @XmlElement ( required = true )
    protected String applicationId;

    protected DatiAccertamentoRPT datiAccertamento;

    public String getAutenticazioneSoggetto () {
        return autenticazioneSoggetto;
    }

    public void setAutenticazioneSoggetto ( String autenticazioneSoggetto ) {
        this.autenticazioneSoggetto = autenticazioneSoggetto;
    }

    public SoggettoVersante getSoggettoVersante () {
        return soggettoVersante;
    }

    public void setSoggettoVersante ( SoggettoVersante soggettoVersante ) {
        this.soggettoVersante = soggettoVersante;
    }

    public SoggettoPagatore getSoggettoPagatore () {
        return soggettoPagatore;
    }

    public void setSoggettoPagatore ( SoggettoPagatore soggettoPagatore ) {
        this.soggettoPagatore = soggettoPagatore;
    }

    public DatiVersamentoRPT getDatiVersamento () {
        return datiVersamento;
    }

    public void setDatiVersamento ( DatiVersamentoRPT datiVersamento ) {
        this.datiVersamento = datiVersamento;
    }

    public String getIdStazioneRichiedente () {
        return idStazioneRichiedente;
    }

    public void setIdStazioneRichiedente ( String idStazioneRichiedente ) {
        this.idStazioneRichiedente = idStazioneRichiedente;
    }

    public String getApplicationId () {
        return applicationId;
    }

    public void setApplicationId ( String applicationId ) {
        this.applicationId = applicationId;
    }

    public DatiAccertamentoRPT getDatiAccertamento () {
        return datiAccertamento;
    }

    public void setDatiAccertamento ( DatiAccertamentoRPT datiAccertamento ) {
        this.datiAccertamento = datiAccertamento;
    }
}
