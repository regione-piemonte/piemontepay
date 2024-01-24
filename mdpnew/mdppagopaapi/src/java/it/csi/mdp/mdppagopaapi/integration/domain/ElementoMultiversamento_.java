/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.789+0200")
@StaticMetamodel(ElementoMultiversamento.class)
public class ElementoMultiversamento_ {
	public static volatile SingularAttribute<ElementoMultiversamento, Integer> id;
	public static volatile SingularAttribute<ElementoMultiversamento, String> applicationId;
	public static volatile SingularAttribute<ElementoMultiversamento, Timestamp> insertDate;
	public static volatile SingularAttribute<ElementoMultiversamento, String> iuv;
	public static volatile SingularAttribute<ElementoMultiversamento, String> modellopagamento;
	public static volatile SingularAttribute<ElementoMultiversamento, Integer> posizione;
	public static volatile SingularAttribute<ElementoMultiversamento, String> transactionId;
	public static volatile SingularAttribute<ElementoMultiversamento, String> xmlElemento;
}
