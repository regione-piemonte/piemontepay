/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.796+0200")
@StaticMetamodel(FasciaCostoServizio.class)
public class FasciaCostoServizio_ {
	public static volatile SingularAttribute<FasciaCostoServizio, BigDecimal> costoFisso;
	public static volatile SingularAttribute<FasciaCostoServizio, Timestamp> dataValidita;
	public static volatile SingularAttribute<FasciaCostoServizio, BigDecimal> importoMassimoFasci;
	public static volatile SingularAttribute<FasciaCostoServizio, Boolean> valido;
	public static volatile SingularAttribute<FasciaCostoServizio, BigDecimal> valoreCommissione;
	public static volatile SingularAttribute<FasciaCostoServizio, InformativaPsp> informativaPsp;
}
