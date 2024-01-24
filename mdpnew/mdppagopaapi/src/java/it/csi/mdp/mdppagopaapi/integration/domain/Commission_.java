/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.776+0200")
@StaticMetamodel(Commission.class)
public class Commission_ {
	public static volatile SingularAttribute<Commission, String> commissionId;
	public static volatile SingularAttribute<Commission, String> commissionDescription;
	public static volatile ListAttribute<Commission, Applicationdetail> applicationdetails;
}
