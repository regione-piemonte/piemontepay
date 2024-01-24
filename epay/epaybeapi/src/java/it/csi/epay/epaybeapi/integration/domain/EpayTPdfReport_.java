/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

/**
 *	Metamodel per la classe EpayTPdfReport
 */
@Generated( value="EII" )
@StaticMetamodel( EpayTPdfReport.class )
public class EpayTPdfReport_ {
	public static volatile SingularAttribute<EpayTPdfReport, Long> id; 
    public static volatile SingularAttribute<EpayTPdfReport, String> codice; 
    public static volatile SingularAttribute<EpayTPdfReport, String> nomeTemplate; 
    public static volatile SingularAttribute<EpayTPdfReport, String> template; 
    public static volatile SingularAttribute<EpayTPdfReport, byte[]> templateCompilato; 
}
