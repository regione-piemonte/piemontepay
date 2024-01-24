/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.914+0200")
@StaticMetamodel(StatoInvioFlussoRiversamento.class)
public class StatoInvioFlussoRiversamento_ {
	public static volatile SingularAttribute<StatoInvioFlussoRiversamento, Long> codStato;
	public static volatile SingularAttribute<StatoInvioFlussoRiversamento, String> descrizione;
	public static volatile SingularAttribute<StatoInvioFlussoRiversamento, String> descrizioneestesa;
	public static volatile ListAttribute<StatoInvioFlussoRiversamento, FlussoRiversamento> flussoRiversamentos1;
	public static volatile ListAttribute<StatoInvioFlussoRiversamento, FlussoRiversamento> flussoRiversamentos2;
}
