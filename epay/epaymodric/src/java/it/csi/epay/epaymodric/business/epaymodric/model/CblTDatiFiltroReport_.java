/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.390+0200")
@StaticMetamodel(CblTDatiFiltroReport.class)
public class CblTDatiFiltroReport_ {
	public static volatile SingularAttribute<CblTDatiFiltroReport, Long> id;
	public static volatile SingularAttribute<CblTDatiFiltroReport, String> nomeFiltro;
	public static volatile SingularAttribute<CblTDatiFiltroReport, String> valore;
	public static volatile SingularAttribute<CblTDatiFiltroReport, CblDTipoCampoFiltro> cblDTipoCampoFiltro;
	public static volatile SingularAttribute<CblTDatiFiltroReport, CblTReport> cblTReport;
}
