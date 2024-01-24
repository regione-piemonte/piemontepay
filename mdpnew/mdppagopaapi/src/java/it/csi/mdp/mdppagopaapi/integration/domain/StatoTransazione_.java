/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.917+0200")
@StaticMetamodel(StatoTransazione.class)
public class StatoTransazione_ {
	public static volatile SingularAttribute<StatoTransazione, Long> codStato;
	public static volatile SingularAttribute<StatoTransazione, String> descrizione;
	public static volatile SingularAttribute<StatoTransazione, String> descrizioneestesa;
	public static volatile ListAttribute<StatoTransazione, Transazione> transaziones;
}
