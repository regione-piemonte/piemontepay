/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.319+0200")
@StaticMetamodel(CblDTipoFileReport.class)
public class CblDTipoFileReport_ {
	public static volatile SingularAttribute<CblDTipoFileReport, Integer> id;
	public static volatile SingularAttribute<CblDTipoFileReport, String> codice;
	public static volatile SingularAttribute<CblDTipoFileReport, String> descrizione;
	public static volatile ListAttribute<CblDTipoFileReport, CblTReport> cblTReports;
}
