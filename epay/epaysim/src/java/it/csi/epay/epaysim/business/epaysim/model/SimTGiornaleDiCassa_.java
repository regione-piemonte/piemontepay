/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-17T12:57:02.814+0200")
@StaticMetamodel(SimTGiornaleDiCassa.class)
public class SimTGiornaleDiCassa_ extends DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<SimTGiornaleDiCassa, Integer> id;
	public static volatile SingularAttribute<SimTGiornaleDiCassa, String> xml;
    public static volatile SingularAttribute<SimTGiornaleDiCassa, String> identificativoFlussoBT;
	public static volatile ListAttribute<SimTGiornaleDiCassa, SimTProvvisorio> simTProvvisorios;
}
