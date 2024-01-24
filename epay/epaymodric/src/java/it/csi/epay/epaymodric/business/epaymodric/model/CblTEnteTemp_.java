/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.418+0200")
@StaticMetamodel(CblTEnteTemp.class)
public class CblTEnteTemp_ extends DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<CblTEnteTemp, Long> id;
	public static volatile SingularAttribute<CblTEnteTemp, String> codiceFiscale;
	public static volatile SingularAttribute<CblTEnteTemp, String> denominazione;
	public static volatile SingularAttribute<CblTEnteTemp, String> emailEnte;
	public static volatile SingularAttribute<CblTEnteTemp, Boolean> entePlurintermediato;
	public static volatile SingularAttribute<CblTEnteTemp, Boolean> flagAccertamento;
	public static volatile SingularAttribute<CblTEnteTemp, Boolean> flagRicezioneErrori;
	public static volatile SingularAttribute<CblTEnteTemp, Boolean> flagRiconciliazione;
	public static volatile SingularAttribute<CblTEnteTemp, Integer> giornoSchedulazione;
	public static volatile SingularAttribute<CblTEnteTemp, String> ibanTesoreria;
	public static volatile SingularAttribute<CblTEnteTemp, String> idEnte;
	public static volatile SingularAttribute<CblTEnteTemp, Integer> modalitaAcquisizioneProvvisori;
	public static volatile SingularAttribute<CblTEnteTemp, Integer> periodicitaSchedulazione;
	public static volatile SingularAttribute<CblTEnteTemp, String> idOperazione;
}
