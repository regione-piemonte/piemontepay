/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.363+0200")
@StaticMetamodel(CblTCodiceVersamentoTemp.class)
public class CblTCodiceVersamentoTemp_ extends DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<CblTCodiceVersamentoTemp, Long> id;
	public static volatile SingularAttribute<CblTCodiceVersamentoTemp, String> azione;
	public static volatile SingularAttribute<CblTCodiceVersamentoTemp, String> codiceFiscaleEnte;
	public static volatile SingularAttribute<CblTCodiceVersamentoTemp, String> codiceVersamento;
	public static volatile SingularAttribute<CblTCodiceVersamentoTemp, Timestamp> dataFineValidita;
	public static volatile SingularAttribute<CblTCodiceVersamentoTemp, Timestamp> dataInizioValidita;
	public static volatile SingularAttribute<CblTCodiceVersamentoTemp, String> descrizioneVersamento;
	public static volatile SingularAttribute<CblTCodiceVersamentoTemp, Boolean> flagAnnullato;
	public static volatile SingularAttribute<CblTCodiceVersamentoTemp, String> idEnte;
	public static volatile SingularAttribute<CblTCodiceVersamentoTemp, String> idOperazione;
	public static volatile SingularAttribute<CblTCodiceVersamentoTemp, String> codiceModalitaIntegrazione;
	public static volatile SingularAttribute<CblTCodiceVersamentoTemp, Boolean> fattura;
}
