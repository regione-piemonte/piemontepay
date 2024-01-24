/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-03T17:55:45.102+0200")
@StaticMetamodel(CsiLogAudit.class)
public class CsiLogAudit_ {
	public static volatile SingularAttribute<CsiLogAudit, Integer> id;
	public static volatile SingularAttribute<CsiLogAudit, String> codiceApplicazione;
	public static volatile SingularAttribute<CsiLogAudit, String> codiceFiscaleOperatore;
	public static volatile SingularAttribute<CsiLogAudit, Timestamp> dataOra;
	public static volatile SingularAttribute<CsiLogAudit, String> descrizione;
	public static volatile SingularAttribute<CsiLogAudit, String> idApplicazione;
	public static volatile SingularAttribute<CsiLogAudit, String> indirizzoIp;
	public static volatile SingularAttribute<CsiLogAudit, String> idOggetto;
	public static volatile SingularAttribute<CsiLogAudit, String> entitaOggetto;
	public static volatile SingularAttribute<CsiLogAudit, String> descrizioneOperazione;
	public static volatile SingularAttribute<CsiLogAudit, Integer> idUtente;
	public static volatile SingularAttribute<CsiLogAudit, AuditAction> Action;
}
