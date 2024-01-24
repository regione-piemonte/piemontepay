/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.370+0200")
@StaticMetamodel(CblTCodiciVersamentoConfig.class)
public class CblTCodiciVersamentoConfig_ {
	public static volatile SingularAttribute<CblTCodiciVersamentoConfig, Long> id;
	public static volatile SingularAttribute<CblTCodiciVersamentoConfig, String> codiceVersamento;
	public static volatile SingularAttribute<CblTCodiciVersamentoConfig, Boolean> flgApplicazioneEpay;
	public static volatile SingularAttribute<CblTCodiciVersamentoConfig, String> idEnte;
	public static volatile SingularAttribute<CblTCodiciVersamentoConfig, String> note;
}
