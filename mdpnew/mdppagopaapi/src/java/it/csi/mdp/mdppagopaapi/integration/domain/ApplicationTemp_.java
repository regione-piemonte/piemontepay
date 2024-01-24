/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.765+0200")
@StaticMetamodel(ApplicationTemp.class)
public class ApplicationTemp_ {
	public static volatile SingularAttribute<ApplicationTemp, ApplicationTempPK> id;
	public static volatile SingularAttribute<ApplicationTemp, String> applicationname;
	public static volatile SingularAttribute<ApplicationTemp, String> cliente;
	public static volatile SingularAttribute<ApplicationTemp, String> esercemail;
	public static volatile SingularAttribute<ApplicationTemp, String> note;
	public static volatile SingularAttribute<ApplicationTemp, String> numeroverde;
	public static volatile SingularAttribute<ApplicationTemp, String> progetto;
	public static volatile SingularAttribute<ApplicationTemp, BigDecimal> redirectNewmdp;
	public static volatile SingularAttribute<ApplicationTemp, String> referentecsi;
	public static volatile SingularAttribute<ApplicationTemp, Timestamp> validoAl;
	public static volatile SingularAttribute<ApplicationTemp, Timestamp> validoDal;
}
