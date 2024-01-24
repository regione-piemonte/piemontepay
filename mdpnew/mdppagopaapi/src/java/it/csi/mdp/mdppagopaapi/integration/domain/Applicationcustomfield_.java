/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.755+0200")
@StaticMetamodel(Applicationcustomfield.class)
public class Applicationcustomfield_ {
	public static volatile SingularAttribute<Applicationcustomfield, Integer> keyid;
	public static volatile SingularAttribute<Applicationcustomfield, String> fielddescription;
	public static volatile SingularAttribute<Applicationcustomfield, String> fieldname;
	public static volatile SingularAttribute<Applicationcustomfield, String> fieldvalue;
	public static volatile SingularAttribute<Applicationcustomfield, Application> application;
	public static volatile SingularAttribute<Applicationcustomfield, Gateway> gateway;
}
