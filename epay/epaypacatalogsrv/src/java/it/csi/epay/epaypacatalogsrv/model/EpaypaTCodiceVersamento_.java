/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-25T12:10:21.762+0100")
@StaticMetamodel(EpaypaTCodiceVersamento.class)
public class EpaypaTCodiceVersamento_ {
	public static volatile SingularAttribute<EpaypaTCodiceVersamento, Integer> idCodiceVersamento;
	public static volatile SingularAttribute<EpaypaTCodiceVersamento, String> codVersamento;
	public static volatile SingularAttribute<EpaypaTCodiceVersamento, String> descrizione;
	public static volatile SingularAttribute<EpaypaTCodiceVersamento, Integer> idEnte;
	public static volatile SingularAttribute<EpaypaTCodiceVersamento, String> codiceModalitaIntegrazione;
}
