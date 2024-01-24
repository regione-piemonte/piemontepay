/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.901+0200")
@StaticMetamodel(Rpt.class)
public class Rpt_ {
	public static volatile SingularAttribute<Rpt, Integer> id;
	public static volatile SingularAttribute<Rpt, String> applicationId;
	public static volatile SingularAttribute<Rpt, String> authSoggetto;
	public static volatile SingularAttribute<Rpt, String> codiceContestoPagamento;
	public static volatile SingularAttribute<Rpt, Boolean> daInviare;
	public static volatile SingularAttribute<Rpt, Timestamp> dataInvio;
	public static volatile SingularAttribute<Rpt, Timestamp> dataMsgRichiesta;
	public static volatile SingularAttribute<Rpt, String> idCanale;
	public static volatile SingularAttribute<Rpt, String> idIntermPsp;
	public static volatile SingularAttribute<Rpt, String> idMsgRichiesta;
	public static volatile SingularAttribute<Rpt, String> idPsp;
	public static volatile SingularAttribute<Rpt, Integer> idStatiRpt;
	public static volatile SingularAttribute<Rpt, String> identificativoDominio;
	public static volatile SingularAttribute<Rpt, String> identificativoIntermediarioPa;
	public static volatile SingularAttribute<Rpt, String> identificativoStazioneIntermediarioPa;
	public static volatile SingularAttribute<Rpt, Timestamp> insertDate;
	public static volatile SingularAttribute<Rpt, String> iuv;
	public static volatile SingularAttribute<Rpt, Timestamp> lastUpdate;
	public static volatile SingularAttribute<Rpt, String> rptXml;
	public static volatile SingularAttribute<Rpt, String> transactionId;
}
