/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

import java.math.BigDecimal;
/**
 *	Metamodel per la classe EpayTEntiTemp
 */
@Generated( value="fabio.fenoglio" )
@StaticMetamodel( EpayTEntiTemp.class )
public class EpayTEntiTemp_ {
	public static volatile SingularAttribute<EpayTEntiTemp, Long> idEnteTemp; 
    public static volatile SingularAttribute<EpayTEntiTemp, String> idOperazione; 
    public static volatile SingularAttribute<EpayTEntiTemp, String> nome; 
    public static volatile SingularAttribute<EpayTEntiTemp, String> codiceFiscale; 
    public static volatile SingularAttribute<EpayTEntiTemp, byte[]> logo; 
    public static volatile SingularAttribute<EpayTEntiTemp, BigDecimal> codiceGs1Gln; 
    public static volatile SingularAttribute<EpayTEntiTemp, String> orari; 
    public static volatile SingularAttribute<EpayTEntiTemp, Boolean> flagInvioPagamenti; 
    public static volatile SingularAttribute<EpayTEntiTemp, String> codiceInterbancario; 
	public static volatile SingularAttribute<EpayTEntiTemp, EpayTEnti> epayTEnti;
}
