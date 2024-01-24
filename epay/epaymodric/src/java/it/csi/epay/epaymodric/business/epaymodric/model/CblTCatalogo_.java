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

@Generated(value="Dali", date="2022-09-19T18:58:20.340+0200")
@StaticMetamodel(CblTCatalogo.class)
public class CblTCatalogo_ extends DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<CblTCatalogo, Long> id;
	public static volatile SingularAttribute<CblTCatalogo, Integer> accertamentoAnno;
	public static volatile SingularAttribute<CblTCatalogo, String> accertamentoDesc;
	public static volatile SingularAttribute<CblTCatalogo, Integer> accertamentoNro;
	public static volatile SingularAttribute<CblTCatalogo, Integer> annoEsercizio;
	public static volatile SingularAttribute<CblTCatalogo, BigDecimal> articolo;
	public static volatile SingularAttribute<CblTCatalogo, String> capitolo;
	public static volatile SingularAttribute<CblTCatalogo, String> categoria;
	public static volatile SingularAttribute<CblTCatalogo, String> codiceFiscaleEnte;
	public static volatile SingularAttribute<CblTCatalogo, String> codiceVersamento;
	public static volatile SingularAttribute<CblTCatalogo, Timestamp> dataFineValidita;
	public static volatile SingularAttribute<CblTCatalogo, Timestamp> dataInizioValidita;
	public static volatile SingularAttribute<CblTCatalogo, String> datiSpecificiRiscossione;
	public static volatile SingularAttribute<CblTCatalogo, String> descrizioneVersamento;
	public static volatile SingularAttribute<CblTCatalogo, String> idEnte;
	public static volatile SingularAttribute<CblTCatalogo, String> pianoDeiConti;
	public static volatile SingularAttribute<CblTCatalogo, String> sottovoce;
	public static volatile SingularAttribute<CblTCatalogo, String> tipologia;
	public static volatile SingularAttribute<CblTCatalogo, String> titolo;
	public static volatile SingularAttribute<CblTCatalogo, Boolean> flagAnnullato;
	public static volatile SingularAttribute<CblTCatalogo, String> chiaveIntersistema;
	public static volatile SingularAttribute<CblTCatalogo, CblTCodiceVersamento> cblTCodiceVersamento;
}
