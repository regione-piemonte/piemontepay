/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.268+0200")
@StaticMetamodel(CblDModalitaProvvisori.class)
public class CblDModalitaProvvisori_ {
	public static volatile SingularAttribute<CblDModalitaProvvisori, Long> id;
	public static volatile SingularAttribute<CblDModalitaProvvisori, String> descrizioneModalita;
	public static volatile SingularAttribute<CblDModalitaProvvisori, BigDecimal> modalitaAcquisizione;
}
