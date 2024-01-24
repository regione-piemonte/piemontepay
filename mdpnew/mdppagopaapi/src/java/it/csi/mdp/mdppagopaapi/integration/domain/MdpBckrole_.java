/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.839+0200")
@StaticMetamodel(MdpBckrole.class)
public class MdpBckrole_ {
	public static volatile SingularAttribute<MdpBckrole, Integer> idrole;
	public static volatile SingularAttribute<MdpBckrole, String> roledescr;
	public static volatile ListAttribute<MdpBckrole, Authorization> authorizations;
	public static volatile ListAttribute<MdpBckrole, MdpBckofficegroup> mdpBckofficegroups;
}
