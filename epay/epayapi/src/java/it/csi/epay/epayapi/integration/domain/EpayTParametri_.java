/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

/**
 *	Metamodel per la classe EpayTParametri
 */
@Generated( value="fabio.fenoglio" )
@StaticMetamodel( EpayTParametri.class )
public class EpayTParametri_ {
    public static volatile SingularAttribute<EpayTParametri, EpayTParametriKey> compositePrimaryKey;
    public static volatile SingularAttribute<EpayTParametri, String> valore; 
    public static volatile SingularAttribute<EpayTParametri, String> descrizione; 
    public static volatile SingularAttribute<EpayTParametri, Integer> progressivo; 
}
