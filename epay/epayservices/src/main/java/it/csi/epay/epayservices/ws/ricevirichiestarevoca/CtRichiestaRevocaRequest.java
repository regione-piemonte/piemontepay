/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.ws.ricevirichiestarevoca;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *        &lt;element name="ctRichiestaRevoca" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}CtRichiestaRevoca"/>
 *         &lt;element name="ctEsitoRevoca" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}CtEsitoRevoca"/>
 *           &lt;element name="parametriRiceviRT" type="{http://serviziofruitore.interfacews.mdp.nodospc.csi.it/}ParametriRiceviRT"/>
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
    "ctRichiestaRevoca",
    "ctEsitoRevoca",
    "parametriRiceviRT"

})
@XmlRootElement(name = "CtRichiestaRevocaRequest")
public class CtRichiestaRevocaRequest {


    @XmlElement(name = "ctRichiestaRevoca", required = true)
    protected ArrayList <CtRichiestaRevoca> ctRichiestaRevoca;

	/**
	 * @return the ctRichiestaRevoca
	 */
	public ArrayList<CtRichiestaRevoca> getCtRichiestaRevoca() {
		return ctRichiestaRevoca;
	}

	/**
	 * @param ctRichiestaRevoca the ctRichiestaRevoca to set
	 */
	public void setCtRichiestaRevoca(ArrayList<CtRichiestaRevoca> ctRichiestaRevoca) {
		this.ctRichiestaRevoca = ctRichiestaRevoca;
	}


	 @XmlElement(name = "ctEsitoRevoca", required = true)
	    protected ArrayList <CtEsitoRevoca> ctEsitoRevoca;

		/**
		 * @return the ctEsitoRevoca
		 */
		public ArrayList<CtEsitoRevoca> getCtEsitoRevoca() {
			return ctEsitoRevoca;
		}

		/**
		 * @param ctEsitoRevoca the ctEsitoRevoca to set
		 */
		public void setCtEsitoRevoca(ArrayList<CtEsitoRevoca> ctEsitoRevoca) {
			this.ctEsitoRevoca = ctEsitoRevoca;
		}
		


		 @XmlElement(name = "parametriRiceviRT", required = true)
		    protected ParametriRiceviRT parametriRiceviRT;

			/**
			 * @return the parametriRiceviRT
			 */
			public ParametriRiceviRT getParametriRiceviRT() {
				return parametriRiceviRT;
			}

			/**
			 * @param ctEsitoRevoca the ctEsitoRevoca to set
			 */
			public void setParametriRiceviRT(ParametriRiceviRT parametriRiceviRT) {
				this.parametriRiceviRT = parametriRiceviRT;
			}
}
