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

@Generated(value="Dali", date="2021-09-23T12:14:16.941+0200")
@StaticMetamodel(WispParam.class)
public class WispParam_ {
	public static volatile SingularAttribute<WispParam, String> keypa;
	public static volatile SingularAttribute<WispParam, String> applicationId;
	public static volatile SingularAttribute<WispParam, String> bolloDigitale;
	public static volatile SingularAttribute<WispParam, String> codicelingua;
	public static volatile SingularAttribute<WispParam, String> contoposte;
	public static volatile SingularAttribute<WispParam, String> effettuazionescelta;
	public static volatile SingularAttribute<WispParam, String> enteCreditore;
	public static volatile SingularAttribute<WispParam, String> esito;
	public static volatile SingularAttribute<WispParam, String> ibanaccredito;
	public static volatile SingularAttribute<WispParam, String> idPsp;
	public static volatile SingularAttribute<WispParam, String> identificativocanale;
	public static volatile SingularAttribute<WispParam, String> identificativocanalescelto;
	public static volatile SingularAttribute<WispParam, String> identificativodominio;
	public static volatile SingularAttribute<WispParam, String> identificativointermediariopspscelto;
	public static volatile SingularAttribute<WispParam, String> identificativopspscelto;
	public static volatile SingularAttribute<WispParam, BigDecimal> importoTransazione;
	public static volatile SingularAttribute<WispParam, Timestamp> insertDate;
	public static volatile SingularAttribute<WispParam, String> keywisp;
	public static volatile SingularAttribute<WispParam, Timestamp> lastUpdate;
	public static volatile SingularAttribute<WispParam, Integer> numPagamentiRpt;
	public static volatile SingularAttribute<WispParam, String> pagamentimodello2;
	public static volatile SingularAttribute<WispParam, String> primitiva;
	public static volatile SingularAttribute<WispParam, String> stornoPagamento;
	public static volatile SingularAttribute<WispParam, String> stringaerrore;
	public static volatile SingularAttribute<WispParam, String> terzoModelloPagamento;
	public static volatile SingularAttribute<WispParam, String> tipoVersamento;
	public static volatile SingularAttribute<WispParam, String> tipoversamentoscelto;
	public static volatile SingularAttribute<WispParam, String> transactionId;
	public static volatile SingularAttribute<WispParam, String> type;
	public static volatile SingularAttribute<WispParam, String> urlBack;
	public static volatile SingularAttribute<WispParam, String> urlReturn;
	public static volatile SingularAttribute<WispParam, String> urlgestione;
	public static volatile SingularAttribute<WispParam, String> versioneinterfacciawisp;
}
