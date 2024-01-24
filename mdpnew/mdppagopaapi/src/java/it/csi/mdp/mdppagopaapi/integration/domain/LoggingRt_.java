/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.835+0200")
@StaticMetamodel(LoggingRt.class)
public class LoggingRt_ {
	public static volatile SingularAttribute<LoggingRt, Timestamp> dataOraInvio;
	public static volatile SingularAttribute<LoggingRt, String> errori;
	public static volatile SingularAttribute<LoggingRt, String> esito;
	public static volatile SingularAttribute<LoggingRt, String> istitutoMittente;
	public static volatile SingularAttribute<LoggingRt, String> iuv;
	public static volatile SingularAttribute<LoggingRt, String> warning;
}
