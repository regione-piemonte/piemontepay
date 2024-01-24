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

@Generated(value="Dali", date="2022-09-19T18:58:20.357+0200")
@StaticMetamodel(CblTCodiceVersamento.class)
public class CblTCodiceVersamento_ extends DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<CblTCodiceVersamento, Long> id;
	public static volatile SingularAttribute<CblTCodiceVersamento, String> idEnte;
	public static volatile SingularAttribute<CblTCodiceVersamento, String> codiceVersamento;
	public static volatile SingularAttribute<CblTCodiceVersamento, String> codiceFiscaleEnte;
	public static volatile SingularAttribute<CblTCodiceVersamento, Timestamp> dataFineValidita;
	public static volatile SingularAttribute<CblTCodiceVersamento, Timestamp> dataInizioValidita;
	public static volatile SingularAttribute<CblTCodiceVersamento, String> descrizioneVersamento;
	public static volatile SingularAttribute<CblTCodiceVersamento, Boolean> flagAnnullato;
	public static volatile SingularAttribute<CblTCodiceVersamento, String> codiceModalitaIntegrazione;
	public static volatile SingularAttribute<CblTCodiceVersamento, Boolean> fattura;
	public static volatile ListAttribute<CblTCodiceVersamento, CblTCatalogo> cblTCatalogos;
}
