/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Classe Java per startTransazioneCarrello complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="startTransazioneCarrello"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="t" type="{<a href="http://interfacecxf.core.mdp.csi.it/">...</a>}Transazione" minOccurs="0"/&gt;
 *         &lt;element name="tea" type="{<a href="http://interfacecxf.core.mdp.csi.it/">...</a>}transazioneExtraAttribute" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="elencoRPT" type="{<a href="http://interfacecxf.core.mdp.csi.it/">...</a>}ElencoRPT" minOccurs="0"/&gt;
 *         &lt;element name="multiBeneficiario" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "startTransazioneCarrello", propOrder = {
				"t",
				"tea",
				"elencoRPT",
				"multiBeneficiario"
} )
@SuppressWarnings ( "unused" )
public class StartTransazioneCarrello {

	protected Transazione t;

	protected List<TransazioneExtraAttribute> tea;

	protected ElencoRPT elencoRPT;

	protected Boolean multiBeneficiario;

	/**
	 * Recupera il valore della proprieta' t.
	 *
	 * @return possible object is
	 * {@link Transazione }
	 */
	public Transazione getT () {
		return t;
	}

	/**
	 * Imposta il valore della proprieta' t.
	 *
	 * @param value allowed object is
	 *              {@link Transazione }
	 */
	public void setT ( Transazione value ) {
		this.t = value;
	}

	/**
	 * Gets the value of the tea property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the tea property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getTea().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link TransazioneExtraAttribute }
	 */
	public List<TransazioneExtraAttribute> getTea () {
		if ( tea == null ) {
			tea = new ArrayList<> ();
		}
		return this.tea;
	}

	/**
	 * Recupera il valore della proprieta' elencoRPT.
	 *
	 * @return possible object is
	 * {@link ElencoRPT }
	 */
	public ElencoRPT getElencoRPT () {
		return elencoRPT;
	}

	/**
	 * Imposta il valore della proprieta' elencoRPT.
	 *
	 * @param value allowed object is
	 *              {@link ElencoRPT }
	 */
	public void setElencoRPT ( ElencoRPT value ) {
		this.elencoRPT = value;
	}

	/**
	 * Recupera il valore della proprieta' multiBeneficiario.
	 *
	 * @return possible object is
	 * {@link Boolean }
	 */
	public Boolean isMultiBeneficiario () {
		return multiBeneficiario;
	}

	/**
	 * Imposta il valore della proprieta' multiBeneficiario.
	 *
	 * @param value allowed object is
	 *              {@link Boolean }
	 */
	public void setMultiBeneficiario ( Boolean value ) {
		this.multiBeneficiario = value;
	}

}
