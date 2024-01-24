/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-27T10:52:25.582+0100")
@StaticMetamodel(MappaturaRiferimentoContabileEpaypa.class)
public class MappaturaRiferimentoContabileEpaypa_ {
	public static volatile SingularAttribute<MappaturaRiferimentoContabileEpaypa, Integer> id;
	public static volatile SingularAttribute<MappaturaRiferimentoContabileEpaypa, Integer> annoAccertamento;
	public static volatile SingularAttribute<MappaturaRiferimentoContabileEpaypa, Integer> annoEsercizio;
	public static volatile SingularAttribute<MappaturaRiferimentoContabileEpaypa, String> categoria;
	public static volatile SingularAttribute<MappaturaRiferimentoContabileEpaypa, String> codiceTipologiaDatoSpecificoRiscossione;
	public static volatile SingularAttribute<MappaturaRiferimentoContabileEpaypa, Timestamp> dataInizioValidita;
	public static volatile SingularAttribute<MappaturaRiferimentoContabileEpaypa, Timestamp> dataFineValidita;
	public static volatile SingularAttribute<MappaturaRiferimentoContabileEpaypa, String> datoSpecificoRiscossione;
	public static volatile SingularAttribute<MappaturaRiferimentoContabileEpaypa, String> descrizione;
	public static volatile SingularAttribute<MappaturaRiferimentoContabileEpaypa, String> descrizioneDatoSpecificoRiscossione;
	public static volatile SingularAttribute<MappaturaRiferimentoContabileEpaypa, Integer> idCodiceVersamento;
	public static volatile SingularAttribute<MappaturaRiferimentoContabileEpaypa, String> livelloPdc;
	public static volatile SingularAttribute<MappaturaRiferimentoContabileEpaypa, Integer> numeroAccertamento;
	public static volatile SingularAttribute<MappaturaRiferimentoContabileEpaypa, Integer> numeroArticolo;
	public static volatile SingularAttribute<MappaturaRiferimentoContabileEpaypa, String> numeroCapitolo;
	public static volatile SingularAttribute<MappaturaRiferimentoContabileEpaypa, String> tipologia;
	public static volatile SingularAttribute<MappaturaRiferimentoContabileEpaypa, String> titolo;
}
