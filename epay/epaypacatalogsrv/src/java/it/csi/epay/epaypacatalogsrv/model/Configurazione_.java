/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-20T12:11:07.054+0200")
@StaticMetamodel(Configurazione.class)
public class Configurazione_ {
	public static volatile SingularAttribute<Configurazione, Integer> id;
	public static volatile SingularAttribute<Configurazione, String> codice;
	public static volatile SingularAttribute<Configurazione, String> descrizione;
	public static volatile SingularAttribute<Configurazione, Long> idEnte;
	public static volatile SingularAttribute<Configurazione, String> valore;
}
