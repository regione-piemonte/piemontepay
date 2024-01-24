/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.895+0200")
@StaticMetamodel(PaymentGateway.class)
public class PaymentGateway_ {
	public static volatile SingularAttribute<PaymentGateway, Long> pgId;
	public static volatile SingularAttribute<PaymentGateway, String> cod;
	public static volatile SingularAttribute<PaymentGateway, String> nome;
	public static volatile SingularAttribute<PaymentGateway, String> transactionId;
}
