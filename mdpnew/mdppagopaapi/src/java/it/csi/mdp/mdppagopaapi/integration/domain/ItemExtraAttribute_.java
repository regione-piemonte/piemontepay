/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.821+0200")
@StaticMetamodel(ItemExtraAttribute.class)
public class ItemExtraAttribute_ {
	public static volatile SingularAttribute<ItemExtraAttribute, ItemExtraAttributePK> id;
	public static volatile SingularAttribute<ItemExtraAttribute, String> name;
	public static volatile SingularAttribute<ItemExtraAttribute, String> value;
	public static volatile SingularAttribute<ItemExtraAttribute, Item> item;
}
