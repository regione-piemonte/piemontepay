/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.489+0200")
@StaticMetamodel(CblTReport.class)
public class CblTReport_ {
	public static volatile SingularAttribute<CblTReport, Long> id;
	public static volatile SingularAttribute<CblTReport, String> codiceFiscaleEnte;
	public static volatile SingularAttribute<CblTReport, Long> idUtente;
	public static volatile SingularAttribute<CblTReport, String> codiceFiscaleUtente;
	public static volatile SingularAttribute<CblTReport, String> nominativoExport;
	public static volatile SingularAttribute<CblTReport, Date> dataOraPrenotazione;
	public static volatile ListAttribute<CblTReport, CblTDatiFiltroReport> cblTDatiFiltroReports;
	public static volatile SingularAttribute<CblTReport, CblDStatoReport> cblDStatoReport;
	public static volatile SingularAttribute<CblTReport, CblDTipoFileReport> cblDTipoFileReport;
	public static volatile SingularAttribute<CblTReport, CblDTipoReport> cblDTipoReport;
	public static volatile SingularAttribute<CblTReport, CblTFileReport> cblTFileReport;
	public static volatile SingularAttribute<CblTReport, CblTEnte> cblTEnte;
}
