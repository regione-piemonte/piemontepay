/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.804+0200")
@StaticMetamodel(Gatewaydetail.class)
public class Gatewaydetail_ {
	public static volatile SingularAttribute<Gatewaydetail, GatewaydetailPK> id;
	public static volatile SingularAttribute<Gatewaydetail, String> backofficeurl;
	public static volatile SingularAttribute<Gatewaydetail, String> contextpg;
	public static volatile SingularAttribute<Gatewaydetail, String> defaultpaymenturl;
	public static volatile SingularAttribute<Gatewaydetail, String> enabled;
	public static volatile SingularAttribute<Gatewaydetail, String> errorurl;
	public static volatile SingularAttribute<Gatewaydetail, BigDecimal> httpport;
	public static volatile SingularAttribute<Gatewaydetail, String> mdpgatewaypage;
	public static volatile SingularAttribute<Gatewaydetail, String> receipturl;
	public static volatile SingularAttribute<Gatewaydetail, String> returnurlmdp;
	public static volatile SingularAttribute<Gatewaydetail, String> sendmailonresponse;
	public static volatile SingularAttribute<Gatewaydetail, Boolean> verificaesito;
	public static volatile SingularAttribute<Gatewaydetail, Gateway> gateway;
	public static volatile SingularAttribute<Gatewaydetail, Paymentmode> paymentmode;
}
