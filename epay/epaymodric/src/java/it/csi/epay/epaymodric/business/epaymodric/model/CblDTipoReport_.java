/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.326+0200")
@StaticMetamodel(CblDTipoReport.class)
public class CblDTipoReport_ {
	public static volatile SingularAttribute<CblDTipoReport, Long> id;
	public static volatile SingularAttribute<CblDTipoReport, String> codice;
	public static volatile SingularAttribute<CblDTipoReport, String> descrizione;
	public static volatile ListAttribute<CblDTipoReport, CblTReport> cblTReports;
}
