/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.784+0200")
@StaticMetamodel(DatiSingoloEsito.class)
public class DatiSingoloEsito_ {
	public static volatile SingularAttribute<DatiSingoloEsito, String> causaleEsito;
	public static volatile SingularAttribute<DatiSingoloEsito, String> datiAggiuntiviEsito;
	public static volatile SingularAttribute<DatiSingoloEsito, String> iur;
	public static volatile SingularAttribute<DatiSingoloEsito, BigDecimal> singoloImportoRevocato;
	public static volatile SingularAttribute<DatiSingoloEsito, Er> er1;
	public static volatile SingularAttribute<DatiSingoloEsito, Er> er2;
}
