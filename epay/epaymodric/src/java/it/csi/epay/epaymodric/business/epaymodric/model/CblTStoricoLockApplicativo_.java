/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.574+0200")
@StaticMetamodel(CblTStoricoLockApplicativo.class)
public class CblTStoricoLockApplicativo_ {
	public static volatile SingularAttribute<CblTStoricoLockApplicativo, Timestamp> dataFine;
	public static volatile SingularAttribute<CblTStoricoLockApplicativo, Timestamp> dataInizio;
	public static volatile SingularAttribute<CblTStoricoLockApplicativo, Long> id;
	public static volatile SingularAttribute<CblTStoricoLockApplicativo, Long> idEnte;
	public static volatile SingularAttribute<CblTStoricoLockApplicativo, String> idUtente;
}
