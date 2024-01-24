/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.808+0200")
@StaticMetamodel(IbanEnteApplication.class)
public class IbanEnteApplication_ {
	public static volatile SingularAttribute<IbanEnteApplication, Integer> id;
	public static volatile SingularAttribute<IbanEnteApplication, String> applicationId;
	public static volatile SingularAttribute<IbanEnteApplication, String> attivo;
	public static volatile SingularAttribute<IbanEnteApplication, Timestamp> dataFineValidita;
	public static volatile SingularAttribute<IbanEnteApplication, Timestamp> dataInizioValidita;
	public static volatile SingularAttribute<IbanEnteApplication, String> iban;
	public static volatile SingularAttribute<IbanEnteApplication, String> idEnte;
	public static volatile SingularAttribute<IbanEnteApplication, String> identificativopsp;
	public static volatile SingularAttribute<IbanEnteApplication, String> tipoversamento;
}
