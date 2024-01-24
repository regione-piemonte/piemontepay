/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

import java.util.Date;
/**
 *	Metamodel per la classe EpayTTracciabilitaChiamanteEsterno
 */
@Generated( value="EII" )
@StaticMetamodel( EpayTTracciabilitaChiamanteEsterno.class )
public class EpayTTracciabilitaChiamanteEsterno_ {
	public static volatile SingularAttribute<EpayTTracciabilitaChiamanteEsterno, Long> idChiamata; 
    public static volatile SingularAttribute<EpayTTracciabilitaChiamanteEsterno, String> digest; 
    public static volatile SingularAttribute<EpayTTracciabilitaChiamanteEsterno, String> iuv; 
    public static volatile SingularAttribute<EpayTTracciabilitaChiamanteEsterno, String> codiceFiscale; 
    public static volatile SingularAttribute<EpayTTracciabilitaChiamanteEsterno, String> idTransazione; 
    public static volatile SingularAttribute<EpayTTracciabilitaChiamanteEsterno, Date> timestampChiamata; 
    public static volatile SingularAttribute<EpayTTracciabilitaChiamanteEsterno, String> remoteHost; 
    public static volatile SingularAttribute<EpayTTracciabilitaChiamanteEsterno, String> identificativoPagamento; 
	public static volatile SingularAttribute<EpayTTracciabilitaChiamanteEsterno, EpayDChiamanteEsterno> epayDChiamanteEsterno;
}
