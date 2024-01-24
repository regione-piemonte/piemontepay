/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.842+0200")
@StaticMetamodel(MdpCurrency.class)
public class MdpCurrency_ {
	public static volatile SingularAttribute<MdpCurrency, MdpCurrencyPK> id;
	public static volatile SingularAttribute<MdpCurrency, String> currencydescr;
	public static volatile SingularAttribute<MdpCurrency, String> enabled;
	public static volatile SingularAttribute<MdpCurrency, Gateway> gateway;
}
