/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

/**
 *	Metamodel per la classe EpayTConfigurazione
 */
@Generated( value="EII" )
@StaticMetamodel( EpayTConfigurazione.class )
public class EpayTConfigurazione_ {
	public static volatile SingularAttribute<EpayTConfigurazione, Integer> id; 
    public static volatile SingularAttribute<EpayTConfigurazione, String> codice; 
    public static volatile SingularAttribute<EpayTConfigurazione, String> valore; 
    public static volatile SingularAttribute<EpayTConfigurazione, String> descrizione; 
	public static volatile SingularAttribute<EpayTConfigurazione, EpayTEnti> epayTEnti;
}
