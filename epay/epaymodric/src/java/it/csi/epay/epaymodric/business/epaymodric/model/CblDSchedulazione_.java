/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.275+0200")
@StaticMetamodel(CblDSchedulazione.class)
public class CblDSchedulazione_ {
	public static volatile SingularAttribute<CblDSchedulazione, Long> id;
	public static volatile SingularAttribute<CblDSchedulazione, String> descrizionePeriodicita;
	public static volatile SingularAttribute<CblDSchedulazione, BigDecimal> periodicitaSchedulazione;
}
