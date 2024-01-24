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

@Generated(value="Dali", date="2018-12-17T11:54:24.548+0100")
@StaticMetamodel(RiferimentoContabile.class)
public class RiferimentoContabile_ extends AbstractPropagableParentEntity_ {
    public static volatile SingularAttribute<RiferimentoContabile, Long> id;
    public static volatile SingularAttribute<RiferimentoContabile, Integer> annoAccertamento;
    public static volatile SingularAttribute<RiferimentoContabile, Integer> annoEsercizio;
    public static volatile SingularAttribute<RiferimentoContabile, Date> dataFineValidita;
    public static volatile SingularAttribute<RiferimentoContabile, Date> dataInizioValidita;
    public static volatile SingularAttribute<RiferimentoContabile, String> datoSpecificoRiscossione;
    public static volatile SingularAttribute<RiferimentoContabile, Tassonomia > tassonomia;
    //	public static volatile SingularAttribute<RiferimentoContabile, String> descrizioneDatoSpecificoRiscossione;
    public static volatile SingularAttribute<RiferimentoContabile, String> livelloPdc;
    public static volatile SingularAttribute<RiferimentoContabile, Integer> numeroAccertamento;
    public static volatile SingularAttribute<RiferimentoContabile, Integer> numeroArticolo;
    public static volatile SingularAttribute<RiferimentoContabile, String> numeroCapitolo;
    public static volatile SingularAttribute<RiferimentoContabile, String> titolo;
    public static volatile SingularAttribute<RiferimentoContabile, Boolean> flagAnnullato;
    public static volatile SingularAttribute<RiferimentoContabile, Boolean> flagMbSecondario;
    public static volatile SingularAttribute<RiferimentoContabile, Boolean> flagMbPrimario;
    public static volatile SingularAttribute<RiferimentoContabile, String> categoria;
    public static volatile SingularAttribute<RiferimentoContabile, String> tipologia;
    //	public static volatile SingularAttribute<RiferimentoContabile, TipologiaDatoSpecificoRiscossione> tipologiaDatoSpecificoRiscossione;
    public static volatile SingularAttribute<RiferimentoContabile, String> codiceTipologiaDatoSpecificoRiscossione;
    public static volatile SingularAttribute<RiferimentoContabile, CodiceVersamento> codiceVersamento;
    public static volatile SingularAttribute<RiferimentoContabile, String> chiaveIntersistema;
    public static volatile ListAttribute<RiferimentoContabile, StoricoRiferimentoContabile> storico;
    public static volatile SingularAttribute<RiferimentoContabile, String> descrizioneDatoSpecificoRiscossione;
    public static volatile ListAttribute<RiferimentoContabile, RiferimentoContabile> riferimentiContabiliSecondariCollegati;

}

