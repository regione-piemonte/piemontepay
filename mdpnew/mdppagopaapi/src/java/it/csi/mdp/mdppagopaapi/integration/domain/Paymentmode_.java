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

@Generated(value="Dali", date="2021-09-23T12:14:16.897+0200")
@StaticMetamodel(Paymentmode.class)
public class Paymentmode_ {
	public static volatile SingularAttribute<Paymentmode, String> paymentmodeId;
	public static volatile SingularAttribute<Paymentmode, String> paymentmodeDescription;
	public static volatile SingularAttribute<Paymentmode, Timestamp> validoAl;
	public static volatile SingularAttribute<Paymentmode, Timestamp> validoDal;
	public static volatile ListAttribute<Paymentmode, Applicationdetail> applicationdetails;
	public static volatile ListAttribute<Paymentmode, Gatewaydetail> gatewaydetails;
	public static volatile ListAttribute<Paymentmode, Transazione> transaziones;
}
