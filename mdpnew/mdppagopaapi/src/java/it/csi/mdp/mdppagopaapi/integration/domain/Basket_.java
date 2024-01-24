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

@Generated(value="Dali", date="2021-09-23T12:14:16.773+0200")
@StaticMetamodel(Basket.class)
public class Basket_ {
	public static volatile SingularAttribute<Basket, String> basketId;
	public static volatile SingularAttribute<Basket, Timestamp> creationDate;
	public static volatile SingularAttribute<Basket, Timestamp> lastChangeDate;
	public static volatile ListAttribute<Basket, Item> items;
}
