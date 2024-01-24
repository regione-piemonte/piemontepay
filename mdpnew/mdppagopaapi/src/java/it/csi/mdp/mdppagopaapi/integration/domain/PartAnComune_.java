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

@Generated(value="Dali", date="2021-09-23T12:14:16.894+0200")
@StaticMetamodel(PartAnComune.class)
public class PartAnComune_ {
	public static volatile SingularAttribute<PartAnComune, Long> idComune;
	public static volatile SingularAttribute<PartAnComune, String> cap;
	public static volatile SingularAttribute<PartAnComune, Timestamp> dStart;
	public static volatile SingularAttribute<PartAnComune, Timestamp> dStop;
	public static volatile SingularAttribute<PartAnComune, String> descComune;
	public static volatile SingularAttribute<PartAnComune, String> descProvincia;
	public static volatile SingularAttribute<PartAnComune, String> descRegione;
	public static volatile SingularAttribute<PartAnComune, BigDecimal> idComuneNext;
	public static volatile SingularAttribute<PartAnComune, BigDecimal> idComunePrev;
	public static volatile SingularAttribute<PartAnComune, String> istatComune;
	public static volatile SingularAttribute<PartAnComune, String> istatProvincia;
	public static volatile SingularAttribute<PartAnComune, String> istatRegione;
	public static volatile SingularAttribute<PartAnComune, String> rStatus;
	public static volatile SingularAttribute<PartAnComune, String> siglaProv;
}
