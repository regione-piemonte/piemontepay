/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-12-06T17:39:50.009+0100")
@StaticMetamodel(AssociazioneUtenteEnte.class)
public class AssociazioneUtenteEnte_ extends AbstractCSILogAuditedParentEntity_ {
	public static volatile SingularAttribute<AssociazioneUtenteEnte, AssociazioneUtenteEntePK> id;
	public static volatile SingularAttribute<AssociazioneUtenteEnte, Date> dataInizioValidita;
	public static volatile SingularAttribute<AssociazioneUtenteEnte, Date> dataFineValidita;
	public static volatile SingularAttribute<AssociazioneUtenteEnte, String> email;
	public static volatile SingularAttribute<AssociazioneUtenteEnte, Boolean> flagAdmin;
	public static volatile SingularAttribute<AssociazioneUtenteEnte, String> utenteInserimento;
	public static volatile SingularAttribute<AssociazioneUtenteEnte, Timestamp> dataInserimento;
	public static volatile SingularAttribute<AssociazioneUtenteEnte, String> utenteModifica;
	public static volatile SingularAttribute<AssociazioneUtenteEnte, Timestamp> dataModifica;
	public static volatile SingularAttribute<AssociazioneUtenteEnte, Ente> ente;
	public static volatile SingularAttribute<AssociazioneUtenteEnte, Utente> utente;
}
