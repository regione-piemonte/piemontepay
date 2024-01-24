/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-27T14:33:21.415+0100")
@StaticMetamodel(LogMigrazione.class)
public class LogMigrazione_ {
	public static volatile SingularAttribute<LogMigrazione, Integer> id;
	public static volatile SingularAttribute<LogMigrazione, String> codiceEntita;
	public static volatile SingularAttribute<LogMigrazione, String> codiceMessaggio;
	public static volatile SingularAttribute<LogMigrazione, Timestamp> dataOra;
	public static volatile SingularAttribute<LogMigrazione, String> idMigrazione;
	public static volatile SingularAttribute<LogMigrazione, String> messaggio;
}
