/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.833+0200")
@StaticMetamodel(LoggingFlusso.class)
public class LoggingFlusso_ {
	public static volatile SingularAttribute<LoggingFlusso, Timestamp> dataOraInvio;
	public static volatile SingularAttribute<LoggingFlusso, String> errori;
	public static volatile SingularAttribute<LoggingFlusso, String> esito;
	public static volatile SingularAttribute<LoggingFlusso, String> idFlusso;
	public static volatile SingularAttribute<LoggingFlusso, String> istitutoMittente;
	public static volatile SingularAttribute<LoggingFlusso, String> tipoFlusso;
	public static volatile SingularAttribute<LoggingFlusso, String> warning;
}
