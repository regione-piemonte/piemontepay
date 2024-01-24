/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.398+0200")
@StaticMetamodel(CblTElaborazione.class)
public class CblTElaborazione_ extends DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<CblTElaborazione, Long> id;
	public static volatile SingularAttribute<CblTElaborazione, Timestamp> dataElaborazione;
	public static volatile SingularAttribute<CblTElaborazione, Timestamp> dataFine;
	public static volatile SingularAttribute<CblTElaborazione, Timestamp> dataInizio;
	public static volatile SingularAttribute<CblTElaborazione, String> listaFlussi;
	public static volatile SingularAttribute<CblTElaborazione, String> msgErrore;
	public static volatile ListAttribute<CblTElaborazione, CblTFlussoOrigine> cblTFlussoOrigines;
	public static volatile SingularAttribute<CblTElaborazione, CblDErrore> cblDErrore;
	public static volatile ListAttribute<CblTElaborazione, CblTStoricoElaborazione> cblTStoricoElaboraziones;
	public static volatile SingularAttribute<CblTElaborazione, CblDStatoElaborazione> cblDStatoElaborazione;
	public static volatile SingularAttribute<CblTElaborazione, CblTEnte> cblTEnte;
}
