/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.pa.pafornode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Its a response to `paSendRTReq` and contains :
 * 
 * - `outcome` and _optional_ `fault` (_see below to details_)
 * 
 * <p>Java class for paSendRTRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paSendRTRes">
 *   &lt;complexContent>
 *     &lt;extension base="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}ctResponse">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paSendRTRes")
public class PaSendRTRes
    extends CtResponse
{


}
