/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.841+0200")
@StaticMetamodel(MdpBckuser.class)
public class MdpBckuser_ {
	public static volatile SingularAttribute<MdpBckuser, Integer> iduser;
	public static volatile SingularAttribute<MdpBckuser, String> codfisc;
	public static volatile SingularAttribute<MdpBckuser, String> email;
	public static volatile SingularAttribute<MdpBckuser, String> firstname;
	public static volatile SingularAttribute<MdpBckuser, String> lastname;
	public static volatile SingularAttribute<MdpBckuser, String> pwdservizio;
	public static volatile ListAttribute<MdpBckuser, MdpBckofficegroup> mdpBckofficegroups;
}
