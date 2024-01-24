/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.837+0200")
@StaticMetamodel(MdpBckofficegroup.class)
public class MdpBckofficegroup_ {
	public static volatile SingularAttribute<MdpBckofficegroup, Integer> idgroup;
	public static volatile SingularAttribute<MdpBckofficegroup, String> description;
	public static volatile ListAttribute<MdpBckofficegroup, Application> applications;
	public static volatile ListAttribute<MdpBckofficegroup, MdpBckrole> mdpBckroles;
	public static volatile ListAttribute<MdpBckofficegroup, MdpBckuser> mdpBckusers;
}
