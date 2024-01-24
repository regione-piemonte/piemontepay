/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.828+0200")
@StaticMetamodel(IuvOttici.class)
public class IuvOttici_ {
	public static volatile SingularAttribute<IuvOttici, Long> id;
	public static volatile SingularAttribute<IuvOttici, String> codVersamento;
	public static volatile SingularAttribute<IuvOttici, Timestamp> dataCreazione;
	public static volatile SingularAttribute<IuvOttici, Timestamp> dataRiconciliazione;
	public static volatile SingularAttribute<IuvOttici, String> iuvOttico;
	public static volatile SingularAttribute<IuvOttici, String> iuvStandard;
	public static volatile SingularAttribute<IuvOttici, Application> application;
	public static volatile SingularAttribute<IuvOttici, Enti> enti;
}
