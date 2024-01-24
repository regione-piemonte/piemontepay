/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.350+0200")
@StaticMetamodel(CblTCatalogoTemp.class)
public class CblTCatalogoTemp_ extends DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<CblTCatalogoTemp, Long> id;
	public static volatile SingularAttribute<CblTCatalogoTemp, Integer> accertamentoAnno;
	public static volatile SingularAttribute<CblTCatalogoTemp, String> accertamentoDesc;
	public static volatile SingularAttribute<CblTCatalogoTemp, Integer> accertamentoNro;
	public static volatile SingularAttribute<CblTCatalogoTemp, Integer> annoEsercizio;
	public static volatile SingularAttribute<CblTCatalogoTemp, BigDecimal> articolo;
	public static volatile SingularAttribute<CblTCatalogoTemp, String> capitolo;
	public static volatile SingularAttribute<CblTCatalogoTemp, String> categoria;
	public static volatile SingularAttribute<CblTCatalogoTemp, String> codiceFiscaleEnte;
	public static volatile SingularAttribute<CblTCatalogoTemp, String> codiceVersamento;
	public static volatile SingularAttribute<CblTCatalogoTemp, Timestamp> dataFineValidita;
	public static volatile SingularAttribute<CblTCatalogoTemp, Timestamp> dataInizioValidita;
	public static volatile SingularAttribute<CblTCatalogoTemp, String> datiSpecificiRiscossione;
	public static volatile SingularAttribute<CblTCatalogoTemp, String> descrizioneVersamento;
	public static volatile SingularAttribute<CblTCatalogoTemp, String> idEnte;
	public static volatile SingularAttribute<CblTCatalogoTemp, String> pianoDeiConti;
	public static volatile SingularAttribute<CblTCatalogoTemp, String> sottovoce;
	public static volatile SingularAttribute<CblTCatalogoTemp, String> tipologia;
	public static volatile SingularAttribute<CblTCatalogoTemp, String> titolo;
	public static volatile SingularAttribute<CblTCatalogoTemp, String> idOperazione;
	public static volatile SingularAttribute<CblTCatalogoTemp, String> azione;
	public static volatile SingularAttribute<CblTCatalogoTemp, Boolean> flagAnnullato;
	public static volatile SingularAttribute<CblTCatalogoTemp, String> chiaveIntersistema;
}
