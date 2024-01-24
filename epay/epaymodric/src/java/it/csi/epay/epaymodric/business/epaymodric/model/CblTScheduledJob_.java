/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.529+0200")
@StaticMetamodel(CblTScheduledJob.class)
public class CblTScheduledJob_ {
	public static volatile SingularAttribute<CblTScheduledJob, Integer> idScheduledJob;
	public static volatile SingularAttribute<CblTScheduledJob, Boolean> active;
	public static volatile SingularAttribute<CblTScheduledJob, Timestamp> inizioUltimaEsecuzione;
	public static volatile SingularAttribute<CblTScheduledJob, Timestamp> fineUltimaEsecuzione;
	public static volatile SingularAttribute<CblTScheduledJob, Long> interval;
	public static volatile SingularAttribute<CblTScheduledJob, String> params;
	public static volatile SingularAttribute<CblTScheduledJob, String> scheduleExpression;
	public static volatile SingularAttribute<CblTScheduledJob, String> status;
	public static volatile SingularAttribute<CblTScheduledJob, String> jobType;
}
