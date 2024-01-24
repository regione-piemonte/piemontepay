/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.782+0200")
@StaticMetamodel(DatiSingolaRevoca.class)
public class DatiSingolaRevoca_ {
	public static volatile SingularAttribute<DatiSingolaRevoca, String> causaleRevoca;
	public static volatile SingularAttribute<DatiSingolaRevoca, String> datiAggiuntiviRevoca;
	public static volatile SingularAttribute<DatiSingolaRevoca, String> iur;
	public static volatile SingularAttribute<DatiSingolaRevoca, BigDecimal> singoloImportoRevocato;
	public static volatile SingularAttribute<DatiSingolaRevoca, Rr> rr;
}
