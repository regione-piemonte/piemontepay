/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.819+0200")
@StaticMetamodel(Item.class)
public class Item_ {
	public static volatile SingularAttribute<Item, Long> itemPkId;
	public static volatile SingularAttribute<Item, Timestamp> creationDate;
	public static volatile SingularAttribute<Item, String> description;
	public static volatile SingularAttribute<Item, BigDecimal> itemId;
	public static volatile SingularAttribute<Item, BigDecimal> priceItem;
	public static volatile SingularAttribute<Item, BigDecimal> quantity;
	public static volatile SingularAttribute<Item, BigDecimal> totalPrice;
	public static volatile SingularAttribute<Item, Basket> basket;
	public static volatile ListAttribute<Item, ItemExtraAttribute> itemExtraAttributes;
}
