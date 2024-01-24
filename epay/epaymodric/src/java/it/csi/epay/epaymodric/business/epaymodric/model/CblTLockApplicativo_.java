/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.468+0200")
@StaticMetamodel(CblTLockApplicativo.class)
public class CblTLockApplicativo_ {
	public static volatile SingularAttribute<CblTLockApplicativo, Long> id;
	public static volatile SingularAttribute<CblTLockApplicativo, Timestamp> dataFine;
	public static volatile SingularAttribute<CblTLockApplicativo, Timestamp> dataInizio;
	public static volatile SingularAttribute<CblTLockApplicativo, String> idUtente;
	public static volatile SingularAttribute<CblTLockApplicativo, CblTEnte> cblTEnte;
}
