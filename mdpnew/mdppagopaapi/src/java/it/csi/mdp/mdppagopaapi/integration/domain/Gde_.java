/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.807+0200")
@StaticMetamodel(Gde.class)
public class Gde_ {
	public static volatile SingularAttribute<Gde, Integer> id;
	public static volatile SingularAttribute<Gde, String> applicationId;
	public static volatile SingularAttribute<Gde, String> canalepagamento;
	public static volatile SingularAttribute<Gde, String> categoriaevento;
	public static volatile SingularAttribute<Gde, String> codiceContesto;
	public static volatile SingularAttribute<Gde, String> componente;
	public static volatile SingularAttribute<Gde, Timestamp> dataoraevento;
	public static volatile SingularAttribute<Gde, String> esito;
	public static volatile SingularAttribute<Gde, String> idIntPsp;
	public static volatile SingularAttribute<Gde, String> idPsp;
	public static volatile SingularAttribute<Gde, String> identificativodominio;
	public static volatile SingularAttribute<Gde, String> identificativoerogatore;
	public static volatile SingularAttribute<Gde, String> identificativofruitore;
	public static volatile SingularAttribute<Gde, String> identificativostazioneintermediariopa;
	public static volatile SingularAttribute<Gde, Timestamp> insertDate;
	public static volatile SingularAttribute<Gde, String> iuv;
	public static volatile SingularAttribute<Gde, Timestamp> lastUpdate;
	public static volatile SingularAttribute<Gde, String> parametrispecificiinterfaccia;
	public static volatile SingularAttribute<Gde, String> sottotipoevento;
	public static volatile SingularAttribute<Gde, String> tipoevento;
	public static volatile SingularAttribute<Gde, String> tipoversamento;
	public static volatile SingularAttribute<Gde, String> transactionid;
}
