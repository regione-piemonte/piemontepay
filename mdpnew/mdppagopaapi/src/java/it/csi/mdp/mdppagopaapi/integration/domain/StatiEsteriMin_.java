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

@Generated(value="Dali", date="2021-09-23T12:14:16.911+0200")
@StaticMetamodel(StatiEsteriMin.class)
public class StatiEsteriMin_ {
	public static volatile SingularAttribute<StatiEsteriMin, String> codice;
	public static volatile SingularAttribute<StatiEsteriMin, String> codiceNext;
	public static volatile SingularAttribute<StatiEsteriMin, String> codicePrev;
	public static volatile SingularAttribute<StatiEsteriMin, String> codrif;
	public static volatile SingularAttribute<StatiEsteriMin, String> continente;
	public static volatile SingularAttribute<StatiEsteriMin, Timestamp> dStart;
	public static volatile SingularAttribute<StatiEsteriMin, Timestamp> dStop;
	public static volatile SingularAttribute<StatiEsteriMin, BigDecimal> idStatoMinistero;
	public static volatile SingularAttribute<StatiEsteriMin, String> rStatus;
	public static volatile SingularAttribute<StatiEsteriMin, String> stato;
	public static volatile SingularAttribute<StatiEsteriMin, String> territorio;
}
