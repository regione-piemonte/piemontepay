/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-25T12:10:21.803+0100")
@StaticMetamodel(StoricoRiferimentoContabile.class)
public class StoricoRiferimentoContabile_ extends AbstractPropagableParentEntity_ {
	public static volatile SingularAttribute<StoricoRiferimentoContabile, Long> id;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, RiferimentoContabile> riferimentoContabile;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, StoricoRiferimentoContabile> storicoRiferimentoContabile;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, Integer> annoAccertamento;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, Integer> annoEsercizio;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, Date> dataFineValidita;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, Date> dataInizioValidita;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, String> datoSpecificoRiscossione;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, String> descrizioneDatoSpecificoRiscossione;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, String> livelloPdc;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, Integer> numeroAccertamento;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, Integer> numeroArticolo;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, String> numeroCapitolo;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, String> titolo;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, String> categoria;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, String> tipologia;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, TipologiaDatoSpecificoRiscossione> tipologiaDatoSpecificoRiscossione;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, CodiceVersamento> codiceVersamento;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, String> chiaveIntersistema;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, Boolean> flagPosizionePrecedente;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, Boolean> flagMbSecondario;
	public static volatile SingularAttribute<StoricoRiferimentoContabile, Boolean> flagMbPrimario;
	public static volatile ListAttribute<StoricoRiferimentoContabile, StoricoRiferimentoContabile> storico;
}
