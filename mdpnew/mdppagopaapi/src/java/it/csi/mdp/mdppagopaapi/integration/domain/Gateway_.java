/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.801+0200")
@StaticMetamodel(Gateway.class)
public class Gateway_ {
	public static volatile SingularAttribute<Gateway, String> gatewayId;
	public static volatile SingularAttribute<Gateway, Boolean> flagNodo;
	public static volatile SingularAttribute<Gateway, String> gatewayDescription;
	public static volatile SingularAttribute<Gateway, String> gatewayProvider;
	public static volatile SingularAttribute<Gateway, String> gatewayservicename;
	public static volatile SingularAttribute<Gateway, Timestamp> validoAl;
	public static volatile SingularAttribute<Gateway, Timestamp> validoDal;
	public static volatile ListAttribute<Gateway, Applicationcustomfield> applicationcustomfields;
	public static volatile ListAttribute<Gateway, Applicationdetail> applicationdetails;
	public static volatile ListAttribute<Gateway, Gatewaycustomfield> gatewaycustomfields;
	public static volatile ListAttribute<Gateway, Gatewaydetail> gatewaydetails;
	public static volatile ListAttribute<Gateway, Language> languages;
	public static volatile ListAttribute<Gateway, MdpCurrency> mdpCurrencies;
	public static volatile ListAttribute<Gateway, Transazione> transaziones;
}
