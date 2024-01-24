/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.846+0200")
@StaticMetamodel(MdpErrori.class)
public class MdpErrori_ {
	public static volatile SingularAttribute<MdpErrori, Integer> id;
	public static volatile SingularAttribute<MdpErrori, Timestamp> data;
	public static volatile SingularAttribute<MdpErrori, String> descrizione;
	public static volatile SingularAttribute<MdpErrori, Application> application;
	public static volatile SingularAttribute<MdpErrori, Transazione> transazione;
}
