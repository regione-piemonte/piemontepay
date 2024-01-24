/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-08-12T14:36:17.741+0200")
@StaticMetamodel(Utente.class)
public class Utente_ extends AbstractPropagableParentEntity_ {
	public static volatile SingularAttribute<Utente, Long> id;
	public static volatile SingularAttribute<Utente, String> codiceFiscale;
	public static volatile SingularAttribute<Utente, String> cognome;
	public static volatile SingularAttribute<Utente, Date> dataFineValidita;
	public static volatile SingularAttribute<Utente, Date> dataInizioValidita;
	public static volatile SingularAttribute<Utente, String> nome;
	public static volatile SingularAttribute<Utente, String> email;
	public static volatile ListAttribute<Utente, AssociazioneUtenteEnte> associazioneUtenteEnte;
}
