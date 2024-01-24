/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.770+0200")
@StaticMetamodel(Authorization.class)
public class Authorization_ {
	public static volatile SingularAttribute<Authorization, AuthorizationPK> id;
	public static volatile SingularAttribute<Authorization, MdpBckrole> mdpBckrole;
}
