/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

import java.util.Date;
/**
 *	Metamodel per la classe EpayTRt
 */
@Generated( value="EII" )
@StaticMetamodel( EpayTRt.class )
public class EpayTRt_ {
	public static volatile SingularAttribute<EpayTRt, Long> idRt; 
    public static volatile SingularAttribute<EpayTRt, byte[]> ricevutaPdf; 
    public static volatile SingularAttribute<EpayTRt, byte[]> rtXml; 
    public static volatile SingularAttribute<EpayTRt, String> idApplicazione; 
    public static volatile SingularAttribute<EpayTRt, String> idTransazione; 
    public static volatile SingularAttribute<EpayTRt, Date> dataoraMsgRicevuta; 
    public static volatile SingularAttribute<EpayTRt, String> idMsgRicevuta; 
    public static volatile SingularAttribute<EpayTRt, String> tipoFirma; 
    public static volatile SingularAttribute<EpayTRt, String> iuv; 
    public static volatile SingularAttribute<EpayTRt, Integer> codEsitoPagamento; 
    public static volatile SingularAttribute<EpayTRt, String> descEsitoPagamento; 
    public static volatile SingularAttribute<EpayTRt, String> idMsgRichiesta; 
	public static volatile SingularAttribute<EpayTRt, EpayTRegistroVersamenti> epayTRegistroVersamenti;
}
