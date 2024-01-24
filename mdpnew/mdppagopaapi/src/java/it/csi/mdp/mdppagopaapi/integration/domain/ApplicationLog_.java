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

@Generated(value="Dali", date="2021-09-23T12:14:16.764+0200")
@StaticMetamodel(ApplicationLog.class)
public class ApplicationLog_ {
	public static volatile SingularAttribute<ApplicationLog, Long> seq;
	public static volatile SingularAttribute<ApplicationLog, String> applicationname;
	public static volatile SingularAttribute<ApplicationLog, String> cliente;
	public static volatile SingularAttribute<ApplicationLog, String> esercemail;
	public static volatile SingularAttribute<ApplicationLog, String> id;
	public static volatile SingularAttribute<ApplicationLog, String> note;
	public static volatile SingularAttribute<ApplicationLog, String> numeroverde;
	public static volatile SingularAttribute<ApplicationLog, String> progetto;
	public static volatile SingularAttribute<ApplicationLog, BigDecimal> redirectNewmdp;
	public static volatile SingularAttribute<ApplicationLog, String> referentecsi;
	public static volatile SingularAttribute<ApplicationLog, Timestamp> validoAl;
	public static volatile SingularAttribute<ApplicationLog, Timestamp> validoDal;
}
