/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-19T11:55:06.590+0200")
@StaticMetamodel(Transazione.class)
public class Transazione_ {
	public static volatile SingularAttribute<Transazione, Long> id;
	public static volatile SingularAttribute<Transazione, Operazione> operazione;
	public static volatile SingularAttribute<Transazione, String> esito;
	public static volatile SingularAttribute<Transazione, Date> data;
	public static volatile SingularAttribute<Transazione, String> idTransazioneEsterna;
}
