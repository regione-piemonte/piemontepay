/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.851+0200")
@StaticMetamodel(NodoErrori.class)
public class NodoErrori_ {
	public static volatile SingularAttribute<NodoErrori, Integer> id;
	public static volatile SingularAttribute<NodoErrori, Timestamp> data;
	public static volatile SingularAttribute<NodoErrori, String> descrizione;
	public static volatile SingularAttribute<NodoErrori, String> transactionId;
	public static volatile SingularAttribute<NodoErrori, Application> application;
}
