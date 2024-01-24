/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdprichiediapplicationid.coop.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodiceFiscaleEnte" type="{http://www.csi.it/epay/epaywso/types}Strig35Type>
 *         &lt;element name="IbanEnte" type="{http://www.csi.it/epay/epaywso/types}Strig35Type"/>
 *         &lt;element name="IbanCodiceVersamento" type="{http://www.csi.it/epay/epaywso/types}Strig35Type"/>
 *         &lt;element name="IbanAppoggio" type="{http://www.csi.it/epay/epaywso/types}Strig35Type"/>
 *         &lt;element name="IdOperazione" type="{http://www.csi.it/epay/epaywso/types}String35Type"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "codiceFiscaleEnte",
    "ibanEnte",
    "ibanCodiceVersamento",
    "ibanAppoggio",
    "idOperazione"
})
@XmlRootElement(name = "RichiediApplicationIdRequest")
public class RichiediApplicationIdRequest {

    @XmlElement(name = "CodiceFiscaleEnte", required = true)
    protected String codiceFiscaleEnte;
    @XmlElement(name = "IbanEnte", required = true)
    protected String ibanEnte;
    @XmlElement(name = "IbanCodiceVersamento", required = true)
    protected String ibanCodiceVersamento;
    @XmlElement(name = "IbanAppoggio", required = true)
    protected String ibanAppoggio;
    @XmlElement(name = "IdOperazione", required = true)
    protected String idOperazione;
	/**
	 * @return the ibanAppoggio
	 */
	public String getIbanAppoggio() {
		return ibanAppoggio;
	}
	/**
	 * @param ibanAppoggio the ibanAppoggio to set
	 */
	public void setIbanAppoggio(String ibanAppoggio) {
		this.ibanAppoggio = ibanAppoggio;
	}
	public String getCodiceFiscaleEnte() {
		return codiceFiscaleEnte;
	}
	public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
		this.codiceFiscaleEnte = codiceFiscaleEnte;
	}
	public String getIbanEnte() {
		return ibanEnte;
	}
	public void setIbanEnte(String ibanEnte) {
		this.ibanEnte = ibanEnte;
	}
	public String getIbanCodiceVersamento() {
		return ibanCodiceVersamento;
	}
	public void setIbanCodiceVersamento(String ibanCodiceVersamento) {
		this.ibanCodiceVersamento = ibanCodiceVersamento;
	}
	public String getIdOperazione() {
		return idOperazione;
	}
	public void setIdOperazione(String idOperazione) {
		this.idOperazione = idOperazione;
	}
}
