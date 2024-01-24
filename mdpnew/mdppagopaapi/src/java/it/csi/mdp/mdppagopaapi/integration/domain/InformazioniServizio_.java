/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.818+0200")
@StaticMetamodel(InformazioniServizio.class)
public class InformazioniServizio_ {
	public static volatile SingularAttribute<InformazioniServizio, String> codiceLingua;
	public static volatile SingularAttribute<InformazioniServizio, Timestamp> dataValidita;
	public static volatile SingularAttribute<InformazioniServizio, String> descrizioneServizio;
	public static volatile SingularAttribute<InformazioniServizio, String> disponibilitaServizio;
	public static volatile SingularAttribute<InformazioniServizio, String> limitazioniServizio;
	public static volatile SingularAttribute<InformazioniServizio, String> urlInformazioniCanale;
	public static volatile SingularAttribute<InformazioniServizio, Boolean> valido;
	public static volatile SingularAttribute<InformazioniServizio, InformativaPsp> informativaPsp;
}
