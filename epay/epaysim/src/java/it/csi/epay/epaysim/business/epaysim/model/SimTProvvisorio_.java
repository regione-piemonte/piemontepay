/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-17T12:57:02.815+0200")
@StaticMetamodel(SimTProvvisorio.class)
public class SimTProvvisorio_ extends DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<SimTProvvisorio, Integer> id;
	public static volatile SingularAttribute<SimTProvvisorio, Integer> annoEsercizio;
	public static volatile SingularAttribute<SimTProvvisorio, Integer> annoProvvisorio;
	public static volatile SingularAttribute<SimTProvvisorio, String> causale;
	public static volatile SingularAttribute<SimTProvvisorio, String> codiceFiscaleEnte;
	public static volatile SingularAttribute<SimTProvvisorio, Date> dataMovimento;
	public static volatile SingularAttribute<SimTProvvisorio, String> identificativoFlusso;
	public static volatile SingularAttribute<SimTProvvisorio, BigDecimal> importoProvvisorio;
	public static volatile SingularAttribute<SimTProvvisorio, Integer> numeroProvvisorio;
	public static volatile SingularAttribute<SimTProvvisorio, String> stato;
	public static volatile SingularAttribute<SimTProvvisorio, SimTGiornaleDiCassa> simTGiornaleDiCassa;
}
