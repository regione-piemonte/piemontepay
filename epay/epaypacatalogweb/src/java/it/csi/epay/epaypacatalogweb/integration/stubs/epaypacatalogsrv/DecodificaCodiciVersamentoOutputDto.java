/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for decodificaCodiciVersamentoOutputDto complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="decodificaCodiciVersamentoOutputDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}decodificaOutputDto">
 *       &lt;sequence>
 *         &lt;element name="tipoPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceVersamentoSecondario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="enteSecondario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flagMbPrimario" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagMbSecondario" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="descrizioneCodiceVersamentoSecondario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idCodiceVersamentoSecondario" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "decodificaCodiciVersamentoOutputDto", propOrder = {
    "codiceVersamentoSecondario",
    "enteSecondario",
    "flagMbPrimario",
    "flagMbSecondario",
    "tipoPagamento",
    "descrizioneCodiceVersamentoSecondario",
    "idCodiceVersamentoSecondario"
} )
public class DecodificaCodiciVersamentoOutputDto extends DecodificaOutputDto {

    protected String codiceVersamentoSecondario;

    protected String enteSecondario;

    protected Boolean flagMbPrimario;

    protected Boolean flagMbSecondario;

    protected String tipoPagamento;
    
    protected String descrizioneCodiceVersamentoSecondario;
    
    protected Long idCodiceVersamentoSecondario;

    /**
     * Gets the value of the codiceVersamentoSecondario property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCodiceVersamentoSecondario () {
        return codiceVersamentoSecondario;
    }

    /**
     * Sets the value of the codiceVersamentoSecondario property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCodiceVersamentoSecondario ( String value ) {
        this.codiceVersamentoSecondario = value;
    }

    /**
     * Gets the value of the enteSecondario property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getEnteSecondario () {
        return enteSecondario;
    }

    /**
     * Sets the value of the enteSecondario property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setEnteSecondario ( String value ) {
        this.enteSecondario = value;
    }

    /**
     * Gets the value of the flagMbPrimario property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean getFlagMbPrimario () {
        return flagMbPrimario;
    }

    /**
     * Sets the value of the flagMbPrimario property.
     * 
     * @param value allowed object is {@link Boolean }
     * 
     */
    public void setFlagMbPrimario ( Boolean value ) {
        this.flagMbPrimario = value;
    }

    public void setTipoPagamento ( String tipoPagamento ) {
        this.tipoPagamento = tipoPagamento;
    }

    /**
     * Gets the value of the flagMbSecondario property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean getFlagMbSecondario () {
        return flagMbSecondario;
    }

    /**
     * Sets the value of the flagMbSecondario property.
     * 
     * @param value allowed object is {@link Boolean }
     * 
     */
    public void setFlagMbSecondario ( Boolean value ) {
        this.flagMbSecondario = value;
    }

    
    /**
     * @return the tipoPagamento
     */
    public String getTipoPagamento () {
        return tipoPagamento;
    }

    
    /**
     * @return the descrizioneCodiceVersamentoSecondario
     */
    public String getDescrizioneCodiceVersamentoSecondario () {
        return descrizioneCodiceVersamentoSecondario;
    }

    
    /**
     * @param descrizioneCodiceVersamentoSecondario the descrizioneCodiceVersamentoSecondario to set
     */
    public void setDescrizioneCodiceVersamentoSecondario ( String descrizioneCodiceVersamentoSecondario ) {
        this.descrizioneCodiceVersamentoSecondario = descrizioneCodiceVersamentoSecondario;
    }

    
    /**
     * @return the idCodiceVersamentoSecondario
     */
    public Long getIdCodiceVersamentoSecondario () {
        return idCodiceVersamentoSecondario;
    }

    
    /**
     * @param idCodiceVersamentoSecondario the idCodiceVersamentoSecondario to set
     */
    public void setIdCodiceVersamentoSecondario ( Long idCodiceVersamentoSecondario ) {
        this.idCodiceVersamentoSecondario = idCodiceVersamentoSecondario;
    }
    
    
    
    

}
