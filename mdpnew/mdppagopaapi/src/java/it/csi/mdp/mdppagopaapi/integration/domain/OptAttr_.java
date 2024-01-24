/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.888+0200")
@StaticMetamodel(OptAttr.class)
public class OptAttr_ {
	public static volatile SingularAttribute<OptAttr, Long> optAttrId;
	public static volatile SingularAttribute<OptAttr, String> buyerCode;
	public static volatile SingularAttribute<OptAttr, String> buyerName;
	public static volatile SingularAttribute<OptAttr, Transazione> transazione;
	public static volatile ListAttribute<OptAttr, OptAttrExtraAttribute> optAttrExtraAttributes;
}
