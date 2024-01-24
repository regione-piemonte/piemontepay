/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.847+0200")
@StaticMetamodel(MdpStatistiche.class)
public class MdpStatistiche_ {
	public static volatile SingularAttribute<MdpStatistiche, Long> id;
	public static volatile SingularAttribute<MdpStatistiche, String> applicationId;
	public static volatile SingularAttribute<MdpStatistiche, String> csv;
	public static volatile SingularAttribute<MdpStatistiche, Timestamp> insertDate;
	public static volatile SingularAttribute<MdpStatistiche, String> nomeReport;
	public static volatile SingularAttribute<MdpStatistiche, String> periodoReport;
}
