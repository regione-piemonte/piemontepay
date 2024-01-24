/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.794+0200")
@StaticMetamodel(Errore.class)
public class Errore_ {
	public static volatile SingularAttribute<Errore, Long> id;
	public static volatile SingularAttribute<Errore, String> codiceErrore;
	public static volatile SingularAttribute<Errore, String> descrizioneErrore;
	public static volatile SingularAttribute<Errore, Boolean> flagMail;
	public static volatile SingularAttribute<Errore, Boolean> flagRielaborazione;
	public static volatile SingularAttribute<Errore, String> tipologia;
}
