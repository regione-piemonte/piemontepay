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

@Generated(value="Dali", date="2022-09-19T18:58:20.427+0200")
@StaticMetamodel(CblTFileReport.class)
public class CblTFileReport_ {
	public static volatile SingularAttribute<CblTFileReport, Long> id;
	public static volatile SingularAttribute<CblTFileReport, byte[]> contenutoFile;
	public static volatile SingularAttribute<CblTFileReport, Date> dataInserimento;
	public static volatile SingularAttribute<CblTFileReport, Date> dataModifica;
	public static volatile SingularAttribute<CblTFileReport, String> nomeFile;
	public static volatile SingularAttribute<CblTFileReport, Date> dataInizioElaborazione;
	public static volatile ListAttribute<CblTFileReport, CblTReport> cblTReports;
}
