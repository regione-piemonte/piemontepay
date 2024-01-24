/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.412+0200")
@StaticMetamodel(CblTEnte.class)
public class CblTEnte_ extends DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<CblTEnte, Long> id;
	public static volatile SingularAttribute<CblTEnte, String> codiceFiscale;
	public static volatile SingularAttribute<CblTEnte, String> denominazione;
	public static volatile SingularAttribute<CblTEnte, String> emailEnte;
	public static volatile SingularAttribute<CblTEnte, Boolean> entePlurintermediato;
	public static volatile SingularAttribute<CblTEnte, Boolean> flagAccertamento;
	public static volatile SingularAttribute<CblTEnte, Boolean> flagRicezioneErrori;
	public static volatile SingularAttribute<CblTEnte, Boolean> flagRiconciliazione;
	public static volatile SingularAttribute<CblTEnte, Integer> giornoSchedulazione;
	public static volatile SingularAttribute<CblTEnte, String> ibanTesoreria;
	public static volatile SingularAttribute<CblTEnte, String> idEnte;
	public static volatile SingularAttribute<CblTEnte, Integer> modalitaAcquisizioneProvvisori;
	public static volatile SingularAttribute<CblTEnte, Integer> periodicitaSchedulazione;
	public static volatile ListAttribute<CblTEnte, CblTFlussoOrigine> cblTFlussoOrigines;
	public static volatile ListAttribute<CblTEnte, CblTElaborazione> cblTElaboraziones;
}
