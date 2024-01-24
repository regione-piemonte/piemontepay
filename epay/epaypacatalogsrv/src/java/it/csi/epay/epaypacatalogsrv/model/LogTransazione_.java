/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-19T11:55:18.105+0200")
@StaticMetamodel(LogTransazione.class)
public class LogTransazione_ {
	public static volatile SingularAttribute<LogTransazione, Long> id;
	public static volatile SingularAttribute<LogTransazione, Sottoscrittore> sottoscrittore;
	public static volatile SingularAttribute<LogTransazione, Transazione> transazione;
	public static volatile SingularAttribute<LogTransazione, String> esito;
	public static volatile SingularAttribute<LogTransazione, String> messaggio;
}
