/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.791+0200")
@StaticMetamodel(Enti.class)
public class Enti_ {
	public static volatile SingularAttribute<Enti, String> enteId;
	public static volatile SingularAttribute<Enti, String> attivo;
	public static volatile SingularAttribute<Enti, String> codiceSegregazione;
	public static volatile SingularAttribute<Enti, String> descrizione;
	public static volatile SingularAttribute<Enti, Boolean> flagInvioFlussoBase;
	public static volatile SingularAttribute<Enti, Boolean> flagInvioFlussoEsteso;
	public static volatile SingularAttribute<Enti, String> partitaIva;
	public static volatile SingularAttribute<Enti, Integer> progressivoApplicationId;
	public static volatile ListAttribute<Enti, IuvAttribute> iuvAttributes;
	public static volatile ListAttribute<Enti, IuvOttici> iuvOtticis;
	public static volatile ListAttribute<Enti, Application> applications;
}
