/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.802+0200")
@StaticMetamodel(Gatewaycustomfield.class)
public class Gatewaycustomfield_ {
	public static volatile SingularAttribute<Gatewaycustomfield, Integer> keyid;
	public static volatile SingularAttribute<Gatewaycustomfield, String> fielddescription;
	public static volatile SingularAttribute<Gatewaycustomfield, String> fieldname;
	public static volatile SingularAttribute<Gatewaycustomfield, String> fieldvalue;
	public static volatile SingularAttribute<Gatewaycustomfield, Gateway> gateway;
}
