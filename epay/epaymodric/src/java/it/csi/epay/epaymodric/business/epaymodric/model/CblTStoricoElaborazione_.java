/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.548+0200")
@StaticMetamodel(CblTStoricoElaborazione.class)
public class CblTStoricoElaborazione_ extends DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<CblTStoricoElaborazione, Long> id;
	public static volatile SingularAttribute<CblTStoricoElaborazione, Timestamp> dataElaborazione;
	public static volatile SingularAttribute<CblTStoricoElaborazione, Timestamp> dataFine;
	public static volatile SingularAttribute<CblTStoricoElaborazione, Timestamp> dataInizio;
	public static volatile SingularAttribute<CblTStoricoElaborazione, String> idEnte;
	public static volatile SingularAttribute<CblTStoricoElaborazione, String> listaFlussi;
	public static volatile SingularAttribute<CblTStoricoElaborazione, String> msgErrore;
	public static volatile SingularAttribute<CblTStoricoElaborazione, String> statoElaborazione;
	public static volatile SingularAttribute<CblTStoricoElaborazione, CblDErrore> cblDErrore;
	public static volatile SingularAttribute<CblTStoricoElaborazione, CblTElaborazione> cblTElaborazione;
}
