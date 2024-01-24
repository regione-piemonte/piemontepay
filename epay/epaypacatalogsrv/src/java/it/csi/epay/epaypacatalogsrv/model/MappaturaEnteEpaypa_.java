/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-01-08T12:07:53.494+0100")
@StaticMetamodel(MappaturaEnteEpaypa.class)
public class MappaturaEnteEpaypa_ {
	public static volatile SingularAttribute<MappaturaEnteEpaypa, Integer> id;
	public static volatile SingularAttribute<MappaturaEnteEpaypa, String> bic;
	public static volatile SingularAttribute<MappaturaEnteEpaypa, String> cap;
	public static volatile SingularAttribute<MappaturaEnteEpaypa, String> civico;
	public static volatile SingularAttribute<MappaturaEnteEpaypa, String> codiceFiscale;
	public static volatile SingularAttribute<MappaturaEnteEpaypa, String> codiceModalitaAcquisizioneProvvisori;
	public static volatile SingularAttribute<MappaturaEnteEpaypa, String> codicePeriodicitaSchedulazioneRiconciliazione;
	public static volatile SingularAttribute<MappaturaEnteEpaypa, String> codiceTipologiaAccertamento;
	public static volatile SingularAttribute<MappaturaEnteEpaypa, Boolean> flagAccertamento;
	public static volatile SingularAttribute<MappaturaEnteEpaypa, Boolean> flagEntePlurintermediato;
	public static volatile SingularAttribute<MappaturaEnteEpaypa, Boolean> flagQualsiasiCodiceVersamento;
	public static volatile SingularAttribute<MappaturaEnteEpaypa, Boolean> flagRicezioneErrori;
	public static volatile SingularAttribute<MappaturaEnteEpaypa, Boolean> flagRicezioneFlussoBaseRendicontazione;
	public static volatile SingularAttribute<MappaturaEnteEpaypa, Boolean> flagRiconciliazioneVersamenti;
	public static volatile SingularAttribute<MappaturaEnteEpaypa, Integer> giornoSchedulazione;
	public static volatile SingularAttribute<MappaturaEnteEpaypa, String> iban;
	public static volatile SingularAttribute<MappaturaEnteEpaypa, Integer> idUtenteAmministratore;
	public static volatile SingularAttribute<MappaturaEnteEpaypa, String> indirizzo;
	public static volatile SingularAttribute<MappaturaEnteEpaypa, String> localita;
	public static volatile SingularAttribute<MappaturaEnteEpaypa, String> siglaProvincia;
}
