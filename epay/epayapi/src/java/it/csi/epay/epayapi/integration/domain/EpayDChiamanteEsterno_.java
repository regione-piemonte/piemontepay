/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

import java.util.Date;
/**
 *	Metamodel per la classe EpayDChiamanteEsterno
 */
@Generated( value="fabio.fenoglio" )
@StaticMetamodel( EpayDChiamanteEsterno.class )
public class EpayDChiamanteEsterno_ {
	public static volatile SingularAttribute<EpayDChiamanteEsterno, String> codiceChiamante; 
    public static volatile SingularAttribute<EpayDChiamanteEsterno, String> descrizioneChiamante; 
    public static volatile SingularAttribute<EpayDChiamanteEsterno, Date> dataInizioValidita; 
    public static volatile SingularAttribute<EpayDChiamanteEsterno, Date> dataFineValidita; 
    public static volatile SingularAttribute<EpayDChiamanteEsterno, String> passphrase; 
    public static volatile SingularAttribute<EpayDChiamanteEsterno, String> url; 
    public static volatile ListAttribute<EpayDChiamanteEsterno, EpayTTracciabilitaChiamanteEsterno> listOfEpayTTracciabilitaChiamanteEsterno;
}
