/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.919+0200")
@StaticMetamodel(StrumentoPagamento.class)
public class StrumentoPagamento_ {
	public static volatile SingularAttribute<StrumentoPagamento, String> cod;
	public static volatile SingularAttribute<StrumentoPagamento, String> nome;
	public static volatile SingularAttribute<StrumentoPagamento, BigDecimal> spId;
	public static volatile SingularAttribute<StrumentoPagamento, String> transactionId;
}
