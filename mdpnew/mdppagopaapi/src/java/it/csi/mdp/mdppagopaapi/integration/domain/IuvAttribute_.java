/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.825+0200")
@StaticMetamodel(IuvAttribute.class)
public class IuvAttribute_ {
	public static volatile SingularAttribute<IuvAttribute, IuvAttributePK> id;
	public static volatile SingularAttribute<IuvAttribute, Date> dataDismissione;
	public static volatile SingularAttribute<IuvAttribute, Long> progressivo;
	public static volatile SingularAttribute<IuvAttribute, Enti> enti;
}
