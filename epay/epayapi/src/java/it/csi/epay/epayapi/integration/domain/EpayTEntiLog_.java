/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

import java.math.BigDecimal;
import java.util.Date;
/**
 *	Metamodel per la classe EpayTEntiLog
 */
@Generated( value="fabio.fenoglio" )
@StaticMetamodel( EpayTEntiLog.class )
public class EpayTEntiLog_ {
	public static volatile SingularAttribute<EpayTEntiLog, Long> seq; 
    public static volatile SingularAttribute<EpayTEntiLog, String> nome; 
    public static volatile SingularAttribute<EpayTEntiLog, String> codiceFiscale; 
    public static volatile SingularAttribute<EpayTEntiLog, byte[]> logo; 
    public static volatile SingularAttribute<EpayTEntiLog, BigDecimal> codiceGs1Gln; 
    public static volatile SingularAttribute<EpayTEntiLog, String> orari; 
    public static volatile SingularAttribute<EpayTEntiLog, Boolean> flagInvioPagamenti; 
    public static volatile SingularAttribute<EpayTEntiLog, String> codiceInterbancario; 
    public static volatile SingularAttribute<EpayTEntiLog, Date> dataTrigger; 
	public static volatile SingularAttribute<EpayTEntiLog, EpayTEnti> epayTEnti;
}
