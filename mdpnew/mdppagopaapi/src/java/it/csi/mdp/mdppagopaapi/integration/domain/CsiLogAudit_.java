/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.780+0200")
@StaticMetamodel(CsiLogAudit.class)
public class CsiLogAudit_ {
	public static volatile SingularAttribute<CsiLogAudit, String> applicationid;
	public static volatile SingularAttribute<CsiLogAudit, String> codappmodify;
	public static volatile SingularAttribute<CsiLogAudit, String> codfisc;
	public static volatile SingularAttribute<CsiLogAudit, Timestamp> dataOra;
	public static volatile SingularAttribute<CsiLogAudit, String> descrizione;
	public static volatile SingularAttribute<CsiLogAudit, String> gatewayid;
	public static volatile SingularAttribute<CsiLogAudit, String> idApp;
	public static volatile SingularAttribute<CsiLogAudit, String> idaction;
	public static volatile SingularAttribute<CsiLogAudit, String> ipAddress;
	public static volatile SingularAttribute<CsiLogAudit, String> keyOper;
	public static volatile SingularAttribute<CsiLogAudit, String> oggOper;
	public static volatile SingularAttribute<CsiLogAudit, String> operazione;
	public static volatile SingularAttribute<CsiLogAudit, String> transactionid;
	public static volatile SingularAttribute<CsiLogAudit, Integer> uniqueid;
	public static volatile SingularAttribute<CsiLogAudit, Integer> utente;
}
